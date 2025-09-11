package ar.edu.uncuyo.videojuegos.service;


import ar.edu.uncuyo.videojuegos.error.BusinessException;
import ar.edu.uncuyo.videojuegos.repository.CategoryRepository;
import ar.edu.uncuyo.videojuegos.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> getAllCategories() {
        return categoryRepository.findAllByActiveTrue();
    }

    public Category getCategory(Long id) throws Exception {
        return categoryRepository.findByIdAndActiveTrue(id)
                .orElseThrow(() -> new BusinessException("Categoría no encontrada"));
    }

    @Transactional
    public void createCategory(String name) throws Exception {
        if (categoryRepository.existsByNameAndActiveTrue(name)) {
            throw new BusinessException("Ya existe una categoría con ese nombre");
        }

        Category category = new Category();
        category.setName(name);
        category.setActive(true);
        categoryRepository.save(category);
    }

    @Transactional
    public void updateCategory(Long id, String name) throws Exception {
        Category category = categoryRepository.findByIdAndActiveTrue(id)
                .orElseThrow(() -> new BusinessException("Categoría no encontrada"));

        if (categoryRepository.existsByNameAndActiveTrue(name)) {
            throw new BusinessException("Ya existe una categoría con ese nombre");
        }

        category.setName(name);
        categoryRepository.save(category);
    }

    @Transactional
    public void deleteCategory(Long id) throws Exception {
        Category category = categoryRepository.findByIdAndActiveTrue(id)
                .orElseThrow(() -> new BusinessException("Categoría no encontrada"));

        category.setActive(false);
        categoryRepository.save(category);
    }
}