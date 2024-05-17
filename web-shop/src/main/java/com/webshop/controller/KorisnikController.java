package com.webshop.controller;

import com.webshop.dto.*;
import com.webshop.model.*;
import com.webshop.repository.KorisnikRepository;
import com.webshop.repository.ProizvodRepository;
import com.webshop.service.KorisnikService;
import com.webshop.service.PrijavaProfilaService;
import com.webshop.service.ProizvodService;
import com.webshop.service.RecenzijeService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.*;

import static com.webshop.model.StatusPrijave.PODNETA;
import static com.webshop.model.TipProdaje.FIKSNA_CENA;
import static com.webshop.model.Uloga.KUPAC;
import static com.webshop.model.Uloga.PRODAVAC;


@RestController
public class KorisnikController {

    @Autowired
    private KorisnikService korisnikService;

    @Autowired
    private ProizvodService proizvodService;

    @Autowired
    private RecenzijeService recenzijeService;

    @Autowired
    private PrijavaProfilaService prijavaProfilaService;

    ///////
    private KupacProdavacDto kupacProdavacDto;

    //2.1
    @PostMapping("/azuriraj-profil")
    public ResponseEntity<String> azurirajProfil(@RequestBody KorisnikDto korisnikDto, HttpSession session) {
        Korisnik prijavljeniKorisnik = (Korisnik) session.getAttribute("korisnik");

        if (prijavljeniKorisnik == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        if (prijavljeniKorisnik.getUloga() == KUPAC || prijavljeniKorisnik.getUloga() == PRODAVAC) {

            String staraLozinka = prijavljeniKorisnik.getPassword();
            String stariUsername = prijavljeniKorisnik.getUsername();
            String stariMail = prijavljeniKorisnik.getMail();

            prijavljeniKorisnik.setIme(korisnikDto.getIme());
            prijavljeniKorisnik.setPrezime(korisnikDto.getPrezime());
            prijavljeniKorisnik.setUsername(korisnikDto.getUsername());
            prijavljeniKorisnik.setMail(korisnikDto.getMail());
            prijavljeniKorisnik.setBrojTelefona(korisnikDto.getBrojTelefona());
            prijavljeniKorisnik.setPassword(korisnikDto.getPassword());
            prijavljeniKorisnik.setDatumRodjenja(korisnikDto.getDatumRodjenja());
            prijavljeniKorisnik.setProfilnaURL(korisnikDto.getProfilnaURL());
            prijavljeniKorisnik.setOpis(korisnikDto.getOpis());


            if (!staraLozinka.equals(prijavljeniKorisnik.getPassword()) || !stariUsername.equals(prijavljeniKorisnik.getUsername()) || !stariMail.equals(prijavljeniKorisnik.getMail())) {
                if(!korisnikDto.getPotvrdaLozinke().equals(staraLozinka))
                {
                    return new ResponseEntity<>("Lozinka nije ispravna!", HttpStatus.BAD_REQUEST);
                }
                else
                {
                    korisnikService.save(prijavljeniKorisnik);
                }
            }
            else
            {
                korisnikService.save(prijavljeniKorisnik);
            }
            return new ResponseEntity<>("Profil uspešno ažuriran!", HttpStatus.OK);
            }
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }

    //2.2
    @GetMapping("/prikaz-profila/{id}")
    public ResponseEntity<KorisnikDto> prikazProfila(@PathVariable Long id) {
        Optional<Korisnik> opk = korisnikService.findById(id);
        if (opk.isPresent()) {
            Korisnik k = opk.get();
            double ocena = korisnikService.getProsecnaOcena(id);
            Set<Proizvod> proizvodi = korisnikService.getProizvod(id);
            Set<Recenzija> recenzije = korisnikService.getRecenzija(id);
            KorisnikDto kdto = new KorisnikDto(k.getIme(), k.getPrezime(), k.getUsername(), k.getDatumRodjenja(), k.getProfilnaURL(), k.getOpis(), k.getUloga(), k.isBlokiran(), ocena, proizvodi, recenzije);
            return new ResponseEntity<>(kdto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /////////////////////////////////
    //2.3
    @PostMapping("/kupovina-proizvoda/{productID}")
    public ResponseEntity<String> kupiProizvod(@PathVariable Long productID, HttpSession session) {
        Optional<Proizvod> op = proizvodService.findById(productID);
        Korisnik prijavljeniKorisnik = (Korisnik) session.getAttribute("korisnik");

        if (prijavljeniKorisnik.getUloga() == KUPAC) {
            if (op.isPresent()) {
                Proizvod p = op.get();
                if(p.isProdat()) { return new ResponseEntity<>(HttpStatus.BAD_REQUEST); }

                korisnikService.kupiProizvod(p, prijavljeniKorisnik.getId());
                kupacProdavacDto.dodajKupacProdavacID(prijavljeniKorisnik.getId(), p.getProdavac().getId());
                return new ResponseEntity<>("Kupljen proizvod!", HttpStatus.OK);
            }
            else {
                return new ResponseEntity<>("Proizvod sa datim id-em ne postoji!", HttpStatus.NOT_FOUND);
            }
        }
        else
        {
            return new ResponseEntity<>("Nemate mogucnost kupovine proizvoda!", HttpStatus.UNAUTHORIZED);
        }
    }



    //2.4
    @PostMapping("/oceni_prodavca/{id}")
    public ResponseEntity<String> oceniProdavca(@RequestBody RecenzijaDto recenzijaDto, @PathVariable int id, HttpSession session)
    {
        Korisnik prijavljeniKorisnik = (Korisnik) session.getAttribute("korisnik");
        if(prijavljeniKorisnik == null)
        {
            return new ResponseEntity<>("Nemate pravo na recenziju", HttpStatus.FORBIDDEN);
        }

        if(prijavljeniKorisnik.getUloga() != KUPAC) {
            return new ResponseEntity<>("Nemate pravo na recenziju", HttpStatus.FORBIDDEN);
        }
        Prodavac prodavac = korisnikService.getProdavacById(id);
        if(prodavac == null) {
            return new ResponseEntity<>("Ne postoji prodavac sa datim id-em", HttpStatus.NOT_FOUND);
        }
        ArrayList<Long> kupci = kupacProdavacDto.vratiKupce();
        ArrayList<Long> prodavci = kupacProdavacDto.vratiProdavce();
        int i = 0, j = 0;
        for(Long kupacID : kupci) {
            if (Objects.equals(prijavljeniKorisnik.getId(), kupacID)) {
                if (Objects.equals(prodavci.get(i), prodavac.getId())) {
                    Recenzija recenzija = new Recenzija(recenzijaDto.getOcena(), recenzijaDto.getKomentar(), recenzijaDto.getDatum(), prijavljeniKorisnik);
                    //recenzijeService.save(recenzija);
                    prodavac.prihvatiRecenziju(recenzija);
                    prodavac.setProsecnaOcena((prodavac.getProsecnaOcena() + recenzija.getOcena()) / 2);
                    korisnikService.saveKorisnik(prodavac);
                    j++;
                    break;
                }
            }
            i++;
        }
        if(j != 0){
            return new ResponseEntity<>("Uspesno dodata recenzija", HttpStatus.OK);
        }
        return new ResponseEntity<>("Kupac nije kupio proizvod od datog prodavca", HttpStatus.NOT_FOUND);
    }


    //2.6 prijava prodavca
    @PostMapping("/prijava_prodavca/{id}")
    public ResponseEntity<String> prijaviProdavca(@RequestBody PrijavaProfilaDto prijavaProfilaDto, @PathVariable Long id, HttpSession session)
    {
        Korisnik prijavljeniKorisnik = (Korisnik) session.getAttribute("korisnik");
        if(prijavljeniKorisnik == null)
        {
            return new ResponseEntity<>("Nemate pravo za prijavu", HttpStatus.FORBIDDEN);
        }

        if(prijavljeniKorisnik.getUloga() == KUPAC) {
            ArrayList<Long> kupci = kupacProdavacDto.vratiKupce();
            ArrayList<Long> prodavci = kupacProdavacDto.vratiProdavce();
            Prodavac prodavac = (Prodavac) korisnikService.findById(id).get();
            int i = 0;
            for(Long kupacID : kupci) {
                if (Objects.equals(prijavljeniKorisnik.getId(), kupacID)) {
                    if (Objects.equals(prodavci.get(i), prodavac.getId())) {
                        PrijavaProfila prijavaProfila = new PrijavaProfila(prijavaProfilaDto.getDatumPodnosenjaPrijave(), PODNETA, prijavaProfilaDto.getRazlogPrijave(), prijavljeniKorisnik, prodavac);
                        prijavaProfilaService.save(prijavaProfila);
                        break;
                    }
                }
                i++;
            }
            return new ResponseEntity<>("Uspesno ste podneli prijavu", HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("Nemate pravo za prijavu", HttpStatus.FORBIDDEN);
        }
    }



/*
    ////////////////////////////////2.2
    //U KorisnikService
    public Prodavac getProdavacById(int id) {
        Optional<Prodavac> prodavac = korisnikRepo.findByIdAndUloga(id, PRODAVAC);
        return prodavac.orElse(null);
    }

    public Kupac getKupacById(int id) {
        Optional<Kupac> kupac = korisnikRepo.findById(id);
        return kupac.orElse(null);
    }

    //u KorisnikRepository
    //ne mogu imati isto ime i iste parametre, a nz jel moze ovako
    //Optional<Prodavac> findById(int id);
    Optional<Prodavac> findByIdAndUloga(long id, Uloga uloga);
    Optional<Kupac> findById(long id);

    //u KorisnikDto
    private final double ProsecnaOcena;
    private Set<Proizvod> proizvodi = new HashSet<>();
    private Set<Recenzija> recenzije = new HashSet<>();

    public KorisnikDto(Prodavac prodavac) {
        this.ime = prodavac.getIme();
        this.prezime = prodavac.getPrezime();
        this.username = prodavac.getUsername();
        this.brojTelefona = prodavac.getBrojTelefona();
        this.datumRodjenja = prodavac.getDatumRodjenja();
        this.profilnaURL = prodavac.getProfilnaURL();
        this.opis = prodavac.getOpis();
        this.ProsecnaOcena = prodavac.getProsecnaOcena();
        this.proizvodi = prodavac.getProizvodiNaProdaju();
        this.recenzije = prodavac.getRecenzije();
    }

    public KorisnikDto(Kupac kupac) {
        this.ime = kupac.getIme();
        this.prezime = kupac.getPrezime();
        this.username = kupac.getUsername();
        this.brojTelefona = kupac.getBrojTelefona();
        this.datumRodjenja = kupac.getDatumRodjenja();
        this.profilnaURL = kupac.getProfilnaURL();
        this.opis = kupac.getOpis();
        this.ProsecnaOcena = kupac.getProsecnaOcena();
        this.proizvodi = kupac.getKupljeniProizvodi();
        this.recenzije = kupac.getRecenzije();
    }


    //u KorisnikController
    @GetMapping("/profile/buyer/{id}")
    public ResponseEntity<KorisnikDto> getProdavac(@PathVariable int id, HttpSession session) {
        Korisnik korisnik = (Korisnik) session.getAttribute("korisnik");
        if(korisnik == null) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        if(korisnik.getUloga() == KUPAC) {
            Prodavac prodavac = korisnikService.getProdavacById(id);
            if(prodavac == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(new KorisnikDto(prodavac), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @GetMapping("/profile/seller/{id}")
    public ResponseEntity<KorisnikDto> getKupac(@PathVariable int id, HttpSession session) {
        Korisnik korisnik = (Korisnik) session.getAttribute("korisnik");
        if(korisnik == null) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        if(korisnik.getUloga() == KUPAC) {
            Kupac kupac = korisnikService.getKupacById(id);
            if(kupac == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(new KorisnikDto(kupac), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }




/*
    /////////////////////////////////2.1
    //Dodati u korisnikDto
    private final LocalDate datumRodjenja;
    private final String profilnaURL;
    private final String opis;
    private final String potvrda_lozinke;

    public KorisnikDto(String ime, String prezime, String username, String password, String mail, String brojTelefona, LocalDate datumRodjenja, String profilnaURL, String opis, String potvrda_lozinke) {
        this.ime = ime;
        this.prezime = prezime;
        this.username = username;
        this.password = password;
        this.mail = mail;
        this.brojTelefona = brojTelefona;
        this.datumRodjenja = datumRodjenja;
        this.profilnaURL = profilnaURL;
        this.opis = opis;
        this.potvrda_lozinke = potvrda_lozinke;
    }

    public String getPotvrda_lozinke() {return potvrda_lozinke;}


    //Dodati u KorisnikController
    //Radjeno tako da Kupac ne moze da promeni svoju ulogu
    @PostMapping("/update_profile")
    public ResponseEntity<?> update_profile(@RequestBody KorisnikDto noviPodaci, HttpSession session) {
        Korisnik korisnik = (Korisnik) session.getAttribute("korisnik");

        if(korisnik == null) {
            return new ResponseEntity<>("Korisnik nije ulogovan!", HttpStatus.FORBIDDEN);
        }
        if(korisnik.getUloga() == KUPAC) {
            if (korisnik.getPassword() != noviPodaci.getPassword() || korisnik.getUsername() != noviPodaci.getUsername() || korisnik.getMail() != noviPodaci.getMail()) {
                if (korisnik.getPassword() != noviPodaci.getPotvrdaLozinke()) {
                    return new ResponseEntity<>("Uneli ste pogresnu lozinku!", HttpStatus.BAD_REQUEST);
                }
            }
            if(korisnikService.isExistentByEmail(noviPodaci.getMail())) {
                return new ResponseEntity<>("Uneti mail je vec u upotrebi!", HttpStatus.BAD_REQUEST);
            }
            if(korisnikService.isExistentByUsername(noviPodaci.getUsername())) {
                return new ResponseEntity<>("Uneti username je vec u upotrebi!", HttpStatus.BAD_REQUEST);
            }
            //ovo bi trebalo da izmeni podatke i u bazi podataka za tog korisnika ako je verovati internetu
            //potrebno izgenerisati setere kod korisnika
            korisnik.setIme(noviPodaci.getIme());
            korisnik.setPrezime(noviPodaci.getPrezime());
            korisnik.setUsername(noviPodaci.getUsername());
            korisnik.setPassword(noviPodaci.getPassword());
            korisnik.setMail(noviPodaci.getMail());
            korisnik.setBrojTelefona(noviPodaci.getBrojTelefona());
            korisnik.setDatumRodjenja(noviPodaci.getDatumRodjenja());
            korisnik.setProfilnaURL(noviPodaci.getProfilnaURL());
            korisnik.setOpis(noviPodaci.getOpis());
            return new ResponseEntity<>("Uspesno ste azurirali podatke!", HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("Korisnik nije kupac!", HttpStatus.FORBIDDEN);
        }
    }

 */
}

