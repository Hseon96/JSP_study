package com.sist.model;
import java.util.*;
import java.text.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sist.dao.*;
public class BoardModel {
	// list.jsp에서 처리해야하는데 언제든지 값만 재사용가능하게 java로 분리시킨거.
	// 그래서 여기서 처리해서 call by reference방식.. 주소에 값을 넣어서 보내는것 = 거기서는 부르기만 하면됨.
	public void boardListData(HttpServletRequest request)
	{					//   ------------------- 얘를 불러서 setAttribute로 값을 받는다
		ReplyBoardDAO dao=new ReplyBoardDAO();
		String page=request.getParameter("page");
		if(page==null)
			page="1";
		int curpage=Integer.parseInt(page);
		List<ReplyBoardVO> list=dao.boardListData(curpage);
		int count=dao.boardRowCount();
		int totalpage=(int)(Math.ceil(count/10.0));
		count=count-((curpage*10)-10);
		// jsp로 결과값 전송
		request.setAttribute("curpage", curpage);
		request.setAttribute("totalpage", totalpage);
		request.setAttribute("count", count);
		request.setAttribute("list", list);
		request.setAttribute("msg", "관리자가 삭제한 게시물입니다");
		String today=new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		request.setAttribute("today", today); 
	}
		// 글쓰기
	public void boardInsert(HttpServletRequest request,HttpServletResponse response)
	{
		try
		{
			request.setCharacterEncoding("UTF-8");
		}catch(Exception ex) {}
		String name=request.getParameter("name");
		String subject=request.getParameter("subject");
		String content=request.getParameter("content");
		String pwd=request.getParameter("pwd");
		
		ReplyBoardVO vo=new ReplyBoardVO();
		vo.setName(name);
		vo.setSubject(subject);
		vo.setContent(content);
		vo.setPwd(pwd);
		
		ReplyBoardDAO dao=new ReplyBoardDAO();
		dao.boardInsert(vo);
		
		// 이동
		try
		{
		response.sendRedirect("list.jsp");
		}catch(Exception ex) {}
		// JSP는 화면에 출력, 모든처리=Java에서 한다
		// Model ==> vo,dao,model...
	}
	// 상세보기 
	public void boardDetailData(HttpServletRequest request)
	{
		// detail.jsp?no=${vo.no}
		String no=request.getParameter("no");
		ReplyBoardDAO dao=new ReplyBoardDAO();
		ReplyBoardVO vo=dao.boardDetailData(Integer.parseInt(no)); // 게시물 번호로 값을 찾아왔다
		//JSP로 전송 --> 무조건 setAttribute로 받으면 됨
		request.setAttribute("vo", vo);
		
	}
	//		 -->            <-- 이리갔다 저리갔다
	// JSP -Controller - model - DAO
	//      ㄴ메소드호출                   
	// ================ JSP (요청) : 클릭, 입력, 마우스 클릭 ===> MODEL에서 받아서 처리 === 결과값 ==> JSP
	//                          우선순위: 항상 자바코딩이 먼저 (맨위에 있음)
	
	
	// 답변  response가 화면 이동의 역할.. 
	public void boardReplyInsert(HttpServletRequest request,HttpServletResponse response)
	{
		try
		{
			request.setCharacterEncoding("UTF-8");
		}catch(Exception ex) {}
		String name=request.getParameter("name");
		String subject=request.getParameter("subject");
		String content=request.getParameter("content");
		String pwd=request.getParameter("pwd");
		String pno=request.getParameter("pno"); // 상위 게시물
		
		ReplyBoardVO vo=new ReplyBoardVO();
		vo.setName(name);
		vo.setSubject(subject);
		vo.setContent(content);
		vo.setPwd(pwd);
		// insert, update, delete, reply => 오라클 처리 (화면은 없다) ==> 화면이동 (list, detail(update는 여기로) 나머지는 list로)
		// DAO 연동
		ReplyBoardDAO dao=new ReplyBoardDAO();
		dao.replyInsert(Integer.parseInt(pno),vo);
		// 화면이동 (list.jsp 목록으로 이동) 
		try
		{
		  response.sendRedirect("list.jsp"); // 다시 호출해서 화면을 이동시킨다..
		}catch(Exception ex) {}
	}
	public void boardUpdateData(HttpServletRequest request)
	{
		// update.jsp?no=${vo.no }
		String no=request.getParameter("no");
		// DAO에서 데이터 읽기
		ReplyBoardDAO dao=new ReplyBoardDAO();
		ReplyBoardVO vo=dao.boardUpdateData(Integer.parseInt(no));
		// 읽은 데이터를 request에 담아서 넘겨준다 (update.jsp) setAttribute는 값받아서 jsp에 값넘긴다
		request.setAttribute("vo", vo);
	}
	
	public void boardUpdateOk(HttpServletRequest request)
	{
		try
		{
			request.setCharacterEncoding("UTF-8");
		}catch(Exception ex) {}
		String name=request.getParameter("name");
		String subject=request.getParameter("subject");
		String content=request.getParameter("content");
		String pwd=request.getParameter("pwd");
		String no=request.getParameter("no"); // 상위 게시물
		
		ReplyBoardVO vo=new ReplyBoardVO();
		vo.setName(name);
		vo.setSubject(subject);
		vo.setContent(content);
		vo.setPwd(pwd);
		vo.setNo(Integer.parseInt(no));
		// DAO연동
		ReplyBoardDAO dao=new ReplyBoardDAO();
		// 결과값 전송
		boolean bCheck=dao.boardUpdate(vo);
		request.setAttribute("bCheck", bCheck); //값 넘어갔다는 뜻
		request.setAttribute("no", no);
	}
	public void boardDelete(HttpServletRequest request)
	{
		String no=request.getParameter("no");
		String pwd=request.getParameter("pwd");
		// DAO연동
		ReplyBoardDAO dao=new ReplyBoardDAO();
		// 결과값 request에 담는다 
		String res=dao.boardDelete(Integer.parseInt(no), pwd);
		request.setAttribute("res", res); //yes 나 no
		
	} 
	
	
}









