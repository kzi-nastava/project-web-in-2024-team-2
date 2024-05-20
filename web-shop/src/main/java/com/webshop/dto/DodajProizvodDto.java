package com.webshop.dto;

import com.webshop.model.Prodavac;
import com.webshop.model.TipProdaje;

import java.io.Serializable;

/**
 * DTO for {@link com.webshop.model.Proizvod}
 */
public class DodajProizvodDto {

    private final String naziv;
    private final String opis;
    private final String profilnaURL;
    private final double cena;
    private final TipProdaje tipProdaje;
    private final String nazivKategorije;
    private final Prodavac prodavac;

    public DodajProizvodDto(String naziv, String opis, String profilnaURL, Double cena, TipProdaje tipProdaje, String nazivKategorije, Prodavac prodavac) {
        this.naziv = naziv;
        this.opis = opis;
        this.profilnaURL = profilnaURL;
        this.cena = cena;
        this.tipProdaje = tipProdaje;
        this.nazivKategorije = nazivKategorije;
        this.prodavac = prodavac;
    }

    public String getNaziv() {
        return naziv;
    }

    public String getOpis() {
        return opis;
    }

    public String getProfilnaURL() {
        return profilnaURL;
    }

    public double getCena() {
        return cena;
    }

    public TipProdaje getTipProdaje() {
        return tipProdaje;
    }

    public String getNazivKategorije() {
        return nazivKategorije;
    }
}
