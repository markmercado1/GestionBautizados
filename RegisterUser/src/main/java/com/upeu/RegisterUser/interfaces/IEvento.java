package com.upeu.RegisterUser.interfaces;

import com.upeu.RegisterUser.modelo.Evento;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IEvento extends CrudRepository<Evento, Integer> {


}
