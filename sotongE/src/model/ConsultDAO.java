package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;



public class ConsultDAO {

	private DataSource ds;
	public ConsultDAO() throws Exception {
		Context init = new InitialContext();
		ds = (DataSource)init.lookup("java:comp/env/jdbc/OracleDB");
	}

	public int createConsult(ConsultDTO cons) throws SQLException{
		
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			StringBuffer insertQuery = new StringBuffer();	

			insertQuery.append("INSERT INTO CONSULT (consult_id, category, title, send, recipient, cons_date, time, content) VALUES (cons_seq.nextval, ?, ?, ?, ?, ?, ?, ?)");		

			con = ds.getConnection();

			pstmt = con.prepareStatement(insertQuery.toString());
			
			pstmt.setString(1, cons.getCategory());
			pstmt.setString(2, cons.getTitle());
			pstmt.setString(3, cons.getSend());
			pstmt.setString(4, cons.getRecipient());
			pstmt.setString(5, cons.getCons_date());
			pstmt.setString(6, cons.getTime());
			pstmt.setString(7, cons.getContent());
			
			int result = pstmt.executeUpdate();
			
			return result;
		
		}
		finally {
			if (pstmt != null) { pstmt.close(); }
			if (con != null) { con.close(); }
		 }
		
	}
	
	public int totalConsultCnt(String send, String type) throws SQLException{
		
		int totalCnt = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		System.out.println("Zzz");
		
		try{
			String query = null;
			if(type.equals("sending")){
				query = "SELECT count(*) FROM consult WHERE send = ?";
			}
			else if(type.equals("receive")){
				query = "SELECT count(*) FROM consult WHERE recipient = ?";
			}
			else{
				
			}
			con = ds.getConnection();
			pstmt = con.prepareStatement(query);
			pstmt.setString(1,send);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				totalCnt = rs.getInt("count(*)");
			}
			System.out.println(totalCnt);
			
			return totalCnt;
		} finally {
			if ( pstmt != null ){
				pstmt.close();
			}			
			if ( con != null ){
				con.close();
			}
		}
	}
		
	public List<ConsultDTO> findConsultList(ConsultPagerDomain consultPager, String send, String type) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		try{
			StringBuffer findQuery = new StringBuffer();

			System.out.println(type + send);
			String query = null;
			if(type.equals("result")){
				query="SELECT consult_id, category,send,recipient, title, cons_date, accept,read FROM CONSULT WHERE recipient=? OR send=? ORDER BY cons_date desc";
			}
			else if(type.equals("sending")){
				findQuery.append("SELECT rn, consult_id, category, send, recipient, title, cons_date, today_date, accept, read ");
				findQuery.append("FROM (SELECT rownum rn, consult_id, category, send, recipient, title, cons_date, today_date, accept, read ");
				findQuery.append("FROM (SELECT * from consult WHERE send = ? ORDER BY consult_id desc) ");
				findQuery.append("WHERE rownum <= ? order by consult_id desc )");
				findQuery.append("WHERE rn > ? ");
				
			}
			else{ //receive
				findQuery.append("SELECT rn, consult_id, category, send, recipient, title, cons_date, today_date, accept, read ");
				findQuery.append("FROM (SELECT rownum rn, consult_id, category, send, recipient, title, cons_date, today_date, accept, read ");
				findQuery.append("FROM (SELECT * from consult WHERE recipient = ? ORDER BY consult_id desc) ");
				findQuery.append("WHERE rownum <= ? order by consult_id desc )");
				findQuery.append("WHERE rn > ? ");
			}
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(findQuery.toString());
			
			if(type.equals("result")){
				pstmt.setString(1,send);
				pstmt.setString(2,send);
			}
			else{
				pstmt.setString(1, send); //물음표는 3개!!! 물음표 페이징 처리3개 들어갈 값 만들기 !
				pstmt.setInt(2, consultPager.getEnd());
				System.out.println(consultPager.getEnd());
				pstmt.setInt(3, consultPager.getStart());
				System.out.println(consultPager.getStart());

			}
			
			rs = pstmt.executeQuery();

			List<ConsultDTO> consultList = new ArrayList<ConsultDTO>();
			while(rs.next()){
				ConsultDTO consult = new ConsultDTO();
				
				consult.setConsult_id(rs.getString("consult_id"));
				consult.setCategory(rs.getString("category"));
				consult.setTitle( rs.getString("title"));
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				
				consult.setCons_date(sdf.format(rs.getTimestamp("cons_date")));
				consult.setCurrent_date(sdf.format(rs.getTimestamp("today_date")));
				consult.setAccept(rs.getString("accept"));
				consult.setRead(rs.getString("read"));
				consult.setSend(rs.getString("send"));
				consult.setRecipient(rs.getString("recipient"));
				
				consultList.add(consult);
			}
			System.out.println(consultList);
			
			return consultList;	
	
		}finally{
			if (pstmt != null) { pstmt.close(); }
			if (con != null) { con.close(); }
		}
		
	}

	public ConsultDTO findContent(String consult_id, String user_id) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			StringBuffer findQuery = new StringBuffer();
			
			findQuery.append("select ");
			findQuery.append("consult_id, category, send, recipient, title, content, time, cons_date, accept, read ");
			findQuery.append("from consult ");
			findQuery.append("where consult_id = ? ");
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(findQuery.toString());
			pstmt.setString(1, consult_id);
			
			rs = pstmt.executeQuery();

			ConsultDTO content = null;
			
			if ( rs.next() ){
				content = new ConsultDTO();

				content.setConsult_id(rs.getString("consult_id"));
				content.setCategory(rs.getString("category"));
				content.setSend(findRecName(rs.getString("send")));
				content.setSend_id(rs.getString("send"));
				content.setRecipient(findRecName(rs.getString("recipient")));
				content.setRecipient_id(rs.getString("recipient"));
				content.setTitle(rs.getString("title"));
				content.setContent(rs.getString("content"));
				content.setTime(rs.getString("time"));
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				content.setCons_date(sdf.format(rs.getTimestamp("cons_date")));
				content.setAccept( rs.getString("accept") );
				content.setRead( rs.getString("read") );
			}
			
			if(content.getRead().equals("N") && content.getRecipient_id().equals(user_id)){
				String readQuery = "update consult set read = 'Y' where consult_id = ?";
				PreparedStatement pstmt2 = null;
				pstmt2 = con.prepareStatement(readQuery.toString());
				pstmt2.setString(1, consult_id);
				pstmt2.executeQuery();
			}
			
			return content;
		} finally {
			if ( pstmt != null ){
				pstmt.close();
			}			
			if ( con != null ){
				con.close();
			}
		}
	}
	
	public String findRecName(String user_id) throws SQLException{
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			StringBuffer recQuery = new StringBuffer();
			if(user_id.startsWith("P")){
				recQuery.append("select name from professor where prof_id = ?");
			}else{
				recQuery.append("select name from student where stu_id = ?");
			}
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(recQuery.toString());
			pstmt.setString(1, user_id);
			
			rs = pstmt.executeQuery();
			String recipient = null;
			if ( rs.next() ){
				recipient = rs.getString("name");
			}
			return recipient;
		} finally {
			if ( pstmt != null ){
				pstmt.close();
			}			
			if ( con != null ){
				con.close();
			}
		}
	}
	
	public int consultAccept(String consult_id, String accept) throws SQLException{
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			StringBuffer upateQuery = new StringBuffer();
			upateQuery.append("update consult set accept = ? where consult_id = ?");
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(upateQuery.toString());
			pstmt.setString(1, accept);
			pstmt.setString(2, consult_id);
			
			int result = pstmt.executeUpdate();
			return result;
			
		} finally {
			if ( pstmt != null ){
				pstmt.close();
			}			
			if ( con != null ){
				con.close();
			}
		}
	}
	
	public int deleteConsult(String consult_id) throws SQLException{
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			StringBuffer deleteQuery = new StringBuffer();	

			deleteQuery.append("delete from consult where consult_id = ?");		

			con = ds.getConnection();

			pstmt = con.prepareStatement(deleteQuery.toString());
			
			pstmt.setString(1, consult_id);
			
			int result = pstmt.executeUpdate();
			
			return result;
		
		}
		finally {
			if (pstmt != null) { pstmt.close(); }
			if (con != null) { con.close(); }
		 }
	}

	public int updateConsult(ConsultDTO cons) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			StringBuffer updateQuery = new StringBuffer();	

			updateQuery.append("update consult set title = ?, content = ? where consult_id = ?");		

			con = ds.getConnection();

			pstmt = con.prepareStatement(updateQuery.toString());

			pstmt.setString(1, cons.getTitle());
			pstmt.setString(2, cons.getContent());
			pstmt.setString(3, cons.getConsult_id());
			
			int result = pstmt.executeUpdate();

			return result;
		
		}
		finally {
			if (pstmt != null) { pstmt.close(); }
			if (con != null) { con.close(); }
		 }
	}
}
