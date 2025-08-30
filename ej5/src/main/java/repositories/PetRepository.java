package repositories;

import entities.Pet;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface PetRepository extends JpaRepository<Pet, String> {

    @Query("SELECT c FROM Pet c WHERE c.usser.id = :id")
    public List<Pet> buscarMascotasPorUsuario(@Param("id") String id);

}
