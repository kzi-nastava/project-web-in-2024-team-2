
package com.webshop.service;

import com.webshop.dto.PrijavaProfilaDto;
import com.webshop.model.*;
import com.webshop.repository.KorisnikRepository;
import com.webshop.repository.PrijavaProfilaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class PrijavaProfilaService {

    @Autowired
    private PrijavaProfilaRepository prijavaProfilaRepository;

    @Autowired
    private KorisnikRepository korisnikRepository;

    public List<PrijavaProfila> getPrijavaProfilaList () {
        return prijavaProfilaRepository.findAll();
    };

    public PrijavaProfila getPrijavaProfilaById(Long id) {
        Optional<PrijavaProfila> prijavaProfila = prijavaProfilaRepository.findById(id);
        return prijavaProfila.orElse(null);
    }

    public void savePrijava(PrijavaProfila prijavaProfila) {
        prijavaProfilaRepository.save(prijavaProfila);
    }

    public PrijavaProfila prijaviProdavca(Long prodavacId, PrijavaProfilaDto razlogPrijave, Korisnik korisnik) {
        Kupac kupac = (Kupac) korisnikRepository.findById(korisnik.getId()).get();
        Prodavac prodavac = (Prodavac) korisnikRepository.findById(prodavacId).get();

        Set<Proizvod> kupljeniProizvodi = kupac.getKupljeniProizvodi();
        Set<Proizvod> proizvodiNaProdaju = prodavac.getProizvodiNaProdaju();

        boolean kupio = false;
        for (Proizvod kupljeni : kupljeniProizvodi) {
            for (Proizvod naProdaju : proizvodiNaProdaju) {
                if (kupljeni.equals(naProdaju)) {
                    kupio = true;
                    break;
                }
            }
        }

        if (kupio) {
            PrijavaProfila prijavaProfila = new PrijavaProfila(razlogPrijave);
            prijavaProfila.setOdnosiSe(prodavac);
            prijavaProfila.setPodnosilac(kupac);
            return prijavaProfilaRepository.save(prijavaProfila);
        }
        return null;
    }

    public PrijavaProfila prijaviKupca(Long prodavacId, PrijavaProfilaDto razlogPrijave, Korisnik korisnik) {
        Kupac kupac = (Kupac) korisnikRepository.findById(korisnik.getId()).get();
        Prodavac prodavac = (Prodavac) korisnikRepository.findById(prodavacId).get();

        Set<Proizvod> kupljeniProizvodi = kupac.getKupljeniProizvodi();
        Set<Proizvod> proizvodiNaProdaju = prodavac.getProizvodiNaProdaju();

        boolean kupio = false;
        for (Proizvod kupljeni : kupljeniProizvodi) {
            for (Proizvod naProdaju : proizvodiNaProdaju) {
                if (kupljeni.equals(naProdaju)) {
                    kupio = true;
                    break;
                }
            }
        }

        if (kupio) {
            PrijavaProfila prijavaProfila = new PrijavaProfila(razlogPrijave);
            prijavaProfila.setOdnosiSe(kupac);
            prijavaProfila.setPodnosilac(prodavac);
            return prijavaProfilaRepository.save(prijavaProfila);
        }
        return null;
    }

}
