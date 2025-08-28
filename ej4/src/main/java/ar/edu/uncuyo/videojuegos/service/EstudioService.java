package ar.edu.uncuyo.videojuegos.service;


import ar.edu.uncuyo.videojuegos.DAO.EstudioDAO;
import ar.edu.uncuyo.videojuegos.entity.EstudioEntity;
import jakarta.persistence.NoResultException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class EstudioService {

    @Autowired
    private EstudioDAO DAO;

    public List<EstudioEntity> obtenerTodos() {
        return DAO.findAll().stream().filter(EstudioEntity::isActivo).collect(Collectors.toList());
    }

    public Optional<EstudioEntity> obtenerPorId(Long id){
        return DAO.findById(id);
    }

    public EstudioEntity guardar(EstudioEntity estudio){
        return DAO.save(estudio);
    }

    public void crearEstudio(String name) throws Exception {
        try {
            validar(name);

            try {
                EstudioEntity estudioAux = DAO.buscarPaisPorNombre(name);
                if (estudioAux != null && estudioAux.isActivo()) {
                    throw new Exception("Existe un país con el nombre indicado");
                }
            } catch (NoResultException ex) {}

            EstudioEntity pais = new EstudioEntity();
            pais.setNombre(name);
            pais.setActivo(true);

            DAO.save(pais);

        } catch (Exception ex){
            ex.printStackTrace();
            throw new Exception("Error de Sistemas");
        }
    }

    public void validar(String nombre) throws Exception{
        try{
            if (nombre == null || nombre.isEmpty()) {
                throw new Exception("Debe indicar el nombre");
            }
        } catch (Exception ex){
            ex.printStackTrace();
            throw new Exception("Error de Sistemas");
        }
    }

    @Transactional
    public void modificarEstudio(Long idEstudio, String nombre) throws Exception {

        try {

            validar(nombre);

            EstudioEntity estudio = buscarEstudio(idEstudio);

            try{
                EstudioEntity paisExsitente = DAO.buscarPaisPorNombre(nombre);
                if (paisExsitente != null && !paisExsitente.getId().equals(idEstudio) && paisExsitente.isActivo()){
                    throw new Exception("Existe un país con el nombre indicado");
                }
            } catch (NoResultException ex) {}

            estudio.setNombre(nombre);
            estudio.setActivo(true);

            DAO.save(estudio);

        } catch (Exception ex){
            ex.printStackTrace();
            throw new Exception("Error de Sistemas");
        }
    }

    public EstudioEntity buscarEstudio(Long id) throws Exception {

        try {

            if (id == null) {
                throw new Exception("Debe indicar el estudio");
            }

            /* El método findById heredado de JpaRepository para buscar la entidad por su ID.
             * Este método devuelve un Optional<E>, que puede contener la entidad si se encuentra,
             * o estar vacío si no se encuentra.
             * ¿Qué es Optional?
             * Un Optional<E> es un contenedor que puede o no contener un valor no nulo de tipo E.
             * Los Optional se utilizan para evitar NullPointerException
             * y para expresar la ausencia de un valor de manera más clara.
             */

            Optional<EstudioEntity> optional = DAO.findById(id);
            EstudioEntity estudio = null;
            if (optional.isPresent()) {
                /*
                 * Si la entidad se encuentra, se obtiene de Optional usando get()
                 */
                estudio= optional.get();

                /* Verifica si la entidad está marcada como eliminada lógicamente (entity.isEliminado()).
                 * Si es así, registra un mensaje de error y lanza una RuntimeException.
                 */
                if (!estudio.isActivo()){
                    throw new Exception("No se encuentra el país indicado");
                }
            }

            return estudio;

        } catch (Exception ex) {
            ex.printStackTrace();
            throw new Exception("Error de sistema");
        }
    }

    @Transactional
    public void eliminarEstudio(Long id) throws Exception {

        try {
            EstudioEntity estudio = buscarEstudio(id);
            estudio.setActivo(false);

            DAO.save(estudio);

        } catch (Exception ex) {
            ex.printStackTrace();
            throw new Exception("Error de sistema");
        }

    }

    public void eliminar(Long id){
        DAO.deleteById(id);
    }
}
