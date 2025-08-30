package repositories;

import entities.Usser;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
@Repository
public interface UsserRepository extends JpaRepository<Usser,String> {
    @Query("SELECT c FROM Usser c WHERE c.mail = :mail")
    public Usser searchByMail(@Param("mail") String mail);
}
