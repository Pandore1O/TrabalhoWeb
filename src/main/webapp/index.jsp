<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="util.Joke" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Kanye Site</title>
	<link rel="stylesheet" href="css/index.css"/>
</head>
<body>
    <div class="container">
        <h1>Bem-vindo ao Kanye Site</h1>
        <p>Escolha uma página para navegar:</p>
        <a href="NoticiasServlet" class="btn">Noticias</a>
        <a href="BiografiaServlet" class="btn">Biografia</a>
        <a href="page3" class="btn">Página 3</a>
        <a href="page4" class="btn">Página 4</a>
    </div>
    <div>
    	<%
    		Joke joke = (Joke) request.getAttribute("joke");
    		String error = (String) request.getAttribute("error");
    		
    		if (joke != null) {
    	%>
    	<p><strong>Setup: </strong> <%= joke.getSetup() %></p>
    	<strong>punchline: </strong> <%= joke.getPunchline() %>
    	<%
    		}
    		else if (error != null)
    		{
    	%>
    	<p><strong>Erro: </strong> <%= error %></p>
    	<%
    		}
    		else
    		{
    	%>
    	<p>Não foi possivel carregar.</p>
    	<%
    		}
    	%>
    </div>
</body>
</html>