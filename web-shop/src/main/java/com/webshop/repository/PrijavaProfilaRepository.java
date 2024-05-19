package com.webshop.repository;

import com.webshop.model.PrijavaProfila;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PrijavaProfilaRepository extends JpaRepository<PrijavaProfila, Long> {

    Optional<PrijavaProfila> findById(Long id);

}