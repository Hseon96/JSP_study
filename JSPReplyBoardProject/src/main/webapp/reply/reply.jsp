<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.sist.dao.*,java.util.*"%>
<jsp:useBean id="dao" class="com.sist.dao.ReplyBoardDAO"/>
<%
	// 1. 데이터 받기(요청) => 페이지
	String strPage=request.getParameter("page");
	if(strPage==null)
		strPage="1";
	int curpage=Integer.parseInt(strPage);
	List<ReplyBoardVO> list=dao.boardListData(curpage);
	
	String today=new SimpleDateFormat("yyyy-MM-dd").format(new Date());
	
	int count=dao.boardRowCount();
	int total=(int)(Math.ceil(count/10.0));
	count=count-((curpage*10)-10); //이 세줄 코드가 번호가 일률적으로 나오게끔하는 코드
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="">
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="text-right">
			<img src="back1.png">
			</div>
		</div>
	<div class="row">
	  <div style= "height: 420px"> <!--넘길때 틀 고정시키는 스타일  -->
		<table class="table">
			<tr class="warning">
				<th class="text-center" width=10%>번호</th>
				<th class="text-center" width=45%>제목</th>
				<th class="text-center" width=15%>이름</th>
				<th class="text-center" width=20%>작성일</th>
				<th class="text-center" width=10%>조회수</th>
			</tr>
			<%
				for(ReplyBoardVO vo:list)
				{
				%>
					<tr>
						<td class="text-center" width=10%><%=count--%></td>
						<td width=45%>
						<%
							if(vo.getGroup_tab()>0) // 답글 맨앞간격주는거
							{
								for(int i=0;i<vo.getGroup_tab();i++)
								{
									out.println("&nbsp;&nbsp;"); // 두칸 띄기
								}
								%>
									<img src="re_icon.gif">
								<% 
							}
						
						%>
						
						<%=vo.getSubject() %>
						&nbsp;
						<%
							if(today.equals(vo.getDbday()))
							{
						%>
							<sup><img src="new.gif"></sup>
						<% 
							}
						%>
						</td>
						<td class="text-center" width=15%><%=vo.getName()%></td>
						<td class="text-center" width=20%><%=vo.getRegdate()%></td>
						<td class="text-center" width=10%><%=vo.getHit()%></td>
					</tr>
				<% 
				}
			%>
			</table>
			</div>
		</div>
			<div class="row">
				<div class="text-center">
					<a href="reply.jsp?page="<%=curpage>1?curpage-1:curpage %> class="btn btn-sm btn-primary">이전</a>
					<%= curpage %> page / <%=total %> pages
					<a href="reply.jsp?page="<%=curpage<total?curpage+1:curpage %> class="btn btn-sm btn-primary">다음</a>
				</div>
			</div>
	</div>
	</div>
	
</body>
</html>