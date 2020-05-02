<template>
    <div id="app">
        <el-header>
            <p>ToDo List</p>
        </el-header>
        <el-container style="border: 1px solid #eee">
            <el-aside width="200px" style="background-color: rgb(238, 241, 246)">
                <el-menu>
                    <template><i class="el-icon-circle-check"></i>Todo</template>
                </el-menu>
            </el-aside>

            <router-view/>
        </el-container>

    </div>
</template>

<script>

    export default {
        name: 'app',
    }
</script>

<style>
    #app {
    }
</style>
