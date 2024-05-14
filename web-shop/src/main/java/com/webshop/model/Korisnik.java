package com.webshop.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.net.URL;
import java.time.LocalDate;
import java.util.Set;


@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "uloga", discriminatorType = DiscriminatorType.STRING)
public class Korisnik implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String ime;

    private String prezime;

    @Column(unique = true, nullable = false)
    private String username;

    private String password;

    @Column(unique = true, nullable = false)
    private String mail;

    @Column(unique = true, nullable = false)
    private String brojTelefona;

    private LocalDate datumRodjenja;

    private String profilnaURL;

    private String opis;

    @Column(insertable = false, updatable = false)
    private Uloga uloga;

    private boolean blokiran;

    /*
    @OneToOne
    PrijavaProfila prijava1;

    @OneToOne
    PrijavaProfila prijava2;
    */

    public Long getId() { return id;}
    public void setId(Long id) { this.id = id;}

    public String getIme() { return ime;}
    public String setIme(String ime) { this.ime = ime; return ime;}

    public String getPrezime() { return prezime;}
    public void setPrezime(String prezime) { this.prezime = prezime;}

    public String getUsername() { return username;}
    public void setUsername(String username) { this.username = username;}

    public String getPassword() { return password;}
    public void setPassword(String password) { this.password = password;}

    public String getMail() { return mail;}
    public void setMail(String mail) { this.mail = mail;}

    public String getBrojTelefona() { return brojTelefona;}
    public void setBrojTelefona(String brojTelefona) { this.brojTelefona = brojTelefona;}

    public LocalDate getDatumRodjenja() { return datumRodjenja;}
    public void setDatumRodjenja(LocalDate DatumRodjenja) {this.datumRodjenja = DatumRodjenja;}

    public String getProfilnaURL() { return profilnaURL;}
    public void setProfilnaURL(String profilnaURL) { this.profilnaURL = profilnaURL;}

    public String getOpis() { return opis;}
    public void setOpis(String opis) { this.opis = opis;}

    public Uloga getUloga() { return uloga;}
    public void setUloga(Uloga uloga) { this.uloga = uloga;}

    public boolean isBlokiran() { return blokiran;}
    public void setBlokiran(boolean blokiran) { this.blokiran = blokiran;}

    /*
    //ovde stavljeno zbog pretrage
    public double prosecnaOcena;
    public double getProsecnaOcena() {return prosecnaOcena;}
    */
}
