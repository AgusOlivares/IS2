package ar.edu.uncuyo.videojuegos.service;

import ar.edu.uncuyo.videojuegos.entity.VideojuegoEntity;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import ar.edu.uncuyo.videojuegos.DAO.VideojuegoDAO;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VideojuegoService {

    @Autowired
    private VideojuegoDAO DAO;

    //Obtiene todos los juegos activos
    public List<VideojuegoEntity> obtenerTodos() {
        return DAO.findAll().stream().filter(VideojuegoEntity::isActivo).collect(Collectors.toList());
    }

    public Optional<VideojuegoEntity> obtenerPorId(Long id) {
        return DAO.findById(id);
    }

    @Transactional
    public VideojuegoEntity guardar(VideojuegoEntity videojuego){
        return DAO.save(videojuego);
    }

    public void eliminar(Long id) {
        DAO.deleteById(id);
    }
}