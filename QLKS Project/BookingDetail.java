import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
public class BookingDetail {
    private int idBooking;
    private int idRoom;
    private String startDay;
    private String endDay;
    private double cost;
    public BookingDetail()
    {
        idBooking = 0;
        idRoom=0;
        startDay ="";
        endDay ="";
        cost = 0;
    }

    public BookingDetail(int idBooking,int idRoom,String startDay,String endDay,double cost)
    {
        this.idBooking = idBooking;
        this.idRoom=idRoom;
        this.startDay =startDay;
        this.endDay =endDay;
        this.cost = cost;
    }

    public int getIdBooking()
    {
        return this.idBooking;
    }
    public void setIdBooking(int idBooking)
    {
        this.idBooking = idBooking;
    }

    public int getIdRoom()
    {
        return this.idRoom;
    }
    public void setIdRoom(int idRoom)
    {
        this.idRoom = idRoom;
    }

    public String getstartDay()
    {
        return this.startDay;
    }
    public void setstartDay(String startDay)
    {
        this.startDay = startDay;
    }

    public String getendDay()
    {
        return this.endDay;
    }
    public void setendDay(String endDay)
    {
        this.endDay = endDay;
    }

    public double getCost()
    {
        return this.cost;
    }
    public void setCost(double cost)
    {
        this.cost = cost;
    }


    public String InputDate() {
        Scanner sc = new Scanner(System.in);
        int day, month, year;
        int check;
        do{
            check = 1;
            System.out.println("Enter Date: ");
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

        String dateStr = String.format("%02d/%02d/%04d", day, month, year);
        return dateStr;
    }

    public void OutputListRoom()
    {
        ListRoom a = new ListRoom();
        a.ShowListRoom();
    }

    public boolean chekcIDRoom(int idRoom)
    {
        ListRoom a = new ListRoom();
        ArrayList<Room> c = new ArrayList<>();
        c = a.getListRoom(); 
        for(Room x: c)
        {
            if( x.getIdRoom()== idRoom )
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

    public void Input()
    {
        Scanner sc = new Scanner(System.in);
        int choice = 1;
        do{
            OutputListRoom();
            System.out.print("Input idRoom: ");
            this.idRoom = sc.nextInt();
            System.out.println();
            if(!chekcIDRoom(this.idRoom))
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
                choice = 1;
                ClearScreen();
            }
        }while(choice != 1);
        System.out.println("Input StartDay: ");
        startDay=InputDate();
        System.out.println("Input EndDay: ");
        endDay = InputDate();
        ListRoom r1 = new ListRoom();
        this.cost = r1.FindCostRoomById(idRoom);
    }

    public void Output()
    {
        System.out.printf("%1s %15s %16s %15s" ,"IdRoom","StartDay","EndDay","Cost");
        System.out.println();
        System.out.printf("%5s %17s %18s %11s",idRoom,startDay,endDay,cost);
        System.out.println();
    }

    
}
