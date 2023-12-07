package org.libertas.bd;

import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;


public class ContatosDAO {
	public void inserir(Contatos c) {
		Conexao con = new Conexao();
		try {
			String sql = " INSERT INTO contatos "
					+ " (nome, endereco, telefone, email, riotid) VALUES (?, ?, ?, ?, ?) ";
			PreparedStatement prep = con.getConexao().prepareStatement(sql);
			prep.setString(1, c.getNome());
			prep.setString(2, c.getEndereco());
			prep.setString(3, c.getTelefone());
			prep.setString(4, c.getEmail());
			prep.setString(5, c.getRiotid());
			prep.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
		con.desconectar();
	}
	public void alterar(Contatos c) {
		Conexao con = new Conexao();
		try {
			String sql = " UPDATE contatos "
					+ " SET nome = ?, endereco = ?, telefone= ?, email= ?, riotid= ?  "
					+ " WHERE id = ? ";
			PreparedStatement prep = con.getConexao().prepareStatement(sql);
			prep.setString(1, c.getNome());
			prep.setString(2, c.getEndereco());
			prep.setString(3, c.getTelefone());
			prep.setString(4, c.getEmail());
			prep.setString(5, c.getRiotid());
			prep.setInt(6, c.getId());
			prep.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
		con.desconectar();
		
	}
	
	public void excluir(Contatos c) {
		Conexao con = new Conexao();
		try {
			String sql = " DELETE FROM contatos "
					+ " WHERE id = ? ";
			PreparedStatement prep = con.getConexao().prepareStatement(sql);
			prep.setInt(1, c.getId());
			
			prep.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
		con.desconectar();
		
	}

	public Contatos consultar(int id) {

		Contatos c = new Contatos();
		Conexao con = new Conexao();
		try {
			String sql = " SELECT * FROM contatos WHERE id = " + id;
			Statement sta = con.getConexao().createStatement();
			ResultSet res = sta.executeQuery(sql);
			while (res.next()) {
				c.setId(res.getInt("id"));
				c.setNome(res.getString("nome"));
				c.setEndereco(res.getString("endereco"));
				c.setTelefone(res.getString("telefone"));
				c.setEmail(res.getString("email"));
				c.setRiotid(res.getString("riotid"));
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		con.desconectar();
		return c;
	}
		
	public List<Contatos> listar(){
		List<Contatos> lista = new LinkedList<Contatos>();
			Conexao con = new Conexao();
		
		try {
			String sql = " SELECT * FROM contatos ORDER BY id ";
			Statement sta = con.getConexao().createStatement();
			ResultSet res = sta.executeQuery(sql);
			while (res.next()) {
				Contatos c= new Contatos();
				c.setId(res.getInt("id"));
				c.setNome(res.getString("nome"));
				c.setEndereco(res.getString("endereco"));
				c.setTelefone(res.getString("telefone"));
				c.setEmail(res.getString("email"));
				c.setRiotid(res.getString("riotid"));
				
				lista.add(c);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		con.desconectar();
		return lista;
	}
}

