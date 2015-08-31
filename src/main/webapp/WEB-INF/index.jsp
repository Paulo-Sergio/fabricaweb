<!DOCTYPE html>
<%@page import="br.com.fabricadeprogramador.persistencia.entidade.Usuario"%>
<html>
<head>
<meta charset="UTF-8">
<title>Tela Inicial</title>
</head>
<body>
<%@include file="menu.jsp"%>
Bem Vindo <%Usuario usuario = (Usuario)request.getSession().getAttribute("usuAutenticado"); %>
<%=usuario.getNome() %>
</body>
</html>