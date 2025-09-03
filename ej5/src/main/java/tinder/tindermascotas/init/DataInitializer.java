package tinder.tindermascotas.init;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import tinder.tindermascotas.entities.Zone;
import tinder.tindermascotas.repositories.UserRepository;
import tinder.tindermascotas.repositories.ZoneRepository;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {
    private final UserRepository userRepository;
    private final ZoneRepository zoneRepository;

    @Override
    public void run(String... args) throws Exception {
        Zone zone1 = Zone.builder().nombre("Capital").build();
        Zone zone2 = Zone.builder().nombre("Godoy Cruz").build();
        Zone zone3 = Zone.builder().nombre("Guaymallén").build();
        zoneRepository.save(zone1);
        zoneRepository.save(zone2);
        zoneRepository.save(zone3);
    }
}
