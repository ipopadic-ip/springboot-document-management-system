package com.ilija.repository;

import com.ilija.model.Dokument;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DokumentRepository extends JpaRepository<Dokument, Long> {
	
	@Query("SELECT d FROM Dokument d WHERE " +
		       "(:naslov IS NULL OR LOWER(d.naslov) LIKE LOWER(CONCAT('%', :naslov, '%'))) AND " +
		       "(:datumOd IS NULL OR d.datumObjave >= :datumOd) AND " +
		       "(:datumDo IS NULL OR d.datumObjave <= :datumDo) AND " +
		       "(:autor IS NULL OR d.autor.korisnickoIme = :autor)")
		List<Dokument> pretraziDokumente(
		       @Param("naslov") String naslov,
		       @Param("datumOd") LocalDate datumOd,
		       @Param("datumDo") LocalDate datumDo,
		       @Param("autor") String autor
		);

}
