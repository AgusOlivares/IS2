package repositories;

import entities.Votes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface VotesRepository extends JpaRepository<Votes,String> {
    @Query("SELECT c FROM Votes c WHERE c.pet1.id = :id ORDER BY c.fecha DESC")
    public List<Votes> searchMyVotes(@Param("id") String id);

    @Query("SELECT c FROM Votes c WHERE c.pet2.id = :id ORDER BY c.fecha DESC")
    public List<Votes> searchRecievedVotes(@Param("id") String id);
}
