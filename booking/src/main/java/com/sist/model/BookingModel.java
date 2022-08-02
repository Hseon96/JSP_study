package com.sist.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sist.controller.Controller;
import com.sist.controller.RequestMapping;
import com.sist.dao.BookingDAO;
import com.sist.vo.BookingVO;

@Controller
public class BookingModel {
	 @RequestMapping("booking/hospital.do")
	   public String booking_hospital(HttpServletRequest request,HttpServletResponse response)
	   {
		   String page=request.getParameter("page");
		   if(page==null)
			   page="1";
		   
		   int curpage=Integer.parseInt(page);
		   Map map=new HashMap();
		   int rowSize=15;
		   int start=(rowSize*curpage)-(rowSize-1); // rownum=1
		   int end=curpage*rowSize;
		   
		   map.put("end", end); // #{end}
		   map.put("start", start); // #{start}
		   map.put("table_name","booking_hospital"); //${table_name}
		   List<BookingVO> list=BookingDAO.bookingListData(map);
		   int totalpage=BookingDAO.bookingTotalPage(map);
		   
		   final int BLOCK=5;
		   int startPage=((curpage-1)/BLOCK*BLOCK)+1;
		   /*
		    *   [1][2][3][4][5]  => startPage =1 
		    *                       endPage   =5
		    *   [6][7][8][9][10] => startPage=6
		    *                       endPage=10
		    */
		   int endPage=((curpage-1)/BLOCK*BLOCK)+BLOCK;
		   
		   if(endPage>totalpage)
			    endPage=totalpage;
		   
		   request.setAttribute("curpage", curpage);
		   request.setAttribute("totalpage", totalpage);
		   request.setAttribute("startPage", startPage);
		   request.setAttribute("endPage", endPage);
		   request.setAttribute("list", list);
		   
		   request.setAttribute("main_jsp", "../booking/hospital.jsp");
		   return "../main/main.jsp";
	   }
	 
	 @RequestMapping("booking/salon.do")
	   public String booking_salon(HttpServletRequest request,HttpServletResponse response)
	   {
		   String page=request.getParameter("page");
		   if(page==null)
			   page="1";
		   
		   int curpage=Integer.parseInt(page);
		   Map map=new HashMap();
		   int rowSize=15;
		   int start=(rowSize*curpage)-(rowSize-1); // rownum=1
		   int end=curpage*rowSize;
		   
		   map.put("end", end); // #{end}
		   map.put("start", start); // #{start}
		   map.put("table_name","booking_salon"); //${table_name}
		   List<BookingVO> list=BookingDAO.bookingListData(map);
		   int totalpage=BookingDAO.bookingTotalPage(map);
		   
		   final int BLOCK=5;
		   int startPage=((curpage-1)/BLOCK*BLOCK)+1;
		   /*
		    *   [1][2][3][4][5]  => startPage =1 
		    *                       endPage   =5
		    *   [6][7][8][9][10] => startPage=6
		    *                       endPage=10
		    */
		   int endPage=((curpage-1)/BLOCK*BLOCK)+BLOCK;
		   
		   if(endPage>totalpage)
			    endPage=totalpage;
		   
		   request.setAttribute("curpage", curpage);
		   request.setAttribute("totalpage", totalpage);
		   request.setAttribute("startPage", startPage);
		   request.setAttribute("endPage", endPage);
		   request.setAttribute("list", list);
		   
		   request.setAttribute("main_jsp", "../booking/salon.jsp");
		   return "../main/main.jsp";
	   }
	 
	 
	 @RequestMapping("booking/training.do")
	   public String booking_training(HttpServletRequest request,HttpServletResponse response)
	   {
		   String page=request.getParameter("page");
		   if(page==null)
			   page="1";
		   
		   int curpage=Integer.parseInt(page);
		   Map map=new HashMap();
		   int rowSize=15;
		   int start=(rowSize*curpage)-(rowSize-1); // rownum=1
		   int end=curpage*rowSize;
		   
		   map.put("end", end); // #{end}
		   map.put("start", start); // #{start}
		   map.put("table_name","booking_training"); //${table_name}
		   List<BookingVO> list=BookingDAO.bookingListData(map);
		   int totalpage=BookingDAO.bookingTotalPage(map);
		   
		   final int BLOCK=5;
		   int startPage=((curpage-1)/BLOCK*BLOCK)+1;
		   /*
		    *   [1][2][3][4][5]  => startPage =1 
		    *                       endPage   =5
		    *   [6][7][8][9][10] => startPage=6
		    *                       endPage=10
		    */
		   int endPage=((curpage-1)/BLOCK*BLOCK)+BLOCK;
		   
		   if(endPage>totalpage)
			    endPage=totalpage;
		   
		   request.setAttribute("curpage", curpage);
		   request.setAttribute("totalpage", totalpage);
		   request.setAttribute("startPage", startPage);
		   request.setAttribute("endPage", endPage);
		   request.setAttribute("list", list);
		   
		   request.setAttribute("main_jsp", "../booking/training.jsp");
		   return "../main/main.jsp";
	   }
	 
	
	   @RequestMapping("booking/hst_detail.do")
	   public String booking_hst_detail(HttpServletRequest request,HttpServletResponse response)
	   {
		   String o_no=request.getParameter("o_no");
		   String cno=request.getParameter("cno");
		   String table_name="booking_";
		   if(Integer.parseInt(cno)==1)
		   {
			   table_name+="hospital";
		   }
		   else if(Integer.parseInt(cno)==2)
		   {
			   table_name+="salon";
		   }
		   else if(Integer.parseInt(cno)==3)
		   {
			   table_name+="training";
		   }
		   
		   Map map=new HashMap();
		   map.put("o_no",o_no);
		   map.put("table_name", table_name);
		   BookingVO vo=BookingDAO.bookingDetailData(map);
		   
		   request.setAttribute("vo", vo);
		   request.setAttribute("main_jsp", "../booking/hst_detail.jsp");
		   return "../main/main.jsp";
	   }
	}

