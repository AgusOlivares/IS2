package repositories;

import entities.Zone;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ZoneRepository extends CrudRepository<Zone,String> {

}
