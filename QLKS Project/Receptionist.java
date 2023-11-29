import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Receptionist extends Staff{
    private double baseSalary;
    private double coefficientsSalary;
    public Receptionist(){
        super();
        baseSalary=0f;
        coefficientsSalary=0f;
    }

    public Receptionist(int id, String name, Date doB, String gender, String address, String email,String phoneNumber, double baseSalary, double coefficientsSalary) {
        super(id, name, doB, gender, address, email,phoneNumber);
        this.baseSalary = baseSalary;
        this.coefficientsSalary = coefficientsSalary;
    }
    public double getBaseSalary() {
        return baseSalary;
    }

    public void setBaseSalary(double baseSalary) {
        this.baseSalary = baseSalary;
    }

    public double getCoefficientsSalary() {
        return coefficientsSalary;
    }

    public void setCoefficientsSalary(double coefficientsSalary) {
        this.coefficientsSalary = coefficientsSalary;
    }

    private void ClearScreen(){
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public void Input(){
        Scanner sc=new Scanner(System.in);
        super.Input();
        System.out.print("Enter basic salary: ");
        baseSalary=sc.nextDouble();
        ClearScreen();
        System.out.print("Enter coefficients salary: ");
        coefficientsSalary=sc.nextDouble();
    }
    public void Output(){
        Scanner sc = new Scanner(System.in);
        super.Output();
        System.out.printf("%-2s %-15s %-5s %-17s %-20s\n","",getBaseSalary(),"",getCoefficientsSalary(),PayRoll());
    }
    @Override public double PayRoll(){
        return coefficientsSalary*baseSalary;
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
            System.out.println("8. Edit basic salary");
            System.out.println("9. Edit Coefficients salary");
            System.out.print("10. Finish");
            System.out.println();
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            ClearScreen();
            switch (choice) {
                case 1:
                    sc.nextLine();
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
                    System.out.print("Enter new basic salary: ");
                    setBaseSalary(sc.nextDouble());
                    System.out.print("Enter new coefficients salary: ");
                    setCoefficientsSalary(sc.nextDouble());
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
                    System.out.print("Enter new basic salary: ");
                    setBaseSalary(sc.nextDouble());
                    break;
                case 9:
                    System.out.print("Enter new coefficients salary: ");
                    setCoefficientsSalary(sc.nextDouble());
                    break;
                case 10:
                    System.out.println("Edit Finished!");
                    break;
                default:
                    System.out.println("Error!!!");
            }
        } while (choice != 11);
    }

    public static void main(String[] args){
        Receptionist r= new Receptionist();
        r.Input();
        r.Output();
        System.out.println("LUONG: "+r.PayRoll()+"VND");
        r.EditStaff();
    }
}
