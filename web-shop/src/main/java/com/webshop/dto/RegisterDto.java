package com.webshop.dto;

import com.webshop.model.Uloga;

import java.io.Serializable;

public class RegisterDto implements Serializable {

    private final String ime;
    private final String prezime;
    private final String username;
    private final String password;
    private final String mail;
    private final String brojTelefona;
    private final Uloga uloga;
    private final String checkPassword;

    public RegisterDto(String ime, String prezime, String username, String password, String mail, String brojTelefona, Uloga uloga, String checkPassword) {
        this.ime = ime;
        this.prezime = prezime;
        this.username = username;
        this.password = password;
        this.mail = mail;
        this.brojTelefona = brojTelefona;
        this.uloga = uloga;
        this.checkPassword = checkPassword;
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

    public String getCheckPassword() {
        return checkPassword;
    }
}
