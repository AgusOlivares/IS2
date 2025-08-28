package ar.edu.uncuyo.videojuegos.DAO;

import ar.edu.uncuyo.videojuegos.entity.EstudioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstudioDAO extends JpaRepository<EstudioEntity, Long> {

}
