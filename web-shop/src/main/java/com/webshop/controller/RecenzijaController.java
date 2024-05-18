package com.webshop.controller;

import com.webshop.dto.KupacProdavacDto;
import com.webshop.dto.RecenzijaDto;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static com.webshop.model.Uloga.KUPAC;

@RestController
public class RecenzijaController {

    @Autowired
    private RecenzijaService recenzijaService;

    @Autowired
    private KorisnikService korisnikService;

    KupacProdavacDto kupacProdavacDto = new KupacProdavacDto();

    /*
    @PostMapping("/oceni-prodavca/{id}")
    public ResponseEntity<?> rateProdavac(@PathVariable(name = "id") Long prodavacId, @RequestBody RecenzijaDto recenzijaDto, HttpSession session) {
//        Korisnik loggedKorisnik = (Korisnik) session.getAttribute("korisnik");
//
//        if (loggedKorisnik == null) {
//            return new ResponseEntity<>("Nema ulogovanog korisnika!", HttpStatus.FORBIDDEN);
//        }
//
//        if (loggedKorisnik.getUloga() != Uloga.KUPAC) {
//            return new ResponseEntity<>("Ulogovani loggedKorisnik nije kupac!", HttpStatus.FORBIDDEN);
//        }
//
//        Recenzija recenzija = new Recenzija(novaRecenzija);
//        recenzija.setDatumRecenzije(LocalDate.now());
//        loggedKorisnik.getDobijenaRecenzija().add(recenzija);
//        recenzijaService.saveRecenzija(recenzija);
//        return new ResponseEntity<>(recenzija, HttpStatus.OK);
//    }

        Korisnik prijavljeniKorisnik = (Korisnik) session.getAttribute("korisnik");
        if (prijavljeniKorisnik == null) {
            return new ResponseEntity<>("Nemate pravo za prijavu", HttpStatus.FORBIDDEN);
        }

        if (prijavljeniKorisnik.getUloga() == Uloga.KUPAC) {

            Optional<Korisnik> optionalKorisnik = korisnikService.getById(prodavacId);
            if (optionalKorisnik.isPresent() && optionalKorisnik.get().getUloga() == Uloga.PRODAVAC) {
                Prodavac prodavac = (Prodavac) optionalKorisnik.get();

                List<Long> kupci = kupacProdavacDto.vratiKupce();
                List<Long> prodavci = kupacProdavacDto.vratiProdavce();

                int i = 0;
                for (Long kupacID : kupci) {
                    if (Objects.equals(prijavljeniKorisnik.getId(), kupacID)) {
                        if (Objects.equals(prodavci.get(i), prodavac.getId())) {
                            Recenzija recenzija = new Recenzija(recenzijaDto.getOcena(), recenzijaDto.getKomentar(), recenzijaDto.getDatumRecenzije(), prijavljeniKorisnik);
                            recenzijaService.saveRecenzija(recenzija);
                            prodavac.prihvatiRecenziju(recenzija);
                            prodavac.setProsecnaOcena((prodavac.getProsecnaOcena() + recenzija.getOcena()) / 2);
                            break;
                        }
                    }
                    i++;
                }
                return new ResponseEntity<>("Uspesno dodata recenzija", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Nemate pravo za recenziju prodavca!", HttpStatus.FORBIDDEN);
            }

        } else {
            return new ResponseEntity<>("Nemate pravo za recenziju prodavca!", HttpStatus.FORBIDDEN);
        }
    }
    */
    //dodato
    @PostMapping("/oceni_prodavca/{id}")
    public ResponseEntity<String> oceniProdavca(@RequestBody RecenzijaDto recenzijaDto, @PathVariable Long id, HttpSession session)
    {
        Korisnik prijavljeniKorisnik = (Korisnik) session.getAttribute("korisnik");
        if(prijavljeniKorisnik == null)
        {
            return new ResponseEntity<>("Nemate pravo na recenziju", HttpStatus.FORBIDDEN);
        }

        if(prijavljeniKorisnik.getUloga() != KUPAC) {
            return new ResponseEntity<>("Nemate pravo na recenziju", HttpStatus.FORBIDDEN);
        }
        //Prodavac prodavac = korisnikService.getProdavacById(id);
        Optional<Korisnik> optionalKorisnik = korisnikService.getById(id);
        if(!optionalKorisnik.isPresent())
        {
            return new ResponseEntity<>("Ne postoji prodavac sa datim id-em", HttpStatus.NOT_FOUND);
        }
        Korisnik korisnik = optionalKorisnik.get();
        ArrayList<Long> kupci = kupacProdavacDto.vratiKupce();
        ArrayList<Long> prodavci = kupacProdavacDto.vratiProdavce();
        int i = 0, j = 0;
        for(Long kupacID : kupci) {
            if (Objects.equals(prijavljeniKorisnik.getId(), kupacID)) {
                if (Objects.equals(prodavci.get(i), korisnik.getId())) {
                    Recenzija recenzija = new Recenzija(recenzijaDto, prijavljeniKorisnik);
                    //recenzijeService.save(recenzija);
                    korisnik.dodajRecenziju(recenzija);
                    //korisnik.setProsecnaOcena((prodavac.getProsecnaOcena() + recenzija.getOcena()) / 2);
                    korisnikService.saveKorisnik(korisnik);
                    j++;
                    break;
                }
            }
            i++;
        }
        if(j != 0){
            return new ResponseEntity<>("Uspesno dodata recenzija", HttpStatus.OK);
        }
        return new ResponseEntity<>("Kupac nije kupio proizvod od datog prodavca", HttpStatus.NOT_FOUND);
    }

}


