package com.example.ej4.DAO;

import com.example.ej4.entity.VideojuegoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VideojuegoDAO extends JpaRepository<VideojuegoEntity, Long> {

}