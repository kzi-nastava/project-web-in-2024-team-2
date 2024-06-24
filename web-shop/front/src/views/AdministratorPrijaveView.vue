<script>
import axios from "axios";

export default {
  name: "AdministratorView",
  data() {
    return {
      korisnik: {},
      prijave: [],
      prijavaId: '',
      razlogOdbijanja: ''
    };
  },
  mounted() {
    this.getLoggedUser();
    this.getPrijave();
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
    getPrijave() {
      axios.get('http://localhost:8081/prijave-profila', {withCredentials: true})
      .then((response) => {
        this.prijave = response.data;
      })
      .catch((error) => {
        console.log(error);
      });
    },
    obradi(id) {
      this.prijavaId = id;
    },
    setPrihvacena(id) {
      axios.post(`http://localhost:8081/prihvati-prijavu/${id}`, {withCredentials: true})
      .then((response) => {
        console.log(response.data);
      })
      .catch((error) => {
        console.log(error);
      });
    },
    setOdbijena(id) {
      axios.post(`http://localhost:8081/odbij-prijavu/${id}`, this.razlogOdbijanja, {withCredentials: true})
      .then((response) => {
        console.log(this.razlogOdbijanja);
        console.log(response.data);
      })
      .catch((error) => {
        console.log(this.razlogOdbijanja);
        console.log(error);
      });
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
            <a class="nav-link active" aria-current="page" href="/">Prijave Profila</a>
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

  <h3>Prijave Profila</h3>

  <section v-if="prijave.length !== 0" @load="getPrijave" v-for="prijava in prijave" class="p-4 p-md-5 text-center text-lg-start shadow-1-strong rounded">
    <div class="row d-flex justify-content-center">
      <div id="prijave" class="col-md-10">
        <div class="card">
          <div class="card-body m-3">
            <div class="row">
              <div class="col-lg-4 d-flex justify-content-center align-items-center mb-4 mb-lg-0">
                <img :src="prijava.podnosilac.profilnaURL" alt="user icon" id="recenzijaImg" class="rounded-circle img-fluid shadow-1" width="200" height="200" />
              </div>
              <div class="col-lg-8">
                <p class="fw-bold lead mb-2"><strong>{{prijava.podnosilac.ime}} {{prijava.podnosilac.prezime}} ({{prijava.podnosilac.uloga}})</strong></p>
                <p class="text-muted fw-light mb-4">{{prijava.razlogPrijave}}</p>
                <div v-if="prijavaId === prijava.id" class="col-md-6">
                  <button v-on:click="setPrihvacena(prijava.id)" href="/administrator_view/edit/{{prijava.id}}" id="editBtn" class="btn btn-primary me-md-2" type="button">Prihvati</button>
                  <form @submit.prevent="setOdbijena(prijava.id)" class="display-5" action="">
                    <input id="cancelBtn" type="submit" class="btn btn-primary me-md-2" value="Odbij">
                    <input class="form-control me-2" type="search" placeholder="razlog" aria-label="Search" v-model="razlogOdbijanja">
                  </form>
                </div>
                <p v-else><b>Status prijave: </b>{{prijava.statusPrijave}}</p>
                <p><b>Odnosi se na:</b> {{prijava.odnosiSe.ime}} {{prijava.odnosiSe.prezime}}</p>
                <p><b>Datum:</b> {{prijava.datumPodnosenjaPrijave}}</p>
              </div>
              <div v-if="prijavaId !== prijava.id" id="buttons" class="col-lg-4 d-flex justify-content-end align-items-end mb-4 mb-lg-0">
                <div class="d-grid gap-2 d-md-flex justify-content-md-end">
                  <form>
                    <button v-on:click="obradi(prijava.id)" id="editBtn" class="btn btn-primary me-md-2" type="button">Obradi</button>
                  </form>
                </div>
              </div>
              <div v-else id="buttons" class="col-lg-4 d-flex justify-content-end align-items-end mb-4 mb-lg-0">
                <div class="d-grid gap-2 d-md-flex justify-content-md-end">
                  <form>
                    <input v-on:click="obradi(prijava.id)" id="cancelBtn" type="submit" class="btn btn-primary me-md-2" value="Cancel">
                  </form>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </section>

  <div v-else class="align-content-lg-center" style="margin-top: 5%;font-size: 150%;opacity: 40%;user-select: none;">Nema prijava profila</div>
</template>

<style scoped>

.me-2 {
  width: 200px;
}

h3 {
  margin-top: 1%;
}

.col-md-6 {
  display: flex;
}

.col-lg-8 {
  width: fit-content;
}

#cancelBtn {
  background-color: #e80000;
  border-color: #e80000;
}

#user {
  cursor: pointer;
}

#icon {
  width: 40px;
  height: 40px;
}
</style>