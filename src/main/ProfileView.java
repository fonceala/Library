package main;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class ProfileView extends Application {

    //This class shows the books that every useer has rented
    //Different for every user

    private User user;
    private Database database;
    public ProfileView(User user){
        this.user = user;
    }

    @Override
    public void start(Stage stage) throws Exception {
        BorderPane root = new BorderPane();
        database = new Database();
        Label listOfBooks = new Label("The books you rented are: ");
        root.setTop(listOfBooks);

        ListView listView = new ListView();
        ObservableList<String> list = FXCollections.observableArrayList();
        for(String s: database.getBook(user.getUsername())){
            list.add(s);
        }

        listView.setItems(list);

        root.setCenter(listView);
        Scene scene = new Scene(root);
        stage.setTitle(user.getUsername() + "'s Profile");
        stage.setScene(scene);
        stage.show();
    }
}
