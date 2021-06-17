/*
Tener encuenta que las clases deben estar en el mismo paquete donde esta HolaSpring2Application.java
o Spring no podra encontrar las clases a ejecutar, tambien puede estar en sub paquetes del mismo como
en este caso mx.com.gm es el paquete y mx.com.gm.domain es el subpaquete
 */
package mx.com.gm.domain;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;

/*Con la utilizacion de lombok no es necesario agregar los gets y sets, toString, equals y hashcode
que se agregan en los pojos o beans de java normalmente. Para esto agregamos anotacion Data*/
@Data
//Se vuelve en una clase de entidad para hibernate 
@Entity
@Table(name = "persona")
public class Persona implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPersona;

    //se validan los campos y se indica con anotaciones que no pueden ser nulos
    //@NotEmpty(message = "Este no es un nombre valido")
    @NotEmpty
    private String nombre;
    
    @NotEmpty
    private String apellido;
    
    @NotEmpty
    //@Email(message = "Este no es un email valido")
    //@Size(min = 13, max = 20, message = "El correo debe tener entre 13 y 20 caracteres")
    @Email
    @Size(min=13,max=20)
    private String email;
    
    private String telefono;
    
    @NotNull
    private Double saldo;
}
