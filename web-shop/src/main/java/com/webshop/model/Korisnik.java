package com.webshop.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.net.URL;
import java.time.LocalDate;
import java.util.Set;

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
    private String lozinka;

    @Column
    @Temporal(TemporalType.DATE)
    private LocalDate datumRodjenja;

    @Column
    private URL profilnaURL;

    @Column
    private String opis;

    @Column
    @Enumerated(EnumType.STRING)
    private Uloga uloga;

    @Column
    private boolean blokiran;


    //zbog recenzija
    @OneToOne(mappedBy = "korisnik",cascade = CascadeType.ALL)
    private Recenzija recenzija;

    //zbog prijava profila
    @OneToOne(mappedBy = "korisnikPodnosilac",cascade = CascadeType.ALL)
    private PrijavaProfila prijava;

    //zbog prijava profila
    @OneToOne(mappedBy = "korisnikOdnosilac",cascade = CascadeType.ALL)
    private PrijavaProfila prijava2;

}
