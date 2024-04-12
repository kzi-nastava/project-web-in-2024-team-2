package com.webshop.model;

import jakarta.persistence.*;

import java.io.Serializable;


@Entity
public class Kategorija implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String naziv;

    @OneToOne(mappedBy = "kategorija", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Proizvod proizvod;
}
