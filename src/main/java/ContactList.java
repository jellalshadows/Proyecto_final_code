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

    /**
     * Metodo vacio para añadir nuevos contactos a la lista, contiene las variables "name", "email" y "phoneNumber"
     * donde se insertaran los datos de los nuevos contactos
     */
    static void AddContacts(){
        String phoneNumber;
        String name;
        String email;

        System.out.println("Name:");
        name= read.nextLine();
        System.out.println("e-mail:");
        email = read.nextLine();

        while (!isValidEmail(email)){
            System.out.println("email invalido, vuelva a intentar:");
            email= read.nextLine();
        }
            System.out.println("Phone number:");
            phoneNumber= read.nextLine();

        for (int i = 0; i < contactList.size(); i++) {
           while (phoneNumber.equals(contactList.get(i).getPhone_number())){
               System.out.println("That phone number already exists, wirte another:");
               phoneNumber= read.nextLine();
            }
        }
            contactList.add(new Contact(name,email,phoneNumber));

            Serialize();
    }

    /**
     * Metodo vacio para eliminar contactos, el metodo contiene la variable phoneToDelete, a travez del cual se comparara si coincide,
     * en caso de coincidir, elimina el contacto de la lista
     */
    static void DeleteContact(){
        String phoneToDelete;
        System.out.println("Write the phone number that you want to delete:");
        phoneToDelete=read.nextLine();

        for (int i = 0; i < contactList.size(); i++) {
            if (phoneToDelete.equals(contactList.get(i).getPhone_number())){
                contactList.remove(i);
                i--;
            }else if (contactList.size()-1==i){
                System.out.println("el numero no existe");
            }
        }
        Serialize();
    }

    /**
     * Metodo para mostrar los contactos ubicados en la lista de contactos
     */
    static void ShowContacts(){
        for (int i = 0; i < contactList.size(); i++) {
            System.out.println(contactList.get(i));
        }
    }

    /**
     * Metodo para buscar un contacto en la lista, contiene una variable llamada "searchContact" que contendra el nombre del contacto que se quiere buscar
     * si dicho nombre coincide con el nombre de alguno de los contactos contenido en el array, lo muestra.
     */
    static void SearchContacts(){
        System.out.println("Type the name of the contact you want to search for:");
        String searchContact= read.nextLine();

        for (int i = 0; i < contactList.size(); i++) {
            if (searchContact.equals(contactList.get(i).getName())){
                System.out.println(contactList.get(i));
                break;
            } else if (i==contactList.size()-1) {
                System.out.println("The name does not exist");
            }

        }
    }

    /**
     * Metodo para validar el email que se quiera introducir a un contacto, a travez de una expresion regular que dira
     * que caracteres son permitidos en el email
     * @param email el email que se quiera validar
     * @return false si no cumple con la expresion regular
     * @return true si cumple con la expresion regular
     */
    public static boolean isValidEmail(String email) {
        Matcher matcher = pattern.matcher(email);
        boolean a = matcher.matches();
        return matcher.matches();
    }

    /**
     * Metodo que guarda en un archivo llamado "ContactList.Data" la lista de contacto para que no se pierda cada vez
     * que salgamos y entremos en el programa, contiene un objeto de tipo "FileOutputStream" que contendra la direccion
     * a donde queremos enviar el archivo incluido el nombre del archivo y su extension, el metodo en cuestion escribe
     * en el fichero el arraylist "contactList".
     */
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

    /**
     * Metodo que carga el contenido del archivo especificado en el obgeto de tipo "FileInputStream",
     * introduce los datos del archivo especificado que contiene un arraylist en el arraylist "contactList" del programa
     */
    public static void Deserialize(){
        try {
            FileInputStream fileInput=new FileInputStream("ContactList.Data");
            ObjectInputStream objectInput=new ObjectInputStream(fileInput);
            contactList = (ArrayList<Contact>) objectInput.readObject();
        }catch (FileNotFoundException e){
            System.out.println("No data in database");
        }catch (IOException e){
            System.out.println(e.getMessage());
            System.out.println("there was error");
        }catch (ClassNotFoundException e){
            System.out.println("Class not found");
        }
    }

    /**
     * Metodo que modifica el contacto especificado a travez de la variable "phone_number" que contendra un numero de
     * telefono especificado por el usuario, en caso de que coincida con algun numero de telefono contenido en la lista
     * de contactos, procedera a mostrar un menu con las diferentes opciones que podra modificar; email, nombre y numero
     * de telefono, en caso de que el bucle que recorre la lista, llegue al final y no halla encontrado ninguna coincidencia
     * saltara un mensaje especificando que ese numero de telefono no existe en la lista
     */
    public static void ModifyContacts(){
        int option;
        System.out.println("Write the phone number of the contact that you want to modify:");
        String phone_number= read.nextLine();

        for (int i = 0; i < contactList.size(); i++) {
            if (contactList.get(i).getPhone_number().equals(phone_number)){
                do {
                    System.out.println("What do you want to modify?:");
                    System.out.println("1.Name");
                    System.out.println("2.Email");
                    System.out.println("3.Phone Number");
                    System.out.println("4.exit");
                    option= read.nextInt();
                    read.nextLine();
                    switch (option){
                        case 1:
                            ModifyName(i);
                            break;
                        case 2:
                            ModifyEmail(i);
                            break;
                        case 3:
                            ModifyPhoneNumber(i);
                            break;
                        default:
                            break;
                    }
                }while (option<4);
                break;
            } else if (i== contactList.size()-1) {
                System.out.println("that phone number doesn´t exists");
            }


        }
        Serialize();
    }

    /**
     * Metodo que permite modificar el numero de telefono, este metodo se encuentra inicializado en el metodo anterior, contiene
     * una variable llamada "newPhoneNumber" que contendra el nuevo numero de telefono que sera insertado en la propiedad "PhoneNumber"
     * del obgeto que halla coincidido con la comprobacion realizada en el metodo "ModifyContacts".
     * @param i aqui estara la variable i del bucle for del metodo "ModifyContacts" que itera a travez del arraylist "contactList".
     */
    public static void ModifyPhoneNumber(int i){
        String newPhoneNumber;
        System.out.println("Write the new phone number:");
        newPhoneNumber= read.nextLine();
        for (int j = 0; j < contactList.size(); j++) {
            while (newPhoneNumber.equals(contactList.get(j).getPhone_number())){
                System.out.println("The phone number is the same or already exists, write another:");
                newPhoneNumber= read.nextLine();
            }
        }
        contactList.get(i).setPhone_number(newPhoneNumber);
    }

    /**
     * Metodo que permite modificar el email, este metodo se encuentra inicializado en el metodo "ModifyContact", contiene
     * una variable llamada "newEmail" que contendra el nuevo correo electronico que sera insertado en la propiedad "Email"
     * del obgeto que halla coincidido con la comprobacion realizada en el metodo "ModifyContacts".
     * @param i aqui estara la variable i del bucle for del metodo "ModifyContacts" que itera a travez del arraylist "contactList".
     */
    public static void ModifyEmail(int i){
        String newEmail;
        System.out.println("Write the new email:");
        newEmail= read.nextLine();
        while (!isValidEmail(newEmail)){
            System.out.println("email invalido, vuelva a intentar:");
            newEmail= read.nextLine();
        }
        contactList.get(i).setEmail(newEmail);
    }
    /**
     * Metodo que permite modificar el nombre, este metodo se encuentra inicializado en el metodo "ModifyContact", contiene
     * una variable llamada "newName" que contendra el nuevo nombre que será insertado en la propiedad "Name"
     * del obgeto que halla coincidido con la comprobacion realizada en el metodo "ModifyContacts".
     * @param i aqui estara la variable i del bucle for del metodo "ModifyContacts" que itera a travez del arraylist "contactList".
     */
    public static void ModifyName(int i){
        String newName;
        System.out.println("Write the new name:");
        newName= read.nextLine();
        contactList.get(i).setName(newName);
    }

    /**
     * Metodo que elimina por completo todos los contactos que esten contenidos en el arraylist "contactList"
     */
    public static void ClearDataBase(){
        contactList.clear();
    }

    /**
     * Metodo que, a travez de un menu, le brinda al usuario la posibilidad de crear y cargar una copia de seguridad, este metodo
     * tiene metodos inicializados que seran explicados mas abajo. Contiene la variable "Option_backup" donde el usuario escribirá
     * la opcion que le sea conveniente.
     */
    public static void BackupDataBase(){
        int option_backup;

        do {
            System.out.println("What do you want to do?:");
            System.out.println("1.Create Backup");
            System.out.println("2.Charge Backup");
            System.out.println("3.exit");
            option_backup= read.nextInt();
            read.nextLine();
            switch (option_backup){
                case 1:
                    CreateBackup();
                    break;
                case 2:
                    ChargeBackup();
                    break;
                default:
                    break;
            }
        }while(option_backup<2);
    }

    /**
     * Metodo que guarda en un archivo llamado "Backup.Data" una copia de seguridad del programa
     * el metodo en cuestion escribe
     * en el fichero el arraylist "contactList".
     */
    public static void CreateBackup(){

        try{
            FileOutputStream backupFile=new FileOutputStream("Backup\\Backup.Data");
            ObjectOutputStream backupobject=new ObjectOutputStream(backupFile);
            backupobject.writeObject(contactList);
        }catch (FileNotFoundException e){
            System.out.println("File not found");
        }catch (IOException e){
            System.out.println(e.getMessage());
        }


    }
    /**
     * Metodo que carga la ultima copia de seguridad generada, introduce los datos del archivo especificado que contiene un arraylist
     * en el arraylist "contactList" del programa
     */
    public static void ChargeBackup(){
        try {
            FileInputStream backupFile=new FileInputStream("Backup\\Backup.Data");
            ObjectInputStream backupobject=new ObjectInputStream(backupFile);
            contactList = (ArrayList<Contact>) backupobject.readObject();
        }catch (FileNotFoundException e){
            System.out.println("No data in database");
        }catch (IOException e){
            System.out.println(e.getMessage());
            System.out.println("there was error");
        }catch (ClassNotFoundException e){
            System.out.println("Class not found");
        }
        Serialize();
    }

}
