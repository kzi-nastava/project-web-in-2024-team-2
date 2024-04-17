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
    private LocalDate datumRecenzije;


    //promenjeno sa private Korisnik korisnik na private Prodavac prodavac
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Prodavac prodavac;

    //dodate recenzije za kupca(vidi klasu kupac)
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Kupac kupac;
}
