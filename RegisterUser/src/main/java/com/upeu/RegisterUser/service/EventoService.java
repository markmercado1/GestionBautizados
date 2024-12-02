package com.upeu.RegisterUser.service;

import com.upeu.RegisterUser.interfaceService.IEventoService;
import com.upeu.RegisterUser.interfaces.IEvento;
import com.upeu.RegisterUser.modelo.Evento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventoService implements IEventoService {
    @Autowired
    private IEvento data;
    @Override
    public List<Evento> listar() {
        return (List<Evento>) data.findAll();
    }

    @Override
    public Optional<Evento> listarId(int id) {
        return data.findById(id); // Busca el evento por su ID y devuelve un Optional
    }

    @Override
    public int save(Evento e) {
        Evento eventoGuardado = data.save(e); // Guarda el evento y devuelve la entidad guardada
        return eventoGuardado != null ? 1 : 0; // Devuelve 1 si se guardó correctamente, de lo contrario 0
    }

    @Override
    public void delete(int id) {
        data.deleteById(id);
    }
}
