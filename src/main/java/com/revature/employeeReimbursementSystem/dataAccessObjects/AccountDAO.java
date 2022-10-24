package com.revature.employeeReimbursementSystem.dataAccessObjects;

import com.revature.employeeReimbursementSystem.entities.Account;
import com.revature.employeeReimbursementSystem.utilities.ConnectionUtility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountDAO {
    private ConnectionUtility cu = ConnectionUtility.getConnectionUtility();
    public Account getAccountByEmployeeId(int employeeId)
    {
        String sql = "select * from accounts where employee_id = ?";
        try(Connection connection = cu.getConnection())
        {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, employeeId);
            ResultSet rs = ps.executeQuery();
            if(rs.next())
            {
                Account account = new Account(
                        rs.getInt("id"),
                        rs.getInt("employee_id"),
                        rs.getDouble("total"),
                        rs.getDouble("pending"),
                        rs.getDouble("awarded"),
                        rs.getDouble("balance")
                );
                return account;

            }
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        return null;
    }
}
