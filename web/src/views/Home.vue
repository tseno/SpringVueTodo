<template>
    <el-container>
        <el-main>
            <div>
                <el-collapse-transition>
                    <el-table :data="tasks">
                        <el-table-column label="やること">
                            <template slot-scope="scope">
                                <label :class="{checked: scope.row.checked}">
                                    <el-checkbox v-model="scope.row.checked"
                                                 @change="onUpdate(scope.row)"></el-checkbox>
                                    {{scope.row.title}}</label>
                            </template>
                        </el-table-column>
                        <el-table-column label="期限" width="120px">
                            <template slot-scope="scope">
                                {{ new Date(scope.row.dueDate).toLocaleDateString() }}
                            </template>
                        </el-table-column>
                        <el-table-column label="削除">
                            <template slot-scope="scope">
                                <el-button icon="el-icon-delete" @click="onDelete(scope.row)"></el-button>
                            </template>
                        </el-table-column>
                    </el-table>
                </el-collapse-transition>
            </div>
        </el-main>
        <el-footer>
            <el-form ref="form" :model="form" :rules="rules" label-width="120px">
                <el-form-item label="やること" prop="title">
                    <el-input type="text" size="large" v-model="form.title"></el-input>
                </el-form-item>
                <el-form-item label="期限" prop="dueDate">
                    <el-date-picker type="date" v-model="form.dueDate" default-time="00:00:00"></el-date-picker>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" @click="onSubmit">登録</el-button>
                </el-form-item>
            </el-form>
        </el-footer>
    </el-container>

</template>

<script>
    import axios from "axios";

    export default {
        name: 'Home',
        data() {
            return {
                tasks: [],
                form: {
                    title: '',
                    dueDate: null
                },
                rules: {
                    title: [
                        {required: true, message: 'やることが未入力です。', trigger: 'blur'}
                    ],
                    dueDate: [
                        {required: true, message: '期限が未入力です。', trigger: 'blur'}
                    ]
                }
            }
        },
        computed: {
            countTask() {
                return this.tasks.length;
            },
            searchText: {
                get() {
                    return this.innerSearchText
                },
                set(value) {
                    this.innerSearchText = value
                }
            }
        },
        methods: {
            onSubmit() {
                this.$refs['form'].validate((valid) => {
                    if (valid) {
                        axios.post('http://localhost:8080/api/tasks', {
                            title: this.form.title,
                            dueDate: this.form.dueDate
                        })
                            .then(res => {
                                    this.getTasks()
                                    this.$notify({
                                        title: 'ToDo List',
                                        message: this.$createElement('p', {style: 'color: #009'},
                                            "'" + this.form.title + "' を登録しました。"),
                                        type: 'success',
                                        duration: 2000
                                    })
                                    this.form.dueDate = null
                                    this.form.title = ''
                                    this.$router.push('/')
                                    console.log(res)
                                }
                            )
                            .catch(error => {
                                    this.$notify({
                                        title: 'ToDo List',
                                        message: this.$createElement('p', {style: 'color: #900'},
                                            "'" + this.form.title + "' の登録に失敗しました。"),
                                        type: 'error',
                                        duration: 2000
                                    })
                                    console.log(error)
                                }
                            )


                    }
                })
            },
            onUpdate(task) {
                let updateTask = this.tasks.filter(t => t.id === task.id)
                axios.put('http://localhost:8080/api/tasks/' + task.id,
                    {
                        id: task.id,
                        title: updateTask[0].title,
                        dueDate: updateTask[0].dueDate,
                        checked: updateTask[0].checked
                    })
                    .then(res => {
                            this.$notify({
                                title: 'ToDo List',
                                message: this.$createElement('p', {style: 'color: #900'},
                                    "'" + task.title + "' を更新しました。"),
                                type: 'success',
                                duration: 2000
                            })
                            console.log(res)
                        }
                    )
                    .catch(error => {
                            this.$notify({
                                title: 'ToDo List',
                                message: this.$createElement('p', {style: 'color: #900'},
                                    "'" + task.title + "' の更新に失敗しました。"),
                                type: 'error',
                                duration: 2000
                            })
                            console.log(error)
                        }
                    )

            },
            onDelete(task) {
                axios.delete('http://localhost:8080/api/tasks/' + task.id)
                    .then(res => {
                            this.getTasksForDeleted()
                            this.$notify({
                                title: 'ToDo List',
                                message: this.$createElement('p', {style: 'color: #900'},
                                    "'" + task.title + "' を削除しました。"),
                                type: 'success',
                                duration: 2000
                            })
                            console.log(res)
                        }
                    )
                    .catch(error => {
                            this.$notify({
                                title: 'ToDo List',
                                message: this.$createElement('p', {style: 'color: #900'},
                                    "'" + task.title + "' の削除に失敗しました。"),
                                type: 'error',
                                duration: 2000
                            })
                            console.log(error)
                        }
                    )

            },
            getTasks() {
                axios.get('http://localhost:8080/api/tasks')
                    .then(response => {
                        this.tasks = response.data
                    });
            },
            getTasksForDeleted() {
                axios.get('http://localhost:8080/api/tasks')
                    .then(response => {
                        // 現状のtasksと、apiから取得したtaskを比較して、無いものは削除する
                        this.tasks = this.tasks.filter(item => response.data.some(r => r.id === item.id))
                    });
            }
        },
        mounted() {
            this.getTasks()
        }
    }
</script>
<style>
    .el-header {
        background-color: #000;
        color: #FFF;
        line-height: 60px;
    }

    .checked {
        text-decoration: line-through;
    }

</style>