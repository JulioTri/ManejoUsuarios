package mx.com.gm.dao;

import mx.com.gm.domain.Persona;
import org.springframework.data.jpa.repository.JpaRepository;



/*Se utiliza JpaRepository que trae mas ventajas que CrudRepository*/
public interface PersonaDao extends JpaRepository<Persona, Long>{
    /*debido a que se extiende de esa clase no es necesario implementar ninguno
    de los metodos mas usados en caso de necesitar uno nuevo se puede agregar aca*/
}
