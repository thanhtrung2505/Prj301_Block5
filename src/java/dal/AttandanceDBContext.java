/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Attandance;

/**
 *
 * @author Do Phong PC
 */
public class AttandanceDBContext extends DBContext {

    public void insert(ArrayList<Attandance> atts) {
        try {
            connection.setAutoCommit(false);
            String sql = "INSERT INTO [Attandance]([studentID],[classid],[present]) VALUES (?,?,?)";                      
            for (Attandance att : atts) {
                PreparedStatement stm = connection.prepareStatement(sql);
                stm.setInt(1, att.getStudent().getStuID());
                stm.setInt(2, att.getClassid());
                stm.setBoolean(3, att.isPresent());
                stm.executeUpdate();
            }
            connection.commit();
        } catch (SQLException ex) {
            Logger.getLogger(AttandanceDBContext.class.getName()).log(Level.SEVERE, null, ex);
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(AttandanceDBContext.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(AttandanceDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }
}
