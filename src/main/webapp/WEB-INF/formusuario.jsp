<!DOCTYPE html>
<%@page import="br.com.fabricadeprogramador.persistencia.entidade.Usuario"%>
<html>
<head>
<meta charset="UTF-8">
<title>Cadastrar Usuario</title>
</head>
<body>
<% Usuario usu = (Usuario) request.getAttribute("usu"); %>
<form action="usucontroller.do" method="post">
	ID: <input type="number" name="id" value="<%=usu.getId()%>">
	Nome: <input type="text" name="nome" value="<%=usu.getNome()%>">
	Login: <input type="text" name="login" value="<%=usu.getLogin()%>">
	Senha: <input type="text" name="senha" value="<%=usu.getSenha()%>">
	
	<input type="submit" value="Cadastrar">
</form>
</body>
</html>