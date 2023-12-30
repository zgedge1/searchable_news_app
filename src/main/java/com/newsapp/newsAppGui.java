
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
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class newsAppGui extends Application{

    private static String apiKey = "f8bc21d568ba450e94b0a6bb37d82c68";
    private static String apiURl = "https://newsapi.org/v2/everything";

    private TextArea searchField;

    private Label authorField0;
    private Label authorField1;
    private Label authorField2;

    private Label titleField0;
    private Label titleField1;
    private Label titleField2;

    private Label descriptionField0;
    private Label descriptionField1;
    private Label descriptionField2;


    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) {

        primaryStage.setTitle("News App");

        // Fonts

        Font fontArial = Font.font("Arial", FontWeight.BOLD, 20);
        Font resultFont = Font.font("Arial", 18);

        //TItle Lavel

        Label titleLabel = new Label("Search Current News");
        titleLabel.setTranslateX(200);
        titleLabel.setFont(fontArial);
        titleLabel.setTranslateY(5);

        //Search FIeld

        searchField = new TextArea();
        searchField.setMaxHeight(10);
        searchField.setTranslateY(10);

        //Buttons

        Button searchButton = new Button("Search");
        searchButton.setTranslateX(60);
        searchButton.setTranslateY(20);
        searchButton.setOnAction(e -> searchNews(searchField.getText()));

        Button quitButton = new Button("Quit");
        quitButton.setTranslateX(480);
        quitButton.setTranslateY(-5);


        //Result Fields

        authorField0 = new Label("Author");
        authorField0.setTranslateX(60);
        authorField0.setTranslateY(70);
        authorField0.setFont(resultFont);


        authorField1 = new Label("Author1");
        authorField1.setTranslateX(60);
        authorField1.setTranslateY(180);
        authorField1.setFont(resultFont);


        authorField2 = new Label("Author2");
        authorField2.setTranslateX(60);
        authorField2.setTranslateY(300);
        authorField2.setFont(resultFont);


        titleField0 = new Label("Title0");
        titleField0.setTranslateX(400);
        titleField0.setTranslateY(6);
        titleField0.setFont(resultFont);
        titleField0.setWrapText(true);
        titleField0.setPrefWidth(300);


        titleField1 = new Label("Title1");
        titleField1.setTranslateX(400);
        titleField1.setTranslateY(120);
        titleField1.setFont(resultFont);
        titleLabel.setWrapText(true);
        titleLabel.setPrefWidth(300);

        titleField2 = new Label("Title2");
        titleField2.setTranslateX(400);
        titleField2.setTranslateY(235);
        titleField2.setFont(resultFont);
        titleField2.setWrapText(true);
        titleField2.setPrefWidth(300);

        descriptionField0 = new Label("Description0");
        descriptionField0.setTranslateX(720);
        descriptionField0.setTranslateY(-60);
        descriptionField0.setFont(resultFont);
        descriptionField0.setWrapText(true);
        descriptionField0.setPrefWidth(350);



        descriptionField1 = new Label("Description1");
        descriptionField1.setTranslateX(720);
        descriptionField1.setTranslateY(50);
        descriptionField1.setFont(resultFont);
        descriptionField1.setWrapText(true);
        descriptionField1.setPrefWidth(350);


        descriptionField2 = new Label("Description2");
        descriptionField2.setTranslateX(720);
        descriptionField2.setTranslateY(170);
        descriptionField2.setFont(resultFont);
        descriptionField2.setWrapText(true);
        descriptionField2.setPrefWidth(350);



    

        //Initial Layout and show GUI

        VBox layout = new VBox();
        layout.getChildren().addAll(titleLabel,searchField, searchButton, quitButton, authorField0, authorField1, 
        authorField2, titleField0, titleField1, titleField2, descriptionField0, descriptionField1, descriptionField2 );
        layout.setMaxHeight(200);

        Scene scene = new Scene(layout, 1000, 600);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public void searchNews(String userInput) {

        try {
            URL url = new URL(buildApiUrl(userInput));

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {

                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

                String line;

                StringBuffer resonse = new StringBuffer();

                while ((line = reader.readLine())!=null) {
                    resonse.append(line);
                    
                }

                parseNewsData(resonse.toString());

                reader.close();
                
            } else {
                displayMessage("ERROR! Cannot connect to API. ERROR CODE: " + connection.getResponseCode());
            }

            connection.disconnect();

        } catch (IOException e) {
            e.printStackTrace();
        }



    }

    public static String buildApiUrl(String userInput) {
        return String.format(apiURl + "?q=" + userInput + "&apiKey=" + apiKey);
    }

    private void parseNewsData(String getData) {

        JSONObject json = new JSONObject(getData);

        JSONArray articleArr = json.getJSONArray("articles");



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


        
        authorField0.setText(author0);
        authorField1.setText(author1);
        authorField2.setText(author2);

        titleField0.setText(title0);
        titleField1.setText(title1);
        titleField2.setText(title2);

        descriptionField0.setText(description0);
        descriptionField1.setText(description1);
        descriptionField2.setText(description2);


            
        }

        private void displayMessage(String message) {
            

        }

    

    
}