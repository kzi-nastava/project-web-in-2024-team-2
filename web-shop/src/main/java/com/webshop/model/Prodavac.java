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


    //promenjeno sa mappedBy = "korisnik" na mappedBy = "prodavac"(promenjeno i u klasi Recenzije)
    @OneToMany(mappedBy = "prodavac", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Recenzija> recenzije = new HashSet<>();

    private double prosecnaOcena;
}
