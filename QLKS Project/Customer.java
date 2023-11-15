import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Customer extends Person{
    public Customer()
    {
        super();
    }
    public Customer(int id, String name, Date doB, String gender, String address, String email, String phoneNumber)
    {
        super(id, name, doB, gender, address, email, phoneNumber);
    }
    public void Input(){
        super.Input();
    }

    public void Output(){
        super.Output();
    }
    public void EditCustomer(){
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
            System.out.println("8. Show All Customer's Information after Edit");
            System.out.print("9. Finish");
            System.out.println();
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            switch (choice) {
                case 1:
                    sc.nextLine();
                    setName(sc.nextLine());
                    super.InputDateOfBirth();
                    setGender(sc.nextLine());
                    setAddress(sc.nextLine());
                    setEmail(sc.nextLine());
                    setPhoneNumber(sc.nextLine());
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
                    Output();
                    sc.nextLine();
                    sc.nextLine();
                    break;
                case 9:
                    System.out.println("Edit Finished!");
                    break;
                default:
                    System.out.println("Error!!!");
            }
        } while (choice != 9);
    }
}
