package com.revature.employeeReimbursementSystem.dataAccessObjects;

import com.revature.employeeReimbursementSystem.entities.Employee;
import com.revature.employeeReimbursementSystem.utilities.ConnectionUtility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO {
    private ConnectionUtility cu = ConnectionUtility.getConnectionUtility();
    // Get all employees rows.
    public List<Employee> getAllEmployees()
    {
        List<Employee> employees = new ArrayList<>();
        String sql = "select * from employees";
        try(Connection connection = cu.getConnection() )
        {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next())
            {
                Employee employee = new Employee(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6));
                employees.add(employee);
            }

        return employees;
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        return null;
    }
    // Get employees row by id.
    public Employee getEmployeeById(int id)
    {
        String sql = "select * from employees where id = ?";
        try(Connection connection = cu.getConnection() )
        {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();
            if(rs.next())
            {
                Employee employee = new Employee(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6));
                return employee;
            }


        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        return null;
    }
    // Get employees row by username.
    public Employee getEmployeeByUsername(String username)
    {
        String sql = "select * from employees where username = ?";
        try(Connection connection = cu.getConnection() )
        {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1,username);
            ResultSet rs = ps.executeQuery();
            if(rs.next())
            {
                Employee employee = new Employee(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6));
                return employee;
            }


        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        return null;
    }
}
