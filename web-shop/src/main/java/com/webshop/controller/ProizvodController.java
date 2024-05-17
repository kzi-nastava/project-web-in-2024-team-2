package com.webshop.controller;

import com.webshop.dto.ProizvodDto;
import com.webshop.model.Proizvod;
import com.webshop.model.TipProdaje;
import com.webshop.service.ProizvodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
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

        if (!proizvodi.hasContent()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        for (Proizvod proizvod : proizvodi) {
            proizvodDtos.add(new ProizvodDto(proizvod));
        }
        return new ResponseEntity<>(proizvodDtos, HttpStatus.OK);
    }


    @GetMapping("/product/{id}")
    public ResponseEntity<ProizvodDto> getProizvod(@PathVariable int id) {
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
