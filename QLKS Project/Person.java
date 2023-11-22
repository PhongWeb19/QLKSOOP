import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Person {
    private int id;
    private String name;
    private Date doB;
    private String gender;
    private String address;
    private String email;
    private String phoneNumber;

    public Person() {
    }

    public Person(int id, String name, Date doB, String gender, String address, String email, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.doB = doB;
        this.gender = gender;
        this.address = address;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDoB() {
        return doB;
    }

    public String getDoBstr(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return dateFormat.format(getDoB());
    }

    public void setDoB(Date doB) {
        this.doB = doB;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public void InputDateOfBirth() {
        Scanner sc = new Scanner(System.in);
        int day, month, year;
        int check;
        do{
            check = 1;
            System.out.println("Enter Date Of Birth: ");
            System.out.print("Enter day: ");
            day = sc.nextInt();
            System.out.print("Enter month: ");

            month = sc.nextInt();

            System.out.print("Enter year: ");
            year = sc.nextInt();

            if (month > 12 || month <= 0) {
                System.out.println("Month Invalid!!");
                check = 0;
            }

            if (year < 1900 || year > 2024) {
                System.out.println("Year Invalid");
                check = 0;
            }

            switch (month) {
                case 1, 3, 5, 7, 8, 10, 12:
                    if (day > 31 || day <= 0) {
                        System.out.println("Day Invalid!!!");
                        check = 0;
                    }
                    break;
                case 4, 6, 9, 11:
                    if (day > 30 || day <= 0) {
                        System.out.println("Day Invalid!!!");
                        check = 0;
                    }
                    break;
                case 2:
                    if (day > 29 || day <= 0) {
                        System.out.println("Day Invalid!!!");
                        check = 0;
                    }
                    break;
            }
        }while(check == 0);

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String dateStr = String.format("%02d/%02d/%04d", day, month, year);
        try {
            setDoB(dateFormat.parse(dateStr));
        } catch (Exception e) {
            System.out.println("Not True Of Simple Date Format");
            return;
        }
    }

    public void InputGender(){
        Scanner sc = new Scanner(System.in);
        int check;
        do{
            check = 1;
            System.out.print("Enter gender: ");
            gender=sc.nextLine();
            gender=gender.toLowerCase();
            if(!gender.equals("nam") && !gender.equals("nu")){
                System.out.println("Error Gender!!!");
                check = 0;
            }

        }while(check == 0);
    }

    public void InputPhoneNumber(){
        Scanner sc = new Scanner(System.in);
        int check;
        do{
            check = 1;
            System.out.print("Enter Phone Number : ");
            phoneNumber=sc.nextLine();
            if(phoneNumber.length() != 10){
                System.out.println("Error Not Enough 10 Number");
                check = 0;
            }

        }while(check == 0);
    }

    public void Input(){

            Scanner sc= new Scanner(System.in);
            System.out.print("Enter ID: ");
            id=sc.nextInt();
            sc.nextLine();
            System.out.print("Enter full name: ");
            name=sc.nextLine();
//        Khúc này là nhập ngày tháng năm sinh
            InputDateOfBirth();
            InputGender();
            System.out.print("Enter address: ");
            address=sc.nextLine();
            System.out.print("Enter email: ");
            email=sc.nextLine();
            InputPhoneNumber();

    }
    public void Output(){
        Scanner sc = new Scanner(System.in);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String dateStr = dateFormat.format(getDoB());
        System.out.printf("%-5s %-10s %-15s %-10s %-20s %-15s %-15s",getId(),getName(),dateStr,getGender(),getAddress(),getEmail(),getPhoneNumber());
    }
    private void ClearScreen(){
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void main(String[] args){
        Person p = new Person();
        p.Input();
        p.Output();
    }


}


