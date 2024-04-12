INSERT INTO KORISNIK (ime, prezime, username, mail, broj_telefona, datum_rodjenja, profilnaurl, opis, uloga, blokiran, prosecna_ocena, prijavljen_korisnik_id, dtype) VALUES ('Nemanja', 'Radic', 'nemanjaRadic12', 'nekimail@gmail.com', '0659932584', '2003-09-25', 'nekiUrl', 'korisnik1', 0, false, 5, 1, 's');

INSERT INTO PROIZVOD (naziv, opis, profilnaurl, kategorija_id, cena, tip_prodaje, datum_objave, recenzija_kupca, recenzija_prodavca, prodat, kupac_id, prodavac_id) VALUES ('sporet', 'smedereveac', 'nekiUrl', 1, 25000, 0, '2024-04-11', true, false, false, 1, 1);

INSERT INTO KATEGORIJA (naziv) VALUES ('uredjaj');

INSERT INTO PONUDA (cena, proizvod_id) VALUES (30000, 1);

INSERT INTO RECENZIJA (ocena, komentar, datumRecenzije, kupac_id, korisnik_id) VALUES (5, 'jak sporet bas', current_date, 1, 1);

INSERT INTO PRIJAVA_PROFILA (datum_podnosenja_prijave, status_prijave, podnosilac_id) VALUES (current_date, 0, 1);