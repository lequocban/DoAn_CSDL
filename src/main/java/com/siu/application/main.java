/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.siu.application;

//import com.siu.DAO.NHANVIEN_DAO;
//import com.siu.model.NHANVIEN;
//import java.util.ArrayList;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import com.siu.application.JForm_Login;
/**
 *
 * @author ACER
 */
public class main {
    public static void main(String[] args) throws Exception {
        try {
            for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        JForm_Login lon = new JForm_Login();
        lon.setLocationRelativeTo(null);
        lon.setVisible(true);
    }
}
