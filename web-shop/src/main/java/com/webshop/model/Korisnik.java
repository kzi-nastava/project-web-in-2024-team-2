package com.webshop.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.net.URL;
import java.time.LocalDate;

enum Uloga { KUPAC, PRODAVAC, ADMINISTRATOR };

@Entity
public class Korisnik implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String ime;

    @Column
    private String prezime;

    @Column(unique = true)
    private String username;

    @Column(unique = true)
    private String mail;

    @Column(unique = true)
    private String brojTelefona;

    @Column
    private LocalDate datumRodjenja;

    @Column
    private URL profilnaURL;

    @Column
    private String opis;

    @Column
    private Uloga uloga;

    @Column
    private boolean blokiran;

}
