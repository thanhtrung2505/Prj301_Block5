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
import model.DetailAttandance;

/**
 *
 * @author Do Phong PC
 */
public class ClassDBContext extends DBContext {

    public ArrayList<DetailAttandance> getDetailAttandanceClass(String classid) {
        ArrayList<DetailAttandance> depts = new ArrayList<>();
        try {
            String sql = "select c.classid, g.groupName, c.date,c.slot,s.studentName,s.studentCode,a.present,su.subName, c.className from Class c,"
                    + " Student s, Subject su,Attandance a,[Group] g where s.studentID = a.studentID and g.groupid = s.groupid and"
                    + " c.classid = a.classid and su.subid = c.subid and c.classid = ?";

            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, classid);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                DetailAttandance d = new DetailAttandance();
                d.setClassid(rs.getInt("classid"));  
                d.setGroupName(rs.getString("groupName"));              
                d.setDate(rs.getDate("date"));
                d.setSlot(rs.getInt("slot"));
                d.setStuName(rs.getString("studentName"));  
                d.setStuCode(rs.getString("studentCode"));
                d.setPresent(rs.getBoolean("present"));
                d.setSubName(rs.getString("subName"));
                d.setClassName(rs.getString("className"));
                depts.add(d);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ClassDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return depts;
    }

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
