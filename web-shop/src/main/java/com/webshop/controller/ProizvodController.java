package com.webshop.controller;

import com.webshop.dto.DodajProizvodDto;
import com.webshop.dto.PonudaDto;
import com.webshop.dto.ProizvodDto;
import com.webshop.model.*;
import com.webshop.service.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.*;

@RestController
public class ProizvodController {

    @Autowired
    private ProizvodService proizvodService;

    @Autowired
    private KategorijaService kategorijaService;

    @Autowired
    private KorisnikService korisnikService;
    @Autowired
    private EmailService emailService;

    @Autowired
    private PonudaService ponudaService;

    @PostMapping("/dodaj-proizvod")
    public ResponseEntity<?> dodajProizvod(@RequestBody DodajProizvodDto dodajDto, HttpSession session) {
        Prodavac prodavac = (Prodavac) session.getAttribute("korisnik");
        if(prodavac == null || prodavac.getUloga() != Uloga.PRODAVAC) {
            return new ResponseEntity<>("Nemate pravo za postavljanje proizvoda na prodaju!", HttpStatus.FORBIDDEN);
        }
        if(!kategorijaService.isExistentByNaziv(dodajDto.getNazivKategorije())) {
            kategorijaService.saveKategorija(new Kategorija(dodajDto.getNazivKategorije()));
        }
        Kategorija kategorija = kategorijaService.findByNaziv(dodajDto.getNazivKategorije());
        Proizvod proizvod = new Proizvod(dodajDto.getNaziv(), dodajDto.getOpis(), dodajDto.getProfilnaURL(), kategorija, dodajDto.getCena(), dodajDto.getTipProdaje(), prodavac, LocalDate.now());
        proizvodService.saveProizvod(proizvod);
        return new ResponseEntity<>("Uspesno postavljen proizvod na prodaju!", HttpStatus.OK);
    }

    @PostMapping("/azuriraj-proizvod/{id}")
    public ResponseEntity<?> azurirajProizvod(@RequestBody DodajProizvodDto dodajDto, @PathVariable Long id, HttpSession session) {
        Korisnik korisnik = (Korisnik) session.getAttribute("korisnik");
        if(korisnik == null || korisnik.getUloga() != Uloga.PRODAVAC) {
            return new ResponseEntity<>("Nemate pravo na azuriranje proizvoda!", HttpStatus.FORBIDDEN);
        }
        Proizvod proizvod = proizvodService.getProizvodById(id);
        if(proizvod == null) {
            return new ResponseEntity<>("Ne postoji proizvod sa datim id-em", HttpStatus.BAD_REQUEST);
        }
        if(!Objects.equals(proizvod.getProdavac().getId(), korisnik.getId())) {
            return new ResponseEntity<>("Ne mozete da azurirate proizvod drugog prodavca", HttpStatus.FORBIDDEN);
        }
        if (proizvod.getTipProdaje() == TipProdaje.FIKSNA_CENA || (proizvod.getTipProdaje() == TipProdaje.AUKCIJA && proizvod.getPonuda().isEmpty())) {
            proizvod.setNaziv(dodajDto.getNaziv());
            proizvod.setOpis(dodajDto.getOpis());
            proizvod.setProfilnaURL(dodajDto.getProfilnaURL());
            proizvod.setCena(dodajDto.getCena());
            proizvod.setTipProdaje(dodajDto.getTipProdaje());
            Kategorija kategorija = new Kategorija(dodajDto.getNazivKategorije());
            if(!kategorijaService.isExistentByNaziv(dodajDto.getNazivKategorije())) {
                kategorijaService.saveKategorija(kategorija);
            }
            proizvod.setKategorija(kategorija);
            proizvodService.saveProizvod(proizvod);
            return new ResponseEntity<>("Izmenili ste podatke o datom proizvodu!", HttpStatus.OK);
        }
        return new ResponseEntity<>("Ne mozete izmeniti podatke o proizvodu jer postoje aktivne ponude za dati proizvod!", HttpStatus.FORBIDDEN);
    }



    @GetMapping("/all-products")
    public ResponseEntity<List<ProizvodDto>> getProizvodi(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size) {
        Page<Proizvod> proizvodi = proizvodService.getProizvodList(page, size);
        List<ProizvodDto> proizvodDtos = new ArrayList<>();

        if (!proizvodi.hasContent()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        for (Proizvod proizvod : proizvodi) {
            proizvodDtos.add(new ProizvodDto(proizvod));
        }
        return new ResponseEntity<>(proizvodDtos, HttpStatus.OK);
    }


    @GetMapping("/product/{id}")
    public ResponseEntity<ProizvodDto> getProizvod(@PathVariable Long id) {
        Proizvod proizvod =  proizvodService.getProizvodById(id);

        if (proizvod == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(new ProizvodDto(proizvod), HttpStatus.OK);
    }

    private ResponseEntity<List<ProizvodDto>> getListResponseEntity(List<Proizvod> proizvodList) {
        List<ProizvodDto> proizvodDtos = new ArrayList<>();

        if (proizvodList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        for (Proizvod proizvod : proizvodList) {
            proizvodDtos.add(new ProizvodDto(proizvod));
        }
        return new ResponseEntity<>(proizvodDtos, HttpStatus.OK);
    }

    @GetMapping("/products/search")
    public ResponseEntity<List<ProizvodDto>> getProizvodiByNazivOrOpis(@RequestParam(required = false) String naziv, @RequestParam(required = false) String opis) {
        List<Proizvod> proizvodList = proizvodService.getProizvodListByNazivOrOpis(naziv, opis);
        return getListResponseEntity(proizvodList);
    }

    @GetMapping("/products/filter")
    public ResponseEntity<List<ProizvodDto>> getProizvodiByFilter(@RequestParam(required = false) Double cenaMin, @RequestParam(required = false) Double cenaMax, @RequestParam(required = false) TipProdaje tipProdaje, @RequestParam(required = false) String kategorija) {
        List<Proizvod> proizvodList = proizvodService.getProizvodListByFilter(cenaMin, cenaMax, tipProdaje, kategorija);
        return getListResponseEntity(proizvodList);
    }

    @GetMapping("/products/{naziv}")
    public ResponseEntity<List<ProizvodDto>> getProizvodiByKategorija(@PathVariable String naziv) {
        List<Proizvod> proizvodList = proizvodService.getProizvodiByKategorija(naziv);
        return getListResponseEntity(proizvodList);
    }

    @PostMapping("/kupi/proizvod/{id}")
    public ResponseEntity<?> kupiProizvod(@PathVariable Long id, @RequestBody PonudaDto ponudaDto, HttpSession session) {
        Korisnik loggedKorisnik = (Korisnik) session.getAttribute("korisnik");
        if (loggedKorisnik == null) {
            return new ResponseEntity<>("Niste prijavljeni!", HttpStatus.FORBIDDEN);
        }
        if (loggedKorisnik.getUloga() != Uloga.KUPAC) {
            return new ResponseEntity<>("Niste kupac!", HttpStatus.FORBIDDEN);
        }
        Proizvod proizvod = proizvodService.getProizvodById(id);
        if (proizvod == null) {
            return new ResponseEntity<>("Ne postoji dati proizvod!", HttpStatus.NO_CONTENT);
        }
        if (proizvod.isProdat()) {
            return new ResponseEntity<>("Proizvod je vec prodat!", HttpStatus.FORBIDDEN);
        }
        Kupac kupac = (Kupac) loggedKorisnik;
        Prodavac prodavac = proizvod.getProdavac();

        double cenaPonude = ponudaDto.getCenaPonude();
        if (proizvod.getTipProdaje() == TipProdaje.FIKSNA_CENA) {
            proizvod.setProdat(true);
            kupac.getKupljeniProizvodi().add(proizvod);
            //ako ne sacuvam kupca i prodavca promene nece biti vidljive u bazi podataka
            korisnikService.saveKorisnik(kupac);
            korisnikService.saveKorisnik(prodavac);
            prodavac.getProizvodiNaProdaju().remove(proizvod);
            proizvodService.saveProizvod(proizvod);
            String kupacEMAIL = kupac.getMail();
            String prodavacEMAIL = prodavac.getMail();
            emailService.sendEmail(kupacEMAIL, "Kupili ste proizvod ", proizvod.getNaziv());
            emailService.sendEmail(prodavacEMAIL, "Prodali ste proizvod ", proizvod.getNaziv());
            return new ResponseEntity<>("Uspesno ste kupili proizvod!", HttpStatus.OK);
        }
        if (proizvod.getTipProdaje() == TipProdaje.AUKCIJA) {
            Set<Ponuda> ponude = proizvod.getPonuda();
            //ako ne postoji data ponuda za proizvod maxPonuda je 0, a ako postoji pronaci ce najvecu za dati proizvod i upisati je u maxPonuda
            double maxPonuda = 0;
            if (!ponude.isEmpty()) {
                for (Ponuda ponuda : ponude) {
                    if (maxPonuda < ponuda.getCena()) {
                        maxPonuda = ponuda.getCena();
                    }
                }
            }
            //ako je nova ponuda veca od najvece moguce ponude za dati proizvod ta ponuda se dodaje u bazu podataka
            if (maxPonuda < cenaPonude) {
                proizvod.getPonuda().add(new Ponuda(cenaPonude, kupac));
                proizvodService.saveProizvod(proizvod);
                emailService.sendEmail(proizvod.getProdavac().getMail(), "Nova ponuda za proizvod", "Nova ponuda za prozivod" + proizvod.getNaziv() + "je" + cenaPonude);
                return new ResponseEntity<>("Uspesno ste podneli ponudu za dati proizvod", HttpStatus.OK);
            }
            return new ResponseEntity<>("Morate uneti ponudu koja je veca od vec postojecih", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Uneli ste los tip prodaje!", HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/zavrsi-aukciju/{id}")
    public ResponseEntity<?> zavrsiAukciju(@PathVariable Long id, HttpSession session) {
        Korisnik korisnik = (Korisnik) session.getAttribute("korisnik");
        if (korisnik == null || korisnik.getUloga() != Uloga.PRODAVAC) {
            return new ResponseEntity<>("Nemate pravo da zavrsite aukciju!", HttpStatus.FORBIDDEN);
        }
        Proizvod proizvod = proizvodService.getProizvodById(id);
        if (proizvod == null) {
            return new ResponseEntity<>("Ne postoji proizvod sa datim id-em", HttpStatus.BAD_REQUEST);
        }
        if (!Objects.equals(proizvod.getProdavac().getId(), korisnik.getId())) {
            return new ResponseEntity<>("Ne mozete da zavrsite aukciju za proizvod drugog prodavca", HttpStatus.FORBIDDEN);
        }
        if (proizvod.isProdat()) {
            return new ResponseEntity<>("Proizvod je vec prodat!", HttpStatus.FORBIDDEN);
        }
        if (proizvod.getTipProdaje() != TipProdaje.AUKCIJA) {
            return new ResponseEntity<>("Proizvod nije postavljen kao aukcija", HttpStatus.BAD_REQUEST);
        }
        if (proizvod.getPonuda().isEmpty()) {
            return new ResponseEntity<>("Nema ponuda za dati proizvod", HttpStatus.BAD_REQUEST);
        }

        Ponuda najvecaPonuda = proizvod.getPonuda().stream().max(Comparator.comparing(Ponuda::getCena)).orElse(null);
        if (najvecaPonuda == null) {
            return new ResponseEntity<>("Nema validnih ponuda za dati proizvod", HttpStatus.BAD_REQUEST);
        }
        if (najvecaPonuda.getCena() < proizvod.getCena()) {
            return new ResponseEntity<>("Ponuda je preniska!", HttpStatus.BAD_REQUEST);
        }
        proizvod.setCena(najvecaPonuda.getCena());
        proizvod.setProdat(true);
        proizvodService.saveProizvod(proizvod);
        emailService.sendEmail(najvecaPonuda.getKupac().getMail(), "Aukcija zavrsena", proizvod.getNaziv());
        emailService.sendEmail(proizvod.getProdavac().getMail(), "Aukcija zavrsena", proizvod.getNaziv());
        proizvod.setProdavac(null);
        return new ResponseEntity<>("Aukcija je uspesno zavrsena", HttpStatus.OK);
    }

}
