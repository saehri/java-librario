package org.bahri;

import java.util.ArrayList;
import java.util.Scanner;

public class Library {
    protected ArrayList<Book> books = new ArrayList<>();
    protected ArrayList<Member> members = new ArrayList<>();
    protected User currentUser;

    // CRUD - CREATE FUNCTIONS
    public void addUser(User user) {
        this.currentUser = user;
    }

    public void addBook() {
        Scanner sc = new Scanner(System.in);

        System.out.print("librario\\Title: ");
        String title = sc.nextLine();
        System.out.print("librario\\Writer: ");
        String writer = sc.nextLine();
        System.out.print("librario\\Amnt of book(s): ");
        Integer quantity = sc.nextInt();

        this.books.add(new Book(title, writer, quantity, this.currentUser.currentBookIndex));
        this.currentUser.increaseCurrentBookIndex();
        this.currentUser.increaseTotalBooks(quantity);
    }

    public void addBook(ArrayList<Book> books) {
        this.books.addAll(books);
    }

    public void addMember() {
        Scanner sc = new Scanner(System.in);

        System.out.print("librario\\Name: ");
        String name = sc.nextLine();

        this.members.add(new Member(name, this.currentUser.currentMemberIndex));
        this.currentUser.increaseCurrentMemberIndex();
    }

    public void addMember(ArrayList<Member> members) {
        this.members.addAll(members);
    }
}
