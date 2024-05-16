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

    //2.5
    @GetMapping("/recenzije_prodavca/{id}")
    public ResponseEntity<Set<RecenzijaDto>> getRecenzija(@PathVariable Long id, HttpSession session) {
        Korisnik prijavljeniKorisnik = (Korisnik) session.getAttribute("korisnik");
        if (prijavljeniKorisnik.getUloga() == KUPAC) {
            Optional<Korisnik> optionalKorisnik = korisnikService.findById(id);
            if (optionalKorisnik.isPresent()) {
                Prodavac prodavac = (Prodavac) optionalKorisnik.get();
                Set<Recenzija> recenzija = prodavac.getRecenzije();
                Set<RecenzijaDto> recenzijadto = new HashSet<>();

                //provera da li je kupac ostavio recenziju da za prodavca, ako nije ne moze da vidi druge recenzije
                int i = 0;
                for(Recenzija r : recenzija) {
                    if(r.getPodnosilac() == prijavljeniKorisnik) { //da li se prijavljeniKorisnik treba konvertovati u kupca?
                        i++;
                    }
                }
                if(i == 0) { return new ResponseEntity<>(HttpStatus.BAD_REQUEST); }


                for(Recenzija r : recenzija) {
                    RecenzijaDto jedna_recenzija = new RecenzijaDto(r.getOcena(), r.getKomentar(), r.getDatumRecenzije());
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
