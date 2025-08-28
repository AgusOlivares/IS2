package ar.edu.uncuyo.videojuegos.service;


import ar.edu.uncuyo.videojuegos.DAO.CategoriaDAO;
import ar.edu.uncuyo.videojuegos.entity.CategoriaEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaDAO DAO;

    public List<CategoriaEntity> obtenerTodos(){
        return DAO.findAll();
    }

    public Optional<CategoriaEntity> obtenerPorId(Long id){
        return DAO.findById(id);
    }

    public CategoriaEntity guardar(CategoriaEntity categoria){
        return DAO.save(categoria);
    }

    public void eliminar(Long id){
        DAO.deleteById(id);
    }


}
