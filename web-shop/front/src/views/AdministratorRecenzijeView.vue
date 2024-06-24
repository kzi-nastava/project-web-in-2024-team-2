<script>
  import axios from "axios";

  export default {
    name: "AdministratorView",
    data() {
      return {
        korisnik: {},
        recenzije: [],
        review: {}
      };
    },
    mounted() {
      this.getLoggedUser();
      this.getRecenzije();
    },
    methods: {
      getLoggedUser() {
        this.korisnik = JSON.parse(localStorage.getItem('korisnik'));
        console.log(this.korisnik);
      },
      logout() {
        localStorage.removeItem('korisnik');
        this.$router.push('/');
      },
      getRecenzije() {
        axios.get('http://localhost:8081/recenzije', {withCredentials: true})
        .then((response) => {
          this.recenzije = response.data;
          console.log(this.recenzije.length);
        })
        .catch((error) => {
          console.log(error);
        });
      },
      deleteRecenzija(id) {
        axios.delete(`http://localhost:8081/obrisi-recenziju/${id}`, {withCredentials: true})
        .then((response) => {
          console.log(response.data);
          alert(response.data);
        })
        .catch((error) => {
          console.log(error);
        });
      },
      goEdit(id) {
        this.$router.push('/administrator_prijave/edit/' + id);
      }
    }
  };
</script>

<template>
  <nav class="navbar navbar-expand-lg bg-body-tertiary">
    <div class="container-fluid">
      <a class="navbar-brand" href="/">WebShop</a>
      <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav me-auto mb-2 mb-lg-0">
          <li class="nav-item">
            <a class="nav-link active" aria-current="page" href="/administrator_view">Recenzije</a>
          </li>
          <li class="nav-item">
            <a class="nav-link active" aria-current="page" href="/administrator_prijave">Prijave Profila</a>
          </li>
        </ul>
        <div @load="getLoggedUser" class="d-grid gap-2 d-md-flex justify-content-md-end">
          <div v-if="korisnik != null" id="user" class="d-grid gap-2 d-md-flex justify-content-md-end">
            <img id="icon" :src="korisnik.profilnaURL" alt="user icon">
            <p><b>{{korisnik.username}}</b></p>
          </div>
          <a v-on:click="logout()" href="#">Logout</a>
        </div>
      </div>
    </div>
  </nav>

  <h3>Recenzije</h3>

  <section v-if="recenzije.length !== 0" @load="getRecenzije" v-for="recenzija in recenzije" :key="recenzija.id" class="p-4 p-md-5 text-center text-lg-start shadow-1-strong rounded">
    <div class="row d-flex justify-content-center">
      <div id="reviews" class="col-md-10">
        <div class="card">
          <div class="card-body m-3">
            <div class="row">
              <div class="col-lg-4 d-flex justify-content-center align-items-center mb-4 mb-lg-0">
                <img :src="recenzija.podnosilac.profilnaURL" alt="user icon" id="recenzijaImg" class="rounded-circle img-fluid shadow-1" width="200" height="200" />
              </div>
              <div class="col-lg-8">
                <p class="fw-bold lead mb-2"><strong>{{recenzija.podnosilac.ime}} {{recenzija.podnosilac.prezime}} ({{recenzija.podnosilac.uloga}})</strong></p>
                <p class="text-muted fw-light mb-4">{{recenzija.komentar}}</p>
                <p><b>Ocena:</b> {{recenzija.ocena}}</p>
                <p><b>Datum:</b> {{recenzija.datumRecenzije}}</p>
              </div>
              <div id="buttons" class="col-lg-4 d-flex justify-content-end align-items-end mb-4 mb-lg-0">
                <div class="d-grid gap-2 d-md-flex justify-content-md-end">
                  <form>
                    <button v-on:click="goEdit(recenzija.id)" href="/administrator_view/edit/{{recenzija.id}}" id="editBtn" class="btn btn-primary me-md-2" type="button">Edit</button>
                    <input v-on:click="deleteRecenzija(recenzija.id)" id="deleteBtn" type="submit" class="btn btn-primary me-md-2" value="Delete">
                  </form>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </section>
  <div v-else class="align-content-lg-center" style="margin-top: 5%;font-size: 150%;opacity: 40%;user-select: none;">Nema recenzija</div>
</template>

<style scoped>

h3 {
  margin-top: 1%;
}

#user {
  cursor: pointer;
}

#icon {
  width: 40px;
  height: 40px;
}

.col-lg-8 {
  width: fit-content;
}

#deleteBtn {
  background-color: #e80000;
  border-color: #e80000;
}

</style>