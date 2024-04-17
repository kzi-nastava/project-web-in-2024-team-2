package com.webshop.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@DiscriminatorValue("Kupac")
public class Kupac extends Korisnik implements Serializable {

    @OneToMany(mappedBy = "kupac", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Proizvod> kupljeniProizvodi = new HashSet<>();

    //dodate recenzije za kupca(dodato i u klasi recenzije)
    @OneToMany(mappedBy = "kupac", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Recenzija> recenzija = new HashSet<>();



    @OneToMany(mappedBy = "kupac", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Ponuda> ponude = new HashSet<>();

    private double prosecnaOcena;
}
