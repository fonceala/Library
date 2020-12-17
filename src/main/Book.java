package main;

import javafx.scene.image.Image;

import java.io.*;

public class Book {

    //This class makes easier data transfer accross stages
    //Hold all the data needed for a book

    private String name;
    private String author;
    private int price;
    private int year;
    private String editor;
    private String description;
    private File image;

    public Book(String name, String author, int price, int year, String editura, String description, File image) {
        this.name = name;
        this.author = author;
        this.price = price;
        this.year = year;
        this.editor = editura;
        this.description = description;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getEditor() {
        return editor;
    }

    public void setEditura(String editura) {
        this.editor = editura;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public File getImage() {
        return image;
    }

    public void setImage(File image) {
        this.image = image;
    }

    public byte[] makebyte() {
        try {

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(this.getImage());
            byte[] employeeAsBytes = baos.toByteArray();
            ByteArrayInputStream bais = new ByteArrayInputStream(employeeAsBytes);
            return employeeAsBytes;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public File read(byte[] data) {
        try {


            ByteArrayInputStream baip = new ByteArrayInputStream(data);
            ObjectInputStream ois = new ObjectInputStream(baip);
            File dataobj = (File ) ois.readObject();
            return dataobj ;

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String toString() {
        return this.getName();
    }
}
