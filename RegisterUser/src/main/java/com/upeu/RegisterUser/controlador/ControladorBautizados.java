package com.upeu.RegisterUser.controlador;

import com.upeu.RegisterUser.interfaceService.IbautizadoService;
import com.upeu.RegisterUser.interfaceService.IpersonasService;

import com.upeu.RegisterUser.modelo.Persona;
import com.upeu.RegisterUser.modelo.Bautizado;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.time.format.DateTimeFormatter;


import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping
public class ControladorBautizados {

    @Autowired
    private IpersonasService personasService;

    @Autowired
    private IbautizadoService bautizadoService;

    // Página principal


    @GetMapping("/bautizados/new")
    public String nuevoBautizo(Model model) {
        List<Persona> personas = personasService.listar(); // Obtener lista de personas
        model.addAttribute("personas", personas); // Pasar las personas al modelo
        model.addAttribute("bautizado", new Bautizado()); // Crear un objeto vacío de Bautizado
        return "formBau"; // Redirigir a la vista formBau.html
    }



    // Listar personas bautizadas
    @GetMapping("/listarBautizados")
    public String listarBautizados(Model model) {
        List<Bautizado> bautizados = bautizadoService.listar();
        model.addAttribute("bautizados", bautizados);
        return "indexBau"; // Debe corresponder con el nombre del archivo en templates (bautizados.html)
    }





    // Mostrar formulario para registrar un bautizo
    @GetMapping("/bautizar/{id}")
    public String bautizarPersona(@PathVariable int id, Model model) {
        Optional<Persona> persona = personasService.listarId(id);
        if (persona.isPresent()) {
            model.addAttribute("persona", persona.get());
            model.addAttribute("bautizado", new Bautizado());
            return "formBau"; // Debe coincidir con el nombre del archivo en templates (bautizar.html)
        } else {
            return "redirect:/listar";
        }
    }

    // Guardar un bautizo

    @PostMapping("/guardarBautizo")
    public String saveBautizados(@Validated Bautizado b, Model model) {
        if (b.getPersona() == null) {
            model.addAttribute("error", "Debe seleccionar una persona.");
            return "formBau";
        }

        if (b.getFechaBautizo() == null) {
            model.addAttribute("error", "Debe ingresar la fecha del bautizo.");
            return "formBau";
        }

        // Verifica si ya existe un registro con esa persona
        Optional<Bautizado> existente = bautizadoService.findByPersonaId(b.getPersona().getId());
        if (existente.isPresent() && b.getId() == 0) {
            model.addAttribute("error", "Esta persona ya ha sido registrada como bautizada.");
            return "formBau";
        }

        // Guardar o actualizar
        bautizadoService.save(b);
        return "redirect:/listarBautizados";
    }




    @PostMapping("/bautizados/editar/{id}")
    public String actualizarBautizado(@PathVariable int id, @ModelAttribute("bautizado") Bautizado bautizado) {
        Optional<Bautizado> bautizadoExistente = bautizadoService.listarId(id);

        if (bautizadoExistente.isPresent()) {
            Bautizado b = bautizadoExistente.get();
            b.setFechaBautizo(bautizado.getFechaBautizo());
            b.setLugar(bautizado.getLugar());
            b.setPersona(bautizado.getPersona());

            bautizadoService.save(b);
            return "redirect:/listarBautizados";  // Redirige después de guardar
        } else {
            return "redirect:/listarBautizados";  // Si no se encuentra, redirige
        }
    }



    @GetMapping("/bautizados/eliminar/{id}")
    public String eliminarPersona(@PathVariable int id) {
        bautizadoService.deleted(id);
        return "redirect:/listarBautizados";
    }
}