<script>
import axios from 'axios';
export default {
  name: 'ProfileView',
  data() {
    return {
      korisnik: {}
    };
  },
  created() {
    this.prikaziProfil();
  },
  methods: {
    prikaziProfil() {
      const id = this.$route.params.id;
      console.log(id);
      axios.get(`http://localhost:8081/profile/${id}`, {withCredentials: true, headers: {'Cache-Control': 'no-cache'
      }})
      .then((response) => {
        this.korisnik = response.data;
      })
      .catch((error) => {
        console.log(error);
      });
    }
  },
};
</script>

<template>
  <div class="user-card">
  <img :src="korisnik.profilnaURL" class="card-img-top" alt="slika">
  <div>  Korisnik : {{korisnik.ime}} {{korisnik.prezime}}</div>
  <div>  Uloga : {{korisnik.uloga}}</div>
  <div>  Broj Telefona: {{korisnik.brojTelefona}}</div>
  <div>  Prosecna ocena: {{korisnik.ocena}} </div>

    <div v-if="korisnik.uloga === 'KUPAC'"> <strong> Kupljeni proizvodi: </strong>
    <ul>
       <li v-for="proizvod in korisnik.proizvodi"> Proizvod: {{proizvod.naziv}}, cena: {{proizvod.cena}} din.</li>
    </ul>
    <div> <strong> Recenzije: </strong> </div>
    <ul>
      <li v-for="recenzija in korisnik.recenzije"> {{recenzija.komentar}}, {{recenzija.ocena}}</li>
    </ul>
    <router-link :to="{ name: 'oceni-kupca', params: { id: korisnik.id}}" class="vidi-jos-link"> Oceni kupca </router-link> <br>
    <router-link :to="{ name: 'prijavi-kupca', params: { id: korisnik.id}}" class="vidi-jos-link"> Prijavi Kupca </router-link>
  </div>

    <div v-if="korisnik.uloga === 'PRODAVAC'"> <strong> Proizvodi na prodaju: </strong>
    <ul>
      <li v-for="proizvod in korisnik.proizvodi"> Proizvod: {{proizvod.naziv}}, cena: {{proizvod.cena}}  din.</li>
    </ul>
      <div> <strong> Recenzije: </strong> </div>
    <ul>
      <li v-for="recenzija in korisnik.recenzije"> {{recenzija.komentar}}, {{recenzija.ocena}}</li>
    </ul>
    <router-link :to="{ name: 'oceni-prodavca', params: { id: korisnik.id}}" class="vidi-jos-link"> Oceni prodavca </router-link> <br>
    <router-link :to="{ name: 'prijavi-prodavca', params: {id: korisnik.id}}" class="vidi-jos-link"> Prijavi prodavca </router-link>
  </div>
  </div>

</template>

<style scoped>
.user-card {
  margin-right: 1000px;
  font-family: Arial, sans-serif; /* Promenite font ako želite neki drugi */
  font-size: 1.3em; /* Promenite veličinu fonta po potrebi */
  color: #333;
}

.vidi-jos-link {
  display: inline-block;
  margin-top: 10px;
  text-decoration: none;
  padding: 5px 10px;
  background-color: #198754;
  color: white;
  border: 1px solid #198754; /* Dodajemo plavi okvir */
  border-radius: 5px;
}

.vidi-jos-link:hover {
  background-color: #0069d9;
  border-color: #0069d9; /* Promena boje okvira pri hoveru */
}
</style>