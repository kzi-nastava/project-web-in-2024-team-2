package com.webshop.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
public class Recenzija implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Integer ocena;

    @Column
    private String komentar;

    @Column
    @Temporal(TemporalType.DATE)
    private LocalDate datumRecenzije;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "korisnik_id")
    private Korisnik korisnik;


    //zbog prodavac
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "prodavac_id")
    private Prodavac prodavac;

    //zbog kupac
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "kupac_id")
    private Kupac kupac;

}


