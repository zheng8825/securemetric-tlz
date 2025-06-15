import { createApp } from 'vue';
import App from './App.vue';
import router from './router'; // ✅ Must import router
import './style.css';

const app = createApp(App);
app.use(router); // ✅ Inject router
app.mount('#app');
