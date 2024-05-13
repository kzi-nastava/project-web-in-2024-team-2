package com.webshop.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@DiscriminatorValue("Kupac")
public class Kupac extends Korisnik implements Serializable {


    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "kupio_kupac_id")
    private Set<Proizvod> kupljeniProizvodi = new HashSet<>();


    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "recenzija_za_kupca")
    private Set<Recenzija> recenzije = new HashSet<>();

    private double prosecnaOcena;
}
