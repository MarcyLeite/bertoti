<template>
  <div class="ma-auto mt-16 d-flex flex-column ga-4">
    <span class="text-h5">Articles</span>
    <v-card
      v-for="(article, i) in articleList" 
      :key="i"
      class="pa-2"
      rounded
    >
      <v-card-title>{{ article.title }}</v-card-title>
      <v-card-subtitle>{{ article.date }}</v-card-subtitle>
      <v-card-text>{{ article.content }}</v-card-text>
        
      <v-card-actions>
        <v-btn>Author: {{ article.author }}</v-btn>
      </v-card-actions>
    </v-card>
  </div>
</template>

<script setup lang="ts">
type Article = {
    title: string
    date: string
    content: string
    author: string
}

const articleList: Article[] = [
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
