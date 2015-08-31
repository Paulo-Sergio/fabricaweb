<%@page import="br.com.fabricadeprogramador.persistencia.entidade.Usuario"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Lista de Usuarios</title>
<link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css" rel="stylesheet" type="text/css">
<script type="text/javascript">
function confirmaExcluir(id){
	if(window.confirm("Tem certeza que deseja excluir ?")){
		location.href="usucontroller.do?acao=excluir&id="+id;
	}
}
</script>
</head>
<body>
<% List<Usuario> lista = (List<Usuario>) request.getAttribute("lista");%>
<table class="table">
	<tr>
		<th>ID</th>
		<th>Nome</th>
		<th>Ação</th>
	</tr>
	<% for(Usuario u: lista){%>
	<tr>
		<td><%= u.getId()%></td>
		<td><%= u.getNome()%></td>
		<td>
			<a href="javascript:confirmaExcluir(<%=u.getId()%>)">excluir</a> | 
			<a href="usucontroller.do?acao=alterar&id=<%=u.getId()%>">alterar</a>
		</td>
	</tr>
	<% }%>
</table>

</body>
</html>