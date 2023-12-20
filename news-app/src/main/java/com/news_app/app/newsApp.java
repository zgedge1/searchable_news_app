
package com.news_app.app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONObject;

public class newsApp {

    private static String apiUrl = "https://newsapi.org/v2/everything";
    private static String apiKey = // Enter API Key

    public static void main(String[] args) {

        
        Scanner scanner = new Scanner(System.in);

        while (true) {

            System.out.println("Search a news article");

            String userInput = scanner.nextLine();

            if (userInput.equals("quit")) {
                System.out.println("Exiting Program!");
                break;
                
            }

            if (userInput.isEmpty()) {
                System.out.println("Search field cannot be empty!");
                continue;
                
            }

            try {
                
                URL url = new URL(buildApiUrl(userInput));

                HttpURLConnection connection = (HttpURLConnection) url.openConnection();

                if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {

                    BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

                    String line;

                    StringBuffer response = new StringBuffer();

                    while ((line = reader.readLine()) != null) {
                        response.append(line);
                        
                    }

                    parseNewsData(response.toString());

                    reader.close();

                    
                    
                } else {
                    System.out.println("ERROR: Cannot connect to API. Code: " + connection.getResponseCode());
                }

                connection.disconnect();


            } catch (IOException e) {
                e.printStackTrace();
            }

            
        }
    }

    private static String buildApiUrl(String userInput) {

        return String.format(apiUrl + "?q=" + userInput + "&apiKey=" + apiKey);
    }

    private static void parseNewsData(String getData) {
        
        JSONObject json = new JSONObject(getData);

        if(!json.has("articles") || json.getJSONArray("articles").isEmpty()) {
            System.out.println("No articles found");
            return;
        }



        JSONArray articleArr = json.getJSONArray("articles");
        
        JSONObject zero = articleArr.getJSONObject(0);
        String author0 = zero.getString("author");
        String title0 = zero.getString("title");
        String description0 = zero.getString("description");

        JSONObject one = articleArr.getJSONObject(1);

        String author1 = one.getString("author");
        String title1 = one.getString("title");
        String description1 = one.getString("description");

        JSONObject two = articleArr.getJSONObject(2);

        String author2 = two.getString("author");
        String title2 = two.getString("title");
        String description2 = two.getString("description");



        System.out.println("");
        System.out.println("Author:" + author0);
        System.out.println("");
        System.out.println("Title: " + title0);
        System.out.println(description0);
        System.out.println("");
        System.out.println("Author: " + author1);
        System.out.println("");
        System.out.println("Title: " + title1);
        System.out.println("");
        System.out.println(description1);
        System.out.println("");
        System.out.println("Author: " + author2);
        System.out.println("");
        System.out.println("Title: " + title2);
        System.out.println("");
        System.out.println(description2);
        System.out.println("");
    
    }
}