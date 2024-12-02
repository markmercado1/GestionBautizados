/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.upeu.RegisterUser.interfaces;

import com.upeu.RegisterUser.modelo.Bautizado;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


/**
 *
 * @author Home
 */
@Repository
public interface IBautizado extends CrudRepository<Bautizado, Integer>{
    Optional<Bautizado> findByPersonaId(int personaId);


}
