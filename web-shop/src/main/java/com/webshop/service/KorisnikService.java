package com.webshop.service;

import com.webshop.dto.KorisnikDto;
import com.webshop.model.Korisnik;
import com.webshop.model.Kupac;
import com.webshop.model.Prodavac;
import com.webshop.model.Proizvod;
import com.webshop.repository.KorisnikRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.webshop.model.Uloga.PRODAVAC;

@Service
public class KorisnikService {

    @Autowired
    private KorisnikRepository korisnikRepo;

    public List<Korisnik> getKorisnikList() {
        return korisnikRepo.findAll();
    }

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

    public Korisnik getProdavacById(Long id) {
        Optional<Korisnik> korisnik = korisnikRepo.findByIdAndUloga(id, PRODAVAC);
        return korisnik.orElse(null);
    }
}
