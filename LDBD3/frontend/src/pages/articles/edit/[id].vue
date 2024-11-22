<template>
  <ArticleEdit
    v-if="article"
    :article="article"
    mode="edit"
    post-url="http://localhost:8080/v1/api/article/create"
  />
</template>

<script lang="ts" setup>
import ArticleEdit from '@/components/ArticleEdit.vue';
import type { Article } from '@/components/ArticlesList.vue';
import axios from 'axios';
import { onMounted, ref } from 'vue';

const { id } = defineProps<{id: string}>()
const article = ref<Article | null>(null)

const fetchArticle = async () => {
  article.value = (await axios.get(`http://localhost:8080/v1/api/article/${id}`)).data
}

onMounted(() => {
  fetchArticle()
})

</script>