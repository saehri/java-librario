package org.bahri;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;

public class Member {
    public Integer id;
    public String name;
    public ArrayList<Book> borrowing = new ArrayList<>();
    public String email;
    public String phoneNumber;
    public String address;

    public Member(String name, Integer id) {
        this.id = id;
        this.name = name;
        this.email = "";
        this.phoneNumber = "";
        this.address = "";
    }

    // Constructor with parameters - This code is used by the jackson library to properly
    // construct the json string to object
    @JsonCreator
    public Member(@JsonProperty("id") Integer id,
                  @JsonProperty("name") String name,
                  @JsonProperty("borrowing") ArrayList<Book> borrowing,
                  @JsonProperty("email") String email,
                  @JsonProperty("address") String address,
                  @JsonProperty("phoneNumber") String phoneNumber) {
        this.id = id;
        this.name = name;
        this.borrowing = borrowing;
        this.email = email;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }
}