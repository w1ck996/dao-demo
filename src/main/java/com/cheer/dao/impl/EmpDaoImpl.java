package com.cheer.dao.impl;

import com.cheer.dao.EmpDao;
import com.cheer.model.Emp;
import com.cheer.util.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

// Ctrl + I
public class EmpDaoImpl implements EmpDao {
    @Override
    public Emp getEmp(Integer empNo) {
        Emp emp = null;
        Connection connection = DBUtils.getInstance().getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String sql = "select * from emp where empno = ?";
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, empNo);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String eName = resultSet.getString("ename");
                String job = resultSet.getString("job");
                Integer mgr = resultSet.getInt("mgr");
                String hireDate = resultSet.getString("hiredate");
                Double sal = resultSet.getDouble("sal");
                Double com = resultSet.getDouble("com");
                Integer deptNo = resultSet.getInt("deptno");
                emp = new Emp(empNo, eName, job, mgr, hireDate, sal, com, deptNo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.getInstance().close(connection, statement, resultSet);
        }
        return emp;
    }

    @Override
    public List<Emp> getList() {
        List<Emp> empList = new ArrayList<>();
        Connection connection = DBUtils.getInstance().getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String sql = "select * from emp";
        try {
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            Emp emp;
            while (resultSet.next()) {
                Integer empNo = resultSet.getInt("empno");
                String eName = resultSet.getString("ename");
                String job = resultSet.getString("job");
                Integer mgr = resultSet.getInt("mgr");
                String hireDate = resultSet.getString("hiredate");
                Double sal = resultSet.getDouble("sal");
                Double com = resultSet.getDouble("com");
                Integer deptNo = resultSet.getInt("deptno");
                emp = new Emp(empNo, eName, job, mgr, hireDate, sal, com, deptNo);
                empList.add(emp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.getInstance().close(connection, statement, resultSet);
        }
        return empList;
    }

    @Override
    public void save(Emp emp) throws Exception {
        Connection connection = DBUtils.getInstance().getConnection();
        PreparedStatement statement = null;
        String sql = "insert into emp values(?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, emp.getEmpNo());
            statement.setString(2, emp.getEName());
            statement.setString(3, emp.getJob());
            statement.setInt(4, emp.getMgr());
            statement.setString(5, emp.getHireDate());
            statement.setDouble(6, emp.getSal());
            statement.setDouble(7, emp.getCom());
            statement.setInt(8, emp.getDeptNo());
            int result = statement.executeUpdate();
            if (result == 1) {
                System.out.println("新增成功！");
                return;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.getInstance().close(connection, statement, null);
        }
        throw new Exception("新增失败");
    }

    @Override
    public void remove(Integer empNo) throws Exception {
        Connection connection = DBUtils.getInstance().getConnection();
        PreparedStatement statement = null;
        String sql = "delete from emp where empno = ?";
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, empNo);
            int result = statement.executeUpdate();
            if (result == 1) {
                System.out.println("删除成功！");
                return;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.getInstance().close(connection, statement, null);
        }
        throw new Exception("删除失败");
    }

    @Override
    public void update(Emp emp) throws Exception {
        Connection connection = DBUtils.getInstance().getConnection();
        PreparedStatement statement = null;
        String sql = "update emp set ename = ?, job =?, mgr = ?, hiredate = ?, sal = ?, com = ?, deptno = ? where " +
                "empno = ?";
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(2 - 1, emp.getEName());
            statement.setString(3 - 1, emp.getJob());
            statement.setInt(4 - 1, emp.getMgr());
            statement.setString(5 - 1, emp.getHireDate());
            statement.setDouble(6 - 1, emp.getSal());
            statement.setDouble(7 - 1, emp.getCom());
            statement.setInt(8 - 1, emp.getDeptNo());
            statement.setInt(8, emp.getEmpNo());
            int result = statement.executeUpdate();
            if (result == 1) {
                System.out.println("更新成功！");
                return;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.getInstance().close(connection, statement, null);
        }
        throw new Exception("更新失败");
    }

    @Override
    public int getCount() {
        Connection connection = DBUtils.getInstance().getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String sql = "select count(*) from emp";
        int count = 0;
        try {
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                count = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.getInstance().close(connection, statement, resultSet);
        }
        return count;
    }
}
