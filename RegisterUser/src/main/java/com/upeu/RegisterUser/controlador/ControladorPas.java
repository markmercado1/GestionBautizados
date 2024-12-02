package com.upeu.RegisterUser.controlador;

import java.util.List;
import java.util.Optional;

import com.upeu.RegisterUser.interfaceService.IpastorService;
import com.upeu.RegisterUser.modelo.Pastores;
import com.upeu.RegisterUser.modelo.Persona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model; // Importación correcta de Model
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping; // Añadido para el manejo de POST
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class ControladorPas {

    @Autowired
    private IpastorService service1;

    // Listar pasores
    @GetMapping("/listarpas")
    public String listar(Model model) {
        List<Pastores> pastores = service1.listar();
        model.addAttribute("pastores", pastores);
        return "Indexpastores"; // Asegúrate de que el nombre coincida con la vista Thymeleaf
    }

    @GetMapping("/newpas")
    public String agregar(Model model) {
        model.addAttribute("pastores", new Pastores());
        return "formpas"; // Asegúrate de que este archivo exista en templates
    }

    // Guardar pastor
    @PostMapping("/savepas")
    public String savepas(@Validated Pastores p, Model model) {
            // Validación de campos
            if (p.getNombre() == null || p.getApellido() == null || p.getEdad() == 0) {
                model.addAttribute("error", "Debe llenar todos los campos correctamente.");
                return "formpas";
            }

            // Verifica si es una actualización o creación
            if (p.getId() != 0) {
                Optional<Pastores> existingPastores = service1.listarId(p.getId());
                if (existingPastores.isPresent()) {
                    Pastores pastoresToUpdate = existingPastores.get();
                    pastoresToUpdate.setNombre(p.getNombre());
                    pastoresToUpdate.setApellido(p.getApellido());
                    pastoresToUpdate.setEdad(p.getEdad());
                    pastoresToUpdate.setCelular(p.getCelular());
                    pastoresToUpdate.setCargo(p.getCargo());
                    service1.save(pastoresToUpdate);
                }
            } else {
                service1.save(p); // Es una nueva creación
            }

            return "redirect:/listarpas"; // Redirige al listado después de guardar
    }
    @GetMapping("/editarpas/{id}")
    public String editapas(@PathVariable int id, Model model){
        Optional<Pastores> pastor = service1.listarId(id);
        if (pastor.isPresent()) {
            model.addAttribute("pastores", pastor.get());
            return "formpas"; // Redirige al formulario para editar
        } else {
            return "redirect:/listarpas"; // Si no encuentra la persona, redirige al listado
        }
    }
    @GetMapping("/eliminarpas/{id}")
    public String eliminarpas(Model model,@PathVariable int id) {
        service1.deleted(id);
        return "redirect:/listarpas";
    }
}