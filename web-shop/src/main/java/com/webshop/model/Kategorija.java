package com.webshop.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Set;


@Entity
public class Kategorija implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String naziv;

    @OneToMany(mappedBy = "kategorija", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Proizvod> proizvod;
}
