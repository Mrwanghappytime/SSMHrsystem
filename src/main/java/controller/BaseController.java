package controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import po.Employee;
import po.Manager;
import service.BaseService;
import service.EmployeeService;
import service.ManagerService;
import vo.BaseVO;
import vo.OparationVO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class BaseController {
    @Autowired
    private ManagerService managerService;
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private BaseService baseService;
    @RequestMapping("hello")
    public String hello(){
        return "index";
    }
    @RequestMapping("login")
    public String login(){
        try {
            SecurityUtils.getSubject().logout();
        }catch (Exception e){

        }
        return "login";
    }
    @RequestMapping("unauthorizedUrl")
    public String unauthorizedUrl(HttpServletRequest request){
        return "unauthorizedUrl";
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
        SecurityUtils.getSubject().logout();
        return "main";
    }
    @RequestMapping("check")
    @ResponseBody
    public OparationVO check(HttpServletRequest request, HttpServletResponse response, BaseVO baseVO){
        HttpSession session = request.getSession();
        String code = (String) session.getAttribute("rand");
        if(code == null || baseVO.getVercode() == null){
            return new OparationVO(0,"sss");
        }
        if(!code.toLowerCase().equals(baseVO.getVercode().toLowerCase())){
            return new OparationVO(0,"sss");
        }
        Subject subject = SecurityUtils.getSubject();
        try {
            if (!subject.isAuthenticated()) {
                UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(baseVO.getName(), baseVO.getPassword());
                subject.login(usernamePasswordToken);
            }
            if(subject.hasRole("employee")){
                Employee employee = new Employee();
                employee.setName(baseVO.getName());
                session.setAttribute("user",employeeService.getEmployee(employee).get(0));
                return new OparationVO(2,"sss");
            }else if(subject.hasRole("manager")){
                Manager employee = new Manager();
                employee.setName(baseVO.getName());
                session.setAttribute("user",managerService.getManager(employee).get(0));
                return new OparationVO(1,"sss");
            }
        }catch (Exception e){
            return new OparationVO(0,"sss");
        }

        return new OparationVO(0,"sss");
    }

}
