
package com.newsapp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONObject;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.transform.Scale;
import javafx.stage.Stage;

public class App extends Application {

    private static String apiUrl = "https://newsapi.org/v2/everything";
    private static String apiKey = // Enter API KEY

    private TextArea newsTextArea;

    public static void main(String[] args) {

        launch(args);
    }

    public void start(Stage primaryStage) {
        primaryStage.setTitle("News App");

        TextField searchField = new TextField();
        searchField.setPromptText("Enter search keyword");

        Button searchButton = new Button("Search");
        searchButton.setOnAction(e -> searchNews(searchField.getText()));

        Button quitButton = new Button("Quit");
        quitButton.setOnAction(e -> primaryStage.close());

        newsTextArea = new TextArea();
        newsTextArea.setEditable(false);

        VBox layout = new VBox(10);
        layout.getChildren().addAll(searchField, searchButton, quitButton, newsTextArea);
        

        Scene scene = new Scene(layout, 400, 300);
    

        primaryStage.setScene(scene);

        primaryStage.show();

    }

    public static void setRoot(String root) {

    }

    private void searchNews (String userInput) {
        if (userInput.isEmpty()) {
            displayMessage ("Search field cannot be empty");
            return;
            
        }

        try {
            
            URL url = new URL(buildApiUrl(userInput));

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {

                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

                String line;

                StringBuilder response = new StringBuilder();

                while ((line = reader.readLine()) !=null) {
                    response.append(line);
                    
                }

                parseNewsData(response.toString());

                reader.close();
                
            } else {
                displayMessage("ERROR: Cannot connect to API. Code: " + connection.getReadTimeout());

            }

        connection.disconnect();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String buildApiUrl(String userInput) {
        return String.format(apiUrl + "?q=" + userInput + "&apiKey=" + apiKey);
    }

    private void parseNewsData(String getData) {
        JSONObject json = new JSONObject(getData);

        if (!json.has("articles") || json.getJSONArray("articles").isEmpty()) {
            displayMessage("No articles found");
            return;
            
        }

        JSONArray articleArr = json.getJSONArray("articles");
        StringBuilder newsInfo = new StringBuilder();

        JSONObject zero = articleArr.getJSONObject(0);
        String author = zero.getString("author");
        String title = zero.getString("title");
        String description = zero.getString("description");

        newsInfo.append("\nAuthor").append(author)
                .append("\ntitle").append(title)
                .append("\n").append(description).append("\n\n");
        
        displayMessage(newsInfo.toString());


    }

    private void displayMessage(String message) {
        newsTextArea.setText(message);
    }

    
}