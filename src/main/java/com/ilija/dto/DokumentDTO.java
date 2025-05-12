package com.ilija.dto;

import java.time.LocalDate;

public class DokumentDTO {
    private Long id;
    private String naslov;
    private LocalDate datumObjave;
    private LocalDate datumBrisanja;
    private Long autorId;
    private String imeAutora;
    private String prezimeAutora;

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
    
    public Long getAutorId() {
        return autorId;
    }

    public void setAutorId(Long autorId) {
        this.autorId = autorId;
    }

    public String getImeAutora() {
        return imeAutora;
    }

    public void setImeAutora(String imeAutora) {
        this.imeAutora = imeAutora;
    }

    public String getPrezimeAutora() {
        return prezimeAutora;
    }

    public void setPrezimeAutora(String prezimeAutora) {
        this.prezimeAutora = prezimeAutora;
    }
}
