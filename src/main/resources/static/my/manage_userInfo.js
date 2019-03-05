
/* vue获取数据 */
var user_Info = new Vue({
    el:'#user',
    data:userdata,
    created:function(){
        var url = "/userInfo";
        var self = this;
        axios.get(url).then(function(response){
            var result = response.data;
            if(result.user_authority != 4396){
                self.user_id = result.user_id;
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
            }else {
                location.href="/loginout"
            }

        })

    },
    methods:{
        userLoginout:function(){
            axios.get("/loginout").then()
        }
    },
});

/* 左侧功能栏根据用户权限显示相对应的功能模块 */
var modalShowLeft = new Vue({
    el:'#fun_show_left',
    data:{
        userdata,
        modalShowData,
    },
    methods:{

    }
});

/* 登录用户信息modal框的数据流，数据展示和数据修改 */
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
            var url = '/changeUserName';
            var self = this;
            var params = {
                user_truename:self.userInfo_change_name,
            };
            axios.post(url,params).then(function (response) {
                var result = response.data;
                if( result.result ){
                    self.message.info = "用户姓名修改成功！";
                    $('.alert-success').removeClass('hide').addClass('in');
                    setTimeout(function(){$('.alert-success').removeClass('in').addClass('hide');},2000);
                    setTimeout(function(){location.reload();},3000);
                    self.userN_display = true;

                }else{
                    self.message.info = "用户姓名超过15位";
                    $('.alert-danger').removeClass('hide').addClass('in');
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
            var url = '/changeUserPhone';
            var self = this;
            var params = {
                user_phone:self.userInfo_change_phone,
            };
            axios.post(url,params).then(function (response) {
                var result = response.data;
                if( result.result ){
                    self.message.info = "用户电话修改成功！";
                    $('.alert-success').removeClass('hide').addClass('in');
                    setTimeout(function(){$('.alert-success').removeClass('in').addClass('hide');},2000);
                    setTimeout(function(){location.reload();},3000);
                    self.userP_display = true;

                }else{
                    self.message.info = "用户电话超过11位";
                    $('.alert-danger').removeClass('hide').addClass('in')
                    setTimeout(function(){$('.alert-danger').removeClass('in').addClass('hide')},3000);
                }
            }).catch(function(reason) {
                self.message.info = "用户电话超过11位";
                $('.alert-danger').removeClass('hide').addClass('in')
                setTimeout(function(){$('.alert-danger').removeClass('in').addClass('hide')},3000);
            })
        },
        /* 用户密码修改 */
        changeUserPwd:function(){
            var url = '/changeUserPwd';
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
                            location.href="/loginout";
                        },1500);
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
});

/* m_userInfo框中数据流(分页功能) */
var m_user = new Vue({
    el:'#allUser',
    data:{
        users:[],
        pagination:{},
        tempUserInfo,
        searchUser:'',
        isDelete:false,
        message,
        ob:'',
    },
    mounted:function () {
        this.userList(1)
    },
    methods:{
        userList:function (start) {
            var url = "/allUsers";
            var self = this;
            var orderBy = self.ob;
            axios.get(url,{params:{start:start,orderBy:orderBy}}).then(function(response){
                var result = response.data;
                self.users = result.users;
                self.pagination = result.page;
            })
        },
        jumpByNumber:function (start) {
            var self = this;
            if( start != self.pagination.pageNum ){
                self.userList(start);
            };
        },
        jumpPage:function (page) {
            var self = this;
            if( page == 'pre' && self.pagination.hasPreviousPage ){
                self.userList( self.pagination.prePage );
            }else if( page == 'next' && self.pagination.hasNextPage ){
                self.userList( self.pagination.nextPage );
            };
        },
        changeUserInfo:function (user) {
            var self = this;
            self.tempUserInfo.user_authority = user.user_authority;
            self.tempUserInfo.user_department = user.user_department;
            self.tempUserInfo.user_name = user.user_name;
            self.tempUserInfo.user_phone = user.user_phone;
            self.tempUserInfo.user_truename = user.user_truename;
        },
        searchU:function () {
            var self = this;
            var url = "/Search?s="+self.searchUser;
            axios.get(url).then(function(response){
                var result = response.data;
                self.users = result.users;
                self.pagination = result.page;
            })
        },
        sortUser:function(orderBy){
            var self = this;
            self.ob = orderBy;
            self.userList();
        },
        deleteUser:function (user) {
            var self = this;
            var url = "/delete/"+user.user_name;
            var id = '#'+user.user_id;
            axios.delete(url).then(function(response){
                var result = response.data;
                if( result.result ){
                    self.message.info = result.info;
                    $(id).collapse('toggle');
                    $('#deleteInfoS').removeClass('hide').addClass('in');
                    setTimeout(function(){$('#deleteInfoS').removeClass('in').addClass('hide');location.reload();},2000);
                }else{
                    self.message.info = result.info;
                    $('#deleteInfoF').removeClass('hide').addClass('in');
                    setTimeout(function(){$('#deleteInfoF').removeClass('in').addClass('hide')},3000);
                }
            })
        },
    }

});

/* 修改用户信息modal框 */
var m_userChangeModal = new Vue({
    el:'#userChangeModal',
    data:{
        tempUserInfo,
        message,
    },
    methods:{
        changeUser:function () {
            var self = this;
            var url = '/update/'+self.tempUserInfo.user_name;
            axios.put(url,self.tempUserInfo).then(function(response){
                var result = response.data;
                self.message.info = result.info;
                if(result.result){
                    $('#changeUserS').removeClass('hide').addClass('in');
                    setTimeout(function(){$('#changeUserS').removeClass('in').addClass('hide');$('#userChangeModal').modal('toggle');location.reload();},1500);
                }else {
                    $('#changeUserF').removeClass('hide').addClass('in')
                    setTimeout(function(){$('#changeUserF').removeClass('in').addClass('hide')},3000);
                }
            })
        }
    }

});

/* 增加用户modal框 */
var m_userAddModal = new Vue({
    el:'#addUserModal',
    data:{
        addUser:{
            user_name:'',
            user_password:'',
            user_truename:'',
            user_phone:'',
            user_department:'',
            user_authority:1,
        },
        message,
    },
    methods:{
        addNewUser:function () {
            var url = "/adduser";
            var self = this;
            axios.post(url,self.addUser).then(function(response){
                var result = response.data;
                self.message.info = result.info;
                if(result.result){
                    self.clearInput();
                    self.isInputEmpty();
                    $('#addUserS').removeClass('hide').addClass('in');
                    setTimeout(function(){$('#addUserS').removeClass('in').addClass('hide');$('#addUserModal').modal('toggle');location.reload();},1000);
                }else {
                    $('#addUserF').removeClass('hide').addClass('in')
                    setTimeout(function(){$('#addUserF').removeClass('in').addClass('hide')},3000);
                }
            })
            /*todo 输入限制*/
        },
        clearInput:function(){
            var self = this;
            self.addUser.user_name = '';
            self.addUser.user_password = '';
            self.addUser.user_department = '';
            self.addUser.user_phone = '';
            self.addUser.user_truename = '';
            self.addUser.user_authority = 1;
            $("#add").attr("disabled","disabled");
            $("#add").attr("title","请填写完带*的项");
        },
        isInputEmpty:function () {
            var self = this;
            if( self.addUser.user_name != '' && self.addUser.user_password != '' && self.addUser.user_truename != '' && self.addUser.user_department != '' && self.addUser.user_authority != '' ){
                $("#add").removeAttr("title","请填写完带*的项");
                $("#add").removeAttr("disabled","disabled");
            }else {
                $("#add").attr("disabled","disabled");
                $("#add").attr("title","请填写完带*的项");

            }
        },
    }

});

