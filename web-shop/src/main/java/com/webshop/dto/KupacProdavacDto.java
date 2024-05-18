package com.webshop.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

//DODATO ZBOG PAMCENJA KOJI KUPAC JE KUPIO PROIZVOD OD KOG PRODAVCA
public class KupacProdavacDto implements Serializable {
    private ArrayList<Long> kupci;
    private ArrayList<Long> prodavci;

    //na istim indeksima je relacija kupac-prodavac
    //pr. na indeksu 2 je KupacId = 4, a ProdavacId = 2 --> kupac sa id-em 4 kupio od prodavca sa id-em 2
    public void dodajKupacProdavacID(Long KupacID, Long prodavacID) {
        this.kupci.add(KupacID);
        this.prodavci.add(prodavacID);
    }

    public ArrayList<Long> vratiKupce() { return kupci; }
    public ArrayList<Long> vratiProdavce() { return prodavci; }

    public KupacProdavacDto() {
        kupci = new ArrayList<>();
        prodavci = new ArrayList<>();
    }

}
