<template>
  <div>
    <button type="button" v-on:click="selectFile()"  class="btn btn-primary"><!--class="sf-button sf-button-dark"-->
      <i class="ace-icon fa fa-upload"></i>
      <span>{{text}}</span>
    </button>
    <input class="hidden" type="file" ref="file" v-on:change="uploadFile()" v-bind:id="inputId+'-input'">
  </div>
</template>

<script>
    export default {
        name: 'file',
        props: {
            text: {
                default: "上传文件"
            },
            inputId: {
                default: "file-upload"
            },
            suffixs: {
                default: []
            },
            use: {
                default: ""
            },
            afterUpload: {
                type: Function,
                default: null
            },
        },
        data: function () {
            return {
            }
        },
        methods: {
            uploadFile () {
                let _this = this;
                let formData = new window.FormData();
                let file = _this.$refs.file.files[0];

                // 判断文件格式
                let suffixs = _this.suffixs;
                let fileName = file.name;
                let suffix = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length).toLowerCase();
                let validateSuffix = false;
                for (let i = 0; i < suffixs.length; i++) {
                    if (suffixs[i].toLowerCase() === suffix) {
                        validateSuffix = true;
                        break;
                    }
                }
                if (!validateSuffix) {
                    Toast.warning("文件格式不正确！只支持上传：" + suffixs.join(","));
                    $("#" + _this.inputId + "-input").val("");
                    return;
                }

                // key："file"必须和后端controller参数名一致
                formData.append('file', file);
                //formData.append('use', _this.use);
                //Loading.show();
                _this.$ajax.post(_this.$javaurl + '/admin/upload', formData).then((response)=>{
                    //Loading.hide();
                    let resp = response.data;
                    console.log("上传文件成功：", resp);
                    Toast.success("上传成功!");
                    //console.log("img1=="+typeof _this.uploadFile(resp));
                    _this.afterUpload(resp);
                    //console.log("img2=="+typeof _this.uploadFile(resp));
                    $("#" + _this.inputId + "-input").val("");
                });
            },

            selectFile () {
                let _this = this;
                $("#" + _this.inputId + "-input").trigger("click");
            }
        }
    }
</script>
<style>
  .btn-white{
    background-color: #eff2f4!important;
    border-color: #ABBAC3;
    color: #6b8595!important;
  }

</style>
