package br.com.fabricadeprogramador.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.fabricadeprogramador.persistencia.entidade.Usuario;
import br.com.fabricadeprogramador.persistencia.jdbc.UsuarioDAO;

// http://localhost:8080/fabricaweb/usucontroller.do
@WebServlet("/usucontroller.do")
public class UsuarioController extends HttpServlet{

	public UsuarioController() {
		System.out.println("construtor...");
	}
	
	@Override
	public void init() throws ServletException {
		System.out.println("init");
		super.init();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String acao = req.getParameter("acao");
		
		if(acao.equals("excluir")){
			String id = req.getParameter("id");
			
			Usuario usu = new Usuario();
			if(id != null && id != "")
				usu.setId(Integer.parseInt(id));
			
			UsuarioDAO usuDAO = new UsuarioDAO();
			usuDAO.excluir(usu);
			//resp.getWriter().println("<h1>Excluido Sucesso</h1>");
			
			resp.sendRedirect("usucontroller.do?acao=lis");
			
		}else if(acao.equals("lis")){
			UsuarioDAO usuDAO = new UsuarioDAO();
			List<Usuario> lista = usuDAO.buscarTodos();
			//resp.getWriter().print(lista);
			
			req.setAttribute("lista", lista);
			
			RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/listausu.jsp");
			dispatcher.forward(req, resp);
			
		}else if(acao.equals("alterar")){
			String id = req.getParameter("id");
			
			UsuarioDAO usuDAO = new UsuarioDAO();
			Usuario usu = usuDAO.buscarPorId(Integer.parseInt(id));
			
			req.setAttribute("usu", usu);
			RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/formusuario.jsp");
			dispatcher.forward(req, resp);
			
		}else if(acao.equals("cad")){
			Usuario usu = new Usuario();
			usu.setId(0);
			usu.setNome("");
			usu.setLogin("");
			usu.setSenha("");
			req.setAttribute("usu", usu);
			RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/formusuario.jsp");
			dispatcher.forward(req, resp);
		}
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("id");
		String nome = req.getParameter("nome");
		String login = req.getParameter("login");
		String senha = req.getParameter("senha");
		
		Usuario usu = new Usuario();
		if(id != null && id != "")
			usu.setId(Integer.parseInt(id));
		usu.setNome(nome);
		usu.setLogin(login);
		usu.setSenha(senha);
		
		UsuarioDAO usuDAO = new UsuarioDAO();
		usuDAO.salvar(usu);
		resp.getWriter().println("<h1>Sucesso</h1>");
		resp.sendRedirect("usucontroller.do?acao=lis");
	}
	
	@Override
	public void destroy() {
		System.out.println("destroy");
		super.destroy();
	}
}
