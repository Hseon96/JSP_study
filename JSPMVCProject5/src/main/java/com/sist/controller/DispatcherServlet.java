package com.sist.controller;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import com.sist.model.*;
@WebServlet("*.do") // list.do find.do 얘네들은 dispatcher에서 찾는다..?
public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    List<String> clsList=new ArrayList<String>();
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		clsList.add("com.sist.model.FoodModel");
	}
    /*
     *   http://localhost:8080/JSPMVCProject5/food/category.do
     *   ------------------------------------------------------- URL
     *                        /JSPMVCProject5/food/category.do?no=1
     *                        ---------------------------------- URI
     *                        /JSPMVCProject5
     *                        --------------- ContextPath
     */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try
		{
			// 사용자 요청한 URL을 읽어 온다 => .do 
			String uri=request.getRequestURI();
			System.out.println("uri_1:"+uri);
			uri=uri.substring(request.getContextPath().length()+1);
			System.out.println("uri_2:"+uri);
			
			for(String cls:clsList)
			{
				Class clsName=Class.forName(cls);
				if(clsName.isAnnotationPresent(Controller.class)==false)
					continue;
				Object obj=clsName.getDeclaredConstructor().newInstance();
				
				// 메소드 찾기
				Method[] methods=clsName.getDeclaredMethods();
				for(Method m:methods)
				{
					RequestMapping rm=m.getAnnotation(RequestMapping.class); //m(메소드)에 있는 어노테이션을 가져온다 //여러개가져올수있음.
					if(uri.equals(rm.value()))  //uri자른거랑 같으면
					{
						String jsp=(String)m.invoke(obj, request);
						RequestDispatcher rd=request.getRequestDispatcher(jsp);
						rd.forward(request, response);
						
						return;
					}
				}
			}
		}catch(Exception ex){}
	}

}








