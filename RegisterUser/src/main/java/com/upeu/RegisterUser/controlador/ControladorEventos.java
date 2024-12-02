package com.upeu.RegisterUser.controlador;

import java.util.List;
import java.util.Optional;

import com.upeu.RegisterUser.interfaceService.IEventoService;
import com.upeu.RegisterUser.modelo.Evento;
import com.upeu.RegisterUser.modelo.Pastores;
import com.upeu.RegisterUser.modelo.Persona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class ControladorEventos {

    @Autowired
    private IEventoService eventoService;



    // Listar eventos
    @GetMapping("/listarp")
    public String listar(Model model) {
        List<Evento> eventos = eventoService.listar();
        model.addAttribute("eventos", eventos);
        return "index2"; // Página donde se muestran los eventos
    }

    // Mostrar formulario para agregar un nuevo evento
    @GetMapping("/newp")
    public String agregar1(Model model) {
        model.addAttribute("evento", new Evento());
        return "form2"; // Formulario para crear o editar eventos
    }
    @PostMapping("/savep")
    public String save(@Validated Evento e, Model model) {
        // Validación de campos básicos
        if (e.getFecha() == null || e.getHoraInicio() == null || e.getHoraFin() == null || e.getTipo() == null) {
            model.addAttribute("error", "Debe llenar todos los campos correctamente.");
            return "form2";
        }

        // Verifica si es una actualización o creación
        if (e.getId() != 0) {
            Optional<Evento> existingEvento = eventoService.listarId(e.getId());
            if (existingEvento.isPresent()) {
                Evento eventoToUpdate = existingEvento.get();
                eventoToUpdate.setFecha(e.getFecha());
                eventoToUpdate.setHoraInicio(e.getHoraInicio());
                eventoToUpdate.setHoraFin(e.getHoraFin());
                eventoToUpdate.setTipo(e.getTipo());
                eventoService.save(eventoToUpdate);
            }
        } else {
            eventoService.save(e); // Es una nueva creación
        }

        return "redirect:/listarp"; // Redirige al listado después de guardar
    }
    @GetMapping("/editarp/{id}")
    public String editarp(@PathVariable int id, Model model){
        Optional<Evento> evento = eventoService.listarId(id); // Renombrado a "evento" en singular
        if (evento.isPresent()) {
            model.addAttribute("evento", evento.get()); // Usar "evento" en lugar de "eventos"
            return "form2"; // Redirige al formulario para editar
        } else {
            return "redirect:/listarp"; // Si no encuentra el evento, redirige al listado
        }
    }
    @GetMapping("/eliminarp/{id}")
    public String eliminarp(Model model,@PathVariable int id) {
        eventoService.delete(id);
        return "redirect:/listarp";
    }


    // Eliminar un evento

}
