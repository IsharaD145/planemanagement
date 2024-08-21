import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Ticket {
    private char row;
    private int seat;
    private int price;
    private Person person;

    public void setRow(char row) {
        this.row = row;
    }

    public void setSeat(int seat) {
        this.seat = seat;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public int getPrice(){
        return price;
    }

    public char getRow() {
        return row;
    }

    public int getSeat() {
        return seat;
    }

    public Person getPerson() {
        return person;
    }

    public Ticket(char row, int seat, int price, Person person){
        this.row = row;
        this.seat = seat;
        this.price = price;
        this.person = person;
    }


    //printing ticket info
    public void printTicketInfo(){
        System.out.println("The row is "+this.row);
        System.out.println("The seat is "+this.seat);
        System.out.println("The price of the seat is : £"+this.price);
        System.out.println("********************************************");
        System.out.println("Details of the person");
        this.person.printPersonInfo();
        System.out.println();
    }


    //writing data to the file
    public void save() throws IOException {   //https://www.baeldung.com/java-write-to-file
        String fileName = this.row + String.valueOf(seat) + ".txt";
        FileWriter seatfile = new FileWriter(fileName);
        try {
            seatfile.write("The row is: " + row + "\n");
            seatfile.write("The seat is: " + seat + "\n");
            seatfile.write("The price is: £" + price + "\n\n");

            seatfile.write("Name: " + person.getName() + "\n");
            seatfile.write("Surname: " + person.getSurname() + "\n");
            seatfile.write("Email: " + person.getEmail() + "\n");
            System.out.println("File "+this.row+String.valueOf(seat)+".txt created successfully");
        }
        catch (IOException e) {
            System.out.println("Error occurred while inserting data");
        }finally {
            seatfile.close();
        }
    }

    //deleting the file
    public void deletefile(){
        String fileName = this.row + String.valueOf(this.seat) + ".txt";
        File file = new File(fileName);
        if (file.exists()) {
           file.delete();
           System.out.println("File " + fileName + " deleted successfully");

        } else {
            System.out.println("File " + fileName + " does not exist");
        }
    }

}
