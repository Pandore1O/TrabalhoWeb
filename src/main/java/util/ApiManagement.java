package util;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ApiManagement {
	
	public void call() {
		
		String API_URL = "https://official-joke-api.appspot.com/random_joke";
		HttpRequest request = HttpRequest.newBuilder().GET().uri(URI.create(API_URL)).build();
		
		HttpClient client = HttpClient.newBuilder().build();
		
		try {
			HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
			
			String jsonData = response.body();
			System.out.println(jsonData);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
}
