package com.webshop.dto;

import com.webshop.model.Kategorija;
import com.webshop.model.Proizvod;
import com.webshop.model.TipProdaje;

import java.io.Serializable;
import java.net.URL;

/**
 * DTO for {@link com.webshop.model.Proizvod}
 */
public class ProizvodDto implements Serializable {

    private final Long id;
    private final String naziv;
    private final String opis;
    private final String profilnaURL;
    private final Kategorija kategorija;
    private final Double cena;
    private final TipProdaje tipProdaje;

    public ProizvodDto(Long id, String naziv, String opis, String profilnaURL, Kategorija kategorija, Double cena, TipProdaje tipProdaje) {
        this.id = id;
        this.naziv = naziv;
        this.opis = opis;
        this.profilnaURL = profilnaURL;
        this.kategorija = kategorija;
        this.cena = cena;
        this.tipProdaje = tipProdaje;
    }

    public ProizvodDto(Proizvod proizvod) {
        this.id = proizvod.getId();
        this.naziv = proizvod.getNaziv();
        this.opis = proizvod.getOpis();
        this.profilnaURL = proizvod.getProfilnaURL();
        this.kategorija = proizvod.getKategorija();
        this.cena = proizvod.getCena();
        this.tipProdaje = proizvod.getTipProdaje();
    }

    public Long getId() {
        return id;
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

    public Kategorija getKategorija() {
        return kategorija;
    }

    public Double getCena() {
        return cena;
    }

    public TipProdaje getTipProdaje() {
        return tipProdaje;
    }
}