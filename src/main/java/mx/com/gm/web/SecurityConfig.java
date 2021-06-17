package mx.com.gm.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.*;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

//se agregan estas anotaciones para la configuracion de la seguridad en la app
@Configuration 
@EnableWebSecurity 
//se extiende de la clase WebSecurityConfigurerAdapter para sobre escribir algunos metodos
public class SecurityConfig extends WebSecurityConfigurerAdapter{
    
    /*Se utiliza el objeto userDetailsService para acceder a Usuario2Service*/
    @Autowired
    private UserDetailsService userDetailsService;
    
    /*inyecta este metodo en fabrica de Spring para que pueda ser usado*/
    @Bean 
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    
    /*Se utiliza u metodo que indica que se usa el userDetailsService y el passwordEncoder
    y se le pasa un argumento de forma automatica al metodo, el UserDetailsService
    se definio en la anotacion Service en Usuario2Service.java*/
    @Autowired
    public void configurerGlobal(AuthenticationManagerBuilder build) throws Exception{
        build.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }
    
    //este metodo permite restringir las urls en otras palabras restringir las paginas y los usuarios
    @Override
    protected void configure(HttpSecurity http) throws Exception{
    
        http.authorizeRequests()
                .antMatchers("/editar/**","/agregar/**","/eliminar")
                    .hasRole("ADMIN")
                .antMatchers("/")
                    .hasAnyRole("USER","ADMIN")
                .and()
                    .formLogin()
                    .loginPage("/login")
                .and()
                    .exceptionHandling().accessDeniedPage("/errores/403")
                ;
    }
}
