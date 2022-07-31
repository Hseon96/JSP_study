package com.sist.model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sist.controller.Controller;
import com.sist.controller.RequestMapping;

/*
 * 		public String display(int a,int b);
 * 
 * 		display(10) 매개변수 a,b 두개를 호출했기에 
 * 					받는것도 두개를 받아야한다..순서도 중요! 갯수중요!
 */

@Controller     // model은 무조건 @controller 올려놔야한다
public class FoodModel {
	// requestMapping 형태 똑같아서 복붙해서 넣기 받는건 String
	/*@RequestMapping("food/category.do")
	public String food_category(HttpServletRequest request,HttpServletResponse response)
	{
		request.setAttribute("msg", "맛집 카테고리!!");
		return "../food/category.jsp"; // 누구한테 넘어가냐면..
	}*/
	@RequestMapping("food/list.do")
	public String food_list(HttpServletRequest request,HttpServletResponse response)
	{
		request.setAttribute("msg", "카테고리별 맛집 출력!!");
		request.setAttribute("main_jsp", "../food/list.jsp");
		return "../main/main.jsp"; // 누구한테 넘어가냐면..
	}
	@RequestMapping("food/detail.do")
	public String food_detail(HttpServletRequest request,HttpServletResponse response)
	{
		request.setAttribute("msg", "맛집 상세보기!!"); //데이터 보내고
		request.setAttribute("main_jsp", "../food/detail.jsp"); //include되는거 보내고
		return "../main/main.jsp"; // 누구한테 넘어가냐면..
	}
	@RequestMapping("food/location.do")
	public String food_location(HttpServletRequest request,HttpServletResponse response)
	{
		request.setAttribute("msg", "지역별 맛집 찾기!!");
		request.setAttribute("main_jsp", "../food/location.jsp");
		return "../main/main.jsp"; // 누구한테 넘어가냐면..
	}
}
