import java.util.ArrayList;
import java.util.Scanner;

public class ContactList {
    static private ArrayList<Contact> contactList=new ArrayList<>();
    static Scanner src=new Scanner(System.in);
    static void AddContacts(){
        System.out.println("Name:");
        String name;
        name=src.next();
        System.out.println("e-mail:");
        String email;
        email=src.next();
        System.out.println("Phone number:");
        String phoneNumber;
        phoneNumber=src.next();
        contactList.add(new Contact(name,email,phoneNumber));
    }

    static void ShowContacts(){
        for (int i = 0; i < contactList.size(); i++) {
            System.out.println(contactList.get(i));
        }
    }
}
