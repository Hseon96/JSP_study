<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body> <!--include 하면 경로 바뀐다!! login파일을 main.jsp로 붙여넣기하면 main으로 경로이동  -->
	<div class="row">
	 <form method=post action="../member/login_ok.jsp">
		<table class="table">
			<tr>
				<th class="text-right" width=30%></th>
				<td width=70%>
					<input type=text name=id size=15 class="input-sm" required> <!--required 로그인 눌러도 안넘어감 로그인 강제입력  -->
					<!-- 해상도 높음 sm md lg xs 해상도 낮음 -->
					</td>
			</tr>
			<tr>
				<th class="text-right" width=30%></th>
				<td width=70%>
					<input type=password name=pwd size=15 class="input-sm" required>
					<!-- 해상도 높음 sm md lg xs 해상도 낮음 -->
					</td>
			</tr>
			<tr>
				<td colspan="2" class="text-center">
					<button class="btn btn-sm btn-danger">로그인</button>
					</td>
			</tr>
		</table>
		</form>
	</div>
</body>
</html>