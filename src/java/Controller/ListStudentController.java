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
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet AttandanceController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AttandanceController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

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
        String groupid = request.getParameter("groupid");
        String slot = request.getParameter("slotid");
        String date = request.getParameter("dateid");
        ArrayList<Class1> classid = clDB.getClassID(date, slot, groupid);
       // request.setAttribute("groupid", groupid);          
        ArrayList<Student> students = stuDB.getAllStudentofGroup(groupid);
        request.setAttribute("students", students);
          int a =0;
          for (Class1 class1 : classid ) {
          a = class1.getClassid();
        }
        Cookie claid = new Cookie("id",""+a);      
        claid.setMaxAge(3600);
        response.addCookie(claid);
        
        //neu slot da duoc diem danh se di thang den detail
        AttendanceDBContext db = new AttendanceDBContext();
        ArrayList<Attendance> isExist = db.getClassId(""+a);
        if (isExist.size() > 0) {
//           DetailAttendanceDBContext clDB = new DetailAttendanceDBContext();
//            ArrayList<DetailAttendance> detailAt = (ArrayList<DetailAttendance>) clDB.getDetailAttandanceClass(""+a);
//            if (detailAt.size() > 0) {
//                request.setAttribute("detailAt", detailAt);
//                request.getRequestDispatcher("/attandance/detail.jsp").forward(request, response);
//            }
             response.sendRedirect("listdetail");
        }
        try{
        if (classid.size() > 0) {
          request.getRequestDispatcher("/attandance/listAttan.jsp").forward(request, response);
        } else {
           try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ArticleController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1> This class does not exist </h1>");
            out.println("<input type=\"submit\" value=\"Return\" onclick=\"window.location.href='http://localhost:43899/Prj301_Block5/list'\"/>");
            out.println("</body>");
            out.println("</html>");
        }    
        }
        }catch(Exception e){}
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
