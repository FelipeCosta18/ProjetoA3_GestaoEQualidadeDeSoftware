package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import model.Livro;
import model.Genero;

public class LivroDAO {

	public static void cadastrar(Livro livro) {
		String sql = "INSERT INTO livro (titulo, autor, idGenero, quantidade, isbn) VALUES (?, ?, ?, ?, ?)";	
		
		PreparedStatement ps = null;
		try {
			Connection conn = Conexao.getConexao();
			ps = conn.prepareStatement(sql);
			ps.setString(1, livro.titulo);
			ps.setString(2, livro.autor);
			ps.setInt(3, livro.genero.id);
			ps.setInt(4, livro.quantidade);
			ps.setString(5, livro.isbn);
			ps.execute();
		} 
		catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.toString());
		}
	}

	public static void excluir(int idLivro) {
		String sql = "DELETE FROM livro WHERE id = ?";	
		
		PreparedStatement ps = null;
		try {
			Connection conn = Conexao.getConexao();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, idLivro);
			ps.execute();
		} 
		catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.toString());
		}
	}

	public static List<Livro> getLivros() {
		List<Livro> listaLivros = new ArrayList<Livro>();
		
		String sql = "SELECT l.id, l.titulo, l.autor, l.quantidade, l.isbn, g.id, g.nome FROM livro l" + 
				" INNER JOIN genero g ON g.id = l.idGenero ORDER BY l.titulo";	
		
		PreparedStatement ps = null;
		try {
			Connection conn = Conexao.getConexao();
			ps = conn.prepareStatement(sql);
			ResultSet rs =  ps.executeQuery();
			
			if(rs != null) {
				while (rs.next()) {
					Genero gen = new Genero();
					gen.id = rs.getInt(6);
					gen.nome = rs.getString(7);

					Livro liv = new Livro();
					liv.id = rs.getInt(1);
					liv.titulo = rs.getString(2);
					liv.autor = rs.getString(3);
					liv.quantidade = rs.getInt(4);
					liv.isbn = rs.getString(5);
					liv.genero = gen;
					listaLivros.add(liv);
				}
			}			
		} 
		catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.toString());
		}
		return listaLivros;
	}
	
	public static void editarTitulo(int id, String novoTitulo) {
		String sql = "UPDATE livro SET titulo = ? WHERE id = ?";	
		
		PreparedStatement ps = null;
		try {
			Connection conn = Conexao.getConexao();
			ps = conn.prepareStatement(sql);
			ps.setString(1, novoTitulo);
			ps.setInt(2, id);
			ps.execute();
		} 
		catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.toString());
		}
	}
	
	public static void editarAutor(int id, String novoAutor) {
		String sql = "UPDATE livro SET autor = ? WHERE id = ?";	
		
		PreparedStatement ps = null;
		try {
			Connection conn = Conexao.getConexao();
			ps = conn.prepareStatement(sql);
			ps.setString(1, novoAutor);
			ps.setInt(2, id);
			ps.execute();
		} 
		catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.toString());
		}
	}
	
	public static void editarGenero(int id, int idGenero) {
		String sql = "UPDATE livro SET idGenero = ? WHERE id = ?";	
		
		PreparedStatement ps = null;
		try {
			Connection conn = Conexao.getConexao();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, idGenero);
			ps.setInt(2, id);
			ps.execute();
		} 
		catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.toString());
		}
	}
	
	public static void editarQuantidade(int id, int quantidade) {
		String sql = "UPDATE livro SET quantidade = ? WHERE id = ?";	
		
		PreparedStatement ps = null;
		try {
			Connection conn = Conexao.getConexao();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, quantidade);
			ps.setInt(2, id);
			ps.execute();
		} 
		catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.toString());
		}
	}
	
	public static void editarISBN(int id, String isbn) {
		String sql = "UPDATE livro SET isbn = ? WHERE id = ?";	
		
		PreparedStatement ps = null;
		try {
			Connection conn = Conexao.getConexao();
			ps = conn.prepareStatement(sql);
			ps.setString(1, isbn);
			ps.setInt(2, id);
			ps.execute();
		} 
		catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.toString());
		}
	}
}