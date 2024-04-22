import java.io.Serializable;
/**
 * Representa un contacto con un nombre, correo electrónico y número de teléfono.
 * Implementa Serializable para admitir la serialización de objetos.
 */
public class Contact implements Serializable {
    private String name;
    private String email;
    private String phone_number;
    /**
     * Construye un objeto Contact con el nombre, correo electrónico y número de teléfono especificados.
     * @param name El nombre del contacto.
     * @param email La dirección de correo electrónico del contacto.
     * @param phone_number El número de teléfono del contacto.
     */
    public Contact(String name, String email, String phone_number) {
        this.name = name;
        this.email = email;
        this.phone_number = phone_number;
    }
    /**
     * Obtiene el nombre del contacto.
     * @return El nombre del contacto.
     */
    public String getName() {
        return name;
    }
    /**
     * Obtiene la dirección de correo electrónico del contacto.
     * @return La dirección de correo electrónico del contacto.
     */
    public String getEmail() {
        return email;
    }
    /**
     * Obtiene el número de teléfono del contacto.
     * @return El número de teléfono del contacto.
     */
    public String getPhone_number() {
        return phone_number;
    }
     /**
     * Establece el nombre del contacto.
     *@param name El nuevo nombre del contacto.
      */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * Establece la dirección de correo electrónico del contacto.
     * @param email La nueva dirección de correo electrónico del contacto.
     */
    public void setEmail(String email) {
        this.email = email;
    }
    /**
     * Establece el número de teléfono del contacto.
     * @param phone_number El nuevo número de teléfono del contacto.
     */
    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }
    /**
     * Devuelve una representación en forma de cadena del contacto.
     * @return Una representación en forma de cadena del contacto, incluyendo nombre, correo electrónico y número de teléfono.
     */
    @Override
    public String toString() {
        String salida=String.format(" name: %s \n email: %s \n phone number: %s \n\n", name, email,phone_number);
        return salida;
    }
}
