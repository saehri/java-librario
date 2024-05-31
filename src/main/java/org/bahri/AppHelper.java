package org.bahri;

public class AppHelper {
    public void showsAppMenu() {
        System.out.println("-----------------------------------------------");
        System.out.println("Lists of commands:");
        System.out.println("- -h for help");
        System.out.println("- add (book | member) for adding data");
        System.out.println("- show (book | member all | member inactive only | renting) for viewing data");
        System.out.println("- rm (book | member | renting) for removing data");
        System.out.println("- rent for renting a book");
        System.out.println("- edit (book | member | renting) for editing data");
        System.out.println("- member (activate | deactivate)");
        System.out.println("- reset library for resetting data");
        System.out.println("- close\n");
    }
}
