package ar.edu.uncuyo.videojuegos.DAO;

import ar.edu.uncuyo.videojuegos.entity.VideojuegoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VideojuegoDAO extends JpaRepository<VideojuegoEntity, Long> {

}