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

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Proizvod proizvod;
}
