package main;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Library extends Application {

    //This class shows the books in the database
    //Has a search engine for different columns of the database
    //Made for each user

    private User user;
    private Database database;
    private List<Book> bookList;
    private BookView bookView;
    private ListView listview;
    private ProfileView profileView;
    public Library(User user){
        this.user = user;
    }

    @Override
    public void start(Stage stage) throws Exception {
        database = new Database();
        database.createBookTable();

        //Cod pentru populare top
        Label usertext = new Label("Logged in as: " + user.getUsername());
        BorderPane root = new BorderPane();
        Button rentButton = new Button("Rent Book!");
        Button logOut = new Button("Logout");
        logOut.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    handleLogoutButton(event);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        Button profile = new Button("Profile");
        profile.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    handleProfileButton();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        HBox topBox = new HBox();
        topBox.setAlignment(Pos.CENTER);
        topBox.setSpacing(20);

        topBox.getChildren().addAll(usertext,rentButton,profile,logOut);
        root.setTop(topBox);
        //===============================================================


        //Cod pentru popularea listview-ului
        VBox centerBox = new VBox();
        centerBox.setAlignment(Pos.CENTER);
        centerBox.setSpacing(10);

        HBox searchBox = new HBox();
        searchBox.setAlignment(Pos.CENTER);
        searchBox.setSpacing(10);

        Button refresh = new Button("Refresh!");
        TextField tf = new TextField();
        ChoiceBox<String> choiceBox = new ChoiceBox<>();
        ObservableList<String> list = FXCollections.observableArrayList();
        list.addAll("Title","Author","Year","Editor");
        choiceBox.setItems(list);
        Button searchButton = new Button("Search");
        searchBox.getChildren().addAll(tf,choiceBox,searchButton,refresh);
        searchButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                handleChoiceBox(choiceBox.getSelectionModel().getSelectedItem(),tf.getText());
            }
        });
        bookList = new ArrayList<>();
        Book b1 = new Book("Shakespeare s The Tempest and The Elizabethan World",
                           "Andrei Zlatescu",
                35,
                             2014,
                           "Publica",
                        "A dramatic riddle vacillating between older and newer symbols of Elizabethan and Jacobean Court cultures, Shakespeare's The Tempest opens original epistemic parallels between assertive politics and its subsequent nihilism, for its essence is in the very representation of ritual origins. Prospero’s provisional tyranny disposes the absolute answers of representative knowledge, his enlightening work of wizardry reduces knowledge itself to an act of efficient representation, nonetheless an empty epiphany. Thus, Shakespeare’s last major folio is both ritual and play, an unprecedented work of “magic theatre” that institutes new stage conventions- in which both the meaningful “fabulation” of the sacred drama and its prescriptive performance in history are shown: a dramatic structure that can produce events in history within its narrative agency, truth that find its new measure in representation.",
                                 new File("./src/images/book1.jpeg"));
        Book b2 = new Book("Vocabulary In Practice 1",
                           "Glennis Pye",
                            41,
                            2002,
                          "Cambridge University Press",
                       "A series of handy vocabulary books aimed at busy learners who want to fit in some extra practice outside the classroom. Offering plenty of practice for vocabulary learners, these small and easy-to-carry books are ideal for busy people to use outside the classroom. All the words have been chosen using the Cambridge International Corpus to ensure that students learn the most widely used vocabulary. Includes regular tests and helpful word lists. This is Level 1/Beginner.",
                                 new File("./src/images/book2.jpeg"));
        Book b3 = new Book("Inteligenta Artificiala",
                "Angie Smibert",
                45,
                2020,
                "Paralela 45",
                "Ce este inteligen?a artificiala? Ce nevoie avem de ea? Cum o sa ne schimbe via?a? Este periculoasa?\n" +
                        "Afla totul despre ma?inile care opereaza cu date ?i înva?a, despre robo?i care pot ajuta oamenii într-un mod uimitor, a?a încât sa ia decizii mai bune ?i sa lucreze mai eficient. Ba chiar ?i sa se distreze mai mult!\n" +
                        "Printr-o combina?ie de activita?i STEM ?i explica?ii pe în?elesul lor, tinerii pasiona?i de ?tiin?a ?i tehnologie descopera ma?inile inteligente de ieri ?i de azi ?i înva?a totul despre rolul ?i utilitatea lor în diverse domenii, indiferent daca e vorba de medicina sau divertisment.",
                new File("./src/images/book3.jpeg"));
        Book b4 = new Book("Print it!",
                "Joy Jolliffe",
                63,
                2016,
                "PAVILION BOOKS",
                "Prints are in! Take a walk down the high street and you won't be able to escape beautiful prints on everything from dresses and bags to gorgeous printed sheets. All these prints help us express our unique style and make vivid personal statements.\n" +
                        "Joy Jolliffe demonstrates that printing is a simple, effective way to make your mark on a multitude of surfaces from clothes to bedding.\n" +
                        "The book features chapters on block printing, stencils, transfer and open screen printing, and tips to guarantee perfect results every time! Projects include a Daisy Border Tea Towel, Photo Montage Wall Art and Sepia Tint Primrose Skirt. Packed with stunning ideas and techniques you'll never be short of inspiration.",
                new File("./src/images/book4.jpeg"));
        Book b5 = new Book("Leadership is Language",
                "L.David Marquet",
                89,
                2020,
                "Penguin Books Ltd",
                "Full of compelling advice on how to lead more effectively by choosing your words more wisely' - ADAM GRANT, author of Originals and Give and Take\n" +
                        "FT Book of the Month\n" +
                        "Your words matter more than you think\n" +
                        "Most of us use the language we inherited from a time when workers worked with their hands and managers worked with their heads. Today, your people do much more than simply follow orders. They contribute to performance and solve problems, and it's time we updated our language to reflect that.\n" +
                        "In Leadership Is Language, former US Navy captain L. David Marquet offers a radical playbook to empower your people and put your team on a path to continuous improvement. The framework will help you achieve the right balance between deliberation and action, and take bold risks without endangering your mission. Among other things, you'll learn:\n" +
                        "· How to avoid the seven common sins of questioning, from binary questions (should we do A or B?) to self-affirming questions (B is the better option, right?)\n" +
                        "Why you should vote first, then discuss, when deciding on a plan with your team, rather than voting after discussion\n" +
                        "· Why it's better to give your people information instead of instructions\n" +
                        "As a submarine captain, Marquet used his counterintuitive model of leadership to turn the worst-performing submarine crew into the best-performing one in the fleet, a story he recounted in his bestselling book Turn the Ship Around! Now, in Leadership Is Language, he draws on a wide range of examples, from the 2017 Oscars Best Picture mishap to the tragic sinking of the SS El Faro, to show you exactly how the words you use (and don't use) impact how your people contribute.",
                new File("./src/images/book5.jpeg"));
        Book b6 = new Book("How to be an Anglo-Saxon in 13 Easy Stages",
                "Scoular Anderson",
                33,
                2011,
                "HarperCollins Publishers",
                "So you want to be an Anglo-Saxon? Find out how to do it in just a few stages. From fighting the Vikings and praying to gods, to making books from animal skin - all is revealed in this fun non-fiction guide by Scoular Anderson.",
                new File("./src/images/book6.jpeg"));
        addBook(b1,bookList);
        addBook(b2,bookList);
        addBook(b3,bookList);
        addBook(b4,bookList);
        addBook(b5,bookList);
        addBook(b6,bookList);
        for(Book b: bookList){
            if(!database.checkBook(b.getName())){
                uploadBook(b);
            }
        }
        listview = new ListView();
        ObservableList<String> items = FXCollections.observableArrayList();
        for(Book b: bookList){
            items.add(b.toString());
        }
        listview.setItems(items);
        refresh.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                listview.setItems(items);
            }
        });
        listview.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {

                    if(mouseEvent.getClickCount() == 2) {
                        Book book = findBook((String) listview.getSelectionModel().getSelectedItem(), bookList);
                        handleListView(book);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        centerBox.getChildren().addAll(searchBox,listview);
        root.setCenter(centerBox);
        //======================================================================

        rentButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                handleRentButton(listview.getSelectionModel().getSelectedItem().toString(),user.getUsername());
            }
        });
        Scene scene = new Scene(root, 600, 900);
        stage.setTitle("Library Application");
        stage.setScene(scene);
        stage.show();
    }

    public void uploadBook(Book book){
        database.insertBook(book.getName(), book.getAuthor(), book.getEditor(), book.getDescription(), book.getPrice(), book.getYear(), book.makebyte());
    }

    public void handleListView(Object book) throws Exception {
        bookView = new BookView((Book) book);
        bookView.start(new Stage());
    }

    public Book findBook(String title,List<Book> books){
        for(Book b: books){
            if(b.getName().equals(title)){
                return b;
            }
        }
        return null;
    }

    public void handleRentButton(String title,String user){
        if(!database.checkRent(title,user)){
            database.insertRent(user,title);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Perfect!");
            alert.setHeaderText("You have 1 more book!");
            alert.setContentText("You rented the book successfully");
            alert.show();
        }
    }

    public void handleChoiceBox(String choice,String value){
        List<Book> listBook;
        ObservableList<String> items = FXCollections.observableArrayList();

        listBook = database.getBookBy(choice,value);
        for(Book b: listBook){
            items.add(b.getName());
        }

        listview.setItems(items);
    }
    public void handleProfileButton() throws Exception {
        profileView = new ProfileView(user);
        profileView.start(new Stage());
    }

    public void handleLogoutButton(ActionEvent event) throws Exception {
        ((Node)(event.getSource())).getScene().getWindow().hide();
        new Login().start(new Stage());
    }

    public void addBook(Book book, List<Book> list){
        if(findBook(book.getName(),list) == null) {
            bookList.add(book);
        }
    }
}
