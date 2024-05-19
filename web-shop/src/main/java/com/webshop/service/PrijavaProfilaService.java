
package com.webshop.service;

import com.webshop.model.Korisnik;
import com.webshop.model.PrijavaProfila;
import com.webshop.repository.PrijavaProfilaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PrijavaProfilaService {

    @Autowired
    PrijavaProfilaRepository prijavaProfilaRepository;

    public PrijavaProfila getPrijavaProfilaById(Long id) {
        Optional<PrijavaProfila> prijavaProfila = prijavaProfilaRepository.findById(id);
        return prijavaProfila.orElse(null);
    }

    public void savePrijava(PrijavaProfila prijavaProfila) {
        prijavaProfilaRepository.save(prijavaProfila);
    }

}
