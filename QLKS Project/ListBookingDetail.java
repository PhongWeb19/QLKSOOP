import java.util.ArrayList;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.Date;
import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
public class ListBookingDetail {
    
    //Lay danh sach booking
    public ArrayList<BookingDetail> getListBooking()
    {
        ArrayList<BookingDetail> bookingDetails = new ArrayList<>();
        File file = new File("BookingDetails.txt");
        try{
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                bookingDetails.add(new BookingDetail(Integer.parseInt(sc.nextLine()),Integer.parseInt(sc.nextLine()),sc.nextLine(),sc.nextLine(),Double.parseDouble(sc.nextLine())));
            }
        } catch (FileNotFoundException e ){
            System.out.println("File Not Found");
        }
        return bookingDetails;
    }

    //Xem danh sach booking
    public void ShowListBooking(int idBooking)
    {
        ArrayList<BookingDetail> ListBookingDetails = getListBooking();
        for(BookingDetail x : ListBookingDetails)
        {
            if(x.getIdBooking() == idBooking)
            {
                x.Output();
            }
        }
    }

    
    //Them booking detail
    public void AddBookingDetals(int idBooking)
    {
        
        BookingDetail BookingDetail = new BookingDetail();
        BookingDetail.Input();
        try{
            FileWriter fileWriter = new FileWriter("BookingDetails.txt", true);
            fileWriter.write(idBooking + "\n");
            fileWriter.write(BookingDetail.getIdRoom() + "\n");
            fileWriter.write(BookingDetail.getstartDay() + "\n");
            fileWriter.write(BookingDetail.getendDay() + "\n");
            fileWriter.write(BookingDetail.getCost() + "\n");            
            fileWriter.close();
        }
        catch (IOException e) {
            System.out.println("Error" + e.getMessage());
        }
    }

    //Xoa booking detail
    public void DeleteBookingDetail(int idBooking)
    {
        ArrayList<BookingDetail> bookingDetails = getListBooking();
        for(BookingDetail bookingdetail : bookingDetails)
        {
            if(bookingdetail.getIdBooking() == idBooking)
            {
                bookingDetails.remove(bookingdetail);
                
                break;
            }
        }
        WriteToFile(bookingDetails,false);
    }

    //Tim kiem
    public void FindBookingDetails(int idBooking)
    {
        ArrayList<BookingDetail> bookingDetails = getListBooking();
        for(BookingDetail bookingdetail : bookingDetails)
        {
            if(bookingdetail.getIdBooking() == idBooking)
            {
                bookingdetail.Output();
            }
        }
    }

    //Sua BookingDetail
    public void EditRoomId(int idRoom,int idBooking)
    {
        Scanner sc = new Scanner(System.in);
        ArrayList<BookingDetail> bookingDetails = getListBooking();
        for(BookingDetail bookingdetail : bookingDetails)
        {
            if(bookingdetail.getIdBooking() == idBooking)
            {
                System.out.println(bookingdetail.getIdBooking());
                System.out.println("Nhap Id Room : ");
                bookingdetail.setIdRoom(idRoom);
                for(BookingDetail bookingDetail1 : bookingDetails)
                {
                    if(bookingDetail1.getIdRoom() == idRoom)
                    {
                        bookingdetail.setCost(bookingDetail1.getCost());
                    }
                }
            }
        }
         WriteToFile(bookingDetails,false);
    }
    
    public void WriteToFile(ArrayList<BookingDetail> bookingDetails,boolean isWrite)
    {
        try{
            FileWriter fileWriter = new FileWriter("BookingDetails.txt", isWrite);
            if(isWrite == false)
            {
                isWrite = true;
            }
            for(BookingDetail bookingDetail: bookingDetails)
            {
                fileWriter.write(bookingDetail.getIdBooking() + "\n");
                fileWriter.write(bookingDetail.getIdRoom() + "\n");
                fileWriter.write(bookingDetail.getstartDay() + "\n");
                fileWriter.write(bookingDetail.getendDay() + "\n");
                fileWriter.write(bookingDetail.getCost() + "\n");            
                
            }
            fileWriter.close();
        }
        catch (IOException e) {
            System.out.println("Error" + e.getMessage());
        }
    }

}
