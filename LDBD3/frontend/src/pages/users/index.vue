<template>
  <div
    class="ma-auto my-16 d-flex flex-column ga-4"
    max-width="980"
  >
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
            :onclick="() => router.push(`/users/${user.id}`)"
          >
            Profile
          </v-btn>
        </v-card-actions>
      </div>
    </v-card>
    <v-pagination
      :length="totalPages"
      @update:model-value="fetchUsers"
    />
  </div>
</template>
  

<script setup lang="ts">
import { useRouter } from 'vue-router';
import axios from 'axios';
import { ref, onMounted } from 'vue';

export type User = {
  id: number
  name: string
  email: string
}

const router = useRouter()

const userList = ref<User[]>([])
const totalPages = ref(0)

const fetchUsers = async (page: number) => {
  const pageInfo = (await axios.get(`http://localhost:8080/v1/api/user/list/${page - 1}`)).data
  userList.value = pageInfo.content
  totalPages.value = pageInfo.totalPages
}

onMounted(() => {
  fetchUsers(1)
})

</script>
