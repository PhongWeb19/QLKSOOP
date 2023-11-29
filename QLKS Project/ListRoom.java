import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

public class ListRoom{

    public ListRoom(){

    }

    /// CLEAR
    private void ClearScreen(){
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    /// LẤY DANH SÁCH CÁC PHÒNG
    public ArrayList<Room> getListRoom(){
        ArrayList<Room> rooms = new ArrayList<>();
        File file = new File("Rooms.txt");
        try{
            Scanner sc = new Scanner(file);
            while(sc.hasNextLine()){
                rooms.add(new Room(Integer.parseInt(sc.nextLine()), sc.nextLine(), Double.parseDouble(sc.nextLine()), sc.nextLine()));
            }
        }catch (FileNotFoundException e) {
            System.out.println("File Not Found");
        }
        return rooms;
    }

    /// XUẤT DANH SÁCH CÁC PHÒNG
    public void ShowListRoom(){
        ArrayList<Room> rooms = getListRoom();
        Scanner sc =new Scanner(System.in);
        System.out.println("      +-----------------------------------+");
        System.out.println("      |           List Room               |");
        System.out.println("      +-----------------------------------+");
        System.out.printf("%-10s %-15s %-15s %-10s\n","ID Room", "Name Room", "Cost", "Status");
        for(Room room : rooms){
            room.OutputRoom();
        }
    }

    public void ShowRoom(Room room){
        ArrayList<Room> rooms = getListRoom();
        Scanner sc =new Scanner(System.in);
        System.out.printf("%-10s %-15s %-15s %-10s","ID Room", "Name Room", "Cost", "Status");
        room.OutputRoom();
    }

// Ghi vào File
    private void WriteToRoom(ArrayList<Room> rooms, boolean isWrite){
        if(rooms.isEmpty()) DeleteEmptyRoom();
        try {
            FileWriter fileWriter = new FileWriter("Rooms.txt", isWrite);
            for(Room room : rooms){
                fileWriter.write(room.getIdRoom() + "\n");
                fileWriter.write(room.getNameRoom() + "\n");
                fileWriter.write(room.getCostRoom() + "\n");
                fileWriter.write(room.getRoomStatus() + "\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("Error" + e.getMessage());
        }
    }

    private void DeleteEmptyRoom(){
        try{
            FileWriter fileWriter = new FileWriter("Rooms.txt", false);
            fileWriter.close();
            
        }catch (IOException e){
            System.out.println("Error " + e.getMessage());
        }
    }

    /// THÊM PHÒNG
    public void AddRoom(){
        Scanner sc = new Scanner(System.in);
        Room room= new Room();
        room.InputRoom();
        try {
            FileWriter fileWriter = new FileWriter("Rooms.txt", true);
            fileWriter.write(room.getIdRoom() + "\n");
            fileWriter.write(room.getNameRoom()+ "\n");
            fileWriter.write(room.getCostRoom()+ "\n");
            fileWriter.write("Available" + "\n");
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("Error" + e.getMessage());
        }
    }
    /// TÌM PHÒNG
    public Room FindRoom(ArrayList<Room> rooms){
        Scanner sc= new Scanner(System.in);
        System.out.println("+-----------------------+");
        System.out.println("| 1 : ID Room           |");
        System.out.println("| 2 : Name Room         |");
        System.out.println("| 3 : Cost Room         |");
        System.out.println("+-----------------------+");
        System.out.println();
        System.out.print("Enter choice: ");
        int choice = sc.nextInt();
        ClearScreen();
        switch(choice){
            case 1:
                ShowListRoom();
                System.out.println();
                System.out.print("Enter ID : ");
                int idNeedFind = sc.nextInt();
                ClearScreen();
                for(Room room: rooms){
                    if(idNeedFind == room.getIdRoom()) {
                        return room;
                    }
                }
                System.out.println("ID is not Found");
                sc.nextLine();
                sc.nextLine();
                ClearScreen();
                break;
            case 2:
                sc.nextLine();
                ShowListRoom();
                System.out.println();
                System.out.print("Enter Name : ");
                String nameNeedFind = sc.nextLine();
                ClearScreen();
                for(Room room: rooms){
                    if(room.getNameRoom().equals(nameNeedFind)) {
                        return room;
                    }
                }
                System.out.println("Name is not Found");
                sc.nextLine();
                ClearScreen();
                break;
            case 3:
                sc.nextLine();
                ShowListRoom();
                System.out.println();
                System.out.print("Enter Cost Room : ");
                double costNeedToFind = sc.nextDouble();
                ClearScreen();
                for(Room room: rooms){
                    if(room.getCostRoom()==costNeedToFind) {
                        return room;
                    }
                }
                System.out.println("Cost is not Found");
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
        if(reChoice == 2) return FindRoom(rooms);
        return null;
    }
//    Sau khi tìm, xuất thông tin phòng
    public void FindAndShowRooms(){
    Scanner sc = new Scanner(System.in);
    System.out.println("---- Find ----");
    ArrayList<Room> rooms = getListRoom();
    ClearScreen();
    ShowListRoom();
    System.out.println();
    System.out.println("----Find By----");
    Room room=FindRoom(rooms);
    ClearScreen();
    if(room == null) return;
    room.OutputRoom();
    sc.nextLine();
}
    /// XÓA PHÒNG
    public void DeleteRoom(){
        Scanner sc = new Scanner(System.in);
        ArrayList<Room> rooms = getListRoom();
        ShowListRoom();
        System.out.println();
        System.out.print("Input ID Room To Delete : ");
        int idRoom = sc.nextInt();
        sc.nextLine();
        ClearScreen();
        int check = 0;
        Room r = new Room();
        for(Room room : rooms){
            if(idRoom == room.getIdRoom()){
                r = room;
                check++;
            }
        }
        rooms.remove(r);
        if(check == 0){
            System.out.println("ID is not Founded");
            sc.nextLine();
            ClearScreen();
            return;
        }
        WriteToRoom(rooms, false);
        if(check >= 1){
            System.out.println("Deleted");
            sc.nextLine();
            ClearScreen();
        }
    }


    /// SỬA THÔNG TIN PHÒNG
    public void EditRooms(){
        ArrayList<Room> rooms = new ArrayList<>();
        rooms = getListRoom();
        ClearScreen();
        Room room =FindByIDandCost(rooms);
        room.EditRoom();

        WriteToRoom(rooms,false);
    }

    private Room FindByIDandCost(ArrayList<Room> rooms){
        ShowListRoom();
        System.out.println();
        Scanner sc= new Scanner(System.in);
        System.out.println("1 : Find By ID Room");
        System.out.println("2 : Find By Name Room ");
        System.out.println();
        System.out.print("Enter choice: ");
        int choice = sc.nextInt();
        ClearScreen();
        ShowListRoom();
        System.out.println();
        switch(choice){
            case 1:
                System.out.print("Enter ID Room : ");
                int idNeedFind = sc.nextInt();
                ClearScreen();
                for(Room room: rooms){
                    if(idNeedFind == room.getIdRoom()) {
                        return room;
                    }
                }
                System.out.println("ID is not Found");
                sc.nextLine();
                sc.nextLine();
                ClearScreen();
                break;
            case 2:
                sc.nextLine();
                System.out.print("Enter Name Room : ");
                String nameNeedFind = sc.nextLine();
                ClearScreen();
                for(Room room: rooms){
                    if(room.getNameRoom().equals(nameNeedFind)) {
                        return room;
                    }
                }
                System.out.println("Name is not Found");
                sc.nextLine();
                ClearScreen();
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
        if(reChoice == 2) return FindByIDandCost(rooms);
        return null;
    }
    

    /// Tìm giá theo id phòng
    public double FindCostRoomById(int idRoom){
        Scanner sc = new Scanner(System.in);
        ArrayList<Room> rooms = getListRoom();
        for(Room room : rooms){
            if(idRoom == room.getIdRoom()){
                return room.getCostRoom();
            }
        }
        System.out.println("Room is not Found");
        sc.nextLine();
        return 0;
    }

    /// MENU PHÒNG

    public void RoomMenu(){
        int roomChoice = 0;
        do{
            ClearScreen();
            Scanner sc = new Scanner(System.in);
            ShowListRoom();
            System.out.println();
            System.out.println("+-----------------------+");
            System.out.println("| 1 : Add Room          |");
            System.out.println("| 2 : Delete Room       |");
            System.out.println("| 3 : Edit Room         |");
            System.out.println("| 4 : Find Room         |");
            System.out.println("| 5 : Return            |");
            System.out.println("+-----------------------+");
            System.out.println();
            System.out.print("Please Input Your Choice : ");
            roomChoice = sc.nextInt();
            switch (roomChoice) {
                case 1:
                    ClearScreen();
                    AddRoom();
                    break;
                case 2:
                    ClearScreen();
                    DeleteRoom();
                    break;
                case 3 :
                    EditRooms();
                    break;
                case 4:
                    ClearScreen();
                    FindAndShowRooms();
                    break;
                default:
                    break;
            }
            sc.nextLine();
        }while(roomChoice != 5);
    }
}
