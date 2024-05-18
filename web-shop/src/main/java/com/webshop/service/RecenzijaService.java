package com.webshop.service;

import com.webshop.model.Korisnik;
import com.webshop.model.Recenzija;
import com.webshop.model.Uloga;
import com.webshop.repository.KorisnikRepository;
import com.webshop.repository.RecenzijaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RecenzijaService {

    @Autowired
    private RecenzijaRepository recenzijaRepository;

    @Autowired
    private KorisnikRepository korisnikRepository;

    public void saveRecenzija(Recenzija recenzija) {
        recenzijaRepository.save(recenzija);
    }
}