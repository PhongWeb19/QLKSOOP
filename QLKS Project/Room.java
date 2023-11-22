import java.util.ArrayList;
import java.util.Scanner;

public class Room{
    private int idRoom;
    private String nameRoom;
    private double costRoom;
    private String roomStatus;

    public Room(){
        idRoom = 0;
        nameRoom ="";
        costRoom = 0f;
        roomStatus = "";
    }

    public Room(int idRoom, String nameRoom, double costRoom, String roomStatus){
        this.idRoom = idRoom;
        this.nameRoom = nameRoom;
        this.costRoom = costRoom;
        this.roomStatus = roomStatus;
    }

    public int getIdRoom(){
        return idRoom;
    }

    public String getNameRoom(){
        return nameRoom;
    }

    public double getCostRoom(){
        return costRoom;
    }

    public String getRoomStatus(){
        return roomStatus;
    }

    public void setIdRoom(int idRoom){
        this.idRoom = idRoom;
    }

    public void setNameRoom(String nameRoom){
        this.nameRoom = nameRoom;
    }

    public void setCostRoom(double costRoom){
        this.costRoom = costRoom;
    }

    public void setRoomStatus(String roomStatus){
        this.roomStatus = roomStatus;
    }

    private void ClearScreen(){
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public void InputRoom(){
        Scanner sc = new Scanner(System.in);
        InputIdRoom();
        System.out.print("Name Room : ");
        InputNameRoom();
        System.out.print("Cost Room : ");
        costRoom = sc.nextDouble();
    }

    public void InputIdRoom(){
        int check;
        do{
            ClearScreen();
            check = 1;
            Scanner sc = new Scanner(System.in);
            System.out.print("ID Room : ");
            idRoom = sc.nextInt();
            sc.nextLine();
            ClearScreen();
            ListRoom lr = new ListRoom();
            ArrayList<Room> rooms =  lr.getListRoom();
            for(Room room : rooms){
                if(idRoom == room.getIdRoom()){
                    ClearScreen();
                    System.out.println("ID is Available, Retry");
                    sc.nextLine();
                    check = 0;
                }
            }
        }while(check == 0);
    }

    public void InputNameRoom(){
        int check;
        do{
            ClearScreen();
            check = 1;
            Scanner sc = new Scanner(System.in);
            System.out.print("Name Room : ");
            nameRoom = sc.nextLine();
            ClearScreen();
            ListRoom lr = new ListRoom();
            ArrayList<Room> rooms =  lr.getListRoom();
            for(Room room : rooms){
                if(nameRoom.equals(room.getNameRoom())){
                    ClearScreen();
                    System.out.println("Name is Available, Retry");
                    sc.nextLine();
                    check = 0;
                }
            }
        }while(check == 0);
    }


    public void OutputRoom(){
        
        System.out.printf("%-1s %-2s %-4s %-10s %-3s %-15s %-10s\n","",getIdRoom(),"",getNameRoom(),"",getCostRoom(),getRoomStatus());
    }
    public void EditRoom(){
        Scanner sc=new Scanner(System.in);
        int choice;
        do {
            System.out.printf("%-10s %-15s %-15s %-10s\n","ID Room", "Name Room", "Cost", "Status");
            OutputRoom();
            System.out.println();
            System.out.println("1 : Edit All Information");
            System.out.println("2 : Change Name Room");
            System.out.println("3 : Change Cost");
            System.out.println("4 : Return");
            System.out.println();
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            ClearScreen();
            switch (choice) {
                case 1:
                    sc.nextLine();
                    InputNameRoom();
                    System.out.print("Enter new Cost: ");
                    setCostRoom(sc.nextDouble());
                    ClearScreen();
                    break;
                case 2:
                    sc.nextLine();
                    InputNameRoom();
                    break;
                case 3:
                    sc.nextLine();
                    System.out.print("Enter new Cost: ");
                    setCostRoom(sc.nextDouble());
                    ClearScreen();
                    break;
                case 4:
                    System.out.println("Edit Finished!");
                    sc.nextLine();
                    ClearScreen();
                    break;
                default:
                    System.out.println("Error!!!");
                    sc.nextLine();
                    ClearScreen();
            }
        } while (choice != 4);

    }
}