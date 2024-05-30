package org.bahri;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // We are going to use for CRUD operation on our locale databases
        String bookDatabasePath = "databases/book.json";
        String memberDatabasePath = "databases/member.json";
        String userPrefDatabasePath = "databases/user-data.json";
        String transactionDatabasePath = "databases/transaction.json";

        // Initialize all app components and state
        Library library = new Library();
        DataManagement dm = new DataManagement();
        AppHelper ah = new AppHelper();
        Scanner sc = new Scanner(System.in);

        // sync local state
        library.addBook(dm.readListDataFromLocaleStorage(bookDatabasePath, Book.class));
        library.addMember(dm.readListDataFromLocaleStorage(memberDatabasePath, Member.class));
        library.addTransaction(dm.readListDataFromLocaleStorage(transactionDatabasePath, Transaction.class));
        library.addUser(dm.readDataFromLocaleStorage(userPrefDatabasePath, User.class));
        // -------------- END OF SYNCING PROCESS

        while (true) {
            // handle user commands
            System.out.print("librario\\");
            String userCommand = sc.nextLine();

            switch (userCommand) {
                case "add book":
                    library.addBook();
                    break;
                case "add member":
                    library.addMember();
                    break;
                case "show book":
                    library.showBooks();
                    break;
                case "show member":
                    library.showMember("active");
                    break;
                case "show member all":
                    library.showMember("all");
                    break;
                case "show member inactive only":
                    library.showMember("inactive");
                    break;
                case "edit member":
                    library.editMember();
                    break;
                case "rm book":
                    library.removeBook();
                    break;
                case "rm member":
                    library.removeMember();
                    break;
                case "edit book":
                    library.editBook();
                    break;
                case "close":
                    sc.close();
                    System.out.println("Closing....");
                    return;
                case "-h":
                    ah.showsAppMenu();
                    break;
                default:
                    System.out.println("\nlibrario\\ WARNING! UNDEFINED COMMAND.\nUse -h for help.\n");
                    break;
            }

            // save progress to local storage
            dm.writeDataToLocaleStorage(library.books, bookDatabasePath);
            dm.writeDataToLocaleStorage(library.members, memberDatabasePath);
            dm.writeDataToLocaleStorage(library.currentUser, userPrefDatabasePath);
            dm.writeDataToLocaleStorage(library.transactions, transactionDatabasePath);
        }
    }
}