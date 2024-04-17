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

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "kategorija_proizvoda", joinColumns = @JoinColumn(name = "proizvod_id"), inverseJoinColumns = @JoinColumn(name = "kategorija_id"))
    private Set<Kategorija> kategorije;

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
