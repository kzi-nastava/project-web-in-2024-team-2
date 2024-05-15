package com.webshop.repository;

import com.webshop.model.Kategorija;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface KategorijaRepository extends JpaRepository<Kategorija, Long> {

    List<Kategorija> findByNazivIgnoreCaseStartsWith(String naziv);
}