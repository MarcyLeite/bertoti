<template>
  <v-layout
    class="ma-auto my-16 d-flex flex-column ga-6"
    max-width="980"
  >
    <div class="w-100 d-flex justify-space-between">
      <div
        v-if="user"
        :key="user.email"
        class="d-flex ga-4 align-center"
      >
        <v-avatar
          color="surface-variant"
          size="128"
        >
          {{ user.name }}
        </v-avatar>
        <div class="d-flex flex-column ga-2">
          <template v-if="!isEditing">
            <div
              class="text-h3"
            >
              {{ user.name }}
            </div>
            <div class="text-subtitle-1 text-medium-emphasis">
              {{ user.email }}
            </div>
          </template>
          <template v-else>
            <v-text-field
              v-model:model-value="editedUser.name"
              variant="underlined"
              width="26rem"
              label="Username"
            />
            <v-text-field
              v-model:model-value="editedUser.email"
              variant="underlined"
              width="26rem"
              label="Email"
            />
          </template>
        </div>
      </div>
      <div>
        <v-btn
          v-if="isMe && !isEditing"
          icon="mdi-pencil"
          :onclick="() => {
            if(!user) return
            editedUser = { ...user }
            isEditing = !isEditing
          }"
        />
        <div
          v-else-if="isMe"
          class="d-flex flex-column justify-space-between align-end h-100"
        >
          <div class="d-flex ga-4">
            <v-btn
              color="success"
              icon="mdi-check"
              :onclick="async () => {
                await editUser()
                isEditing = !isEditing
              }
              "
            />
            <v-btn
              icon="mdi-cancel"
              :onclick="() => 
                isEditing = !isEditing"
            />
            <v-btn 
              color="error"
              icon="mdi-trash-can"
              variant="text"
              @click="() => deleteUser()"
            />
          </div>
        </div>
      </div>
    </div>
    <v-divider />
    <div class="d-flex justify-end">
      <v-btn
        v-if="isMe"
        color="secondary"
        @click="() => router.push('/articles/edit')"
      >
        Create Article
      </v-btn>
    </div>
    <div class="d-flex flex-column ga-4">
      <ArticlesList
        :fetch-url="`http://localhost:8080/v1/api/user/${id}/articles/`"
        :author-mode="isMe"
        hide-author
      />
    </div>
  </v-layout>
</template>

<script setup lang="ts">
import axios from 'axios';
import { onMounted, ref } from 'vue';
import { accessToken } from '@/store';
import { type User } from './index.vue';
import ArticlesList from '@/components/ArticlesList.vue';
import { useRouter } from 'vue-router';

const { id } = defineProps<{id: string}>()
const user = ref<User | null>(null)
const isEditing = ref(false)
const isMe = ref(false)

const editedUser = ref<User>({
  id: 0,
  email: '',
  name: ''
})

const router = useRouter()

const fetchUser = async () => {
  const fetchedUser = (await axios.get(`http://localhost:8080/v1/api/user/${id}`)).data
  user.value = fetchedUser
  await checkUserMe()
}

const checkUserMe = async () => {
  if (accessToken.value === '') return
  isMe.value = (await axios.get(`http://localhost:8080/v1/api/user/verify-me/${accessToken.value}/${id}`)).data
}

const editUser = async () => {
  const newUser = (await axios.post(`http://localhost:8080/v1/api/user/edit`, {
    token: accessToken.value,
    user: editedUser.value
  })).data

  user.value = newUser
}

const deleteUser = async () => {
  await axios.post('http://localhost:8080/v1/api/user/delete', {
    token: accessToken.value
  })

  router.push('/')
}

onMounted(() => {
  fetchUser()
})
</script>
