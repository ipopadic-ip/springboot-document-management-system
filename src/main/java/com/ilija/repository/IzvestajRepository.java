package com.ilija.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ilija.model.Izvestaj;

public interface IzvestajRepository extends JpaRepository<Izvestaj, Long> {
}
