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
import model.Attendance;
import model.Class1;

/**
 *
 * @author Do Phong PC
 */
public class AttendanceDBContext extends DBContext {

    public ArrayList<Attendance> getClassId(String classid) {
        ArrayList<Attendance> depts = new ArrayList<>();
        try {
            String sql = "SELECT  classid from [Attandance] where classid  = "+classid+"";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Attendance d = new Attendance();
                d.setClassid(rs.getInt("classid"));
                depts.add(d);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ClassDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return depts;
    }

    public void insert(ArrayList<Attendance> atts) {
        try {
            connection.setAutoCommit(false);
            String sql = "INSERT INTO [Attandance]([studentID],[classid],[present]) VALUES (?,?,?)";
            for (Attendance att : atts) {
                PreparedStatement stm = connection.prepareStatement(sql);
                stm.setInt(1, att.getStudent().getStuID());
                stm.setInt(2, att.getClassid());
                stm.setBoolean(3, att.isPresent());
                stm.executeUpdate();
            }
            connection.commit();
        } catch (SQLException ex) {
            Logger.getLogger(AttendanceDBContext.class.getName()).log(Level.SEVERE, null, ex);
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(AttendanceDBContext.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(AttendanceDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }
}
