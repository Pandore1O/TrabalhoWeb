<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Latest News</title>
    <link rel="stylesheet" type="text/css" href="css/NoticiasNew.css">
</head>
<body>

    <header>
      <div id="logoCorner">
      	<a href="HomeServlet"><img id="logo" src="https://media1.tenor.com/m/afqg5H7O4eQAAAAd/kanye-west-stare.gif"/></a>
      	<h1>Kanye Site</h1>
      </div>
      <h1 id="sobre">Noticias do Ye</h1>
      <a
        href="https://open.spotify.com/intl-pt/artist/5K4W6rqBFWDnAN6FQUkS6x?si=9hlPHd_tSeaQ_LxXVtTT1w"
      >
        <img
          class="spotifyIcon"
          src="https://cdn-icons-png.flaticon.com/512/174/174872.png"
          alt="spotifyIcon"
        />
      </a>
    </header>

    <div class="news-container" style="padding: 20px;">

        <!-- Exibir as notícias usando JSTL -->
        <c:forEach var="article" items="${articles}">
            <div class="news-item" style="border-bottom: 1px solid #656d77; margin-bottom: 20px;">
                <div class="news-image">
                    <img src="${article.urlToImage}" alt="Image" style="border-radius: 50%; width: 100px; height: 100px;">
                </div>
                <div class="news-content">
                    <h2 style="color: #5b708a;">${article.title}</h2>
                    <p style="color: #656d77;">${article.description}</p>
                    <a href="${article.url}" style="color: #1d3756;">Read more</a>
                </div>
            </div>
        </c:forEach>

    </div>

    <footer>
      <p>2024 Ye © Você ja sabe que tem, não minta para si mesmo.</p>
    </footer>

</body>
</html>