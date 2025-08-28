package ar.edu.uncuyo.videojuegos.DAO;

import ar.edu.uncuyo.videojuegos.entity.CategoriaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaDAO extends JpaRepository<CategoriaEntity, Long> {

}
