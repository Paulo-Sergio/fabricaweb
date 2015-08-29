package br.com.fabricadeprogramador;

import java.util.List;

import br.com.fabricadeprogramador.persistencia.entidade.Usuario;
import br.com.fabricadeprogramador.persistencia.jdbc.UsuarioDAO;

public class TestUsuarioDAO {

	public static void main(String[] args) {
//		testExcluir();
//		testBuscarPorId();
//		testBuscarTodos();
		testAutenticar();
//		testBuscarPorNome();
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
		usuDAO.alterar(usu);
		
		System.out.println("Alterado com sucesso!");
	}
	
	public static void testExcluir(){
		Usuario usu = new Usuario();
		usu.setId(6);
		
		UsuarioDAO usuDAO = new UsuarioDAO();
		usuDAO.excluir(usu);
		
		System.out.println("Excluido com sucesso!");
	}
	
	public static void testSalvar(){
		Usuario usu = new Usuario();
		usu.setId(null);
		usu.setNome("Virmeson");
		usu.setLogin("vir");
		usu.setSenha("684956");
		
		UsuarioDAO usuDAO = new UsuarioDAO();
		usuDAO.salvar(usu);
		
		System.out.println("Salvo com sucesso!");
	}

	public static void testBuscarPorId(){
		UsuarioDAO usuDAO = new UsuarioDAO();
		Usuario usu = usuDAO.buscarPorId(4);
		System.out.println(usu);
	}
	
	public static void testBuscarTodos(){
		UsuarioDAO usuDAO = new UsuarioDAO();
		List<Usuario> lista = usuDAO.buscarTodos();
		for(Usuario u: lista){
			System.out.println(u);
		}
	}
	
	public static void testAutenticar(){
		Usuario usu = new Usuario();
		usu.setLogin("kelly");
		usu.setSenha("4532687906");
		
		UsuarioDAO usuDAO = new UsuarioDAO();
		Usuario usuAutenticado = usuDAO.autenticar(usu);
		System.out.println(usuAutenticado);
	}
	
	public static void testBuscarPorNome(){
		UsuarioDAO usuDAO = new UsuarioDAO();
		List<Usuario> listaNomes = usuDAO.buscarPorNome("Paulo");
		for (Usuario u : listaNomes) {
			System.out.println(u);
		}
	}
	
}
