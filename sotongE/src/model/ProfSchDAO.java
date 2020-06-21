package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Calendar;
import java.util.Date;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class ProfSchDAO {
	
	private DataSource ds;

	public ProfSchDAO() throws Exception {
		Context init = new InitialContext();
		ds = (DataSource)init.lookup("java:comp/env/jdbc/OracleDB");
	}	
	public int createSch(ProfSchDTO sch) throws SQLException{
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			StringBuffer insertQuery = new StringBuffer();	

			insertQuery.append("INSERT INTO P_SCHEDULE (prof_id, day, time, title, cons_date) VALUES (?, ?, ?, ?, ?)");		

			con = ds.getConnection();

			pstmt = con.prepareStatement(insertQuery.toString());
			
			int day = getDay(sch.getCons_date());
			pstmt.setString(1, sch.getProf_id());
			pstmt.setString(2, day+" ");
			pstmt.setString(3, sch.getTime());
			pstmt.setString(4, sch.getTitle());
			pstmt.setString(5, sch.getCons_date());

			int result = pstmt.executeUpdate();
			
			return result;
		
		}catch(Exception e){
			return 0;
		}
		finally {
			if (pstmt != null) { pstmt.close(); }
			if (con != null) { con.close(); }
		 }
		
	}

	public int getDay(String date) throws Exception {

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date nDate = dateFormat.parse(date);

		Calendar cal = Calendar.getInstance();
		cal.setTime(nDate);
		int dayNum = cal.get(Calendar.DAY_OF_WEEK);

		return dayNum;
	}
	
	public List<ProfSchDTO> findProfSch(String recipient_id, String user_id) throws SQLException{
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String prof_id = null;
		
		if(recipient_id != null){
			prof_id = recipient_id;
		}
		else{
			prof_id = user_id;
		}
		System.out.println(prof_id);
		try{
			StringBuffer findQuery = new StringBuffer();
			
			findQuery.append("select ");
			findQuery.append("prof_id, day, time, title, cons_date ");
			findQuery.append("from p_schedule ");
			findQuery.append("where prof_id = ? and cons_date > sysdate");
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(findQuery.toString());
			pstmt.setString(1, prof_id);
			
			rs = pstmt.executeQuery();
			
			List<ProfSchDTO> schList = new ArrayList<ProfSchDTO>();
			ProfSchDTO sch = null;
			System.out.println(rs.next());
			while(rs.next()){
				sch = new ProfSchDTO();
				
				sch.setProf_id(rs.getString("prof_id"));
				switch (rs.getString("day")) {
				case "1         ":
					sch.setDay("일요일");
					break;
				case "2         ":
					sch.setDay("월요일");
					break;
				case "3         ":
					sch.setDay("화요일");
					break;
				case "4         ":
					sch.setDay("수요일");
					break;
				case "5         ":
					sch.setDay("목요일");
					break;
				case "6         ":
					sch.setDay("금요일");
					break;
				case "7         ":
					sch.setDay("토요일");
					break;
				}
				sch.setTime(rs.getString("time"));
				sch.setTitle(rs.getString("title"));

				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				sch.setCons_date( sdf.format(rs.getTimestamp("cons_date")));
				
				schList.add(sch);
			}
			System.out.println(schList);
			return schList;
		
			
		}finally{
			
			if ( pstmt != null ){
				pstmt.close();
			}			
			if ( con != null ){
				con.close();
			}
		}
	}
	
	
	
}
