import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class ListStaff {
    ArrayList<Staff> staffs = new ArrayList<Staff>();
    public ListStaff() {
    }

    private int inner = 0;
    private int innerFileName;

    private void ClearScreen(){
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public ListStaff(ArrayList<Staff> staffs) {
        this.staffs = staffs;
    }
    /// LẤY DANH SÁCH NHÂN VIÊN DỌN DẸP
    public ArrayList<Staff> GetListCleaningStaff(){
        ArrayList<Staff> staffs = new ArrayList<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyy");
        File file = new File("CleaningStaffs.txt");
        try{
            Scanner sc = new Scanner(file);
            while(sc.hasNextLine()){
                staffs.add(new CleaningStaff(Integer.parseInt(sc.nextLine()), sc.nextLine(),dateFormat.parse(sc.nextLine()),sc.nextLine(),sc.nextLine(),sc.nextLine(),sc.nextLine(), Integer.parseInt(sc.nextLine())));
                sc.nextLine();
            }
            sc.close();
        }catch(FileNotFoundException e){
            System.out.println("File Not Found");
        }catch(ParseException e){
            System.out.println("Lỗi: Error Date Form");
        }

        return staffs;
    }

    /// LẤY DANH SÁCH NHÂN VIÊN BẢO VỆ 
    public ArrayList<Staff> GetListSecurity(){
        ArrayList<Staff> staffs = new ArrayList<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyy");
        File file = new File("Securities.txt");
        try{
            Scanner sc = new Scanner(file);
            while(sc.hasNextLine()){
                staffs.add(new Security(Integer.parseInt(sc.nextLine()), sc.nextLine(),dateFormat.parse(sc.nextLine()),sc.nextLine(),sc.nextLine(),sc.nextLine(),sc.nextLine(), Integer.parseInt(sc.nextLine())));
                sc.nextLine();
            }
            sc.close();
        }catch(FileNotFoundException e){
            System.out.println("File Not Found");
        }catch(ParseException e){
            System.out.println("Lỗi: Error Date Form");
        }

        return staffs;
    }

    /// LẤY DANH SÁCH NHÂN VIÊN TIẾP TÂN
    public ArrayList<Staff> GetListReceptionist(){
        ArrayList<Staff> staffs = new ArrayList<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyy");
        File file = new File("Receptionists.txt");
        try{
            Scanner sc = new Scanner(file);
            while(sc.hasNextLine()){
                staffs.add(new Receptionist(Integer.parseInt(sc.nextLine()), sc.nextLine(),dateFormat.parse(sc.nextLine()),sc.nextLine(),sc.nextLine(),sc.nextLine(),sc.nextLine(), Double.parseDouble(sc.nextLine()), Double.parseDouble(sc.nextLine())));
                sc.nextLine();
            }
            sc.close();
        }catch(FileNotFoundException e){
            System.out.println("File Not Found");
        }catch(ParseException e){
            System.out.println("Lỗi: Error Date Form");
        }

        return staffs;
    }

    /// CHỌN 1 DANH SÁCH NHÂN VIÊN TỪ FILE
    private ArrayList<Staff> GetList(){

        ArrayList<Staff> staffs = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        System.out.println();
        System.out.println("1 : List Cleaning Staff");
        System.out.println("2 : List Security");
        System.out.println("3 : List Receptionist");
        System.out.print("Enter your choice: ");
        int choice = sc.nextInt();
        innerFileName = choice;
        switch (choice){
            case 1:
                return GetListCleaningStaff();
            case 2:
                return GetListSecurity();
            case 3:
                return GetListReceptionist();
        }

        return staffs;
    }

    /// XUẤT DANH SÁCH NHÂN VIÊN BẤT KỲ
    public void ShowListStaff(ArrayList<Staff> staffs){
        for (Staff staff : staffs) {
            if (staff instanceof Security) {
                Security s =(Security)staff ;
                s.Output();
            } else if (staff instanceof Receptionist) {
                Receptionist r =(Receptionist)staff;
                r.Output();
            } else if(staff instanceof  CleaningStaff){
                CleaningStaff c =(CleaningStaff)staff;
                c.Output();
            }
        }
    }

    /// XUẤT DANH SÁCH TẤT CẢ NHÂN VIÊN
    public void ShowAllListStaff(){
        ArrayList<Staff> cs = GetListCleaningStaff();
        System.out.println("---- Cleaning Staff List 1----");
        ShowListStaff(cs);
        System.out.println();
        ArrayList<Staff> sc = GetListSecurity();
        System.out.println("---- Security List 2----");
        ShowListStaff(sc);
        System.out.println();
        ArrayList<Staff> rc = GetListReceptionist();
        System.out.println("---- Receptionist List 3----");
        ShowListStaff(rc);
    }
    /// GHI VÀO FILE
    private void WriteToStaff(ArrayList<Staff> staffs, boolean isWrite){
        Scanner sc = new Scanner(System.in);
        try{
            if(staffs.isEmpty()) {
                        DeleteEmptyStaff(staffs);
                        return;
                    }
            for(Staff staff : staffs){
                if (staff instanceof CleaningStaff) {
                    FileWriter fileWriter = new FileWriter("CleaningStaffs.txt", isWrite);
                    if(!isWrite) isWrite = true;
                    fileWriter.write(staff.getId() + "\n");
                    fileWriter.write(staff.getName() + "\n");
                    fileWriter.write(staff.getDoBstr() + "\n");
                    fileWriter.write(staff.getGender() + "\n");
                    fileWriter.write(staff.getAddress() + "\n");
                    fileWriter.write(staff.getEmail() + "\n");
                    fileWriter.write(staff.getPhoneNumber() + "\n");
                    fileWriter.write(((CleaningStaff) staff).getNumberOfRoom() + "\n");
                    fileWriter.write(staff.PayRoll() + "\n");
                    fileWriter.close();
                }
                if (staff instanceof Security) {
                    FileWriter fileWriter = new FileWriter("Securities.txt", isWrite);
                    if(!isWrite) isWrite = true;
                    fileWriter.write(staff.getId() + "\n");
                    fileWriter.write(staff.getName() + "\n");
                    fileWriter.write(staff.getDoBstr() + "\n");
                    fileWriter.write(staff.getGender() + "\n");
                    fileWriter.write(staff.getAddress() + "\n");
                    fileWriter.write(staff.getEmail() + "\n");
                    fileWriter.write(staff.getPhoneNumber() + "\n");
                    fileWriter.write((((Security) staff).getNumberDayOfWork()) + "\n");
                    fileWriter.write(staff.PayRoll() + "\n");
                    fileWriter.close();
                }
                else if(staff instanceof Receptionist){
                    FileWriter fileWriter = new FileWriter("Receptionists.txt", isWrite);
                    if(!isWrite) isWrite = true;
                    fileWriter.write(staff.getId() + "\n");
                    fileWriter.write(staff.getName() + "\n");
                    fileWriter.write(staff.getDoBstr() + "\n");
                    fileWriter.write(staff.getGender() + "\n");
                    fileWriter.write(staff.getAddress() + "\n");
                    fileWriter.write(staff.getEmail() + "\n");
                    fileWriter.write(staff.getPhoneNumber() + "\n");
                    fileWriter.write((((Receptionist) staff).getBaseSalary()) + "\n");
                    fileWriter.write(((Receptionist) staff).getCoefficientsSalary() + "\n");
                    fileWriter.write(staff.PayRoll() + "\n");
                    fileWriter.close();
                }
            }
        }catch (IOException e){
            System.out.println("Error " + e.getMessage());
        }
    }

    /// KIỂM TRA DƯ LIỆU RỖNG HAY KHÔNG
    private void DeleteEmptyStaff(ArrayList<Staff> staffs){
        Scanner sc = new Scanner(System.in);
        String fileName = "";
        switch (innerFileName) {
            case 1:
                fileName = "CleaningStaffs.txt";
                break;
            case 2:
                fileName = "Securities.txt";
                break;
            case 3:
                fileName = "Receptionists.txt";
                break;
        
            default:
                break;
        }
        try{
                FileWriter fileWriter = new FileWriter(fileName, false);
                fileWriter.close();
                
            }catch (IOException e){
                System.out.println("Error " + e.getMessage());
            }
    }

    /// THÊM NHÂN VIÊN
    public void AddStaff() {
        Scanner sc = new Scanner(System.in);
        int choice;
            System.out.println("Choice Type of Staff");
            System.out.println("1 : Cleaning Staff");
            System.out.println("2 : Security");
            System.out.println("3 : Receptionist");
            System.out.println();
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            ClearScreen();
            switch (choice) {
                case 1:
                    CleaningStaff cl = new CleaningStaff();
                    cl.Input();
                    staffs.add(cl);

                    break;
                case 2:
                    Security c = new Security();
                    c.Input();
                    staffs.add(c);
                    break;
                case 3:
                    Receptionist r= new Receptionist();
                    r.Input();
                    staffs.add(r);
                    break;
                default:
                    System.out.println("Error. Retry pls!!");
                    break;
            }
        WriteToStaff(staffs, true);
    }
    /// TÌM NHÂN VIÊN
    public Staff FindStaff(ArrayList<Staff> staffs){
        Scanner sc= new Scanner(System.in);
        System.out.println("1 : ID");
        System.out.println("2 : Name ");
        System.out.println("3 : Phone Number");
        System.out.println();
        System.out.print("Enter choice: ");
        int choice = sc.nextInt();
        ClearScreen();
        ShowListStaff(staffs);
        System.out.println();
        switch(choice){
            case 1:
                System.out.print("Enter ID : ");
                int idNeedFind = sc.nextInt();
                ClearScreen();
                for(Staff staff : staffs){
                    if(idNeedFind == staff.getId()) {
                        return staff;
                    }
                }
                ClearScreen();
                System.out.println("ID is not Found");
                sc.nextLine();
                sc.nextLine();
                break;
            case 2:
                sc.nextLine();
                System.out.print("Enter Name : ");
                String nameNeedFind = sc.nextLine();
                ClearScreen();
                for(Staff staff:staffs){
                    if(staff.getName().equals(nameNeedFind)) {
                        return staff;
                    }
                }
                System.out.println("Name is not Found");
                sc.nextLine();
                break;
            case 3:
                sc.nextLine();
                System.out.print("Enter Phone Number : ");
                String phoneNumBerNeedFind = sc.nextLine();
                ClearScreen();
                for(Staff staff:staffs){
                    if(staff.getPhoneNumber().equals(phoneNumBerNeedFind)) {
                        return staff;
                    }
                }
                System.out.println("Phone Number is not Found");
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
        if(reChoice == 2)  {

            if(inner == 1) RemoveStaff();
            else if(inner == 2) EditStaffs();
            else if(inner == 3) FindAndShowStaffs();
        }
        return null;
    }
    //Sau khi tim nhân viên , show thông tin
    public void FindAndShowStaffs(){
        inner = 3;
        Scanner sc = new Scanner(System.in);
        System.out.println("---- Find ----");
        ArrayList<Staff> staffs = GetList();
        ClearScreen();
        ShowListStaff(staffs);
        System.out.println();
        System.out.println("----Find By----");
        Staff staff = FindStaff(staffs);
        ClearScreen();
        if(staff == null) return;
        staff.Output();
        sc.nextLine();
    }
    ///XÓA NHÂN VIÊN
    public void RemoveStaff(){
        inner = 1;
        ArrayList<Staff> staffs = new ArrayList<>();
        System.out.println("----Remove List----- ");
        staffs = GetList();
        ClearScreen();
        ShowListStaff(staffs);
        System.out.println();
        System.out.println("----Remove By----- ");
        System.out.println();
        Staff staff = FindStaff(staffs);
        staffs.remove(staff);
        WriteToStaff(staffs, false);
    }

    /// CHỈNH SỬA NHÂN VIÊN

    public void EditStaffs(){
        inner = 2;        ArrayList<Staff> staffs = new ArrayList<>();
        System.out.println("----Edit----- ");
        staffs = GetList();
        ClearScreen();
        ShowListStaff(staffs);
        System.out.println();
        System.out.println("----Edit By----- ");
        Staff st = FindStaff(staffs);

        if(st instanceof  Security){
            st.EditStaff();
        }else if(st instanceof CleaningStaff){
            st.EditStaff();
        }
        else if(st instanceof Receptionist){
            st.EditStaff();
        }

        WriteToStaff(staffs, false);
    }


    /// XUẤT DANH SÁCH NHÂN VIÊN
    public  void OutputStaffs(ArrayList<Staff> staffs) {
        for (Staff st : staffs) {
            if (st instanceof Security) {
                Security s =(Security)st ;
                s.Output();
            } else if (st instanceof Receptionist) {
                Receptionist r =(Receptionist)st;
                r.Output();
            } else if(st instanceof  CleaningStaff){
                CleaningStaff c =(CleaningStaff)st;
                c.Output();
            }
        }
    }



    public static void main(String[] args){

        ListStaff l = new ListStaff();
        l.StaffMenu();

    }

    /// MENU NHÂN VIÊN
    public void StaffMenu(){
        int roomChoice = 0;
        do{
            ClearScreen();
            Scanner sc = new Scanner(System.in);
            ShowAllListStaff();
            System.out.println();
            System.out.println("1 : Add Staff");
            System.out.println("2 : Delete Staff");
            System.out.println("3 : Edit Staff");
            System.out.println("4 : Find Staff");
            System.out.println("5 : Return");
            System.out.println();
            System.out.print("Please Input Your Choice : ");
            roomChoice = sc.nextInt();
            switch (roomChoice) {
                case 1:
                    ClearScreen();
                    AddStaff();
                    break;
                case 2:
                    ClearScreen();
                    RemoveStaff();
                    break;
                case 3 :
                    ClearScreen();
                    EditStaffs();
                    break;
                case 4:
                    ClearScreen();
                    FindAndShowStaffs();
                    break;
                default:
                    break;
            }
            sc.nextLine();
        }while(roomChoice != 5);
    }

}

