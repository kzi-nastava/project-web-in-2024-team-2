INSERT INTO KATEGORIJA (naziv) VALUES ('Alat');
INSERT INTO KATEGORIJA (naziv) VALUES ('IT');
INSERT INTO KATEGORIJA (naziv) VALUES ('Namestaj');
INSERT INTO KATEGORIJA (naziv) VALUES ('Elektronika');

INSERT INTO KORISNIK (blokiran, datum_rodjenja, prosecna_ocena, uloga, broj_telefona, ime, mail, opis, password, prezime, profilnaurl, username) VALUES (false, current_date, 5, 0, '04321351', 'Nemanja', 'nemanjaradic234@gmail.com', 'top g', 'lozinkolozic', 'Radic', 'https://uxwing.com/wp-content/themes/uxwing/download/peoples-avatars/man-user-circle-icon.png', 'Nemanja12');
INSERT INTO KORISNIK (blokiran, datum_rodjenja, prosecna_ocena, uloga, broj_telefona, ime, mail, opis, password, prezime, profilnaurl, username) VALUES (false, current_date, 4.78, 1, '063283921', 'Ivan', 'e14nemanjaradic@gmail.com', 'lep decko1', 'lozinka341', 'Ristic', 'https://cdn-icons-png.freepik.com/512/219/219986.png', 'Ivan123');
INSERT INTO KORISNIK (blokiran, datum_rodjenja, prosecna_ocena, uloga, broj_telefona, ime, mail, opis, password, prezime, profilnaurl, username) VALUES (false, current_date, 4.97, 1, '063728123', 'Jovan', 'jovan34@gmail.com', 'dusa od coveka', 'jovan34', 'Trkulja', 'https://cdn-icons-png.freepik.com/512/219/219986.png', 'JovanTrk');
INSERT INTO KORISNIK (blokiran, datum_rodjenja, prosecna_ocena, uloga, broj_telefona, ime, mail, opis, password, prezime, profilnaurl, username) VALUES (false, current_date, 2.14, 2, '062124921', 'Dragan', 'dragan1@gmail.com', 'gotivan', 'gidra123', 'Jelic', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSKd660VhmxQvYEIS2_B3Sv-J0myC_4seDOhA&s', 'gidra789');
INSERT INTO KORISNIK (blokiran, datum_rodjenja, prosecna_ocena, uloga, broj_telefona, ime, mail, opis, password, prezime, profilnaurl, username) VALUES (false, current_date, 4.34, 0, '069218924', 'Milan', 'milan23@gmail.com', 'pa milan', 'mikimilan12', 'Milanovic', 'https://uxwing.com/wp-content/themes/uxwing/download/peoples-avatars/man-user-circle-icon.png', 'mikimilan234');

INSERT INTO PROIZVOD (cena, datum_objave, kategorija_id, tip_prodaje,  naziv, opis, profilnaurl, prodavac_id, recenzija_kupca_ostavljena, recenzija_prodavca_ostavljena, prodat) VALUES (34500, current_date, 3, 'AUKCIJA', 'Ugaona garnitura', 'Simpo ugaona garnitura', 'https://bplus.rs/wp-content/uploads/2020/03/ug-caracas2.jpg', 2, true, true, false);
INSERT INTO PROIZVOD (cena, datum_objave, kategorija_id, tip_prodaje,  naziv, opis, profilnaurl, prodavac_id, recenzija_kupca_ostavljena, recenzija_prodavca_ostavljena, prodat) VALUES (5000, current_date, 2, 'FIKSNA_CENA', 'RAM', '16GB DDR5 6400MHz', 'https://knv.rs//images/product/2024/03/06/3b25fe45022831971fe6237a09b125b3.jpg.jpg', 2, true, true, false);
INSERT INTO PROIZVOD (cena, datum_objave, kategorija_id, tip_prodaje,  naziv, opis, profilnaurl, prodavac_id, recenzija_kupca_ostavljena, recenzija_prodavca_ostavljena, prodat) VALUES (3500, current_date, 2, 'FIKSNA_CENA', 'RAM Module', '16GB DDR4 3200MHz', 'https://3gstore.rs//p/261/261435/ram-ddr4-silicon-power-4gb-2666mhz-sp004gblfu266n02-142953-154374-132705.png', 3, false, true, false);
INSERT INTO PROIZVOD (cena, datum_objave, kategorija_id, tip_prodaje,  naziv, opis, profilnaurl, prodavac_id, recenzija_kupca_ostavljena, recenzija_prodavca_ostavljena, prodat) VALUES (5000, current_date, 2,'FIKSNA_CENA', 'Procesor', 'Intel® Core™ i5-12500H Processor up to 4.50 GHz', 'https://www.gamecentar.rs/media/catalog/product/cache/3f726396bb6a19726231308fc4c8c5aa/3/3/33472-thumbnailintel-core-i5-12400f-6core-2-5ghz-box.jpg', 3, false, true, false);
INSERT INTO PROIZVOD (cena, datum_objave, kategorija_id, tip_prodaje,  naziv, opis, profilnaurl, prodavac_id, recenzija_kupca_ostavljena, recenzija_prodavca_ostavljena, prodat) VALUES (7800, current_date, 1, 'FIKSNA_CENA', 'Mesalica', 'mesalica neke marke', 'https://www.bosshop.rs/media/2020/09/me%C5%A1alica.jpg', 3, false, false, false);
INSERT INTO PROIZVOD (cena, datum_objave, kategorija_id, tip_prodaje,  naziv, opis, profilnaurl, prodavac_id, recenzija_kupca_ostavljena, recenzija_prodavca_ostavljena, prodat) VALUES (7800, current_date, 1, 'FIKSNA_CENA', 'Mesalica', 'mesalica neke marke', 'https://www.bosshop.rs/media/2020/09/me%C5%A1alica.jpg', 3, false, false, false);
INSERT INTO PROIZVOD (cena, datum_objave, kategorija_id, tip_prodaje,  naziv, opis, profilnaurl, prodavac_id, recenzija_kupca_ostavljena, recenzija_prodavca_ostavljena, prodat) VALUES (7800, current_date, 1, 'FIKSNA_CENA', 'Mesalica', 'mesalica neke marke', 'https://www.bosshop.rs/media/2020/09/me%C5%A1alica.jpg', 3, false, false, false);
INSERT INTO PROIZVOD (cena, datum_objave, kategorija_id, tip_prodaje,  naziv, opis, profilnaurl, prodavac_id, recenzija_kupca_ostavljena, recenzija_prodavca_ostavljena, prodat) VALUES (7800, current_date, 1, 'FIKSNA_CENA', 'Mesalica', 'mesalica neke marke', 'https://www.bosshop.rs/media/2020/09/me%C5%A1alica.jpg', 3, false, false, false);
INSERT INTO PROIZVOD (cena, datum_objave, kategorija_id, tip_prodaje,  naziv, opis, profilnaurl, prodavac_id, recenzija_kupca_ostavljena, recenzija_prodavca_ostavljena, prodat) VALUES (7800, current_date, 1, 'FIKSNA_CENA', 'Mesalica', 'mesalica neke marke', 'https://www.bosshop.rs/media/2020/09/me%C5%A1alica.jpg', 3, false, false, false);
INSERT INTO PROIZVOD (cena, datum_objave, kategorija_id, tip_prodaje,  naziv, opis, profilnaurl, prodavac_id, recenzija_kupca_ostavljena, recenzija_prodavca_ostavljena, prodat) VALUES (7800, current_date, 1, 'FIKSNA_CENA', 'Mesalica', 'mesalica neke marke', 'https://www.bosshop.rs/media/2020/09/me%C5%A1alica.jpg', 3, false, false, false);
INSERT INTO PROIZVOD (cena, datum_objave, kategorija_id, tip_prodaje,  naziv, opis, profilnaurl, prodavac_id, recenzija_kupca_ostavljena, recenzija_prodavca_ostavljena, prodat) VALUES (7800, current_date, 1, 'FIKSNA_CENA', 'Mesalica', 'mesalica neke marke', 'https://www.bosshop.rs/media/2020/09/me%C5%A1alica.jpg', 3, false, false, false);
INSERT INTO PROIZVOD (cena, datum_objave, kategorija_id, tip_prodaje,  naziv, opis, profilnaurl, prodavac_id, recenzija_kupca_ostavljena, recenzija_prodavca_ostavljena, prodat) VALUES (7800, current_date, 1, 'FIKSNA_CENA', 'Mesalica', 'mesalica neke marke', 'https://www.bosshop.rs/media/2020/09/me%C5%A1alica.jpg', 3, false, false, false);
INSERT INTO PROIZVOD (cena, datum_objave, kategorija_id, tip_prodaje,  naziv, opis, profilnaurl, prodavac_id, recenzija_kupca_ostavljena, recenzija_prodavca_ostavljena, prodat) VALUES (7800, current_date, 1, 'FIKSNA_CENA', 'Mesalica', 'mesalica neke marke', 'https://www.bosshop.rs/media/2020/09/me%C5%A1alica.jpg', 3, false, false, false);
INSERT INTO PROIZVOD (cena, datum_objave, kategorija_id, tip_prodaje,  naziv, opis, profilnaurl, prodavac_id, recenzija_kupca_ostavljena, recenzija_prodavca_ostavljena, prodat) VALUES (7800, current_date, 1, 'FIKSNA_CENA', 'Mesalica', 'mesalica neke marke', 'https://www.bosshop.rs/media/2020/09/me%C5%A1alica.jpg', 3, false, false, false);

INSERT INTO RECENZIJA (datum_recenzije, ocena, podnosilac_id, komentar) VALUES (current_date, 5, 3, 'svaka cast');
INSERT INTO RECENZIJA (datum_recenzije, ocena, podnosilac_id, komentar) VALUES (current_date, 3, 1, 'dobro odradjen posao');
INSERT INTO RECENZIJA (datum_recenzije, ocena, podnosilac_id, komentar) VALUES (current_date, 4, 1, 'bravo');
INSERT INTO RECENZIJA (datum_recenzije, ocena, podnosilac_id, komentar) VALUES (current_date, 4, 1, 'odlicno');
INSERT INTO RECENZIJA (datum_recenzije, ocena, podnosilac_id, komentar) VALUES (current_date, 4, 2, 'dobar');

-- INSERT INTO KORISNIK_KUPLJENI_PROIZVODI (kupac_id, kupljeni_proizvodi_id) VALUES (1, 1);
INSERT INTO KORISNIK_KUPLJENI_PROIZVODI (kupac_id, kupljeni_proizvodi_id) VALUES (1, 2);
INSERT INTO KORISNIK_KUPLJENI_PROIZVODI (kupac_id, kupljeni_proizvodi_id) VALUES (1, 4);
INSERT INTO KORISNIK_KUPLJENI_PROIZVODI (kupac_id, kupljeni_proizvodi_id) VALUES (1, 3);
INSERT INTO KORISNIK_KUPLJENI_PROIZVODI (kupac_id, kupljeni_proizvodi_id) VALUES (1, 5);

INSERT INTO KORISNIK_DOBIJENA_RECENZIJA (dobijena_recenzija_id, korisnik_id) VALUES (1, 3);
INSERT INTO KORISNIK_DOBIJENA_RECENZIJA (dobijena_recenzija_id, korisnik_id) VALUES (2, 2);
INSERT INTO KORISNIK_DOBIJENA_RECENZIJA (dobijena_recenzija_id, korisnik_id) VALUES (3, 2);
INSERT INTO KORISNIK_DOBIJENA_RECENZIJA (dobijena_recenzija_id, korisnik_id) VALUES (4, 3);
INSERT INTO KORISNIK_DOBIJENA_RECENZIJA (dobijena_recenzija_id, korisnik_id) VALUES (5, 1);

INSERT INTO PRIJAVA_PROFILA (datum_podnosenja_prijave, status_prijave, odnosi_se_id, podnosilac_id, razlog_prijave) VALUES (current_date, 'PODNETA', 2, 1, 'tako mi doslo');
INSERT INTO PRIJAVA_PROFILA (datum_podnosenja_prijave, status_prijave, odnosi_se_id, podnosilac_id, razlog_prijave) VALUES (current_date, 'PODNETA', 3, 2, 'neadekvatan recnik');
INSERT INTO PRIJAVA_PROFILA (datum_podnosenja_prijave, status_prijave, odnosi_se_id, podnosilac_id, razlog_prijave) VALUES (current_date, 'PODNETA', 2, 3, 'vredja');
INSERT INTO PRIJAVA_PROFILA (datum_podnosenja_prijave, status_prijave, odnosi_se_id, podnosilac_id, razlog_prijave) VALUES (current_date, 'PODNETA', 1, 2, 'nema smisla sta covek prica');

INSERT INTO PONUDA (cena, kupac_id) VALUES (5000, 1);
INSERT INTO PONUDA (cena, kupac_id) VALUES (34000, 1);
INSERT INTO PONUDA (cena, kupac_id) VALUES (8300, 1);
INSERT INTO PONUDA (cena, kupac_id) VALUES (2300, 1);
INSERT INTO PONUDA (cena, kupac_id) VALUES (35000, 1);

-- INSERT INTO PROIZVOD_PONUDA (ponuda_id, proizvod_id) VALUES (1, 1);
INSERT INTO PROIZVOD_PONUDA (ponuda_id, proizvod_id) VALUES (2, 1);
INSERT INTO PROIZVOD_PONUDA (ponuda_id, proizvod_id) VALUES (3, 2);
-- INSERT INTO PROIZVOD_PONUDA (ponuda_id, proizvod_id) VALUES (4, 3);
INSERT INTO PROIZVOD_PONUDA (ponuda_id, proizvod_id) VALUES (5, 1);