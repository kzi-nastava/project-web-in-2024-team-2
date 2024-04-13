package com.webshop.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

enum StatusPrijave { PODNETA, ODBIJENA, PRIHVACENA };

@Entity
public class PrijavaProfila implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column
    private String razlogPrijave;

    @Column
    @Temporal(TemporalType.DATE)
    private LocalDate datumPodnosenjaPrijave;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "korisnik_podnosilac_prijave_id")
    private Korisnik korisnikPodnosilac;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "korisnik_na_koga_se_odnosi_prijava_id")
    private Korisnik korisnikOdnosilac;

    @Column
    @Enumerated(EnumType.STRING)
    private StatusPrijave statusPrijave;

}

