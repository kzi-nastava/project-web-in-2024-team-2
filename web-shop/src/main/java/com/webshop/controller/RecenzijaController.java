package com.webshop.controller;

import com.webshop.dto.KupacProdavacDto;
import com.webshop.dto.RecenzijaDto;
import com.webshop.dto.RecenzijaProdavacaDto;
import com.webshop.model.*;
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
import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

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

    @GetMapping("/recenzija/{id}")
    public ResponseEntity<?> getRecenzijaById(@PathVariable("id") Long id) {
        Recenzija recenzija = recenzijaService.findById(id);
        if (recenzija == null) {
            return new ResponseEntity<>("Ne postoji recenzija sa tim id-jem!", HttpStatus.NOT_FOUND);
        }
        RecenzijaDto recenzijaDto = new RecenzijaDto(recenzija);
        return new ResponseEntity<>(recenzijaDto, HttpStatus.OK);
    }

    @PostMapping("/oceni-prodavca/{id}")
    public ResponseEntity<?> rateProdavac(@PathVariable(name = "id") Long prodavacId, @RequestBody RecenzijaDto recenzijaDto, HttpSession session) {
        Korisnik loggedKorisnik = (Korisnik) session.getAttribute("korisnik");
        if (loggedKorisnik == null) {
            return new ResponseEntity<>("User not logged in", HttpStatus.UNAUTHORIZED);
        }
        if(loggedKorisnik.getUloga() != Uloga.KUPAC) {
            return new ResponseEntity<>("Prodavca moze da oceni samo kupac!", HttpStatus.FORBIDDEN);
        }
        Recenzija recenzija = new Recenzija(recenzijaDto, loggedKorisnik);
        if(recenzijaService.addRecenzija(recenzija, prodavacId, loggedKorisnik.getId()) == null) {
            return new ResponseEntity<>("Mozete oceniti prodavca samo ako ste od istog kupili proizvod!", HttpStatus.BAD_REQUEST);
        }

        //dodate tri linije koda ispod(radi dobijanja nove prosecne ocene prodavca)
        Prodavac prodavac = (Prodavac) korisnikService.getProdavacById(prodavacId);
        prodavac.setProsecnaOcena((prodavac.getProsecnaOcena()+recenzija.getOcena())/2);
        korisnikService.saveKorisnik(prodavac);

        return new ResponseEntity<>("Uspesno ste ostavili recenziju prodavca", HttpStatus.OK);
        //return new ResponseEntity<>(recenzijaService.addRecenzija(recenzija, prodavacId, loggedKorisnik.getId()), HttpStatus.OK);
    }

    @PostMapping("/oceni-kupca/{id}")
    public ResponseEntity<?> rateKupac(@PathVariable(name = "id") Long kupacId, @RequestBody RecenzijaDto recenzijaDto, HttpSession session) {
        Korisnik loggedKorisnik = (Korisnik) session.getAttribute("korisnik");
        if (loggedKorisnik == null) {
            return new ResponseEntity<>("User not logged in", HttpStatus.UNAUTHORIZED);
        }
        if(loggedKorisnik.getUloga() != Uloga.PRODAVAC) {
            return new ResponseEntity<>("Kupca moze da oceni samo prodavac!", HttpStatus.FORBIDDEN);
        }
        Recenzija recenzija = new Recenzija(recenzijaDto, loggedKorisnik);
        if(recenzijaService.addRecenzija(recenzija, loggedKorisnik.getId(), kupacId) == null) {
            return new ResponseEntity<>("Mozete oceniti prodavca samo ako ste od istog kupili proizvod!", HttpStatus.BAD_REQUEST);
        }

        //dodate tri linije koda ispod i getKupacById u korisnikService
        Kupac kupac = (Kupac) korisnikService.getKupacById(kupacId);
        kupac.setProsecnaOcena((kupac.getProsecnaOcena()+recenzija.getOcena())/2);
        korisnikService.saveKorisnik(kupac);

        return new ResponseEntity<>("Uspesno ste ostavili recenziju prodavca", HttpStatus.OK);

        //return new ResponseEntity<>(recenzijaService.addRecenzija(recenzija, loggedKorisnik.getId(), kupacId), HttpStatus.OK);
    }


    @PutMapping("/izmeni-recenziju/{id}")
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

    @GetMapping("/recenzije-prodavaca/{prodavacID}")
    public ResponseEntity<?> getRecenzijeProdavaca(@PathVariable Long prodavacID, HttpSession session) {
        Korisnik loggedKorisnik = (Korisnik) session.getAttribute("korisnik");

        if (loggedKorisnik == null) {
            return new ResponseEntity<>("Korisnik nije ulogovan!", HttpStatus.FORBIDDEN);
        }

        if (loggedKorisnik.getUloga() != Uloga.KUPAC) {
            return new ResponseEntity<>("Samo kupci mogu pristupiti ovoj funkcionalnosti!", HttpStatus.FORBIDDEN);
        }

        Set<Recenzija> recenzijeProdavca = recenzijaService.getRecenzijeProdavca(prodavacID, loggedKorisnik.getId());
        if(recenzijeProdavca == null) {
            return new ResponseEntity<>("Nema datih recenzija!", HttpStatus.NOT_FOUND);
        }

        List<RecenzijaProdavacaDto> recenzijeZaSlanje = new ArrayList<>();
        for(Recenzija r : recenzijeProdavca) {
            recenzijeZaSlanje.add(new RecenzijaProdavacaDto(r));
        }

        return new ResponseEntity<>(recenzijeZaSlanje, HttpStatus.OK);
    }

    @GetMapping("/recenzije-kupca/{kupacID}")
    public ResponseEntity<?> getRecenzijeKupca(@PathVariable Long kupacID, HttpSession session) {
        Korisnik loggedKorisnik = (Korisnik) session.getAttribute("korisnik");

        if(loggedKorisnik == null) {
            return new ResponseEntity<>("Korisnik nije ulogovan!", HttpStatus.FORBIDDEN);
        }

        if(loggedKorisnik.getUloga() != Uloga.PRODAVAC) {
            return new ResponseEntity<>("Samo prodavci mogu pristupiti ovoj funkcionalnosti!", HttpStatus.FORBIDDEN);
        }

        Set<Recenzija> recenzijeKupca = recenzijaService.getRecenzijeKupca(loggedKorisnik.getId(), kupacID);
        if(recenzijeKupca == null) {
            return new ResponseEntity<>("Nema datih recenzija!", HttpStatus.NOT_FOUND);
        }

        List<RecenzijaProdavacaDto> recenzijeZaSlanje = new ArrayList<>();
        for(Recenzija r : recenzijeKupca) {
            recenzijeZaSlanje.add(new RecenzijaProdavacaDto(r));
        }

        return new ResponseEntity<>(recenzijeZaSlanje, HttpStatus.OK);
    }

    @GetMapping("/moje-recenzije")
    public ResponseEntity<?> mojeRecenzije(HttpSession session) {
        Korisnik korisnik = (Korisnik) session.getAttribute("korisnik");
        if(korisnik == null) {
            return new ResponseEntity<>("Niste prijavljeni!", HttpStatus.FORBIDDEN);
        }
        if(korisnik.getUloga() != Uloga.PRODAVAC && korisnik.getUloga() != Uloga.KUPAC) {
            return new ResponseEntity<>("Niste ni kupac ni prodavac!", HttpStatus.FORBIDDEN);
        }
        List<Recenzija> recenzije = recenzijaService.mojeRecenzije(korisnik.getId());
        if(recenzije.isEmpty()) {
            return new ResponseEntity<>("Niste dali ni jednu recenziju!", HttpStatus.NOT_FOUND);
        }
        List<RecenzijaProdavacaDto> recenzijeZaSlanje = new ArrayList<>();
        for(Recenzija r : recenzije) {
            recenzijeZaSlanje.add(new RecenzijaProdavacaDto(r));
        }
        return new ResponseEntity<>(recenzijeZaSlanje, HttpStatus.OK);
    }

}


