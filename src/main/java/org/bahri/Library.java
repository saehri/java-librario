package org.bahri;

import java.util.ArrayList;
import java.util.Scanner;

public class Library {
    protected ArrayList<Book> books = new ArrayList<>();
    protected ArrayList<Member> members = new ArrayList<>();
    protected User currentUser;
    protected Book selectedBook;

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

        Book newBook = new Book(title, writer, quantity, this.currentUser.currentBookIndex);

        this.books.add(newBook);
        this.currentUser.increaseCurrentBookIndex();
        this.currentUser.increaseTotalBooks(quantity);

        System.out.println("--------------------------------");
        System.out.println("Message: Successfully added new book:");
        newBook.showsBookDetails();
        System.out.println();
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

        System.out.println("\nMESSAGE: SUCCESSFULLY ADDED NEW MEMBER!\n");
    }

    public void addMember(ArrayList<Member> members) {
        this.members.addAll(members);
    }

    // CRUD - EDIT FUNCTIONS
    public void editBook() {
        Integer bookId = findBookId();
        Scanner sc = new Scanner(System.in);

        if(bookId.equals(-1)) return;

        System.out.println("-----------------------------------");
        System.out.println("You are about to edit this book:");
        this.selectedBook.showsBookDetails();
        System.out.println();

        System.out.print("librario\\Proceed? (Y|N) ");
        String confirm = sc.nextLine().toLowerCase().trim();

        if(confirm.equals("y") || confirm.equals("yes")) {
            while(true) {
                System.out.print("librario\\Which field do you want to edit? ");
                String field = sc.nextLine();
                System.out.print("librario\\Enter new data: ");
                String newData = sc.nextLine();

                this.selectedBook.editBookDetails(field, newData);

                System.out.print("\nlibrario\\Edit another field? (Y|N) ");
                String editAgain = sc.nextLine().toLowerCase().trim();

                if(editAgain.isEmpty() || editAgain.equals("n") | editAgain.equals("no")) {
                    System.out.println();
                    return;
                }
            }
        } else {
            System.out.println("--------------------------------");
            System.out.println("Warning: Canceled book editing!\n");
        }

        // clear selected book;
        this.selectedBook = null;
    }

    // CRUD - DELETE FUNCTIONS
    public void removeBook() {
        Integer bookId = findBookId();
        Scanner sc = new Scanner(System.in);

        if(bookId.equals(-1)) return;

        System.out.println("-----------------------------------");
        System.out.println("You are about to remove this book:");
        this.selectedBook.showsBookDetails();
        System.out.println();

        System.out.print("librario\\Proceed? (Y|N) ");
        String confirm = sc.nextLine().toLowerCase().trim();

        if(confirm.equals("y") || confirm.equals("yes")) {
            try {
                boolean removed = this.books.removeIf(book -> book.id.equals(bookId));
                if(!removed) throw new Exception("There are no books with id: " + bookId);
                else {
                    this.currentUser.decreaseTotalBooks(this.selectedBook.quantity);
                    System.out.println("\n-------------------------------");
                    System.out.println("Successfully removed book:");
                    this.selectedBook.showsBookDetails();
                    System.out.println();
                }
            } catch(Exception e) {
                System.out.println(e.getMessage());
            }
        } else {
            System.out.println("--------------------------------");
            System.out.println("Warning: Canceled book deletion!\n");
        }

        // clear selected book
        this.selectedBook = null;
    }

    // UTILS
    public Integer findBookId() {
        // make sure that there are books inside the list
        if(this.books.isEmpty()) {
            System.out.println("\n-----------------------------------");
            System.out.println("There are no books on your shelf. Start by adding one.\n");
            return -1;
        }

        Scanner sc = new Scanner(System.in);
        System.out.print("librario\\Enter book ID: ");
        Integer bookId = sc.nextInt();
        sc.nextLine();

        boolean isBookExist = false;
        // check if the books exist
        for (Book book : this.books) {
            if(book.id.equals(bookId)) {
                isBookExist = true;
                this.selectedBook = book;
                break;
            }
        }

        // handle book id invalid or book does not exist
        if(!isBookExist) {
            System.out.println("--------------------------------");
            System.out.println("Warning: There are no book with id " + bookId + "\n");
            return -1;
        }

        return bookId;
    }
}
