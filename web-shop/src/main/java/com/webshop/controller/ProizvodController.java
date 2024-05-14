package com.webshop.controller;

import com.webshop.dto.ProizvodDto;
import com.webshop.model.Korisnik;
import com.webshop.model.Proizvod;
import com.webshop.service.ProizvodService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ProizvodController {

    @Autowired
    private ProizvodService proizvodService;

    @GetMapping("/all-products")
    public ResponseEntity<List<ProizvodDto>> getProizvodi(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "3") int size) {
        Page<Proizvod> proizvodi = proizvodService.getProizvodList(page, size);
        List<ProizvodDto> proizvodDtos = new ArrayList<>();

        for (Proizvod proizvod : proizvodi) {
            proizvodDtos.add(new ProizvodDto(proizvod));
        }

        return ResponseEntity.ok(proizvodDtos);
    }
}
