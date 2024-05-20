package com.webshop.service;

import com.webshop.dto.RecenzijaDto;
import com.webshop.dto.RecenzijaProdavacaDto;
import com.webshop.model.*;
import com.webshop.repository.KorisnikRepository;
import com.webshop.repository.RecenzijaRepository;
import jakarta.transaction.Transactional;
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

    public List<Recenzija> getAllRecenzija() {
        return recenzijaRepository.findAll();
    }

    public Recenzija addRecenzija(Recenzija recenzija, Long prodavacId, Long kupacId) {
        List<Proizvod> proizvodiProdavca = proizvodService.getProizvodByProdavacId(prodavacId);
        Optional<Korisnik> korisnik = korisnikRepository.findById(prodavacId);
        Prodavac prodavac = null;
        if (korisnik.isPresent()) {
            Korisnik p = korisnik.get();
            if (p instanceof Prodavac) {
                prodavac = (Prodavac) p;
            } else {
                return null;
            }
        }
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
            /*
            if (kupljeniProizvodi.contains(proizvod)) {
                recenzija.setDatumRecenzije(LocalDate.now());
                Set<Recenzija> dobijenaRecenzija = prodavac.getDobijenaRecenzija();
                dobijenaRecenzija.add(recenzija);
                return recenzijaRepository.save(recenzija);
            }
             */
            for (Proizvod p1 : proizvodiProdavca) {
                if (p1.getId().equals(p.getId())) {
                    recenzija.setDatumRecenzije(LocalDate.now());
                    Set<Recenzija> dobijenaRecenzija = prodavac.getDobijenaRecenzija();
                    dobijenaRecenzija.add(recenzija);
                    return recenzijaRepository.save(recenzija);
                }
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

    /*
    public List<RecenzijaProdavacaDto> getRecenzijaList(Long kupacId) {
        Optional<List<Korisnik>> prodavci = korisnikRepository.findAllByUloga(Uloga.PRODAVAC);
        List<RecenzijaProdavacaDto> recenzijaDtoList = new ArrayList<>();

        if (prodavci.isEmpty()) {
            return null;
        }
        boolean dao = false;
        for (int i = 0; i < prodavci.get().size(); i++) {
            Korisnik prodavac = prodavci.get().get(i);
            for (Recenzija r : prodavac.getDobijenaRecenzija()) {
                if (r.getPodnosilac().getId().equals(kupacId)) {
                    dao = true;
                    break;
                }
            }
        }
        if (dao) {
            for (int i = 0; i < prodavci.get().size(); i++) {
                Korisnik prodavac = prodavci.get().get(i);
                for (Recenzija r : prodavac.getDobijenaRecenzija()) {
                    recenzijaDtoList.add(new RecenzijaProdavacaDto(r));
                }
            }
        }
    }
        return recenzijaDtoList;
    }
     */

    public Set<Recenzija> getRecenzijeProdavca(Long prodavacId, Long kupacId) {
        Optional<Korisnik> prodavacOptional = korisnikRepository.findById(prodavacId);
        Optional<Korisnik> kupacOptional = korisnikRepository.findById(kupacId);
        Prodavac prodavac = null;
        Kupac kupac = null;
        if (prodavacOptional.isPresent()) {
                prodavac = (Prodavac) prodavacOptional.get();
        }
        else {
            return null;
        }
        if (kupacOptional.isPresent()) {
            kupac = (Kupac) kupacOptional.get();
        }
        else {
            return null;
        }
        Set<Recenzija> recenzije = prodavac.getDobijenaRecenzija();
        if(recenzije == null) {
            return null;
        }
        //ako je kupac ostavio bar jednu recenziju za tog prodavca vrati sve recenzije tog prodavca
        for (Recenzija r :recenzije) {
            if (r.getPodnosilac().getId().equals(kupac.getId())) {
                return recenzije;
            }
        }
        return null;
    }

    public Set<Recenzija> getRecenzijeKupca(Long prodavacId, Long kupacId) {
        Optional<Korisnik> prodavacOptional = korisnikRepository.findById(prodavacId);
        Optional<Korisnik> kupacOptional = korisnikRepository.findById(kupacId);
        Prodavac prodavac = null;
        Kupac kupac = null;
        if (prodavacOptional.isPresent()) {
            prodavac = (Prodavac) prodavacOptional.get();
        }
        else {
            return null;
        }
        if (kupacOptional.isPresent()) {
            kupac = (Kupac) kupacOptional.get();
        }
        else {
            return null;
        }
        Set<Recenzija> recenzije = kupac.getDobijenaRecenzija();
        if(recenzije == null) {
            return null;
        }
        //ako je prodavac ostavio bar jednu recenziju za tog kupca vrati sve recenzije tog kupca
        for (Recenzija r :recenzije) {
            if (r.getPodnosilac().getId().equals(prodavac.getId())) {
                return recenzije;
            }
        }
        return null;
    }

    //kupac/prodavac moze da vidi sve recenzije koje je dao drugim prodavcima/kupcima
    public List<Recenzija> mojeRecenzije(Long Id)
    {
        Korisnik korisnik = korisnikRepository.findById(Id).get();
        List<Recenzija> sveRecenzije = recenzijaRepository.findAll();
        List<Recenzija> mojeRecenzije = new ArrayList<>();
        for(Recenzija r :sveRecenzije) {
            if(r.getPodnosilac().getId().equals(Id)) {
                mojeRecenzije.add(r);
            }
        }
        return mojeRecenzije;
    }
}