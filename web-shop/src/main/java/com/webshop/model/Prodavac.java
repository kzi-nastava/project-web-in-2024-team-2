package com.webshop.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@DiscriminatorValue("Prodavac")
public class Prodavac extends Korisnik implements Serializable {

    @OneToMany(mappedBy = "prodavac", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Proizvod> proizvodiNaProdaju = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Recenzija> recenzije = new HashSet<>();

    private Double prosecnaOcena;

    public Double getProsecnaOcena() {
        return prosecnaOcena;
    }

    public Set<Proizvod> getProizvodiNaProdaju() {
        return proizvodiNaProdaju;
    }

    public Set<Recenzija> getRecenzije() {
        return recenzije;
    }
}
