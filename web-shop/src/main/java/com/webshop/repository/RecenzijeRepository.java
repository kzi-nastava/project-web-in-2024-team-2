package com.webshop.repository;

import com.webshop.model.Recenzija;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecenzijeRepository extends JpaRepository<Recenzija, Long> {

    Recenzija save(Recenzija recenzija);

}
