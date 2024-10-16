package com.webshop.repository;

import com.webshop.model.Kategorija;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface KategorijaRepository extends JpaRepository<Kategorija, Long> {

    List<Kategorija> findByNazivIgnoreCaseStartsWith(String naziv);

    Boolean existsKategorijakByNaziv(String naziv);

    Optional<Kategorija> findByNaziv(String naziv);
}
