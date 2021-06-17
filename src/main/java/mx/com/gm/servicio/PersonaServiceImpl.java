package mx.com.gm.servicio;

import java.util.List;
import mx.com.gm.dao.PersonaDao;
import mx.com.gm.domain.Persona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/*La capa de servicio podria usar otras tablas o varios objetos del tipo DAO 
entonces los metodos deben ser transaccionales ya que en caso de error no debe 
guardar info en las tablas afectadas*/

/*Debido a que la capa de datos se encarga de hacer el commit o el rollback
    de forma automatica entonces se debe indicar esta configuracion en la capa de
    servicio*/
 

 /*con la anotacion de Service Spring reconoce la clase como de servicio lo cual 
permite la inyeccion dentro del controlador*/
@Service
public class PersonaServiceImpl implements PersonaService {

    @Autowired
    private PersonaDao personaDao;

    @Override
    /*Como solo se lee informacion se usa Transactional de forma de solo lectura
    es decir pregunta si es necesario comenzar una transaccion y la inicia
    ya que es solo lectura no deberia de hacerlo ya que no modificara datos de la bd*/
    @Transactional(readOnly = true)
    public List<Persona> listarPersonas() {
        return (List<Persona>) personaDao.findAll();
    }

    @Override
    @Transactional
    public void guardar(Persona persona) {
        personaDao.save(persona);
    }

    @Override
    @Transactional
    public void eliminar(Persona persona) {
        personaDao.delete(persona);
    }

    @Override
    @Transactional(readOnly = true)
    public Persona econtrarPersona(Persona persona) {
        //para este caso busca por id o si no regresa null (orElse)
        return personaDao.findById(persona.getIdPersona()).orElse(null);
    }

}
