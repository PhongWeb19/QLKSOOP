import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Customer extends Person{
    public Customer()
    {
        super();
    }
    public Customer(int id, String name, Date doB, String gender, String address, String email, String phoneNumber)
    {
        super(id, name, doB, gender, address, email, phoneNumber);
    }


    public int getId() {
        return super.getId();
    }

    public void setId(int id) {
        super.setId(id);
    }

    public String getName() {
        return super.getName();
    }

    public void setName(String name) {
        super.setName(name);
    }

    public Date getDoB() {
        return super.getDoB();
    } 
    
    public String getDoBstr(){
        return super.getDoBstr();
    }

    public void setDoB(Date doB) {
        super.setDoB(doB);
    }

    public String getGender() {
        return super.getGender();
    }

    public void setGender(String gender) {
        super.setGender(gender);
    }

    public String getAddress() {
        return super.getAddress();
    }

    public void setAddress(String address) {
        super.setAddress(address);
    }

    public String getEmail() {
        return super.getEmail();
    }

    public void setEmail(String email) {
        super.setEmail(email);
    }

    public String getPhoneNumber() {
        return super.getPhoneNumber();
    }

    public void setPhoneNumber(String phoneNumber) {
        super.setPhoneNumber(phoneNumber);
    }

    public void Input(){
        super.Input();
    }

    public void Output(){
        super.Output();
    }

}
