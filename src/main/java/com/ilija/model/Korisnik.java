package com.ilija.model;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
//Koristim ovaj pristup jer omogućava da zajednički podaci budu u jednoj tabeli, dok se specifični atributi čuvaju u tabelama podklasa
public class Korisnik {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String korisnickoIme;
    private String lozinka;
    
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "korisnik_uloge",
        joinColumns = @JoinColumn(name = "korisnik_id"),
        inverseJoinColumns = @JoinColumn(name = "uloga_id")
    )
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

    public String getLozinka() {
        return lozinka;
    }

    public void setLozinka(String lozinka) {
        this.lozinka = lozinka;
    }
}
