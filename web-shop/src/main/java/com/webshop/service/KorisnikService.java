package com.webshop.service;

import com.webshop.dto.KorisnikDto;
import com.webshop.model.Kupac;
import com.webshop.model.Prodavac;
import com.webshop.repository.KorisnikRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KorisnikService {

    @Autowired
    private KorisnikRepository korisnikRepo;

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

}
