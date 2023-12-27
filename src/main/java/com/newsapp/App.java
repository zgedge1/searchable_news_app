
package com.newsapp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONObject;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class App extends Application{

    private static String apiUrl = "https://newsapi.org/v2/everything";
    private static String apiKey = "f8bc21d568ba450e94b0a6bb37d82c68";

    private TextArea newsTextArea;

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) {
        primaryStage.setTitle("News App");

        TextArea searchField = new TextArea();
        searchField.setPromptText("Enter a keyword");

        Button searchButton = new Button("Search");
        searchButton.setOnAction(e -> newsSearch(searchField.getText()));

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



    public void newsSearch(String userInput) {
        if (userInput.isEmpty()) {
            displayMessage("Input field cannot be blank");
            
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
                displayMessage("ERROR: Cannot connect to API. CODE: " + connection.getResponseCode());
            }

            connection.disconnect();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String buildApiUrl(String userInput) {
        return String.format(apiUrl + "?q=" + userInput + "&apiKey=" + apiKey);
    }

    private void parseNewsData(String getData) {
        
        JSONObject json = new JSONObject(getData);
        

        JSONArray articlesArr = json.getJSONArray("articles");

        StringBuilder newsInfo = new StringBuilder();

        JSONObject zero = articlesArr.getJSONObject(0);

        String title = zero.getString("title");
        String author = zero.getString("author");
        String description = zero.getString("description");

        newsInfo.append("\nTitle ").append(title)
                .append("\nAuthor ").append(author)
                .append("\n").append(description).append("\n\n");
        
        displayMessage(newsInfo.toString());
    }

    public void displayMessage(String message) {
        newsTextArea.setText(message);
    }

    
}