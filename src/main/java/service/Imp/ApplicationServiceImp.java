package service.Imp;

import dao.ApplicationDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import po.Application;
import po.Manager;
import service.ApplicationService;
import vo.ApplicationVo;
import vo.OparationVO;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Transactional
@Service("applicationService")
public class ApplicationServiceImp implements ApplicationService {
    @Autowired
    private ApplicationDao applicationDao;
    @Override
    public OparationVO addApplication(List<Application> applications) {
        if(applications.size() <= 0){
            return new OparationVO(1,"没有需要插入到内容");
        }
        try {
            applicationDao.insert(applications);
            return new OparationVO(0,"插入成功");
        }catch (Exception e){
            e.printStackTrace();
            return new OparationVO(1,"插入失败");
        }

    }

    @Override
    public OparationVO<List<ApplicationVo>> viewEmpApps(HttpServletRequest request) {
        Manager m = (Manager) request.getSession().getAttribute("user");
        try {
            List<ApplicationVo> list = applicationDao.getAppsByMgrId(m.getId());
            return new OparationVO(0,"查询成功",list);
        }catch (Exception e){
            e.printStackTrace();
            return new OparationVO(1,"查询失败");
        }
    }

    @Override
    public OparationVO updateApplication(Application application) {
        try{
            applicationDao.update(application);
            return new OparationVO(0,"操作成功");
        }catch (Exception e){
            return new OparationVO(0,"操作失败");
        }
    }
}
