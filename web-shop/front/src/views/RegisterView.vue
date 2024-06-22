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
            <a class="nav-link active" aria-current="page" href="/">Home</a>
          </li>
        </ul>
        <div class="d-grid gap-2 d-md-flex justify-content-md-end">
          <button id="loginBtn" class="btn btn-primary me-md-2" style="margin-left: 40px" type="button" v-on:click="login()">Login</button>
          <button id="registerBtn" class="btn btn-primary" type="button">Register</button>
        </div>
      </div>
    </div>
  </nav>

  <form class="row g-3" @submit.prevent="register">
    <h1><b>Register</b></h1>
    <div class="col-md-6">
      <label for="inputIme" class="form-label"><b>Ime</b></label>
      <input type="text" class="form-control" id="inputIme" v-model="korisnik.ime">
    </div>
    <div class="col-md-6">
      <label for="inputPrezime" class="form-label"><b>Prezime</b></label>
      <input type="text" class="form-control" id="inputPrezime" v-model="korisnik.prezime">
    </div>
    <div class="col-md-6">
      <label for="inputEmail4" class="form-label"><b>Еmail</b></label>
      <input type="email" class="form-control" id="inputEmail4" v-model="korisnik.mail">
    </div>
    <div class="col-md-6">
      <label for="inputUsername" class="form-label"><b>Korisničko ime</b></label>
      <input type="text" class="form-control" id="inputUsername" v-model="korisnik.username">
    </div>
    <div class="col-12">
      <label for="inputPhone" class="form-label"><b>Telefon</b></label>
      <input type="text" class="form-control" id="inputPhone" placeholder="+381 64..." v-model="korisnik.brojTelefona">
    </div>
    <div class="col-md-6">
      <label for="inputPassword" class="form-label"><b>Lozinka</b></label>
      <input type="password" class="form-control" id="inputPassword" v-model="korisnik.password">
    </div>
    <div class="col-md-6">
      <label for="inputRole" class="form-label"><b>Uloga</b></label>
      <select id="inputRole" class="form-select" v-model="korisnik.uloga">
        <option selected>Izaberi...</option>
        <option>KUPAC</option>
        <option>PRODAVAC</option>

      </select>
    </div>
    <div class="col-md-6">
      <label for="inputPassword2" class="form-label"><b>Ponovljena lozinka</b></label>
      <input type="password" class="form-control" id="inputPassword2" v-model="korisnik.checkPassword">
    </div>

    <div class="col-md-6">
      <button class="btn btn-primary" id="btn-register">Registruj se</button>
    </div>
  </form>
</template>

<script>
import axios from "axios";
export default {
  name: "RegisterView",
  data: function () {
    return {
      korisnik: {},
    };
  },
  methods: {
    register() {
      axios.post("http://localhost:8081/register-user", this.korisnik, {withCredentials: true,})
          .then((res) => {
            console.log(res.data);
            alert("" + res.data);
            this.$router.push("/");
          })
          .catch((error) => {
            alert(error.response.data);
          });
    },
    login() {
      this.$router.push("/login");
    }
  },
};
</script>

<style scoped>
.g-3 {
  width: 50%;
  margin: 1% auto auto;
  border-radius: 15px;
  padding: 1%;
  box-shadow: 0 0 20px rgba(0, 0, 0, 0.2);
}

#btn-register {
  margin-top: 30px;
  width: 200px;
}

#loginBtn {
  background-color: #198754;
  border-color: #198754;
}

#loginBtn:hover {
  background-color: #12613e;
}

#registerBtn {
  background-color: #198754;
  border-color: #198754;
}

#registerBtn:hover {
  background-color: #12613e;
}
.btn-primary {
  background-color: #198754;
  border-color: #198754;
}

.btn-primary:hover {
  background-color: #12613e;
}
</style>