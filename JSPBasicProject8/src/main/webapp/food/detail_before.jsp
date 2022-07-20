<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	String fno=request.getParameter("no"); // fno = no 둘이 안같아도 된다 ?no로 넘겼기 때문에
	Cookie cookie=new Cookie("f"+fno,fno); //(키,값) //"f"+fno이 이름중복되면 안된다,한번만수행=> 한개만 출력되게 덮어써진다
	cookie.setPath("/");		// f5..f6.. ㄴ> 모든 URL 중 맛집food로 시작하는 것만 쿠키가져와라! 
	cookie.setMaxAge(60*60*24); //하루가 지나면 사라짐
	response.addCookie(cookie);
	// 이동
	response.sendRedirect("food_detail.jsp?no="+fno); //어디로 이동?
	/*
			화면 이동 / 데이터 전송 
			---------------
			GET
				자바스크립트 : location.href
				HTML : <a> , <form> 
				자바 : sendRedirect , forward 
			POST 
				자바스크립트 : ajax, axios (RestFul) --> List => [] , VO => {}  ---> JSON
				HTML : <form> 
				자바는 모든형식이 GET밖에 안됨
	*/
	
%>
