package main;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class Login extends Application {

    //This class is made manipulate the login data
    //It validates the credential
    //lets you register
    //communicates with the database to see if the user and password are correct

    private Register registerForm;
    private Database database;
    private User user;
    private Library library;

    @Override
    public void start(Stage primaryStage) throws Exception{
        database = new Database();
        database.createDatabase();
        database.createUserTable();
        //Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
        BorderPane root = new BorderPane();

        //Elemente pentru centrul interfetei
        HBox usnBox = new HBox();
        Label username = new Label("Username");
        TextField usnText = new TextField();
        usnBox.setAlignment(Pos.CENTER);
        usnBox.setSpacing(10);
        usnBox.getChildren().addAll(username,usnText);

        HBox pwdBox = new HBox();
        Label password = new Label("Password");
        PasswordField pwdText = new PasswordField();
        pwdBox.setAlignment(Pos.CENTER);
        pwdBox.setSpacing(10);
        pwdBox.getChildren().addAll(password,pwdText);

        Button loginButton = new Button("Login");
        loginButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                handleLoginButton(usnText.getText(),pwdText.getText(),usnText,pwdText,actionEvent);
            }
        });

        VBox loginBox = new VBox();
        loginBox.setSpacing(10);
        loginBox.setPadding(new Insets(70,10,10,10));
        loginBox.setAlignment(Pos.CENTER);
        loginBox.getChildren().addAll(usnBox,pwdBox,loginButton);


        //Elemente pentru bottom
        VBox registerBox = new VBox();
        Label register = new Label("Don't have an account yet? Register");
        Button registerButton = new Button("Register!");
        registerButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    handleRegisterButton(actionEvent);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        registerBox.setAlignment(Pos.CENTER);
        registerBox.getChildren().addAll(register,registerButton);
        registerBox.setPadding(new Insets(20,20,20,20));
        root.setCenter(loginBox);
        root.setBottom(registerBox);


        primaryStage.setTitle("Login");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }

    public void handleRegisterButton(ActionEvent event) throws Exception {
        registerForm = new Register();
        Stage stage = new Stage();
        stage.setTitle("Register");
        registerForm.start(stage);
        // ((Node)(event.getSource())).getScene().getWindow().hide();
    }


    public void handleLoginButton(String username, String password,TextField tf, PasswordField passwordField, ActionEvent event){
        if(!database.checkCredentials(username,password)){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Login failed!");
            alert.setHeaderText("Try again!");
            alert.setContentText("Username or password do not match the database!");
            tf.setText("");
            passwordField.setText("");
            alert.show();
        }else{
//            Alert alert = new Alert(Alert.AlertType.INFORMATION);
//            alert.setTitle("Login successful!");
//            alert.setHeaderText("Welcome!");
//            alert.setContentText("You will be logged in soon!");
//            alert.show();
            user = new User(username,password);
            ((Node)(event.getSource())).getScene().getWindow().hide();
            try{
                database.createRentTable();
                library = new Library(user);
                Stage stage = new Stage();
                library.start(stage);
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }

    //public void handleLoginButton()
    public static void main(String[] args) {
        launch(args);
    }
}