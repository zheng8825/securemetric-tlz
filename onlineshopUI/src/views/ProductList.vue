<template>
  <div>
    <!-- Logout Button -->
    <div style="text-align: right; margin-bottom: 10px;">
      <button @click="logout">Logout</button>
    </div>

    <h2>Products</h2>

    <!-- Add Product Section -->
    <div v-if="isAdmin">
      <input v-model="newProduct.name" placeholder="Name" />
      <input v-model="newProduct.price" type="number" placeholder="Price" />
      <button @click="addProduct">Add Product</button>
    </div>

    <!-- Product List -->
    <ul>
      <li v-for="p in products" :key="p.id">
        {{ p.name }} - {{ p.price }}
        <button v-if="isAdmin" @click="startEdit(p)">Edit</button>
        <button v-if="isAdmin" @click="deleteProduct(p.id)">Delete</button>
      </li>
    </ul>

    <!-- Edit Form -->
    <div v-if="editProductData.id">
      <h3>Edit Product</h3>
      <input v-model="editProductData.name" placeholder="Name" />
      <input v-model="editProductData.price" type="number" placeholder="Price" />
      <button @click="confirmEditProduct">Save</button>
      <button @click="cancelEdit">Cancel</button>
    </div>
  </div>
</template>


<script>
import api from '../api';
import { jwtDecode } from 'jwt-decode';

export default {
  data() {
    return {
      products: [],
      role: '',
      newProduct: { name: '', price: '' },
      editProductData: { id: '', name: '', price: '' }
    };
  },
  computed: {
    isAdmin() {
      return this.role === 'ROLE_ADMIN';
    }
  },
  methods: {
    async fetch() {
      const res = await api.get('/products/all');
      this.products = res.data.data;
    },
    async addProduct() {
      if (!this.newProduct || !this.newProduct.name || !this.newProduct.price) {
        alert('Product name and price must not be empty.');
        return;
      }
      try {
        await api.post('/products/add', {
          name: this.newProduct.name,
          price: this.newProduct.price
        });
        this.newProduct = { name: '', price: '' };
        this.fetch();
      } catch (err) {
        alert('Failed to add product: ' + (err.response?.data?.message || err.message));
      }
    },
    startEdit(p) {
      this.editProductData = { ...p };
    },
    async confirmEditProduct() {
      if (!this.editProductData || !this.editProductData.name || !this.editProductData.price) {
        alert('Product name and price must not be empty.');
        return;
      }
      try {
        await api.put(`/products/update/${this.editProductData.id}`, {
          name: this.editProductData.name,
          price: this.editProductData.price
        });
        this.editProductData = { id: '', name: '', price: '' };
        this.fetch();
      } catch (err) {
        alert('Failed to update product: ' + (err.response?.data?.message || err.message));
      }
    },
    cancelEdit() {
      this.editProductData = { id: '', name: '', price: '' };
    },
    async deleteProduct(id) {
      if (!id) {
        alert('Invalid product ID.');
        return;
      }
      await api.delete(`/products/delete/${id}`);
      this.fetch();
    },
    logout() {
      localStorage.removeItem('token'); // clear token
      this.$router.push('/login');      // redirect to login page
    }

  },
  async mounted() {
    const token = localStorage.getItem('token');
    if (token) {
      const payload = jwtDecode(token);
      this.role = payload.role; // or payload.roles[0]
    }
    this.fetch();
  }
};
</script>
