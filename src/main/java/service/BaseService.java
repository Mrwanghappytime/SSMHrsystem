package service;

import vo.BaseVO;

import javax.servlet.http.HttpServletRequest;

public interface BaseService {
    public int findUser(BaseVO baseVO, HttpServletRequest request);
}
