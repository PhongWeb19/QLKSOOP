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

    public void OutputRoom(){
        System.out.println(getIdRoom() + "  " + getNameRoom() + "   " + getCostRoom() + "     " + getRoomStatus());
    }
}