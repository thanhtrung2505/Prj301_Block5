/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import dal.AttendanceDBContext;
import dal.ClassDBContext;
import dal.DetailAttendanceDBContext;
import dal.GroupDBContext;
import dal.StudentDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Attendance;
import model.Class1;
import model.DetailAttendance;
import model.Group;
import model.Student;

/**
 *
 * @author Do Phong PC
 */
public class ListStudentController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ArrayList<Group> groups = grDB.getAllGroup();
        ArrayList<Class1> Class1 = clDB.getSlot();
        ArrayList<Class1> Class2 = clDB.getDate();
        request.setAttribute("groups", groups);
        request.setAttribute("slots", Class1);
        request.setAttribute("dates", Class2);
        ArrayList<Student> students = new ArrayList<>();
        String group = "1";
        String slot = "1";
        String date = "1";
        request.setAttribute("students", students);
        request.setAttribute("groupid", group);
        request.setAttribute("slotid", slot);
        request.setAttribute("dateid", date);
        request.getRequestDispatcher("/attandance/add.jsp").forward(request, response);
    }
    GroupDBContext grDB = new GroupDBContext();
    ClassDBContext clDB = new ClassDBContext();
    StudentDBContext stuDB = new StudentDBContext();

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    @SuppressWarnings("empty-statement")
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String group = request.getParameter("groupid");
        String slot = request.getParameter("slotid");
        String date = request.getParameter("dateid");
        ArrayList<Class1> classid = clDB.getClassID(date, slot, group);

        int a = 0;
        for (Class1 class1 : classid) {
            a = class1.getClassid();
        }
        Cookie claid = new Cookie("id", "" + a);
        claid.setMaxAge(3600);
        response.addCookie(claid);

        //neu slot da duoc diem danh se di thang den detail
        AttendanceDBContext db = new AttendanceDBContext();
        ArrayList<Attendance> isExist = db.getClassId("" + a);
        if (isExist.size() > 0) {
            response.sendRedirect("listdetail");
        }
        try {
            if (classid.size() > 0) {
                ArrayList<Student> students = stuDB.getAllStudentofGroup(group);
                request.setAttribute("students", students);
                ArrayList<Group> groups = grDB.getAllGroup();
                ArrayList<Class1> Class1 = clDB.getSlot();
                ArrayList<Class1> Class2 = clDB.getDate();
                request.setAttribute("groups", groups);
                request.setAttribute("slots", Class1);
                request.setAttribute("dates", Class2);
                String groupName = grDB.getGroupNameByID(group);
                request.setAttribute("groupid", groupName);
                request.setAttribute("slotid", slot);
                request.setAttribute("dateid", date);
                //request.getRequestDispatcher("/attandance/listAttan.jsp").forward(request, response);
                request.getRequestDispatcher("/attandance/add.jsp").forward(request, response);
            } else {
                try (PrintWriter out = response.getWriter()) {                             
                    out.println("<h1> This class does not exist </h1>");
                    out.println("<input type=\"submit\" value=\"Return\" onclick=\"window.location.href='http://localhost:43899/Prj301_Block5/list'\"/>");                  
                }
            }
        } catch (Exception e) {
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
