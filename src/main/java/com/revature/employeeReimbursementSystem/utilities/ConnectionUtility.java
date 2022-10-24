package com.revature.employeeReimbursementSystem.utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtility {
    // Create state for ConnectionUtility class.
    private static ConnectionUtility cu;
    private static String url;
    private static String user;
    private static String password;
    //No arguments constructor for ConnectionUtility objects.
    private ConnectionUtility()
    {
        url = "jdbc:postgresql://project-1.c0ceum5b5yxh.us-east-2.rds.amazonaws.com:5432/postgres?currentSchema=public";
        user = "postgres";
        password = "Jody2022!!";

    }
    public static synchronized ConnectionUtility getConnectionUtility()
    {
        return (cu==null) ? new ConnectionUtility() : cu;
    }
    public Connection getConnection()
    {

        Connection connection = null;
        try
        {
            connection = DriverManager.getConnection( url,user,password );

        }
        catch(SQLException e)
        {
            e.printStackTrace();

        }
        return connection;
    }
    // Connection Tester, comment out after testing.
//    public static void main(String[] args) throws SQLException
//    {
//        ConnectionUtility cu = ConnectionUtility.getConnectionUtility();
//        Connection conn = cu.getConnection();
//        if (conn != null)
//        {
//            System.out.println("Connection successful" + conn);
//            conn.close();
//        }
//        else
//        {
//            System.out.println("Connection failed");
//
//        }
//    }
}
