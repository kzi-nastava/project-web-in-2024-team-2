package com.webshop.dto;

import com.webshop.model.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/**
 * DTO for {@link com.webshop.model.Korisnik}
 */
public class KorisnikDto implements Serializable {

    private final String ime;
    private final String prezime;
    private final String username;
    private  String password;
    private  String mail;
    private final String brojTelefona;
    private  Uloga uloga;

    public KorisnikDto(String ime, String prezime, String username, String password, String mail, String brojTelefona, Uloga uloga) {
        this.ime = ime;
        this.prezime = prezime;
        this.username = username;
        this.password = password;
        this.mail = mail;
        this.brojTelefona = brojTelefona;
        this.uloga = uloga;
    }

    public KorisnikDto(Korisnik korisnik) {
        this.ime = korisnik.getIme();
        this.prezime = korisnik.getPrezime();
        this.username = korisnik.getUsername();
        this.password = korisnik.getPassword();
        this.mail = korisnik.getMail();
        this.brojTelefona = korisnik.getBrojTelefona();
        this.uloga = korisnik.getUloga();
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


    //dodato sve ispod
    private  LocalDate datumRodjenja;
    private  String profilnaURL;
    private  String opis;
    private  String potvrda_lozinke;

    public KorisnikDto(String ime, String prezime, String username, String password, String mail, String brojTelefona, LocalDate datumRodjenja, String profilnaURL, String opis, String potvrda_lozinke) {
        this.ime = ime;
        this.prezime = prezime;
        this.username = username;
        this.password = password;
        this.mail = mail;
        this.brojTelefona = brojTelefona;
        this.datumRodjenja = datumRodjenja;
        this.profilnaURL = profilnaURL;
        this.opis = opis;
        this.potvrda_lozinke = potvrda_lozinke;
    }


    private double ProsecnaOcena;
    private Set<Proizvod> proizvodi = new HashSet<>();
    private Set<Recenzija> recenzije = new HashSet<>();

    public KorisnikDto(Prodavac prodavac) {
        this.ime = prodavac.getIme();
        this.prezime = prodavac.getPrezime();
        this.username = prodavac.getUsername();
        this.brojTelefona = prodavac.getBrojTelefona();
        this.datumRodjenja = prodavac.getDatumRodjenja();
        this.profilnaURL = prodavac.getProfilnaURL();
        this.opis = prodavac.getOpis();
        this.ProsecnaOcena = prodavac.getProsecnaOcena();
        this.proizvodi = prodavac.getProizvodiNaProdaju();
        //this.recenzije = prodavac.getRecenzije();
    }

    public KorisnikDto(Kupac kupac) {
        this.ime = kupac.getIme();
        this.prezime = kupac.getPrezime();
        this.username = kupac.getUsername();
        this.brojTelefona = kupac.getBrojTelefona();
        this.datumRodjenja = kupac.getDatumRodjenja();
        this.profilnaURL = kupac.getProfilnaURL();
        this.opis = kupac.getOpis();
        this.ProsecnaOcena = kupac.getProsecnaOcena();
        this.proizvodi = kupac.getKupljeniProizvodi();
        //this.recenzije = kupac.getRecenzije();
    }
}


