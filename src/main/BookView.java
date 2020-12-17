package main;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.FileInputStream;

public class BookView extends Application {

    //This class displays all the book information

    private Book book;

    public BookView(Book book){
        this.book = book;
    }

    @Override
    public void start(Stage stage) throws Exception {
        BorderPane root = new BorderPane();
        Image image = new Image(new FileInputStream(book.read(book.makebyte()).getPath()));
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(200);
        imageView.setFitWidth(200);
        root.setCenter(imageView);
        TextArea ta = new TextArea();
        ta.setText(book.getDescription());
        Label author = new Label("The author of the book is: " + book.getAuthor());
        Label publication = new Label("The publication Year is: " + book.getYear());
        Label price = new Label("Renting the book will cost: " + book.getPrice());
        Label editor = new Label("This book is provided by: " + book.getEditor());
        TextArea description = new TextArea(book.getDescription());
        description.setEditable(false);
        VBox rightBox = new VBox();
        rightBox.setAlignment(Pos.CENTER);
        rightBox.setSpacing(20);
        rightBox.getChildren().addAll(author,publication,price,editor,description);

        root.setRight(rightBox);
        Scene scene = new Scene(root);
        stage.setTitle(book.getName());
        stage.setScene(scene);
        stage.show();
    }
}
