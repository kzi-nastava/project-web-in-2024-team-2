package com.webshop.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Prodavac extends Korisnik implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "prodavac", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Proizvod> proizvodiNaProdaju = new HashSet<>();

    @OneToMany(mappedBy = "prodavac", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Recenzija> recenzije = new HashSet<>();

    @Column
    private Double prosecnaOcena;

    //zbog proizvod
    @OneToOne(mappedBy = "prodavacProizvoda",cascade = CascadeType.ALL)
    private Proizvod proizvod;

}

