package com.webshop.repository;

import com.webshop.model.Proizvod;
import com.webshop.model.TipProdaje;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProizvodRepository extends JpaRepository<Proizvod, Long> {

    Page<Proizvod> findAll(Pageable pageable);

    Optional<Proizvod> findById(long id);

    Optional<List<Proizvod>> findAllByNazivContainsIgnoreCaseOrOpisContainsIgnoreCase(String naziv, String opis);

    @Query("SELECT p FROM Proizvod p WHERE (:cenaOd IS NULL OR p.cena >= :cenaOd) AND (:cenaDo IS NULL OR p.cena <= :cenaDo) AND (:tipProdaje IS NULL OR p.tipProdaje = :tipProdaje) AND (:kategorija IS NULL OR p.kategorija.naziv = :kategorija)")
    Optional<List<Proizvod>> findAllByFilter(@Param("cenaOd") Double cenaOd, @Param("cenaDo") Double cenaDo, @Param("tipProdaje") TipProdaje tipProdaje, @Param("kategorija") String kategorija);

    List<Proizvod> findAllByKategorijaNaziv(String kategorija);

    @Transactional
    @Modifying
    @Query("DELETE FROM Proizvod p WHERE p.prodavac.id = :id")
    void deleteByProdavacId(Long id);
}