package com.webshop.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.net.URL;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

enum TipProdaje { FIKSNA_CENA, AUKCIJA };

@Entity
public class Proizvod implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String naziv;

    @Column
    private String opis;

    @Column
    private URL profilnaURL;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Kategorija kategorija;

    @Column
    private Double cena;

    @Column
    private TipProdaje tipProdaje;

    @Column
    private LocalDate datumObjave;

    @OneToMany(mappedBy = "proizvod", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Ponuda> ponude = new HashSet<>();

//    @Column
//    private Prodavac prodavacRecenzija;

    @Column
    private boolean recenzijaKupca;

    @Column
    private boolean recenzijaProdavca;

    @Column
    private boolean prodat;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Kupac kupac;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Prodavac prodavac;

}
