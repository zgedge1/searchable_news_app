
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

    public static String apiUrl = "https://newsapi.org/v2/everything";
    public static String apiKey = "f8bc21d568ba450e94b0a6bb37d82c68";

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        while (true) {

            System.out.println("Enter a keyword or type 'quit to exit");

            String userInput = scanner.nextLine();

            if (userInput.equals("quit")) {
                break;

            }

            if (userInput.isBlank()) {
                System.out.println("ERROR! Input cannot be blank");
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
                    System.out.println("ERROR: Cannot connect to API. Error Code " + connection.getResponseCode());
                }



                connection.disconnect();

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

        if (articleArr.equals("articles") || articleArr.isEmpty()) {
            System.out.println("ERROR: No news results found");
            return;
            
        }

        JSONObject zero = articleArr.getJSONObject(0);
        JSONObject one = articleArr.getJSONObject(1);
        JSONObject two = articleArr.getJSONObject(2);

        String title0 = zero.getString("title");
        String author0 = zero.getString("author");
        String description0 = zero.getString("description");

        String title1 = one.getString("title");
        String author1 = one.getString("author");
        String description1 = one.getString("description");
        
        String title2 = two.getString("title");
        String author2 = two.getString("author");
        String description2 = two.getString("description");



        System.out.println("");
        System.out.println(title0);
        System.out.println("");
        System.out.println(author0);
        System.out.println("");
        System.out.println(description0);
        System.out.println("");
        System.out.println(title1);
        System.out.println("");
        System.out.println(author1);
        System.out.println("");
        System.out.println(description1);
        System.out.println("");
        System.out.println(title2);
        System.out.println("");
        System.out.println(author2);
        System.out.println("");
        System.out.println(description2);
        System.out.println("");

    }
}