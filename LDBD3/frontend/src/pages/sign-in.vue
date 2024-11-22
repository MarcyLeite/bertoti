<template>
  <div class="d-flex h-100 justify-center align-center">
    <v-card class="pa-12">
      <div class="d-flex ga-12">
        <div class="">
          <v-card-title>Sign-in</v-card-title>
          <v-card-subtitle>Create a new Backless account!</v-card-subtitle>
        </div>
        <div class="d-flex flex-column ga-2">
          <v-text-field
            v-model="email"
            label="Email"
          />
          <v-text-field
            v-model="name"
            label="Name"
          />
          <v-text-field
            v-model="password"
            width="20rem"
            label="Password"
            type="password"
          />
          <div class="d-flex ga-6 justify-end">
            <v-btn
              color="primary"
              variant="flat"
              @click="createAccount"
            >
              Create Account
            </v-btn>
          </div>
        </div>
      </div>
    </v-card>
  </div>
</template>

<script setup lang="ts">
import axios, { AxiosError } from 'axios';
import { ref } from 'vue';
import { useRouter } from 'vue-router';

type SignupError = {
  name: string,
  password: string,
  email: string
}

const router = useRouter()

const email = ref('')
const name = ref('')
const password = ref('')

const createAccount = async () => {
  try {
    const response = await axios.post('http://localhost:8080/v1/api/user/signup', {
      email: email.value,
      name: name.value,
      password: password.value
    })
    if(!response) return
    
    router.push('/login')

  } catch (e) {
    const error = e as AxiosError<SignupError>
    if(!error.response) return
  }
}
</script>