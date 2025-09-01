package ar.edu.uncuyo.videojuegos.repository;

import ar.edu.uncuyo.videojuegos.entity.Category;
import ar.edu.uncuyo.videojuegos.entity.Studio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findAllByActiveTrue();

    Optional<Category> findByIdAndActiveTrue(Long id);

    boolean existsByNameAndActiveTrue(String name);
}
