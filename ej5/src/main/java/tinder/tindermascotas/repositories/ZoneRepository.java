package tinder.tindermascotas.repositories;

import tinder.tindermascotas.entities.Zone;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ZoneRepository extends CrudRepository<Zone,String> {

    Zone getZoneById(String id);

    String id(String id);

    Zone getReferenceById(String id);
}
