import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Array;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Date;

public class ListCustomer {
    private int Customers = 0;
    private int choice = 0;

    private void ClearScreen(){
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
    // //Lay danh sach khach hang tu file
    public ArrayList<Customer> GetListCustomer()
    {
        ArrayList<Customer> customers = new ArrayList<>();
        File file = new File("Customers.txt");
        try{
            Scanner sc = new Scanner(file);
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            while(sc.hasNextLine())
            {
                Customers++;
                customers.add(new Customer(Integer.parseInt(sc.nextLine()),sc.nextLine(),dateFormat.parse(sc.nextLine()),sc.nextLine(),sc.nextLine(),sc.nextLine(),sc.nextLine()));
            }
        }
        catch(FileNotFoundException e){
            System.out.println("Lỗi" + e);
        }catch(ParseException e){
        System.out.println("Lỗi: Error Date Form");
        }
        return customers;
    }
    //Hien thi danh sach khach hàm có tham số
    public void ShowListCustomer(ArrayList<Customer> customers)
    {
        for(Customer customer : customers)
        {
            customer.Output();
            System.out.println();
        }
    }
//    Hiển thị danh sách hàm không tham số, cái naỳ sẽ gọi lại trong CustomerMenu
    public void ShowAllListCustomer(){
        ArrayList<Customer> customers = GetListCustomer();
        System.out.println("---- List Customer----");
        System.out.printf("%-5s %-10s %-15s %-10s %-20s %-15s %-15s\n", "ID", "Name", "Date of Birth", "Gender","Address", "Email", "Phone Number");
        ShowListCustomer(customers);
        System.out.println();
    }
//    Ghi vào File
    public void WriteToCustomer(ArrayList<Customer> customers, boolean isWrite){
        if(customers.isEmpty()){
            DeleteEmptyCustomer();
        }
        for(Customer customer : customers){
                try {
                    FileWriter fileWriter = new FileWriter("Customers.txt", isWrite);
                    if(!isWrite) isWrite = true;
                    fileWriter.write(customer.getId() + "\n");
                    fileWriter.write(customer.getName() + "\n");
                    fileWriter.write(customer.getDoBstr() + "\n");
                    fileWriter.write(customer.getGender() + "\n");
                    fileWriter.write(customer.getAddress() + "\n");
                    fileWriter.write(customer.getEmail() + "\n");
                    fileWriter.write(customer.getPhoneNumber() + "\n");
                    fileWriter.close();
                }catch (IOException e){
                    System.out.println("Error " + e.getMessage());
                }
            }
    }

    private void DeleteEmptyCustomer(){
        try{
                FileWriter fileWriter = new FileWriter("Customers.txt", false);
                fileWriter.close();
                
            }catch (IOException e){
                System.out.println("Error " + e.getMessage());
            }
    }
// Thêm khách hàng
    public void AddCustomer()
    {
        ClearScreen();
        Customer customer = new Customer();
        customer.Input();
        try{
            FileWriter fileWriter = new FileWriter("Customers.txt", true);
            fileWriter.write(customer.getId() + "\n");
            fileWriter.write(customer.getName() + "\n");
            fileWriter.write(customer.getDoBstr() + "\n");
            fileWriter.write(customer.getAddress() + "\n");
            fileWriter.write(customer.getGender() + "\n");
            fileWriter.write(customer.getEmail() + "\n");
            fileWriter.write(customer.getPhoneNumber() + "\n");
            System.out.println();
            fileWriter.close();
        }
        catch (IOException e) {
            System.out.println("Error" + e.getMessage());
        }
    }
//    Tim kiem Khach hàng
    public Customer FindCustomer(ArrayList<Customer> customers){
        Scanner sc= new Scanner(System.in);
        System.out.println("+-----------------------+");
        System.out.println("| 1 : ID                |");
        System.out.println("| 2 : Name              |");
        System.out.println("| 3 : Phone Number      |");
        System.out.println("+-----------------------+");
        System.out.print("Enter choice: ");
        int choice = sc.nextInt();
        ClearScreen();
        ShowAllListCustomer();
        System.out.println();
        switch(choice){
            case 1:
                System.out.print("Enter ID : ");
                int idNeedFind = sc.nextInt();
                for(Customer customer : customers){
                    if(idNeedFind == customer.getId()) {
                        return customer;
                    }
                }
                System.out.println("ID is not Found");
                sc.nextLine();
                sc.nextLine();
                break;
            case 2:
                sc.nextLine();
                System.out.print("Enter Name : ");
                String nameNeedFind = sc.nextLine();
                for(Customer customer: customers){
                    if(customer.getName().equals(nameNeedFind)) {
                        return customer;
                    }
                }
                System.out.println("Name is not Found");
                sc.nextLine();
                break;
            case 3:
                sc.nextLine();
                System.out.print("Enter Phone Number : ");
                String phoneNumBerNeedFind = sc.nextLine();
                for(Customer customer: customers){
                    if(customer.getPhoneNumber().equals(phoneNumBerNeedFind)) {
                        return customer;
                    }
                }
                System.out.println("Phone Number is not Found");
                sc.nextLine();
                break;
            default:
                System.out.println("Error choice!");
        }
        int check;
        int reChoice;
        ClearScreen();
        do{
            check = 1;
            System.out.println("1 : Return");
            System.out.println("2 : Retry");
            System.out.println();
            System.out.println("Please Input Your Choice");
            reChoice = sc.nextInt();
            if(reChoice > 2 || reChoice <= 0) {
                check = 0;
                System.out.println("The Choice is not Found");
            }
            ClearScreen();
        }while(check == 0);
        if(reChoice == 2) return FindCustomer(customers);
        return null;
    }
//    Sau khi tim kím thì xuất ra thông tin khách hàng
    public void FindAndShowCustomers(){
    Scanner sc = new Scanner(System.in);
    System.out.println("---- Find ----");
    ArrayList<Customer> customers = GetListCustomer();
    ClearScreen();
    ShowListCustomer(customers);
    System.out.println();
    System.out.println("----Find By----");
    Customer customer=FindCustomer(customers);
    ClearScreen();
    if(customer == null) return;
    customer.Output();
    sc.nextLine();
}
    //Xóa khách hàng
    public void DeleteCustomer()
    {
        ArrayList<Customer> customers = new ArrayList<>();
        System.out.println("----Remove List----- ");
        customers = GetListCustomer();
        ClearScreen();
        ShowListCustomer(customers);
        System.out.println();
        System.out.println("----Remove By----- ");
        System.out.println();
        Customer customer = FindCustomer(customers);
        customers.remove(customer);
        WriteToCustomer(customers, false);
    }
// Chình sửa thông tin khách hàng
    public void EditCustomers()
    {
        ArrayList<Customer> customers = new ArrayList<>();
        System.out.println("----Edit----- ");
        System.out.println();
        customers = GetListCustomer();
        ShowListCustomer(customers);
        ClearScreen();
        System.out.println("----Edit By----- ");
        Customer customer =FindCustomer(customers);

        customer.EditCustomer();

        WriteToCustomer(customers,false);
    }
//    Menu customer
    public void CustomerMenu(){
        Scanner sc = new Scanner(System.in);
        do{
            ClearScreen();
            ShowAllListCustomer();
            System.out.println();
            System.out.println("+-----------------------+");
            System.out.println("| 1 : Add Customer      |");
            System.out.println("| 2 : Delete Customer   |");
            System.out.println("| 3 : Edit Customer     |");
            System.out.println("| 4 : Find Customer     |");
            System.out.println("| 5 : Exit              |");
            System.out.println("+-----------------------+");
            System.out.println();
            System.out.print("Please Input Your Order : ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    AddCustomer();
                    break;
                case 2:
                    DeleteCustomer();
                    break;
                case 3:
                    EditCustomers();
                    break;
                case 4:
                    FindAndShowCustomers();
                    break;
                default:
                    break;
            }

            if(choice > 5 || choice <= 0){
                ClearScreen();
                System.out.println("The Choice is not Found");
                sc.nextLine();
                sc.nextLine();
            }
        }while(choice != 5);
    }

    public static void main(String[] args){
        ListCustomer lc = new ListCustomer();
        lc.CustomerMenu();
    }
}
            