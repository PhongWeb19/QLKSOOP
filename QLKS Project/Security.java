import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Security extends Staff {
    private int numberDayOfWork;
    public Security(){
        super();
        numberDayOfWork=0;
    }

    public Security(int id, String name, Date doB, String gender, String address, String email, String phoneNumber, int numberDayOfWork) {
        super(id, name, doB, gender, address, email, phoneNumber);
        this.numberDayOfWork = numberDayOfWork;
    }

    public int getNumberDayOfWork() {
        return numberDayOfWork;
    }

    public void setNumberDayOfWork(int numberDayOfWork) {
        this.numberDayOfWork = numberDayOfWork;
    }

    private void ClearScreen(){
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public void Input(){
        Scanner sc= new Scanner(System.in);
        super.Input();
        System.out.print("Enter number day of work: ");
        numberDayOfWork=sc.nextInt();
    }
    public void Output(){
        Scanner sc = new Scanner(System.in);
        super.Output();
        System.out.printf("%-5s %-10s %-20s\n","", getNumberDayOfWork(),PayRoll());

    }
    @Override public double PayRoll(){
        return numberDayOfWork*200000;
    }
    @Override public void EditStaff(){
        Scanner sc=new Scanner(System.in);
        int choice;
        do {
            ClearScreen();
            Output();
            System.out.println();
            System.out.println("What do you want to do? ");
            System.out.println("1. Edit All Information");
            System.out.println("2. Edit Name");
            System.out.println("3. Edit Date Of Birth");
            System.out.println("4. Edit Gender");
            System.out.println("5. Edit Address");
            System.out.println("6. Edit Email");
            System.out.println("7. Edit phone number");
            System.out.println("8. Edit number day of work");
            System.out.println("9. Finish");
            System.out.println();
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            ClearScreen();
            switch (choice) {
                case 1:
                    System.out.print("Enter new Name: ");
                    setName(sc.nextLine());
                    super.InputDateOfBirth();
                    System.out.print("Enter new Gender: ");
                    InputGender();
                    System.out.print("Enter new Address: ");
                    setAddress(sc.nextLine());
                    System.out.print("Enter new Email: ");
                    setEmail(sc.nextLine());
                    System.out.print("Enter new phone number: ");
                    InputPhoneNumber();
                    System.out.print("Enter new Day of Work: ");
                    setNumberDayOfWork(sc.nextInt());

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
                    InputGender();
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
                    InputPhoneNumber();
                    break;
                case 8:
                    System.out.print("Enter new Day of Work: ");
                    setNumberDayOfWork(sc.nextInt());
                    break;
                case 9:
                    System.out.println("Edit Finished!");
                    break;
                default:
                    System.out.println("Error!!!");
            }
        } while (choice != 10);
    }
    public static void main(String[] args){
        Security s = new Security();
        s.Input();
        s.Output();
        System.out.println("Luong= "+s.PayRoll());
        s.EditStaff();
    }
}
