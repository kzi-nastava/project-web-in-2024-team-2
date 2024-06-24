package com.webshop.dto;

import com.webshop.model.Admin;
import com.webshop.model.Korisnik;
import com.webshop.model.PrijavaProfila;
import com.webshop.model.StatusPrijave;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * DTO for {@link com.webshop.model.PrijavaProfila}
 */
public class AdminPrijavaProfilaDto implements Serializable {

    private final Long id;
    private final LocalDate datumPodnosenjaPrijave;
    private final StatusPrijave statusPrijave;
    private final String razlogPrijave;
    private final Korisnik podnosilac;
    private final Korisnik odnosiSe;

    public AdminPrijavaProfilaDto(Long id, LocalDate datumPodnosenjaPrijave, StatusPrijave statusPrijave, String razlogPrijave, Korisnik podnosilac, Korisnik odnosiSe) {
        this.id = id;
        this.datumPodnosenjaPrijave = datumPodnosenjaPrijave;
        this.statusPrijave = statusPrijave;
        this.razlogPrijave = razlogPrijave;
        this.podnosilac = podnosilac;
        this.odnosiSe = odnosiSe;
    }

    public AdminPrijavaProfilaDto(PrijavaProfila prijavaProfila) {
        this.id = prijavaProfila.getId();
        this.datumPodnosenjaPrijave = prijavaProfila.getDatumPodnosenjaPrijave();
        this.statusPrijave = prijavaProfila.getStatusPrijave();
        this.razlogPrijave = prijavaProfila.getRazlogPrijave();
        this.podnosilac = prijavaProfila.getPodnosilac();
        this.odnosiSe = prijavaProfila.getOdnosiSe();
    }

    public Long getId() {
        return id;
    }

    public LocalDate getDatumPodnosenjaPrijave() {
        return datumPodnosenjaPrijave;
    }

    public StatusPrijave getStatusPrijave() {
        return statusPrijave;
    }

    public String getRazlogPrijave() {
        return razlogPrijave;
    }

    public Korisnik getPodnosilac() {
        return podnosilac;
    }

    public Korisnik getOdnosiSe() {
        return odnosiSe;
    }

}