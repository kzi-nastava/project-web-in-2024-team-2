package com.webshop.controller;

import com.webshop.dto.ProizvodDto;
import com.webshop.model.Kategorija;
import com.webshop.model.Korisnik;
import com.webshop.model.Proizvod;
import com.webshop.model.TipProdaje;
import com.webshop.service.ProizvodService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ProizvodController {

    @Autowired
    private ProizvodService proizvodService;

    @GetMapping("/all-products")
    public ResponseEntity<List<ProizvodDto>> getProizvodi(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size) {
        Page<Proizvod> proizvodi = proizvodService.getProizvodList(page, size);
        List<ProizvodDto> proizvodDtos = new ArrayList<>();

        for (Proizvod proizvod : proizvodi) {
            proizvodDtos.add(new ProizvodDto(proizvod));
        }
        return ResponseEntity.ok(proizvodDtos);
    }


    @GetMapping("/product/{id}")
    public ResponseEntity<ProizvodDto> getProizvod(@PathVariable int id) {
        Proizvod proizvod =  proizvodService.getProizvodById(id);

        if (proizvod == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(new ProizvodDto(proizvod));
    }

    @GetMapping("/products/search")
    public ResponseEntity<List<ProizvodDto>> getProizvodiByNazivOrOpis(@RequestParam(required = false) String naziv, @RequestParam(required = false) String opis) {
        List<Proizvod> proizvodList = proizvodService.getProizvodListByNazivOrOpis(naziv, opis);
        List<ProizvodDto> proizvodDtos = new ArrayList<>();

        for (Proizvod proizvod : proizvodList) {
            proizvodDtos.add(new ProizvodDto(proizvod));
        }
        return ResponseEntity.ok(proizvodDtos);
    }

    @GetMapping("/products/filter")
    public ResponseEntity<List<ProizvodDto>> getProizvodiByFilter(@RequestParam(required = false) Double cenaMin, @RequestParam(required = false) Double cenaMax, @RequestParam(required = false) TipProdaje tipProdaje, @RequestParam(required = false) String kategorija) {
        List<Proizvod> proizvodList = proizvodService.getProizvodListByFilter(cenaMin, cenaMax, tipProdaje, kategorija);
        List<ProizvodDto> proizvodDtos = new ArrayList<>();

        for (Proizvod proizvod : proizvodList) {
            proizvodDtos.add(new ProizvodDto(proizvod));
        }
        return ResponseEntity.ok(proizvodDtos);
    }
}
