<template>
    <div  >
        <!--左侧导航开始-->
        <div class="left">
            <Left></Left>
        </div>
        <!--左侧导航结束-->
        <!--右侧部分开始-->
        <div id="page-wrapper" class="gray-bg dashbard-1">
            <div class="row J_mainContent" id="content-main">
                <Right></Right>
            </div>
            <!--顶部-->
            <!--<Footer></Footer>-->
        </div>
    </div>

</template>

<script>
    import Left from './left'
    import Right from './right'
    import Footer from './footer'
    export default {
        name: "layout",
        components:{
            Left,
            Right,
            Footer
        }
    }

/*    import "@/ajh/css/bootstrap.min.css";
    import "@/ajh/css/font-awesome.min.css";
    import "@/ajh/css/animate.css";
    import "@/ajh/css/style.css?v=4.1.0";*/
</script>

<style  scoped>

    .right{
        padding-left: 220px;
    }
    .left{
        width: 200px;
        min-height: 400px;
        display: block;
        float: left;
        height: 100%;
    }



</style>
