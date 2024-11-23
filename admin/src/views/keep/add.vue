<template>
    <div>
        <div class="contentpanel">
            <div class="row">
                <div class="col-xs-12">
                    <!-- PAGE CONTENT BEGINS -->
                    <form class="form-horizontal"><!--role="form"-->
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                                            <h4 class="panel-title">添加收藏</h4>
                                <p></p>
                            </div>
                            <div class="panel-body">



                            <div class="form-group">
                                <label class="col-sm-3 control-label no-padding-right"> 联系人 </label>
                                <div class="col-sm-9">
                                    <input type="text" placeholder="请输入联系人"
                                           class="form-control col-xs-10 col-sm-5"
                                           v-model="keep.cid"/>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-sm-3 control-label no-padding-right"> 类型 </label>
                                <div class="col-sm-9">
                                    <input type="text" placeholder="请输入类型"
                                           class="form-control col-xs-10 col-sm-5"
                                           v-model="keep.ctype"/>
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


    export default {

        components: {File,
        },
        name: "add.vue",
        data: function () {
            return {
                keep:{},
                keeps: [],
                tlist :[],
                qlist:[],
                ualist:[],
                glist:[],

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

        },
        methods: {



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
                 if (!Validator.require(_this.keep.cid, "联系人")) {
                     return;
                 }
                 if (!Validator.require(_this.keep.ctype, "类型")) {
                     return;
                 }
                //Loading.show();
                _this.$ajax.post(_this.$javaurl + "/admin/keep/save", _this.keep).then((response) => {
                    // Loading.hide();
                    let resp = response.data;
                    console.log("data==" + resp);
                    if (resp == "success") {
                        Toast.success("保存成功！");
                        //alert("保存成功！");
                        _this.$router.push("/keep");
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
                    _this.tlist = resp.tlist;
                    _this.qlist = resp.qlist;
                    _this.ualist = resp.ualist;
                    _this.glist = resp.glist;
                    //_this.communitys = resp.communityList;
                })
            },
        },
        computed: {
        }
    }
</script>

