package com.webshop.controller;

import com.webshop.dto.ProizvodDto;
import com.webshop.model.*;
import com.webshop.repository.ProizvodRepository;
import com.webshop.service.KorisnikService;
import com.webshop.service.ProizvodService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.webshop.dto.KorisnikDto;

import java.util.*;

import static com.webshop.model.TipProdaje.FIKSNA_CENA;
import static com.webshop.model.Uloga.KUPAC;
import static com.webshop.model.Uloga.PRODAVAC;


@RestController
public class KorisnikController {

    @Autowired
    private KorisnikService korisnikService;

    @Autowired
    private ProizvodService proizvodService;

    //2.1 i 3.1
    @PostMapping("/azuriraj-profil")
    public ResponseEntity<String> azurirajProfil(@RequestBody KorisnikDto korisnikDto, HttpSession session) {
        Korisnik prijavljeniKorisnik = (Korisnik) session.getAttribute("korisnik");

        if (prijavljeniKorisnik == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        if (prijavljeniKorisnik.getUloga() == KUPAC || prijavljeniKorisnik.getUloga() == PRODAVAC) {

            String staraLozinka = prijavljeniKorisnik.getPassword();
            String stariUsername = prijavljeniKorisnik.getUsername();
            String stariMail = prijavljeniKorisnik.getMail();

            prijavljeniKorisnik.setIme(korisnikDto.getIme());
            prijavljeniKorisnik.setPrezime(korisnikDto.getPrezime());
            prijavljeniKorisnik.setUsername(korisnikDto.getUsername());
            prijavljeniKorisnik.setMail(korisnikDto.getMail());
            prijavljeniKorisnik.setBrojTelefona(korisnikDto.getBrojTelefona());
            prijavljeniKorisnik.setPassword(korisnikDto.getPassword());
            prijavljeniKorisnik.setDatumRodjenja(korisnikDto.getDatumRodjenja());
            prijavljeniKorisnik.setProfilnaURL(korisnikDto.getProfilnaURL());
            prijavljeniKorisnik.setOpis(korisnikDto.getOpis());


            if (!staraLozinka.equals(prijavljeniKorisnik.getPassword()) || !stariUsername.equals(prijavljeniKorisnik.getUsername()) || !stariMail.equals(prijavljeniKorisnik.getMail())) {
                if(!korisnikDto.getPotvrdaLozinke().equals(staraLozinka))
                {
                    return new ResponseEntity<>("Lozinka nije ispravna!", HttpStatus.BAD_REQUEST);
                }
                else
                {
                    korisnikService.save(prijavljeniKorisnik);
                }
            }
            else
            {
                korisnikService.save(prijavljeniKorisnik);
            }
            return new ResponseEntity<>("Profil uspešno ažuriran!", HttpStatus.OK);
            }
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }

    //2.2 i 3.2
    @GetMapping("/prikaz-profila/{id}")
    public ResponseEntity<KorisnikDto> prikazProfila(@PathVariable Long id) {
        Optional<Korisnik> opk = korisnikService.findById(id);
        if (opk.isPresent()) {
            Korisnik k = opk.get();
            double ocena = korisnikService.getProsecnaOcena(id);
            Set<Proizvod> proizvodi = korisnikService.getProizvod(id);
            Set<Recenzija> recenzije = korisnikService.getRecenzija(id);
            KorisnikDto kdto = new KorisnikDto(k.getIme(), k.getPrezime(), k.getUsername(), k.getDatumRodjenja(), k.getProfilnaURL(), k.getOpis(), k.getUloga(), k.isBlokiran(), ocena, proizvodi, recenzije);
            return new ResponseEntity<>(kdto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /////////////////////////////////
    //2.3
    @PostMapping("/kupovina-proizvoda/{productID}")
    public ResponseEntity<String> kupiProizvod(@PathVariable Long productID, HttpSession session) {
        Optional<Proizvod> op = proizvodService.findById(productID);
        Korisnik prijavljeniKorisnik = (Korisnik) session.getAttribute("korisnik");

        if (prijavljeniKorisnik.getUloga() == KUPAC) {
            if (op.isPresent()) {
                Proizvod p = op.get();
                korisnikService.kupiProizvod(p, prijavljeniKorisnik.getId());
                return new ResponseEntity<>("Kupljen proizvod!", HttpStatus.OK);
            }
            else {
                return new ResponseEntity<>("Proizvod sa datim id-em ne postoji!", HttpStatus.NOT_FOUND);
            }
        }
        else
        {
            return new ResponseEntity<>("Nemate mogucnost kupovine proizvoda!", HttpStatus.UNAUTHORIZED);
        }
    }

}

