package mx.com.gm.web;

//Para que Spring reconosca la clase se debe poner esta anotacion
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import mx.com.gm.domain.Persona;
import mx.com.gm.servicio.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

//controlador de tipo MVC
@Controller
//Esto permite mandar informacion a la consola como el log4j
@Slf4j
public class ControladorInicio {

    //para inyectar la dependencia se hace con la anotacion @Autowired
    @Autowired
    private PersonaService personaService;

    @GetMapping("/")
    //se puede saber quien hizo la autenticacion con @AuthenticationPrincipal
    public String inicio(Model model, @AuthenticationPrincipal User user) {
        var personas = personaService.listarPersonas();
        log.info("Ejecutando el controlador Spring MVC");
        log.info("usuario que hizo login:" + user);
        model.addAttribute("personas", personas);
        var saldoTotal = 0D;
        for (var p : personas) {
            saldoTotal += p.getSaldo();
        }
        model.addAttribute("saldoTotal",saldoTotal);
        model.addAttribute("totalClientes", personas.size());
        return "index";
    }

    @GetMapping("/agregar")
    //al definir Persona como argumento de la funcion Spring inyecta la funcion
    //Spring automaticamente crea un un nuevo objeto de la clase Persona en caso de no haber uno
    public String agregar(Persona persona) {
        return "modificar";
    }

    @PostMapping("/guardar")
    /*para recuperar los errores se debe indicar que el objeto se va a validar 
    usando una anotacion @Valid y luego usar argumento Errors para saber si hay 
    errores ademas estos dos atributos deben ir juntos*/
    public String guardar(@Valid Persona persona, Errors errores) {
        if (errores.hasErrors()) {
            return "modificar";
        }
        personaService.guardar(persona);
        //redireccionada a la pagina de inicio
        return "redirect:/";
    }

    /*para que se pueda asociar el path variable con el metodo es necesario poner
    el nombre del atributo de la clase como la variable */
    @GetMapping("/editar/{idPersona}")
    /*como el parametro en el mapeo se llama idPersona que es igual al atributo
    de la clase Persona que ingresa en el metodo Spring automaticamente busca una
    persona con ese id y hace la peticion*/
    public String editar(Persona persona, Model model) {
        persona = personaService.econtrarPersona(persona);
        model.addAttribute("persona", persona);
        return "modificar";
    }

    /*Debido que thymeleaf mando el id como un queryparam Spring lo reconoce 
    como el id del elemento Persona que debe eliminar*/
    @GetMapping("/eliminar")
    /*como el parametro en el mapeo se llama idPersona que es igual al atributo
    de la clase Persona que ingresa en el metodo Spring automaticamente busca una
    persona con ese id y hace la peticion*/
    public String eliminar(Persona persona) {
        personaService.eliminar(persona);
        return "redirect:/";
    }
}
