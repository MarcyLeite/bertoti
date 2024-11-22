<template>
  <v-app>
    <PageHeader />
    <div class="d-flex justify-center h-100">
      <v-main max-width="1280">
        <router-view />
      </v-main>
    </div>
  </v-app>
</template>

<script lang="ts" setup>
import axios from 'axios';
import { isLogged, accessToken } from './store';
import { watch } from 'vue';
import { useRoute } from 'vue-router';


const route = useRoute()

const verifySession = async () => {
  accessToken.value = localStorage.getItem('access-token') ?? ''
  if(accessToken.value === '') {
    isLogged.value = false
    return
  }
  
  const verification = (await axios.get(`http://localhost:8080/v1/api/user/verify/${accessToken.value}`)).data
  isLogged.value = verification
}

watch(() => route.path, () => {
  verifySession()
})

</script>
