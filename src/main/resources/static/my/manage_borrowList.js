
/*借入/归还记录模块数据处理（m_returnList）*/
var m_borrowList = new Vue({
    el:'#allBorrowList',
    data:{
        message,
        userdata,
        tempBorrowListInfo,
        searchBorrow:'',
        borrowLists:[],
        pagination:{},
    },
    mounted:function () {
        this.getAllBorrowList(1)

    },
    methods:{
        getAllBorrowList:function (start) {
            var self = this;
            var url = '/allBorrowList?start='+start;
            axios.get(url).then(function(response){
                var result = response.data;
                self.borrowLists = result.AllBorrowLists;
                self.pagination = result.page;

            })
        },
        jumpByNumber:function (start) {
            var self = this;
            if( start != self.pagination.pageNum ){
                self.getAllBorrowList(start);
            };
        },
        jumpPage:function (page) {
            var self = this;
            if( page == 'pre' && self.pagination.hasPreviousPage ){
                self.getAllBorrowList( self.pagination.prePage );
            }else if( page == 'next' && self.pagination.hasNextPage ){
                self.getAllBorrowList( self.pagination.nextPage );
            };
        },
        searchBorrowList:function () {
            var self = this;
            var url = "/SearchBorrowList";
            axios.get(url,{params:{borrowList:self.searchBorrow}}).then(function(response){
                var result = response.data;
                self.borrowLists = result.SearchBorrowLists;
                self.pagination = result.page;
            })
        },
        addBorrowinfoToTemp:function (borrow) {
            var self = this;
            self.tempBorrowListInfo.borrowinfo_id = borrow.borrowinfo_id;
            self.tempBorrowListInfo.borrowinfo_user_id = borrow.borrowinfo_user_id;
            self.tempBorrowListInfo.borrowinfo_user_truename = borrow.borrowinfo_user_truename;
            self.tempBorrowListInfo.borrowinfo_product_id = borrow.borrowinfo_product_id;
            self.tempBorrowListInfo.borrowinfo_num = borrow.borrowinfo_num;
            self.tempBorrowListInfo.borrowinfo_product_name = borrow.borrowinfo_product_name;
            self.tempBorrowListInfo.borrowinfo_product_price = borrow.borrowinfo_product_price;
            self.tempBorrowListInfo.borrowinfo_time = borrow.borrowinfo_time;
        },
    }
});

/*归还确认modal框数据处理*/
var returnModal = new Vue({
    el:'#borrowReturnModal',
    data:{
        message,
        userdata,
        tempBorrowListInfo,
    },
    methods:{
        clearTemp:function () {
            var self = this;
            self.tempBorrowListInfo.borrowinfo_id = '';
            self.tempBorrowListInfo.borrowinfo_user_id = '';
            self.tempBorrowListInfo.borrowinfo_user_truename = '';
            self.tempBorrowListInfo.borrowinfo_product_id = '';
            self.tempBorrowListInfo.borrowinfo_product_name = '';
            self.tempBorrowListInfo.borrowinfo_product_price = '';
            self.tempBorrowListInfo.borrowinfo_num = '';
            self.tempBorrowListInfo.borrowinfo_time = '';
            self.tempBorrowListInfo.borrowinfo_return = 0;
            self.tempBorrowListInfo.borrowinfo_return_num = 0;
            self.tempBorrowListInfo.borrowinfo_missprice = 0;
        },
        returnBorrowList:function () {
            var self = this;
            var url = '/updateBorrow';
            self.tempBorrowListInfo.borrowinfo_return = 1;
            axios.post(url,self.tempBorrowListInfo).then(function (response) {
                var result = response.data;
                self.message.info = result.info;
                if(result.result){
                    console.log(result);
                    $('#borrowReturnS').removeClass('hide').addClass('in');
                    $('.btn-danger').attr("disabled","disabled");
                    setTimeout(function(){
                        $('#borrowReturnS').removeClass('in').addClass('hide');
                        $('#borrowReturnModal').modal('toggle');
                        $('.btn-danger').removeAttr("disabled","disabled");
                        location.reload();
                    },1500);
                }else {
                    $('#borrowReturnF').removeClass('hide').addClass('in');
                    setTimeout(function(){$('#borrowReturnF').removeClass('in').addClass('hide')},3000);
                }
            })
        }
    },
    computed:{
        missPrice:function () {
            var self = this;
            self.tempBorrowListInfo.borrowinfo_missprice = (self.tempBorrowListInfo.borrowinfo_num*self.tempBorrowListInfo.borrowinfo_product_price)
                -(self.tempBorrowListInfo.borrowinfo_return_num*self.tempBorrowListInfo.borrowinfo_product_price);
            /*if(self.tempBorrowListInfo.borrowinfo_missprice<0){
                self.tempBorrowListInfo.borrowinfo_missprice = self.tempBorrowListInfo.borrowinfo_num*self.tempBorrowListInfo.borrowinfo_product_price;
                return self.tempBorrowListInfo.borrowinfo_missprice
            }*/
            return self.tempBorrowListInfo.borrowinfo_missprice
        },
    }
});

/*挂失确认modal框数据处理*/
var missModal = new Vue({
    el:'#borrowMissModal',
    data:{
        message,
        userdata,
        tempBorrowListInfo,
    },
    methods:{
        clearTemp:function () {
            var self = this;
            self.tempBorrowListInfo.borrowinfo_id = '';
            self.tempBorrowListInfo.borrowinfo_user_id = '';
            self.tempBorrowListInfo.borrowinfo_user_truename = '';
            self.tempBorrowListInfo.borrowinfo_product_id = '';
            self.tempBorrowListInfo.borrowinfo_product_name = '';
            self.tempBorrowListInfo.borrowinfo_product_price = '';
            self.tempBorrowListInfo.borrowinfo_num = '';
            self.tempBorrowListInfo.borrowinfo_time = '';
            self.tempBorrowListInfo.borrowinfo_return = 0;
            self.tempBorrowListInfo.borrowinfo_return_num = 0;
            self.tempBorrowListInfo.borrowinfo_missprice = 0;
        },
        missBorrowList:function () {
            var self = this;
            var url = '/updateBorrow';
            self.tempBorrowListInfo.borrowinfo_return = 2;
            axios.post(url,self.tempBorrowListInfo).then(function (response) {
                var result = response.data;
                self.message.info = result.info;
                if(result.result){
                    $('#borrowMissS').removeClass('hide').addClass('in');
                    $('.btn-danger').attr("disabled","disabled");
                    setTimeout(function(){
                        $('#borrowMissS').removeClass('in').addClass('hide');
                        $('#borrowMissModal').modal('toggle');
                        $('.btn-danger').removeAttr("disabled","disabled");
                        location.reload();
                    },1500);
                }else {
                    $('#borrowMissF').removeClass('hide').addClass('in');
                    setTimeout(function(){$('#borrowMissF').removeClass('in').addClass('hide')},3000);
                }
            })
        }
    }

});

/*删除确认modal框数据处理*/
var deleteBorrowModal = new Vue({
    el:'#borrowDeleteModal',
    data:{
        message,
        userdata,
        tempBorrowListInfo,
    },
    methods:{
        clearTemp:function () {
            var self = this;
            self.tempBorrowListInfo.borrowinfo_id = '';
            self.tempBorrowListInfo.borrowinfo_user_id = '';
            self.tempBorrowListInfo.borrowinfo_user_truename = '';
            self.tempBorrowListInfo.borrowinfo_product_id = '';
            self.tempBorrowListInfo.borrowinfo_product_name = '';
            self.tempBorrowListInfo.borrowinfo_product_price = '';
            self.tempBorrowListInfo.borrowinfo_num = '';
            self.tempBorrowListInfo.borrowinfo_time = '';
            self.tempBorrowListInfo.borrowinfo_return = 0;
            self.tempBorrowListInfo.borrowinfo_return_num = 0;
            self.tempBorrowListInfo.borrowinfo_missprice = 0;
        },
        deleteBorrow:function () {
            var self = this;
            var url = "/deleteBorrow/"+self.tempBorrowListInfo.borrowinfo_id;
            axios.delete(url).then(function(response) {
                var result = response.data;
                self.message.info = result.info;
                if(result.result){
                    console.log(result);
                    $('#borrowDeleteS').removeClass('hide').addClass('in');
                    $('.btn-danger').attr("disabled","disabled");
                    setTimeout(function(){
                        $('#borrowDeleteS').removeClass('in').addClass('hide');
                        $('#borrowDeleteModal').modal('toggle');
                        $('.btn-danger').removeAttr("disabled","disabled");
                        location.reload();
                    },1500);
                }else {
                    $('#borrowDeleteF').removeClass('hide').addClass('in');
                    setTimeout(function(){$('#borrowDeleteF').removeClass('in').addClass('hide')},3000);
                }
            });
        }
    },
});
