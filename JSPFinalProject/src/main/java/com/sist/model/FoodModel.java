package com.sist.model;
import com.sist.controller.Controller;
import com.sist.controller.RequestMapping;
import java.util.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sist.dao.*;
import com.sist.vo.*;
@Controller
public class FoodModel {
	@RequestMapping("food/food_list.do") // 카테고리별 맛집 리스트
	public String food_list(HttpServletRequest request,HttpServletResponse response)
	{
		 String cno=request.getParameter("cno");
		   // cno ==> DAO ==> 데이터 읽기
		   List<FoodVO> list=FoodDAO.foodListData(Integer.parseInt(cno));
		   FoodCategoryVO vo=FoodDAO.foodCategoryInfoData(Integer.parseInt(cno));
		   request.setAttribute("list", list);
		   request.setAttribute("vo", vo);
		   request.setAttribute("main_jsp", "../food/food_list.jsp");
		   return "../main/main.jsp";//forward
	}
	
	// 쿠키 전송
	@RequestMapping("food/food_detail_before.do")
	public String food_detail_before(HttpServletRequest request,HttpServletResponse response)
	{
		// 쿠키 전송 => 화면 출력 (x) --출력은 forward
		String fno=request.getParameter("fno");
		Cookie cookie=new Cookie("food"+fno, fno);
		cookie.setPath("/");
		cookie.setMaxAge(60*60*24);
		response.addCookie(cookie);
		// insert_ok.do ==> list.do로 이동해라 --redirect사용
		// => 상세보기로 이동
		 return "redirect:../food/food_detail.do?fno="+fno; //request 초기화 => 화면 이동 (sendRedirect())
	}
	/*
	 * 	Model 
	 *   1. 사용자 요청 데이터 받기 (==없을수도있다)
	 *   2. 데이터베이스 연결 (핵심)
	 *   3. request에 값 담기
	 *   4. 어떤 JSP로 보낼지 설정
	 *   
	 *   JSP 
	 *   	request에 있는 데이터 출력 
	 */
	@RequestMapping("food/food_detail.do")
	public String food_detail(HttpServletRequest request,HttpServletResponse response)
	{
		String fno=request.getParameter("fno");
		// 데이터베이스 연동
		FoodVO vo=FoodDAO.foodDetailData(Integer.parseInt(fno));
		// request에 담아서 넘겨준다
		//서울특별시 관악구 행운1길 79 상가 104호 지번 서울시 관악구 봉천동 1668-21 상가 104호
		//------------------------------1   -------------------------------2
		String address=vo.getAddress();
		String addr1=address.substring(0,address.lastIndexOf("지"));
		String addr2=address.substring(address.lastIndexOf("지")+2);
		request.setAttribute("addr1", addr1.trim());
		request.setAttribute("addr2", addr2.trim());  // 지도출력..
		request.setAttribute("vo", vo);
		request.setAttribute("main_jsp", "../food/food_detail.jsp");
		return "../main/main.jsp";
	}
	
	
	
}







