<script setup>

</script>

<template>
  <nav class="navbar navbar-expand-lg bg-body-tertiary">
    <div class="container-fluid">
      <a class="navbar-brand" href="#">WebShop</a>
      <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav me-auto mb-2 mb-lg-0">
          <li class="nav-item">
            <a class="nav-link active" aria-current="page" href="#">Home</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="#">Link</a>
          </li>
        </ul>
        <form class="d-flex" role="search">
          <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search">
          <button class="btn btn-outline-success" type="submit">Search</button>
        </form>
        <div class="d-grid gap-2 d-md-flex justify-content-md-end">
          <button id="loginBtn" class="btn btn-primary me-md-2" style="margin-left: 40px" type="button">Login</button>
          <button id="registerBtn" class="btn btn-primary" type="button">Register</button>
        </div>
      </div>
    </div>
  </nav>



  <footer>
    <p style="user-select: none">&copy; {{new Date().getFullYear()}} - All rights reserved</p>
  </footer>
</template>

<style scoped>
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
</style>