package com.ilija.model;

import jakarta.persistence.Entity;

@Entity
public class Clanak extends Dokument {

    private String sadrzaj;

    public String getSadrzaj() {
        return sadrzaj;
    }

    public void setSadrzaj(String sadrzaj) {
        this.sadrzaj = sadrzaj;
    }
}
