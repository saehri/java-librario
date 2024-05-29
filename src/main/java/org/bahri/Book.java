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

    public Book(String title, String writer, Integer quantity, Integer id) {
        this.id = id;
        this.title = title;
        this.writer = writer;
        this.quantity = quantity;
        this.genre = "";
        this.borrowedCount = 0;
        this.descriptions = "";
    }
}
