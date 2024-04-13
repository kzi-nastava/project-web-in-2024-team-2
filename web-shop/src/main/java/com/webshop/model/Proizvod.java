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

    private String naziv;

    private String opis;

    private URL profilnaURL;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Kategorija kategorija;

    private Double cena;

    @Enumerated(EnumType.STRING)
    private TipProdaje tipProdaje;

    private LocalDate datumObjave;

    @OneToMany(mappedBy = "proizvod", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Ponuda> ponude = new HashSet<>();

    private boolean recenzijaKupcaOstavljena;

    private boolean recenzijaProdavcaOstavljena;

    private boolean prodat;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Kupac kupac;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Prodavac prodavac;


}
