package com.example.ej4.DAO;

import com.example.ej4.entity.EstudioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstudioDAO extends JpaRepository<EstudioEntity, Long> {

}
