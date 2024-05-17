package com.webshop.service;

import com.webshop.dto.KorisnikDto;
import com.webshop.model.Korisnik;
import com.webshop.model.Kupac;
import com.webshop.model.Prodavac;
import com.webshop.repository.KorisnikRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.webshop.model.Uloga.KUPAC;
import static com.webshop.model.Uloga.PRODAVAC;

@Service
public class KorisnikService {

    @Autowired
    private KorisnikRepository korisnikRepo;

    public Optional<Korisnik> getById(Long id) {
        return korisnikRepo.findById(id);
    }

    public void createKupac(KorisnikDto korisnikDto) {
        Kupac kupac = new Kupac(korisnikDto);
        korisnikRepo.save(kupac);
    }

    public void createProdavac(KorisnikDto korisnikDto) {
        Prodavac prodavac = new Prodavac(korisnikDto);
        korisnikRepo.save(prodavac);
    }

    public Boolean isExistentByEmail(String mail) {
        return korisnikRepo.existsKorisnikByMail(mail);
    }

    public Boolean isExistentByUsername(String username) {
        return korisnikRepo.existsKorisnikByUsername(username);
    }

    public Korisnik login(String username, String password) {
        Korisnik korisnik = korisnikRepo.findByUsername(username);

        if (korisnik == null || !korisnik.getPassword().equals(password)) {
            return null;
        }
        return korisnik;
    }

    public boolean checkPassword(Long id, String password) {
        Korisnik korisnik = korisnikRepo.findById(id).orElse(null);

        if (korisnik == null) {
            return false;
        }

        return korisnik.getPassword().equals(password);
    }

    public void saveKorisnik(Korisnik korisnik) {
        korisnikRepo.save(korisnik);
    }

    //dodato
    public Prodavac getProdavacById(Long id) {
        Optional<Prodavac> prodavac = korisnikRepo.findByIdAndUloga(id, PRODAVAC);
        return prodavac.orElse(null);
    }
    //dodato
    public Kupac getKupacById(Long id) {
        Optional<Kupac> kupac = korisnikRepo.findByUlogaAndId(KUPAC, id);
        return kupac.orElse(null);
    }


}
