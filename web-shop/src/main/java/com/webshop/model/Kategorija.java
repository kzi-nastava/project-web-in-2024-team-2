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

    //zbog proizvod
    @OneToOne(mappedBy = "kategorija",cascade = CascadeType.ALL)
    private Proizvod proizvod;

}
