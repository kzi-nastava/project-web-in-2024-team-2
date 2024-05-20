package com.webshop.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.webshop.model.Korisnik;
import com.webshop.model.PrijavaProfila;
import com.webshop.model.StatusPrijave;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * DTO for {@link com.webshop.model.PrijavaProfila}
 */
public class PrijavaProfilaDto implements Serializable {

    private final String razlogPrijave;

    @JsonCreator
    public PrijavaProfilaDto(String razlogPrijave) {
        this.razlogPrijave = razlogPrijave;
    }

    public PrijavaProfilaDto(PrijavaProfila prijavaProfila) {
        this.razlogPrijave = prijavaProfila.getRazlogPrijave();
    }

    public PrijavaProfilaDto(PrijavaProfilaDto prijavaProfilaDto) {
        this.razlogPrijave = prijavaProfilaDto.getRazlogPrijave();
    }

    public String getRazlogPrijave() {
        return razlogPrijave;
    }

}