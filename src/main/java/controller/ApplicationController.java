package controller;

import com.sun.corba.se.impl.oa.poa.AOMEntry;
import dao.ApplicationDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import po.Application;
import po.AttendType;
import service.ApplicationService;
import service.AttendTypeService;
import vo.ApplicationVo;
import vo.OparationVO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("application")
public class ApplicationController {

    @Autowired
    private AttendTypeService attendTypeService;
    @Autowired
    private ApplicationService applicationService;

    @RequestMapping(value = "/getall",method = RequestMethod.POST)
    @ResponseBody
    public OparationVO<List<AttendType>> getall(){
        return attendTypeService.getall();
    }
    @RequestMapping(value = "/submitApp",method = RequestMethod.POST)
    @ResponseBody
    public OparationVO submitApp(HttpServletRequest request, HttpServletResponse response, Application application){
        System.out.println(application);
        List<Application> applications = new ArrayList<>();
        applications.add(application);
        return applicationService.addApplication(applications);
    }
    @RequestMapping(value = "/viewEmpApps",method = RequestMethod.POST)
    @ResponseBody
    public OparationVO<List<ApplicationVo>> viewEmpApps(HttpServletRequest request){
        return applicationService.viewEmpApps(request);
    }
    @RequestMapping(value="/result",method = RequestMethod.POST)
    @ResponseBody
    public OparationVO result(HttpServletRequest request,Integer id,String result){
        if(id == null || result == null){
            return new OparationVO(1,"操作失败");
        }
        Application application = new Application();
        application.setId(id);
        application.setResult(true);
        if(result.equals("pass")){
            application.setResults(true);
        }else{
            application.setResults(false);
        }
        return applicationService.updateApplication(application);
    }




}
