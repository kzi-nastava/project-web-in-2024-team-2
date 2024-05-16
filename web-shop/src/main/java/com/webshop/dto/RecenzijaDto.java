package com.webshop.dto;

import java.io.Serializable;
import java.time.LocalDate;

public class RecenzijaDto implements Serializable {
    private Long id;
    private int ocena;
    private String komentar;
    private LocalDate datum;

    public RecenzijaDto(int ocena, String komentar, LocalDate datum) {
        this.ocena = ocena;
        this.komentar = komentar;
        this.datum = datum;
    }

    public Long getID() {
        return id;
    }

    public int getOcena() {
        return ocena;
    }

    public String getKomentar() {
        return komentar;
    }

    public LocalDate getDatum() {
        return datum;
    }
}
