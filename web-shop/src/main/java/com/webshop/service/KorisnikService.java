package com.webshop.service;

import com.webshop.model.*;
import com.webshop.repository.KorisnikRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static com.webshop.model.Uloga.KUPAC;
import static com.webshop.model.Uloga.PRODAVAC;

@Service
public class KorisnikService {
    @Autowired
    private KorisnikRepository korisnikRepository;

    public Korisnik save(Korisnik korisnik)
    {
        return this.korisnikRepository.save(korisnik);
    }

    public Optional<Korisnik> findById(Long id)
    {
        return this.korisnikRepository.findById(id);
    }

    public double getProsecnaOcena(Long id)
    {
        //vraca korisnika sa prosledjenim id-om
       Korisnik korisnik = this.korisnikRepository.findById(id).get();
       if(korisnik.getUloga() == KUPAC)
       {
           return ((Kupac) korisnik).getProsecnaOcena();
       }
       else if(korisnik.getUloga() == PRODAVAC)
       {
           return ((Prodavac) korisnik).getProsecnaOcena();
       }
       return 0;
    }

    public Set<Proizvod> getProizvod(Long id)
    {
        Korisnik korisnik = this.korisnikRepository.findById(id).get();
        if(korisnik.getUloga() == KUPAC)
        {
            return ((Kupac) korisnik).getKupljeniProizvodi();
        }
        else if(korisnik.getUloga() == PRODAVAC)
        {
            return ((Prodavac) korisnik).getProizvodiNaProdaju();
        }
        return new HashSet<>();
    }

    public Set<Recenzija> getRecenzija(Long id)
    {
        Korisnik korisnik = this.korisnikRepository.findById(id).get();
        if(korisnik.getUloga() == KUPAC)
        {
            return ((Kupac) korisnik).getRecenzije();
        }
        else if(korisnik.getUloga() == PRODAVAC)
        {
            return ((Prodavac) korisnik).getRecenzije();
        }
        return new HashSet<>();
    }

}
