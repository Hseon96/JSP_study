<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.sist.model.*"%>
 <jsp:useBean id="model" class="com.sist.model.BoardModel"/>
 <%
 	  //수정 데이터를 읽어 와라 ==> BoardModel     // JSTL -> 제어문(<c:set>이런거) 이 필요할때 EL만 필요!!
 	  model.boardUpdateData(request); // ${}
 	  
 %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<style type="text/css">
.container{
	margin-top: 50px;
}
.row{
	margin: 0px auto;
	width: 750px;
}

</style>
</head>
<body>
	<div class="container">
			<div class="row">
					<div class="text-right">
						<img src="back1.png" style="width:250px;height: 150px">
					</div>
				</div>
			<div style="height: 20px"></div>
			<div class="row">
				<form method="post" action="update_ok.jsp"> 
				<%--
					action은 보내고 싶은 곳에 보내주는 역할
						input, select, textarea 여기에 입력한 값만 넘어간다! 
				 --%>
				<table class="table">
					<tr>
						<th width=15% class="text-right">이름</th>
						<td width=85%>
							<input type=text name=name size=15 class="input-sm" required value="${vo.name }">
							<input type="hidden" name=no value="${vo.no }">
							<!--		ㄴ감춰서 보낼때
									 
							  -->
						</td>
					</tr>
					<tr>
						<th width=15% class="text-right">제목</th>
						<td width=85%>
							<input type=text name=subject size=50 class="input-sm" required value="${vo.subject }">
						</td>
					</tr>
					<tr>
						<th width=15% class="text-right">내용</th>
						<td width=85%>
							<textarea rows="10" cols="52" name="content" required>${vo.content }</textarea>
						</td>
					</tr>
					<tr>
						<th width=15% class="text-right">비밀번호</th>
						<td width=85%>
							<input type="password" name=pwd size=10 class="input-sm" required>
						</td>
					</tr>
					<tr>
						<td colspan="2" class="text-center">
							<button class="btn btn-sm btn-danger">수정하기</button>
							<input type=button value="취소" class="btn btn-sm btn-warning"
							onclick="javascript:history.back()"> <!--취소나오면 javascript:history.back()무조건 써야됨!  -->
						</td>
					</tr>
				</table>
				</form>
			</div>
	</div>
</body>
</html>