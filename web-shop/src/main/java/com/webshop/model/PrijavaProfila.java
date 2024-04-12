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
    private LocalDate datumPodnosenjaPrijave;

    @Column
    private StatusPrijave statusPrijave;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Korisnik podnosilac;

    @OneToMany(mappedBy = "prijavljenKorisnik", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Korisnik> prijavljen;
}
