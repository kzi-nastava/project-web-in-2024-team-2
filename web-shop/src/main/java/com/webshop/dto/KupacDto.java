package com.webshop.dto;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * DTO for {@link com.webshop.model.Kupac}
 */
public class KupacDto implements Serializable {

    private final String ime;
    private final String prezime;
    private final String username;
    private final String password;
    private final String mail;
    private final String brojTelefona;
    private final LocalDate datumRodjenja;
    private final String profilnaURL;
    private final String opis;

    public KupacDto(String ime, String prezime, String username, String password, String mail, String brojTelefona, LocalDate datumRodjenja, String profilnaURL, String opis) {
        this.ime = ime;
        this.prezime = prezime;
        this.username = username;
        this.password = password;
        this.mail = mail;
        this.brojTelefona = brojTelefona;
        this.datumRodjenja = datumRodjenja;
        this.profilnaURL = profilnaURL;
        this.opis = opis;
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
}