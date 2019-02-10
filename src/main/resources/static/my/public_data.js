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

