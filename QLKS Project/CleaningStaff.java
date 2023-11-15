import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class CleaningStaff extends Staff{
    private int numberOfRoom;
    public CleaningStaff(){
        super();
        numberOfRoom=0;
    }

    public CleaningStaff(int id, String name, Date doB, String gender, String address, String email, String phoneNumber, int numberOfRoom) {
        super(id, name, doB, gender, address, email, phoneNumber);
        this.numberOfRoom = numberOfRoom;
    }

    public int getNumberOfRoom() {
        return numberOfRoom;
    }

    public void setNumberOfRoom(int numberOfRoom) {
        this.numberOfRoom = numberOfRoom;
    }
    public void Input(){
        Scanner sc=new Scanner(System.in);
        super.Input();
        System.out.print("Enter number of room: ");
        numberOfRoom=sc.nextInt();
    }
    public void Output(){
        Scanner sc = new Scanner(System.in);
        super.Output();
        System.out.println(getNumberOfRoom() + "\t" + PayRoll() + " VND");
    }
    @Override public double PayRoll(){
        return numberOfRoom*30000;
    }
    @Override public void EditStaff(){
        Scanner sc=new Scanner(System.in);
        int choice;
        do {
            System.out.println("What do you want to do? ");
            System.out.println("1. Edit All Information");
            System.out.println("2. Edit Name");
            System.out.println("3. Edit Date Of Birth");
            System.out.println("4. Edit Gender");
            System.out.println("5. Edit Address");
            System.out.println("6. Edit Email");
            System.out.println("7. Edit phone number");
            System.out.println("8. Edit number of room");
            System.out.println("9. Show All Staff's Information after Edit");
            System.out.print("10. Finish");
            System.out.println();
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            switch (choice) {
                case 1:
                    sc.nextLine();
                    sc.nextLine();
                    setName(sc.nextLine());
                    super.InputDateOfBirth();
                    setGender(sc.nextLine());
                    setAddress(sc.nextLine());
                    setEmail(sc.nextLine());
                    setPhoneNumber(sc.nextLine());
                    setNumberOfRoom(sc.nextInt());
                    break;
                case 2:
                    sc.nextLine();
                    System.out.print("Enter new Name: ");
                    setName(sc.nextLine());
                    break;
                case 3:
                    sc.nextLine();
                    System.out.print("Enter new Date Of Birth: ");
                    super.InputDateOfBirth();
                    break;
                case 4:
                    sc.nextLine();
                    System.out.print("Enter new Gender: ");
                    setGender(sc.nextLine());
                    break;
                case 5:
                    sc.nextLine();
                    System.out.print("Enter new Address: ");
                    setAddress(sc.nextLine());
                    break;
                case 6:
                    sc.nextLine();
                    System.out.print("Enter new Email: ");
                    setEmail(sc.nextLine());
                    break;
                case 7:
                    sc.nextLine();
                    System.out.print("Enter new phone number: ");
                    setPhoneNumber(sc.nextLine());
                    break;
                case 8:
                    System.out.print("Enter new number of room: ");
                    setNumberOfRoom(sc.nextInt());
                    break;
                case 9:
                    Output();
                    sc.nextLine();
                    break;
                case 10:
                    System.out.println("Edit Finished!");
                    break;
                default:
                    System.out.println("Error!!!");
            }
        } while (choice != 10);
    }

    public static void main(String[] args){
        CleaningStaff c= new CleaningStaff();
        c.Input();
        c.Output();
        System.out.println("Luong= "+c.PayRoll());
        c.EditStaff();
    }

}
