import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Scanner;
import java.util.ArrayList;

public class ListBooking {

    private int choice = 0;
    public ListBooking(){

    }

    // Clear
    private void ClearScreen(){
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
    // Lấy danh sách Booking
    public ArrayList<Booking> getListBooking(){
        ArrayList<Booking> bookings = new ArrayList<>();
        File file = new File("Bookings.txt");
        try{
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                bookings.add(new Booking(Integer.parseInt(sc.nextLine()),Integer.parseInt(sc.nextLine()),Integer.parseInt(sc.nextLine())));
            }
        } catch (FileNotFoundException e ){
            System.out.println("File Not Found");
        }
        return bookings;
    }

    // Xuất danh sách Booking
    public void ShowListBooking(){
        ArrayList<Booking> bookings = getListBooking();
        Scanner sc = new Scanner(System.in);
        System.out.println("ID Booking    ID Receptionist   ID Customer");
        for(Booking booking : bookings)
        {
            booking.Output();
        }
    }

    private void UpdateFile(ArrayList<Booking> bookings, boolean isWrite){
        try{
            FileWriter fileWriter = new FileWriter("Bookings.txt", isWrite);
            if(isWrite == false)
            {
                isWrite = true;
            }
            for(Booking booking: bookings)
            {
                fileWriter.write(booking.getIdBooking() + "\n");
                fileWriter.write(booking.getIdReceptionist() + "\n");
                fileWriter.write(booking.getIdCustomer() + "\n");       
                
            }
            fileWriter.close();
        }
        catch (IOException e) {
            System.out.println("Error" + e.getMessage());
        }
    }
    

    

    // Thêm Booking
    public void AddBooking(){
        ListStaff a = new ListStaff();
        ArrayList<Staff> c = new ArrayList<>();
        c=a.GetListReceptionist();   
        a.ShowListStaff(c);
        Booking booking = new Booking();
        booking.Input();
        ListBookingDetail lbd = new ListBookingDetail();
        lbd.AddBookingDetals(booking.getIdBooking());
        try{
            FileWriter filewriter = new FileWriter("Bookings.txt",true);
            filewriter.write(booking.getIdBooking() + "\n");
            filewriter.write(booking.getIdReceptionist() + "\n");
            filewriter.write(booking.getIdCustomer() + "\n");
            filewriter.close();
        } catch ( IOException e ){
            System.out.print("Error"+ e.getMessage());
        }
    }

    // Xóa Booking
    public void DeleteBooking(){
        ClearScreen();
        Scanner sc = new Scanner(System.in);
        ArrayList<Booking> bookings = getListBooking();
        ShowListBooking();
        System.out.print("Input ID Booking To Delete : ");
        int idBooking = sc.nextInt(); 
        int check = 0;
        for(Booking booking : bookings){
            if(idBooking == booking.getIdBooking()){
                bookings.remove(booking);
                check++;
                break;
            }
        } 
        if(check == 0){
            System.out.print("ID Is Not Founded !!");
            sc.nextLine();
            return ;
        }
        UpdateFile(bookings, false);
        ListBookingDetail lbd = new ListBookingDetail();
        lbd.DeleteBookingDetail(idBooking);
        
        if(check >= 1){
            System.out.print("Deleted");
            sc.nextLine();
        }
    }

    // Sửa Booking
    public void FixBooking(){
        Scanner sc = new Scanner(System.in);
        ListBookingDetail lbd = new ListBookingDetail();
        ArrayList<Booking> bookings = getListBooking();
        ClearScreen();
        ShowListBooking();
        System.out.println();
        System.out.print("Input ID Booking To Fix : ");
        int idBooking = sc.nextInt();
        ClearScreen();
        for ( Booking booking : bookings){
            if(idBooking == booking.getIdBooking()){
                System.out.println("ID Booking   ID Receptionist    ID Customer ");
                booking.Output();
                lbd.FindBookingDetails(idBooking);
            }
        }
        System.out.println();
        FixBookingMenu(idBooking);
    }

    private void FixBookingMenu(int idBooking){
        Scanner sc = new Scanner(System.in);
        ListBookingDetail lbd = new ListBookingDetail();
        ArrayList<Booking> bookings = getListBooking();
        Booking infoBooking = new Booking();
        BookingDetail boolingDetail = new BookingDetail();
        ListRoom rooms = new ListRoom();
        int isChoose = 0;
        int changeChoice = 0;
        do{
            isChoose = 1;
            System.out.print("1. Change ID Receptionist. \n");
            System.out.print("2. Change ID Customer. \n");
            System.out.print("3.Change ID Room.\n");
            System.out.print("Please Input Your Order : ");
            changeChoice = sc.nextInt();
            if(changeChoice < 0 || changeChoice > 3)
            {
                isChoose = 0;
                System.out.print("There Is No Choice Found !!");
                sc.nextLine();
            }
        } while ( isChoose != 1);
        ClearScreen();
        sc.nextLine();
        int changeID = 0;
        switch (changeChoice) {
            case 1:
                infoBooking.OutputReceptionist();
                System.out.print("New ID Receptionist : ");
                changeID = sc.nextInt();
                if(!infoBooking.checkIDReceptionist(changeID))
                {
                    int choice = 1;
                    do{
                        System.out.printf("Input ID Receptionist Again: ");
                        changeID = sc.nextInt();
                    }while(choice != 1);
                }
                break;
            case 2 :
                infoBooking.OutputCustomer();
                System.out.print("New ID Customer : ");
                changeID = sc.nextInt();
                if(!infoBooking.checkIDCustomer(changeID))
                {
                    int choice = 1;
                    do{
                        System.out.printf("Input ID Customer Again: ");
                        changeID = sc.nextInt();
                    }while(choice != 1);
                }
                break;
            case 3 :
                rooms.ShowListRoom();
                System.out.println("New ID Room: ");
                changeID = sc.nextInt();
                if(!boolingDetail.chekcIDRoom(changeID))
                {
                    ClearScreen();
                    boolingDetail.OutputListRoom();
                    int choice = 1;
                    do{
                        System.out.printf("Input ID Room Again: ");
                        changeID = sc.nextInt();
                    }while(choice != 1);
                }
            default:
                break;
        }
        for(Booking booking : bookings){
            if(idBooking == (booking.getIdBooking()))
            {
                if(changeChoice==1){
                    if(booking.checkIDReceptionist(changeID))
                    {
                        booking.setIdReceptionist(changeID);
                    }
                }
                if(changeChoice==2){
                    booking.setIdCustomer(changeID);
                }
                if(changeChoice==3)
                {
                    lbd.EditRoomId(changeID,idBooking);
                }
            }
        }
        ClearScreen();
        WriteFile(bookings);
        System.out.println("Change is successful !!");
        sc.nextLine();
    }

    // Ghi vào file mới 
    private void WriteFile(ArrayList<Booking> bookings){
        try {
                FileWriter fileWriter = new FileWriter("Bookings.txt", false);
                for(Booking booking : bookings){
                    fileWriter.write(booking.getIdBooking() + "\n");
                    fileWriter.write(booking.getIdReceptionist() + "\n");
                    fileWriter.write(booking.getIdCustomer() + "\n");
                }
                fileWriter.close();
            } catch (IOException e) {
                System.out.println("Error" + e.getMessage());
            }
    }

    // Tìm Booking
    public void FindBooking(){
        ClearScreen();
        ShowListBooking();
        Scanner sc = new Scanner(System.in);
        ListBookingDetail lbd = new ListBookingDetail();
        ArrayList<Booking> bookings = getListBooking();
        System.out.print("Input ID Booking To Find : ");
        int idBooking = sc.nextInt();
        ClearScreen();
        for(Booking booking : bookings){
            if(idBooking == booking.getIdBooking()){
                System.out.println("ID Booking    ID Receptionist    ID Customer");
                booking.Output();
                int choice = 0;
                do{
                    if(choice != 1)
                    {
                        System.out.println("1:Show more");
                        System.out.printf("Input choice: ");
                        choice = sc.nextInt();
                    }
                    else
                    {
                        sc.nextLine();
                        sc.nextLine();
                        choice = 2;
                    }
                    // ClearScreen();
                    switch (choice) {
                        case 1:
                            ClearScreen();
                            System.out.println("ID Booking    ID Receptionist    ID Customer     IdRoom     StartDay     EndDay     Cost");
                            // booking.Output();
                            booking.OutputAll(idBooking);
                            // lbd.ShowListBooking(idBooking);
                            break;
                    
                        default:
                            break;
                    }
                }while(choice==1);
                return;
            }
        }
        System.out.println("Booking is not Founded");
        sc.nextLine();
        sc.nextLine();
    }

    public void BookingMenu(){
        Scanner sc = new Scanner(System.in);
        do{
            ClearScreen();
            ShowListBooking();
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
                    ClearScreen();
                    AddBooking();
                    break;
                case 2:
                    ClearScreen();
                    DeleteBooking();
                    break;
                case 3:
                    ClearScreen();
                    FixBooking();
                    break;
                case 4:
                    ClearScreen();
                    FindBooking();
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
        ListBooking lb = new ListBooking();
        lb.BookingMenu();
    }
}