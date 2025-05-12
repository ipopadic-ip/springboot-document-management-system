package com.ilija.dto;

import java.util.HashSet;
import java.util.Set;

import com.ilija.model.Role;

public class KorisnikDTO {

    private Long id;
    private String korisnickoIme;
    
    private Set<Role> uloge = new HashSet<>();


    public Set<Role> getUloge() {
		return uloge;
	}

	public void setUloge(Set<Role> uloge) {
		this.uloge = uloge;
	}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    public void setKorisnickoIme(String korisnickoIme) {
        this.korisnickoIme = korisnickoIme;
    }
    
//    public Role getUloga() {
//        return uloga;
//    }
//
//    public void setUloga(Role uloga) {
//        this.uloga = uloga;
//    }
}
