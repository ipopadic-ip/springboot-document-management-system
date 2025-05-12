package com.ilija.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
//Koristim ovaj pristup jer omogućava da zajednički podaci budu u jednoj tabeli, dok se specifični atributi čuvaju u tabelama podklasa
public class Dokument {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String naslov;

    private LocalDate datumObjave;
    private LocalDate datumBrisanja;
    
    @ManyToOne(optional = false)
    @JoinColumn(name = "autor_id")
    private Autor autor;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNaslov() {
        return naslov;
    }

    public void setNaslov(String naslov) {
        this.naslov = naslov;
    }

    public LocalDate getDatumObjave() {
        return datumObjave;
    }

    public void setDatumObjave(LocalDate datumObjave) {
        this.datumObjave = datumObjave;
    }

    public LocalDate getDatumBrisanja() {
        return datumBrisanja;
    }

    public void setDatumBrisanja(LocalDate datumBrisanja) {
        this.datumBrisanja = datumBrisanja;
    }
    
    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }
}