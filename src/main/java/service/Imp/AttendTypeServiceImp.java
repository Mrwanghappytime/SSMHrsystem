package service.Imp;

import dao.AttendTypeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import po.AttendType;
import service.AttendTypeService;
import vo.OparationVO;

import java.util.List;

@Service("attendTypeService")
public class AttendTypeServiceImp implements AttendTypeService {
    @Autowired
    private AttendTypeDao attendTypeDao;


    @Override
    public OparationVO<List<AttendType>> getall() {
        try {
            List<AttendType> attendTypes = attendTypeDao.getAll();
            return  new OparationVO<>(0,"s",attendTypes);
        }catch (Exception e){
            e.printStackTrace();
            return new OparationVO<>(1,"ss");
        }
    }
}
