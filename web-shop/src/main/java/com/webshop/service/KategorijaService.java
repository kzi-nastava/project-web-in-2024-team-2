package com.webshop.service;

import com.webshop.model.Kategorija;
import com.webshop.repository.KategorijaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KategorijaService {

    @Autowired
    private KategorijaRepository kategorijaRepository;

    public List<Kategorija> getKategorijaList() {
        return kategorijaRepository.findAll();
    }

    public List<Kategorija> getKategorija(String naziv) {
        return kategorijaRepository.findByNazivIgnoreCaseStartsWith(naziv);
    }
}