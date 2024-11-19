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
          :onclick="() => router.push('/users')"
        >
          Users
        </v-btn>
        <v-divider vertical />
        <v-btn
          variant="flat"
          color="primary"
          :onclick="() => router.push('/login')"
        >
          login
        </v-btn>
      </div>
    </template>
  </v-app-bar>
</template>

<script setup lang="ts">
import { useRoute, useRouter } from 'vue-router';

const route = useRoute()
const router = useRouter()
</script>