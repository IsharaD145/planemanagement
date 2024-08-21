import java.io.File;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;//import java.util.*;
public class W2053089_PlaneManagement {
private static int[][] seats;
private static Ticket[][] tickets =  new Ticket[4][14];
private static Scanner input = new Scanner(System.in);

public static void main(String[] args) throws IOException {
    //array declaration
    seats = new int[4][];
    seats[0] = new int[14];
    seats[1] = new int[12];
    seats[2] = new int[12];
    seats[3] = new int[14];
    while (true) {

        //main menue
        System.out.println("   Welcome to the Plane Management application");
        System.out.println("**************************************************");
        System.out.println("*                 MENU OPTIONS                   *");
        System.out.println("**************************************************");
        System.out.println("      1) Buy a seat");
        System.out.println("      2) Cancel a seat");
        System.out.println("      3) Find first available seat");
        System.out.println("      4) Show seating plan");
        System.out.println("      5) Print tickets information and total sales");
        System.out.println("      6) Search ticket");
        System.out.println("      0) Quit");
        System.out.println("***************************************************");
        char choisse;
        while (true) {
            try{
                System.out.print("Please select an option: ");
                String choise = input.next();
                input.nextLine();
                if (choise.length() == 1) {
                    choisse = choise.charAt(0);
                    switch (choisse) {
                        case '1':
                            buy_seat();
                            break;

                        case '2':
                            cancel_seat();
                            break;

                        case '3':
                            find_first_available();
                            break;

                        case '4':
                            show_seating_plan();
                            break;

                        case '5':
                            print_tickets_info();
                            break;

                        case '6':
                            search_ticket();
                            break;

                        case '0':
                            System.exit(0);
                        default:
                            System.out.println("Invalid choice please select proper choice: ");
                    }
                    break;
                } else {
                    System.out.println("Invalid choice");
                }
            }catch (InputMismatchException e){
                System.out.println("Enter Proper data Type");
            }catch (Exception e){
                System.out.println("Error occured");
            }

        }

    }

}

//method for printing ticket info
private static void print_tickets_info() {
    int totalprice = 0;
    for (int row=0;row<tickets.length;row++){
        for(int seat=0;seat<tickets[row].length;seat++){
            if(tickets[row][seat] != null){
                Ticket ticket =tickets[row][seat];
                ticket.printTicketInfo();
                totalprice += ticket.getPrice();
            }
        }
    }
    System.out.println("The total amount of the tickets sold are : £"+totalprice );
    System.out.println();
}

//method to cancel seat
private static void cancel_seat() {
    System.out.println("  Seat Cancellation");
    System.out.println("************************");
    String[] inputValues = inputValidation();
    String inRow = inputValues[0];
    char seatRow = inRow.charAt(0);
    int seatNo = Integer.parseInt(inputValues[1]);
    if (seatNo >= 1 && seatNo <= seats[seatRow - 'A'].length) {
        if (seats[seatRow - 'A'][seatNo-1] == 1) {
            seats[seatRow - 'A'][seatNo-1] = 0;
            Ticket ticket = tickets[seatRow-'A'][seatNo-1];
            ticket.deletefile();
            tickets[seatRow-'A'][seatNo-1]=null;
            System.out.println("Seat cancelled successfully");

            System.out.println();
        } else {
            System.out.println("Seat you entered is not booked");
            System.out.println();
        }
    }
}


//method to show seating plan
public static void show_seating_plan() {
    System.out.println("    seating plan ");
    System.out.println("*********************");
    for (int row = 0; row < seats.length; row++) {
        for (int s = 0; s < seats[row].length; s++) {
            if (seats[row][s] == 1) {
                System.out.print("X");
            } else {
                System.out.print("O");
            }
        }
        System.out.println();
    }
    System.out.println();
}

//buy seat method
public static void buy_seat() throws IOException {
    String name,surname,email;
    System.out.println("       Buy seat");
    System.out.println("************************");
    String[] inputValues = inputValidation();
    String inRow = inputValues[0];
    char seatRow = inRow.charAt(0);
    int seatNo = Integer.parseInt(inputValues[1]);
    if (seats[seatRow - 'A'][seatNo - 1] != 0) {
        System.out.println("Seat is already booked");
    } else {
        seats[seatRow - 'A'][seatNo - 1] = 1;
        int price = priceCheker(seatNo);
        String reg = "[a-zA-Z]+$";
        String emailreg = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+[a-zA-Z]$";  // https://www.tutorialspoint.com/checking-for-valid-email-address-using-regular-expressions-in-java
        while(true){
            System.out.print("Enter your First name : ");
            name = input.next();
            if(name.matches(reg)){
                break;
            }else{
                System.out.println("Enter proper data type");
            }
        }
        while(true){
            System.out.print("Enter your surname : ");
            surname = input.next();
            if(surname.matches(reg)){
                break;
            }else{
                System.out.println("Enter proper data type");
            }
        }
            while(true){
        System.out.print("Enter your email : ");
        email = input.next();
                if(email.matches(emailreg)){
                    break;
                }else{
                    System.out.println("Enter Email in the proper format");
                }
            }
            //creating objects and updating ticket class
        Person record = new Person(name, surname, email);
        Ticket ticket = new Ticket(seatRow,seatNo,price, record);
        tickets[seatRow-'A'][seatNo-1]= ticket;
        System.out.println("Seat booked successfully!!!!!");
        ticket.save();
        System.out.println();
        System.out.println("Ticket Information");
        ticket.printTicketInfo();

    }

}

//method to find the first available seat starting from the first row A
public static void find_first_available() {
    String rowDisplay = null;
    for (int seatRow = 0; seatRow < seats.length; seatRow++) {
        for (int seatNo = 0; seatNo < seats[seatRow].length; seatNo++) {
            if (seats[seatRow][seatNo] == 0) {
                if (seatRow == 0) {
                    rowDisplay = "A";
                } else if (seatRow == 1) {
                    rowDisplay = "B";
                } else if (seatRow == 2) {
                    rowDisplay = "C";
                } else if (seatRow == 3) {
                    rowDisplay = "D";
                }
                System.out.println("Seat " + rowDisplay + (seatNo+1) + " is available");
                System.out.println(1);
                return;
            }
        }
    }

}

//method used to search tickets
public static void search_ticket(){
    System.out.println("     Search Ticket ");
    System.out.println("**********************");
    String[] inputValues = inputValidation();
    String inRow = inputValues[0];
    char seatRow = inRow.charAt(0);
    int seatNo = Integer.parseInt(inputValues[1]);
    if (seats[seatRow - 'A'][seatNo-1] == 1) {
        System.out.println("This Seat is booked");
        Ticket ticket = tickets[seatRow - 'A'][seatNo - 1];
        ticket.printTicketInfo();;
        return;
    } else {
        System.out.println("The seat is available");
    }
}

//method used to do the input validations
public static String[] inputValidation(){
    String[] inputValues = new String[2];
    while (true) {
        System.out.print("Enter Seat Row (A/B/C/D) : ");
        String inRow = input.next();
        input.nextLine();
        if (inRow.length() == 1) {
            char seatRow = Character.toUpperCase(inRow.charAt(0));
            if (seatRow >= 'A' && seatRow <= 'D' && Character.isLetter(seatRow)) {
                while (true) {
                    try {
                        System.out.print("Enter seat number : ");
                        int seatNo = input.nextInt();
                        if (seatNo >= 1 && seatNo <= seats[seatRow - 'A'].length) { // https://www.w3schools.in/java/examples/find-ascii-value-of-a-character
                            inputValues[0] = String.valueOf(seatRow);
                            inputValues [1] = String.valueOf(seatNo);
                            return inputValues;
                        } else {
                            System.out.println("Invalid seat number");
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("Enter proper data type");
                        input.nextLine();
                    }catch (Exception e){
                        System.out.println("Error occurd");
                    }
                }
            } else {
                System.out.println("Invalid seat row");
            }
        } else {
            System.out.println("Enter valid seat row");
        }
    }
}

//method which returns the price
public static int priceCheker(int seatNo){
    int price;
    if (seatNo<=5){
        price = 200;
    }
    else if((seatNo>5) && (seatNo<=9)){
        price = 150;
    }
    else {
        price = 180;
    }
    return price;
}

}
