<template>
  <div class="w-75 ma-auto mt-16 d-flex flex-column ga-4">
    <div class="d-flex ga-4 align-center">
      <v-avatar
        color="surface-variant"
        size="128"
      >
        {{ user.name }}
      </v-avatar>
      <div class="d-flex flex-column ga-2">
        <div class="text-h3">
          {{ user.name }}
        </div>
        <div class="text-subtitle-1 text-medium-emphasis">
          {{ user.email }}
        </div>
      </div>
    </div>
    <v-divider />
    <div class="d-flex flex-column ga-4">
      <v-card
        v-for="(article, i) in articleList" 
        :key="i"
        class="pa-2"
        rounded
      >
        <v-card-title>{{ article.title }}</v-card-title>
        <v-card-subtitle>{{ article.date }}</v-card-subtitle>
        <v-card-text>{{ article.content }}</v-card-text>
      </v-card>
    </div>
  </div>
</template>

<script setup lang="ts">

const user = {
    name: 'Mario',
    email: 'mario.mario@nintendo.com'
}
const articleList = [
{
    title: 'Foo',
    author: 'Mario',
    content: 'Lorem Ipsum',
    date: '18/11/2024'
  },
  {
    title: 'Bar',
    author: 'Luigi',
    content: 'Zombie Ipsum',
    date: '18/11/2024'
  }
]
</script>
