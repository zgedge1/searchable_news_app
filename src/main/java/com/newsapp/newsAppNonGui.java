
package com.newsapp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;


import org.json.JSONArray;
import org.json.JSONObject;




public class newsAppNonGui {

    private static String apiKey = "f8bc21d568ba450e94b0a6bb37d82c68";
    private static String apiUrl = "https://newsapi.org/v2/everything";


     public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        while (true) {

            System.out.println("Enter a keyword or type 'exit' at anytime to quit");

            String userInput = scanner.nextLine();
            if (userInput.equals("exit")) {
                System.out.println("Exiting News App....");
                break;
            }

            if (userInput.isBlank()) {
                System.out.println("ERROR: Input cannot be blank");
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

                    scanner.close();
                    
                }


            } catch (IOException e) {
                e.printStackTrace();
            }



            
            
        }
     }

     public static String buildApiUrl(String userInput) {
        return String.format(apiUrl + "?q=" + userInput + "&apiKey=" + apiKey);
        
     }

     private static void parseNewsData(String getData) {

        JSONObject json = new JSONObject(getData);
        

        JSONArray articleArr = json.getJSONArray("articles");

        if (!json.has("articles") || json.getJSONArray("articles").isEmpty()) {
            System.out.println("ERROR: No news articles found");
            return;
            
        }

        JSONObject zer0 = articleArr.getJSONObject(0);

        String author = zer0.getString("author");
        String title = zer0.getString("title");
        String description = zer0.getString("description");

        System.out.println("");
        System.out.println("\nAuthor: " + author);
        System.out.println("");
        System.out.println("\nTitle: " + title);
        System.out.println("");
        System.out.println(description);

     }
}