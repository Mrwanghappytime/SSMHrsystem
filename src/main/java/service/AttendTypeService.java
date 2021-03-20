package service;

import po.AttendType;
import vo.OparationVO;

import java.util.List;

public interface AttendTypeService {
    OparationVO<List<AttendType>> getall();
}
