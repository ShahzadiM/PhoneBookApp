import java.util.*;
import java.io.*;
public class PhoneBookApp {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Name of input file: ");
        String filename = scanner.next();
        Scanner fileScanner = new Scanner(new File(filename));

        final int MAX = 10;
        String[] firstnames = new String[MAX];
        String[] lastnames = new String[MAX];
        String[] PhoneNumber = new String[MAX];

        int count = read(firstnames, lastnames, PhoneNumber, fileScanner);

        char choice;
        do {
            printMenu();
            choice = scanner.next().charAt(0);
            switch (choice) {
                case 'l':
                    lookup(firstnames, lastnames, PhoneNumber, scanner, count);
                    break;
                case 'r':
                    reverseLookup(firstnames, lastnames, PhoneNumber, count, scanner);
                    break;
                case 'c':
                    changeNumber(firstnames, lastnames, PhoneNumber, scanner, count);
                    break;
                case 'a':
                    boolean added = addNumber(firstnames, lastnames, count, scanner, MAX, PhoneNumber);
                    if (added) {
                        count++;
                    }
                    break;
                case 'q':
                    break;
                default:
                    System.out.println("Invalid choice");
            }
        } while (choice != 'q');


        System.out.println("Name of output file: ");
        filename = scanner.next();
        Printphonenumberstofile(filename, firstnames, lastnames, PhoneNumber, count);

    }

    public static int index(String[] firstnames, String[] lastnames, String firstname, String lastname, int count) {
        for (int i = 0; i < count; i++) {
            if (firstnames[i].equals(firstname) && lastnames[i].equals(lastname)) {
                return i;
            }
        }
        return -1;
    }

    public static int read(String[] firstnames, String[] lastnames, String[] PhoneNumber, Scanner scanner) {
        int count = 0;
        while (scanner.hasNext()) {
            firstnames[count] = scanner.next();
            lastnames[count] = scanner.next();
            PhoneNumber[count] = scanner.next();

            count++;
        }
        return count;
    }

    public static void printMenu() {
        System.out.println("Choices:");
        System.out.print("l: lookup, ");
        System.out.print("r: reverse lookup, ");
        System.out.print("c: change number, ");
        System.out.print("a: add entry, ");
        System.out.print("q: quit ");
    }

    public static void lookup(String[] firstnames, String[] lastnames, String[] PhoneNumber, Scanner scanner, int count) {
        System.out.println("First name: ");
        String firstname = scanner.next();
        System.out.println("Last name: ");
        String lastname = scanner.next();

        int index = index(firstnames, lastnames, firstname, lastname, count);

        if (index < 0) {
            System.out.println("Name not found");
        } else {
            System.out.printf("%s %s's phone number is %s%n", firstname, lastname, PhoneNumber[index]);
        }
    }

    public static void reverseLookup(String[]firstnames,String[]lastnames,String[]phoneNumbers,int count, Scanner scanner){
        System.out.println("Phone Number: ");
        String phoneNumber = scanner.next();

        int index = -1;
        for(int i = 0; i < count; i++){
            if(phoneNumbers[i].equals(phoneNumber)){
                index = i;
                break;
            }
        }

        if(index < 0){
            System.out.println("Phone number not found");
        }else{
            System.out.println(phoneNumber + " is " + firstnames[index] + " " + lastnames[index] + "'s phone number");
        }
    }

    public static void changeNumber(String[] firstnames, String[] lastnames, String[] PhoneNumber, Scanner scanner, int count) {
        System.out.println("First name: ");
        String firstname = scanner.next();
        System.out.println("Last name: ");
        String lastname = scanner.next();

        int index = index(firstnames, lastnames, firstname,lastname,count);

        if (index == -1) {
            System.out.println("Name not found");
        } else {
            System.out.println("New phone number: ");
            String newnum = scanner.next();
            PhoneNumber[index] = newnum;
            System.out.println("Phone number updated");
        }
    }


    public static boolean addNumber(String[] firstnames, String[] lastnames, int count, Scanner scanner, int max,String[] PhoneNumber) {
        if (count >= max) {
            System.out.println("Database is full");
            return false;
        }
        System.out.println("First name: ");
        String firstname = scanner.next();
        System.out.println("last name: ");
        String lastname = scanner.next();

        int index = index(firstnames, lastnames, firstname, lastname, count);

        if (index >= 0) {
            System.out.println("That name already exists");
            return false;
        }

        System.out.println("Phone number: ");
        String phonenumber = scanner.next();

        firstnames[count] = firstname;
        lastnames[count] = lastname;
        PhoneNumber[count] = phonenumber;

        System.out.println("Entry added");
        return true;

    }


    public static void Printphonenumbers(String[] firstnames, String[] lastnames, String[] PhoneNumber, int count) {
        for (int i = 0; i < count; i++) {
            System.out.printf("%5s  $%,12d%n", firstnames[i], lastnames[i],PhoneNumber[i]);
        }
    }

    public static void Printphonenumberstofile(String filename, String[] firstnames, String[] lastnames, String[] PhoneNumber, int count) throws Exception {
        PrintWriter output = new PrintWriter(filename);
        for (int i = 0; i < count; i++) {
            output.println(firstnames[i] + " " + lastnames[i] + " " + PhoneNumber[i]);
        }
        output.close();
    }
}
