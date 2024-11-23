<template>
    <div>
        <div class="contentpanel">
            <div class="row">
                <div class="col-xs-12">
                    <!-- PAGE CONTENT BEGINS -->
                    <form class="form-horizontal"><!--role="form"-->
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <h4 class="panel-title">添加联系人</h4>
                                <p></p>
                            </div>
                            <div class="panel-body">


                                <div class="form-group">
                                    <label class="col-sm-3 control-label no-padding-right"> 姓名 </label>
                                    <div class="col-sm-9">
                                        <input type="text" placeholder="请输入姓名"
                                               class="form-control col-xs-10 col-sm-5"
                                               v-model="liaison.name"/>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-sm-3 control-label no-padding-right"> 性别 </label>
                                    <div class="col-sm-9">
                                        <select v-model="liaison.sex" class="form-control">
                                            <option value="男">男</option>
                                            <option value="女">女</option>
                                        </select>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-sm-3 control-label no-padding-right"> 年龄 </label>
                                    <div class="col-sm-9">
                                        <input type="number" placeholder="请输入年龄"
                                               class="form-control col-xs-10 col-sm-5"
                                               v-model="liaison.age"/>
                                    </div>
                                </div>


                                <div class="form-group">
                                    <label class="col-sm-3 control-label no-padding-right"> 电话号码 </label>
                                    <div class="col-sm-9">
                                        <input type="text" placeholder="请输入电话号码"
                                               class="form-control col-xs-10 col-sm-5"
                                               v-model="liaison.tel"/>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-sm-3 control-label no-padding-right"> 电子邮件地址 </label>
                                    <div class="col-sm-9">
                                        <input type="mail" placeholder="请输入电子邮件地址"
                                               class="form-control col-xs-10 col-sm-5"
                                               v-model="liaison.email"/>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-sm-3 control-label no-padding-right"> 社交媒体账号 </label>
                                    <div class="col-sm-9">
                                        <input type="text" placeholder="请输入社交媒体账号"
                                               class="form-control col-xs-10 col-sm-5"
                                               v-model="liaison.maccount"/>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-sm-3 control-label no-padding-right"> 物理地址 </label>
                                    <div class="col-sm-9">
                                        <input type="text" placeholder="请输入物理地址"
                                               class="form-control col-xs-10 col-sm-5"
                                               v-model="liaison.address"/>
                                    </div>
                                </div>


                                <div class="form-group">
                                    <label class="col-sm-3 control-label no-padding-right"> 备注： </label>
                                    <div class="col-sm-9">
                                        <!-- 图片上传的组件 -->
                                        <file v-bind:input-id="'image-upload2'"
                                              v-bind:text="'图片上传'"
                                              v-bind:suffixs="['jpg', 'jpeg', 'png','gif','webp']"
                                              v-bind:after-upload="afterUpload2" style="display:none"></file>
                                        <!-- 视频上传的组件 -->
                                        <file v-bind:input-id="'video-upload2'"
                                              v-bind:text="'视频上传'"
                                              v-bind:suffixs="['mp4']"
                                              v-bind:after-upload="afterUploadVideo" style="display:none"></file>
                                        <quill-editor v-model="liaison.note"
                                                      ref="myQuillEditor"
                                                      class=" editer"
                                                      :options="editorOption">
                                        </quill-editor>
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
    import File from "@/components/file";

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


    import {quillEditor} from 'vue-quill-editor'; //调节编辑器
    import {addQuillTitle} from '@/util/qulEditor';

    import {ImageDrop} from 'quill-image-drop-module'//调节图片大小
    import ImageResize from 'quill-image-resize-module'

    Quill.register('modules/imageDrop', ImageDrop)
    Quill.register('modules/imageResize', ImageResize)

    export default {

        components: {File,
            quillEditor,
            addQuillTitle
        },
        name: "add.vue",
        data: function () {
            return {
                liaison:{},
                liaisons: [],
                imgUrl:'',
                editorOption:
                    {
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
                            } ,
                            imageResize: {
                                displayStyles: {
                                    backgroundColor: 'black',
                                    border: 'none',
                                    color:'white'
                                },
                                //modules: ['Resize', 'DisplaySize', 'Toolbar']
                            } ,
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


            //视频上传
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

            /**
             * 点击【保存】
             */
            save() {
                let _this = this;
                // 保存校验
                if (!Validator.require(_this.liaison.name, "姓名")) {
                    return;
                }
                if (!Validator.require(_this.liaison.sex, "性别")) {
                    return;
                }

                if (!Validator.require(_this.liaison.age, "年龄") || !Validator.checkAge(_this.liaison.age, "年龄")) {
                    return;
                }
                if (!Validator.require(_this.liaison.tel, "电话号码") || !Validator.checkTel(_this.liaison.tel, "电话号码")) {
                    return;
                }
                if (!Validator.require(_this.liaison.email, "电子邮件地址")) {
                    return;
                }
                if (!Validator.require(_this.liaison.maccount, "社交媒体账号")) {
                    return;
                }
                if (!Validator.require(_this.liaison.address, "物理地址")) {
                    return;
                }
                _this.liaison.uid=_this.loginUser.id;
                //Loading.show();
                _this.$ajax.post(_this.$javaurl + "/admin/liaison/save", _this.liaison).then((response) => {
                    // Loading.hide();
                    let resp = response.data;
                    console.log("data==" + resp);
                    if (resp == "success") {
                        Toast.success("保存成功！");
                        //alert("保存成功！");
                        _this.$router.push("/liaison");
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

