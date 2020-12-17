package main;

import java.util.ArrayList;
import java.util.List;

public class User {

    //User class makes easy the transfer of the data between stages
    //hold all the information needed for the user

    private String username;
    private String password;
    private List<String> rentedBooks;

    public User(String username,String password){
        this.username = username;
        this.password = password;
        rentedBooks = new ArrayList<>();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

   // public void addBook()
}
