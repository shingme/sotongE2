package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class StudentDAO {
	
	private DataSource ds;
	
	public StudentDAO() throws Exception {
		Context init = new InitialContext();
		ds = (DataSource)init.lookup("java:comp/env/jdbc/OracleDB");
	}	
	
	//학생인지 찾기
	public StudentDTO findStudent(String user_id) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StudentDTO student = null;
		
		try {
			StringBuffer findQuery = new StringBuffer();
			findQuery.append("SELECT ");
			findQuery.append("name, stu_pwd, dept_name ");
			findQuery.append("FROM student ");
			findQuery.append("WHERE stu_id=? ");		
			
			con = ds.getConnection(); //커넥션 얻기 
			pstmt = con.prepareStatement(findQuery.toString());
			pstmt.setString(1, user_id);
			
			rs = pstmt.executeQuery();
			
			
			if(rs.next()){ //true일때 
				student = new StudentDTO();
				student.setStu_id(user_id);
				student.setName(rs.getString("name"));
				student.setStu_pwd(rs.getString("stu_pwd"));
				student.setDept_name(rs.getString("dept_name"));
				
			}
			
			return student;
			
		} finally {
			if ( pstmt != null ){
				pstmt.close();
			}			
			if ( con != null ){
				con.close();
			}
		}
	}


	public List<StudentDTO> findStudentName(String name) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			System.out.println("name");
			StringBuffer findQuery = new StringBuffer();
			findQuery.append("SELECT ");
			findQuery.append("stu_id, name, dept_name ");
			findQuery.append("FROM student ");
			findQuery.append("WHERE name like '%' || ? || '%'");		
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(findQuery.toString());
			pstmt.setString(1, name);
			
			rs = pstmt.executeQuery();
			
			List<StudentDTO> stuList = null;
			stuList = new ArrayList<StudentDTO>();
			
			if(rs.next() == false) return null;

			do {
				StudentDTO stu = new StudentDTO();
				
				stu.setStu_id(rs.getString("stu_id"));
				stu.setName(rs.getString("name"));
				stu.setDept_name(rs.getString("dept_name"));
				stuList.add(stu);					
				} while (rs.next());
			
			return stuList;
		} finally {
			if ( pstmt != null ){
				pstmt.close();
			}			
			if ( con != null ){
				con.close();
			}
		}
	}
	
	public List<StudentDTO> findStudentDept(String dept_name) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			StringBuffer findQuery = new StringBuffer();
			findQuery.append("SELECT ");
			findQuery.append("stu_id, name, dept_name ");
			findQuery.append("FROM student ");
			findQuery.append("WHERE dept_name like '%' || ? || '%'");
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(findQuery.toString());
			pstmt.setString(1, dept_name);
			
			rs = pstmt.executeQuery();			

			List<StudentDTO> stuList = null;
			stuList = new ArrayList<StudentDTO>();
			
			if(rs.next() == false) return null;

			do {
				StudentDTO stu = new StudentDTO();
				
				stu.setStu_id(rs.getString("stu_id"));
				stu.setName(rs.getString("name"));
				stu.setDept_name(rs.getString("dept_name"));
				stuList.add(stu);					
				} while ((rs.next()));
			
			return stuList;
		} finally {
			if ( pstmt != null ){
				pstmt.close();
			}			
			if ( con != null ){
				con.close();
			}
		}
	}
	
	public List<StudentDTO> findStudentTime(String user_id) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			StringBuffer findQuery = new StringBuffer();
			findQuery.append("SELECT ");
			findQuery.append("stu_id, name, dept_name ");
			findQuery.append("FROM STUDENT where stu_id in( select distinct user_id ");
			findQuery.append("FROM TIME, TIMETABLE ");
			findQuery.append("WHERE time.time_no = timetable.time_no and prof_id = ? )");		
		
			con = ds.getConnection();
			pstmt = con.prepareStatement(findQuery.toString());
			pstmt.setString(1, user_id);
			
			rs = pstmt.executeQuery();
			
			List<StudentDTO> stuList = null;
			stuList = new ArrayList<StudentDTO>();
			
			if(rs.next() == false) return null;

			do {
				StudentDTO stu = new StudentDTO();
				
				stu.setStu_id(rs.getString("stu_id"));
				stu.setName(rs.getString("name"));
				stu.setDept_name(rs.getString("dept_name"));
				stuList.add(stu);					
				} while ((rs.next()));
			
			return stuList;
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
