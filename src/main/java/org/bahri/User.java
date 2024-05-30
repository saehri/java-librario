package org.bahri;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class User {
    public String name;
    public String email;
    public String organizations;
    public Integer currentBookId;
    public Integer currentMemberId;
    public Integer currentBookRentingId;
    public Integer totalBooks;

    public void increaseCurrentBookIndex() {
        this.currentBookId += 1;
    }

    public void increaseCurrentMemberIndex() {
        this.currentMemberId += 1;
    }

    public void increaseCurrentBookRentingId() {
        this.currentBookRentingId += 1;
    }

    public void increaseTotalBooks(Integer amount) {
        this.totalBooks += amount;
    }

    public void decreaseTotalBooks(Integer amount) {
        this.totalBooks -= amount;
    }

    @JsonCreator
    public User(@JsonProperty("name") String name,
                     @JsonProperty("email") String email,
                     @JsonProperty("organizations") String organizations,
                     @JsonProperty("totalBooks") int totalBooks,
                     @JsonProperty("currentBookIndex") int currentBookId,
                     @JsonProperty("currentMemberIndex") int currentMemberId) {
        this.name = name;
        this.email = email;
        this.organizations = organizations;
        this.currentBookId = currentBookId;
        this.totalBooks = totalBooks;
        this.currentMemberId = currentMemberId;
    }
}