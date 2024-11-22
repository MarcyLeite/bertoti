<template>
  <div
    class="my-16 d-flex flex-column ga-4" 
    max-width="980"
  >
    <span class="text-h5">Articles</span>
    <ArticlesList fetch-url="http://localhost:8080/v1/api/article/list/" />
  </div>
</template>

<script setup lang="ts">
import ArticlesList from '@/components/ArticlesList.vue';

</script>
