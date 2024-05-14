package com.webshop.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.net.URL;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Proizvod implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String naziv;

    private String opis;

    private String profilnaURL;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Kategorija kategorija;

    private Double cena;

    @Enumerated(EnumType.STRING)
    private TipProdaje tipProdaje;

    private LocalDate datumObjave;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Ponuda> ponuda = new HashSet<>();

    private boolean recenzijaKupcaOstavljena;

    private boolean recenzijaProdavcaOstavljena;

    private boolean prodat;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Prodavac prodavac;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public String getProfilnaURL() {
        return profilnaURL;
    }

    public void setProfilnaURL(String profilnaURL) {
        this.profilnaURL = profilnaURL;
    }

    public Kategorija getKategorija() {
        return kategorija;
    }

    public void setKategorija(Kategorija kategorija) {
        this.kategorija = kategorija;
    }

    public Double getCena() {
        return cena;
    }

    public void setCena(Double cena) {
        this.cena = cena;
    }

    public TipProdaje getTipProdaje() {
        return tipProdaje;
    }

    public void setTipProdaje(TipProdaje tipProdaje) {
        this.tipProdaje = tipProdaje;
    }

    public LocalDate getDatumObjave() {
        return datumObjave;
    }

    public void setDatumObjave(LocalDate datumObjave) {
        this.datumObjave = datumObjave;
    }

    public Set<Ponuda> getPonuda() {
        return ponuda;
    }

    public void setPonuda(Set<Ponuda> ponuda) {
        this.ponuda = ponuda;
    }

    public boolean isRecenzijaKupcaOstavljena() {
        return recenzijaKupcaOstavljena;
    }

    public void setRecenzijaKupcaOstavljena(boolean recenzijaKupcaOstavljena) {
        this.recenzijaKupcaOstavljena = recenzijaKupcaOstavljena;
    }

    public boolean isRecenzijaProdavcaOstavljena() {
        return recenzijaProdavcaOstavljena;
    }

    public void setRecenzijaProdavcaOstavljena(boolean recenzijaProdavcaOstavljena) {
        this.recenzijaProdavcaOstavljena = recenzijaProdavcaOstavljena;
    }

    public boolean isProdat() {
        return prodat;
    }

    public void setProdat(boolean prodat) {
        this.prodat = prodat;
    }

    public Prodavac getProdavac() {
        return prodavac;
    }

    public void setProdavac(Prodavac prodavac) {
        this.prodavac = prodavac;
    }
}
