package com.webshop.controller;

import com.webshop.dto.DodajProizvodDto;
import com.webshop.dto.ProizvodDto;
import com.webshop.model.*;
import com.webshop.service.KategorijaService;
import com.webshop.service.KorisnikService;
import com.webshop.service.ProizvodService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
public class ProizvodController {

    @Autowired
    private ProizvodService proizvodService;

    @Autowired
    private KategorijaService kategorijaService;

    @Autowired
    private KorisnikService korisnikService;

    @PostMapping("/dodaj-proizvod")
    public ResponseEntity<?> dodajProizvod(@RequestBody DodajProizvodDto dodajDto, HttpSession session) {
        Prodavac prodavac = (Prodavac) session.getAttribute("korisnik");
        if(prodavac == null || prodavac.getUloga() != Uloga.PRODAVAC) {
            return new ResponseEntity<>("Nemate pravo za postavljanje proizvoda na prodaju!", HttpStatus.FORBIDDEN);
        }
        if(!kategorijaService.isExistentByNaziv(dodajDto.getNazivKategorije())) {
            kategorijaService.saveKategorija(new Kategorija(dodajDto.getNazivKategorije()));
        }
        Kategorija kategorija = kategorijaService.findByNaziv(dodajDto.getNazivKategorije());
        Proizvod proizvod = new Proizvod(dodajDto.getNaziv(), dodajDto.getOpis(), dodajDto.getProfilnaURL(), kategorija, dodajDto.getCena(), dodajDto.getTipProdaje(), prodavac, LocalDate.now());
        proizvodService.saveProizvod(proizvod);
        return new ResponseEntity<>("Uspesno postavljen proizvod na prodaju!", HttpStatus.OK);
    }

    @PostMapping("/azuriraj-proizvod/{id}")
    public ResponseEntity<?> azurirajProizvod(@RequestBody DodajProizvodDto dodajDto, @PathVariable Long id, HttpSession session) {
        Korisnik korisnik = (Korisnik) session.getAttribute("korisnik");
        if(korisnik == null || korisnik.getUloga() != Uloga.PRODAVAC) {
            return new ResponseEntity<>("Nemate pravo na azuriranje proizvoda!", HttpStatus.FORBIDDEN);
        }
        Proizvod proizvod = proizvodService.getProizvodById(id);
        if(proizvod == null) {
            return new ResponseEntity<>("Ne postoji proizvod sa datim id-em", HttpStatus.BAD_REQUEST);
        }
        if(!Objects.equals(proizvod.getProdavac().getId(), korisnik.getId())) {
            return new ResponseEntity<>("Ne mozete da azurirate proizvod drugog prodavca", HttpStatus.FORBIDDEN);
        }
        if (proizvod.getTipProdaje() == TipProdaje.FIKSNA_CENA || (proizvod.getTipProdaje() == TipProdaje.AUKCIJA && proizvod.getPonuda().isEmpty())) {
            proizvod.setNaziv(dodajDto.getNaziv());
            proizvod.setOpis(dodajDto.getOpis());
            proizvod.setProfilnaURL(dodajDto.getProfilnaURL());
            proizvod.setCena(dodajDto.getCena());
            proizvod.setTipProdaje(dodajDto.getTipProdaje());
            Kategorija kategorija = new Kategorija(dodajDto.getNazivKategorije());
            if(!kategorijaService.isExistentByNaziv(dodajDto.getNazivKategorije())) {
                kategorijaService.saveKategorija(kategorija);
            }
            proizvod.setKategorija(kategorija);
            proizvodService.saveProizvod(proizvod);
            return new ResponseEntity<>("Izmenili ste podatke o datom proizvodu!", HttpStatus.OK);
        }
        return new ResponseEntity<>("Ne mozete izmeniti podatke o proizvodu jer postoje aktivne ponude za dati proizvod!", HttpStatus.FORBIDDEN);
    }



    @GetMapping("/all-products")
    public ResponseEntity<List<ProizvodDto>> getProizvodi(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size) {
        Page<Proizvod> proizvodi = proizvodService.getProizvodList(page, size);
        List<ProizvodDto> proizvodDtos = new ArrayList<>();

        if (!proizvodi.hasContent()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        for (Proizvod proizvod : proizvodi) {
            proizvodDtos.add(new ProizvodDto(proizvod));
        }
        return new ResponseEntity<>(proizvodDtos, HttpStatus.OK);
    }


    @GetMapping("/product/{id}")
    public ResponseEntity<ProizvodDto> getProizvod(@PathVariable Long id) {
        Proizvod proizvod =  proizvodService.getProizvodById(id);

        if (proizvod == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(new ProizvodDto(proizvod), HttpStatus.OK);
    }

    private ResponseEntity<List<ProizvodDto>> getListResponseEntity(List<Proizvod> proizvodList) {
        List<ProizvodDto> proizvodDtos = new ArrayList<>();

        if (proizvodList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        for (Proizvod proizvod : proizvodList) {
            proizvodDtos.add(new ProizvodDto(proizvod));
        }
        return new ResponseEntity<>(proizvodDtos, HttpStatus.OK);
    }

    @GetMapping("/products/search")
    public ResponseEntity<List<ProizvodDto>> getProizvodiByNazivOrOpis(@RequestParam(required = false) String naziv, @RequestParam(required = false) String opis) {
        List<Proizvod> proizvodList = proizvodService.getProizvodListByNazivOrOpis(naziv, opis);
        return getListResponseEntity(proizvodList);
    }

    @GetMapping("/products/filter")
    public ResponseEntity<List<ProizvodDto>> getProizvodiByFilter(@RequestParam(required = false) Double cenaMin, @RequestParam(required = false) Double cenaMax, @RequestParam(required = false) TipProdaje tipProdaje, @RequestParam(required = false) String kategorija) {
        List<Proizvod> proizvodList = proizvodService.getProizvodListByFilter(cenaMin, cenaMax, tipProdaje, kategorija);
        return getListResponseEntity(proizvodList);
    }

    @GetMapping("/products/{naziv}")
    public ResponseEntity<List<ProizvodDto>> getProizvodiByKategorija(@PathVariable String naziv) {
        List<Proizvod> proizvodList = proizvodService.getProizvodiByKategorija(naziv);
        return getListResponseEntity(proizvodList);
    }

}
