package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import po.Employee;
import service.EmployeeService;
import vo.AttendVo;
import vo.OparationVO;

import javax.crypto.spec.OAEPParameterSpec;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("employee")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;
    @RequestMapping(value = "/punch",method = RequestMethod.POST)
    @ResponseBody
    public OparationVO punch(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return employeeService.punch(request);
    }
    @RequestMapping(value = "/punchPage",method = RequestMethod.GET)
    public String punchPage(){
        return "employee/punch";
    }

    @RequestMapping(value="/viewUnPunchPage",method=RequestMethod.GET)
    public String viewUnPunchPage(){
        return "employee/viewUnAttend";
    }
    @RequestMapping(value = "/viewUnPunch",method = RequestMethod.POST)
    @ResponseBody
    public OparationVO<List<AttendVo>> viewUnPunch(HttpServletRequest request, HttpServletResponse response){
        return employeeService.viewUnPunch(request);
    }
    @RequestMapping(value = "/appChange",method = RequestMethod.GET)
    public String appChange(HttpServletRequest request,int id){
        request.getSession().setAttribute("appId",id);
        return "employee/appChange";
    }

    @RequestMapping(value = "/viewemployeeSalary",method = RequestMethod.GET)
    public String viewemployeeSalary(){
        return "employee/viewSalary";
    }
}
