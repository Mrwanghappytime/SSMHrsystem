package service.Imp;

import dao.AttendDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import service.AttendService;

@Service("attendService")
@Transactional
public class AttendServiceImp implements AttendService {

    @Autowired
    private AttendDao attendDao;
    @Override
    public void autoSetPunchAttendType() {
        attendDao.autoSetAttendType();
    }
}
