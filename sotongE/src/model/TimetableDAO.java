package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class TimetableDAO {
	
	private DataSource ds;
	
	public TimetableDAO() throws Exception {
		Context init = new InitialContext();
		ds = (DataSource)init.lookup("java:comp/env/jdbc/OracleDB");
	}
	
	//DB에서 해당 시간표 가져오기 
	public List<TimetableDTO> findTimetable(String user_id, String recipient_id) throws SQLException{
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String user = user_id;
		System.out.println("user" + user);
	
		try{
			if(recipient_id != null){	// 교수의 시간표 
				user = recipient_id;
				System.out.println(user);
				StringBuffer findQuery = new StringBuffer();
				findQuery.append("SELECT ");
				findQuery.append("subject, name, day, time ");
				findQuery.append("FROM professor, time t ");
				findQuery.append("WHERE professor.prof_id = t.prof_id ");
				findQuery.append("AND professor.prof_id = ? ORDER BY time, day");
				
				con = ds.getConnection();
				pstmt = con.prepareStatement(findQuery.toString());
				pstmt.setString(1, user);
			
				rs = pstmt.executeQuery();
				
			}
			else{
				if((user.substring(0, 1)).equals("s")){	//학생의 시간표 
					StringBuffer findQuery = new StringBuffer();
					findQuery.append("SELECT ");
					findQuery.append("t.subject, p.name, t.day, t.time ");
					findQuery.append("FROM professor p, time t, timetable ta ");
					findQuery.append("WHERE p.prof_id = t.prof_id AND t.time_no = ta.time_no ");
					findQuery.append("AND user_id = ? ORDER BY time, day");
			
					con = ds.getConnection();
					pstmt = con.prepareStatement(findQuery.toString());
					pstmt.setString(1, user);
			
					rs = pstmt.executeQuery();	
				
				}
			}
			
				List<TimetableDTO> timeList = null;
				timeList = new ArrayList<TimetableDTO>();
					
				while(rs.next()){
					TimetableDTO timetable = new TimetableDTO();
				
					timetable.setUser_id(user_id);
					timetable.setSubject(rs.getString("subject"));
					timetable.setName(rs.getString("name"));
					int day = Integer.parseInt(rs.getString("day"));
					timetable.setDay(day);
					int time = Integer.parseInt(rs.getString("time"));
					timetable.setTime(time);

					timeList.add(timetable);
				}
			
			
			return timeList;
			
		}finally{
			
			if ( pstmt != null ){
				pstmt.close();
			}			
			if ( con != null ){
				con.close();
			}
			
		}
	}
	
	//시간표 형태로 틀 잡기, timeList는 DB에서 가져온 교수 또는 학생의 시간표 정보
	public List<WeekDTO> findWeekTable(List<TimetableDTO> timeList)throws SQLException{
		
		List<WeekDTO> weekList = new ArrayList<WeekDTO>(); //월~금 다섯개에 데이터가 들어간다. 과목명 또는 공백!
		
		int i = 1;
		while(i <= 6){ //1~6교시이기 때문에 6번을 돌린다! 
			weekList.add(findWeekTime(i+1, timeList)); //weekList에 1교시 월~금 , 2교시 월~금 ... 담긴 정보를 추가한다. 
			i++;
		}
		return weekList; // 시간표 형태가 담긴 List를 반환
	}
	//월~금 1~5교시 대로 시간표 형태로 잡기!
	public WeekDTO findWeekTime(int time, List<TimetableDTO> timeList){
		WeekDTO week = new WeekDTO();
		
		Iterator<TimetableDTO> iter = timeList.iterator();
		while(iter.hasNext()){
			TimetableDTO timetable = (TimetableDTO)iter.next();
			
			if(timetable.getTime() == time){ // time은 몇교시인지! 
				if(timetable.getDay() == 2)						 
					week.setFirst(timetable.getSubject());
				else if(timetable.getDay() == 3) 				 
					week.setSecond(timetable.getSubject());
				else if(timetable.getDay() == 4) 				
					week.setThree(timetable.getSubject());
				else if(timetable.getDay() == 5)			 
					week.setFour(timetable.getSubject());
				else 				
					week.setFive(timetable.getSubject());
			}
		}
		return week; // weekDto를 하나씩 리턴한다. 
	}



}
