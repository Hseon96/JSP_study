package com.sist.dao;
import java.util.*; // List
import java.sql.*; // Connection, Statement, Result
import javax.sql.*; // DataSource
import javax.naming.*; //Context
public class ReplyBoardDAO {
	private Connection conn;
	private PreparedStatement ps;
	
	// 객체 주소 가지고 오기 (Connection) => 톰캣에 의해 10개 생성이 되어있다 => 저장공간 POOL (java://comp//env)
	// 객체명 => jdbc/oracle 
	public void getConnection()
	{
		try
		{
			Context init=new InitialContext(); // JNDI
			Context c=(Context)init.lookup("java://comp/env");
			DataSource ds=(DataSource)c.lookup("jdbc/oracle"); //jdbc가 가지고 있는 모든정보 가지고옴
			conn=ds.getConnection();
			
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
		
	}
	//반환 => 다음 사용자 재사용 ======> Connection의 생성 갯수를 제한
	public void disConnection()
	{
		try
		{
			if(ps!=null)ps.close();
			if(conn!=null)conn.close();
		}catch(Exception ex) {}
	}
	// <%= %>  --> &lt;%값 %&gt; --> ${}
	public List<ReplyBoardVO> boardListData(int page)
	{
		List<ReplyBoardVO> list=new ArrayList<ReplyBoardVO>();
		try
		{
			//TO_CHAR(regdate,'YYYY-MM-DD') ==> DATE_FORMAT(regdate,'%Y-%m-%d')
			getConnection();
			String sql="SELECT no,subject,name,TO_CHAR(regdate,'YYYY-MM-DD'),hit,group_tab,num "
					+ "FROM (SELECT no,subject,name,regdate,hit,group_tab,rownum as num "
					+ "FROM (SELECT no,subject,name,regdate,hit,group_tab "
					+ "FROM replyBoard ORDER BY group_id DESC,group_step ASC)) " //관련답변끼리 모아두고 순서대로 출력
					+ "WHERE num BETWEEN ? AND ?";
			ps=conn.prepareStatement(sql);
			int rowSize=10;
			int start=(rowSize*page)-(rowSize-1);
			int end=rowSize*page;
			
			ps.setInt(1, start);
			ps.setInt(2, end);
			
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				ReplyBoardVO vo=new ReplyBoardVO();
				vo.setNo(rs.getInt(1));
				vo.setSubject(rs.getString(2));
				vo.setName(rs.getString(3));
				vo.setDbday(rs.getString(4));
				vo.setHit(rs.getInt(5));
				vo.setGroup_tab(rs.getInt(6));
				
				list.add(vo);
			}
			rs.close();
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
		finally
		{
			disConnection();
		}
		return list;
	}
	public int boardRowCount()
	{
		int count=0;
		try
		{
			getConnection();
			String sql="SELECT COUNT(*) FROM replyBoard";
			ps=conn.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			rs.next();
			count=rs.getInt(1);
			rs.close();
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
		finally
		{
			disConnection();
		}
		return count;
	}
	// JDBC => autoCommit => 오류 (rollback)
	public void boardInsert(ReplyBoardVO vo)
	{
		try
		{
			getConnection();
			String sql="INSERT INTO replyBoard(no,name,subject,content,pwd,group_id) VALUES("
					+"(SELECT NVL(MAX(no)+1,1) FROM replyBoard),?,?,?,?,"
					+ "(SELECT NVL(MAX(no)+1,1) FROM replyBoard))";
			ps=conn.prepareStatement(sql);
			ps.setString(1, vo.getName());
			ps.setString(2, vo.getSubject());
			ps.setString(3, vo.getContent());
			ps.setString(4, vo.getPwd());
			
			//sql 문장 실행 요청
			ps.executeUpdate();
			
		}catch(Exception ex) 
		{
			ex.printStackTrace();
		}
		finally
		{
			disConnection();
		}
	}
	// 답변하기 
	public void replyInsert(ReplyBoardVO vo)
	{
		 try
		 {
			 getConnection();
			 conn.setAutoCommit(false);
			 // 상위 글의 정보(gi,gt,gs) => SELECT
			 // gs의 순서변경 ==> UPDATE
			 // 실제 저장 ==> INSERT
			 // depth변경 ==> UPDATE 답변이 몇개 달렸는지
			 // ==================== 일괄처리 (트랜잭션) -- 2개이상(INSERT,UPDATE,DELETE) 처리하면 트랜잭션 SELECT몇번을 쓰든 데이터는 안바뀌니 트랜잭션아님
			 conn.commit(); // 4개중에 하나라도 오류나면 다 취소하게 .. 밑에 롤백하게끔  // 4개가 다 실패하던지 다 성공하든지 //
		 }catch(Exception ex)
		 {
			 try
			 {
				 conn.rollback();
			 }catch(Exception e) {}
			 ex.printStackTrace();
		 }
		 finally
		 {
			 try
			 {
				 conn.setAutoCommit(true); // 트랜잭션!!! 롤백(전체 취소)전에 맨마지막에 커밋. 
			 }catch(Exception ex) {}
			 disConnection();
		 }
	}
}
