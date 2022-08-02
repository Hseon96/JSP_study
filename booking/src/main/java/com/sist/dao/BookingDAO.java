package com.sist.dao;
import java.util.*;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.sist.vo.BookingVO;
import java.io.*;
public class BookingDAO {
   // 1. XML파일 읽기 
   private static SqlSessionFactory ssf;
   static
   {
	   try
	   {
		   Reader reader=Resources.getResourceAsReader("Config.xml"); 
		   ssf=new SqlSessionFactoryBuilder().build(reader);// 파싱 
	   }catch(Exception ex)
	   {
		   ex.printStackTrace();
	   }
   } // 상세VO
   // <select id="bookingListData" resultType="SeoulLNSVO" parameterType="hashmap">
   public static List<BookingVO> bookingListData(Map map)
   {
	   SqlSession session=null; // PreparedStatement 
	   // ssf  => Connection
	   List<BookingVO> list=null;
	   try
	   {
		   session=ssf.openSession();
		   list=session.selectList("bookingListData",map);
	   }catch(Exception ex)
	   {
		   System.out.println("bookingListData(Map map) : error");
		   ex.printStackTrace();
	   }
	   finally
	   {
		   if(session!=null) // if(ps!=null) ps.close()
		   {
		      session.close();
		   }
	   }
	   return list;
   }
   // <select id="bookingTotalPage" resultType="int" parameterType="hashmap">
   public static int bookingTotalPage(Map map)
   {
	   int total=0;
	   SqlSession session=null;
	   try
	   {
		   session=ssf.openSession();
		   total=session.selectOne("bookingTotalPage", map);
	   }catch(Exception ex)
	   {
		   System.out.println("bookingTotalPage : error");
		   ex.printStackTrace();
	   }
	   finally
	   {
		   if(session!=null)
			   session.close(); // POOL => 반환 
	   }
	   return total;
   }
   
   // 상세보기 
   public static BookingVO bookingDetailData(Map map)
   {
	   BookingVO vo=new BookingVO();
	    SqlSession session=null;
	    try
	    {
	    	session=ssf.openSession();
	    	vo=session.selectOne("bookinghstDetailData", map);//row
	    }catch(Exception ex)
	    {
	    	System.out.println("bookingDetailData: error");
	    	ex.printStackTrace();
	    }
	    finally
	    {
	    	if(session!=null)
	    		session.close();
	    }
	    return vo;
   }
}







