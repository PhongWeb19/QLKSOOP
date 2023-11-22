import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
public class Booking {
    private int idReceptionist;
    private int idCustomer;
    private int idBooking ;
    public Booking()
    {
        idReceptionist = 0;
        idCustomer = 0;
        idBooking = 0;
    }
    public Booking(int idBooking, int  idReceptionist , int  idCustomer)
    {
        this.idBooking = idBooking;
        this.idReceptionist = idReceptionist;
        this.idCustomer = idCustomer;
    }
    public void setIdReceptionist(int  idReceptionist)
    {
        this.idReceptionist = idReceptionist;
    }
    public int  getIdReceptionist()
    {
        return idReceptionist;
    }
    public void setIdCustomer(int  idCustomer)
    {
        this.idCustomer = idCustomer;
    }
    public int  getIdCustomer()
    {
        return idCustomer;
    }
    public int getIdBooking ()
    {
        return idBooking;
    }

    public void setIdBooking()
    {
        File file = new File("CountID.txt");
        try{
            Scanner sc = new Scanner(file);
            idBooking = Integer.parseInt(sc.nextLine());
            FileWriter fileWriter = new FileWriter("CountID.txt", false);
            int count = idBooking + 1;
            fileWriter.write(String.valueOf(count));
            fileWriter.close();

        } catch(FileNotFoundException e) {
            System.out.println("File Not Found");
        }
        catch (IOException e) {
            System.out.println("Error" + e.getMessage());
        }

    }

    public void OutputReceptionist()
    {
        ListStaff a = new ListStaff();
        ArrayList<Staff> c = new ArrayList<>();
        c=a.GetListReceptionist();   
        a.ShowListStaff(c);
    }

    public void OutputCustomer()
    {
        ListCustomer a = new ListCustomer();
        a.ShowAllListCustomer();
    }

    public boolean checkIDReceptionist(int idReceptionist)
    {
        ListStaff a = new ListStaff();
        ArrayList<Staff> c = new ArrayList<>();
        c=a.GetListReceptionist(); 
        for(Staff x: c)
        {
            if(x.getId() == idReceptionist )
            {
                return true;
            }
        }
        return false;
    }
    public boolean checkIDCustomer(int idCustomer)
    {
         ListCustomer a = new ListCustomer();
         ArrayList<Customer> c = new ArrayList<>();
         c= a.GetListCustomer();   
        for(Customer x: c)
        {
            if(x.getId() == idCustomer )
            {
                return true;
            }
        }
        return false;
    }
    private void ClearScreen(){
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
    public void checkInputIdReceptionist()
    {
        Scanner sc = new Scanner(System.in);
        int choice = 1;
        do{ 
            OutputReceptionist();
            System.out.print("ID Receptionist : ");
            idReceptionist = sc.nextInt();
            if(!checkIDReceptionist(idReceptionist))
            {
                ClearScreen();
                System.out.println("Id Not Found!!");
                sc.nextLine();
                sc.nextLine();
                ClearScreen();
                choice = 2;
                OutputReceptionist();
            }
            else
            {
                choice = 1;
                ClearScreen();
            }
        }while(choice != 1);
    }
    public void checkInputIdCustomer()
    {
        Scanner sc = new Scanner(System.in);
        int choice = 1;
         do{
            
            OutputCustomer();
            System.out.print("ID Customer : ");
            idCustomer = sc.nextInt();
            if(!checkIDCustomer(idCustomer))
            {
                ClearScreen();
                System.out.println("Id Not Found!!");
                sc.nextLine();
                sc.nextLine();
                ClearScreen();
                choice = 2;
            }
            else
            {
                setIdBooking();
                idBooking = getIdBooking();
                choice = 1;
                ClearScreen();
            }
        }while(choice != 1);
    }
    public void Input(){
        Scanner sc = new Scanner(System.in);
        checkInputIdReceptionist();
        checkInputIdCustomer();
    }

    public void Output(){
        System.out.printf("%-3s %-16s %-15s %-20s","",getIdBooking(),getIdReceptionist(),getIdCustomer());
        System.out.println();
    }

    public void OutputAll(int idBooking)
    {
        ListBookingDetail listBookingDetail = new ListBookingDetail();
        ArrayList<BookingDetail> listBookings = new ArrayList<>();
        listBookings = listBookingDetail.getListBooking();
        for(BookingDetail x : listBookings)
        {
            if(x.getIdBooking() == idBooking)
            {
                System.out.printf("%-3s %-16s %-15s %-14s","",getIdBooking(),getIdReceptionist(),getIdCustomer());
                System.out.printf("%-6s %-12s %-12s %-12s",x.getIdRoom(),x.getstartDay(),x.getendDay(),x.getCost());
                System.out.println();
            }
        }
        
    }
}