package com.webshop.dto;

import com.webshop.model.Korisnik;
import com.webshop.model.Recenzija;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * DTO for {@link com.webshop.model.Recenzija}
 */
public class RecenzijaDto implements Serializable {

    private Integer ocena;
    private String komentar;
    private LocalDate datumRecenzije;
    private Korisnik podnosilac;

    public RecenzijaDto() {}

    public RecenzijaDto(Integer ocena, String komentar, LocalDate datumRecenzije,
                        Korisnik podnosilac) {
        this.ocena = ocena;
        this.komentar = komentar;
        this.datumRecenzije = datumRecenzije;
        this.podnosilac = podnosilac;
    }

    public RecenzijaDto(Recenzija recenzija, Korisnik podnosilac) {
        this.ocena = recenzija.getOcena();
        this.komentar = recenzija.getKomentar();
        this.datumRecenzije = recenzija.getDatumRecenzije();
        this.podnosilac = podnosilac;
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

    public Korisnik getPodnosilac() {
        return podnosilac;
    }

    public RecenzijaDto(String komentar) {
        this.komentar = komentar;
    }
}
