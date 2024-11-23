<template>
    <div>
        <div class="header"  style="margin-bottom: 0">
            <Top></Top>
        </div>
        <div class="gray-bg">
            <router-view></router-view>
           <!-- <h2>右下</h2>-->
        </div>

    </div>
</template>

<script>
    import Top from './top'
    export default {
        name: "right",
        components:{
            Top,
        },
    }
</script>

<style scoped>
    /*.header{
        background: skyblue;
        height: 50px;
        width: 100%;
    }*/

</style>
