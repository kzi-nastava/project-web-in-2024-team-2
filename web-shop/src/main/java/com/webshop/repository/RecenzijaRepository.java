package com.webshop.repository;

import com.webshop.model.Korisnik;
import com.webshop.model.Recenzija;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecenzijaRepository extends JpaRepository<Recenzija, Long> {

    void deleteById(Long id);

}
