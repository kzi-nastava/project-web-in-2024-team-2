package com.webshop.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.webshop.dto.KorisnikDto;
import com.webshop.dto.KupacDto;
import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "uloga", discriminatorType = DiscriminatorType.INTEGER)
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

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Recenzija> dobijenaRecenzija = new HashSet<>();

    public Korisnik() {}

    public Korisnik(Long id, String ime, String prezime, String username, String password, String mail, String brojTelefona, LocalDate datumRodjenja, String profilnaURL, String opis, Uloga uloga, boolean blokiran) {
        this.id = id;
        this.ime = ime;
        this.prezime = prezime;
        this.username = username;
        this.password = password;
        this.mail = mail;
        this.brojTelefona = brojTelefona;
        this.datumRodjenja = datumRodjenja;
        this.profilnaURL = profilnaURL;
        this.opis = opis;
        this.uloga = uloga;
        this.blokiran = blokiran;
    }

    public Korisnik(Korisnik korisnik) {
        this.id = korisnik.getId();
        this.ime = korisnik.getIme();
        this.prezime = korisnik.getPrezime();
        this.username = korisnik.getUsername();
        this.password = korisnik.getPassword();
        this.mail = korisnik.getMail();
        this.brojTelefona = korisnik.getBrojTelefona();
        this.datumRodjenja = korisnik.getDatumRodjenja();
        this.profilnaURL = korisnik.getProfilnaURL();
        this.opis = korisnik.getOpis();
        this.blokiran = korisnik.isBlokiran();
    }

    public Korisnik(KorisnikDto korisnikDto) {
        this.ime = korisnikDto.getIme();
        this.prezime = korisnikDto.getPrezime();
        this.username = korisnikDto.getUsername();
        this.password = korisnikDto.getPassword();
        this.mail = korisnikDto.getMail();
        this.brojTelefona = korisnikDto.getBrojTelefona();
    }

    public Korisnik(KupacDto kupacDto) {
        this.ime = kupacDto.getIme();
        this.prezime = kupacDto.getPrezime();
        this.username = kupacDto.getUsername();
        this.password = kupacDto.getPassword();
        this.mail = kupacDto.getMail();
        this.brojTelefona = kupacDto.getBrojTelefona();
        this.datumRodjenja = kupacDto.getDatumRodjenja();
        this.profilnaURL = kupacDto.getProfilnaURL();
        this.opis = kupacDto.getOpis();
    }

    public Long getId() {
        return id;
    }

    public String getIme() {
        return ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getMail() {
        return mail;
    }

    public String getBrojTelefona() {
        return brojTelefona;
    }

    public LocalDate getDatumRodjenja() {
        return datumRodjenja;
    }

    public String getProfilnaURL() {
        return profilnaURL;
    }

    public String getOpis() {
        return opis;
    }

    public Uloga getUloga() {
        return uloga;
    }

    public boolean isBlokiran() {
        return blokiran;
    }

    public Set<Recenzija> getDobijenaRecenzija() {
        return dobijenaRecenzija;
    }

    public void setUloga(Uloga uloga) {
        this.uloga = uloga;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public void setDatumRodjenja(LocalDate datumRodjenja) {
        this.datumRodjenja = datumRodjenja;
    }

    public void setProfilnaURL(String profilnaURL) {
        this.profilnaURL = profilnaURL;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public void setBrojTelefona(String brojTelefona) {
        this.brojTelefona = brojTelefona;
    }

    public void setBlokiran(boolean blokiran) {
        this.blokiran = blokiran;
    }

}