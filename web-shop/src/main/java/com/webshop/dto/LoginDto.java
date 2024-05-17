package com.webshop.dto;

import com.webshop.model.Korisnik;

import java.io.Serializable;

/**
 * DTO for {@link com.webshop.model.Korisnik}
 */
public class LoginDto implements Serializable {

    private final String username;
    private final String password;

    public LoginDto(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public LoginDto(Korisnik korisnik) {
        this.username = korisnik.getUsername();
        this.password = korisnik.getPassword();
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}