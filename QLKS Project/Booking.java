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

    public void Input(){
        Scanner sc = new Scanner(System.in);
        System.out.print("ID Receptionist : ");
        idReceptionist = sc.nextInt();
        // ArrayList < ListStaff > liststaffs = GetListReceptionist() ;    
        // for (ListStaff ls : lss ){

        // }
        System.out.print("ID Customer : ");
        idCustomer = sc.nextInt();
        setIdBooking();
        idBooking = getIdBooking();
    }

    public void Output(){
        System.out.println("     " + getIdBooking() + "              " + getIdReceptionist()+ "             "+getIdCustomer());
    }

}