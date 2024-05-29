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
}