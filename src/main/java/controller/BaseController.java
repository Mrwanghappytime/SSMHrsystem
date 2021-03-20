package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import service.BaseService;
import service.Imp.BaseServiceIml;
import vo.BaseVO;
import vo.OparationVO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class BaseController {
    @Autowired
    private BaseService baseService;
    @RequestMapping("hello")
    public String hello(){
        return "index";
    }
    @RequestMapping("login")
    public String login(){
        return "login";
    }
    @ResponseBody
    @RequestMapping("check")
    public OparationVO check(HttpServletRequest request, HttpServletResponse response, BaseVO baseVO){

        HttpSession session = request.getSession();

        String code = (String) session.getAttribute("rand");

        if(code == null || baseVO.getVercode() == null){
            return new OparationVO(0,"sss");
        }

        if(!code.toLowerCase().equals(baseVO.getVercode().toLowerCase())){
            return new OparationVO(0,"sss");
        }
        int i = baseService.findUser(baseVO,request);
        if(i==1){
            return new OparationVO(2,"xixix");
        }else if(i==2){
            return new OparationVO(1,"haha");
        }else{
            return new OparationVO(0,"sss");
        }
    }
    @RequestMapping("indexOfEmployee")
    public String indexOfEmployee(){
        return "employee/index";
    }
    @RequestMapping("indexOfManager")
    public String indexOfManager(){
        return "manager/index";
    }
    @RequestMapping("logout")
    public String loginout(HttpServletRequest request,HttpServletResponse response){
        HttpSession session = request.getSession();
        session.invalidate();
        return "main";
    }
}
