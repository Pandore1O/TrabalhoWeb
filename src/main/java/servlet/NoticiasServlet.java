package servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class NoticiasServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final String API_KEY = "70525fc45f084571b7162dd0f4088da6";
	private static final String API_URL = "https://newsapi.org/v2/everything?q=KanyeWest&from=2024-09-11&sortBy=popularity&apiKey=" + API_KEY;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String jsonResponse = fetchNewsFromAPI();
		
		JsonObject newsData = JsonParser.parseString(jsonResponse).getAsJsonObject();
		JsonArray articlesArray = newsData.getAsJsonArray("articles");
		
		List<Map<String, Object>> articles = new ArrayList<>();
		Gson gson = new Gson();
		
		for (int i = 0; i < articlesArray.size(); i++) {
			articles.add(gson.fromJson(articlesArray.get(i), Map.class));
		}
		
		request.setAttribute("articles", articles);	
		request.getRequestDispatcher("/NoticiasNew.jsp").forward(request, response);
	}
	
	private String fetchNewsFromAPI() throws IOException {
	
		HttpURLConnection connection = null;
		BufferedReader reader = null;
		
		try {
			URL url = new URL(API_URL);
			
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.setRequestProperty("Accept", "application/json");
			
			if (connection.getResponseCode() != 200) {
				throw new IOException("Erro de resposta da API: " + connection.getResponseCode());
			}
			
			reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			StringBuilder response = new StringBuilder();
			String line;
			while ((line = reader.readLine()) != null) {
				response.append(line);
			}
			
			return response.toString();
		} finally {
			if (reader != null) {
				reader.close();
			}
			
			if (connection != null) {
				connection.disconnect();
			}
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
