<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!DOCTYPE html>
<html>
<style>
   <%@include file='styles/menu.css' %>
</style>
 <head>
    <meta charset="UTF-8">
    <title>Notices List</title>
    <style>
   .blink {
    animation: blink 5s infinite; /* Параметры анимации */
   }
   @keyframes blink {
    from { opacity: 0; /* Непрозрачный текст */ }
    to { opacity: 2; /* Прозрачный текст */ }
   }
  </style>
 </head>
 <body>
     <jsp:include page="_header.jsp"></jsp:include>
     <jsp:include page="_menu.jsp"></jsp:include>
    <h3>Объявления</h3>
 
    <p style="color: red;">${errorString}</p>
    
   <table>
   
       <c:forEach items="${noticesList}" var="notices" >
          
          <tr>
          <td><p>${notices.notices_date}</p> </td>
          <td><p>${notices.content}</p> </td>
           <td>
          <c:choose>
    <c:when test="${notices.user_name == 'Admin'}">
        <p><font color="red"> ${notices.user_name}</font></p>
    </c:when>
    <c:when test="${notices.user_name == 'Koly'}">
        <p><font color="green"> ${notices.user_name}</font></p>
    </c:when>
    <c:when test="${notices.user_name == 'Pety'}">
        <p><font color="Brown"> ${notices.user_name}</font></p>
    </c:when>
    <c:otherwise>
       <p><font color="black"> ${notices.user_name}</font></p>
    </c:otherwise>
    </c:choose>
          </td>
          <td>
          <ul class="menu">
          <c:choose>
            <c:when test="${user == notices.user_name}">
    <li><p class="blink">Управление</p>
            
        <ul class="submenu">
            <li><a href="editNotices?notices_id=${notices.notices_id}">Изменить</a></li>
            <li><a href="deletenotices?notices_id=${notices.notices_id}" >Удалить</a></li> 
        </ul>
        </li>
         </c:when>
    </c:choose>
        </ul>
          </td>
          </tr>
             
       </c:forEach>
      
  </table>
  
  <a href="createNotices" >Добавить объявление</a>
 
 </body>
</html>