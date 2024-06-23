<script>
import axios from 'axios';
export default {
  name: 'OceniProdavca',
  data() {
    return {
      recenzija: {},
      korisnik: {}
    };
  },
  mounted() {
    this.prikaziProfil();
  },
  methods: {
    prikaziProfil() {
      const id = this.$route.params.id;
      axios.get(`http://localhost:8081/profile/${id}`, {withCredentials: true})
          .then((response) => {
            this.korisnik = response.data;
          })
          .catch((error) => {
            console.log(error);
          });
    },
    OceniKupca() {
      const id = this.$route.params.id;
      axios.post(`http://localhost:8081/oceni-prodavca/${id}`, this.recenzija, {withCredentials: true})
          .then((res) => {
            alert("Uspesno ste podneli recenziju!");
            this.$router.push('/profiles');
          })
          .catch((error) => {
            alert("Ne mozete da podnesete recenziju");
            this.$router.push('/profiles');
          });
    }
  }
};
</script>

<template>

  <div class="main">
    <h3>Ocenite kupca: <br> {{korisnik.ime}} {{korisnik.prezime}} </h3>

    <form class="profile-form"  @submit.prevent="OceniKupca">
      <label for="ocena">Ocena:</label>
      <input v-model="recenzija.ocena" type="number" id="ocena" name="ocena" min="1" max="5">

      <label for="komentar">Recenzija:</label>
      <textarea v-model="recenzija.komentar" type="text" id="komentar" name="komentar" placeholder="Unesite recenziju"> </textarea>

      <button type="submit">Prosledite recenziju</button>
    </form>
  </div>

</template>

<style scoped>
.main {
  max-width: 400px;
  margin: 0 auto;
  padding: 20px;
  background-color: #f9f9f9;
  border-radius: 8px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}
h2 {
  text-align: center;
  color: #333;
}
.profile-form {
  display: flex;
  flex-direction: column;
  margin-top: 20px; /* Adjust this margin as needed */
}
.form-group {
  margin-bottom: 15px;
}
label {
  font-weight: bold;
  margin-bottom: 5px;
}
input[type="number"],
textarea,
button {
  padding: 10px;
  font-size: 16px;
  border: 1px solid #ccc;
  border-radius: 5px;
  width: 100%;
}
button {
  background-color: #4CAF50;
  color: white;
  border: none;
  cursor: pointer;
  transition: background-color 0.3s ease;
  margin-top: 10px; /* Adjust this margin as needed */
}
button:hover {
  background-color: #45a049;
}
</style>