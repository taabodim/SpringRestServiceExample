package com.admarketplace.controller;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
//import com.admarketplace.controller.RandomString;
import javax.net.ssl.HttpsURLConnection;

public class RestClient {

	void addStudent(String name) throws Exception {

		URL url = new URL("http://localhost:8080/add/"+name);
		HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
		httpCon.setDoOutput(true);
		httpCon.setRequestMethod("GET");
		System.out.println("This is the response from server: ");
		
		BufferedReader in = new BufferedReader(
		        new InputStreamReader(httpCon.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();
 
		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		System.out.println(response.toString());
		
		in.close();

	}
	void deleteAllStudents() throws Exception {
			URL url = new URL("http://localhost:8080/deleteAll");
			HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
			httpCon.setDoOutput(true);
			httpCon.setRequestMethod("DELETE");
			System.out.println("trying to delete all students using http delete");
			System.out.println("This is the response from server: ");
			
			BufferedReader in = new BufferedReader(
			        new InputStreamReader(httpCon.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();
	 
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			System.out.println(response.toString());
			
			in.close();
	}
	
	void addManyStudents() throws Exception
	{
		RandomString rand = new RandomString(3);
		for(int i=0;i<1000;i++)
		{
			
			addStudent(rand.nextString());
		}
		
		System.out.println("added 1000 students to the school using post method");
	}
	
	public static void main(String args[]) throws Exception
	{
		new RestClient().addManyStudents();
		//new RestClient().deleteAllStudents();
	}

	
	// HTTP GET request
		private void sendGet() throws Exception {
	 
			String url = "http://www.google.com/search?q=mkyong";
	 
			URL obj = new URL(url);
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();
	 
			// optional default is GET
			con.setRequestMethod("GET");
	 
			//add request header
//			con.setRequestProperty("User-Agent", USER_AGENT);
	 
			int responseCode = con.getResponseCode();
			System.out.println("\nSending 'GET' request to URL : " + url);
			System.out.println("Response Code : " + responseCode);
	 
			BufferedReader in = new BufferedReader(
			        new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();
	 
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
	 
			//print result
			System.out.println(response.toString());
	 
		}
	 
		// HTTP POST request
		private void sendPost() throws Exception {
	 
			String url = "https://selfsolve.apple.com/wcResults.do";
			URL obj = new URL(url);
			HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();
	 
			//add reuqest header
			con.setRequestMethod("POST");
//			con.setRequestProperty("User-Agent", USER_AGENT);
			con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
	 
			String urlParameters = "name=pinkle";
	 
			// Send post request
			con.setDoOutput(true);
			DataOutputStream wr = new DataOutputStream(con.getOutputStream());
			wr.writeBytes(urlParameters);
			wr.flush();
			wr.close();
	 
			int responseCode = con.getResponseCode();
			System.out.println("\nSending 'POST' request to URL : " + url);
			System.out.println("Post parameters : " + urlParameters);
			System.out.println("Response Code : " + responseCode);
	 
			BufferedReader in = new BufferedReader(
			        new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();
	 
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
	 
			//print result
			System.out.println(response.toString());
	 
		}
	 
	
}
