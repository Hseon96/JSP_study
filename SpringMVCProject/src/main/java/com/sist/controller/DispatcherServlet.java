package com.sist.controller;

import java.io.*;
import java.lang.reflect.Method;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.*;
import org.w3c.dom.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;
/*
 * 		자바 / HTML (태그형(JSTL) 프로그램 사용) 분리
 * 
 * 		MVC 구조 
 * 		Model : 데이터관리 , 요청을 처리후에 request, session에 전송값을 담아준다
 * 				~Model(조립기) dao+vo를 묶어서 , ~DAO(데이터베이스 연결) , ~VO (데이터 모음) ===> 자바 (재사용, 확장)
 * 				---------------------------------------------------------------
 * 				 jsp  => 첫줄 <% %> 
 * 		View  :  기능은 없다 (Front: JavaScript) => 데이터를 받아서 화면 출력 ==> JSTL/EL ==> jsp
 * 		Controller : Model과 View를 연결시켜주는 역할
 * 					 요청받기 , 결과값 보내기 , 화면 이동
 * 
 * 		순서잘기억!!!*** controller가 서빙 model이 주방 jsp가 식탁 view가 손님
 * 
 * 							  브라우저에서 전송(요청) ===> 자바로는 받을 수 없다 (서블릿, JSP) 
 * 		사용자 요청 (.do) =======> Controller =========> 처리할 Model을 찾는다 <========> DAO 연결
 * 															| =====> request, session에 값을 담는다
 * 															|        request.setAttribute()
 * 															|        session.setAttribute()
 * 														Controller로 다시 이동
 * 															|
 * 														 JSP찾기
 * 															|
 * 														JSP로 request,session을 전송
 * 															|
 * 														화면 출력 => 사용자 브라우저에서 읽어간다
 * 
 * 		====> 파일 공개(JSP) / 비공개 (화면 출력이 아니고 기능) => 서블릿으로 만든다
 * 			Controller : 프레임워크 (Spring, Struts) => 제작됨
 * 				===> 포털 (다음, 네이버)
 * 				===> 프레임 워크 vs 라이브러리 (자바는)
 * 					(레고)		  (완제품)
 * 					마음대로 수정o      x
 * 
 *    *** MVC : Controller를 URL주소로 찾기 		
 *    
 */
@WebServlet("*.do") // ==>확장자 .do(마음대로수정)를 줘야 컨트롤러를 실행할수 있다 
public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private List<String> packList=new ArrayList<String>(); // XML => 패키지 저장
	private List<String> clsList=new ArrayList<String>(); // 클래스를 모은다 
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		try
		{
			URL url=this.getClass().getClassLoader().getResource(".");
			// ==> 현재 프로젝트의 경로
			File file=new File(url.toURI());
			System.out.println(file.getPath());
			String path=file.getPath();
			path=path.replace("\\", File.separator); // 윈도우와 맥을 통합하는 방식
			// File.separator  ==> \\ (window) , / (mac)
			path=path.substring(0,path.lastIndexOf(File.separator));
			System.out.println(path);
			
			// XML 파싱
			/*
			 * 	 JAXP : DOM / SAX (MyBatis, Spring)
			 * 			DOM => 메모리에 트리형태로 저장 후 처리 (수정, 삭제, 추가)
			 * 			SAX => 한줄씩 태그의 값을 읽어 온다 (read only)
			 * 			=> XML
			 * 				데이터 저장위치 => <태그>데이터</태그>
			 * 							  <태그 속성="데이터">
			 * 			=> XML은 사용자 정의 태그 (속성이나 태그를 추가할 수 없다) => 태그, 속성을 다외워야함..
			 * 			   마이바티스 , 스프링 => 자체에서 제작후 지원
			 * 			   -------------- XML은 태그 순서가 있다!! 
			 *   JAXB : 빅데이터 (클래스 변수 === 태그명) ===> 1.8까지 지원
			 *   
			 */
			DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
			DocumentBuilder db=dbf.newDocumentBuilder();
			Document doc=db.parse(new File(path+File.separator+"application.xml"));
			Element root=doc.getDocumentElement();
			System.out.println(root.getTagName());
			
			NodeList list=root.getElementsByTagName("component-scan"); //모아주는거 
			for(int i=0;i<list.getLength();i++)
			{
				Element cs=(Element)list.item(i);
				String value=cs.getAttribute("base-package");
				packList.add(value);
			}
			
			// 출력 
			for(String s:packList) // s.는 패키지
			{
				//System.out.println(s);
				path=path.substring(0,path.lastIndexOf(File.separator));
				System.out.println(path);
				String ss=path+File.separator+"WEB-INF"+File.separator+"classes"+File.separator+s.replace(".", File.separator);
				System.out.println(ss);
				File dir=new File(ss);
				File[] files=dir.listFiles();
				for(File f:files)
				{
					//System.out.println(f.getName());
					// ListModel.class
					String sss=s+"."+f.getName().substring(0,f.getName().lastIndexOf("."));
					// com.sist.model.Listmodel 이렇게 나와야 메모리 할당된다
					System.out.println(sss);
					clsList.add(sss);
					
				}
			}
			// Controller ==> Model 찾기
			
		}catch(Exception ex) {}
	}

	// 위에까진 Model 등록 (메뉴에 대해 알려준거고)
	// Model을 찾아서 요청 수행 => JSP 찾기 => request,session 전송 (그 메뉴에 대한 실제처리)
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try
		{
			// 사용자 URL http://localhost:8080/SpringMVCProject/main/main.do (URL)
            // 												  -------------- 이정도 자를꺼
			// @RequestMapping("main/main.do")
			String uri=request.getRequestURI(); ///SpringMVCProject/main/main.do (URI)
			uri=uri.substring(request.getContextPath().length()+1); ///main/main.do
			// Model클래스 찾기
			for(String strCls:clsList)
			{
				Class clsName=Class.forName(strCls); // 등록된 클래스 정보 읽기
				if(clsName.isAnnotationPresent(Controller.class)==false)
						continue; // 클래스위에 @Controller가 있는지 확인  ==> false 없으면 제외를 하겠다 continue
				// 메모리 할당 
				Object obj=clsName.getDeclaredConstructor().newInstance(); 
				
				// 요청 처리 ==> 메소드로 되어있는걸 찾아와라
				Method[] methods=clsName.getDeclaredMethods(); // Model클래스에 선언된 모든 메소드 읽기
				for(Method m:methods)
				{
					RequestMapping rm=m.getAnnotation(RequestMapping.class);
					// 메소드 위에 있는 어노테이션을 확인
					if(uri.equals(rm.value())) // 처리 메소드를 찾아서 => 수행
					{
						String jsp=(String)m.invoke(obj, request,response); //메소드 호출
						// 메소드명은 마음대로 사용이 가능 **invoke를 쓰면 메소드를 호출가능!!
						/*
						 * 		Model 메소드
						 * 		@RequestMapping("uri")
						 * 		public String main_main(HttpServletRequest request,HttpServletResponse response)
						 * 		{
						 * 			return "출력할 JSP";
						 * 
						 * 			request  ==> 요청값을 받는다 => 처리 => setAttribute() 
						 * 			response ==> 쿠키사용 ,파일업로드 (응답과 관련된 부분이라 두개(req,res) 다 넘어가야한다)
						 * 		}
						 */
						// 이동 방식 ==> sendRedirect(기존에 만들어진 파일로 이동 : 재호출), forward (화면 출력)
						//              ㄴ> request를 초기화 						 ㄴ> request 전송
						 if(jsp.startsWith("redirect"))
						 {
							 // return "redirect:../main/main.do" => sendredirect
							 response.sendRedirect(jsp.substring(jsp.indexOf(":")+1)); ///main/main.do잘라낸다
						 }
						 else
						 {
							 // return "../main/main.jsp"	=> forward 이동시킨다..
							 RequestDispatcher rd=request.getRequestDispatcher(jsp);
							 rd.forward(request, response);
							 
						 }
							 return;
					}
				}
				
			}
		}catch(Exception ex) {}
		
	}

}
