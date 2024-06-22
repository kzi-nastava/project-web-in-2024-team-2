package com.webshop.dto;

import com.webshop.model.Korisnik;
import com.webshop.model.Proizvod;
import com.webshop.model.Recenzija;
import com.webshop.model.Uloga;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class PrikazProfilaFrontDto implements Serializable {

    private final String ime;
    private final String prezime;
    private final String username;
    private final String password;
    private final String mail;
    private final String brojTelefona;
    private final Uloga uloga;
    private final double ocena;
    private final long id;
    private Set<Proizvod> proizvodi = new HashSet<Proizvod>();
    private Set<Recenzija> recenzije = new HashSet<Recenzija>();

    public PrikazProfilaFrontDto(String ime, String prezime, String username, String password, String mail, String brojTelefona, Uloga uloga,
                                 double ocena, long id, Set<Proizvod> proizvodi, Set<Recenzija> recenzije) {
        this.ime = ime;
        this.prezime = prezime;
        this.username = username;
        this.password = password;
        this.mail = mail;
        this.brojTelefona = brojTelefona;
        this.uloga = uloga;
        this.ocena = ocena;
        this.id = id;
        this.proizvodi = proizvodi;
        this.recenzije = recenzije;
    }

    public PrikazProfilaFrontDto(Korisnik korisnik, double ocena, Set<Proizvod> proizvodi, Set<Recenzija> recenzije) {
        this.ime = korisnik.getIme();
        this.prezime = korisnik.getPrezime();
        this.username = korisnik.getUsername();
        this.password = korisnik.getPassword();
        this.mail = korisnik.getMail();
        this.brojTelefona = korisnik.getBrojTelefona();
        this.uloga = korisnik.getUloga();
        this.ocena = ocena;
        this.id = korisnik.getId();
        this.proizvodi = proizvodi;
        this.recenzije = recenzije;
    }

    public PrikazProfilaFrontDto(Korisnik korisnik, double ocena) {
        this.ime = korisnik.getIme();
        this.prezime = korisnik.getPrezime();
        this.username = korisnik.getUsername();
        this.password = korisnik.getPassword();
        this.mail = korisnik.getMail();
        this.brojTelefona = korisnik.getBrojTelefona();
        this.uloga = korisnik.getUloga();
        this.ocena = ocena;
        this.id = korisnik.getId();
    }

    public String getIme() {
        return ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getMail() {
        return mail;
    }

    public String getBrojTelefona() {
        return brojTelefona;
    }

    public Uloga getUloga() {
        return uloga;
    }

    public double getOcena() {
        return ocena;
    }

    public long getId() {
        return id;
    }

    public Set<Proizvod> getProizvodi() {
        return proizvodi;
    }

    public Set<Recenzija> getRecenzije() {
        return recenzije;
    }
}
