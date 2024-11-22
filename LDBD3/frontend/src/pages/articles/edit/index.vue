<template>
  <ArticleEdit
    mode="create"
    post-url="http://localhost:8080/v1/api/article/create"
  />
</template>

<script lang="ts" setup>
import ArticleEdit from '@/components/ArticleEdit.vue';

</script>