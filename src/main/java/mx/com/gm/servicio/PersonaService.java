package mx.com.gm.servicio;

import java.util.List;
import mx.com.gm.domain.Persona;
/*Esta es la capa de servicio la cual permite que no se acceda directamente a la
capa de datos y permite el manejo de clientes*/
public interface PersonaService {

    public List<Persona> listarPersonas();
    public void guardar(Persona persona);
    public void eliminar(Persona persona);
    public Persona econtrarPersona(Persona persona);
}
