package ar.edu.uncuyo.videojuegos.service;


import ar.edu.uncuyo.videojuegos.entity.Studio;
import ar.edu.uncuyo.videojuegos.error.BusinessException;
import ar.edu.uncuyo.videojuegos.repository.StudioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class StudioService {

    @Autowired
    private StudioRepository studioRepository;

    public List<Studio> getAllStudios() {
        return studioRepository.findAllByActiveTrue();
    }

    public Studio getStudio(Long id) throws Exception {
        return studioRepository.findByIdAndActiveTrue(id)
                .orElseThrow(() -> new BusinessException("Estudio no encontrado"));
    }

    @Transactional
    public void createStudio(String name) throws Exception {
        if (studioRepository.existsByNameAndActiveTrue(name)) {
            throw new BusinessException("Ya existe un estudio con ese nombre");
        }

        Studio studio = new Studio();
        studio.setName(name);
        studio.setActive(true);
        studioRepository.save(studio);
    }

    @Transactional
    public void updateStudio(Long id, String name) throws Exception {
        Studio studio = studioRepository.findByIdAndActiveTrue(id)
            .orElseThrow(() -> new BusinessException("Estudio no encontrado"));

        if (studioRepository.existsByNameAndActiveTrue(name)) {
            throw new BusinessException("Ya existe un estudio con ese nombre");
        }

        studio.setName(name);
        studioRepository.save(studio);
    }

    @Transactional
    public void deleteStudio(Long id) throws Exception {
        Studio studio = studioRepository.findByIdAndActiveTrue(id)
                .orElseThrow(() -> new BusinessException("Estudio no encontrado"));

        studio.setActive(false);
        studioRepository.save(studio);
    }
}
