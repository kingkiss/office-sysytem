/*m_officeInfo物品管理模块数据处理*/
var m_officeInfo = new Vue({
    el:'#allProduct',
    data:{
        message,
        userdata,
        tempProductInfo,
        searchProduct:'',
        products:[],
        pagination:{},
    },
    mounted:function(){
        this.productList(1);
    },
    methods:{
        productList:function (start) {
            var url = "/allProduct";
            var self = this;
            axios.get(url,{params:{start:start}}).then(function(response){
                var result = response.data;
                self.products = result.AllProducts;
                self.pagination = result.page;
            })
        },
        jumpByNumber:function (start) {
            var self = this;
            if( start != self.pagination.pageNum ){
                self.productList(start);
            };
        },
        jumpPage:function (page) {
            var self = this;
            if( page == 'pre' && self.pagination.hasPreviousPage ){
                self.productList( self.pagination.prePage );
            }else if( page == 'next' && self.pagination.hasNextPage ){
                self.productList( self.pagination.nextPage );
            };
        },
        searchProductList:function () {
            var self = this;
            var url = "/SearchProduct";
            axios.get(url,{params:{product:self.searchProduct}}).then(function(response){
                var result = response.data;
                self.products = result.SearchProduct;
                self.pagination = result.page;
            })
        },
        /*添加数据到申请物品暂存*/
        addTempProduct: function (product) {
            var self = this;
            self.tempProductInfo.product_name = product.product_name;
            self.tempProductInfo.product_num = product.product_num;
            self.tempProductInfo.product_price = product.product_price;
            self.tempProductInfo.product_type = product.product_type;
            self.tempProductInfo.product_id = product.product_id;
        },
        /*删除物品modal框*/
        deleteProductModal:function (product) {
            var self = this;
            self.tempProductInfo.product_name = product.product_name;
            self.tempProductInfo.product_num = product.product_num;
            self.tempProductInfo.product_price = product.product_price;
            self.tempProductInfo.product_type = product.product_type;
            self.tempProductInfo.product_id = product.product_id;
        },
    }
});

/*修改物品modal框数据处理*/
var m_changeProduct = new Vue({
    el:'#changeProduct',
    data:{
        message,
        userdata,
        tempProductInfo,
        Types:[],
    },
    mounted:function(){
        this.getType();
    },
    methods: {
        getType:function () {
            var self = this;
            var url = '/allType';
            axios.get(url).then(function (response) {
                var result = response.data;
                self.Types = result.AllType;
            })
        },
        clearTemp:function () {
            self.tempProductInfo.product_name = '';
            self.tempProductInfo.product_num = 0;
            self.tempProductInfo.product_price = 0;
            self.tempProductInfo.product_type = '';
            self.tempProductInfo.product_id = '';
        },
        changeP:function () {
            var self = this;
            var url = '/updateProduct/'+self.tempProductInfo.product_id;
            axios.put(url,self.tempProductInfo).then(function(response) {
                var result = response.data;
                self.message.info = result.info;
                if(result.result){
                    $('#changeProductS').removeClass('hide').addClass('in');
                    setTimeout(function(){$('#changeProductS').removeClass('in').addClass('hide');$('#changeProduct').modal('toggle');location.reload();},1500);
                }else {
                    $('#changeProductF').removeClass('hide').addClass('in')
                    setTimeout(function(){$('#changeProductF').removeClass('in').addClass('hide')},3000);
                }
            });
        }
    }
});

/*添加物品modal框数据处理*/
var m_addProduct = new Vue({
    el:'#addProduct',
    data:{
        message,
        tempProductInfo,
        Types:[],
    },
    mounted:function(){
        this.getType();
    },
    methods:{
        clearTemp:function () {
            self.tempProductInfo.product_name = '';
            self.tempProductInfo.product_num = 0;
            self.tempProductInfo.product_price = 0;
            self.tempProductInfo.product_type = '';
            self.tempProductInfo.product_id = '';
        },
        getType:function () {
            var self = this;
            var url = '/allType';
            axios.get(url).then(function (response) {
                var result = response.data;
                self.Types = result.AllType;
            })
        },
        addNewProduct:function () {
            var self = this;
            var url = '/addProduct';
            var param = {
                product_name:self.tempProductInfo.product_name,
                product_num:self.tempProductInfo.product_num,
                product_price:self.tempProductInfo.product_price,
                product_type:self.tempProductInfo.product_type,
            };
            if( tempProductInfo.product_name!='' && tempProductInfo.product_type!=''){
                console.log("非空测试");
                axios.post(url,param).then(function (response) {
                    var result = response.data;
                    self.message.info = result.info;
                    if( result.result ){
                        console.log(result);
                        $('#addProductS').removeClass('hide').addClass('in')
                        setTimeout(function(){$('#addProductS').removeClass('in').addClass('hide');$('#addProduct').modal('toggle');location.reload();},1500);
                    }else{
                        $('#addProductF').removeClass('hide').addClass('in')
                        setTimeout(function(){$('#addProductF').removeClass('in').addClass('hide')},3000);
                    }
                });
            }else {
                self.message.info = "名称或类型不能为空";
                $('#addProductF').removeClass('hide').addClass('in')
                setTimeout(function(){$('#addProductF').removeClass('in').addClass('hide')},3000);
            }
        },
    }
});

/*删除物品确认modal框*/
var m_deleteProduct = new Vue({
    el:'#deleteProductModal',
    data:{
        message,
        tempProductInfo,
    },
    methods:{
        clearTemp:function () {
            self.tempProductInfo.product_name = '';
            self.tempProductInfo.product_num = 0;
            self.tempProductInfo.product_price = 0;
            self.tempProductInfo.product_type = '';
            self.tempProductInfo.product_id = '';
        },
        /*提交删除*/
        deleteProductS:function () {
            var self = this;
            var url = "/deleteProduct/"+tempProductInfo.product_id;
            axios.delete(url).then(function(response) {
                var result = response.data;
                self.message.info = result.info;
                if(result.result){
                    console.log(result);
                    $('#deleteProductS').removeClass('hide').addClass('in');
                    setTimeout(function(){$('#deleteProductS').removeClass('in').addClass('hide');$('#deleteProductModal').modal('toggle');location.reload();},1500);
                }else {
                    $('#deleteProductF').removeClass('hide').addClass('in');
                    setTimeout(function(){$('#deleteProductF').removeClass('in').addClass('hide')},3000);
                }
            });
        }
    },
})