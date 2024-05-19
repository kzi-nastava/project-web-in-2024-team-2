package com.webshop.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;

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
    private Korisnik odnosiSe;

    public StatusPrijave getStatusPrijave() {
        return statusPrijave;
    }

    public void setStatusPrijave(StatusPrijave statusPrijave) {
        this.statusPrijave = statusPrijave;
    }

    public Korisnik getOdnosiSe() {
        return odnosiSe;
    }

    public Long getId() {
        return id;
    }

    public LocalDate getDatumPodnosenjaPrijave() {
        return datumPodnosenjaPrijave;
    }

    public String getRazlogPrijave() {
        return razlogPrijave;
    }

    public Korisnik getPodnosilac() {
        return podnosilac;
    }
}