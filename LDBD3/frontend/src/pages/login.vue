<template>
  <div class="d-flex h-100 justify-center align-center">
    <v-card class="pa-12">
      <div class="d-flex ga-12">
        <div>
          <v-card-title>Login</v-card-title>
          <v-card-subtitle>to continue to Backless</v-card-subtitle>
        </div>
        <div class="d-flex flex-column ga-2">
          <v-text-field label="Email" />
          <v-text-field
            label="Password"
            type="password"
          />
          <div class="d-flex ga-6 justify-end">
            <v-btn
              width="12rem"
              color="primary"
              variant="text"
              :onclick="() => router.push('/sign-in')"
            >
              Create account
            </v-btn>
            <v-btn
              color="primary"
              variant="flat"
            >
              Login
            </v-btn>
          </div>
        </div>
      </div>
    </v-card>
  </div>
</template>

<script setup lang="ts">
import { useRouter } from 'vue-router';

const router = useRouter()
</script>