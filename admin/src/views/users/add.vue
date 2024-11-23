<template>
    <div>
        <div class="contentpanel">
            <div class="row">
                <div class="col-xs-12">
                    <!-- PAGE CONTENT BEGINS -->
                    <form class="form-horizontal"><!--role="form"-->
                        <div class="panel panel-success">
                            <div class="panel-heading">
                                <h4 class="panel-title">添加用户</h4>
                                <p></p>
                            </div>
                            <div class="panel-body">

                                <div class="form-group">
                                    <label class="col-sm-3 control-label no-padding-right"> 用户名 </label>
                                    <div class="col-sm-9">
                                        <input type="text" placeholder="请输入用户名" class="form-control col-xs-10 col-sm-5"
                                               v-model="users.uname" @blur="checkName();"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label no-padding-right"> 姓名 </label>
                                    <div class="col-sm-9">
                                        <input type="text" placeholder="请输入姓名" class="form-control col-xs-10 col-sm-5"
                                               v-model="users.name"/>
                                    </div>
                                </div>


                                <div class="form-group">
                                    <label class="col-sm-3 control-label no-padding-right"> 图片上传 </label>
                                    <div class="col-sm-9">
                                        <file v-bind:input-id="'image-upload'"
                                              v-bind:text="'图片上传'"
                                              v-bind:suffixs="['jpg', 'jpeg', 'png','gif','webp']"
                                              v-bind:after-upload="afterUpload"></file>
                                        <div v-show="users.img" class="row">
                                            <div class="col-md-4">
                                                <img v-bind:src="imgUrl+users.img" class="img-responsive">
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label no-padding-right"> 性别 </label>
                                    <div class="col-sm-9">
                                        <select v-model="users.sex" class="form-control">
                                            <option value="男">男</option>
                                            <option value="女">女</option>
                                        </select>
                                        <!--<input type="text" placeholder="请输入性别" class="form-control col-xs-10 col-sm-5" v-model="users.sex" />-->
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label no-padding-right"> 地址 </label>
                                    <div class="col-sm-9">
                                        <input type="text" placeholder="请输入地址" class="form-control col-xs-10 col-sm-5"
                                               v-model="users.address"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label no-padding-right"> 密码 </label>
                                    <div class="col-sm-9">
                                        <input type="password" placeholder="请输入密码"
                                               class="form-control col-xs-10 col-sm-5" v-model="users.pwd"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label no-padding-right"> 重复密码 </label>
                                    <div class="col-sm-9">
                                        <input type="password" placeholder="请输入密码"
                                               class="form-control col-xs-10 col-sm-5" v-model="pwd"/>
                                    </div>
                                </div>
                                <!--<div class="form-group" >
                                    <label class="col-sm-3 control-label no-padding-right" > 角色 </label>
                                    <div class="col-sm-9">
                                        <input type="text" placeholder="请输入角色" class="form-control col-xs-10 col-sm-5" v-model="users.utype" />
                                    </div>
                                </div>-->
                                <div class="form-group">
                                    <label class="col-sm-3 control-label no-padding-right"> 电话 </label>
                                    <div class="col-sm-9">
                                        <input type="text" placeholder="请输入电话" class="form-control col-xs-10 col-sm-5"
                                               v-model="users.tel"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label no-padding-right"> 年龄 </label>
                                    <div class="col-sm-9">
                                        <input type="text" placeholder="请输入年龄"
                                               class="form-control col-xs-10 col-sm-5"
                                               v-model="users.age"/>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-sm-3 control-label no-padding-right"> 邮箱 </label>
                                    <div class="col-sm-9">
                                        <input type="text" placeholder="请输入邮箱"
                                               class="form-control col-xs-10 col-sm-5"
                                               v-model="users.email"/>
                                    </div>
                                </div>
                                <div class="space-4"></div>

                                <div class="clearfix form-actions1">
                                    <div class="col-md-offset-3 col-md-9">
                                        <button class="btn btn-info" type="button" @click="save();">
                                            <i class="ace-icon fa fa-check bigger-110"></i>
                                            提交
                                        </button>
                                        &nbsp; &nbsp; &nbsp;
                                        <button class="btn" type="reset">
                                            <i class="ace-icon fa fa-undo bigger-110"></i>
                                            重置
                                        </button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </form>
                </div><!-- /.col -->
            </div>
        </div>
    </div>

</template>

<script>

    const toolbarOptions = [
        ["bold", "italic", "underline", "strike"], // toggled buttons
        ["blockquote", "code-block"],

        [{header: 1}, {header: 2}], // custom button values
        [{list: "ordered"}, {list: "bullet"}],
        [{script: "sub"}, {script: "super"}], // superscript/subscript
        [{indent: "-1"}, {indent: "+1"}], // outdent/indent
        [{direction: "rtl"}], // text direction

        [{size: ["small", false, "large", "huge"]}], // custom dropdown
        [{header: [1, 2, 3, 4, 5, 6, false]}],
        [{color: []}],
        [{background: []}],
        //   [{'color': []}, {'background': []}],          // dropdown with defaults from theme
        [{font: []}],
        [{align: []}],
        ["link", "image", "video"],
        ["clean"] // remove formatting button
    ];


    import File from "@/components/file";

    import {quillEditor} from 'vue-quill-editor'; //调节编辑器
    import {addQuillTitle} from '@/util/qulEditor';

    import {ImageDrop} from 'quill-image-drop-module'//调节图片大小
    import ImageResize from 'quill-image-resize-module'

    Quill.register('modules/imageDrop', ImageDrop)
    Quill.register('modules/imageResize', ImageResize)


    export default {
        components: {File, quillEditor, addQuillTitle},
        name: "add.vue",
        data: function () {
            return {
                users: {},
                userss: [],
                tlist: [],
                qlist: [],
                imgUrl: '',
                userInfo: {},
                editorOption: {
                    modules: {
                        toolbar: {
                            container: toolbarOptions, // 工具栏
                            handlers: {
                                image: function (value) {
                                    if (value) {
                                        $("#" + "image-upload2-input").trigger("click");
                                    } else {
                                        this.quill.format("image", false);
                                    }
                                },
                                /* video: function (value) {
                                     if (value) {
                                         $("#" + "video-upload2-input").trigger("click");
                                     } else {
                                         this.quill.format("video", false);
                                     }
                                 }*/
                            }
                        },
                        imageResize: {
                            displayStyles: {
                                backgroundColor: 'black',
                                border: 'none',
                                color: 'white'
                            },
                            //modules: ['Resize', 'DisplaySize', 'Toolbar']
                        },
                    }
                }
            }
        },
        mounted: function () {
            let _this = this;
            _this.imgUrl = _this.$imgurl;
            _this.loginUser = Tool.getLoginUser();
            if (_this.loginUser.id != null) {
            } else {
                _this.$router.push("/login");
            }
            _this.users.name = '普通用户1';
            _this.users.sex = '男';
            _this.users.email = '1854634@qq.com';
            _this.users.salary = 5000;
            _this.users.age = '20';
            _this.commonAll();
            addQuillTitle();

        },
        methods: {

            onEditorReady(editor) { //准备编辑器
                console.log("ready", editor);
            },
            onEditorBlur() {
            },//失去焦点事件
            onEditorFocus() {
            }, /*获得焦点事件*/
            onEditorChange() {
            }, /*内容改变事件*/
            afterUpload2(resp) {
                let _this = this;
                let quill = this.$refs.myQuillEditor.quill;
                if (resp) {
                    let img = resp.content;
                    // 获取光标所在位置
                    let length = quill.getSelection().index;
                    // 插入图片，res为服务器返回的图片链接地址
                    quill.insertEmbed(length, "image", _this.imgUrl + img);
                    // 调整光标到最后
                    quill.setSelection(length + 1);
                } else {
                    // 提示信息，需引入Message
                    Message.error("图片插入失败");
                }
            },
            afterUpload(resp) {
                let _this = this;
                console.log("resp==" + resp)
                let img = resp.content;
                console.log("img==" + img)
                _this.users.img = img;
            },
            afterUploadVideo(resp) {
                let quill = this.$refs.myQuillEditor.quill;
                if (resp) {
                    let _this = this;
                    let img = resp.content;
                    // 获取光标所在位置
                    let length = quill.getSelection().index;
                    // 插入图片，res为服务器返回的视频链接地址
                    quill.insertEmbed(length, "video", _this.imgUrl + img);
                    // 调整光标到最后
                    quill.setSelection(length + 1);
                } else {
                    // 提示信息，需引入Message
                    Message.error("视频插入失败");
                }
            },
            checkName() {
                let _this = this;
                _this.$ajax.post(_this.$javaurl + "/admin/users/checkName", _this.users).then((response) => {
                    // Loading.hide();
                    let rs = response.data;
                    console.log("data==" + rs);
                    if (rs == "ng") {
                        console.log("名称已存在！请替换！");
                        Toast.warning("名称已存在！请替换！");
                        _this.users.uname = "";
                        return false;
                        //alert("名称已存在！请替换！");
                    } else {
                        console.log("名称可以用！");
                        // Toast.warning(resp.message)
                    }
                })
            },

            /**
             * 点击【保存】
             */
            save() {
                let _this = this;
                // 保存校验
                if (!Validator.require(_this.users.uname, "用户名")) {
                    return;
                }
                if (!Validator.require(_this.users.name, "姓名")) {
                    return;
                }
                if (!Validator.require(_this.users.sex, "性别")) {
                    return;
                }
                if (!Validator.require(_this.users.address, "地址")) {
                    return;
                }
                if (!Validator.require(_this.users.pwd, "密码") || !Validator.length(_this.users.pwd, "密码", 6, 12)) {
                    return;
                }
                if (!Validator.require(_this.pwd, "重复密码") || !Validator.length(_this.pwd, "重复密码", 6, 12) ||
                    !Validator.checkEqual(_this.users.pwd, _this.pwd, "重复密码")) {
                    return;
                }
                _this.users.utype = '普通用户';
                /*if (!Validator.require(_this.users.utype, "角色")) {
                    return;
                }*/
                if (!Validator.require(_this.users.tel, "电话号码") || !Validator.checkTel(_this.users.tel, "电话号码")) {
                    return;
                }

                if (!Validator.require(_this.users.age, "年龄")) {
                    return;
                }
                _this.users.status = "正常";
                /*if (!Validator.require(_this.users.status, "目前状态")) {
                    return;
                }*/
                if (!Validator.require(_this.users.email, "邮箱")) {
                    return;
                }
                //Loading.show();
                _this.$ajax.post(_this.$javaurl + "/admin/users/save", _this.users).then((response) => {
                    // Loading.hide();
                    let resp = response.data;
                    console.log("data==" + resp);
                    if (resp == "success") {
                        Toast.success("保存成功！");
                        //alert("保存成功！");
                        _this.$router.push("/users");
                    } else {
                        Toast.error("保存失败！");
                    }
                })
            },
            commonAll() {
                let _this = this;
                _this.$ajax.post(_this.$javaurl + '/admin/common/all').then((response) => {
                    let resp = response.data;
                    console.log(resp);
                    //_this.communitys = resp.communityList;
                })
            },
        },
        computed: {
            editor() {
                return this.$refs.myQuillEditor.quill;
            }
        }
    }
</script>

