package com.webshop.service;

import com.webshop.model.Korisnik;
import com.webshop.model.Kupac;
import com.webshop.model.Proizvod;
import com.webshop.model.Recenzija;
import com.webshop.repository.KorisnikRepository;
import com.webshop.repository.RecenzijaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class RecenzijaService {

    @Autowired
    private RecenzijaRepository recenzijaRepository;

    @Autowired
    private KorisnikRepository korisnikRepository;

    @Autowired
    private ProizvodService proizvodService;

    public Recenzija addRecenzija(Recenzija recenzija, Long prodavacId, Long kupacId) {
        Proizvod proizvod = proizvodService.getProizvodById(prodavacId);
        Korisnik prodavac = proizvod.getProdavac();
        Optional<Korisnik> korisnikOpt = korisnikRepository.findById(kupacId);
        Set<Proizvod> kupljeniProizvodi = new HashSet<>();

        if (korisnikOpt.isPresent()) {
            Korisnik k = korisnikOpt.get();
            if (k instanceof Kupac) {
                Kupac kupac = (Kupac) k;
                kupljeniProizvodi = kupac.getKupljeniProizvodi();
            }
        }

        for (Proizvod p : kupljeniProizvodi) {
            if (kupljeniProizvodi.contains(proizvod)) {
                recenzija.setDatumRecenzije(LocalDate.now());
                Set<Recenzija> dobijenaRecenzija = prodavac.getDobijenaRecenzija();
                dobijenaRecenzija.add(recenzija);
                return recenzijaRepository.save(recenzija);
            }
        }
        return null;
    }
}
