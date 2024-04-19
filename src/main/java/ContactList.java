import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class ContactList implements Serializable {
    static private ArrayList<Contact> contactList=new ArrayList<>();
    /*la expresion regular de abajo se utiliza para comprobar que existe un punto y un arroba en la expresion proporcionada y que ademas tanto antes
    * del @ como despues y luego del punto, existe al menos una o mas letras mayusculas o minusculas y que pueden haber numeros dentro de la expresion,
    * la doble barra inclinada se usa pq el punto tiene un significado especifico en las expresiones regulares, por lo tanto se escapa con una barra pero
    * la barra tambien tiene otro significado y hay que escapar tambien asique se usa otra barra y ya deberia estar bien */
    static final String EMAIL_REGEX="^[a-z0-9A-Z.]+@[a-zA-Z]+\\.[a-zA-Z]+$";
    static final Pattern pattern = Pattern.compile(EMAIL_REGEX);

    static Scanner read =new Scanner(System.in);
    static void AddContacts(){
        System.out.println("Name:");
        String name;
        name= read.nextLine();
        System.out.println("e-mail:");
        String email = read.nextLine();

        while (!isValidEmail(email)){
            System.out.println("email invalido, vuelva a intentar:");
            email= read.nextLine();
        }
            System.out.println("Phone number:");
            String phoneNumber;
            phoneNumber= read.nextLine();
            contactList.add(new Contact(name,email,phoneNumber));
            Serialize();
    }

    static void ShowContacts(){
        for (int i = 0; i < contactList.size(); i++) {
            System.out.println(contactList.get(i));
        }
    }

    static void SearchContacts(){
        System.out.println("Type the name of the contact you want to search for:");
        String searchContact= read.nextLine();

        for (int i = 0; i < contactList.size(); i++) {
            if (searchContact.equals(contactList.get(i).getName())){
                System.out.println(contactList.get(i));
            }
        }
    }

    public static boolean isValidEmail(String email) {
        Matcher matcher = pattern.matcher(email);
        boolean a = matcher.matches();
        return matcher.matches();
    }

    public static void Serialize(){
        try{
            FileOutputStream fileOutput=new FileOutputStream("ContactList.Data");
            ObjectOutputStream objectOutput=new ObjectOutputStream(fileOutput);
            objectOutput.writeObject(contactList);
            objectOutput.close();
            fileOutput.close();
        }catch (FileNotFoundException e){
            System.out.println("File not found");
        }catch (IOException e){
            System.out.println("there was error");
        }

    }

    public static void Deserialize(){
        try {
            FileInputStream fileInput=new FileInputStream("ContactList.Data");
            ObjectInputStream objectInput=new ObjectInputStream(fileInput);
            contactList = (ArrayList<Contact>) objectInput.readObject();
        }catch (FileNotFoundException e){
            System.out.println("No data in database");
        }catch (IOException e){
            System.out.println("there was error");
        }catch (ClassNotFoundException e){
            System.out.println("Class not found");
        }
    }

    //public static void ModifyContacts(){

    //}
}
