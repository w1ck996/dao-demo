package com.cheer.dao.impl;

import com.cheer.dao.EmpDao;
import com.cheer.model.Emp;
import org.junit.Test;

import static org.junit.Assert.*;

// 单元测试也可以称为白盒测试，目的是为了测试目标方法是否完成了相应功能，而不仅仅是测试有无异常抛出
public class EmpDaoImplTest {

    @Test
    public void getEmp() {
        EmpDao empDao = new EmpDaoImpl();
        Emp emp = empDao.getEmp(7369);

        // 断言emp对象不为空
        assertNotNull(emp);
    }

    // 断言结果List<Emp>的长度为14
    @Test
    public void getList() {
    }

    @Test
    public void save() {
    }

    @Test
    public void remove() {
    }

    @Test
    public void update() {
    }

    @Test
    public void getCount() {
    }
}