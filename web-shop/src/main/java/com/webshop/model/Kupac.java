package com.webshop.model;

import com.webshop.dto.KorisnikDto;
import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@DiscriminatorValue("Kupac")
public class Kupac extends Korisnik implements Serializable {

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Proizvod> kupljeniProizvodi = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Recenzija> recenzije = new HashSet<>();

    private Double prosecnaOcena;

    public Kupac() {
        super();
    }

    public Kupac(Long id, String ime, String prezime, String username, String password, String mail, String brojTelefona, LocalDate datumRodjenja, String profilnaURL, String opis, Uloga uloga, boolean blokiran, Double prosecnaOcena) {
        super(id, ime, prezime, username, password, mail, brojTelefona, datumRodjenja, profilnaURL, opis, Uloga.KUPAC, blokiran);
        this.prosecnaOcena = prosecnaOcena;
    }

    public Kupac(Korisnik korisnik) {
        super(korisnik);
        this.setUloga(Uloga.KUPAC);
        this.prosecnaOcena = 0.0;
    }

    public Kupac(KorisnikDto korisnikDto) {
        super(korisnikDto);
        this.setUloga(Uloga.KUPAC);
        this.prosecnaOcena = 0.0;
    }

    public Set<Proizvod> getKupljeniProizvodi() {
        return kupljeniProizvodi;
    }

    public void setKupljeniProizvodi(Set<Proizvod> kupljeniProizvodi) {
        this.kupljeniProizvodi = kupljeniProizvodi;
    }

    public Set<Recenzija> getRecenzije() {
        return recenzije;
    }

    public void setRecenzije(Set<Recenzija> recenzije) {
        this.recenzije = recenzije;
    }

    public Double getProsecnaOcena() {
        return prosecnaOcena;
    }

    public void setProsecnaOcena(Double prosecnaOcena) {
        this.prosecnaOcena = prosecnaOcena;
    }
}
