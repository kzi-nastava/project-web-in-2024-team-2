package com.webshop.service;

import com.webshop.model.Proizvod;
import com.webshop.model.TipProdaje;
import com.webshop.repository.ProizvodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProizvodService {

    @Autowired
    private ProizvodRepository proizvodRepository;

    public Page<Proizvod> getProizvodList(int page, int size) {
        Pageable tenProductPage = PageRequest.of(page, size);
        return proizvodRepository.findAll(tenProductPage);
    }

    public Proizvod getProizvodById(int id) {
        Optional<Proizvod> proizvod = proizvodRepository.findById(id);
        return proizvod.orElse(null);
    }

    public List<Proizvod> getProizvodListByNazivOrOpis(String naziv, String opis) {
        Optional<List<Proizvod>> proizvodList = proizvodRepository.findAllByNazivContainsIgnoreCaseOrOpisContainsIgnoreCase(naziv, opis);
        return proizvodList.orElse(null);
    }

    public List<Proizvod> getProizvodListByFilter(Double cenaMin, Double cenaMax, TipProdaje tip, String kategorija) {
        Optional<List<Proizvod>> proizvodList = proizvodRepository.findAllByFilter(cenaMin, cenaMax, tip, kategorija);
        return proizvodList.orElse(null);
    }

    public  List<Proizvod> getProizvodiByKategorija(String kategorija) {
        return proizvodRepository.findAllByKategorijaNaziv(kategorija);
    }

    public void deleteProizvodByProdavacId(Long id) {
        proizvodRepository.deleteByProdavacId(id);
    }
}
