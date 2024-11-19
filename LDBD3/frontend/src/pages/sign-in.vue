<template>
  <div class="d-flex h-100 justify-center align-center">
    <v-card class="pa-12">
      <div class="d-flex ga-12">
        <div class="">
          <v-card-title>Sign-in</v-card-title>
          <v-card-subtitle>Create a new Backless account!</v-card-subtitle>
        </div>
        <div class="d-flex flex-column ga-2">
          <v-text-field label="Email" />
          <v-text-field label="Name" />
          <v-text-field
            width="20rem"
            label="Password"
            type="password"
          />
          <v-text-field
            label="Confirm Password"
            type="password"
          />
          <div class="d-flex ga-6 justify-end">
            <v-btn
              color="primary"
              variant="flat"
            >
              Create Account
            </v-btn>
          </div>
        </div>
      </div>
    </v-card>
  </div>
</template>