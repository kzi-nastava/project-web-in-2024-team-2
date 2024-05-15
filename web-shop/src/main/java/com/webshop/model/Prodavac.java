package com.webshop.model;

import com.webshop.dto.KorisnikDto;
import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@DiscriminatorValue("Prodavac")
public class Prodavac extends Korisnik implements Serializable {

    @OneToMany(mappedBy = "prodavac", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Proizvod> proizvodiNaProdaju = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Recenzija> recenzije = new HashSet<>();

    private Double prosecnaOcena;

    public Prodavac() {
        super();
    }

    public Prodavac(Long id, String ime, String prezime, String username, String password, String mail, String brojTelefona, LocalDate datumRodjenja, String profilnaURL, String opis, Uloga uloga, boolean blokiran, Double prosecnaOcena) {
        super(id, ime, prezime, username, password, mail, brojTelefona, datumRodjenja, profilnaURL, opis, Uloga.PRODAVAC, blokiran);
        this.prosecnaOcena = prosecnaOcena;
    }

    public Prodavac(Korisnik korisnik) {
        super(korisnik);
        this.prosecnaOcena = 0.0;
    }

    public Prodavac(KorisnikDto korisnikDto) {
        super(korisnikDto);
        this.setUloga(Uloga.PRODAVAC);
        this.prosecnaOcena = 0.0;
    }

    public Set<Proizvod> getProizvodiNaProdaju() {
        return proizvodiNaProdaju;
    }

    public Set<Recenzija> getRecenzije() {
        return recenzije;
    }

    public Double getProsecnaOcena() {
        return prosecnaOcena;
    }
}
