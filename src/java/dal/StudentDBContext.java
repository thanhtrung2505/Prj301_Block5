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
import model.Student;

/**
 *
 * @author Do Phong PC
 */
public class StudentDBContext extends DBContext {

    public ArrayList<Student> getAllStudentofGroup(String groupid) {
        ArrayList<Student> depts = new ArrayList<>();
        try {

            String sql = "SELECT  studentID, studentName,studentCode\n"
                    + "  FROM  Student where groupid =?";

            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, groupid);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Student d = new Student();
                d.setStuID(rs.getInt("studentID"));
                d.setStuName(rs.getString("studentName"));
                d.setStuCode(rs.getString("studentCode"));
                depts.add(d);
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return depts;
    }
}
