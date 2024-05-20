
package com.webshop.service;

import com.webshop.model.Kategorija;
import com.webshop.repository.KategorijaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    //dodato
    public Boolean isExistentByNaziv(String naziv) {
        return kategorijaRepository.existsKategorijakByNaziv(naziv);
    }

    public void saveKategorija(Kategorija kategorija) {
        kategorijaRepository.save(kategorija);
    }

    public Kategorija findByNaziv(String naziv) {
        Optional<Kategorija> kategorija = kategorijaRepository.findByNaziv(naziv);
        return kategorija.orElse(null);
    }
}
