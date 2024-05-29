package org.bahri;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Book {
    public Integer id;
    public Integer quantity;
    public Integer borrowedCount;
    public String title;
    public String genre;
    public String descriptions;
    public String writer;
    public String year;

    public Book(String title, String writer, Integer quantity, Integer id) {
        this.id = id;
        this.title = title;
        this.writer = writer;
        this.quantity = quantity;
        this.genre = "";
        this.borrowedCount = 0;
        this.descriptions = "";
        this.year = "";
    }

    public void showsBookDetails() {
        System.out.println("ID: " + this.id);
        System.out.println("Title: " + this.title);
        System.out.println("Description: " + this.descriptions);
        System.out.println("Writer: " + this.writer);
        System.out.println("Genre: " + this.genre);
        System.out.println("Year: " + this.year);
        System.out.println("Quantity: " + this.quantity);
        System.out.println("BorrowedCount: " + this.borrowedCount);
    }

    public void editBookDetails(String field, String newData) {
        switch(field){
            case "title":
                this.title = newData;
                break;
            case "description":
                this.descriptions = newData;
                break;
            case "writer":
                this.writer = newData;
                break;
            case "year":
                this.year = newData;
                break;
            case "genre":
                this.genre = newData;
                break;
            default:
                System.out.println("You cannot modify this field directly: " + field);
        }
    }

    // Constructor with parameters - This code is used by the jackson library to properly
    // construct the json string to object
    @JsonCreator
    public Book(@JsonProperty("id") int id,
                @JsonProperty("queryId") int queryId,
                @JsonProperty("quantity") int quantity,
                @JsonProperty("borrowedCount") int borrowedCount,
                @JsonProperty("title") String title,
                @JsonProperty("genre") String genre,
                @JsonProperty("year") String year,
                @JsonProperty("descriptions") String descriptions) {
        this.id = id;
        this.quantity = quantity;
        this.borrowedCount = borrowedCount;
        this.title = title;
        this.genre = genre;
        this.year = year;
        this.descriptions = descriptions;
    }
}
