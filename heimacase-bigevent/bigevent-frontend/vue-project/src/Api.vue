<script setup>
    import { onMounted, ref } from 'vue'
    const apiurl = ref('http://localhost:8080/user/test')
    const count = ref(0)
    const msg = ref()
    onMounted(()=> {
        msg.value = 'onMounted() called'
    })
</script>

<template>
    <div>
        <h1>{{ apiurl }}</h1>
        <button @click="count++">Count: {{ count }}</button>
    </div>
    <div>
        <h1>{{ msg }}</h1>
    </div>
</template>