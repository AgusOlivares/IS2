package ar.edu.uncuyo.videojuegos.service;

import ar.edu.uncuyo.videojuegos.entity.Category;
import ar.edu.uncuyo.videojuegos.entity.Studio;
import ar.edu.uncuyo.videojuegos.entity.Videogame;
import ar.edu.uncuyo.videojuegos.error.BusinessException;
import ar.edu.uncuyo.videojuegos.repository.VideogameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class VideogameService {

    @Autowired
    private VideogameRepository videogameRepository;
    @Autowired
    private StudioService studioService;
    @Autowired
    private CategoryService categoryService;

    public List<Videogame> getAllVideogames() {
        return videogameRepository.findAllByActiveTrue();
    }

    public Videogame getVideogame(Long id) throws Exception {
        return videogameRepository.findByIdAndActiveTrue(id)
                .orElseThrow(() -> new BusinessException("Videojuego no encontrado"));
    }

    @Transactional
    public void createVideogame(Videogame game, long studioId, long categoryId) throws Exception {
        if (videogameRepository.existsByTitleAndActiveTrue(game.getTitle())) {
            throw new BusinessException("Ya existe un videojuego con ese nombre");
        }

        Studio studio = studioService.getStudio(studioId);
        Category category = categoryService.getCategory(categoryId);
        game.setStudio(studio);
        game.setCategory(category);
        game.setActive(true);

        videogameRepository.save(game);
    }

    @Transactional
    public void updateVideogame(Videogame newGame, long studioId, long categoryId) throws Exception {
        Videogame game = videogameRepository.findByIdAndActiveTrue(newGame.getId())
                .orElseThrow(() -> new BusinessException("Videojuego no encontrado"));

        if (videogameRepository.existsByTitleAndActiveTrue(newGame.getTitle())) {
            throw new BusinessException("Ya existe un videojuego con ese nombre");
        }

        game.setTitle(newGame.getTitle());
        game.setDescription(newGame.getDescription());
        game.setAmount(newGame.getAmount());
        game.setImageUrl(newGame.getImageUrl());
        game.setOnSale(newGame.isOnSale());
        game.setPrice(newGame.getPrice());
        game.setReleaseDate(newGame.getReleaseDate());

        Studio studio = studioService.getStudio(studioId);
        Category category = categoryService.getCategory(categoryId);
        game.setStudio(studio);
        game.setCategory(category);

        videogameRepository.save(game);
    }

    @Transactional
    public void deleteVideogame(Long id) throws Exception {
        Videogame videogame = videogameRepository.findByIdAndActiveTrue(id)
                .orElseThrow(() -> new BusinessException("Videojuego no encontrado"));

        videogame.setActive(false);
        videogameRepository.save(videogame);
    }
}