package mx.com.gm.web;

import java.util.Locale;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

//anotacion para que se reconozca todos los cambios en la clase
@Configuration
//es necesario configurar un listener para manejar el cambio de idiomas dentro de la app
public class WebConfig implements WebMvcConfigurer {

    //cuando se manda a llamar este Bean se agrega al contexto de Spring
    @Bean
    //se define el lenguage por defecto
    public LocaleResolver localeResolver() {
        var slr = new SessionLocaleResolver();
        slr.setDefaultLocale(new Locale("es"));
        return slr;
    }

    //se configira el cambio de idiomas
    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        var lci = new LocaleChangeInterceptor();
        //se especifica el parametro que indica el cambio de lenguage
        lci.setParamName("lang");
        return lci;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registro) {
        registro.addInterceptor(localeChangeInterceptor());
    }

    //se debe configurar el registro de los usuarios ya que las otras funciones
    //bloquearon el acceso a la app y por tanto no carga nada y solo apareceria un error
    @Override
    public void addViewControllers(ViewControllerRegistry registro){
        //setViewName le pone un nombre a esa vista que es index en este caso
        registro.addViewController("/").setViewName("index");
        registro.addViewController("/login");
        registro.addViewController("/errores/403").setViewName("/errores/403");
    }
}
