package service;

import entities.Pet;
import entities.Votes;
import enums.Sexo;
import exceptions.ErrorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repositories.PetRepository;
import repositories.VotesRepository;

import java.util.Date;
import java.util.Optional;

@Service
public class VotesService {
    @Autowired
    private PetRepository petRepository;
    @Autowired
    private VotesRepository votesRepository;
    @Autowired
    private NotificationService notificationService;

    public void vote(String idUsser, String idPet1, String idPet2) throws ErrorService {
        Votes vote = new Votes();
        vote.setFecha(new Date());
        if (idPet1.equals(idPet2)) {
            throw new ErrorService("No puede votarse a si mismo");}

        Optional<Pet> response = petRepository.findById(idPet1);
        if  (response.isPresent()) {
            Pet pet1 = response.get();
            if(pet1.getUsser().getId().equals(response)){
                vote.setPet1(pet1);
            } else {
                throw new ErrorService("No tiene permisos para realizar esta operación");
            }
        } else {
            throw new ErrorService("No existe una mascota con ese identificador");
        }

        Optional<Pet> response2 = petRepository.findById(idPet2);
        if(response2.isPresent()) {
            Pet pet2 = response2.get();
            vote.setPet2(pet2);
            notificationService.send("Su Mascota ha sido votada", "Tinder de Mascotas", pet2.getUsser().getMail());

        } else {
            throw new ErrorService("No existe una mascota con ese identificador");
        }

        votesRepository.save(vote);
    }

    public void response(String usserId, String voteId) throws ErrorService {
        Optional<Votes> response = votesRepository.findById(voteId);
        if (response.isPresent()) {
            Votes vote = response.get();
            vote.setRespuesta(new Date());

            if(vote.getPet2().getUsser().getId().equals(usserId)) {
                notificationService.send("Tu voto fue correspondido", "Tinder de Mascotas", vote.getPet1().getUsser().getMail());
                votesRepository.save(vote);
            } else {
                throw new ErrorService("No tiene permisos para realizar esta operacion");
            }
        } else {
            throw new ErrorService("No existe el voto solicitado");
        }
    }

}
