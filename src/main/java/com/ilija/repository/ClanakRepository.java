package com.ilija.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ilija.model.Clanak;

public interface ClanakRepository extends JpaRepository<Clanak, Long> {
}
