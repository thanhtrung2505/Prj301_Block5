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
import model.Group;

/**
 *
 * @author Do Phong PC
 */
public class GroupDBContext extends DBContext {

    public ArrayList<Group> getAllGroup() {
        ArrayList<Group> depts = new ArrayList<>();
        try {

            String sql = "SELECT [groupid]\n"
                    + "      ,[groupName]\n"
                    + "  FROM [dbo].[Group]";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Group d = new Group();
                d.setGroupid(rs.getInt("groupid"));
                d.setGroupName(rs.getString("groupName"));
                depts.add(d);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClassDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return depts;
    }
    
}
