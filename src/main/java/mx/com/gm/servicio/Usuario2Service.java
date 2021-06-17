package mx.com.gm.servicio;

import java.util.ArrayList;
import lombok.extern.slf4j.Slf4j;
import mx.com.gm.dao.Usuario2Dao;
import mx.com.gm.domain.Rol;
import mx.com.gm.domain.Usuario2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/*Esta es una clase de servicio que usa Spring Security*/
/*Esta clase es la que busca los usuarios y las claves de igualforma busca los 
roles para el login*/
@Service("userDetailsService")
@Slf4j
public class Usuario2Service implements UserDetailsService{

    @Autowired
    private Usuario2Dao usuario2Dao;
    
    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario2 usuario2 = usuario2Dao.findByUsername(username);
        
        if (usuario2 == null) {
            throw new UsernameNotFoundException(username);
        }
        var roles = new ArrayList<GrantedAuthority>();
        
        for (Rol rol : usuario2.getRoles()) {
            roles.add(new SimpleGrantedAuthority(rol.getNombre()));
        }
        return new User(usuario2.getUsername(),usuario2.getPassword(),roles);
    }
    
}
