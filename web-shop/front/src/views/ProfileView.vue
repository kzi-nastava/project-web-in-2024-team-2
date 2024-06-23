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
  <img :src="korisnik.profilnaURL" class="card-img-top" alt="slika">
  <div>  Korisnik : {{korisnik.ime}} {{korisnik.prezime}}</div>
  <div>  Uloga : {{korisnik.uloga}}</div>
  <div>  Broj Telefona: {{korisnik.brojTelefona}}</div>
  <div>  Prosecna ocena: {{korisnik.ocena}} </div>

  <div v-if="korisnik.uloga === 'KUPAC'"> Kupljeni proizvodi:
    <div v-for="proizvod in korisnik.proizvodi"> {{proizvod.naziv}} {{proizvod.cena}} </div>
    <div> Recenzije: </div>
    <div v-for="recenzija in korisnik.recenzije"> {{recenzija.ocena}} {{recenzija.komentar}} </div>
    <router-link :to="{ name: 'oceni-kupca', params: { id: korisnik.id}}"> Oceni kupca </router-link>
  </div>

  <div v-if="korisnik.uloga === 'PRODAVAC'"> Proizvodi na prodaju:
    <div v-for="proizvod in korisnik.proizvodi"> {{proizvod.naziv}} {{proizvod.cena}} </div>
    <div> Recenzije: </div>
    <div v-for="recenzija in korisnik.recenzije"> {{recenzija.ocena}} {{recenzija.komentar}}</div>
    <router-link :to="{ name: 'oceni-prodavca', params: { id: korisnik.id}}"> Oceni prodavca </router-link>
  </div>
</template>

<style scoped>

</style>