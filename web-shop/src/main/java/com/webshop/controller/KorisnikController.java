package com.webshop.controller;

import com.webshop.dto.KorisnikDto;
import com.webshop.dto.KupacDto;
import com.webshop.dto.LoginDto;
import com.webshop.model.Korisnik;
import com.webshop.model.Kupac;
import com.webshop.model.Prodavac;
import com.webshop.model.Uloga;
import com.webshop.service.KorisnikService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KorisnikController {

    @Autowired
    private KorisnikService korisnikService;

    @PostMapping("/register-user")
    public ResponseEntity<?> registerUser(@RequestBody KorisnikDto korisnikDto) {
        if (korisnikService.isExistentByEmail(korisnikDto.getMail()) || korisnikService.isExistentByUsername(korisnikDto.getUsername())) {
            return new ResponseEntity<>("Korisnik vec postoji!", HttpStatus.CONFLICT);
        } else {
            if (korisnikDto.getUloga() == Uloga.KUPAC) {
                korisnikService.createKupac(korisnikDto);
                return new ResponseEntity<>("Kupac se uspesno registrovao!", HttpStatus.OK);
            } else if (korisnikDto.getUloga() == Uloga.PRODAVAC) {
                korisnikService.createProdavac(korisnikDto);
                return new ResponseEntity<>("Prodavac se uspesno registrovao!", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Bad request!", HttpStatus.BAD_REQUEST);
            }
        }
    }

    @PostMapping("/login-user")
    public ResponseEntity<?> loginUser(@RequestBody LoginDto loginDto, HttpSession session) {
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
            return new ResponseEntity<>("KOrisnik nije ulogovan!", HttpStatus.FORBIDDEN);
        }
        session.invalidate();
        return new ResponseEntity<>("Logged out!", HttpStatus.OK);
    }
}

    @PutMapping("/logged-user/update")
    public ResponseEntity<?> updateUser(@RequestBody KupacDto kupacDto, HttpSession session) {
        Korisnik loggedUser = (Korisnik) session.getAttribute("korisnik");

        if (loggedUser == null) {
            return new ResponseEntity<>("Nijedan korisnik nije prijavljen!", HttpStatus.BAD_REQUEST);
        }

        if (loggedUser.getUloga() != KUPAC) {
            return new ResponseEntity<>("Ulogovani korisnik nije kupac!", HttpStatus.FORBIDDEN);
        }

        if (kupacDto.getUsername() != null || kupacDto.getMail() != null) {
            if (!korisnikService.checkPassword(loggedUser.getId(), kupacDto.getPassword())) {
                return new ResponseEntity<>("Trenutna lozinka nije tačna.", HttpStatus.BAD_REQUEST);
            }

            if (kupacDto.getUsername() != null) {
                loggedUser.setUsername(kupacDto.getUsername());
            }

            if (kupacDto.getMail() != null) {
                loggedUser.setMail(kupacDto.getMail());
            }
        }
