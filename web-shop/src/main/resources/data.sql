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

INSERT INTO PROIZVOD (cena, datum_objave, prodat, recenzija_kupca_ostavljena, recenzija_prodavca_ostavljena, tip_prodaje, kategorija_id, kupac_id, prodavac_id, naziv, opis, profilnaurl) VALUES (12342, current_date, false, true, true, 'FIKSNA_CENA', 1, 2, 1, 'Hilti busilica', 'jaka busilica', 'url');
INSERT INTO PROIZVOD (cena, datum_objave, prodat, recenzija_kupca_ostavljena, recenzija_prodavca_ostavljena, tip_prodaje, kategorija_id, kupac_id, prodavac_id, naziv, opis, profilnaurl) VALUES (34500, current_date, true, false, true, 'AUKCIJA', 2, 2, 1, 'Ugaona garnitura', 'Simpo ugaona garnitura', 'url');
INSERT INTO PROIZVOD (cena, datum_objave, prodat, recenzija_kupca_ostavljena, recenzija_prodavca_ostavljena, tip_prodaje, kategorija_id, kupac_id, prodavac_id, naziv, opis, profilnaurl) VALUES (5000, current_date, true, false, true, 'FIKSNA_CENA', 3, 2, 1, 'RAM', '16GB DDR5 6400MHz', 'url');
INSERT INTO PROIZVOD (cena, datum_objave, prodat, recenzija_kupca_ostavljena, recenzija_prodavca_ostavljena, tip_prodaje, kategorija_id, kupac_id, prodavac_id, naziv, opis, profilnaurl) VALUES (5000, current_date, true, false, true, 'FIKSNA_CENA', 3, 2, 1, 'RAM', '16GB DDR5 6400MHz', 'url');

INSERT INTO RECENZIJA (datum_recenzije, ocena, korisnik_id, komentar) VALUES (current_date, 5, 1, 'svaka cast');
INSERT INTO RECENZIJA (datum_recenzije, ocena, korisnik_id, komentar) VALUES (current_date, 3, 1, 'dobro odradjen posao');
INSERT INTO RECENZIJA (datum_recenzije, ocena, korisnik_id, komentar) VALUES (current_date, 4, 1, 'bravo');

INSERT INTO PONUDA (cena, kupac_id, proizvod_id) VALUES (5000, 1, 2);
INSERT INTO PONUDA (cena, kupac_id, proizvod_id) VALUES (5000, 1, 3);
INSERT INTO PONUDA (cena, kupac_id, proizvod_id) VALUES (5000, 1, 1);
INSERT INTO PONUDA (cena, kupac_id, proizvod_id) VALUES (5000, 2, 1);