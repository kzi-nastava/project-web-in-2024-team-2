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
    private URL slika;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "kategorija_id")
    private Kategorija kategorija;

    @Column
    private Double cena;

    @Column
    @Enumerated(EnumType.STRING)
    private TipProdaje tipProdaje;

    @Column
    @Temporal(TemporalType.DATE)
    private LocalDate datumObjave;

    @OneToMany(mappedBy = "proizvod", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Ponuda> ponude = new HashSet<>();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "prodavac_proizvoda_id")
    private Prodavac prodavacProizvoda;

    @Column
    private boolean recenzijaKupca;

    @Column
    private boolean recenzijaProdavca;

    @Column
    private boolean prodat;

    //zbog prodavac
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "prodavac_id")
    private Prodavac prodavac;

    //zbog kupac
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "kupac_id")
    private Kupac kupac;

}
