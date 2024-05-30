package org.bahri;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;

public class Member {
    public Integer id;
    public String name;
    public String email;
    public String phoneNumber;
    public String address;
    public boolean isActive;

    public Member(String name, Integer id) {
        this.id = id;
        this.name = name;
        this.email = "";
        this.phoneNumber = "";
        this.address = "";
        this.isActive = true;
    }

    public void showsMemberDetail() {
        System.out.println("ID: " + this.id);
        System.out.println("Name: " + this.name);
        System.out.println("Email: " + this.email);
        System.out.println("Phone number: " + this.phoneNumber);
        System.out.println("Active: " + this.isActive);
    }

    public void editMemberDetail(String field, String newData) {
        switch(field){
            case "name":
                this.name = newData;
                break;
            case "email":
                this.email = newData;
                break;
            case "phoneNumber":
            case "phone number":
                this.phoneNumber = newData;
                break;
            case "address":
                this.address = newData;
                break;
            default:
                System.out.println("You cannot modify this field: " + field);
        }
    }

    public void deactivate() {
        this.isActive = false;
    }

    // Constructor with parameters - This code is used by the jackson library to properly
    // construct the json string to object
    @JsonCreator
    public Member(@JsonProperty("id") Integer id,
                  @JsonProperty("name") String name,
                  @JsonProperty("email") String email,
                  @JsonProperty("address") String address,
                  @JsonProperty("isActive") boolean isActive,
                  @JsonProperty("phoneNumber") String phoneNumber) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.isActive = isActive;
    }
}