package com.webshop.repository;

import com.webshop.model.Korisnik;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KorisnikRepository extends JpaRepository<Korisnik, Long> {

    Boolean existsKorisnikByMail(String mail);

    Boolean existsKorisnikByUsername(String username);

    Korisnik findByUsername(String username);

}