package com.example.ej4.service;


import com.example.ej4.DAO.EstudioDAO;
import com.example.ej4.entity.EstudioEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.beans.ConstructorProperties;
import java.util.List;
import java.util.Optional;


@Service
public class EstudioService {

    @Autowired
    private EstudioDAO DAO;

    public List<EstudioEntity> obtenerTodos() {
        return DAO.findAll();
    }

    public Optional<EstudioEntity> obtenerPorId(Long id){
        return DAO.findById(id);
    }

    public EstudioEntity guardar(EstudioEntity estudio){
        return DAO.save(estudio);
    }

    public void eliminar(Long id){
        DAO.deleteById(id);
    }
}
