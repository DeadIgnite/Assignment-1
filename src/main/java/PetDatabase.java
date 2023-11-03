/*
    Author: Cody Zellmer-Johnson
    Class: CSC 422
    Assignment: Version Control Pet Database
    Release: 1
 */
import java.util.ArrayList;
import java.util.Scanner;

public class PetDatabase {
    // list and scanner to be used throughout the application
    private static final ArrayList<Pet> petList = new ArrayList<>();
    private static final Scanner s = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Pet Database Program.");
        // calls getUserDecision function and uses switch statement to call correct function
        do {
            switch (getUserDecision()) {
                case "1" -> showAllPets();
                case "2" -> addPet();
                case "3" -> searchByName();
                case "4" -> searchByAge();
                case "5" -> {
                    System.out.println("Thank you for using the Pet Database! Goodbye!");
                    System.exit(0);
                }
            }
        } while (true);
    }

    private static String getUserDecision() {
        // prints table of choices
        System.out.println("What would you like to do?");
        System.out.println("\t1) View all pets");
        System.out.println("\t2) Add more pets");
        System.out.println("\t3) Search pets by name");
        System.out.println("\t4) search pets by age");
        System.out.println("\t5) Exit program");
        System.out.print("Your choice: ");
        // returns the users choice
        return s.next();
    }

    private static void printDashes() {
        // prints + at the beginning and end, dashes in the middle
        for (int i = 0; i <= 25; i++) {
            if (i == 0 || i == 25) {
                System.out.print("+");
            } else {
                System.out.print("-");
            }
        }
        System.out.print("\n");
    }

    // prints table header when called
    private static void printTableHeader() {
        printDashes();
        System.out.printf("| %3s | %-10s| %4s |\n", "ID", "Name", "Age");
        printDashes();
    }

    // prints table footer when called
    private static void printTableFooter(int rowCount) {
        printDashes();
        System.out.println(rowCount + " rows in set.");
    }

    // prints a table row using information from passed in Pet object when called
    private static void printTableRow(Pet pet) {
        System.out.printf("| %-3d | %-10s| %4d |\n", petList.indexOf(pet), pet.getName(), pet.getAge());
    }

    private static void showAllPets() {
        printTableHeader();
        // for each pet in the list, print a row
        for (Pet pet : petList) {
            printTableRow(pet);
        }
        printTableFooter(petList.size());
    }

    private static void addPet() {
        do {
            // gets name and age
            System.out.print("Enter the pet's name (type 'done' to exit): ");
            String name = s.next();
            // if the user types done break the loop
            if (name.equalsIgnoreCase("done")) {
                break;
            }
            System.out.print("Enter the pet's age: ");
            int age = s.nextInt();
            // creates new pet object and adds it to list
            petList.add(new Pet(name, age));
        } while (true);
    }

    private static void searchByName() {
        // gets name to search for
        System.out.print("Enter the name you would like to search for: ");
        String name = s.next();
        printTableHeader();
        int counter = 0;
        // for each pet in the list
        for (Pet pet : petList) {
            // if the name matches the chosen name
            if (pet.getName().equalsIgnoreCase(name)) {
                // print a row
                printTableRow(pet);
                counter++;
            }
        }
        printTableFooter(counter);
    }

    private static void searchByAge() {
        // gets age to search for
        System.out.print("Enter the age you would like to search for: ");
        int age = s.nextInt();
        printTableHeader();
        int counter = 0;
        // for each pet in the list
        for (Pet pet : petList) {
            // if the age matches the chosen age
            if (pet.getAge() == age) {
                // print a row
                printTableRow(pet);
                counter++;
            }
        }
        printTableFooter(counter);
    }
}
