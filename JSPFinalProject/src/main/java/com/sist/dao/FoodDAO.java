package com.sist.dao;
import java.util.*;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.sist.vo.FoodCategoryVO;
import com.sist.vo.FoodVO;

import java.io.*;
public class FoodDAO {
	// XML 파싱 => 등록된 데이터 읽기// 모든내역을 읽는것 :SqlSessionFactory
	private static SqlSessionFactory ssf;
	static
	{
		try
		{
			// XML 읽기
			// 					src/main/java => Config.xml  (classpath영역 => 마이바티스 자동인식)
			Reader reader=Resources.getResourceAsReader("Config.xml");
			ssf=new SqlSessionFactoryBuilder().build(reader);
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
	
	 // getConnection , disConnection 
	   public static List<FoodCategoryVO> foodCategoryData()
	   {
		   SqlSession session=null;
		   List<FoodCategoryVO> list=null;
		   try
		   {
		      session=ssf.openSession();
		      list=session.selectList("foodCategoryData");
		   }catch(Exception ex)
		   {
			   ex.printStackTrace();
		   }
		   finally
		   {
		      session.close();
		   }
		   return list;
	   }
	   
	   /*
   <select id="foodListData" resultType="FoodVO" parameterType="int">
   SELECT fno,poster,name,tel,type
   FROM food_house
   WHERE cno=#{cno}
   </select>
   */
   public static List<FoodVO> foodListData(int cno)
   {
	   SqlSession session=null;
	   List<FoodVO> list=null;
	   try
	   {
		   session=ssf.openSession(); // connection 주소를 얻어 온다
		   list=session.selectList("foodListData",cno); // while(rs.next())
		   // 앞에는 id , 뒤는 넘겨주는..
	   }catch(Exception ex)
	   {
		   ex.printStackTrace();
	   }
	   finally
	   {
		   session.close(); // 반환  ===> POOLED (connection: 8개 디폴트) 
	   }
	   return list;
   }
   
   /*
   <select id="foodCategoryInfoData" resultType="FoodCategoryVO" parameterType="int">
   SELECT title,subject
   FROM food_house
   WHERE cno=#{cno}
   </select>
	    */
   public static FoodCategoryVO foodCategoryInfoData(int cno)
   {
	   SqlSession session=null;
	   FoodCategoryVO vo=null;
	   try
	   {
		   session=ssf.openSession();
		   vo=session.selectOne("foodCategoryInfoData",cno); //food_mapper에서값 보기! // id와 sql의 ?값
		   // WHERE cno=${cno} ==> ? 값임
	   }catch(Exception ex)
	   {
		   ex.printStackTrace();
	   }
	   finally
	   {
		   session.close();
	   }
	   return vo;
   }
		/*
		 * <select id="foodDetailData" resultType="FoodVO" parameterType="int">
		   SELECT * FROM food_house			리턴형				매개변수(사용자가 .)
		   WHERE fno=#{fno}
		  </select>   
		  
		  ===> select
		  			selectList (while(rs.next())
		  			selectOne  rs.next()
		  	   insert
		  	   		insert
		  	   	update 
		  	   		update
		  	   	delete
		  	   		delete
		  	   		
		  	    ?  ===> parameterType
		  	    	결과값 ==> resultType
		  
	 */
	   public static FoodVO foodDetailData(int fno)  // 리턴형 ,id값 , 매개변수
	   {
		   SqlSession session=null;
		   FoodVO vo=null;
		   try
		   {
			   session=ssf.openSession();
			   vo=session.selectOne("foodDetailData",fno); //rs.next()  //food_mapper에서값 보기! 
			   		//     ======하나일땐! // id와 sql의 ?값(매개변수)
			   // WHERE cno=${cno} ==> ? 값임
		   }catch(Exception ex)
		   {
			   ex.printStackTrace();
		   }
		   finally
		   {
			   session.close();
		   }
		   return vo;
	   }
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	}