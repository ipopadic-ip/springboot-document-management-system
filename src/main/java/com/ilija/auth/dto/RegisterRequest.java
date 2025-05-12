package com.ilija.auth.dto;

import java.util.List;
import java.util.Set;

import com.ilija.model.Role;


public class RegisterRequest {
    private String korisnickoIme;
    private String lozinka;
    private Set<String> uloge;
    private String ime;
    private String prezime;
    
	
	public Set<String> getUloge() {
		return uloge;
	}
	public void setUloge(Set<String> uloge) {
		this.uloge = uloge;
	}
	public String getIme() {
		return ime;
	}
	public void setIme(String ime) {
		this.ime = ime;
	}
	public String getPrezime() {
		return prezime;
	}
	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}
	public String getKorisnickoIme() {
		return korisnickoIme;
	}
	public void setKorisnickoIme(String korisnickoIme) {
		this.korisnickoIme = korisnickoIme;
	}
	public String getLozinka() {
		return lozinka;
	}
	public void setLozinka(String lozinka) {
		this.lozinka = lozinka;
	}
	
	
    
}