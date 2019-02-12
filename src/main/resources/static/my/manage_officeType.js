/*物品类型管理数据流处理*/
var m_officeType = new Vue({
    el:'#allTypeInfo',
    data:{
        message,
        tempTypeInfo,
        TypeInfos:[],
        pagination:{},
    },
    mounted:function () {
        this.getType(1);
    },
    methods:{
        getType:function (start) {
            var self = this;
            var url = '/TypeInfo';
            axios.get(url,{params:{start:start}}).then(function(response){
                var result = response.data;
                self.TypeInfos = result.TypeInfos;
                self.pagination = result.page;
            })
        },
        jumpByNumber:function (start) {
            var self = this;
            if( start != self.pagination.pageNum ){
                self.getType(start);
            };
        },
        jumpPage:function (page) {
            var self = this;
            if( page == 'pre' && self.pagination.hasPreviousPage ){
                self.getType( self.pagination.prePage );
            }else if( page == 'next' && self.pagination.hasNextPage ){
                self.getType( self.pagination.nextPage );
            };
        },
        allStationeryList:function () {
            var self = this;
            var url = '/typeCategory';
            axios.get(url,{params:{category:'文具事务用品'}}).then(function(response){
                var result = response.data;
                self.TypeInfos = result.typeCategory;
                self.pagination = result.page;
            })

        },
        allConsumableList:function () {
            var self = this;
            var url = '/typeCategory';
            axios.get(url,{params:{category:'办公耗材'}}).then(function(response){
                var result = response.data;
                self.TypeInfos = result.typeCategory;
                self.pagination = result.page;
            })
        },
        allEquipmentList:function () {
            var self = this;
            var url = '/typeCategory';
            axios.get(url,{params:{category:'办公设备'}}).then(function(response){
                var result = response.data;
                self.TypeInfos = result.typeCategory;
                self.pagination = result.page;
            })
        },
        deleteTypeToTemp:function (type) {
            var self = this;
            self.tempTypeInfo.type_id = type.type_id;
            self.tempTypeInfo.type_category = type.type_category;
            self.tempTypeInfo.product_type = type.product_type;
        },

    }


})

/*添加类型modal框*/
var m_addTypeModal = new Vue({
    el:'#addTypeModal',
    data:{
        message,
        tempTypeInfo,
    },
    methods:{
        addNewType:function(){
            var self = this;
            var url = '/addType';
            if(self.tempTypeInfo.type_category!='' && self.tempTypeInfo.product_type!=''){
                axios.post(url,self.tempTypeInfo).then(function (response) {
                    var result = response.data;
                    self.message.info = result.info;
                    if( result.result ){
                        console.log(result);
                        $('#addTypeS').removeClass('hide').addClass('in')
                        setTimeout(function(){$('#addTypeS').removeClass('in').addClass('hide');$('#addTypeModal').modal('toggle');location.reload();},1500);
                    }else{
                        $('#addTypeF').removeClass('hide').addClass('in')
                        setTimeout(function(){$('#addTypeF').removeClass('in').addClass('hide')},3000);
                    }
                });
            }else{
                $('#addTypeF').removeClass('hide').addClass('in')
                setTimeout(function(){$('#addTypeF').removeClass('in').addClass('hide')},3000);
            }
        },
        clearTemp:function () {
            self.tempTypeInfo.type_id = '';
            self.tempTypeInfo.type_category= '文具事务用品';
            self.tempTypeInfo.product_type = '';
        },
    }

})

/*删除类型modal框*/
var m_deleteTypeModal = new Vue({
    el:'#deleteTypeModal',
    data:{
        message,
        tempTypeInfo,
    },
    methods:{
        deleteType:function(){
            var self = this;
            var url = "/deleteType/"+tempTypeInfo.type_id;
            axios.delete(url).then(function(response) {
                var result = response.data;
                self.message.info = result.info;
                if(result.result){
                    console.log(result);
                    $('#deleteTypeS').removeClass('hide').addClass('in');
                    setTimeout(function(){$('#deleteTypeS').removeClass('in').addClass('hide');$('#deleteTypeModal').modal('toggle');location.reload();},1500);
                }else {
                    $('#deleteTypeF').removeClass('hide').addClass('in');
                    setTimeout(function(){$('#deleteTypeF').removeClass('in').addClass('hide')},3000);
                }
            });
        },
        clearTemp:function () {
            self.tempTypeInfo.type_id = '';
            self.tempTypeInfo.type_category= '文具事务用品';
            self.tempTypeInfo.product_type = '';
        },
    },
})

