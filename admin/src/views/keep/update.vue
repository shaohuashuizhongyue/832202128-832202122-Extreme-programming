<template>
    <div>
        <div class="contentpanel">
        <div class="row">
            <div class="col-xs-12">
                <!-- PAGE CONTENT BEGINS -->
                <form class="form-horizontal" ><!--role="form"-->
                    <div class="panel panel-default">
                        <div class="panel-heading">
                                                        <h4 class="panel-title">修改收藏</h4>
                            <p></p>
                        </div>
                        <div class="panel-body">
                        <div class="space-4">  <input hidden v-model="keep.id" /></div>
                            <div class="form-group" >
                                <label class="col-sm-3 control-label no-padding-right" > 联系人 </label>
                                <div class="col-sm-9">
                                    <input type="text" placeholder="请输入联系人" class="form-control col-xs-10 col-sm-5" v-model="keep.cid" />
                                </div>
                            </div>
                            <div class="form-group" >
                                <label class="col-sm-3 control-label no-padding-right" > 类型 </label>
                                <div class="col-sm-9">
                                    <input type="text" placeholder="请输入类型" class="form-control col-xs-10 col-sm-5" v-model="keep.ctype" />
                                </div>
                            </div>
                    <div class="space-4"> </div>

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
                    </div></div>
                </form>
            </div><!-- /.col -->
        </div>
        <div>
        </div>
    </div>
    </div>


</template>

<script>

    const toolbarOptions = [
        ["bold", "italic", "underline", "strike"], // toggled buttons
        ["blockquote", "code-block"],

        [{ header: 1 }, { header: 2 }], // custom button values
        [{ list: "ordered" }, { list: "bullet" }],
        [{ script: "sub" }, { script: "super" }], // superscript/subscript
        [{ indent: "-1" }, { indent: "+1" }], // outdent/indent
        [{ direction: "rtl" }], // text direction

        [{ size: ["small", false, "large", "huge"] }], // custom dropdown
        [{ header: [1, 2, 3, 4, 5, 6, false] }],
        [{ color: [] }],
        [{ background: [] }],
        //   [{'color': []}, {'background': []}],          // dropdown with defaults from theme
        [{ font: [] }],
        [{ align: [] }],
        ["link", "image", "video"],
        ["clean"] // remove formatting button
    ];


    import File from "@/components/file";

    import {quillEditor} from 'vue-quill-editor'; //调节编辑器
    import { addQuillTitle } from '@/util/qulEditor';
    import { ImageDrop } from 'quill-image-drop-module'//调节图片大小
    import ImageResize from 'quill-image-resize-module'
    Quill.register('modules/imageDrop', ImageDrop)
    Quill.register('modules/imageResize', ImageResize)


    export default {
        components: {File,quillEditor,addQuillTitle},
        name: "update.vue",
        data:function(){
            return {
                keep: {},
                keeps: [],
                tlist :[],
                qlist:[],
                ualist:[],
                glist:[],
                imgUrl:'',
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
                            modules: ['Resize', 'DisplaySize', 'Toolbar']
                        },
                    }
                }
            }
        },
        mounted:function () {
            let _this = this;
            _this.imgUrl=_this.$imgurl;
            _this.loginUser= Tool.getLoginUser();
            if (_this.loginUser.id!=null) {
                _this.keep=SessionStorage.get("KEEP");
            }else {
                _this.$router.push("/login");
            }
            _this.commonAll();
            addQuillTitle();
        },
        methods:{

            onEditorReady(editor){ //准备编辑器
                console.log("ready", editor);
            },
            onEditorBlur(){},//失去焦点事件
            onEditorFocus(){}, /*获得焦点事件*/
            onEditorChange(){},/*内容改变事件*/
            afterUpload2(resp) {
                let _this = this;
                let quill = this.$refs.myQuillEditor.quill;
                if (resp) {
                    let img = resp.content;
                    // 获取光标所在位置
                    let length = quill.getSelection().index;
                    // 插入图片，res为服务器返回的图片链接地址
                    quill.insertEmbed(length, "image", _this.imgUrl+img);
                    // 调整光标到最后
                    quill.setSelection(length + 1);
                } else {
                    // 提示信息，需引入Message
                    Message.error("图片插入失败");
                }
            },


            afterUploadVideo(resp) {
                let quill = this.$refs.myQuillEditor.quill;
                if (resp) {
                    let _this= this;
                    let img = resp.content;
                    // 获取光标所在位置
                    let length = quill.getSelection().index;
                    // 插入图片，res为服务器返回的视频链接地址
                    quill.insertEmbed(length, "video", _this.imgUrl+img);
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
                         if ( !Validator.require(_this.keep.cid, "联系人")) {
                             return;
                         }
                         if ( !Validator.require(_this.keep.ctype, "类型")) {
                             return;
                         }
                //Loading.show();
                _this.$ajax.post(_this.$javaurl+"/admin/keep/save", _this.keep).then((response)=>{
                    // Loading.hide();
                    let resp = response.data;
                    console.log("data=="+resp);
                    if (resp=="success") {
                        Toast.success("保存成功！");
                        //alert("保存成功！");
                        _this.$router.push("/keep");
                    } else {
                        Toast.error("保存失败！");
                    }
                })
            },
            afterUpload(resp) {
                let _this = this;
                console.log("resp=="+resp)
                let img = resp.content;
                console.log("img=="+img)
                _this.keep.img = img;
            },
            commonAll() {
                let _this = this;
                _this.$ajax.post(_this.$javaurl + '/admin/common/all').then((response)=>{
                    let resp = response.data;
                    console.log(resp);
                    _this.tlist = resp.tlist;
                    _this.qlist = resp.qlist;
                    _this.ualist = resp.ualist;
                    _this.glist = resp.glist;
                    //_this.communitys = resp.communityList;
                })
            },
        },
        computed:{
            editor(){
                return this.$refs.myQuillEditor.quill;
            }
        }
    }
</script>

