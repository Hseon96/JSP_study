<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--
	JSTL / EL 
	----------
	EL
		사용법 : ${}
			   ${자바의 일반 변수(X)}
			  * ${request.getAttribute()} =>출력후 종료
			  * ${session.getAttribute()} =>출력하고 데이터 유지
			   ${application.getAttribute()}
			   ${request.getParameter()}
			   ${request.getParameterValues()}
			   => request, session, application => JSP 내장 객체 
			   	  => 기존의 저장된 데이터 + 추가데이터(setAttribute)를 첨부할 수 있다
		================================================================== 
		MVC(MV) => 재사용 , 확장기능 (자바,HTML분리) => JSP단점은 1회용
												보안이 취약 (전송할때 JSP는 파일을 전체 전송) 
		
		JSP   ============   Java    ========================= 오라클 
		 		request					요청된 데이터 가지고 오기
		 					<=======
		 				request, session에 실어서 결과값을 추가해서 전송
		 				ㄴ setAttribute로 
		--------------------------------------------------------------------
		 EL 연산자 : 
		 			산술연산자 : + ,- ,* , /(div) ,%(mod)
		 							   ------정수/정수=실수
		 					 "10"+"20" => 1020(x) , 30(o)
		 					 null + 1 => 1  null은(0으로 처리)
		 					 *** 문자열 결합은 += 
		 			논리연산자 : &&(and) , ||(or)
		 			비교연산자 : == , != , < , > , <= , >=
		 			** 모든 데이터 비교가 가능하다
		 			 
	JSTL : 제어문 , 변수 선언 , 화면이동 , 날짜변환 , 숫자변환, String 메소드 
		   ----------------------------------------------------- 태그형으로 제작
		   *** 분산
		       HTML/CSS   JavaScript   JAVA    SQL   => 분리한걸 다 묶는게 Controller
		       --------   ----------   ----    ---
					|		  |			|		|
				 퍼블리셔       Front     Back    DBA
		*** JSP는 자바 코딩없이 태그형 프로그램만 제어  ==> JSP(HTML:타임리프) => VUE,REACT
		XML로 제작
		--------- 대소문자 구분 , 여는 태그와 닫는 태그가 동일, 지원하는 속성만 사용이 가능, 속성값 반드시 ""
		--------- MyBatis / JPA / Spring / Spring Boot
		--------- VueJs, ReactJS ==> jsx(JavaScript+XML)
		--------- JSON / Annotation
		--------- RestFul
		545page
		1. <c:set>
			<c:set var="" value=""> : 변수 설정 ===> MVC에서는 사용 빈도가 없다
				   -----  --------
				   변수명     값   ==> ${var에 저장된 변수}
		2. <c:out> : 화면 출력 (JavaScript) ==> Jquery => $
					 $('태그,id,class').click() ==> JS연동
					 <c:out value="">
					 		 ---- 출력할 내용
		3. <c:forEach> : for문
		   -----------
		     일반 for
		     				int i=1    i<=10   i++
		     <c:forEach var="i" begin="" end="" step="">   --> step="1" 생략이 가능, -는 사용할 수 없다 
		     			---------------  ------ -------
		     			 루프변수    초기값     조건    증가값	
		     
		     forEach구문
		     	for(String name:list)
		     	<c:forEach var="name" items="list" varStatue="s">
		     			  ----  ---------            ----------
		     			  받는 변수    배열/컬렉션           인덱스번호
		4. *** <c:if> : if
		   <c:if test="조건문">
		   		------        ===========> <c:else>가 없다
		5. <c:choose> : 다중 조건문
		   <c:choose> 
		   		<c:when test="조건문">출력</c:when> if
		   		<c:when test="조건문">출력</c:when> else if
		   		<c:when test="조건문">출력</c:when> else if
		   		<c:otherwise></c:otherwise>      else
		   </c:choose>
		6. *** <c:redirect> : sendRedirect("url") : 화면이동
			<c:redirect url=""> 
		7. <fmt:formatDate> : SimpleDateFormat
		    ==> $이용하지않고 화면 출력
		    ==> <fmt:formatDate value="${today}" pattern="yyyy-MM-dd">
		    		TO_CHAR(regdate,'YYYY-MM-DD')
		8. <fmt:formatNumber> : NumberFormat
		    ==> <fmt:formatNumber value="10000" pattern="99,999"> 세자리앞에 ,찍어주라
		          TO_CHAR(won,'L000,000')
  --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>
</html>