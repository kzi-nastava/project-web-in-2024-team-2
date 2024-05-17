package com.webshop.repository;

import com.webshop.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface KorisnikRepository extends JpaRepository<Korisnik, Long> {

    Korisnik save(Korisnik korisnik);

    Optional<Korisnik> findById(Long id);
    Kupac save(Kupac kupac);

    Optional<Prodavac> findByIdAndUloga(long id, Uloga uloga);
}
