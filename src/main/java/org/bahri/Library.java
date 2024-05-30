package org.bahri;

import java.util.ArrayList;
import java.util.Scanner;

public class Library {
    protected ArrayList<Book> books = new ArrayList<>();
    protected ArrayList<Member> members = new ArrayList<>();
    protected ArrayList<RentingEntry> rentingEntryList = new ArrayList<>();
    protected User currentUser;
    protected Book selectedBook;
    protected Member selectedMember;

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

        Book newBook = new Book(title, writer, quantity, this.currentUser.currentBookId);

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

        System.out.print("librario\\Enter member name: ");
        String name = sc.nextLine();

        Member newMember = new Member(name, this.currentUser.currentMemberId);

        this.members.add(newMember);
        this.currentUser.increaseCurrentMemberIndex();

        System.out.println("--------------------------------");
        System.out.println("Message: Successfully added new member:");
        newMember.showsMemberDetail();
        System.out.println();
    }

    public void addMember(ArrayList<Member> members) {
        this.members.addAll(members);
    }

    public void addRentingEntry() {
        Scanner sc = new Scanner(System.in);

        Integer memberId = -1;
        while(memberId == -1) {
            memberId = findMember();
        }

        if(!this.selectedMember.isActive) {
            System.out.println("--------------------------------");
            System.out.println("Message: This is inactive member. You need to activate for this actions.");
            this.selectedMember.showsMemberDetail();
            System.out.println();
            return;
        }

        // make sure that member can rent several books at the same time
        // thus why we use while loop
        ArrayList<Integer> rentedBooks = new ArrayList<>();

        while (true) {
            Integer bookId = findBook();

            if(bookId != -1) { // make sure that the book is existed
                boolean isCanRent = (this.selectedBook.rentedBookCount + 1) <= this.selectedBook.quantity;

                if(isCanRent) {
                    this.selectedBook.increaseRentedBokCount(1);
                    rentedBooks.add(this.selectedBook.id);

                    System.out.print("librario\\Add next? (Y|N): ");
                    String continueAdd = sc.nextLine().toLowerCase().trim();

                    if(!(continueAdd.equals("y") || continueAdd.equals("yes"))) break;
                } else {
                    System.out.println("--------------------------------");
                    System.out.println("Message: You can't rent this book. Because the books");
                    System.out.println("are fully rented.\n");
                    break;
                }
            }
        }

        if(!rentedBooks.isEmpty()) {
            System.out.print("librario\\Enter due date: ");
            String dueDate = sc.nextLine();

            RentingEntry newRentingEntry = new RentingEntry(
                    this.currentUser.currentBookRentingId,
                    dueDate,
                    this.selectedMember,
                    rentedBooks);
            this.rentingEntryList.add(newRentingEntry);
            this.currentUser.increaseCurrentBookRentingId();

            System.out.println("--------------------------------");
            System.out.println("Message: Successfully added new transaction:");
            newRentingEntry.showRentingDetails();
            System.out.println();
        }
    }

    public void addRentingEntry(ArrayList<RentingEntry> rentingEntries) {
        this.rentingEntryList.addAll(rentingEntries);
    }

    // CRUD - READ FUNCTIONS
    public void showBooks() {
        if(this.books.isEmpty()) {
            System.out.println("-----------------------------------");
            System.out.println("There are no books on your shelf. Start by adding one.\n");
            return;
        }

        System.out.println("-------------------------------");
        System.out.println("Books on your shelf:");

        for(Book book : this.books) {
            book.showsBookDetails();
            System.out.println("----------------------------");
        }
        System.out.println();
    }

    public void showMember(String memberState) {
        if(this.members.isEmpty()) {
            System.out.println("-----------------------------------");
            System.out.println("There are no registered member. Start by adding one.\n");
            return;
        }

        System.out.println("-------------------------------");
        System.out.println("Registered member:");

        if(memberState.equals("all")) {
            for(Member member : this.members) {
                member.showsMemberDetail();
                System.out.println("----------------------------");
            }

            System.out.println();
            return;
        }

        if(memberState.equals("active")) {
            for(Member member : this.members) {
                if(member.isActive) {
                    member.showsMemberDetail();
                    System.out.println("----------------------------");
                }
            }

            System.out.println();
            return;
        }

        for(Member member : this.members) {
            if(!member.isActive) {
                member.showsMemberDetail();
                System.out.println("----------------------------");
            }
        }

        System.out.println();
    }

    // CRUD - EDIT FUNCTIONS
    public void editBook() {
        Integer bookId = findBook();
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
                String field = sc.nextLine().toLowerCase().trim();
                System.out.print("librario\\Enter new data: ");
                String newData = sc.nextLine();

                this.selectedBook.editBookDetails(field, newData);

                System.out.print("\nlibrario\\Edit another field? (Y|N) ");
                String editAgain = sc.nextLine().toLowerCase().trim();

                if(editAgain.isEmpty() || editAgain.equals("n") | editAgain.equals("no")) {
                    System.out.println("----------------------------");
                    System.out.println("Edited book:");
                    this.selectedBook.showsBookDetails();
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

    public void editMember() {
        Integer memberId = findMember();
        Scanner sc = new Scanner(System.in);

        if(memberId.equals(-1)) return;

        System.out.println("-----------------------------------");
        System.out.println("You are about to edit this member data:");
        this.selectedMember.showsMemberDetail();
        System.out.println();

        System.out.print("librario\\Proceed? (Y|N) ");
        String confirm = sc.nextLine().toLowerCase().trim();

        if(confirm.equals("y") || confirm.equals("yes")) {
            while(true) {
                System.out.print("librario\\Which field do you want to edit? ");
                String field = sc.nextLine().toLowerCase().trim();
                System.out.print("librario\\Enter new data: ");
                String newData = sc.nextLine();

                this.selectedMember.editMemberDetail(field, newData);
                System.out.print("\nlibrario\\Edit another field? (Y|N) ");
                String editAgain = sc.nextLine().toLowerCase().trim();

                if(editAgain.isEmpty() || editAgain.equals("n") | editAgain.equals("no")) {
                    System.out.println("----------------------------");
                    System.out.println("Edited member:");
                    this.selectedMember.showsMemberDetail();
                    System.out.println();
                    return;
                }
            }
        } else {
            System.out.println("--------------------------------");
            System.out.println("Warning: Canceled member editing!\n");
        }

        // clear selected book;
        this.selectedBook = null;
    }

    // CRUD - DELETE FUNCTIONS
    public void removeBook() {
        Integer bookId = findBook();
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

    public void removeMember() {
        Integer memberId = findMember();
        Scanner sc = new Scanner(System.in);

        if(memberId.equals(-1)) return;

        System.out.println("-----------------------------------");
        System.out.println("You are about to remove this member:");
        this.selectedMember.showsMemberDetail();
        System.out.println();

        System.out.print("librario\\Proceed? (Y|N) ");
        String confirm = sc.nextLine().toLowerCase().trim();

        if(confirm.equals("y") || confirm.equals("yes")) {
            this.selectedMember.deactivate();
            System.out.println("--------------------------------");
            System.out.println("Message: Successfully removed member:");
            this.selectedMember.showsMemberDetail();
            System.out.println();
        } else {
            System.out.println("--------------------------------");
            System.out.println("Warning: Canceled book deletion!\n");
        }

        // clear selected member
        this.selectedMember = null;
    }

    // UTILS
    public Integer findBook() {
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

    public Integer findMember() {
        // make sure that there are books inside the list
        if(this.members.isEmpty()) {
            System.out.println("\n-----------------------------------");
            System.out.println("There are no members registered. Start by adding one.\n");
            return -1;
        }

        Scanner sc = new Scanner(System.in);
        System.out.print("librario\\Enter member ID: ");
        Integer memberId = sc.nextInt();
        sc.nextLine();

        boolean isMemberExist = false;
        // check if the books exist
        for (Member member : this.members) {
            if(member.id.equals(memberId)) {
                isMemberExist = true;
                this.selectedMember = member;
                break;
            }
        }

        // handle book id invalid or book does not exist
        if(!isMemberExist) {
            System.out.println("--------------------------------");
            System.out.println("Warning: There are no member with id " + memberId + "\n");
            return -1;
        }

        return memberId;
    }

    public void resetLibrary() {
        Scanner sc = new Scanner(System.in);
        System.out.println("---------------------------------------------");
        System.out.println("Warning: You are about to reset your library.");
        System.out.println("all saved data will be removed.");
        System.out.print("librario\\Proceed (Y|N)? ");
        String confirm = sc.nextLine().toLowerCase().trim();

        if(confirm.equals("y") || confirm.equals("yes")) {
            this.books = new ArrayList<>();
            this.members = new ArrayList<>();
            this.rentingEntryList = new ArrayList<>();
            this.selectedMember = null;
            this.selectedBook = null;

            this.currentUser.totalBooks = 0;
            this.currentUser.currentBookId = 0;
            this.currentUser.currentMemberId = 0;
            this.currentUser.currentBookRentingId = 0;

            System.out.println("------------------------------------");
            System.out.println("Message: Library successfully cleared.\n");
        } else {
            System.out.println("------------------------------------");
            System.out.println("Message: Canceled resetting library.\n");
        }
    }
}
