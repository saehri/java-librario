package org.bahri;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class RentingEntry {
    public Integer id;
    public ArrayList<Integer> bookId;
    public String dueDate;
    public Integer memberId;
    public Member member;

    public RentingEntry(Integer id, String dueDate, Member member, ArrayList<Integer> bookId) {
        this.id = id;
        this.bookId = bookId;
        this.member = member;
        this.memberId = member.id;
        this.dueDate = dueDate;
    }

    public void showRentingDetails() {
        System.out.println("ID: " + this.id);
        System.out.println("Due date: " + this.dueDate);
        System.out.println("Member ID: " + this.memberId);
        System.out.println("Member name: " + this.member.name);
        System.out.println("Member email: " + this.member.email);
        System.out.println("-----------------------------------");
    }

    // Constructor with parameters - This code is used by the jackson library to properly
    // construct the json string to object
    @JsonCreator
    public RentingEntry(@JsonProperty("id") Integer id,
                        @JsonProperty("bookId") ArrayList<Integer> bookId,
                        @JsonProperty("dueDate") String dueDate,
                        @JsonProperty("memberId") Integer memberId,
                        @JsonProperty("member") Member member) {
        this.id = id;
        this.bookId = bookId;
        this.dueDate = dueDate;
        this.memberId = memberId;
        this.member = member;
    }
}