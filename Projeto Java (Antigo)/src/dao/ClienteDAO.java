package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import model.Cliente;

public class ClienteDAO {

	public static void cadastrar(Cliente cliente) {
		String sql = "INSERT INTO cliente (nome, email, cpf, endereco, contato) VALUES (?, ?, ?, ?, ?)";	
		
		PreparedStatement ps = null;
		try {
			Connection conn = Conexao.getConexao();
			ps = conn.prepareStatement(sql);
			ps.setString(1, cliente.nome);
			ps.setString(2, cliente.email);
			ps.setString(3, cliente.cpf);
			ps.setString(4, cliente.endereco);
			ps.setString(5, cliente.contato);
			ps.execute();
		} 
		catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.toString());
		}
	}

	public static void excluir(int idCliente) {
		String sql = "DELETE FROM cliente WHERE id = ?";	
		
		PreparedStatement ps = null;
		try {
			Connection conn = Conexao.getConexao();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, idCliente);
			ps.execute();
		} 
		catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.toString());
		}
	}

	public static List<Cliente> getClientes() {
		List<Cliente> listaClientes = new ArrayList<Cliente>();
		
		String sql = "SELECT id, nome, email, cpf, endereco, contato FROM cliente ORDER BY nome";
	
		PreparedStatement ps = null;
		try {
			Connection conn = Conexao.getConexao();
			ps = conn.prepareStatement(sql);
			ResultSet rs =  ps.executeQuery();
			
			if(rs != null) {
				while (rs.next()) {
					Cliente cli = new Cliente();
					cli.id = rs.getInt(1);
					cli.nome = rs.getString(2);
					cli.email = rs.getString(3);
					cli.cpf = rs.getString(4);
					cli.endereco = rs.getString(5);
					cli.contato = rs.getString(6);
					listaClientes.add(cli);
				}
			}			
		} 
		catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.toString());
		}
		return listaClientes;
	}
	
	public static void editarNome(int id, String nome) {
		String sql = "UPDATE cliente SET nome = ? WHERE id = ?";	
		
		PreparedStatement ps = null;
		try {
			Connection conn = Conexao.getConexao();
			ps = conn.prepareStatement(sql);
			ps.setString(1, nome);
			ps.setInt(2, id);
			ps.execute();
		} 
		catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.toString());
		}
	}
	
	public static void editarEmail(int id, String email) {
		String sql = "UPDATE cliente SET email = ? WHERE id = ?";	
		
		PreparedStatement ps = null;
		try {
			Connection conn = Conexao.getConexao();
			ps = conn.prepareStatement(sql);
			ps.setString(1, email);
			ps.setInt(2, id);
			ps.execute();
		} 
		catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.toString());
		}
	}
	
	public static void editarCPF(int id, String cpf) {
		String sql = "UPDATE cliente SET cpf = ? WHERE id = ?";	
		
		PreparedStatement ps = null;
		try {
			Connection conn = Conexao.getConexao();
			ps = conn.prepareStatement(sql);
			ps.setString(1, cpf);
			ps.setInt(2, id);
			ps.execute();
		} 
		catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.toString());
		}
	}
	
	public static void editarEndereco(int id, String endereco) {
		String sql = "UPDATE cliente SET endereco = ? WHERE id = ?";	
		
		PreparedStatement ps = null;
		try {
			Connection conn = Conexao.getConexao();
			ps = conn.prepareStatement(sql);
			ps.setString(1, endereco);
			ps.setInt(2, id);
			ps.execute();
		} 
		catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.toString());
		}
	}
	
	public static void editarContato(int id, String contato) {
		String sql = "UPDATE cliente SET contato = ? WHERE id = ?";	
		
		PreparedStatement ps = null;
		try {
			Connection conn = Conexao.getConexao();
			ps = conn.prepareStatement(sql);
			ps.setString(1, contato);
			ps.setInt(2, id);
			ps.execute();
		} 
		catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.toString());
		}
	}
}