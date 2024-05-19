package com.webshop.repository;

import com.webshop.model.Korisnik;
import com.webshop.model.Uloga;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface KorisnikRepository extends JpaRepository<Korisnik, Long> {

    Boolean existsKorisnikByMail(String mail);

    Boolean existsKorisnikByUsername(String username);

    Korisnik findByUsername(String username);

    Optional<Korisnik> findById(Long id);

    Optional<Korisnik> findByIdAndUloga(Long id, Uloga uloga);

}
