package com.webshop.controller;

import com.webshop.dto.RecenzijaDto;
import com.webshop.model.Korisnik;
import com.webshop.model.Prodavac;
import com.webshop.model.Recenzija;
import com.webshop.service.KorisnikService;
import com.webshop.service.RecenzijeService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static com.webshop.model.Uloga.KUPAC;

@RestController
public class RecenzijeController {

    @Autowired
    private RecenzijeService recenzijeService;
    private KorisnikService korisnikService;

    //2.5 - Kupac moze da vidi recenzije svih prodavaca
    @GetMapping("/recenzije_prodavca/{id}")
    public ResponseEntity<Set<RecenzijaDto>> getRecenzija(@PathVariable Long id, HttpSession session) {
        Korisnik korisnik = (Korisnik) session.getAttribute("korisnik");
        if (korisnik.getUloga() == KUPAC) {
            Optional<Korisnik> optionalKorisnik = korisnikService.findById(id);
            if (optionalKorisnik.isPresent()) {
                Prodavac prodavac = (Prodavac) optionalKorisnik.get();
                Set<Recenzija> recenzija = prodavac.getRecenzije();
                Set<RecenzijaDto> recenzijadto = new HashSet<>();
                for(Recenzija r : recenzija) {
                    RecenzijaDto jedna_recenzija = new RecenzijaDto(r.getId(), r.getOcena(), r.getKomentar(), r.getDatumRecenzije());
                    recenzijadto.add(jedna_recenzija);
                }
                return new ResponseEntity<>(recenzijadto, HttpStatus.OK);

            }
            else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

        }
        else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}
