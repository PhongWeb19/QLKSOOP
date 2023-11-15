import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
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

    public void AddCustomer()
    {
        ClearScreen();
        Customer member = new Customer();
        member.Input();
        try{
            FileWriter fileWriter = new FileWriter("Customers.txt", true);
            fileWriter.write(member.getId() + "\n");
            fileWriter.write(member.getName() + "\n");
            fileWriter.write(member.getDoBstr() + "\n");
            fileWriter.write(member.getAddress() + "\n");
            fileWriter.write(member.getGender() + "\n");            
            fileWriter.write(member.getEmail() + "\n");
            fileWriter.write(member.getPhoneNumber() + "\n");
            System.out.println();
            fileWriter.close();
        }
        catch (IOException e) {
            System.out.println("Error" + e.getMessage());
        }
    }
    //Xu li Date
    public static Date Xuli(String NNN)
    {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        try{
            return dateFormat.parse(NNN);
        }
        catch(ParseException e)
        {
            e.printStackTrace();
            return null;
        }
    }

    // //Lay danh sach khach hang tu file
    private ArrayList<Customer> GetListCustomer()
    {
        ArrayList<Customer> ListCustomer = new ArrayList<>();
        File file = new File("Customers.txt");
        try{
            Scanner sc = new Scanner(file);
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            while(sc.hasNextLine())
            {
                Customers++;
                ListCustomer.add(new Customer(Integer.parseInt(sc.nextLine()),sc.nextLine(),Xuli(sc.nextLine()),sc.nextLine(),sc.nextLine(),sc.nextLine(),sc.nextLine())); 
            }
        }
        catch(FileNotFoundException e){
            System.out.println("Lỗi" + e);
        };
        return ListCustomer;
    }
    //Hien thi danh sach khach hang
    public void ShowListCustomer()
    {
        ArrayList<Customer> a = GetListCustomer();
        for(Customer x : a)
        {
           x.Output();
        }
    }

    //Xóa khách hàng
    public void DeleteCustomer()
    {
        ClearScreen();
        ShowListCustomer();
        System.out.println();
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhap id khach hang can xoa: ");
        int id = sc.nextInt();
        ArrayList<Customer> Customers = GetListCustomer();
        
        try {
            FileWriter fileWriter = new FileWriter("Customers.txt", false);
            for (Customer x : Customers) {
                if (x.getId() != id) {
                    fileWriter.write(x.getId() + "\n");
                    fileWriter.write(x.getName() + "\n");
                    fileWriter.write(x.getDoBstr() + "\n");
                    fileWriter.write(x.getAddress() + "\n");
                    fileWriter.write(x.getGender() + "\n");
                    fileWriter.write(x.getEmail() + "\n");
                    fileWriter.write(x.getPhoneNumber() + "\n");
                }
            }
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void EditCustomers()
    {
        ClearScreen();
        ShowListCustomer();
        System.out.println();
        System.out.print("Input The ID : ");
        Scanner sc = new Scanner(System.in);
        int id = sc.nextInt();
        EditCustomer(id);
    }

    //Hàm chỉnh sửa nội dung Khách hàng 
    public void EditCustomer(int id){
            ArrayList<Customer> Customers = GetListCustomer();
            Scanner sc=new Scanner(System.in);
            int choice;
            for(Customer x: Customers)
            {
                if(x.getId() == id)
                {
                    do {
                        ClearScreen();
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
                        ClearScreen();
                        switch (choice) {
                            case 1:
                                sc.nextLine();
                                x.Input();
                                break;
                            case 2:
                                sc.nextLine();
                                System.out.print("Enter new Name: ");
                                x.setName(sc.nextLine());
                                break;
                            case 3:
                                sc.nextLine();
                                System.out.print("Enter new Date Of Birth: ");
                                x.InputDateOfBirth();
                                break;
                            case 4:
                                sc.nextLine();
                                System.out.print("Enter new Gender: ");
                                x.setGender(sc.nextLine());
                                break;
                            case 5:
                                sc.nextLine();
                                System.out.print("Enter new Address: ");
                                x.setAddress(sc.nextLine());
                                break;
                            case 6:
                                sc.nextLine();
                                System.out.print("Enter new Email: ");
                                x.setEmail(sc.nextLine());
                                break;
                            case 7:
                                sc.nextLine();
                                System.out.print("Enter new phone number: ");
                                x.setPhoneNumber(sc.nextLine());
                                break;
                            case 8:
                                x.Output();
                                sc.nextLine();
                                sc.nextLine();
                                break;
                            case 9:
                                System.out.println("Edit Finished!");

                                break;
                            default:
                                System.out.println("Error!!!");
                        }
                            GhiDe(x.getId(),x);
                    } while (choice != 9);
                    
                }
            }
        } 
        
    //Thay đổi nội dung sau khi thay đổi
    public void GhiDe(int id,Customer x)
        {
            ArrayList<Customer> Customers = GetListCustomer();
            for(Customer customer: Customers)
            {
                if(customer.getId()==id)
                {
                    try{
                        FileWriter fileWriter = new FileWriter("Customers.txt", false);
                        fileWriter.write(x.getId() + "\n");
                        fileWriter.write(x.getName() + "\n");
                        fileWriter.write(x.getDoBstr() + "\n");
                        fileWriter.write(x.getAddress() + "\n");
                        fileWriter.write(x.getGender() + "\n");            
                        fileWriter.write(x.getEmail() + "\n");
                        fileWriter.write(x.getPhoneNumber() + "\n");
                        fileWriter.close();
                    }
                    catch (IOException e) {
                        System.out.println("Error" + e.getMessage());
                    }
                }
                else
                {
                    try{
                        FileWriter fileWriter = new FileWriter("Customers.txt", true);
                        fileWriter.write(customer.getId() + "\n");
                        fileWriter.write(customer.getName() + "\n");
                        fileWriter.write(customer.getDoBstr() + "\n");
                        fileWriter.write(customer.getAddress() + "\n");
                        fileWriter.write(customer.getGender() + "\n");            
                        fileWriter.write(customer.getEmail() + "\n");
                        fileWriter.write(customer.getPhoneNumber() + "\n");
                        fileWriter.close();
                    }
                    catch (IOException e) {
                        System.out.println("Error" + e.getMessage());
                    }
                }
            }
        }

    //Tìm kiếm khách hàng
    public void FindCustomers()
    {
        ClearScreen();
        Scanner sc = new Scanner(System.in);
        ShowListCustomer();
        int choose;
        do{
            System.out.println("\n"+"1: Search ID");
            System.out.println("2: Search Name ");
            System.out.println("3: Search SDT");
            System.out.println("4: Return");
            choose = sc.nextInt();
            ClearScreen();
            switch(choose)
            {
                case 1: {
                    SearchCustomerWithId();
                    break;
                }
                case 2: {
                    SearchCustomerWithName();
                    break;
                }
                case 3: {
                    SearchCustomerWithPhonenumber();
                    break;
                }
                default:{
                    break;
                }
            }
        }while(choose != 4);
    }

    //Tìm kiếm khách hàng với ID
    public void SearchCustomerWithId()
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhap ID muon tim kiem: ");
        int id = sc.nextInt();
        ArrayList<Customer> Customers = GetListCustomer();
        for(Customer x : Customers)
        {
            if(x.getId() == id)
            {
                x.Output();
                return;
            }
        }
        System.out.println("Khong tim thay!!!");
    }

    //Tìm kiếm khách hàng với Tên
    public void SearchCustomerWithName()
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhap ten muon tim");
        String name = sc.nextLine();
        ArrayList<Customer> Customers = GetListCustomer();
        for(Customer x: Customers)
        {
            if(x.getName().equals(name))
            {
                x.Output();
                return;
            }
        }
        System.out.println("Khong tim thay!!!");
    }

    //Tìm kiếm khách hàng với Số điện thoại
    public void SearchCustomerWithPhonenumber()
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhap ten muon tim");
        String phonenumber = sc.nextLine();
        ArrayList<Customer> Customers = GetListCustomer();
        for(Customer x: Customers)
        {
            if(x.getPhoneNumber().equals(phonenumber))
            {
                x.Output();
                return;
            }
        }
        System.out.println("Khong tim thay!!!");
    }

    public void CustomerMenu(){
        Scanner sc = new Scanner(System.in);
        do{
            ClearScreen();
            ShowListCustomer();
            System.out.println();
            System.out.println("1 : Add Booking");
            System.out.println("2 : Delete Booking");
            System.out.println("3 : Edit Booking");
            System.out.println("4 : Find Booking");
            System.out.println("5 : Exit");
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
                    FindCustomers();
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
            