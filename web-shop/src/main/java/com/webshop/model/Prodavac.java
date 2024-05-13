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
    @JoinColumn(name = "recenzija_za_prodavca")
    private Set<Recenzija> recenzije = new HashSet<>();

    private double prosecnaOcena;
}
