package com.ilija.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ilija.model.Autor;

public interface AutorRepository extends JpaRepository<Autor, Long> {
}