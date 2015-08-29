package br.com.fabricadeprogramador;

import br.com.fabricadeprogramador.persistencia.entidade.Usuario;
import br.com.fabricadeprogramador.persistencia.jdbc.UsuarioDAO;

public class TestUsuarioDAO {

	public static void main(String[] args) {
		testExcluir();
	}
	
	public static void testCadastrar(){
		//criando usuario
		Usuario usu = new Usuario();
		usu.setNome("Jãozão");
		usu.setLogin("jzao");
		usu.setSenha("12345");
		
		//cadastrando usuario
		UsuarioDAO usuDAO = new UsuarioDAO();
		usuDAO.cadastrar(usu);
		
		System.out.println("Cadastrado com sucesso!");
	}
	
	public static void testAlterar(){
		Usuario usu = new Usuario();
		usu.setId(3);
		usu.setNome("Paulo");
		usu.setLogin("maozinha");
		usu.setSenha("98765");
		
		UsuarioDAO usuDAO = new UsuarioDAO();
		usuDAO.Alterar(usu);
		
		System.out.println("Alterado com sucesso!");
	}
	
	public static void testExcluir(){
		Usuario usu = new Usuario();
		usu.setId(2);
		
		UsuarioDAO usuDAO = new UsuarioDAO();
		usuDAO.excluir(usu);
		
		System.out.println("Excluido com sucesso!");
	}

}
