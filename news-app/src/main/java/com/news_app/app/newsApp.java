
package com.news_app.app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONObject;

public class newsApp {

    private static String apiKey = // Enter API Key
    private static String apiUrl = "https://newsapi.org/v2/everything";

    public static void main(String[] args) {
        
        Scanner scanner = new Scanner(System.in);

        while (true) {

            System.out.println("Enter a news topic to search or type 'quit' to exit: ");

            String newsInput = scanner.nextLine();

            if (newsInput.equals("quit")) {
                System.out.println("Exiting Program");
                break;
                
            }

            if (newsInput.isBlank()) {
                System.out.println("ERROR: News topic could not be blank!");
                continue;
                
            }

            try {
                
                URL url = new URL(buildApiUrl(newsInput));

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
                    System.out.println("ERROR: Cannot connect to API URL with error code: " + connection.getResponseCode());
                }

                connection.disconnect();


            } catch (IOException e) {
                e.printStackTrace();
            }

            
        }
    }

    private static String buildApiUrl(String newsInput) {
        return apiUrl + "?q=" + newsInput + "&apiKey=" + apiKey;
    }

    private static void parseNewsData(String getData) {

        JSONObject json = new JSONObject(getData);

        JSONArray articles0 = json.getJSONArray("articles");
        JSONObject zero = articles0.getJSONObject(0);
        
        String author0 = zero.getString("author");
        String title0 = zero.getString("title");
        String description0 = zero.getString("description");

        System.out.println("");
        System.out.println("Author:" + author0);
        System.out.println("");
        System.out.println("Title: " +  title0);
        System.out.println("");
        System.out.println(description0);
        System.out.println("");




    }

}