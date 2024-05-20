package com.webshop.controller;

import com.webshop.dto.AdminPrijavaProfilaDto;
import com.webshop.dto.OdbijenoDto;
import com.webshop.dto.PrijavaProfilaDto;
import com.webshop.model.*;
import com.webshop.service.EmailService;
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

import java.util.ArrayList;
import java.util.List;


@RestController
public class PrijavaProfilaController {

    @Autowired
    private PrijavaProfilaService prijavaProfilaService;

    @Autowired
    KorisnikService korisnikService;

    @Autowired
    ProizvodService proizvodService;

    @Autowired
    private EmailService emailService;

    @GetMapping("/prijave-profila")
    public ResponseEntity<?> getPrijaveProfila(HttpSession session) {
        Korisnik loggedKorisnik = (Korisnik) session.getAttribute("korisnik");
        if (loggedKorisnik == null) {
            return new ResponseEntity<>("Niste prijavljeni!", HttpStatus.FORBIDDEN);
        }
        if (loggedKorisnik.getUloga() != ADMINISTRATOR) {
            return new ResponseEntity<>("Nemate prava da vidite prijave!", HttpStatus.UNAUTHORIZED);
        }

        List<PrijavaProfila> prijavaProfilaList = prijavaProfilaService.getPrijavaProfilaList();
        List<AdminPrijavaProfilaDto> prijavaProfilaDtoList = new ArrayList<>();

        for (PrijavaProfila prijavaProfila : prijavaProfilaList) {
            prijavaProfilaDtoList.add(new AdminPrijavaProfilaDto(prijavaProfila));
        }

        return new ResponseEntity<>(prijavaProfilaDtoList, HttpStatus.OK);
    }

    @PostMapping("/odbij-prijavu/{id}")
    public ResponseEntity<?> odbijPrijavu(@RequestBody OdbijenoDto razlogOdbijanja, @PathVariable Long id, HttpSession session) {
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
        OdbijenoDto razlog = new OdbijenoDto(razlogOdbijanja);
        emailService.sendEmail(prijavaProfila.getOdnosiSe().getMail(), "PRIJAVA ODBIJENA!", razlog.getRazlogOdbijanja());
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
            List<Proizvod> proizvodList = proizvodService.getProizvodByProdavacId(prodavac.getId());
            StringBuilder opis = new StringBuilder();
            for (Proizvod proizvod : proizvodList) {
                if (proizvod.isProdat()) {
                    continue;
                }
                opis.append(proizvod.getNaziv()).append(", ");
                proizvodService.deleteProizvodById(proizvod.getId());
            }
            opis.replace(opis.length() - 2, opis.length(), "");
            emailService.sendEmail(prodavac.getMail(), "Obrisani proizvodi!", opis.toString());
            return new ResponseEntity<>("Prijava profila prodavca prihvacena!", HttpStatus.OK);
        }
        prijavaProfila.getOdnosiSe().setBlokiran(true);
        emailService.sendEmail(prijavaProfila.getOdnosiSe().getMail(), "Obavestenje o blokadi!", "Blokirani ste zbog necega!");
        return new ResponseEntity<>("Prijava profila kupca prihvacena!", HttpStatus.OK);
    }

    @PostMapping("/prijavi-prodavca/{id}")
    public ResponseEntity<?> prijaviProdavca(@PathVariable Long id, @RequestBody PrijavaProfilaDto razlogPrijave, HttpSession session) {
        Korisnik loggedKorisnik = (Korisnik) session.getAttribute("korisnik");
        if (loggedKorisnik == null) {
            return new ResponseEntity<>("Niste prijavljeni!", HttpStatus.FORBIDDEN);
        }
        if (loggedKorisnik.getUloga() != Uloga.KUPAC) {
            return new ResponseEntity<>("Niste kupac!", HttpStatus.FORBIDDEN);
        }

        PrijavaProfila prijavaProfila = prijavaProfilaService.prijaviProdavca(id, razlogPrijave, loggedKorisnik);
        if (prijavaProfila == null) {
            return new ResponseEntity<>("Kupac nije nista kupio od ovog prodavca!", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>("Uspesno podneta prijava!", HttpStatus.OK);
    }

    @PostMapping("/prijavi-kupca/{id}")
    public ResponseEntity<?> prijaviKupca(@PathVariable Long id, @RequestBody PrijavaProfilaDto razlogPrijave, HttpSession session) {
        Korisnik loggedKorisnik = (Korisnik) session.getAttribute("korisnik");
        if (loggedKorisnik == null) {
            return new ResponseEntity<>("Niste prijavljeni!", HttpStatus.FORBIDDEN);
        }
        if(loggedKorisnik.getUloga() != Uloga.PRODAVAC) {
            return new ResponseEntity<>("Niste prodavac!", HttpStatus.FORBIDDEN);
        }

        PrijavaProfila prijavaProfila = prijavaProfilaService.prijaviKupca(loggedKorisnik.getId(), razlogPrijave, korisnikService.getById(id).get());
        if (prijavaProfila == null) {
            return new ResponseEntity<>("Prodavac nije nista prodao ovom kupcu!", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>("Uspesno podneta prijava!", HttpStatus.OK);
    }
}