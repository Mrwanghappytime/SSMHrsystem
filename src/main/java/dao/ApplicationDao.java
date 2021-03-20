package dao;

import po.Application;
import vo.ApplicationVo;

import java.util.List;

public interface ApplicationDao {
    void insert(List<Application> applications);

    List<ApplicationVo> getAppsByMgrId(Integer id);

    void update(Application application);
}
