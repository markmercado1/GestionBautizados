package com.upeu.RegisterUser.interfaces;

import com.upeu.RegisterUser.modelo.Pastores;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPastores extends CrudRepository<Pastores, Integer> {
}
