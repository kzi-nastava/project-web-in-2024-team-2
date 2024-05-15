package com.webshop.service;

import com.webshop.model.Recenzija;
import com.webshop.repository.RecenzijeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecenzijeService {

    @Autowired
    private RecenzijeRepository recenzijeRepository;

    public Recenzija save(Recenzija recenzija) {
        return this.recenzijeRepository.save(recenzija);
    }

}
