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
    private ArrayList<Room> getListRoom(){
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
        System.out.println("ID    Name       Cost          Status");
        for(Room room : rooms){
            room.OutputRoom();
        }
    }

    private void UpdateFile(ArrayList<Room> rooms){
            try {
                FileWriter fileWriter = new FileWriter("Rooms.txt", false);
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

    /// THÊM PHÒNG
    public void AddRoom(){
        Scanner sc = new Scanner(System.in);
        System.out.print("ID Room : ");
        int idRoom = sc.nextInt();
        sc.nextLine();
        System.out.print("Name Room : ");
        String nameRoom = sc.nextLine();
        System.out.print("Cost Room : ");
        double costRoom = sc.nextDouble();
        try {
                FileWriter fileWriter = new FileWriter("Rooms.txt", true);
                fileWriter.write(idRoom + "\n");
                fileWriter.write(nameRoom + "\n");
                fileWriter.write(costRoom + "\n");
                fileWriter.write("Available" + "\n");
                fileWriter.close();
            } catch (IOException e) {
                System.out.println("Error" + e.getMessage());
            }
    }

    /// XÓA PHÒNG
    public void DeleteRoom(){
        Scanner sc = new Scanner(System.in);
        ArrayList<Room> rooms = getListRoom();
        System.out.print("Input ID Room To Delete : ");
        int idRoom = sc.nextInt();
        sc.nextLine();
        int check = 0;
        System.out.println();
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
            return;
        }
        UpdateFile(rooms);
        if(check >= 1){
            System.out.println("Deleted");
            sc.nextLine();
        }
    }

    /// SỬA THÔNG TIN PHÒNG
    public void EditRoom(){
        Scanner sc = new Scanner(System.in);
        ArrayList<Room> rooms = getListRoom();
        ClearScreen();
        ShowListRoom();
        System.out.println();
        System.out.print("Input ID Room To Fix : ");
        int idRoom = sc.nextInt();
        ClearScreen();
        for(Room room : rooms){
            if(idRoom == room.getIdRoom()){
                System.out.println("ID    Name       Cost          Status");
                System.out.println(room.getIdRoom() + "  " + room.getNameRoom() + "   " + room.getCostRoom() + "     " + room.getRoomStatus());
            }
        }
        System.out.println();
        EditRoomMenu(idRoom);
    }

    ///  MENU SỬA PHÒNG
    private void EditRoomMenu(int idRoom){
        Scanner sc = new Scanner(System.in);
        ArrayList<Room> rooms = getListRoom();
        int isChoose = 0;
        int changeChoice = 0;
        do{
            isChoose = 1;
            System.out.println("1 : Change ID Room");
            System.out.println("2 : Change Name Room");
            System.out.println("3 : Change Cost");
            System.out.println();
            System.out.print("Please Input Your Order : ");
            changeChoice = sc.nextInt();
            if(changeChoice > 3 || changeChoice <= 0){
                isChoose = 0;
                System.out.println("There is no Choice Found");
                sc.nextLine();
            }
        }while(isChoose != 1);
        ClearScreen();
        sc.nextLine();
        String changeText = "";
        int newIDRoom = 0;
        float changeCost = 0f;
        switch (changeChoice) {
            case 1:
                System.out.print("ID Room : ");
                newIDRoom = sc.nextInt();
                break;
            case 2:
                System.out.print("Name Room : ");
                changeText = sc.nextLine();
                break;
            case 3:
                System.out.print("Cost Room : ");
                changeCost = sc.nextFloat();
                sc.nextLine();
                break;
            default:
                break;
        }

        for(Room room : rooms){
            if(idRoom == room.getIdRoom()){

                if(changeChoice == 1){
                    room.setIdRoom(newIDRoom);
                }
                if(changeChoice == 2){
                    room.setNameRoom(changeText);
                }
                if(changeChoice == 3){
                    room.setCostRoom(changeCost);
                }
            }
        }
        ClearScreen();
        WriteFile(rooms);
        System.out.println("Change is successful");
        sc.nextLine();
    }

    /// GHI VÀO FILE DANH SÁCH MỚI

    private void WriteFile(ArrayList<Room> rooms){
        try {
                FileWriter fileWriter = new FileWriter("Rooms.txt", false);
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


    /// TÌM PHÒNG
    public void FindRoom(){
        Scanner sc = new Scanner(System.in);
        ArrayList<Room> rooms = getListRoom();
        System.out.print("Input ID Room To Find : ");
        int idRoom = sc.nextInt();
        ClearScreen();
        for(Room room : rooms){
            if(idRoom == room.getIdRoom()){
                System.out.println("ID    Name       Cost          Status");
                System.out.println(room.getIdRoom() + "  " + room.getNameRoom() + "   " + room.getCostRoom() + "     " + room.getRoomStatus());
                sc.nextLine();
                sc.nextLine();
                return;
            }
        }
        System.out.println("Room is not Founded");
        sc.nextLine();
        sc.nextLine();

    }

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
            System.out.println("1 : Add Room");
            System.out.println("2 : Delete Room");
            System.out.println("3 : Fix Room");
            System.out.println("4 : Find Room");
            System.out.println("5 : Return");
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
                    EditRoom();
                    break;
                case 4:
                    ClearScreen();
                    FindRoom();
                    break;
                default:
                    break;
            }
            sc.nextLine();
        }while(roomChoice != 5);
    }
}
