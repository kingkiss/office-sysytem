/* 用户信息数据  */
var userdata = {
    user_name:'',
    user_truename:'',
    user_department:'',
    user_phone:'',
    user_authority:'',
    user_position:'',
    user_id:'',
    show_7:false,
    show_3:false,
};
/* 各种信息流，提示信息 */
var message = {
    show:false,
    info:'',
};

/*修改用户信息暂存数据*/
var tempUserInfo = {
    user_name:'',
    user_department:'',
    user_phone:'',
    user_truename:'',
    user_authority:'',
};

/*模块功能显示通用数据（modalShowLeft&Right）*/
var modalShowData = {
    m_officelist:true,
    m_officeInfo:false,
    m_userInfo:false,
    m_applyCheck:false,
    m_applylist:false,
    m_borrowlist:false,
    m_returnlist:false,
    m_usedata:false,
};

/*申请物品信息暂存数据*/
var tempProductInfo = {
    product_id:'',
    product_name:'',
    product_num:0,
    product_price:0,
    product_type:'',
    apply_num:1,
};

/*物品类型信息暂存数据*/
var tempTypeInfo = {
    type_id:'',
    type_category:'文具事务用品',
    product_type:'',
};

/*申请审核信息暂存数据*/
var tempApplyCheckInfo = {
    apply_id:'',
    apply_user_id:'',
    apply_user_truename:'',
    apply_num:'',
    apply_product_id:'',
    apply_product_name:'',
    apply_product_price:'',
    apply_passman_id:'',
    apply_passman_name:'',
    apply_pass:0,
    apply_time:''
};

/*归还记录信息暂存数据*/
var tempBorrowListInfo = {
    borrowinfo_id:'',
    borrowinfo_user_id:'',
    borrowinfo_user_truename:'',
    borrowinfo_product_id:'',
    borrowinfo_product_name:'',
    borrowinfo_product_price:'',
    borrowinfo_num:'',
    borrowinfo_time:'',
    borrowinfo_return:0,
    borrowinfo_return_num:0,
    borrowinfo_missprice:0,
    authority:'',
};