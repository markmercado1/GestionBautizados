package com.upeu.RegisterUser.service;

import com.upeu.RegisterUser.interfaceService.IpastorService;
import com.upeu.RegisterUser.interfaces.IPastores;
import com.upeu.RegisterUser.modelo.Pastores;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class PastoresService implements IpastorService {

    @Autowired
    private IPastores data;

    @Override
    public List<Pastores> listar() {
        return (List<Pastores>) data.findAll();
    }

    @Override
    public Optional<Pastores> listarId(int id) {
        return data.findById(id);
    }

    @Override
    public int save(Pastores p) {
        int res =0;
        Pastores pastoresGuarda = data.save(p);
        if (!pastoresGuarda.equals(null)){
            res =1;
        }
        return res;

    }

    @Override
    public void deleted(int id) {
        data.deleteById(id);
    }
}
