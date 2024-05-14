package com.webshop.dto;

import com.webshop.model.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.*;

public class KorisnikDto implements Serializable {
    private Long id;
    private String ime;
    private String prezime;
    private String username;
    private String password;
    private String mail;
    private String brojTelefona;
    private LocalDate datumRodjenja;
    private String profilnaURL;
    private String opis;
    private Uloga uloga;
    private boolean blokiran;
    private double prosecnaOcena;


    private String potvrdaLozinke;

    private Set<Proizvod> proizvodi = new HashSet<>();
    private Set<Recenzija>  recenzija = new HashSet<>();

    public KorisnikDto(Long id, String ime, String prezime, String username, String password, String mail, String brojTelefona,
                       LocalDate datumRodjenja, String profilnaURL, String opis, Uloga uloga, boolean blokiran) {
        this.id = id;
        this.ime = ime;
        this.prezime = prezime;
        this.username = username;
        this.password = password;
        this.mail = mail;
        this.brojTelefona = brojTelefona;
        this.datumRodjenja = datumRodjenja;
        this.profilnaURL = profilnaURL;
        this.opis = opis;
        this.uloga = uloga;
        this.blokiran = blokiran;

        this.potvrdaLozinke = password;
    }

    public KorisnikDto(Korisnik korisnik) {
        this.id = korisnik.getId();
        this.ime = korisnik.getIme();
        this.prezime = korisnik.getPrezime();
        this.username = korisnik.getUsername();
        this.password = korisnik.getPassword();
        this.mail = korisnik.getMail();
        this.brojTelefona = korisnik.getBrojTelefona();
        this.datumRodjenja = korisnik.getDatumRodjenja();
        this.profilnaURL = korisnik.getProfilnaURL();
        this.opis = korisnik.getOpis();
        this.uloga = korisnik.getUloga();
        this.blokiran = korisnik.isBlokiran();

        this.potvrdaLozinke = korisnik.getPassword();
    }

    //geteri i seteri
    public Long getId() {
        return id;
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

    public LocalDate getDatumRodjenja() {
        return datumRodjenja;
    }

    public String getProfilnaURL() {
        return profilnaURL;
    }

    public String getOpis() {
        return opis;
    }

    public Uloga getUloga() {
        return uloga;
    }

    public boolean isBlokiran() {
        return blokiran;
    }

    //zbog potvrde lozinke
    public String getPotvrdaLozinke() {
        return potvrdaLozinke;
    }

    //konstruktor za podatke vidljive drugim korisnicima
    public KorisnikDto(String ime, String prezime, String username, LocalDate datumRodjenja, String profilnaURL, String opis, Uloga uloga, boolean blokiran,
                       double prosecnaOcena, Set<Proizvod> proizvodi, Set<Recenzija> recenzija) {
        this.ime = ime;
        this.prezime = prezime;
        this.username = username;
        this.datumRodjenja = datumRodjenja;
        this.profilnaURL = profilnaURL;
        this.opis = opis;
        this.uloga = uloga;
        this.blokiran = blokiran;
        this.prosecnaOcena = prosecnaOcena;
        this.proizvodi = proizvodi;
        this.recenzija = recenzija;
    }
}



