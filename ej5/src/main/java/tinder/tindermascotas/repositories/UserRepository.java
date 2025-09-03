package tinder.tindermascotas.repositories;

import tinder.tindermascotas.entities.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
@Repository
public interface UserRepository extends JpaRepository<User,String> {
    @Query("SELECT c FROM User c WHERE c.mail = :mail")
    public User searchByMail(@Param("mail") String mail);
}
