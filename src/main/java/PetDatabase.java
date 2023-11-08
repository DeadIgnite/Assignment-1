/*
    Author: Cody Zellmer-Johnson
    Class: CSC 422
    Assignment: Version Control Pet Database
    Release: 5
 */
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class PetDatabase {
    // list and scanner to be used throughout the application
    private static ArrayList<Pet> petList;
    private static final Scanner s = new Scanner(System.in);

    public static void main(String[] args) {
        loadPets();
        System.out.println("Pet Database Program.");
        // calls getUserDecision function and uses switch statement to call correct function
        do {
            switch (getUserDecision()) {
                case "1" -> showAllPets();
                case "2" -> addPet();
                case "3" -> removePet();
                case "4" -> {
                    System.out.println("Thank you for using the Pet Database! Goodbye!");
                    savePets();
                    System.exit(0);
                }
            }
        } while (true);
    }

    private static void loadPets() {
        File newFile = new File("pets.bin");
        if (newFile.length() != 0) {
            try {
                FileInputStream fis = new FileInputStream(newFile);
                ObjectInputStream ois = new ObjectInputStream(fis);
                petList = (ArrayList<Pet>) ois.readObject();
                ois.close(); fis.close();
            } catch (IOException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        } else {
            petList = new ArrayList<>();
        }
    }

    private static void savePets() {
        File newFile = new File("pets.bin");
        try {
            FileOutputStream fos = new FileOutputStream(newFile);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(petList);
            oos.close(); fos.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static String getUserDecision() {
        // prints table of choices
        System.out.println("What would you like to do?");
        System.out.println("\t1) View all pets");
        System.out.println("\t2) Add new pets");
        System.out.println("\t3) Remove an existing pet");
        System.out.println("\t4) Exit program");
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
        // clears the new line character left over
        s.nextLine();
        do {
            // gets name and age
            System.out.print("add pet (name age): ");
            String pet = s.nextLine();
            // if the user types done break the loop
            if (pet.equalsIgnoreCase("done")) {
                break;
            }
            String[] petInfo = pet.split(" ");
            // if database is full
            if (petList.size() == 5) {
                System.out.println("Error: Database is full.");
                break;
                // if the input does not contain exactly 2 values
            } else if (petInfo.length != 2) {
                System.out.println("Error: " + pet + " is not a valid input.");
                // if the age is less than 1 or greater than 20
            } else if(1 > Integer.parseInt(petInfo[1]) || Integer.parseInt(petInfo[1]) > 20) {
                System.out.println("Error: " + petInfo[1] + " is not a valid age.");
            } else {
                // creates new pet object and adds it to list
                petList.add(new Pet(petInfo[0], Integer.parseInt(petInfo[1])));
            }
        } while (true);
    }

    /*private static void searchByName() {
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

    private static void updatePet() {
        // prints all pets
        showAllPets();
        // gets specified ID
        System.out.print("Enter the ID of the pet you wish to update: ");
        int id = s.nextInt();
        Pet pet = petList.get(id);
        // gets updated name and age
        System.out.print("Enter new name and age for the pet (separate by a space): ");
        String name = s.next();
        int age = s.nextInt();
        // prints feed back for user
        System.out.printf("%s %d has been updated to ", pet.getName(), pet.getAge());
        // updates the pet
        pet.setName(name);
        pet.setAge(age);
        System.out.printf("%s %d.\n", pet.getName(), pet.getAge());
    } */

    private static void removePet() {
        showAllPets();
        // gets the desired ID
        System.out.print("Enter the ID of the pet you would like to remove: ");
        int id = s.nextInt();
        if (id >= petList.size() || id <= 0) {
            System.out.println("Error: ID " + id + " does not exist.");
        } else {
            // prints feedback for the user
            System.out.printf("%s %d has been removed.\n", petList.get(id).getName(), petList.get(id).getAge());
            // removes the pet from the list
            petList.remove(id);
        }
    }
}
