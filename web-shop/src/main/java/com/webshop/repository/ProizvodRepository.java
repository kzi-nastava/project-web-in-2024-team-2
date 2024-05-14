package com.webshop.repository;

import com.webshop.model.Proizvod;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProizvodRepository extends JpaRepository<Proizvod, Long> {
    Page<Proizvod> findAll(Pageable pageable);
    Optional<Proizvod> findById(long id);
    Optional<List<Proizvod>> findAllByNazivContainsIgnoreCaseOrOpisContainsIgnoreCase(String naziv, String opis);
}