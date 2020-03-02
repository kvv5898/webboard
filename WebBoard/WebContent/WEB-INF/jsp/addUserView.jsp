<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 
<!DOCTYPE html>
<html>
   <head>
      <meta charset="UTF-8">
      <title>Add Users</title>
   </head>
   <body>
       <jsp:include page="_header.jsp"></jsp:include>
       <jsp:include page="_menu.jsp"></jsp:include>
      <h3>Регистрация нового пользователя</h3>
       
      <p style="color: red;">${errorString}</p>
       
      <form method="POST" action="${pageContext.request.contextPath}/adduser">
         <table>
            <tr>
               <td>Имя</td>
               <td><input type="text" name="user_name" value="${user_account.user_name}" /></td>
               <td>E-mail</td>
               <td><input type="text" name="email" value="${user_account.email}" /></td>
               <td>Пароль</td>
               <td><input type="text" name="password" value="${user_account.password}" /></td>
            </tr>
            <tr>
               <td colspan="2">                   
                   <input type="submit" value="Применить" />
                   <a href="home">Отмена</a>
               </td>
            </tr>
         </table>
      </form>
      </body>
      </html>
       