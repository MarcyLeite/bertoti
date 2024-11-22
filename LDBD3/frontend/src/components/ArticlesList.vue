<template>
  <div class="d-flex flex-column ga-4">
    <v-card
      v-for="(article, i) in articleList" 
      :key="i"
      class="pa-2 d-flex justify-space-between align-center"
      rounded
    >
      <div>
        <v-card-title>{{ article.title }}</v-card-title>
        <v-card-subtitle>{{ article.date }}</v-card-subtitle>
        <v-card-text>{{ article.content }}</v-card-text>
      
        <v-card-actions v-if="!hideAuthor">
          <v-btn @click="() => router.push(`/users/${article.user.id}`)">
            Author: {{ article.user.name }}
          </v-btn>
        </v-card-actions>
      </div>
      <div
        v-if="authorMode"
        class="d-flex ga-2"
      >
        <v-btn
          icon="mdi-pencil"
          variant="flat"
          @click="() => router.push(`/articles/edit/${article.id}`)"
        />
        <v-btn 
          color="error"
          icon="mdi-trash-can"
          variant="text"
          @click="() => deleteArticle(article.id)"
        />
      </div>
    </v-card>
    <v-pagination
      :length="totalPages"
      @update:model-value="fetchArticles"
    />
  </div>
</template>

<script setup lang="ts">
import axios from 'axios'
import { onMounted, ref } from 'vue'
import type { User } from '@/pages/users/index.vue'
import { useRouter } from 'vue-router';
import { accessToken } from '@/store';

type Props = {
    fetchUrl: string,
    hideAuthor?: boolean
    authorMode?: boolean
}

const { fetchUrl, hideAuthor, authorMode } = defineProps<Props>()

const router = useRouter()

export type Article = {
    id: number
    title: string
    date: string
    content: string
    user: User
}

const articleList = ref<Article[]>([])
const totalPages = ref(0)

const fetchArticles = async (page: number) => {
  const pageInfo = (await axios.get(`${fetchUrl}${page - 1}`)).data
  articleList.value = pageInfo.content
  totalPages.value = pageInfo.totalPages
  
}

const deleteArticle = async (id: number) => {
  await axios.post('http://localhost:8080/v1/api/article/delete', {
    token: accessToken.value,
    article: {
      id
    }
  })
  fetchArticles(1)
}

onMounted(() => {
  fetchArticles(1)
})
</script>
