package com.webshop.dto;

import com.webshop.model.Recenzija;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * DTO for {@link com.webshop.model.Recenzija}
 */
public class RecenzijaProdavacaDto implements Serializable {
    private final Integer ocena;
    private final String komentar;
    private final LocalDate datumRecenzije;

    public RecenzijaProdavacaDto(Integer ocena, String komentar, LocalDate datumRecenzije) {
        this.ocena = ocena;
        this.komentar = komentar;
        this.datumRecenzije = datumRecenzije;
    }

    public RecenzijaProdavacaDto(Recenzija recenzija) {
        this.ocena = recenzija.getOcena();
        this.komentar = recenzija.getKomentar();
        this.datumRecenzije = recenzija.getDatumRecenzije();
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