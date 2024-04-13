package com.webshop.model;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
public class Ponuda implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Double cena;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "kupac_id")
    private Kupac kupac;


    //zbog proizvod
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "proizvod_id")
    private Proizvod proizvod;


}
