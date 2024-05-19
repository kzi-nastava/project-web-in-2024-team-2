package com.webshop.dto;

import com.webshop.model.Korisnik;
import com.webshop.model.Uloga;

import java.io.Serializable;

/**
 * DTO for {@link com.webshop.model.Korisnik}
 */
public class KorisnikDto implements Serializable {

    private final String ime;
    private final String prezime;
    private final String username;
    private final String password;
    private final String mail;
    private final String brojTelefona;
    private final Uloga uloga;

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
}