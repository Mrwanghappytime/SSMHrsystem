package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import po.Employee;
import po.Manager;
import service.ManagerService;
import vo.OparationVO;
import vo.PaymentVo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("manager")
public class ManagerController {
    @Autowired
    private ManagerService managerService;

    @RequestMapping(value = "/punch",method = RequestMethod.POST)
    @ResponseBody
    public OparationVO punch(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return managerService.punch(request);
    }
    @RequestMapping(value = "/managerPunchPage",method = RequestMethod.GET)
    public String managerPunchPage(){
        return "manager/punch";
    }
    @RequestMapping(value = "/viewSalary",method = RequestMethod.GET)
    public String viewSalary(){
        //request.getSession().setAttribute("appId",id);
        return "manager/viewSalary";
    }

    @RequestMapping(value = "/viewApp",method = RequestMethod.GET)
    public String viewApp(){
        return "manager/viewApps";
    }
    @RequestMapping(value = "/viewEmp",method = RequestMethod.GET)
    public String viewEmp(){
        return "manager/viewEmp";
    }
    @RequestMapping(value = "/addEmp",method = RequestMethod.GET)
    public String addEmp(){
        return "manager/addEmp";
    }
    @RequestMapping(value = "/viewDeptSal",method = RequestMethod.GET)
    public String viewDeptSal(){
        return "manager/viewDeptSal";
    }

    @RequestMapping(value = "viewDptEmployee",method = RequestMethod.POST)
    @ResponseBody
    public OparationVO<List<Employee>> viewDptEmployee(HttpServletRequest request){
        Manager manager = (Manager) request.getSession().getAttribute("user");
        return managerService.getDptEmployee(manager.getId());
    }
    @RequestMapping(value = "viewDeptSal",method = RequestMethod.POST)
    @ResponseBody
    public OparationVO<List<PaymentVo>> viewDeptSal(HttpServletRequest request){
        Manager manager = (Manager) request.getSession().getAttribute("user");
        return managerService.viewDeptSal(manager.getId());
    }
    @RequestMapping(value = "addEmp",method = RequestMethod.POST)
    @ResponseBody
    public OparationVO addEmp(HttpServletRequest request,Employee employee){
        employee.setManagerId(((Manager)(request.getSession().getAttribute("user"))).getId());
        return managerService.addEmp(employee);
    }






}
