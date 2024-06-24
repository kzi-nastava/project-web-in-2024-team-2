<script>
import axios from 'axios';
export default {
  data() {
    return {
      prijava: {
        razlogPrijave: "",
      },
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
    PrijaviProdavca() {
      const id = this.$route.params.id;
      axios.post(`http://localhost:8081/prijavi-kupca/${id}`, this.prijava, {withCredentials: true})
          .then((res) => {
            alert("Uspesno ste prijavili kupca!");
            this.$router.push('/profiles');
          })
          .catch((error) => {
            console.log(error.data);
            alert("Ne mozete da prijavite kupca!");
            this.$router.push('/profiles');
          });
    }
  }
};


</script>

<template>

  <div class="main">
    <h3>Prijavite kupca: <br> {{korisnik.ime}} {{korisnik.prezime}} </h3>

    <form class="profile-form" @submit.prevent="PrijaviProdavca">

      <label for="razlogPrijave">Podnesite razlog prijave:</label>
      <textarea v-model="prijava.razlogPrijave" type="text" id="razlogPrijave" name="razlogPrijave" placeholder="Unesite razlog prijave"> </textarea>

      <button type="submit">Prosledite prijavu</button>
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