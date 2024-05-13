INSERT INTO KORISNIK (blokiran, datum_rodjenja, prosecna_ocena, uloga, broj_telefona, ime, mail, opis, password, prezime, profilnaurl, username) VALUES (false, current_date, 5, 'Prodavac', '04321351', 'Nemanja', 'nekimail@gmail.com', 'top g', 'lozinkolozic', 'Radic', 'url', 'Nemanja12');
INSERT INTO KORISNIK (blokiran, datum_rodjenja, prosecna_ocena, uloga, broj_telefona, ime, mail, opis, password, prezime, profilnaurl, username) VALUES (true, current_date, 4.78, 'Kupac', '063283921', 'Ivan', 'nekimail1@gmail.com', 'lep decko1', 'lozinka341', 'Ristic', 'url', 'Ivan123');
INSERT INTO KORISNIK (blokiran, datum_rodjenja, prosecna_ocena, uloga, broj_telefona, ime, mail, opis, password, prezime, profilnaurl, username) VALUES (false, current_date, 3.65, 'Administrator', '061801294', 'Isidor', 'nekimail2@gmail.com', 'lep decko2', 'lozinka123', 'Ivanov', 'url', 'Isidor3');


INSERT INTO PRIJAVA_PROFILA (datum_podnosenja_prijave, status_prijave, odnosi_se_id, podnosilac_id, razlog_prijave) VALUES (current_date, 'PODNETA', 1, 1, 'tako mi doslo');
INSERT INTO PRIJAVA_PROFILA (datum_podnosenja_prijave, status_prijave, odnosi_se_id, podnosilac_id, razlog_prijave) VALUES (current_date, 'ODBIJENA', 3, 2, 'neadekvatan recnik');
INSERT INTO PRIJAVA_PROFILA (datum_podnosenja_prijave, status_prijave, odnosi_se_id, podnosilac_id, razlog_prijave) VALUES (current_date, 'PRIHVACENA', 2, 3, 'vredja');
INSERT INTO PRIJAVA_PROFILA (datum_podnosenja_prijave, status_prijave, odnosi_se_id, podnosilac_id, razlog_prijave) VALUES (current_date, 'PRIHVACENA', 1, 2, 'vredja');


INSERT INTO KATEGORIJA (naziv) VALUES ('Alat');
INSERT INTO KATEGORIJA (naziv) VALUES ('IT');
INSERT INTO KATEGORIJA (naziv) VALUES ('Namestaj');
INSERT INTO KATEGORIJA (naziv) VALUES ('Elektronika');



INSERT INTO PROIZVOD (cena, datum_objave, kategorija_id, tip_prodaje,  naziv, opis, profilnaurl, kupio_kupac_id, prodavac_id) VALUES (34500, current_date, 2, 'AUKCIJA', 'Ugaona garnitura', 'Simpo ugaona garnitura', 'url', 1, 2);
INSERT INTO PROIZVOD (cena, datum_objave, kategorija_id, tip_prodaje,  naziv, opis, profilnaurl, kupio_kupac_id, prodavac_id) VALUES (5000, current_date, 1, 'FIKSNA_CENA', 'RAM', '16GB DDR5 6400MHz', 'url', 2, 1);
INSERT INTO PROIZVOD (cena, datum_objave, kategorija_id, tip_prodaje,  naziv, opis, profilnaurl, kupio_kupac_id, prodavac_id) VALUES (5000, current_date, 2,'FIKSNA_CENA', 'Procesor', 'Intel® Core™ i5-12500H Processor up to 4.50 GHz', 'url', 1, 1);
INSERT INTO PROIZVOD (cena, datum_objave, kategorija_id, tip_prodaje,  naziv, opis, profilnaurl, kupio_kupac_id, prodavac_id) VALUES (7800, current_date, 3, 'FIKSNA_CENA', 'Mesalica', 'mesalica neke marke', 'url', 2, 2);




INSERT INTO RECENZIJA (datum_recenzije, ocena, korisnik_id, komentar, recenzija_za_kupca, recenzija_za_prodavca) VALUES (current_date, 5, 1, 'svaka cast', 1, 2);
INSERT INTO RECENZIJA (datum_recenzije, ocena, korisnik_id, komentar, recenzija_za_kupca, recenzija_za_prodavca) VALUES (current_date, 3, 1, 'dobro odradjen posao', 2, 1);
INSERT INTO RECENZIJA (datum_recenzije, ocena, korisnik_id, komentar, recenzija_za_kupca, recenzija_za_prodavca) VALUES (current_date, 4, 1, 'bravo', 2, 2);

INSERT INTO PONUDA (cena, kupac_id, proizvod_id) VALUES (5000, 1, 1);
INSERT INTO PONUDA (cena, kupac_id, proizvod_id) VALUES (5000, 1, 2);
INSERT INTO PONUDA (cena, kupac_id, proizvod_id) VALUES (5000, 2, 3);
INSERT INTO PONUDA (cena, kupac_id, proizvod_id) VALUES (5000, 2, 2);
