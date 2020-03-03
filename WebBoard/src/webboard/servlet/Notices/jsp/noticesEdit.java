package webboard.servlet.Notices.jsp;

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
import webboard.servlet.Notices.mysql.Edit;
import webboard.servlet.Notices.mysql.Info;
import webboard.tabl.Notices;
import webbordlog.logUser;

@WebServlet(urlPatterns = { "/editNotices" })
public class noticesEdit extends HttpServlet {
    private static final long serialVersionUID = 1L;
 
    
 
    // Отобразить страницу редактирования заметки.
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
       
        String notices_idStr = (String) request.getParameter("notices_id");
 
        Integer notices_id = null;
        try {
        	notices_id = Integer.parseInt(notices_idStr);
        } catch (Exception e) {
        }
        
        
        Integer Notices_edit = null;
 
        String errorString = null;
 
        try {
        	Notices_edit = Info.find2(conn, notices_id);
        } catch (SQLException e) {
            e.printStackTrace();
            errorString = e.getMessage();
        }
        
        if (errorString != null && Notices_edit == null) {
            response.sendRedirect(request.getServletPath() + "/notices");
            return;
        }
 
        request.setAttribute("errorInt", errorString);
        request.setAttribute("Notices_edit", Notices_edit);
 
        RequestDispatcher dispatcher = request.getServletContext()
                .getRequestDispatcher("/WEB-INF/jsp/EditNotices.jsp");
        dispatcher.forward(request, response);
 
    }
 
    // После того, как пользователь отредактировал информацию и нажал на Submit.
    // Данный метод будет выполнен.
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
        Connection conn = logUser.getStoredConnection(request);
        String notices_idStr = (String) request.getParameter("notices_id");
        
        Integer notices_id = null;
        try {
        	notices_id = Integer.parseInt(notices_idStr);
        } catch (Exception e) {
        }
        
        String user_name = (String) request.getParameter("user_name");
        String content = (String) request.getParameter("content");
        String us_Date = (String) request.getParameter("us_Date");
        String roles = null;
        System.out.println("user_name - " + user_name);
        Notices notices_edit = new Notices(notices_id, user_name, us_Date, content, roles);
 
        String errorString = null;
 
        try {
        	Edit.noticesEDIT(conn, notices_edit);
        } catch (SQLException e) {
            e.printStackTrace();
            errorString = e.getMessage();
        }
        // Сохранить информацию в request attribute перед тем как forward к views.
        request.setAttribute("errorString", errorString);
        request.setAttribute("notices_edit", notices_edit);
 
        // Если имеется ошибка, forward к странице edit.
        if (errorString != null) {
            RequestDispatcher dispatcher = request.getServletContext()
                    .getRequestDispatcher("/WEB-INF/jsp/EditNotices.jsp");
            dispatcher.forward(request, response);
        }
        // Если все хорошо.
        // Redirect к странице со списком продуктов.
        else {
            response.sendRedirect(request.getContextPath() + "/notices");
        }
    }
 
}