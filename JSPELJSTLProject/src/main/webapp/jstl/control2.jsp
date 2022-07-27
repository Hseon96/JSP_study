<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	List<String> list=new ArrayList<String>();
	list.add("홍길동");
	list.add("심청이");
	list.add("춘향이");
	list.add("박문수");
	list.add("이순신");
	
	//request.setAttribute("list", list); 이렇게 쓰지말고 밑에처럼
%>
<c:set var="list" value="<%=list %>"/> <!-- $쓰면 위에 request필요하니깐 쓰면 안됨  -->
<%--
	request.setAttribute("list", list); 키,값  == 둘이 동일한 문장 
 --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3>자바 for문(일반)</h3>
	<%
		for(int i=1;i<=10;i++)
		{
	%>
		<%=i %>&nbsp;
	<% 
		}
	%>
	<h3>JSTL for문(일반)</h3>  	<!-- 이렇게 코딩해라 -->
	<%-- end는 포함 --%>
		<c:forEach var="i" begin="1" end="10" step="1"> <%--step이 1일 경우에 생략이 가능, 증가는 가능! 감소는 할수없다x --%>
			${i }&nbsp;
		</c:forEach>
		
	<h3>자바 for-Each</h3>
	<%
		for(String name:list)
		{
	%>
		<%=name %><br>
	<% 
		}
	%>		
	<h3>JSTL for-Each</h3>		<!-- 이렇게 코딩해라 -->
		<c:forEach var="name" items="${list }">
			${name }<br>
		</c:forEach>
		
		
	<h3>자바 데이터 구분</h3>
	<%
		String data="red,green,blue,yellow,white";
		StringTokenizer st=new StringTokenizer(data,",");
		while(st.hasMoreTokens())
		{
		%>
			<%=st.nextToken() %>&nbsp;
		<% 
		}
	%>
	<h3>JSTL SpringTokenizer</h3> <!-- 이렇게 코딩해라 -->
	<c:forTokens items="red,green,blue,yellow,white" delims="," var="color">
		${color }&nbsp;
	</c:forTokens>
</body>
</html>