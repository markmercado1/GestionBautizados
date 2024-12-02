package com.upeu.RegisterUser.interfaceService;
import org.springframework.data.repository.CrudRepository;
import com.upeu.RegisterUser.modelo.Evento;

import java.util.List;
import java.util.Optional;

public interface IEventoService {
    public List<Evento>listar();
    public Optional<Evento>listarId(int id);
    public int save(Evento e);
    public void delete(int id);
}
