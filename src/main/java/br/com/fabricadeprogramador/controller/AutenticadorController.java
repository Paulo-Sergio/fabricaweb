package br.com.fabricadeprogramador.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.fabricadeprogramador.persistencia.entidade.Usuario;
import br.com.fabricadeprogramador.persistencia.jdbc.UsuarioDAO;

@WebServlet ("/autenticador.do")
public class AutenticadorController extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//Caso não ache a sessão eu não quero criar uma sessão nova. Por isso (false) como parametro
		HttpSession sessao = req.getSession(false);
		
		if(sessao != null){
			sessao.invalidate();
		}
		resp.sendRedirect("login.html");
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//1-capturando dados da tela
		String login = req.getParameter("login");
		String senha = req.getParameter("senha");
		//2-colocando dados em objeto Usuario
		Usuario usu = new Usuario();
		usu.setLogin(login);
		usu.setSenha(senha);
		//3-consultando se o usuario existe no banco
		UsuarioDAO usuDAO = new UsuarioDAO();
		Usuario usuAutenticado = usuDAO.autenticar(usu);
		
		//4-verificando se usuario foi encontrado
		if(usuAutenticado != null){
			//5-colocando usuario na sessao
			HttpSession sessao = req.getSession();
			sessao.setAttribute("usuAutenticado", usuAutenticado);
			sessao.setMaxInactiveInterval(30);
			//6-levando usuario para tela principal
			RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/index.jsp");
			dispatcher.forward(req, resp);
		}else{
			resp.getWriter().print("<script>window.alert('Não encontrado!'); location.href='login.html'</script>");
		}
	}
}
