<template>
  <div id="register" class="text-center">
    <form class="form-register" @submit.prevent="register">
      <h1 class="header">create account</h1>
      <div class="alert alert-danger" role="alert" v-if="registrationErrors">
        {{ registrationErrorMsg }}
      </div>
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
      <input
        type="password"
        id="confirmPassword"
        class="form-control"
        placeholder="confirm password"
        v-model="user.confirmPassword"
        required
      />
      <router-link :to="{ name: 'login' }" id="have-an-account">I have an account!</router-link>
      <b-button type="submit">create</b-button>
    </form>
  </div>
</template>

<script>
import authService from '../services/AuthService';

export default {
  name: 'register',
  data() {
    return {
      user: {
        username: '',
        password: '',
        confirmPassword: '',
        role: 'user',
      },
      registrationErrors: false,
      registrationErrorMsg: 'There were problems registering this user.',
    };
  },
  methods: {
    register() {
      if (this.user.password != this.user.confirmPassword) {
        this.registrationErrors = true;
        this.registrationErrorMsg = 'Password & Confirm Password do not match.';
      } else {
        authService
          .register(this.user)
          .then((response) => {
            if (response.status == 201) {
              this.$router.push({
                path: '/login',
                query: { registration: 'success' },
              });
            }
          })
          .catch((error) => {
            const response = error.response;
            this.registrationErrors = true;
            if (response.status === 400) {
              this.registrationErrorMsg = 'Bad Request: Validation Errors';
            }
          });
      }
    },
    clearErrors() {
      this.registrationErrors = false;
      this.registrationErrorMsg = 'There were problems registering this user.';
    },
  },
};
</script>

<style scoped>
h1 {
  margin-top: 6%;
  margin-bottom: 4%;
}

#confirmPassword {
  margin: 3px;
  max-width: 20%;
  text-align: center;
}

#password {
  margin: 3px;
  max-width: 20%;
  text-align: center;
}

#username {
  text-align: center;
  margin: 3px;
  max-width: 20%;
}

.form-register {
  display: flex;
  flex-direction: column;
  align-items: center;
}

#have-an-account {
  text-decoration: none;
  color: black;
  margin-top: 20px;
  margin-bottom: 20px;
  font-size: 17px;
}

#have-an-account:hover {
  font-weight: bold;
  cursor: pointer;
}

</style>
