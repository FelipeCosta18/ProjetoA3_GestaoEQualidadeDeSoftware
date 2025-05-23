package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import model.Emprestimo;
import model.Cliente;
import model.Livro;

public class EmprestimoDAO {

	public static void cadastrar(Emprestimo emprestimo) {
		String sql = "INSERT INTO emprestimo (idCliente, idLivro, dataEmprestimo, dataDevolucao) VALUES (?, ?, ?, ?)";	
		
		PreparedStatement ps = null;
		try {
			Connection conn = Conexao.getConexao();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, emprestimo.cliente.id);
			ps.setInt(2, emprestimo.livro.id);
			ps.setString(3, emprestimo.dataEmprestimo);
			ps.setString(4, emprestimo.dataDevolucao);
			ps.execute();
		} 
		catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.toString());
		}
	}

	public static void excluir(int idEmprestimo) {
		String sql = "DELETE FROM emprestimo WHERE id = ?";	
		
		PreparedStatement ps = null;
		try {
			Connection conn = Conexao.getConexao();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, idEmprestimo);
			ps.execute();
		} 
		catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.toString());
		}
	}

	public static List<Emprestimo> getEmprestimos() {
		List<Emprestimo> listaEmprestimos = new ArrayList<Emprestimo>();
		
		String sql = "SELECT e.id, e.dataEmprestimo, e.dataDevolucao, c.id, c.nome, c.cpf, l.id, l.titulo "
				+ "FROM emprestimo e " 
				+ "INNER JOIN cliente c ON c.id = e.idCliente "
				+ "INNER JOIN livro l ON l.id = e.idLivro ORDER BY e.dataEmprestimo";	
		
		PreparedStatement ps = null;
		try {
			Connection conn = Conexao.getConexao();
			ps = conn.prepareStatement(sql);
			ResultSet rs =  ps.executeQuery();
			
			if(rs != null) {
				while (rs.next()) {
					Cliente cli = new Cliente();
					cli.id = rs.getInt(4);
					cli.nome = rs.getString(5);
					cli.cpf = rs.getString(6);

					Livro liv = new Livro();
					liv.id = rs.getInt(7);
					liv.titulo = rs.getString(8);
					
					Emprestimo emp = new Emprestimo();
					emp.id = rs.getInt(1);
					emp.dataEmprestimo = rs.getString(2);
					emp.dataDevolucao = rs.getString(3);
					emp.cliente = cli;
					emp.livro = liv;
					listaEmprestimos.add(emp);
				}
			}			
		} 
		catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.toString());
		}
		return listaEmprestimos;
	}
	
	public static void editarCliente(int id, int idCliente) {
		String sql = "UPDATE emprestimo SET idCliente = ? WHERE id = ?";	
		
		PreparedStatement ps = null;
		try {
			Connection conn = Conexao.getConexao();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, idCliente);
			ps.setInt(2, id);
			ps.execute();
		} 
		catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.toString());
		}
	}
	
	public static void editarLivro(int id, int idLivro) {
		String sql = "UPDATE emprestimo SET idLivro = ? WHERE id = ?";	
		
		PreparedStatement ps = null;
		try {
			Connection conn = Conexao.getConexao();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, idLivro);
			ps.setInt(2, id);
			ps.execute();
		} 
		catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.toString());
		}
	}
	
	public static void editarDataEmprestimo(int id, String dataEmprestimo) {
		String sql = "UPDATE emprestimo SET dataEmprestimo = ? WHERE id = ?";	
		
		PreparedStatement ps = null;
		try {
			Connection conn = Conexao.getConexao();
			ps = conn.prepareStatement(sql);
			ps.setString(1, dataEmprestimo);
			ps.setInt(2, id);
			ps.execute();
		} 
		catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.toString());
		}
	}
	
	public static void editarDataDevolucao(int id, String dataDevolucao) {
		String sql = "UPDATE emprestimo SET dataDevolucao = ? WHERE id = ?";	
		
		PreparedStatement ps = null;
		try {
			Connection conn = Conexao.getConexao();
			ps = conn.prepareStatement(sql);
			ps.setString(1, dataDevolucao);
			ps.setInt(2, id);
			ps.execute();
		} 
		catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.toString());
		}
	}
}