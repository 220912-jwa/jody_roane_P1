package com.revature.employeeReimbursementSystem.dataAccessObjects;

import com.revature.employeeReimbursementSystem.entities.Claim;
import com.revature.employeeReimbursementSystem.utilities.ConnectionUtility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClaimDAO {
    private ConnectionUtility cu = ConnectionUtility.getConnectionUtility();
    public Claim createClaim(Claim newClaim)
    {
        String sql = "insert into claims\n" +
                "values\n" +
                "(default,\n" +
                "?,\n" +
                "?,\n" +
                "?,\n" +
                "?,\n" +
                "?,\n" +
                "?,\n" +
                "?,\n" +
                "?,\n" +
                "?,\n" +
                "?,\n" +
                "?,\n" +
                "?,\n" +
                "?,\n" +
                "?,\n" +
                "?,\n" +
                "?,\n" +
                "?,\n" +
                "?)";
        try (Connection connection = cu.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1,newClaim.getFirstName());
            ps.setString(2,newClaim.getLastname());
            ps.setString(3,newClaim.getRole());
            ps.setInt(4,newClaim.getEmployeeId());
            ps.setDouble(5,newClaim.getBalance());
            ps.setString(6,newClaim.getEvent());
            ps.setInt(7,newClaim.getCoverage());
            ps.setString(8,newClaim.getDescription());
            ps.setString(9,newClaim.getLocation());
            ps.setDouble(10,newClaim.getCost());
            ps.setDouble(11,newClaim.getReimbursement());
            ps.setDate(12,newClaim.getStartDate());
            ps.setDate(13,newClaim.getEndDate());
            ps.setString(14,newClaim.getPassingGrade());
            ps.setString(15,newClaim.getFinalGrade());
            ps.setString(16,newClaim.getClaimStatus());
            ps.setBoolean(17,newClaim.isAwardStatus());
            ps.setInt(18,newClaim.getPriority());
            ps.executeUpdate();
//            if (rs.next())
//            {
//                newClaim.setId(rs.getInt("id"));
//                return newClaim;
//            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Claim> getClaimsForApproval()
    {
        List<Claim> claims = new ArrayList<>();
        String sql = "select * from claims where awardstatus = false order by priority desc";
        try(Connection connection = cu.getConnection())
        {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next())
            {
                Claim claim = new Claim(
                        rs.getInt("id"),
                        rs.getString("firstName"),
                        rs.getString("lastname"),
                        rs.getString("role"),
                        rs.getInt("employee_Id"),
                        rs.getDouble("balance"),
                        rs.getString("event"),
                        rs.getInt("coverage"),
                        rs.getString("description"),
                        rs.getString("location"),
                        rs.getDouble("cost"),
                        rs.getDouble("reimbursement"),
                        rs.getDate("startDate"),
                        rs.getDate("endDate"),
                        rs.getString("passingGrade"),
                        rs.getString("finalGrade"),
                        rs.getString("claimStatus"),
                        rs.getBoolean("awardStatus"),
                        rs.getInt("priority"));
                claims.add(claim);
            }
            return claims;
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        return null;
    }
    public Claim geClaimById(int id)
    {
        String sql = "select * from claims where id = ?";
        try(Connection connection = cu.getConnection())
        {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next())
            {
                Claim claim = new Claim(
                        rs.getInt("id"),
                        rs.getString("firstName"),
                        rs.getString("lastName"),
                        rs.getString("role"),
                        rs.getInt("employee_id"),
                        rs.getDouble("balance"),
                        rs.getString("event"),
                        rs.getInt("coverage"),
                        rs.getString("description"),
                        rs.getString("location"),
                        rs.getDouble("cost"),
                        rs.getDouble("reimbursement"),
                        rs.getDate("startDate"),
                        rs.getDate("endDate"),
                        rs.getString("passingGrade"),
                        rs.getString("finalGrade"),
                        rs.getString("claimStatus"),
                        rs.getBoolean("awardStatus"),
                        rs.getInt("priority"));
                return claim;
            }
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        return null;
    }
    public void updateClaimById(Claim updatedClaim)
    {
        String sql = "update claims \n" +
                "set\n" +
                "passinggrade = ?,\n" +
                "finalgrade  = ?,\n" +
                "reimbursement = ?,\n" +
                "claimstatus = ?,\n" +
                "awardstatus = ?\n" +
                "where id = ?\n" +
                ";\n" +
                "update claims\n" +
                "set\n" +
                "priority = (select id from options where option = ?)\n" +
                "where id = ?\n" +
                ";\n" +
                "update accounts\n" +
                "set\n" +
                "pending = (pending - pending),\n" +
                "awarded = (awarded + pending)\n" +
                "where employee_id = ?\n" +
                ";\n" +
                "update accounts\n" +
                "set\n" +
                "balance = (total - pending - awarded)\n" +
                "where employee_id = ?";
        try(Connection connection = cu.getConnection())
        {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1,updatedClaim.getPassingGrade());
            ps.setString(2,updatedClaim.getFinalGrade());
            ps.setDouble(3,updatedClaim.getReimbursement());
            ps.setString(4,updatedClaim.getClaimStatus());
            ps.setBoolean(5, updatedClaim.isAwardStatus());
            ps.setInt(6,updatedClaim.getId());
            ps.setString(7,updatedClaim.getClaimStatus());
            ps.setInt(8,updatedClaim.getId());
            ps.setInt(9,updatedClaim.getEmployeeId());
            ps.setInt(10,updatedClaim.getEmployeeId());
            ps.executeUpdate();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
    }
    public List<Claim> getClaimsByEmployeeId(int employeeId)
    {
        List<Claim> claims = new ArrayList<>();
        String sql = "select * from claims where employee_id = ? order by priority desc";
        try(Connection connection = cu.getConnection())
        {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1,employeeId);
            ResultSet rs = ps.executeQuery();
            while(rs.next())
            {
                Claim claim = new Claim(
                        rs.getInt("id"),
                        rs.getString("firstName"),
                        rs.getString("lastname"),
                        rs.getString("role"),
                        rs.getInt("employee_Id"),
                        rs.getDouble("balance"),
                        rs.getString("event"),
                        rs.getInt("coverage"),
                        rs.getString("description"),
                        rs.getString("location"),
                        rs.getDouble("cost"),
                        rs.getDouble("reimbursement"),
                        rs.getDate("startDate"),
                        rs.getDate("endDate"),
                        rs.getString("passingGrade"),
                        rs.getString("finalGrade"),
                        rs.getString("claimStatus"),
                        rs.getBoolean("awardStatus"),
                        rs.getInt("priority"));
                claims.add(claim);
            }
            return claims;
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        return null;
    }

}
