/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.siu.helper;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;

/**
 *
 * @author ADMIN
 */
public class DatabaseHelper {

    public static Connection openConnection() throws Exception {
//        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//        String url = "jdbc:sqlserver://LAPTOP-3CGRBNFL\\SQLEXPRESS:1433;databaseName=QL_THUVIEN;encrypt=true;trustServerCertificate=true;";

        //url Tu 
//        String url = "jdbc:sqlserver://localhost:1433;databaseName=QL_THUVIEN;encrypt=true;trustServerCertificate=true;";
        
        //url may LE QUOC BAN
        String url = "jdbc:sqlserver://LEQUOCBAN\\SQLEXPRESS:1433;databaseName=QL_THUVIEN;encrypt=true;trustServerCertificate=true;";
        String user = "sa";
        String password = "123456789";

        Connection con = DriverManager.getConnection(url, user, password);

        return con;
    }
}
