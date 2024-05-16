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

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Korisnik podnosilac;

    //////
    public Recenzija(Integer ocena, String komentar, LocalDate datumRecenzije , Korisnik podnosilac) {
        this.ocena = ocena;
        this.komentar = komentar;
        this.datumRecenzije = datumRecenzije;
        this.podnosilac = podnosilac;
    }

    public Recenzija() {
    }

    public Long getId() {
        return id;
    }

    public Integer getOcena() {
        return ocena;
    }

    public String getKomentar() {
        return komentar;
    }

    public LocalDate getDatumRecenzije() {
        return datumRecenzije;
    }
}
