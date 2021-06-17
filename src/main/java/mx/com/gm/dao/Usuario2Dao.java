package mx.com.gm.dao;

import mx.com.gm.domain.Usuario2;
import org.springframework.data.jpa.repository.JpaRepository;


public interface Usuario2Dao extends JpaRepository<Usuario2, Long> {
    /*Para empezar a utilizar la seguridad de Spring es necesario crear el siguiente
    metodo ya que lo pide el framework*/
    Usuario2 findByUsername(String username);
}
