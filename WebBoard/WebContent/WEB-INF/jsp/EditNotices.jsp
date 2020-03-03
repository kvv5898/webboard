<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
   <head>
      <meta charset="UTF-8">
      <title>Edit Notices</title>
   </head>
   <body>
 
      <h3>Редактирование объявления</h3>
 
      <p style="color: red;">${errorString}</p>
 
         <form method="POST" action="${pageContext.request.contextPath}/editnotices">
            <table border="1">
               <tr>
                   <td>NOTIСES_ID</td>
                   <td style="color:red;"><input type="hidden" name="notices_id" value="${Notices.notices_id}" />${Notices.notices_id}</td>
               </tr>
               <tr>
                   <td>USER_NAME</td>
                   <td style="color:red;"><input type="hidden" name="user_name" value="${Notices.user_name}" />${Notices.user_name}</td>
               </tr>
               <tr>
                  <td>CONTENT</td>
                  <td><input type="text" name="content" value="${Notices.content}" /></td>
               </tr>
               <tr>
                  <td colspan = "2">
                      <input type="submit" value="Применить" />
                      <a href="${pageContext.request.contextPath}/notices">Назад</a>
                  </td>
               </tr>
            </table>
         </form>
   </body>
</html>