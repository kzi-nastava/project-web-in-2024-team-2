package com.webshop.dto;

import com.webshop.model.Korisnik;

import java.io.Serializable;
import java.time.LocalDate;

public class PrijavaProfilaDto implements Serializable {
    private LocalDate datumPodnosenjaPrijave;
    private String razlogPrijave;
    private Korisnik Podnosilac;
    private Korisnik OdnosiSe;

    public PrijavaProfilaDto() {}

    public LocalDate getDatumPodnosenjaPrijave() {
        return datumPodnosenjaPrijave;
    }

    public String getRazlogPrijave() {
        return razlogPrijave;
    }

    public Korisnik getPodnosilac() {
        return Podnosilac;
    }

    public Korisnik getOdnosiSe() {
        return OdnosiSe;
    }
}
