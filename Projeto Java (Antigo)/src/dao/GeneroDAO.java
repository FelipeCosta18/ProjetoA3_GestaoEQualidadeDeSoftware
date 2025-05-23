package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import model.Genero;

public class GeneroDAO {

	public static void cadastrar(Genero genero) {
		String sql = "INSERT INTO genero (nome) VALUES (?)";	
		
		PreparedStatement ps = null;
		try {
			Connection conn = Conexao.getConexao();
			ps = conn.prepareStatement(sql);
			ps.setString(1, genero.nome);
			ps.execute();
		}
		catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.toString());
		}
	}

	public static void editar(int id, String nome) {
		String sql = "UPDATE genero SET nome = ? WHERE id = ?";	
		
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

	public static void excluir(int idGenero) {
		String sql = "DELETE FROM genero WHERE id = ?";	
		
		PreparedStatement ps = null;
		try {
			Connection conn = Conexao.getConexao();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, idGenero);
			ps.execute();
		} 
		catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.toString());
		}
	}

	public static List<Genero> getGeneros() {
		List<Genero> listaGeneros = new ArrayList<Genero>();
		
		String sql = "SELECT id, nome FROM genero ORDER BY nome";
	
		PreparedStatement ps = null;
		try {
			Connection conn = Conexao.getConexao();
			ps = conn.prepareStatement(sql);
			ResultSet rs =  ps.executeQuery();
			
			if(rs != null) {
				while (rs.next()) {
					Genero gen = new Genero();
					gen.id = rs.getInt(1);
					gen.nome = rs.getString(2);
					listaGeneros.add(gen);
				}
			}			
		} 
		catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.toString());
		}
		return listaGeneros;
	}
}