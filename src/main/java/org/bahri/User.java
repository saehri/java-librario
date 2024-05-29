package org.bahri;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class User {
    public String name;
    public String email;
    public String organizations;
    public Integer currentBookIndex;
    public Integer currentMemberIndex;
    public Integer totalBooks;

    public void increaseCurrentBookIndex() {
        this.currentBookIndex += 1;
    }

    public void increaseCurrentMemberIndex() {
        this.currentMemberIndex += 1;
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
                     @JsonProperty("currentBookIndex") int currentBookIndex,
                     @JsonProperty("currentMemberIndex") int currentMemberIndex) {
        this.name = name;
        this.email = email;
        this.organizations = organizations;
        this.currentBookIndex = currentBookIndex;
        this.totalBooks = totalBooks;
        this.currentMemberIndex = currentMemberIndex;
    }
}