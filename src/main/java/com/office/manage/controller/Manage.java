package com.office.manage.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/*各个模块的页面跳转控制*/

@Controller
public class Manage {
	//登录入口
    @RequestMapping("/login")
    public String login(HttpServletRequest request,Model m){
        HttpSession session = request.getSession();
        if( session.getAttribute("user_id") != null ){
            boolean isUserInfo = false;
            boolean isOfficeList = true;
            boolean isOfficeInfo = false;
            boolean isOfficeType = false;
            boolean isApplyCheck = false;
            boolean isApplyList = false;
            boolean isBorrowList = false;
            boolean isReturnList = false;
            boolean isUseData = false;

            m.addAttribute("isUserInfo",isUserInfo);
            m.addAttribute("isOfficeList",isOfficeList);
            m.addAttribute("isOfficeInfo",isOfficeInfo);
            m.addAttribute("isOfficeType",isOfficeType);
            m.addAttribute("isApplyCheck",isApplyCheck);
            m.addAttribute("isApplyList",isApplyList);
            m.addAttribute("isBorrowList",isBorrowList);
            m.addAttribute("isReturnList",isReturnList);
            m.addAttribute("isUseData",isUseData);
            return "manage";
        }
        return "login";
    }

    //退出登录
    @RequestMapping("/loginout")
    public String loginout(HttpServletRequest request){
    	HttpSession session = request.getSession();
    	session.invalidate();
    	return "login";
    }

    //页面跳转到管理主页面(物品申请)
    @RequestMapping("/manage")
    public String manage(Model m){
        boolean isUserInfo = false;
        boolean isOfficeList = true;
        boolean isOfficeInfo = false;
        boolean isOfficeType = false;
        boolean isApplyCheck = false;
        boolean isApplyList = false;
        boolean isBorrowList = false;
        boolean isReturnList = false;
        boolean isUseData = false;



        m.addAttribute("isUserInfo",isUserInfo);
        m.addAttribute("isOfficeList",isOfficeList);
        m.addAttribute("isOfficeInfo",isOfficeInfo);
        m.addAttribute("isOfficeType",isOfficeType);
        m.addAttribute("isApplyCheck",isApplyCheck);
        m.addAttribute("isApplyList",isApplyList);
        m.addAttribute("isBorrowList",isBorrowList);
        m.addAttribute("isReturnList",isReturnList);
        m.addAttribute("isUseData",isUseData);
        return "manage";
    }

    //用户管理模块的页面(用户管理)
    @RequestMapping("/manage_userInfo")
    public String manage_userInfo(Model m,HttpServletRequest request){
        boolean isUserInfo = true;
        boolean isOfficeList = false;
        boolean isOfficeInfo = false;
        boolean isOfficeType = false;
        boolean isApplyCheck = false;
        boolean isApplyList = false;
        boolean isBorrowList = false;
        boolean isReturnList = false;
        boolean isUseData = false;

        HttpSession session = request.getSession();
        m.addAttribute("isUserInfo",isUserInfo);
        m.addAttribute("isOfficeList",isOfficeList);
        m.addAttribute("isOfficeInfo",isOfficeInfo);
        m.addAttribute("isOfficeType",isOfficeType);
        m.addAttribute("isApplyCheck",isApplyCheck);
        m.addAttribute("isApplyList",isApplyList);
        m.addAttribute("isBorrowList",isBorrowList);
        m.addAttribute("isReturnList",isReturnList);
        m.addAttribute("isUseData",isUseData);

        return "manage";
    }

    //页面跳转到物品申请管理页面(物品申请)-_-!
    @RequestMapping("/manage_officelist")
    public String manage_officelist(Model m,HttpServletRequest request){
        boolean isUserInfo = false;
        boolean isOfficeList = true;
        boolean isOfficeInfo = false;
        boolean isOfficeType = false;
        boolean isApplyCheck = false;
        boolean isApplyList = false;
        boolean isBorrowList = false;
        boolean isReturnList = false;
        boolean isUseData = false;

        m.addAttribute("isUserInfo",isUserInfo);
        m.addAttribute("isOfficeList",isOfficeList);
        m.addAttribute("isOfficeInfo",isOfficeInfo);
        m.addAttribute("isOfficeType",isOfficeType);
        m.addAttribute("isApplyCheck",isApplyCheck);
        m.addAttribute("isApplyList",isApplyList);
        m.addAttribute("isBorrowList",isBorrowList);
        m.addAttribute("isReturnList",isReturnList);
        m.addAttribute("isUseData",isUseData);
        return "manage";
    }

    //页面跳转到物品管理页面(物品管理)
    @RequestMapping("/manage_officeInfo")
    public String manage_officeInfo(Model m,HttpServletRequest request){
        boolean isUserInfo = false;
        boolean isOfficeList = false;
        boolean isOfficeInfo = true;
        boolean isOfficeType = false;
        boolean isApplyCheck = false;
        boolean isApplyList = false;
        boolean isBorrowList = false;
        boolean isReturnList = false;
        boolean isUseData = false;

        m.addAttribute("isUserInfo",isUserInfo);
        m.addAttribute("isOfficeList",isOfficeList);
        m.addAttribute("isOfficeInfo",isOfficeInfo);
        m.addAttribute("isOfficeType",isOfficeType);
        m.addAttribute("isApplyCheck",isApplyCheck);
        m.addAttribute("isApplyList",isApplyList);
        m.addAttribute("isBorrowList",isBorrowList);
        m.addAttribute("isReturnList",isReturnList);
        m.addAttribute("isUseData",isUseData);
        return "manage";
    }

    //页面跳转到物品类型管理页面(物品类型)
    @RequestMapping("/manage_officeType")
    public String manage_officeType(Model m,HttpServletRequest request){
        boolean isUserInfo = false;
        boolean isOfficeList = false;
        boolean isOfficeInfo = false;
        boolean isOfficeType = true;
        boolean isApplyCheck = false;
        boolean isApplyList = false;
        boolean isBorrowList = false;
        boolean isReturnList = false;
        boolean isUseData = false;

        m.addAttribute("isUserInfo",isUserInfo);
        m.addAttribute("isOfficeList",isOfficeList);
        m.addAttribute("isOfficeInfo",isOfficeInfo);
        m.addAttribute("isOfficeType",isOfficeType);
        m.addAttribute("isApplyCheck",isApplyCheck);
        m.addAttribute("isApplyList",isApplyList);
        m.addAttribute("isBorrowList",isBorrowList);
        m.addAttribute("isReturnList",isReturnList);
        m.addAttribute("isUseData",isUseData);

        return "manage";
    }

    //页面跳转到申请审核管理页面(申请审核)
    @RequestMapping("/manage_applyCheck")
    public String manage_applyCheck(Model m,HttpServletRequest request){
        boolean isUserInfo = false;
        boolean isOfficeList = false;
        boolean isOfficeInfo = false;
        boolean isOfficeType = false;
        boolean isApplyCheck = true;
        boolean isApplyList = false;
        boolean isBorrowList = false;
        boolean isReturnList = false;
        boolean isUseData = false;

        m.addAttribute("isUserInfo",isUserInfo);
        m.addAttribute("isOfficeList",isOfficeList);
        m.addAttribute("isOfficeInfo",isOfficeInfo);
        m.addAttribute("isOfficeType",isOfficeType);
        m.addAttribute("isApplyCheck",isApplyCheck);
        m.addAttribute("isApplyList",isApplyList);
        m.addAttribute("isBorrowList",isBorrowList);
        m.addAttribute("isReturnList",isReturnList);
        m.addAttribute("isUseData",isUseData);

        return "manage";
    }

    //页面跳转到申请记录管理页面(申请记录)
    @RequestMapping("/manage_applyList")
    public String manage_applyList(Model m,HttpServletRequest request){
        boolean isUserInfo = false;
        boolean isOfficeList = false;
        boolean isOfficeInfo = false;
        boolean isOfficeType = false;
        boolean isApplyCheck = false;
        boolean isApplyList = true;
        boolean isBorrowList = false;
        boolean isReturnList = false;
        boolean isUseData = false;

        m.addAttribute("isUserInfo",isUserInfo);
        m.addAttribute("isOfficeList",isOfficeList);
        m.addAttribute("isOfficeInfo",isOfficeInfo);
        m.addAttribute("isOfficeType",isOfficeType);
        m.addAttribute("isApplyCheck",isApplyCheck);
        m.addAttribute("isApplyList",isApplyList);
        m.addAttribute("isBorrowList",isBorrowList);
        m.addAttribute("isReturnList",isReturnList);
        m.addAttribute("isUseData",isUseData);

        return "manage";
    }

    //页面跳转到借入记录管理页面(借入记录)
    @RequestMapping("/manage_borrowList")
    public String manage_borrowList(Model m,HttpServletRequest request){
        boolean isUserInfo = false;
        boolean isOfficeList = false;
        boolean isOfficeInfo = false;
        boolean isOfficeType = false;
        boolean isApplyCheck = false;
        boolean isApplyList = false;
        boolean isBorrowList = true;
        boolean isReturnList = false;
        boolean isUseData = false;

        m.addAttribute("isUserInfo",isUserInfo);
        m.addAttribute("isOfficeList",isOfficeList);
        m.addAttribute("isOfficeInfo",isOfficeInfo);
        m.addAttribute("isOfficeType",isOfficeType);
        m.addAttribute("isApplyCheck",isApplyCheck);
        m.addAttribute("isApplyList",isApplyList);
        m.addAttribute("isBorrowList",isBorrowList);
        m.addAttribute("isReturnList",isReturnList);
        m.addAttribute("isUseData",isUseData);

        return "manage";
    }

    //页面跳转到归还记录管理页面(归还记录)
    @RequestMapping("/manage_returnList")
    public String manage_returnList(Model m,HttpServletRequest request){
        boolean isUserInfo = false;
        boolean isOfficeList = false;
        boolean isOfficeInfo = false;
        boolean isOfficeType = false;
        boolean isApplyCheck = false;
        boolean isApplyList = false;
        boolean isBorrowList = false;
        boolean isReturnList = true;
        boolean isUseData = false;

        m.addAttribute("isUserInfo",isUserInfo);
        m.addAttribute("isOfficeList",isOfficeList);
        m.addAttribute("isOfficeInfo",isOfficeInfo);
        m.addAttribute("isOfficeType",isOfficeType);
        m.addAttribute("isApplyCheck",isApplyCheck);
        m.addAttribute("isApplyList",isApplyList);
        m.addAttribute("isBorrowList",isBorrowList);
        m.addAttribute("isReturnList",isReturnList);
        m.addAttribute("isUseData",isUseData);

        return "manage";
    }

    //页面跳转到使用统计管理页面(使用统计)
    @RequestMapping("/manage_useData")
    public String manage_useData(Model m,HttpServletRequest request){
        boolean isUserInfo = false;
        boolean isOfficeList = false;
        boolean isOfficeInfo = false;
        boolean isOfficeType = false;
        boolean isApplyCheck = false;
        boolean isApplyList = false;
        boolean isBorrowList = false;
        boolean isReturnList = false;
        boolean isUseData = true;

        m.addAttribute("isUserInfo",isUserInfo);
        m.addAttribute("isOfficeList",isOfficeList);
        m.addAttribute("isOfficeInfo",isOfficeInfo);
        m.addAttribute("isOfficeType",isOfficeType);
        m.addAttribute("isApplyCheck",isApplyCheck);
        m.addAttribute("isApplyList",isApplyList);
        m.addAttribute("isBorrowList",isBorrowList);
        m.addAttribute("isReturnList",isReturnList);
        m.addAttribute("isUseData",isUseData);

        return "manage";
    }

}
