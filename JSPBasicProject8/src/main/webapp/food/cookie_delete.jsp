<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    <%
    	Cookie[] cookies=request.getCookies();
    	if(cookies!=null)
    	{
			for(int i=0;i<cookies.length;i++)
			{
				if(cookies[i].getName().startsWith("f"))
				{
					cookies[i].setPath("/");
					cookies[i].setMaxAge(0); //setMaxAge을 0 주면 삭제다!
					response.addCookie(cookies[i]); // addCookie 도 줘야 삭제
				}
			}
    	}
    	
    	response.sendRedirect("food_list.jsp"); // 다시 목록으로 이동해서 보여줘야됨 //html없애는 부분은 다 이동!
    %>