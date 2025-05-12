package com.ilija.model;

import jakarta.persistence.Entity;

@Entity
public class Izvestaj extends Dokument {

    private String tip;
    private String sadrzaj;

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public String getSadrzaj() {
        return sadrzaj;
    }

    public void setSadrzaj(String sadrzaj) {
        this.sadrzaj = sadrzaj;
    }
}
