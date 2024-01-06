
package com.newsapp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONObject;

public class newsAppNonGui {

    private static String apiKey = "f8bc21d568ba450e94b0a6bb37d82c68";
    private static String apiUrl = "https://newsapi.org/v2/everything";

    public static void main(String[] args) {

        System.out.println("Welcome to the news app! Enter a keyword or type 'quit to exit the program");

        Scanner scanner = new Scanner(System.in);
        
        while (true) {

            System.out.println("");
            System.out.println("Enter a keyword");

            String userInput = scanner.nextLine();
            if (userInput.equals("quit")) {
                System.out.println("Exiting program: Thank you for using the news app! ");
                break;
                
            }

            if (userInput.isBlank()) {
                System.out.println("ERROR! Entry cannot be blank");
                continue;                
            }

            

            try {
                
                           URL url = new URL(buildApiUrl(userInput));

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {

                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

                String line;

                StringBuffer response = new StringBuffer();

                while ((line = reader.readLine())!=null) {
                    response.append(line);
                    
                }

                parseNewsData(response.toString());

                reader.close();
                
            } else {
                System.out.println("ERROR: Cannot connect to API URL: " + connection.getInputStream());
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
        JSONArray articleArr = json.getJSONArray("articles");

        if (!json.has("articles") || json.getJSONArray("articles").isEmpty()) {
            System.out.println("ERROR! No news articles found");
            return;
            
        }

        JSONObject zero = articleArr.getJSONObject(0);
        JSONObject one = articleArr.getJSONObject(1);

        String title0 = zero.getString("title");
        String decription0 = zero.getString("description");
        String printUrl0 = zero.getString("url");

        String title1 = one.getString("title");
        String description1 = one.getString("description");
        String printUrl1 = one.getString("url");

        

        System.out.println("");
        System.out.println("\nTitle: "+ title0);
        System.out.println(decription0);
        System.out.println("\nRead More: " + printUrl0);
        
        System.out.println("Title: "  + title1);
        System.out.println(description1);
        System.out.println("Read More: " + printUrl1);

        
        
        
        
        
    }

    
}