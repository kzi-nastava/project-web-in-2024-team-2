package com.webshop.controller;

import com.webshop.dto.*;
import com.webshop.model.*;
import com.webshop.repository.ProizvodRepository;
import com.webshop.repository.RecenzijaRepository;
import com.webshop.service.KorisnikService;
import com.webshop.service.ProizvodService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

import static com.webshop.model.Uloga.*;

@RestController
public class KorisnikController {

    @Autowired
    private KorisnikService korisnikService;
    @Autowired
    private ProizvodService proizvodService;
    @Autowired
    private ProizvodRepository proizvodRepository;
    @Autowired
    private RecenzijaRepository recenzijaRepository;

    @PostMapping("/register-user")
    public ResponseEntity<?> registerUser(@RequestBody RegisterDto registerDto) {
        if(!Objects.equals(registerDto.getPassword(), registerDto.getCheckPassword())) {
            return new ResponseEntity<>("Unesite ispravnu potvrdu lozinke!", HttpStatus.BAD_REQUEST);
        }
        KorisnikDto korisnikDto = new KorisnikDto(registerDto.getIme(), registerDto.getPrezime(), registerDto.getUsername(), registerDto.getPassword(), registerDto.getMail(), registerDto.getBrojTelefona(), registerDto.getUloga());
        if (korisnikService.isExistentByEmail(korisnikDto.getMail()) || korisnikService.isExistentByUsername(korisnikDto.getUsername())) {
            return new ResponseEntity<>("Korisnik vec postoji!", HttpStatus.CONFLICT);
        } else {
            if (korisnikDto.getUloga() == KUPAC) {
                korisnikService.createKupac(korisnikDto);
                return new ResponseEntity<>("Kupac se uspesno registrovao!", HttpStatus.OK);
            } else if (korisnikDto.getUloga() == PRODAVAC) {
                korisnikService.createProdavac(korisnikDto);
                return new ResponseEntity<>("Prodavac se uspesno registrovao!", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Bad request!", HttpStatus.BAD_REQUEST);
            }
        }
    }

    @PostMapping("/login-user")
    public ResponseEntity<?> loginUser(@RequestBody LoginDto loginDto, HttpSession session) {
        if (session.getAttribute("korisnik") != null) {
            return new ResponseEntity<>("Korisnik je vec ulogovan!", HttpStatus.BAD_REQUEST);
        }

        if (loginDto.getUsername().isEmpty() || loginDto.getPassword().isEmpty()) {
            return new ResponseEntity<>("Invalid login data!", HttpStatus.BAD_REQUEST);
        }

        Korisnik loggedKorisnik = korisnikService.login(loginDto.getUsername(), loginDto.getPassword());
        if (loggedKorisnik == null) {
            return new ResponseEntity<>("Korisnik ne postoji!", HttpStatus.BAD_REQUEST);
        }
        session.setAttribute("korisnik", loggedKorisnik);
        return new ResponseEntity<>("Logged in!", HttpStatus.OK);
    }

    @PostMapping("/logout-user")
    public ResponseEntity<?> logoutUser(HttpSession session) {
        Korisnik korisnik = (Korisnik) session.getAttribute("korisnik");

        if (korisnik == null) {
            return new ResponseEntity<>("Korisnik nije ulogovan!", HttpStatus.FORBIDDEN);
        }
        session.invalidate();
        return new ResponseEntity<>("Logged out!", HttpStatus.OK);
    }

    @PutMapping("/logged-user/update")
    public ResponseEntity<?> updateUser(@RequestBody KupacDto kupacDto, HttpSession session) {
        Korisnik loggedUser = (Korisnik) session.getAttribute("korisnik");

        if (loggedUser == null) {
            return new ResponseEntity<>("Nijedan korisnik nije prijavljen!", HttpStatus.BAD_REQUEST);
        }

        if (loggedUser.getUloga() == ADMINISTRATOR) {
            return new ResponseEntity<>("Ulogovani korisnik nije kupac ni prodavac!", HttpStatus.FORBIDDEN);
        }

        if (kupacDto.getUsername() != null || kupacDto.getMail() != null || kupacDto.getPassword() != null
        || !Objects.equals(kupacDto.getUsername(), "") || !Objects.equals(kupacDto.getMail(), "") || !Objects.equals(kupacDto.getPassword(), "")) {
            if (!korisnikService.checkPassword(loggedUser.getId(), kupacDto.getOldPassword())) {
                return new ResponseEntity<>("Trenutna lozinka nije tačna.", HttpStatus.BAD_REQUEST);
            }

            if (kupacDto.getUsername() != null && !Objects.equals(kupacDto.getUsername(), "")) {
                loggedUser.setUsername(kupacDto.getUsername());
            }

            if (kupacDto.getMail() != null && !Objects.equals(kupacDto.getMail(), "")) {
                loggedUser.setMail(kupacDto.getMail());
            }

            if(kupacDto.getPassword() != null && !Objects.equals(kupacDto.getPassword(), "")) {
                loggedUser.setPassword(kupacDto.getPassword());
            }
        }

        if (kupacDto.getIme() != null && !Objects.equals(kupacDto.getIme(), ""))
            loggedUser.setIme(kupacDto.getIme());

        if (kupacDto.getPrezime() != null && !Objects.equals(kupacDto.getPrezime(), ""))
            loggedUser.setPrezime(kupacDto.getPrezime());

        if (kupacDto.getBrojTelefona() != null && !Objects.equals(kupacDto.getBrojTelefona(), ""))
            loggedUser.setBrojTelefona(kupacDto.getBrojTelefona());

        if (kupacDto.getDatumRodjenja() != null)
            loggedUser.setDatumRodjenja(kupacDto.getDatumRodjenja());

        if (kupacDto.getProfilnaURL() != null && !Objects.equals(kupacDto.getProfilnaURL(), ""))
            loggedUser.setProfilnaURL(kupacDto.getProfilnaURL());

        if (kupacDto.getOpis() != null &&!Objects.equals(kupacDto.getOpis(), ""))
            loggedUser.setOpis(kupacDto.getOpis());

        korisnikService.saveKorisnik(loggedUser);
        return new ResponseEntity<>("Korisnik je uspesno azurirao podatke!", HttpStatus.OK);
    }

    @GetMapping("/profiles")
    public ResponseEntity<?> getProfiles(HttpSession session) {
        Korisnik loggedKorisnik = (Korisnik) session.getAttribute("korisnik");
        if (loggedKorisnik == null) {
            return new ResponseEntity<>("Nema prijavljenih korisnika!", HttpStatus.FORBIDDEN);
        }

        List<Korisnik> korisnikList = korisnikService.getKorisnikList();
        List<PrikazProfilaFrontDto> profiliDto = new ArrayList<>();
        for (Korisnik korisnik : korisnikList) {
            if (korisnik.getUloga() != ADMINISTRATOR) {
                profiliDto.add(new PrikazProfilaFrontDto(korisnik, 0));
            }
        }
        return new ResponseEntity<>(profiliDto, HttpStatus.OK);
    }

    @GetMapping("/profile/{id}")
    public ResponseEntity<?> getProfile(HttpSession session, @PathVariable long id) {
        Korisnik loggedKorisnik = (Korisnik) session.getAttribute("korisnik");
        if(loggedKorisnik == null) {
            return new ResponseEntity<>("Nema prijavljenih korisnika!",HttpStatus.FORBIDDEN);
        }
        Optional<Korisnik> korisnikOpt = korisnikService.getById(id);
        Korisnik korisnik = korisnikOpt.get();
        Kupac kupac;
        Prodavac prodavac;

        if(korisnik.getUloga() == PRODAVAC) {
            prodavac = (Prodavac) korisnik;
            Set<Proizvod> proizvodi = prodavac.getProizvodiNaProdaju();
            Set<Proizvod> proizvodiNaProdaju = new HashSet<>();
            for (Proizvod proizvod : proizvodi) {
                if(!proizvod.isProdat()) {
                    proizvodiNaProdaju.add(proizvod);
                }
            }
            Set<Recenzija> recenzije = prodavac.getDobijenaRecenzija();
            double ocena = prodavac.getProsecnaOcena();
            PrikazProfilaFrontDto profil = new PrikazProfilaFrontDto(prodavac,prodavac.getProsecnaOcena(), proizvodiNaProdaju, recenzije);
            return new ResponseEntity<>(profil, HttpStatus.OK);
        }

        if(korisnik.getUloga() == KUPAC) {
            kupac = (Kupac) korisnik;
            Set<Proizvod> proizvodi = kupac.getKupljeniProizvodi();
            Set<Recenzija> recenzije = kupac.getDobijenaRecenzija();
            double ocena = kupac.getProsecnaOcena();
            PrikazProfilaFrontDto profil = new PrikazProfilaFrontDto(kupac,kupac.getProsecnaOcena(), proizvodi, recenzije);
            return new ResponseEntity<>(profil, HttpStatus.OK);
        }


        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}