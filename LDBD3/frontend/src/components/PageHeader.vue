<template>
  <v-app-bar>
    <v-app-bar-title>Backless</v-app-bar-title>
    
    <template #append>
      <div class="d-flex ga-2 mr-2">
        <v-btn
          :active="route.path === '/articles'"
          :onclick="() => router.push('/articles')"
        >
          Articles
        </v-btn>
        
        <v-btn
          :active="route.path === '/users'"
          :onclick="() => router.push('/users')"
        >
          Users
        </v-btn>
        <v-divider
          vertical
          thickness="4"
        />
        <v-btn
          v-if="!isLogged"
          variant="flat"
          color="primary"
          :onclick="() => router.push('/login')"
        >
          login
        </v-btn><v-btn
          v-else
          variant="flat"
          color="primary"
          :onclick="logout"
        >
          Logout
        </v-btn>
      </div>
    </template>
  </v-app-bar>
</template>

<script setup lang="ts">
import { useRoute, useRouter } from 'vue-router';
import { isLogged } from '@/store';

const route = useRoute()
const router = useRouter()

const logout = () => {
  localStorage.setItem('access-token', '')
  router.push('/')
}
</script>