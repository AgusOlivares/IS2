package ar.edu.uncuyo.videojuegos.DAO;

import ar.edu.uncuyo.videojuegos.entity.EstudioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EstudioDAO extends JpaRepository<EstudioEntity, Long> {
    @Query("SELECT p FROM EstudioEntity p WHERE p.nombre = :nombre AND p.activo = TRUE")
    public EstudioEntity buscarPaisPorNombre(@Param("nombre")String nombre);
}
