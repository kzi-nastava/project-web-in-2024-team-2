<script>
import axios from "axios";
export default {
  name: "LoginView",
  data: function () {
    return {
      korisnik: {},
    };
  },
  methods: {
    login() {
      axios.post("http://localhost:8081/login-user", this.korisnik, {withCredentials: true,})
          .then((res) => {
            console.log(res);
            localStorage.setItem("korisnik", JSON.stringify(res.data));
            if (res.data.uloga === 'KUPAC') {
              this.$router.push("/kupac_view");
            }
            else if (res.data.uloga === 'PRODAVAC') {
              this.$router.push("/prodavac_view");
            }
            else {
              this.$router.push("/administrator_view");
            }
          })
          .catch((err) => {
            console.log(err);
            alert(err.response.data);
          });
    },
  },
};

</script>

<template>
  <div class="main">
    <h3>Enter your login credentials</h3>
    <form action="" @submit.prevent="login()">
      <label for="first">Username:</label>
      <input v-model="korisnik.username" type="text" id="first" name="first" placeholder="Enter your Username" required>

      <label for="password">Password:</label>
      <input v-model="korisnik.password" type="password" id="password" name="password" placeholder="Enter your Password" required>

      <div class="wrap">
        <button type="submit" href="/">Login</button>
      </div>
    </form>
    <p>Not registered?
      <a href="/register" style="text-decoration: none;">Create an account</a>
    </p>
  </div>
</template>

<style scoped>
body {
  display: flex;
  align-items: center;
  justify-content: center;
  font-family: sans-serif;
  line-height: 1.5;
  min-height: 100vh;
  background: #f3f3f3;
  flex-direction: column;
  margin: 0;
}

.main {
  margin: 10% auto auto;
  background-color: #fff;
  border-radius: 15px;
  box-shadow: 0 0 20px rgba(0, 0, 0, 0.2);
  padding: 10px 20px;
  transition: transform 0.2s;
  width: 500px;
  text-align: center;
}

label {
  display: block;
  width: 100%;
  margin-top: 10px;
  margin-bottom: 5px;
  text-align: left;
  color: #555;
  font-weight: bold;
}


input {
  display: block;
  width: 100%;
  margin-bottom: 15px;
  padding: 10px;
  box-sizing: border-box;
  border: 1px solid #ddd;
  border-radius: 5px;
}

button {
  padding: 15px;
  border-radius: 10px;
  margin-top: 15px;
  margin-bottom: 15px;
  border: none;
  color: white;
  cursor: pointer;
  background-color: #198754;
  width: 100%;
  font-size: 16px;
}

.wrap {
  display: flex;
  justify-content: center;
  align-items: center;
}

</style>