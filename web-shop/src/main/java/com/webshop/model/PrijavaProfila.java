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

    private LocalDate datumPodnosenjaPrijave;

    @Enumerated(value = EnumType.STRING)
    private StatusPrijave statusPrijave;

    private String razlogPrijave;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Korisnik podnosilac;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Korisnik odnosi_se;
}
