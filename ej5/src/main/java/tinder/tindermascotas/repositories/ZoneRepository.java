package tinder.tindermascotas.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tinder.tindermascotas.entities.Zone;

@Repository
public interface ZoneRepository extends CrudRepository<Zone, String> {

    Zone getZoneById(String id);

    String id(String id);

    Zone getReferenceById(String id);
}
