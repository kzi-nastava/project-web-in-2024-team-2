package com.webshop.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.webshop.dto.RecenzijaDto;
import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
public class Recenzija implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Integer ocena;

    @Column
    private String komentar;

    @Column
    private LocalDate datumRecenzije;

    @ManyToOne(fetch = FetchType.EAGER /*cascade = CascadeType.ALL*/)
    @JsonIgnore
    private Korisnik podnosilac;

    public Recenzija() {}

    public Recenzija(Long id, Integer ocena, String komentar, LocalDate datumRecenzije, Korisnik podnosilac) {
        this.id = id;
        this.ocena = ocena;
        this.komentar = komentar;
        this.datumRecenzije = datumRecenzije;
        this.podnosilac = podnosilac;
    }

    public Recenzija(RecenzijaDto recenzijaDto) {
        this.ocena = recenzijaDto.getOcena();
        this.komentar = recenzijaDto.getKomentar();
        this.datumRecenzije = recenzijaDto.getDatumRecenzije();
    }

    public Recenzija(Integer ocena, String komentar, LocalDate datumRecenzije, Korisnik prijavljeniKorisnik) {
        this.ocena = ocena;
        this.komentar = komentar;
        this.datumRecenzije = datumRecenzije;
        this.podnosilac = prijavljeniKorisnik;
    }

    public Integer getOcena() {
        return ocena;
    }

    public void setOcena(Integer ocena) {
        this.ocena = ocena;
    }

    public String getKomentar() {
        return komentar;
    }

    public void setKomentar(String komentar) {
        this.komentar = komentar;
    }

    public LocalDate getDatumRecenzije() {
        return datumRecenzije;
    }

    public void setDatumRecenzije(LocalDate datumRecenzije) {
        this.datumRecenzije = datumRecenzije;
    }

    public Korisnik getPodnosilac() {
        return podnosilac;
    }

    public void setPodnosilac(Korisnik podnosilac) {
        this.podnosilac = podnosilac;
    }
}
