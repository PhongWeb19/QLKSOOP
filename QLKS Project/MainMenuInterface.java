import java.util.Scanner;



public class MainMenuInterface{
    private static int choice = 0;
    private static int choiceMax = 5;

    public static void main(String[] args){
        MainMenu();
    }
    
    private static void ClearScreen(){
        System.out.print("\033[H\033[2J");
        System.out.flush();
        // for(int i =0; i< 50;i++){
        //     System.out.println();
        // }
    }
    
    public static void MainMenu(){
        while(choice != 1){
            switch (choice){
                case 0 : 
                    Menu();
                    break;
                case 1: 
                    break;
                case 2:
                    ListStaff ls = new ListStaff();
                    ls.StaffMenu();
                    choice = 0;
                    break;
                case 3:
                    ListRoom lr = new ListRoom();
                    lr.RoomMenu();
                    choice = 0;
                    break;
                case 4:
                    ListCustomer lc = new ListCustomer();
                    lc.CustomerMenu();
                    choice = 0;
                    break;
                case 5:
                    ListBooking lb = new ListBooking();
                    lb.BookingMenu();
                    choice = 0;
                    break;
                default:
                    break;
            }
        }
    }

    private static void Menu(){
        Scanner sc = new Scanner(System.in);
        int isChoose = 0;
        do{
            ClearScreen();
            isChoose = 1;
            System.out.println("+------------------------------+");
            System.out.println("| 1 : Exit                     |");
            System.out.println("| 2 : Staff Manager            |");
            System.out.println("| 3 : Room Manager             |");
            System.out.println("| 4 : Customer Manager         |");
            System.out.println("| 5 : Booking Manager          |");
            System.out.println("+------------------------------+");
            System.out.println();
            System.out.print("Please Input Your Choice: ");
            choice = sc.nextInt();
            if(choice > choiceMax || choice <= 0){
                isChoose = 0;
                System.out.println("Error");
                sc.nextLine();
            }
        }while(isChoose == 0);
    }

}
