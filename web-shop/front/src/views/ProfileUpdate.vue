<script>
import axios from 'axios';

export default {
  name: "ProfileUpdate",
  data() {
    return {
      userData: {
        ime: '',
        prezime: '',
        username: '',
        password: '',
        mail: '',
        brojTelefona: '',
        datumRodenja: '',
        profilnaURL: '',
        opis: '',
        oldPassword: '',
      }
    };
  },
  mounted() {
    this.updateUser();
  },
  methods: {
    updateUser() {
      axios.put('http://localhost:8081/logged-user/update', this.userData, {withCredentials: true})
      .then((res) => {
        localStorage.setItem('korisnik', JSON.stringify(res.data));
        alert("Korisnik je uspesno azuriran!");
        if(res.data.uloga === 'KUPAC') {
          this.$router.push('/kupac_view');
        }
        else if(res.data.uloga === 'PRODAVAC') {
          this.$router.push('/prodavac_view');
        }
      })
      .catch((err) => {
        console.log(err);
        alert(err.response.data);
      })
  }
  }
};

</script>

<template>
  <div class="main">
    <h2>Azuriraj profil</h2>

    <form class="profile-form"  @submit.prevent="updateUser">
      <label for="ime">Ime:</label>
      <input v-model="userData.ime" type="text" id="ime" name="ime" placeholder="Unesite ime">

      <label for="prezime">Prezime:</label>
      <input v-model="userData.prezime" type="text" id="prezime" name="prezime" placeholder="Unesite prezime">

      <label for="username">Korisničko ime:</label>
      <input v-model="userData.username" type="text" id="username" name="username" placeholder="Unesite korisnicko ime">

      <label for="password">Lozinka:</label>
      <input v-model="userData.password" type="password" id="password" name="password" placeholder="Unesite lozinku">

      <label for="mail">Email:</label>
      <input v-model="userData.mail" type="email" id="mail" name="mail" placeholder="Unesite email">

      <label for="brojTelefona">Broj telefona:</label>
      <input v-model="userData.brojTelefona" type="text" id="brojTelefona" name="brojTelefona" placeholder="Unesite broj telefona">

      <label for="datumRodjenja">Datum rodjenja:</label>
      <input v-model="userData.datumRodjenja" type="date" id="datumRodjenja" name="datumRodjenja">

      <label for="profilnaURL">Profilna slika URL:</label>
      <input v-model="userData.profilnaURL" type="text" id="profilnaURL" name="profilnaURL" placeholder="Dodajte novu sliku">

      <label for="opis">Opis:</label>
      <textarea v-model="userData.opis" type="text" id="opis" name="opis" placeholder="Unesite opis"></textarea>

      <label for="oldPassowrd">Stara lozinka (za potvrdu promena):</label>
      <input v-model="userData.oldPassword" type="password" id="oldPassword" name="oldPassword" placeholder="Potvrdite vasu lozinku">

      <button type="submit">Ažuriraj profil</button>
    </form>
  </div>
</template>

<style scoped>

.main {
  max-width: 500px;
  margin: 0 auto;
  padding: 20px;
}

.profile-form {
  display: grid;
  gap: 10px;
}

</style>
