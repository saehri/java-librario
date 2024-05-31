package org.bahri;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class RentingEntry {
    public Integer id;
    public ArrayList<Integer> bookId;
    public String dueDate;
    public Integer memberId;
    public Member renter;
    public boolean returned;

    public RentingEntry(Integer id, String dueDate, Member renter, ArrayList<Integer> bookId) {
        this.id = id;
        this.bookId = bookId;
        this.renter = renter;
        this.memberId = renter.id;
        this.dueDate = dueDate;
        this.returned = false;
    }

    public void showRentingDetails() {
        if(this.returned) System.out.println("BOOK(S) HAS BEEN RETURNED.");
        System.out.println("ID: " + this.id);
        System.out.println("Due date: " + this.dueDate);
        System.out.println("Returned: " + this.returned);
        System.out.println("Member ID: " + this.memberId);
        System.out.println("Member name: " + this.renter.name);
        System.out.println("Member email: " + this.renter.email);
    }

    public void returnBook() {
        this.returned = true;
    }

    // Constructor with parameters - This code is used by the jackson library to properly
    // construct the json string to object
    @JsonCreator
    public RentingEntry(@JsonProperty("id") Integer id,
                        @JsonProperty("bookId") ArrayList<Integer> bookId,
                        @JsonProperty("dueDate") String dueDate,
                        @JsonProperty("memberId") Integer memberId,
                        @JsonProperty("returned") boolean returned,
                        @JsonProperty("member") Member renter) {
        this.id = id;
        this.bookId = bookId;
        this.dueDate = dueDate;
        this.memberId = memberId;
        this.renter = renter;
        this.returned = returned;
    }
}
