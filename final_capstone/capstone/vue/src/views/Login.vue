<template>
  <div id="login" class="text-center">
    <form class="form-signin" @submit.prevent="login">
      <h1 class="header">tenmo</h1>
      <div
        class="alert alert-danger"
        role="alert"
        v-if="invalidCredentials"
      >Invalid username and password!</div>
      <div
        class="alert alert-success"
        role="alert"
        v-if="this.$route.query.registration"
      >Thank you for registering, please sign in.</div>
      <input
        type="text"
        id="username"
        class="form-control"
        placeholder="username"
        v-model="user.username"
        required
        autofocus
      />
      <input
        type="password"
        id="password"
        class="form-control"
        placeholder="password"
        v-model="user.password"
        required
      />
      <router-link :to="{ name: 'register' }" id="register-new-account"><br>I need an account!</router-link>
      <b-button id="btn" type="submit">sign in</b-button>
    </form>
  </div>
</template>

<script>
import authService from "../services/AuthService";

export default {
  name: "login",
  components: {},
  data() {
    return {
      user: {
        username: "",
        password: ""
      },
      invalidCredentials: false
    };
  },
  methods: {
    login() {
      authService
        .login(this.user)
        .then(response => {
          if (response.status == 200) {
            this.$store.commit("SET_AUTH_TOKEN", response.data.token);
            this.$store.commit("SET_USER", response.data.user);
            this.$router.push("/");
          }
        })
        .catch(error => {
          const response = error.response;

          console.log('***')
          console.log(response)
          if (response.status === 401) {
            this.invalidCredentials = true;
          }
        });
    }
  }
};
</script>


<style scoped>
#btn {
  margin-top: 22px;
}

#username {
  max-width: 20%;
  margin: 3px;
  text-align: center;
}

#password {
  max-width: 20%;
  margin: 3px;
  text-align: center;
}

.form-signin {
  display: flex;
  flex-direction: column;
  align-items: center;
}

#register-new-account {
  text-decoration: none;
  color : black;
}

#register-new-account:hover {
  font-weight: bold;
  cursor: pointer;
}

h1 {
  margin-top: 6%;
  margin-bottom: 4%;
}

</style>