package com.revature.employeeReimbursementSystem.dataAccessObjects;

import com.revature.employeeReimbursementSystem.entities.Account;
import com.revature.employeeReimbursementSystem.entities.Option;
import com.revature.employeeReimbursementSystem.utilities.ConnectionUtility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OptionDAO {
    private ConnectionUtility cu = ConnectionUtility.getConnectionUtility();
    public List<Option> getOptions()
    {
        List<Option> options = new ArrayList<>();
        String sql = "select * from options";
        try(Connection connection = cu.getConnection())
        {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next())
            {
                Option option = new Option(
                        rs.getInt("id"),
                        rs.getString("option"));
                options.add(option);
            }
            return options;
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        return null;
    }
}
