import java.io.Serializable;
import java.util.Scanner;

public class Main implements Serializable {
    static Scanner src=new Scanner(System.in);
    public static void main(String[] args) {
        int option;
        ContactList.Deserialize();
        do{
            System.out.println("What do you want to do?:");
            System.out.println("1.Add new contact");
            System.out.println("2.Show contacts list");
            System.out.println("3.Search contact");
            System.out.println("4.exit");
            option=src.nextInt();

            switch (option){
                case 1:
                    ContactList.AddContacts();
                    break;
                case 2:
                    ContactList.ShowContacts();
                    break;
                case 3:
                    ContactList.SearchContacts();
                    break;
                default:
                    break;
            }
        }while(option<3);

    }
}
