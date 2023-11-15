import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

public abstract class Staff extends Person {
    public Staff() {
        super();
    }

    public Staff(int id, String name, Date doB, String gender, String address, String email, String phoneNumber) {
        super(id, name, doB, gender, address, email, phoneNumber);
    }

    public void Input(){
        super.Input();
    }
    public void Output(){
        super.Output();
    }
    public abstract double PayRoll();
    public abstract void EditStaff();
}
