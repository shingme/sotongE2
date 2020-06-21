package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
//import javax.servlet.http.HttpSession;
import javax.sql.DataSource;



public class ResultDAO {

	private DataSource ds;
	public ResultDAO() throws Exception {
		Context init = new InitialContext();
		ds = (DataSource)init.lookup("java:comp/env/jdbc/OracleDB");
	}
	
	public int createResult(ResultDTO cons) throws SQLException{
		
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			StringBuffer insertQuery = new StringBuffer();	

			insertQuery.append("INSERT INTO RESULT (consult_id, user_id, result_cons, cons_date) VALUES (?, ?, ?, to_char(sysdate,'yyyy-mm-dd'))");		

			con = ds.getConnection();

			pstmt = con.prepareStatement(insertQuery.toString());
			
			pstmt.setString(1, cons.getConsult_id());
			pstmt.setString(2, cons.getUser_id());
			pstmt.setString(3, cons.getResult_cons());
			
			int result = pstmt.executeUpdate();
			
			return result;
		
		}
		finally {
			if (pstmt != null) { pstmt.close(); }
			if (con != null) { con.close(); }
		 }
		
	}
		
	public ResultDTO findResult(String consult_id, String user_id) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			StringBuffer findQuery = new StringBuffer();
			
			findQuery.append("select ");
			findQuery.append("result_cons, cons_date ");
			findQuery.append("from result ");
			findQuery.append("where consult_id = ? and user_id = ?");
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(findQuery.toString());
			pstmt.setString(1, consult_id);
			pstmt.setString(2, user_id);
			
			rs = pstmt.executeQuery();

			ResultDTO result = null;
			
			if ( rs.next() ){
				result = new ResultDTO();
				
				result.setResult_cons(rs.getString("result_cons"));
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				result.setCons_date(sdf.format(rs.getTimestamp("cons_date")));
			}
			
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

	public int deleteResult(String consult_id) throws SQLException {

		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			StringBuffer deleteQuery = new StringBuffer();	

			deleteQuery.append("delete from result where consult_id = ?");		

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

}
