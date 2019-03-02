
/*文具事务用品数据表格stationery_category(m_officelist框中数据)*/
var stationery = new Vue({
    el:'#stationery_category',
    data:{
        message,
        tempProductInfo,
        searchStationeryProduct:'',
        stationeries:[],
        pagination:{},
    },
    mounted:function () {
        this.stationeryList(1);

    },
    methods:{
        stationeryList:function (start) {
            var url = "/allStationery";
            var self = this;
            axios.get(url,{params:{start:start}}).then(function(response){
                var result = response.data;
                self.stationeries = result.products;
                self.pagination = result.page;
            })
        },
        jumpByNumber:function (start) {
            var self = this;
            if( start != self.pagination.pageNum ){
                self.stationeryList(start);
            };
        },
        jumpPage:function (page) {
            var self = this;
            if( page == 'pre' && self.pagination.hasPreviousPage ){
                self.stationeryList( self.pagination.prePage );
            }else if( page == 'next' && self.pagination.hasNextPage ){
                self.stationeryList( self.pagination.nextPage );
            };
        },
        searchStationery:function () {
            var self = this;
            var url = "/SearchOffice";
            axios.get(url,{params:{type:'文具事务用品',product:self.searchStationeryProduct}}).then(function(response){
                var result = response.data;
                self.stationeries = result.SearchProduct;
                self.pagination = result.page;
            })
        },
        /*添加数据到申请物品暂存*/
        applyStationeryProduct:function (product) {
            var self = this;
            self.tempProductInfo.product_name = product.product_name;
            self.tempProductInfo.product_num = product.product_num;
            self.tempProductInfo.product_price = product.product_price;
            self.tempProductInfo.product_type = product.product_type;
            self.tempProductInfo.product_id = product.product_id;
        }
    }

});

/*办公耗材数据表格consumable_category(m_officelist框中数据)*/
var consumable = new Vue({
    el:'#consumable_category',
    data:{
        message,
        tempProductInfo,
        searchConsumableProduct:'',
        consumables:[],
        pagination:{},
    },
    mounted:function () {
        this.consumableList(1)
    },
    methods:{
        consumableList:function (start) {
            var url = "/allConsumable";
            var self = this;
            axios.get(url,{params:{start:start}}).then(function(response){
                var result = response.data;
                self.consumables = result.products;
                self.pagination = result.page;
            })
        },
        jumpByNumber:function (start) {
            var self = this;
            if( start != self.pagination.pageNum ){
                self.consumableList(start);
            };
        },
        jumpPage:function (page) {
            var self = this;
            if( page == 'pre' && self.pagination.hasPreviousPage ){
                self.consumableList( self.pagination.prePage );
            }else if( page == 'next' && self.pagination.hasNextPage ){
                self.consumableList( self.pagination.nextPage );
            };
        },
        searchConsumable:function () {
            var self = this;
            var url = "/SearchOffice";
            axios.get(url,{params:{type:'办公耗材',product:self.searchConsumableProduct}}).then(function(response){
                var result = response.data;
                self.consumables = result.SearchProduct;
                self.pagination = result.page;
            })
        },
        applyConsumableProduct:function (product) {
            var self = this;
            self.tempProductInfo.product_name = product.product_name;
            self.tempProductInfo.product_num = product.product_num;
            self.tempProductInfo.product_price = product.product_price;
            self.tempProductInfo.product_type = product.product_type;
            self.tempProductInfo.product_id = product.product_id;
        }
    }
});

/*办公设备数据表格equipment_category(m_officelist框中数据)*/
var equipment = new Vue({
    el:'#equipment_category',
    data:{
        message,
        tempProductInfo,
        searchEquipmentProduct:'',
        equipments:[],
        pagination:{},
    },
    mounted:function () {
        this.equipmentList(1);
    },
    methods:{
        equipmentList:function (start) {
            var url = "/allEquipment";
            var self = this;
            axios.get(url,{params:{start:start}}).then(function(response){
                var result = response.data;
                self.equipments = result.products;
                self.pagination = result.page;

            })
        },
        jumpByNumber:function (start) {
            var self = this;
            if( start != self.pagination.pageNum ){
                self.equipmentList(start);
            };
        },
        jumpPage:function (page) {
            var self = this;
            if( page == 'pre' && self.pagination.hasPreviousPage ){
                self.equipmentList( self.pagination.prePage );
            }else if( page == 'next' && self.pagination.hasNextPage ){
                self.equipmentList( self.pagination.nextPage );
            };
        },
        searchEquipment:function () {
            var self = this;
            var url = "/SearchOffice";
            axios.get(url,{params:{type:'办公设备',product:self.searchEquipmentProduct}}).then(function(response){
                var result = response.data;
                self.equipments = result.SearchProduct;
                self.pagination = result.page;
            })
        },
        applyEquipmentProduct:function (product) {
            var self = this;
            self.tempProductInfo.product_name = product.product_name;
            self.tempProductInfo.product_num = product.product_num;
            self.tempProductInfo.product_price = product.product_price;
            self.tempProductInfo.product_type = product.product_type;
            self.tempProductInfo.product_id = product.product_id;
        }
    }
});

/*物品申请modal框数据处理*/
var m_officeApplyModal = new Vue({
    el:'#applyModal',
    data:{
        tempProductInfo,
        userdata,
        message,
    },
    methods:{
        submitApply:function () {
            var self = this;
            var url = '/addApply';
            var nowDate = new Date();
            console.log(tempProductInfo.product_id);
            var param = {
                apply_user_id:self.userdata.user_id,
                apply_user_truename:self.userdata.user_truename,
                apply_user_name:self.userdata.user_name,
                apply_product_name:self.tempProductInfo.product_name,
                apply_num:self.tempProductInfo.apply_num,
                apply_product_price:self.tempProductInfo.product_price,
                apply_product_id:self.tempProductInfo.product_id
            };
            axios.post(url,param).then(function (response) {
                var result = response.data;
                self.message.info = result.info;
                if(result.result){
                    $('#addApplyS').removeClass('hide').addClass('in');
                    setTimeout(function(){$('#addApplyS').removeClass('in').addClass('hide');$('#applyModal').modal('toggle');location.reload();},1500);
                }else{
                    $('#addApplyS').removeClass('hide').addClass('in');
                    setTimeout(function(){$('#addApplyS').removeClass('in').addClass('hide')},3000);
                }
            })
        }
    }
});

