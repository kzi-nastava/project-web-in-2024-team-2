package com.webshop.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@DiscriminatorValue("Kupac")
public class Kupac extends Korisnik implements Serializable {

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Proizvod> kupljeniProizvodi = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Recenzija> recenzije = new HashSet<>();

    private Double prosecnaOcena;

    public Double getProsecnaOcena() {
        return prosecnaOcena;
    }

    public Set<Proizvod> getKupljeniProizvodi() {
        return kupljeniProizvodi;
    }

    public Set<Recenzija> getRecenzije() {
        return recenzije;
    }

    ///////////
    public void kupiProizvod(Proizvod p)
    {
        kupljeniProizvodi.add(p);
    }

}
