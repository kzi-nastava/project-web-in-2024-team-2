package com.webshop.model;

import com.webshop.dto.PrijavaProfilaDto;
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

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Korisnik podnosilac;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Korisnik odnosiSe;

    public PrijavaProfila() {}

    public PrijavaProfila(PrijavaProfilaDto prijavaProfilaDto) {
        this.datumPodnosenjaPrijave = LocalDate.now();
        this.statusPrijave = StatusPrijave.PODNETA;
        this.razlogPrijave = prijavaProfilaDto.getRazlogPrijave();
    }

    public PrijavaProfila(String razlogPrijave) {
        this.razlogPrijave = razlogPrijave;
        this.statusPrijave = StatusPrijave.PODNETA;
        this.datumPodnosenjaPrijave = LocalDate.now();
    }

    public PrijavaProfila(PrijavaProfila prijavaProfila) {
        this.id = prijavaProfila.getId();
        this.razlogPrijave = prijavaProfila.getRazlogPrijave();
        this.datumPodnosenjaPrijave = prijavaProfila.getDatumPodnosenjaPrijave();
        this.statusPrijave = prijavaProfila.getStatusPrijave();
        this.odnosiSe = prijavaProfila.getOdnosiSe();
        this.podnosilac = prijavaProfila.getPodnosilac();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDatumPodnosenjaPrijave() {
        return datumPodnosenjaPrijave;
    }

    public void setDatumPodnosenjaPrijave(LocalDate datumPodnosenjaPrijave) {
        this.datumPodnosenjaPrijave = datumPodnosenjaPrijave;
    }

    public StatusPrijave getStatusPrijave() {
        return statusPrijave;
    }

    public void setStatusPrijave(StatusPrijave statusPrijave) {
        this.statusPrijave = statusPrijave;
    }

    public String getRazlogPrijave() {
        return razlogPrijave;
    }

    public void setRazlogPrijave(String razlogPrijave) {
        this.razlogPrijave = razlogPrijave;
    }

    public Korisnik getPodnosilac() {
        return podnosilac;
    }

    public void setPodnosilac(Korisnik podnosilac) {
        this.podnosilac = podnosilac;
    }

    public Korisnik getOdnosiSe() {
        return odnosiSe;
    }

    public void setOdnosiSe(Korisnik odnosiSe) {
        this.odnosiSe = odnosiSe;
    }
}