<template>
  <div class="w-75 ma-auto mt-16 d-flex flex-column ga-4">
    <span class="text-h5">Users</span>
    <v-card
      v-for="(user, i) in userList"
      :key="i"
      class="pa-4"
    >
      <div class="d-flex justify-space-between">
        <div class="d-flex align-center">
          <v-avatar
            color="surface-variant"
            size="x-large"
          >
            {{ user.name.slice(0, 2).toUpperCase() }}
          </v-avatar>
          <div>
            <v-card-title>{{ user.name }}</v-card-title>
            <v-card-subtitle>{{ user.email }}</v-card-subtitle>
          </div>
        </div>
        <v-card-actions>
          <v-btn
            variant="outlined"
            :onclick="() => router.push('/users/1')"
          >
            Profile
          </v-btn>
        </v-card-actions>
      </div>
    </v-card>
  </div>
</template>
  

<script setup lang="ts">
import { useRouter } from 'vue-router';

type User = {
    name: string,
    email: string,
}

const userList: User[] = [
    {
        name: 'Mario',
        email: 'mario.mario@nintendo.com'
    },
    {
        name: 'Luigi',
        email: 'luigi.mario@nintendo.com'
    }
]

const router = useRouter()

</script>
