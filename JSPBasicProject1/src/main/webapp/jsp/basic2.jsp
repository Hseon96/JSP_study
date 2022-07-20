<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,java.text.*"%>
    <%@ page import="java.util.*" %>
<%--
	생략할 수 있는 import
	1. java.lang.*
	2. javax.servlet.http =>이건 생략가능 request, response 포함 ..
 --%>
 <%
 	// 1. 사용자가 보내준 데이터 받기 
 	// 2. 데이터베이스 연동
 	// 3. 데이터 읽기
 	// 자바가 먼저 => HTML
 	// MVC ==> Model 데이터받으면 ==> View 출력
 	// insert / update / delete => 화면을 분리 (입력창따로 , 처리창 따로)
 	// 데이터 읽기 => java  , 화면 출력 => HTML  둘이 합친게 JSP 
 	Date date=new Date();
 	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
 	
 %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	오늘 날짜:<%=sdf.format(date) %> <%-- ;을 사용하지 않는다 --%>
	<%--
		<%=은 out.println(=> 여기안에 들어가는 코딩 sdf.format(date) );  
	 --%>
</body>
</html>