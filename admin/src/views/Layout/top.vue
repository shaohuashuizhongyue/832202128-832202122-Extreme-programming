<template>
    <div class="row content-tabs">
        <button class="roll-nav roll-left J_tabLeft"><i class="fa fa-backward"></i>
        </button>
        <nav class="page-tabs J_menuTabs" style="background: #9967CE;">
            <div class="page-tabs-content">
                <a href="#" class="active J_menuTab" data-id="#" style="background: #9967CE">联系人管理系统</a>
            </div>
        </nav>
        <!--<button class="roll-nav roll-right J_tabRight"><i class="fa fa-forward"></i>
        </button>-->
        <div class="btn-group roll-nav roll-right">
            <!--<button class="dropdown J_tabClose" data-toggle="dropdown">关闭操作<span class="caret"></span>

            </button>-->
            <!--<ul role="menu" class="dropdown-menu dropdown-menu-right">
                <li class="J_tabShowActive"><a>定位当前选项卡</a>
                </li>
                <li class="divider"></li>
                <li class="J_tabCloseAll"><a>关闭全部选项卡</a>
                </li>
                <li class="J_tabCloseOther"><a>关闭其他选项卡</a>
                </li>
            </ul>-->
        </div>
        <a @click="logOut();" class="roll-nav roll-right J_tabExit" style="margin-right: 20px;"><i class="fa fa fa-sign-out"></i> 退出</a>
    </div>

</template>

<script>
    export default {
        data:function () {
            return{
                loginUser: {},

            }

        },
        mounted:function () {
            let _this=this;
            _this.loginUser= Tool.getLoginUser();
            if (!_this.loginUser) {
                _this.$router.push("/login");
            }else {
                //_this.location.reload();
                //_this.$router.push("/home");
            }
        },
        methods:{
            /*注销*/
            logOut(){
                let _this = this;
                _this.$ajax.get(_this.$javaurl+"/admin/users/logOut").then((response)=>{
                    let resp = response.data;
                    if (resp.success){
                        Toast.success("注销成功！");
                        Tool.setLoginUser(null);
                        this.$router.push("/login");
                    } else {
                        console.log("信息=="+resp.message);
                    }
                })

            },
        }
    }
</script>

<style scoped>
    .panel-default {
        background-color: #9967cead;
    }
</style>
