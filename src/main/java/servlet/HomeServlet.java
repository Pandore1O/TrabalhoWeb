package servlet;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import util.Joke;

public class HomeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	String jsonResponse = call();
    	
    	if (jsonResponse != null && !jsonResponse.isEmpty()) {
    		System.out.println("Resposta da API: " + jsonResponse);
    		
    		Gson gson = new Gson();
        	Joke joke = gson.fromJson(jsonResponse, Joke.class);
        	
        	request.setAttribute("joke", joke);
        	
        	System.out.println("Setup: " + joke.getSetup());
        	System.out.println("Punchline:" + joke.getPunchline());
    	}
    	else
    	{
    		System.out.println("A resposta da API está vazia ou nula");
    		request.setAttribute("error", "Não foi possivel carregar a piada.");
    	}
    	
    	request.getRequestDispatcher("/index.jsp").forward(request, response);
    }
    
    public String call() {
		
		String API_URL = "https://official-joke-api.appspot.com/random_joke";
		HttpRequest request = HttpRequest.newBuilder().GET().uri(URI.create(API_URL)).build();
		HttpClient client = HttpClient.newBuilder().build();
		
		try {
			HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
			
			if (response.statusCode() == 200) {
				System.out.println("Resposta da API obtida com sucesso.");
	            System.out.println("JSON Response: " + response.body());
				return response.body();
			}
			else
			{
				System.out.println("Erro na resposta da API:" + response.statusCode());
			}
			
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
	
		return null;
	}
    
   /* {
    	"type":"general",
    	"setup":"XYZ",
    	"punchline":"123",
    	"id":222
    }*/

}