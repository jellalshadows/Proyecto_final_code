import java.io.Serializable;
import java.util.Objects;

public class Contact implements Serializable {
    private String name;
    private String email;
    private String phone_number;

    public Contact(String name, String email, String phone_number) {
        this.name = name;
        this.email = email;
        this.phone_number = phone_number;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    @Override
    public String toString() {
        String salida=String.format(" name: %s \n email: %s \n phone number: %s \n\n", name, email,phone_number);
        return salida;
    }
}
