package br.com.fabricadeprogramador.filter;

import java.io.IOException;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter(dispatcherTypes={DispatcherType.REQUEST}, urlPatterns="/*")
public class FiltroAutenticacao implements Filter{

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		
		String uri = httpRequest.getRequestURI();
		HttpSession sessao = httpRequest.getSession(false);
		
		//1)primeiro de tudo se houver sessão do usuario vai estar liberado!!
		//2)se não tem a palavra 'login' ou 'autenticador.do' na requisição da url o acesso vai ser barrado e direcionado 'login.html'
		//se existir alguma dessas palavras vai entrar chain.doFilter(request, response), ou seja pagina liberada
		if(sessao != null || uri.lastIndexOf("login") != -1 || uri.lastIndexOf("autenticador.do") != -1){
			chain.doFilter(request, response);
		}else{
			httpResponse.sendRedirect("login.html");
		}
	}

	@Override
	public void destroy() {
		
	}
}
