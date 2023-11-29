import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.ArrayList;

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
            if(sc.hasNextInt())phoneNumber=sc.nextLine();
            else{
                ClearScreen();
                System.out.println("Error Input PhoneNumber");
                sc.nextLine();
                sc.nextLine();
                check = 0;
                ClearScreen();
                continue;
            }
            if(phoneNumber.length() != 10){
                System.out.println("Error Not Enough 10 Number");
                ClearScreen();
                check = 0;
            }

        }while(check == 0);
    }

    public void Input(){

            Scanner sc= new Scanner(System.in);
            InputID();
            ClearScreen();
            System.out.print("Enter full name: ");
            name=sc.nextLine();
            ClearScreen();
//        Khúc này là nhập ngày tháng năm sinh
            InputDateOfBirth();
            ClearScreen();
            InputGender();
            ClearScreen();
            System.out.print("Enter address: ");
            address=sc.nextLine();
            ClearScreen();
            System.out.print("Enter email: ");
            email=sc.nextLine();
            ClearScreen();
            InputPhoneNumber();
            ClearScreen();
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

    private void InputID(){
        Scanner sc = new Scanner(System.in);
        int isChoose;
        int[] listID = GetListID();
        do{  
            isChoose = 1;
            System.out.print("Enter ID: ");
            if(sc.hasNextInt()) id = sc.nextInt();
            else{
                ClearScreen();
                System.out.println("Error Input ID");
                sc.nextLine();
                sc.nextLine();
                isChoose = 0;
                ClearScreen();
                continue;
            }
            ClearScreen();
            for(int i = 1; i <= listID[0]; i++){
                if(id == listID[i]) {
                    System.out.println("ID is Available, Rertry");
                    sc.nextLine();
                    sc.nextLine();
                    ClearScreen();
                    isChoose = 0;
                }
            }
        }while(isChoose == 0);
        

    }

    public int[] GetListID(){
        int[] listID = new int[10000];
        int count = 0;
        ListStaff ls = new ListStaff();
        ListCustomer lc = new ListCustomer();
        ArrayList<Staff> staffs = new ArrayList<>();
        ArrayList<Customer> customers = new ArrayList<>();
        staffs = ls.GetListCleaningStaff();
        for(Staff staff : staffs){
            listID[++count] = staff.getId();
        }
        staffs = ls.GetListReceptionist();
        for(Staff staff : staffs){
            listID[++count] = staff.getId();
        }
        staffs = ls.GetListSecurity();
        for(Staff staff : staffs){
            listID[++count] = staff.getId();
        }
        customers = lc.GetListCustomer();
        for(Customer customer : customers){
            listID[++count] = customer.getId();
        }
        listID[0] = count;
        return listID;
    }
}


