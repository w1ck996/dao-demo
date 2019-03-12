package com.cheer.dao;

import com.cheer.model.Emp;

import java.util.List;

public interface EmpDao {
    // 根据员工编号获取员工
    Emp getEmp(Integer empNo);

    // 获取所有员工
    List<Emp> getList();

    // 增加一个员工
    void save(Emp emp);

    // 删除一个员工
    void remove(Integer empNo);

    // 修改一个员工
    void update(Emp emp);

    // 获取所有员工数
    int getCount();
}
