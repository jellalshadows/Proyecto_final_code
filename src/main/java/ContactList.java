import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class ContactList {
    static private ArrayList<Contact> contactList=new ArrayList<>();
    /*la expresion regular de abajo se utiliza para comprobar que existe un punto y un arroba en la expresion proporcionada y que ademas tanto antes
    * del @ como despues y luego del punto, existe al menos una o mas letras mayusculas o minusculas y que pueden haber numeros dentro de la expresion,
    * la doble barra inclinada se usa pq el punto tiene un significado especifico en las expresiones regulares, por lo tanto se escapa con una barra pero
    * la barra tambien tiene otro significado y hay que escapar tambien asique se usa otra barra y ya deberia estar bien */
    static final String EMAIL_REGEX="^[a-z0-9A-Z]+@[a-zA-Z]+\\.[a-zA-Z]+$";
    static final Pattern pattern = Pattern.compile(EMAIL_REGEX);

    static Scanner src=new Scanner(System.in);
    static void AddContacts(){

        System.out.println("Name:");
        String name;
        name=src.nextLine();
        System.out.println("e-mail:");
        String email = src.next();
        if (isValidEmail(email)){
            System.out.println("Phone number:");
            String phoneNumber;
            phoneNumber=src.next();
            contactList.add(new Contact(name,email,phoneNumber));
        }else{
            while (isValidEmail(email)==false){
                System.out.println("email invalido, vuelva a intentar:");
                email= src.next();
            }
        }



    }

    static void ShowContacts(){
        for (int i = 0; i < contactList.size(); i++) {
            System.out.println(contactList.get(i));
        }
    }

    public static boolean isValidEmail(String email) {
        Matcher matcher = pattern.matcher(email);
        boolean a = matcher.matches();
        return matcher.matches();
    }
}
