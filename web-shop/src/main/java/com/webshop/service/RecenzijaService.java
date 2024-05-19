package com.webshop.service;

import com.webshop.dto.RecenzijaDto;
import com.webshop.dto.RecenzijaProdavacaDto;
import com.webshop.model.*;
import com.webshop.repository.KorisnikRepository;
import com.webshop.repository.RecenzijaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

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

    public void saveRecenzija(Recenzija recenzija) {
        recenzijaRepository.save(recenzija);
    }

    public Recenzija findById(Long id) {
        Optional<Recenzija> recenzija = recenzijaRepository.findById(id);
        return recenzija.orElse(null);
    }

    public void deleteRecenzijaById(Long id) {
        recenzijaRepository.deleteById(id);
    }

    public List<RecenzijaProdavacaDto> getRecenzijaList(Long kupacId) {
        Optional<List<Korisnik>> prodavci = korisnikRepository.findAllByUloga(Uloga.PRODAVAC);
        List<RecenzijaProdavacaDto> recenzijaDtoList = new ArrayList<>();

        if (prodavci.isEmpty()) {
            return null;
        }

        for (int i = 0; i < prodavci.get().size(); i++) {
            Korisnik prodavac = prodavci.get().get(i);
            for (Recenzija r : prodavac.getDobijenaRecenzija()) {
                if (r.getPodnosilac().getId().equals(kupacId)) {
                    break;
                }
            }
        }

        for (int i = 0; i < prodavci.get().size(); i++) {
            Korisnik prodavac = prodavci.get().get(i);
            for (Recenzija r : prodavac.getDobijenaRecenzija()) {
                recenzijaDtoList.add(new RecenzijaProdavacaDto(r));
            }
        }
        return recenzijaDtoList;

    }
}
