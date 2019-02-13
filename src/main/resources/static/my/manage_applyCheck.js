
/*申请审核模块数据处理(m_applyCheck框中数据)*/

/*申请审核模块数据流处理*/
var m_applyCheck = new Vue({
    el:'#allApplyCheck',
    data:{
        message,
        userdata,
        tempApplyCheckInfo,
        searchApply:'',
        applyChecks:[],
        pagination:{},
    },
    mounted:function(){
        this.getAllApplyCheck(1);
    },
    methods:{
        getAllApplyCheck:function (start) {
            var self = this;
            var url = '/allApplyCheck';
            axios.get(url,{params:{start:start}}).then(function(response){
                var result = response.data;
                self.applyChecks = result.AllApplyChecks;
                self.pagination = result.page;

            })
        },
        jumpByNumber:function (start) {
            var self = this;
            if( start != self.pagination.pageNum ){
                self.getAllApplyCheck(start);
            };
        },
        jumpPage:function (page) {
            var self = this;
            if( page == 'pre' && self.pagination.hasPreviousPage ){
                self.getAllApplyCheck( self.pagination.prePage );
            }else if( page == 'next' && self.pagination.hasNextPage ){
                self.getAllApplyCheck( self.pagination.nextPage );
            };
        },
        searchApplyCheck:function () {
            var self = this;
            var url = "/SearchApplyCheck";
            axios.get(url,{params:{applyCheck:self.searchApply}}).then(function(response){
                var result = response.data;
                self.applyChecks = result.SearchApplyChecks;
                self.pagination = result.page;
            })
        },
        addApplyTemp:function (applyCheck) {
            var self = this;
            self.tempApplyCheckInfo.apply_id = applyCheck.apply_id;
            self.tempApplyCheckInfo.apply_num = applyCheck.apply_num;
            self.tempApplyCheckInfo.apply_passman_id = userdata.user_id;
            self.tempApplyCheckInfo.apply_passman_name = userdata.user_truename;
            self.tempApplyCheckInfo.apply_user_id = applyCheck.apply_user_id;
            self.tempApplyCheckInfo.apply_user_truename = applyCheck.apply_user_truename;
            self.tempApplyCheckInfo.apply_product_id = applyCheck.apply_product_id;
            self.tempApplyCheckInfo.apply_product_name = applyCheck.apply_product_name;
            self.tempApplyCheckInfo.apply_product_price = applyCheck.apply_product_price;
        }
    },

})

/*通过申请modal框数据处理*/
var passApplyModal = new Vue({
    el:'#passApplyModal',
    data:{
        message,
        userdata,
        tempApplyCheckInfo,
    },
    methods:{
        clearTemp:function () {
            self.tempApplyCheckInfo.apply_id = '';
            self.tempApplyCheckInfo.apply_num = '';
            self.tempApplyCheckInfo.apply_passman_id = '';
            self.tempApplyCheckInfo.apply_passman_name = '';
            self.tempApplyCheckInfo.apply_user_truename = '';
            self.tempApplyCheckInfo.apply_product_name = '';
            self.tempApplyCheckInfo.apply_product_price = '';
            self.tempApplyCheckInfo.apply_pass = 0;
        },
        passA:function () {
            var self = this;
            var url = '/sumbitApplyCheck';
            self.tempApplyCheckInfo.apply_pass = 1;
            axios.post(url,self.tempApplyCheckInfo).then(function(response){
                var result = response.data;
                self.message.info = result.info;
                if(result.result){
                    $('#passApplyS').removeClass('hide').addClass('in');
                    setTimeout(function(){$('#passApplyS').removeClass('in').addClass('hide');$('#passApplyModal').modal('toggle');location.reload();},1000);
                }else {
                    $('#passApplyF').removeClass('hide').addClass('in')
                    setTimeout(function(){$('#passApplyF').removeClass('in').addClass('hide')},3000);
                }
            })
        }
    }
});

/*通过申请modal框数据处理*/
var rejectApplyModal = new Vue({
    el:'#rejectApplyModal',
    data:{
        message,
        userdata,
        tempApplyCheckInfo,
    },
    methods:{
        clearTemp:function () {
            self.tempApplyCheckInfo.apply_id = '';
            self.tempApplyCheckInfo.apply_num = '';
            self.tempApplyCheckInfo.apply_passman_id = '';
            self.tempApplyCheckInfo.apply_passman_name = '';
            self.tempApplyCheckInfo.apply_user_truename = '';
            self.tempApplyCheckInfo.apply_product_name = '';
            self.tempApplyCheckInfo.apply_product_price = '';
        },
        rejectA:function () {
            var self = this;
            var url = '/sumbitApplyCheck';
            self.tempApplyCheckInfo.apply_pass = 2;
            axios.post(url,self.tempApplyCheckInfo).then(function(response){
                var result = response.data;
                self.message.info = result.info;
                if(result.result){
                    $('#rejectApplyS').removeClass('hide').addClass('in');
                    setTimeout(function(){$('#rejectApplyS').removeClass('in').addClass('hide');$('#rejectApplyModal').modal('toggle');location.reload();},1000);
                }else {
                    $('#rejectApplyF').removeClass('hide').addClass('in')
                    setTimeout(function(){$('#rejectApplyF').removeClass('in').addClass('hide')},3000);
                }
            })
        },
    }
});

