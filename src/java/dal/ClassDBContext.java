/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Class1;
import model.DetailAttendance;

/**
 *
 * @author Do Phong PC
 */
public class ClassDBContext extends DBContext {

    public ArrayList<Class1> getSlot() {
        ArrayList<Class1> depts = new ArrayList<>();
        try {
            String sql = "SELECT DISTINCT  \n"
                    + "      [slot]\n"
                    + "  FROM [dbo].[Class]";

            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Class1 d = new Class1();
                d.setSlot(rs.getInt("slot"));
                depts.add(d);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ClassDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return depts;
    }

    public ArrayList<Class1> getDate() {
        ArrayList<Class1> depts = new ArrayList<>();
        try {
            String sql = "SELECT DISTINCT \n"
                    + "      [date]\n"
                    + "  FROM [dbo].[Class]";

            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Class1 d = new Class1();
                d.setDate(rs.getDate("date"));
                depts.add(d);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ClassDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return depts;
    }

    public  ArrayList<Class1> getClassID(String date, String slot, String groupid) {
         ArrayList<Class1> depts = new ArrayList<>();
        try {
            String sql = "SELECT [classid]\n"
                    + "  FROM [dbo].[Class]\n"
                    + "  where date = '"+date+"' and slot ="+slot+" and groupid ="+groupid+"";
            PreparedStatement stm = connection.prepareStatement(sql);
      
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Class1 d = new Class1();
                d.setClassid(rs.getInt("classid"));
                depts.add(d);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ClassDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return depts;
    }
}
