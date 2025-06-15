import { createRouter, createWebHistory } from 'vue-router';
import Login from './views/Login.vue';
import ProductList from './views/ProductList.vue';

const routes = [
  { path: '/', redirect: '/login' }, // ✅ Add this line
  { path: '/login', component: Login },
  { path: '/products', component: ProductList, meta: { requiresAuth: true } },
  { path: '/:pathMatch(.*)*', redirect: '/login' }
];


const router = createRouter({
  history: createWebHistory(),
  routes
});

// ✅ Auth guard
router.beforeEach((to, from, next) => {
  const token = localStorage.getItem('token');
  if (to.meta.requiresAuth && !token) {
    return next('/login');
  }
  next();
});

export default router;
