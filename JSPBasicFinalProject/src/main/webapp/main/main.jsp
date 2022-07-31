<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    	String id=(String)session.getAttribute("id");
    /*
    		Session : 저장 위치 = 서버 
    				저장되는 내용 => 프로그램 종료시 (logout, 브라우저 종료시까지 저장해야 되는 데이터)
    					=> id ,name ... (장바구니:임시로 저장) 
    					=> session 사용 : 프로젝트에 있는 모든 JSP에서 데이터 공유가 가능
    					=> HttpSession 클래스의 객체 
    					   HttpSession session=request.getSession()
    					   					   ------- session 생성, cookie생성
    					=> 주요기능
    						1) 저장	: setAttribute(key,값) => cookie는 문자열만 저장이 가능
    												   -- => Object로 저장이 가능
							2) 저장 값 읽기 : Object getAttribute(key) => 형변환 
							3) 삭제 (일부) : removeAttribute(key)
							4) 전체 (삭제) : invalidate() => logout
							5) 기간 설정 ==> 30분 setMaxactiveInterval(1800) => 초 
						=> getAttribute() => null => 로그인이 안된 상태
											실제 데이터 => 로그인이 된 상태 
    */
    String log_jsp="";
	if(id==null)
	{
		log_jsp="../member/login.jsp";
	}
	else
	{
		log_jsp="../member/logout.jsp";
	}
	
	String[] jsp_list={
			"",
			"../food/category.jsp",  //1
			"../food/food_list.jsp", //2
			"../food/food_detail.jsp" //3 메인 주소에서 뒤에 주소 1,2,3번누르면 메인페이지에서 출력
	};
	// 사용자가 요청 => 화면을 보여달라
		String mode=request.getParameter("mode");
		if(mode==null)
			mode="1";
		int index=Integer.parseInt(mode);
		String jsp=jsp_list[index];
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<style type="text/css">
.container{
	margin-top: 20px;
	width: 1200px;
}
/* .col-sm-2{
	height : 800px;
	border : 1px solid red;
}
.col-sm-10{
	height: 800px;
	border: 1px solid green;
} */ /*어떻게 나눠져있는지만 확인   */
</style>
</head>
<body>
	<jsp:include page="../main/header.jsp"></jsp:include><!--파일이 바뀌었으니깐 header.jsp가 아니라 main.jsp로 생각해야한다  -->
	<div class="container">
	<div class="col-sm-3">
	  <jsp:include page="<%=log_jsp %>"></jsp:include> <!--include되면 main영역으ㅡ로  -->
	</div>
	<div class="col-sm-9"> <!--12까지있는데 둘이 더해서 12만 되면됨! 3개로 나누면 알아서  -->
		<jsp:include page="<%=jsp %>"></jsp:include>
	</div>
</body>
</html>