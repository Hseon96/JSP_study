<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<header id="header" class="fixed-top d-flex align-items-center">
    <div class="container d-flex align-items-center justify-content-between">

      <div class="logo">
        <!-- Uncomment below if you prefer to use an image logo -->
        <a href="Main.jsp"><img src="../assets/img/logo.png" alt="" class="img-fluid"></a>
        <h1 class="text-light"><a href="Main.jsp"><span>PetFam</span></a></h1>
      </div>

      <nav id="navbar" class="navbar">
        <ul>
<!--           <li><a class="nav-link scrollto active" href="#hero">Home</a></li> -->
          <li><a class="nav-link scrollto" href="../about/about.jsp">소개</a></li>
          <li class="dropdown"><a href="#"><span>예약</span> <i class="bi bi-chevron-down"></i></a>
          <ul>
              <li><a href="../booking/booking_hospital.jsp">병원</a></li>
              <li><a href="#">훈련</a></li>
              <li><a href="#">미용</a></li>
            </ul>
          
          <li><a class="nav-link scrollto" href="#portfolio" hover="Recommendation")>추천</a></li>
          
          
          <li class="dropdown"><a href="#"><span>커뮤니티</span> <i class="bi bi-chevron-down"></i></a>
          <ul>
              <li><a href="#">자유</a></li>
              <li><a href="#">QnA</a></li>
              <li><a href="#">자랑</a></li>
            </ul>
            
            
          <li class="dropdown"><a href="#"><span>마이</span> <i class="bi bi-chevron-down"></i></a>
            <ul>
              <li><a href="#">Drop Down 1</a></li>
              <li class="dropdown"><a href="#"><span>Deep Drop Down</span> <i class="bi bi-chevron-right"></i></a>
                <ul>
                  <li><a href="#">Deep Drop Down 1</a></li>
                  <li><a href="#">Deep Drop Down 2</a></li>
                  <li><a href="#">Deep Drop Down 3</a></li>
                  <li><a href="#">Deep Drop Down 4</a></li>
                  <li><a href="#">Deep Drop Down 5</a></li>
                </ul>
              </li>
              <li><a href="#">Drop Down 2</a></li>
              <li><a href="#">Drop Down 3</a></li>
              <li><a href="#">Drop Down 4</a></li>
            </ul>
          </li>
          <li><a class="nav-link scrollto" href="#contact">연락하기</a></li>
          <li><a class="getstarted scrollto" href="#about">Get Started</a></li>
        </ul>
        <i class="bi bi-list mobile-nav-toggle"></i>
      </nav><!-- .navbar -->

    </div>
  </header>
</body>
</html>