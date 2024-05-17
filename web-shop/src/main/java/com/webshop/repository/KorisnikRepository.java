package com.webshop.repository;

import com.webshop.model.Korisnik;
import com.webshop.model.Kupac;
import com.webshop.model.Prodavac;
import com.webshop.model.Uloga;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface KorisnikRepository extends JpaRepository<Korisnik, Long> {

    Boolean existsKorisnikByMail(String mail);

    Boolean existsKorisnikByUsername(String username);

    Korisnik findByUsername(String username);

    Optional<Korisnik> findById(Long id);

    //dodato
    Optional<Prodavac> findByIdAndUloga(Long id, Uloga uloga);
    Optional<Kupac> findByUlogaAndId(Uloga uloga,Long id);

}
