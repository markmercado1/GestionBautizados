package com.upeu.RegisterUser.interfaceService;

import com.upeu.RegisterUser.modelo.Pastores;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;


public interface IpastorService  {
    public List<Pastores>listar();
    public Optional<Pastores>listarId(int id);
    public int save(Pastores p);
    public void deleted(int id);

}
