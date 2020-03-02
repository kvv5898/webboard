package webboard.servlet.jsp;
 
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import webboard.conn.ConnMySQL;
import webboard.servlet.mysql.edituser;
import webboard.servlet.mysql.finduser;
import webboard.tabl.User_account;
import webbordlog.logUser;

 
@WebServlet(urlPatterns = { "/edituser" })
public class EditUser extends HttpServlet {
    private static final long serialVersionUID = 1L;
 
    
 
    // ���������� �������� �������������� �������.
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection conn = null;
		try {
			conn = ConnMySQL.conni();
		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
       
        String user_idStr = (String) request.getParameter("user_id");
 
        Integer user_id = null;
        try {
        	user_id = Integer.parseInt(user_idStr);
        } catch (Exception e) {
        }
        
        
        User_account User_account_edit = null;
 
        String errorString = null;
 
        try {
        	User_account_edit = finduser.userFIND2(conn, user_id);
        } catch (SQLException e) {
            e.printStackTrace();
            errorString = e.getMessage();
        }
        
        if (errorString != null && User_account_edit == null) {
            response.sendRedirect(request.getServletPath() + "/user");
            return;
        }
 
        request.setAttribute("errorInt", errorString);
        request.setAttribute("User_account", User_account_edit);
 
        RequestDispatcher dispatcher = request.getServletContext()
                .getRequestDispatcher("/WEB-INF/jsp/EditUserAccount.jsp");
        dispatcher.forward(request, response);
 
    }
 
    // ����� ����, ��� ������������ �������������� ���������� � ����� �� Submit.
    // ������ ����� ����� ��������.
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
        Connection conn = logUser.getStoredConnection(request);
        String user_idStr = (String) request.getParameter("user_id");
        
        Integer user_id = null;
        try {
        	user_id = Integer.parseInt(user_idStr);
        } catch (Exception e) {
        }
        
        String user_name = (String) request.getParameter("user_name");
        String email = (String) request.getParameter("email");
        String password = (String) request.getParameter("password");
        String us_Date = (String) request.getParameter("us_Date");
        String[] roles = null;
        System.out.println("user_name - " + user_name);
        User_account user_edit = new User_account(user_id, user_name, email, password, us_Date, roles);
 
        String errorString = null;
 
        try {
        	edituser.userEDIT(conn, user_edit);
        } catch (SQLException e) {
            e.printStackTrace();
            errorString = e.getMessage();
        }
        // ��������� ���������� � request attribute ����� ��� ��� forward � views.
        request.setAttribute("errorString", errorString);
        request.setAttribute("edit_user", user_edit);
 
        // ���� ������� ������, forward � �������� edit.
        if (errorString != null) {
            RequestDispatcher dispatcher = request.getServletContext()
                    .getRequestDispatcher("/WEB-INF/jsp/EditUserAccount.jsp");
            dispatcher.forward(request, response);
        }
        // ���� ��� ������.
        // Redirect � �������� �� ������� ���������.
        else {
            response.sendRedirect(request.getContextPath() + "/user");
        }
    }
 
}