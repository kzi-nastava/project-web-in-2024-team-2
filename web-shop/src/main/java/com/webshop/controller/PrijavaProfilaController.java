package com.webshop.controller;

import com.webshop.model.Korisnik;
import com.webshop.model.PrijavaProfila;
import com.webshop.service.KorisnikService;
import com.webshop.service.ProizvodService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.webshop.model.StatusPrijave.*;
import static com.webshop.model.Uloga.ADMINISTRATOR;
import static com.webshop.model.Uloga.PRODAVAC;

import com.webshop.service.PrijavaProfilaService;


@RestController
public class PrijavaProfilaController {

    @Autowired
    private PrijavaProfilaService prijavaProfilaService;

    @Autowired
    KorisnikService korisnikService;

    @Autowired
    ProizvodService proizvodService;


    @PostMapping("/odbij-prijavu/{id}")
    public ResponseEntity<?> odbijPrijavu(/*@RequestBody TextNode razlogOdbijanja,*/ @PathVariable Long id, HttpSession session) {
        Korisnik korisnik = (Korisnik) session.getAttribute("korisnik");
        if(korisnik == null){
            return new ResponseEntity<>("Niste prijavljeni!", HttpStatus.FORBIDDEN);
        }
        if(korisnik.getUloga() != ADMINISTRATOR){
            return new ResponseEntity<>("Niste admin!", HttpStatus.FORBIDDEN);
        }
        PrijavaProfila prijavaProfila = prijavaProfilaService.getPrijavaProfilaById(id);
        if(prijavaProfila == null) {
            return new ResponseEntity<>("Ne postoji data prijava profila!", HttpStatus.FORBIDDEN);
        }
        if(prijavaProfila.getStatusPrijave() != PODNETA) {
            return new ResponseEntity<>("Prijava profila je vec obradjena", HttpStatus.BAD_REQUEST);
        }
        prijavaProfila.setStatusPrijave(ODBIJENA);
        prijavaProfilaService.savePrijava(prijavaProfila);
        return new ResponseEntity<>("Prijava Profila odbijena!", HttpStatus.OK);
    }

    @PostMapping("/prihvati-prijavu/{id}")
    public ResponseEntity<?> prihvatiPrijavu(@PathVariable Long id, HttpSession session) {
        Korisnik korisnik = (Korisnik) session.getAttribute("korisnik");
        if(korisnik == null) {
            return new ResponseEntity<>("Niste prijavljeni!", HttpStatus.FORBIDDEN);
        }
        if(korisnik.getUloga() != ADMINISTRATOR){
            return new ResponseEntity<>("Niste admin!", HttpStatus.FORBIDDEN);
        }
        PrijavaProfila prijavaProfila = prijavaProfilaService.getPrijavaProfilaById(id);
        if(prijavaProfila == null) {
            return new ResponseEntity<>("Ne postoji data prijava profila!", HttpStatus.FORBIDDEN);
        }
        if(prijavaProfila.getStatusPrijave() != PODNETA) {
            return new ResponseEntity<>("Prijava profila je vec obradjena", HttpStatus.BAD_REQUEST);
        }
        prijavaProfila.setStatusPrijave(PRIHVACENA);
        prijavaProfilaService.savePrijava(prijavaProfila);

        //ovaj deo ce da radi samo ako je tabela korisnik-kupljeni proizvodi prazna
        //jer se ne moze obrisati proizvod ako ga je kupac kupio
        if(prijavaProfila.getOdnosiSe().getUloga() == PRODAVAC) {
            Korisnik prodavac = korisnikService.getProdavacById(prijavaProfila.getOdnosiSe().getId());
            prodavac.setBlokiran(true);
            proizvodService.deleteProizvodByProdavacId(prodavac.getId());
            korisnikService.saveKorisnik(prodavac);
            return new ResponseEntity<>("Prijava Profila prihvacena!", HttpStatus.OK);
        }
        prijavaProfila.getOdnosiSe().setBlokiran(true);
        korisnikService.saveKorisnik(prijavaProfila.getOdnosiSe());
        return new ResponseEntity<>("Prijava Profila prihvacena!", HttpStatus.OK);
    }
}
