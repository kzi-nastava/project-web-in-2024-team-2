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

    private final LocalDate datumPodnosenjaPrijave;
    private final StatusPrijave statusPrijave;
    private final String razlogPrijave;

    public AdminPrijavaProfilaDto(LocalDate datumPodnosenjaPrijave, StatusPrijave statusPrijave, String razlogPrijave) {
        this.datumPodnosenjaPrijave = datumPodnosenjaPrijave;
        this.statusPrijave = statusPrijave;
        this.razlogPrijave = razlogPrijave;
    }

    public AdminPrijavaProfilaDto(PrijavaProfila prijavaProfila) {
        this.datumPodnosenjaPrijave = prijavaProfila.getDatumPodnosenjaPrijave();
        this.statusPrijave = prijavaProfila.getStatusPrijave();
        this.razlogPrijave = prijavaProfila.getRazlogPrijave();
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

}