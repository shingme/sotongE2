package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class ProfDAO {
	
private DataSource ds;
	
	public ProfDAO() throws Exception {
		Context init = new InitialContext();
		ds = (DataSource)init.lookup("java:comp/env/jdbc/OracleDB");
	}	
	
	//교수 아이디로 db에서  교수정보 찾아서 DTO에 저장
	public ProfDTO findProf(String user_id) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			StringBuffer findQuery = new StringBuffer();
			findQuery.append("SELECT ");
			findQuery.append("name, prof_pwd, office, dept_name ");
			findQuery.append("FROM professor ");
			findQuery.append("WHERE prof_id=? ");		
		
			con = ds.getConnection();
			pstmt = con.prepareStatement(findQuery.toString());
			pstmt.setString(1, user_id);
			
			rs = pstmt.executeQuery();
			
			ProfDTO prof = null;
			
			if ( rs.next() ){
				prof = new ProfDTO();
				prof.setProf_id(user_id);
				prof.setName(rs.getString("name"));
				prof.setProf_pwd(rs.getString("prof_pwd"));
				prof.setOffice("office");
				prof.setDept_name("dept_name");
				
				return prof;
			}
			else{
				return null;
			}
			
		} finally {
			if ( pstmt != null ){
				pstmt.close();
			}			
			if ( con != null ){
				con.close();
			}
		}
	}
	//교수이름으로 db에서 교수정보 가져와 dto에 저장
	public List<ProfDTO> findProfessor(String name) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			StringBuffer findQuery = new StringBuffer();
			findQuery.append("SELECT ");
			findQuery.append("prof_id, name, dept_name, office ");
			findQuery.append("FROM PROFESSOR ");
			findQuery.append("WHERE name Like '%' || ? || '%'");		
		
			con = ds.getConnection();
			
			pstmt = con.prepareStatement(findQuery.toString());
			pstmt.setString(1, name);
			
			rs = pstmt.executeQuery();
			
			List<ProfDTO> profList = null;
			profList = new ArrayList<ProfDTO>();
			
			if(rs.next() == false)
				return null;

			do {
				ProfDTO prof = new ProfDTO();
				
				prof.setProf_id(rs.getString("prof_id"));
				prof.setName(rs.getString("name"));
				prof.setOffice(rs.getString("office"));
				prof.setDept_name(rs.getString("dept_name"));
				profList.add(prof);					
				} while (rs.next());
			return profList;
		} finally {
			
			if ( pstmt != null ){
				pstmt.close();
			}			
			if ( con != null ){
				con.close();
			}
		}
	}
	

	//교수가 속한 학과로 db에서 교수정보 가져와 dto에 저장
	public List<ProfDTO> findProfDept(String dept) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			StringBuffer findQuery = new StringBuffer();
			findQuery.append("SELECT ");
			findQuery.append("prof_id, name, dept_name, office ");
			findQuery.append("FROM PROFESSOR ");
			findQuery.append("WHERE dept_name like '%' || ? || '%'");		
		
			con = ds.getConnection();
			
			pstmt = con.prepareStatement(findQuery.toString());
			pstmt.setString(1, dept);
			
			rs = pstmt.executeQuery();
			
			List<ProfDTO> profList = null;
			profList = new ArrayList<ProfDTO>();
			
			if(rs.next() == false) return null;

			do {
				ProfDTO prof = new ProfDTO();
				
				prof.setProf_id(rs.getString("prof_id"));
				prof.setName(rs.getString("name"));
				prof.setOffice(rs.getString("office"));
				prof.setDept_name(rs.getString("dept_name"));
				profList.add(prof);					
				} while ((rs.next()));
			return profList;
		} finally {
			
			if ( pstmt != null ){
				pstmt.close();
			}			
			if ( con != null ){
				con.close();
			}
		}
	}
	
	//교수 일정 DB에서 가져오기
	public List<ProfDTO> findProfTime(String user_id) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			StringBuffer findQuery = new StringBuffer();
			findQuery.append("SELECT ");
			findQuery.append("prof_id, name, dept_name, office ");
			findQuery.append("FROM PROFESSOR where prof_id in( select distinct prof_id ");
			findQuery.append("FROM TIME, TIMETABLE ");
			findQuery.append("WHERE time.time_no = timetable.time_no and user_id = ? )");		
		
			con = ds.getConnection();
			pstmt = con.prepareStatement(findQuery.toString());
			pstmt.setString(1, user_id);
			
			rs = pstmt.executeQuery();
			
			List<ProfDTO> profList = null;
			profList = new ArrayList<ProfDTO>();
			
			if(rs.next() == false) return null;

			do {
				ProfDTO prof = new ProfDTO();
				
				prof.setProf_id(rs.getString("prof_id"));
				prof.setName(rs.getString("name"));
				prof.setOffice(rs.getString("office"));
				prof.setDept_name(rs.getString("dept_name"));
				profList.add(prof);					
				} while ((rs.next()));
			return profList;
		} finally {
			
			if ( pstmt != null ){
				pstmt.close();
			}			
			if ( con != null ){
				con.close();
			}
		}
		
	}
	

}
