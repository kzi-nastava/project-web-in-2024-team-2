package com.webshop.controller;

import com.webshop.dto.KupacProdavacDto;
import com.webshop.dto.RecenzijaDto;
import com.webshop.dto.RecenzijaProdavacaDto;
import com.webshop.model.Korisnik;
import com.webshop.model.Prodavac;
import com.webshop.model.Recenzija;
import com.webshop.model.Uloga;
import com.webshop.service.KorisnikService;
import com.webshop.service.RecenzijaService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.*;

import static com.webshop.model.Uloga.KUPAC;

@RestController
public class RecenzijaController {

    @Autowired
    private RecenzijaService recenzijaService;

    @Autowired
    private KorisnikService korisnikService;

    @GetMapping("/recenzije")
    public ResponseEntity<?> GetRecenzije(HttpSession session) {
        Korisnik loggedKorisnik = (Korisnik) session.getAttribute("korisnik");
        if (loggedKorisnik == null) {
            return new ResponseEntity<>("Korisnik nije ulogovan!", HttpStatus.FORBIDDEN);
        }
        if (loggedKorisnik.getUloga() != Uloga.ADMINISTRATOR) {
            return new ResponseEntity<>("Korisnik nema prava da vidi sve recenzije!", HttpStatus.UNAUTHORIZED);
        }

        List<Recenzija> recenzijaList = recenzijaService.getAllRecenzija();
        List<RecenzijaDto> recenzijaDtoList = new ArrayList<>();

        for (Recenzija recenzija : recenzijaList) {
            recenzijaDtoList.add(new RecenzijaDto(recenzija));
        }

        return new ResponseEntity<>(recenzijaDtoList, HttpStatus.OK);

    }

    @PostMapping("/oceni-prodavca/{id}")
    public ResponseEntity<?> rateProdavac(@PathVariable(name = "id") Long prodavacId, @RequestBody RecenzijaDto recenzijaDto, HttpSession session) {
        Korisnik loggedKorisnik = (Korisnik) session.getAttribute("korisnik");
        if (loggedKorisnik == null) {
            return new ResponseEntity<>("User not logged in", HttpStatus.UNAUTHORIZED);
        }
        Recenzija recenzija = new Recenzija(recenzijaDto, loggedKorisnik);
        return new ResponseEntity<>(recenzijaService.addRecenzija(recenzija, prodavacId, loggedKorisnik.getId()), HttpStatus.OK);
    }

    @PostMapping("/izmeni-recenziju/{id}")
    public ResponseEntity<?> izmeniRecenziju(@PathVariable Long id, @RequestBody RecenzijaDto komentar, HttpSession session) {
        Korisnik korisnik = (Korisnik) session.getAttribute("korisnik");
        if (korisnik == null || korisnik.getUloga() != Uloga.ADMINISTRATOR) {
            return new ResponseEntity<>("Ne mozete izmeniti recenziju!",HttpStatus.UNAUTHORIZED);
        }
        Recenzija recenzija = recenzijaService.findById(id);
        if(recenzija == null) {
            return new ResponseEntity<>("Ne postoji data recenzija!",HttpStatus.NOT_FOUND);
        }
        if(komentar.getKomentar() == null) {
            return new ResponseEntity<>("Niste uneli izmenu recenzije!", HttpStatus.BAD_REQUEST);
        }
        recenzija.setKomentar(komentar.getKomentar());
        recenzijaService.saveRecenzija(recenzija);
        return new ResponseEntity<>("Uspesno izmenjena recenzija", HttpStatus.OK);
    }

    @DeleteMapping("/obrisi-recenziju/{id}")
    public ResponseEntity<?> obrisiRecenziju(@PathVariable Long id, HttpSession session) {
        Korisnik korisnik = (Korisnik) session.getAttribute("korisnik");
        if (korisnik == null || korisnik.getUloga() != Uloga.ADMINISTRATOR) {
            return new ResponseEntity<>("Ne mozete obrisati recenziju!",HttpStatus.UNAUTHORIZED);
        }
        Recenzija recenzija = recenzijaService.findById(id);
        if(recenzija == null) {
            return new ResponseEntity<>("Ne postoji data recenzija!",HttpStatus.NOT_FOUND);
        }

        List<Korisnik> korisnici = korisnikService.getKorisnikList();

        for (Korisnik k : korisnici) {
            Set<Recenzija> dobijeneRecenzije = k.getDobijenaRecenzija();
            for (Recenzija r : dobijeneRecenzije) {
                if (r.getId().equals(id)) {
                    k.getDobijenaRecenzija().remove(r);
                }
            }
        }

        recenzijaService.deleteRecenzijaById(id);
        return new ResponseEntity<>("Uspesno obrisana recenzija", HttpStatus.OK);
    }

    @GetMapping("/recenzije-prodavaca")
    public ResponseEntity<?> getRecenzijeProdavaca(HttpSession session) {
        Korisnik loggedKorisnik = (Korisnik) session.getAttribute("korisnik");

        if (loggedKorisnik == null) {
            return new ResponseEntity<>("Korisnik nije ulogovan!", HttpStatus.FORBIDDEN);
        }

        if (loggedKorisnik.getUloga() != Uloga.KUPAC) {
            return new ResponseEntity<>("Samo kupci mogu pristupiti ovoj funkcionalnosti!", HttpStatus.FORBIDDEN);
        }

        List<RecenzijaProdavacaDto> listaRecenzija = recenzijaService.getRecenzijaList(loggedKorisnik.getId());
        if (listaRecenzija.isEmpty()) {
            return new ResponseEntity<>("Recenzija ne postoji!", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(listaRecenzija, HttpStatus.OK);
    }

}


