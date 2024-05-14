package com.webshop.service;

import com.webshop.model.Proizvod;
import com.webshop.repository.ProizvodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Service
public class ProizvodService {

    @Autowired
    private ProizvodRepository proizvodRepository;

    public Page<Proizvod> getProizvodList( int page, int size) {
        Pageable tenProductPage = PageRequest.of(page, size);
        return proizvodRepository.findAll(tenProductPage);
    }

    public Proizvod getProizvodById(int id) {
        Optional<Proizvod> proizvod = proizvodRepository.findById(id);
        return proizvod.orElse(null);
    }
}
