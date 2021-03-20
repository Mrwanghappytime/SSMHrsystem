package dao;

import po.Manager;

import java.util.List;

public interface ManagerDao {
    public List<Manager> selectManager(Manager manager);
}
