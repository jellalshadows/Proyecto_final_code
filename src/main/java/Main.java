import com.sun.jdi.connect.Connector;

import java.io.Serializable;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main implements Serializable {
    static Scanner src=new Scanner(System.in);

    /**
     * Metodo que le brinda al usuario un menu que contiene opciones como "añadir nuevo contacto", "mostrar los contactos de la lista",
     * "buscar contacto", "eliminar contacto", "eliminar todos los contactos de la lista", "modificar contacto", "copa de seguridad",
     * "eliminar un contacto" o salir del programa. Este metodo llama a los diferentes metodos que corresponden a cada una de las opciones
     * brindadas por el menu, dichos metodos se encuentran comentado en su respectiva clase "ContactList".
     *
     * @param args El parámetro 'args' representa los argumentos de línea de comandos pasados al programa.
     * En este método, podemos acceder a estos argumentos a través del arreglo 'args'. Por ejemplo, si ejecutamos el programa con
     * "java MiClase argumento1 argumento2", 'args' contendrá {"argumento1", "argumento2"}.
     */
    public static void main(String[] args) {
        int option;
        ContactList.Deserialize();
            try {
                do{

                    System.out.println("What do you want to do?:");
                    System.out.println("1.Add new contact");
                    System.out.println("2.Show contacts list");
                    System.out.println("3.Search contact");
                    System.out.println("4.Delete all contact");
                    System.out.println("5.Modify contact");
                    System.out.println("6.Backup");
                    System.out.println("7.Delete contact");
                    System.out.println("8.Exit");
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
                        case 4:
                            ContactList.ClearDataBase();
                            break;
                        case 5:
                            ContactList.ModifyContacts();
                            break;
                        case 6:
                            ContactList.BackupDataBase();
                            break;
                        case 7:
                            ContactList.DeleteContact();
                            break;
                        default:
                            break;
                    }
                }while(option<8);
            }catch (InputMismatchException e){
                System.out.println("Just digit accepted");
            }
    }
}
