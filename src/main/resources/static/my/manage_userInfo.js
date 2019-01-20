/* 用户信息数据  */
var userdata = {
    user_name:'',
    user_truename:'',
    user_department:'',
    user_phone:'',
    user_authority:'',
    user_position:'',
    show_7:false,
    show_3:false,
}
/* 各种信息流，提示信息 */
var message = {
    show:false,
    info:'',
}

/* vue获取数据 */
var user_Info = new Vue({
    el:'#user',
    data:userdata,
    created:function(){
        var url = "http://localhost:8080/userInfo";
        var self = this;
        axios.get(url).then(function(response){
            var result = response.data;
            self.user_truename = result.user_truename;
            self.user_name = result.user_name;
            self.user_department = result.user_department;
            self.user_phone = result.user_phone;
            self.user_authority = result.user_authority;
            if(result.user_authority == 1){
                self.user_position = "普通员工";
            }else if(result.user_authority == 3){
                self.user_position = "财务人员";
            }else{
                self.user_position = "管理员";
            }

        })

    },
    methods:{
        userLoginout:function(){
            axios.get("http://localhost:8080/loginout").then()
        }
    },
})

/* 左侧功能栏根据用户权限显示相对应的功能模块 */
var modalShow = new Vue({
    el:'#fun_show',
    data:{
        userdata,
        m_office:true,
        m_officeInfo:false,
        m_userInfo:false,
        m_applyCheck:false,
        m_applylist:false,
        m_borrowlist:false,
        m_returnlist:false,
        m_usedata:false,
    },
    methods:{

    }
})

/* modal框的数据流，数据展示和数据修改 */
var modalData = new Vue({
    el:'#userInfo',
    data:{
        userdata,
        userInfo_change_name:'',
        userInfo_change_phone:'',
        userInfo_change_old_pwd:'',
        userInfo_change_new_pwd:'',
        userInfo_change_sure_pwd:'',
        userN_display:true,
        userP_display:true,
        user_pwd_change:true,
        message,
    },
    methods:{
        /* 取消修改姓名 */
        cancelChangeN:function(){
            var self = this;
            self.userN_display = true;
        },
        /* 取消修改电话 */
        cancelChangeP:function(){
            var self = this;
            self.userP_display = true;
        },
        /* 取消修改密码 */
        cancelChangePwd:function(){
            var self = this;
            self.user_pwd_change = true;
        },
        /* 打开修改姓名 */
        openChangeN:function(){
            var self = this;
            self.userN_display = false;
        },
        /* 打开修改电话 */
        openChangeP:function(){
            var self = this;
            self.userP_display = false;
        },
        /* 打开修改电话 */
        openPwdChange:function(){
            var self = this;
            self.user_pwd_change = false;
        },
        /* 取消修改所有（关闭modal框） */
        turnoff:function(){
            var self = this;
            self.userN_display = true;
            self.userP_display = true;
            self.user_pwd_change = true;
        },
        /* 用户姓名修改 */
        changeUserName:function(){
            var url = 'http://localhost:8080/changeUserName';
            var self = this;
            var params = new URLSearchParams();
            params.append('userNewName',self.userInfo_change_name);
            params.append('user_name',self.userdata.user_name);
            axios.post(url,params).then(function (response) {
                var result = response.data;
                if( result.result ){
                    self.message.info = "用户姓名修改成功！";
                    $('.alert-success').removeClass('hide').addClass('in');
                    setTimeout(function(){$('.alert-success').removeClass('in').addClass('hide');},2000);
                    setTimeout(function(){location.reload();},3000);
                    self.userN_display = true;

                }else{
                    self.message.info = "用户姓名超过10位";
                    $('.alert-danger').removeClass('hide').addClass('in')
                    setTimeout(function(){$('.alert-danger').removeClass('in').addClass('hide')},3000);
                }
            }).catch(function(reason) {
                self.message.info = "网络故障";
                $('.alert-danger').removeClass('hide').addClass('in')
                setTimeout(function(){$('.alert-danger').removeClass('in').addClass('hide')},3000);
            })
        },
        /* 用户电话修改 */
        changeUserPhone:function(){
            var url = 'http://localhost:8080/changeUserPhone';
            var self = this;
            var params = new URLSearchParams();
            params.append('userNewPhone',self.userInfo_change_phone);
            params.append('user_name',self.userdata.user_name);
            axios.post(url,params).then(function (response) {
                var result = response.data;
                if( result.result ){
                    self.message.info = "用户姓名修改成功！";
                    $('.alert-success').removeClass('hide').addClass('in');
                    setTimeout(function(){$('.alert-success').removeClass('in').addClass('hide');},2000);
                    setTimeout(function(){location.reload();},3000);
                    self.userN_display = true;

                }else{
                    self.message.info = "用户电话超过11位";
                    $('.alert-danger').removeClass('hide').addClass('in')
                    setTimeout(function(){$('.alert-danger').removeClass('in').addClass('hide')},3000);
                }
            }).catch(function(reason) {
                self.message.info = "网络故障";
                $('.alert-danger').removeClass('hide').addClass('in')
                setTimeout(function(){$('.alert-danger').removeClass('in').addClass('hide')},3000);
            })
        },
        /* 用户密码修改 */
        changeUserPwd:function(){
            var url = 'http://localhost:8080/changeUserPwd';
            var self = this;
            if( self.userInfo_change_new_pwd == self.userInfo_change_sure_pwd ){
                /*self.Sure_pwd = false;*/
                var params = new URLSearchParams();
                params.append('userOldPwd',self.userInfo_change_old_pwd);
                params.append('userNewPwd',self.userInfo_change_new_pwd);
                params.append('user_name',self.userdata.user_name);
                axios.post(url,params).then(function (response) {
                    var result = response.data;
                    if( result.result ){
                        self.message.info = result.info;
                        $('.alert-success').removeClass('hide').addClass('in');
                        setTimeout(function(){
                            $('.alert-success').removeClass('in').addClass('hide');
                            location.href="http://localhost:8080/loginout";
                        },3000);
                        /* 旧密码错误 */
                    }else{
                        self.message.info = result.info;
                        /*self.Old_pwd = true;*/
                        $('.alert-danger').removeClass('hide').addClass('in')
                        setTimeout(function(){$('.alert-danger').removeClass('in').addClass('hide')},3000);
                    }
                }).catch(function(reason) {
                    self.message.info = "网络故障";
                    $('.alert-danger').removeClass('hide').addClass('in')
                    setTimeout(function(){$('.alert-danger').removeClass('in').addClass('hide')},3000);
                });

            }else{
                /*self.Sure_pwd = true;*/
                self.message.info = "确认密码不相同";
                $('.alert-danger').removeClass('hide').addClass('in')
                setTimeout(function(){$('.alert-danger').removeClass('in').addClass('hide')},3000);
            }

        }
    }
})


