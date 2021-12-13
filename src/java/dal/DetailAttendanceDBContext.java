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
import model.DetailAttendance;

/**
 *
 * @author Do Phong PC
 */
public class DetailAttendanceDBContext extends DBContext{
     public ArrayList<DetailAttendance> getDetailAttandanceClass(String classid) {
        ArrayList<DetailAttendance> depts = new ArrayList<>();
        try {
            String sql = "select c.classid, g.groupName, c.date,c.slot,s.studentName,s.studentCode,a.present,su.subName, c.className from Class c,"
                    + " Student s, Subject su,Attandance a,[Group] g where s.studentID = a.studentID and g.groupid = s.groupid and"
                    + " c.classid = a.classid and su.subid = c.subid and c.classid = ?";

            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, classid);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                DetailAttendance d = new DetailAttendance();
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
     
 public ArrayList<DetailAttendance> getDetailAttandanceEdit(String classid,String stuID) {
        ArrayList<DetailAttendance> depts = new ArrayList<>();
        try {
            String sql = "select c.classid, g.groupName, c.date,c.slot,s.studentName,s.studentCode,a.present,su.subName, c.className from Class c,"
                    + " Student s, Subject su,Attandance a,[Group] g where s.studentID = a.studentID and g.groupid = s.groupid and"
                    + " c.classid = a.classid and su.subid = c.subid and c.classid = ? and s.studentID = ?";

            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, classid);
            stm.setString(2, stuID);           
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                DetailAttendance d = new DetailAttendance();
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
}
