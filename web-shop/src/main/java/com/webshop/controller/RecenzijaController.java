package com.webshop.controller;

import com.webshop.dto.KupacProdavacDto;
import com.webshop.dto.RecenzijaDto;
import com.webshop.model.Korisnik;
import com.webshop.model.Prodavac;
import com.webshop.model.Recenzija;
import com.webshop.model.Uloga;
import com.webshop.service.KorisnikService;
import com.webshop.service.ProizvodService;
import com.webshop.service.RecenzijaService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
public class RecenzijaController {

    @Autowired
    private RecenzijaService recenzijaService;

    @PostMapping("/oceni-prodavca/{id}")
    public ResponseEntity<?> rateProdavac(@PathVariable(name = "id") Long prodavacId, @RequestBody RecenzijaDto recenzijaDto, HttpSession session) {
        Korisnik loggedKorisnik = (Korisnik) session.getAttribute("korisnik");
        Recenzija recenzija = new Recenzija(recenzijaDto, loggedKorisnik);
        return new ResponseEntity<>(recenzijaService.addRecenzija(recenzija, prodavacId, loggedKorisnik.getId()), HttpStatus.OK);
    }

}
