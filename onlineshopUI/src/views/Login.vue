<template>
  <div>
    <h2>Login</h2>
    <form @submit.prevent="login">
      <input v-model="username" placeholder="Username" required />
      <input v-model="password" type="password" placeholder="Password" required />
      <button type="submit">Login</button>
    </form>
    <p v-if="error">{{ error }}</p>
  </div>
</template>

<script>
import api from '../api';

export default {
  data() {
    return { username: '', password: '', error: '' };
  },
  methods: {
    async login() {
      try {
        const res = await api.post('/api/auth/login', {
          username: this.username,
          password: this.password
        });
        localStorage.setItem('token', res.data.token);
        this.$router.push('/products');
      } catch (err) {
        this.error = err.response?.data?.message || err.message;
      }
    }
  },
  mounted() {
    // Clear token on entering login page
    localStorage.removeItem('token');
  }
};
</script>
