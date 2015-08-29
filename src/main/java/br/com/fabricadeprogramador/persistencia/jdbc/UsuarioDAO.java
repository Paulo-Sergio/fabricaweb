package br.com.fabricadeprogramador.persistencia.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fabricadeprogramador.persistencia.entidade.Usuario;

public class UsuarioDAO {

	private Connection con = ConexaoFactory.getConnection();
	
	public void cadastrar(Usuario usu) {
		String sql = "insert into usuario (nome, login, senha) values (?,?,?)";
		
		try {
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setString(1, usu.getNome());
			preparador.setString(2, usu.getLogin());
			preparador.setString(3, usu.getSenha());
			
			preparador.execute();
			preparador.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void alterar(Usuario usu){
		String sql = "update usuario set nome=?, login=?, senha=? where id=?";
		
		try {
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setString(1, usu.getNome());
			preparador.setString(2, usu.getLogin());
			preparador.setString(3, usu.getSenha());
			preparador.setInt(4, usu.getId());
			
			preparador.execute();
			preparador.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void excluir(Usuario usu) {
		String sql = "delete from usuario where id=?";
		
		try {
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setInt(1, usu.getId());
			
			preparador.execute();
			preparador.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void salvar(Usuario usu){
		if(usu.getId() != null){
			alterar(usu);
		}else{
			cadastrar(usu);
		}
	}

	/**
	 * Busca de um registro no banco por ID
	 * @param id do usuario
	 * @return Usuario ou null quando não encontrado
	 */
	public Usuario buscarPorId(Integer id) {
		String sql = "select * from usuario where id=?";
		
		try {
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setInt(1, id);
			
			ResultSet resultado = preparador.executeQuery();
			if(resultado.next()){
				Usuario usuario = new Usuario();
				//usuario.setId(id);
				usuario.setId(resultado.getInt("id"));
				usuario.setNome(resultado.getString("nome"));
				usuario.setLogin(resultado.getString("login"));
				usuario.setSenha(resultado.getString("senha"));
				
				return usuario;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Realiza a busca de todos os registros no banco de dados
	 * @return Uma lista de objetos Usuarios
	 */
	public List<Usuario> buscarTodos(){
		String sql = "select * from usuario";
		
		try {
			PreparedStatement preparador = con.prepareStatement(sql);
			
			ResultSet resultado = preparador.executeQuery();
			List<Usuario> listaUsuario = new ArrayList<Usuario>();
			while(resultado.next()){
				Usuario usuario = new Usuario();
				usuario.setId(resultado.getInt("id"));
				usuario.setNome(resultado.getString("nome"));
				usuario.setLogin(resultado.getString("login"));
				usuario.setSenha(resultado.getString("senha"));
				
				listaUsuario.add(usuario);
			}
			return listaUsuario;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

}
