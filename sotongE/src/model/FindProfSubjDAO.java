package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class FindProfSubjDAO {
	//과목명으로 교수 id찾기 
	private DataSource ds;
	
		public FindProfSubjDAO() throws Exception {
			Context init = new InitialContext();
			ds = (DataSource)init.lookup("java:comp/env/jdbc/OracleDB");
		}
		
		public ProfDTO findProfSub(String subject) throws SQLException{
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			try{
				StringBuffer findQuery = new StringBuffer();
				findQuery.append("SELECT ");
				findQuery.append("p.prof_id, p.name, p.prof_pwd, p.office, p.dept_name ");
				findQuery.append("FROM timetable ta, time t, professor p ");
				findQuery.append("WHERE ta.time_no = t.time_no AND t.prof_id = p.prof_id ");
				findQuery.append("AND subject = ?");
					
				con = ds.getConnection();
				pstmt = con.prepareStatement(findQuery.toString());
				pstmt.setString(1, subject);
				
				rs = pstmt.executeQuery();
				
				ProfDTO prof = null;
				
				if(rs.next()){
					prof = new ProfDTO();
					prof.setProf_id(rs.getString("prof_id"));
					prof.setName(rs.getString("name"));
					prof.setProf_pwd(rs.getString("prof_pwd"));
					prof.setOffice("office");
					prof.setDept_name("dept_name");
				}
				return prof;
			}finally {
				if ( pstmt != null ){
					pstmt.close();
				}			
				if ( con != null ){
					con.close();
				}
			}
					
				
		
		}

}
