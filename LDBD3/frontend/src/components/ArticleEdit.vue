<template>
  <v-layout class="ma-16 d-flex flex-column ga-4">
    <v-text-field
      v-model:model-value="title"
      label="Title"
      max-width="32rem"
    />
    <v-textarea
      v-model:model-value="content"
      label="Content"
      variant="solo-filled"
      auto-grow
    />
    <div class="d-flex ga-4 justify-end">
      <v-btn
        @click="() => router.back()"
      >
        Back
      </v-btn>
      <v-btn
        color="success"
        @click="editArticle"
      >
        {{ mode === 'create' ? 'Create' : 'Save' }}
      </v-btn>
    </div>
  </v-layout>
</template>
  
<script lang="ts" setup>
import { accessToken } from '@/store';
import axios from 'axios';
import { ref } from 'vue';
import { useRouter } from 'vue-router';
import type { Article } from './ArticlesList.vue';


type Props = {
    article?: Article
    postUrl: string
    mode: 'create' | 'edit'
}

const { article, postUrl, mode } = defineProps<Props>()

const title = ref(article?.title ?? '')
const content = ref(article?.content ?? '')

const router = useRouter()

const editArticle = async () => {
    await axios.post(postUrl, {
        token: accessToken.value,
        article: {
            id: article?.id,
            title: title.value,
            content: content.value
        }
    })
    router.back()
}

</script>