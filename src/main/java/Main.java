import java.util.Scanner;

public class Main {
    static Scanner src=new Scanner(System.in);
    public static void main(String[] args) {
        int option;
        System.out.println("What do you want to do?:");
        System.out.println("1.Add new contact");
        System.out.println("2.Show contacts list");
        System.out.println("3.exit");
        option=src.nextInt();

        switch (option){
            case 1:
                ContactList.AddContacts();
                break;
            default:
                break;
        }
    }
}
