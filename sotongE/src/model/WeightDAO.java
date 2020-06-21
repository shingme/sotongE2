package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class WeightDAO {
	private DataSource ds;
	
	public WeightDAO() throws Exception {
		Context init = new InitialContext();
		ds = (DataSource)init.lookup("java:comp/env/jdbc/OracleDB");
	}	
	//가중치 DB에 넣기
	public int giveWeight(List<WeightDTO> weightList) throws SQLException{
	
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			String query = "MERGE INTO WEIGHT "
						+ "USING DUAL " //하나의 테이블을 이용하므로 DUAL
						+ "ON (STU_ID = ? AND CATEGORY = ?)" 
						+ "WHEN MATCHED THEN " //on절에서 일치하는 row가 있다면 
							+ "UPDATE SET WEIGHT = ? "
						+ "WHEN NOT MATCHED THEN " //일치하는 row가 없다면 
							+ "INSERT (STU_ID, CATEGORY, WEIGHT) VALUES( ? , ? , ? )";
			
			StringBuffer insertQuery = new StringBuffer();	
			insertQuery.append(query);
			con = ds.getConnection();
			pstmt = con.prepareStatement(insertQuery.toString());
			int result = 0;
			Iterator weigItr = weightList.iterator();
			
			while(weigItr.hasNext()){
				WeightDTO weight = (WeightDTO)weigItr.next();
				pstmt.setString(1, weight.getStu_id());
				pstmt.setString(2, weight.getCategory());
				pstmt.setInt(3, weight.getWeight());
				pstmt.setString(4, weight.getStu_id());
				pstmt.setString(5, weight.getCategory());
				pstmt.setInt(6, weight.getWeight());
				result = pstmt.executeUpdate();
				pstmt.clearParameters();
			}
			return result;
		}
		finally {
			if (pstmt != null) { pstmt.close(); }
			if (con != null) { con.close(); }
		 }
		
	}
	//상담완료된 전체 갯수 가져오기 
	public int totalConsultCount() throws SQLException{
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		int totalCount = 0;
		
		
		try{
			
			String query = "select count(*) from CONSULT "
							+ "where RECIPIENT like 'P%' "
							+ "AND accept = 'Y' "
							+ "AND CONS_DATE < sysdate";
			
			con = ds.getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
			System.out.println("zzz");
			if(rs.next())
				totalCount = rs.getInt("count(*)");
			
			System.out.println("상담완료 총 갯수 : " + totalCount);
			
			return totalCount;
		}finally {
			if(stmt != null){
				stmt.close();
			}		
			if ( con != null ){
				con.close();
			}
		}
	}
	
	//상담 완료한 교수님들 리스트로 뽑아오기 
	public Map<String, HashMap<String,Integer>> profConsultCompleteList() throws SQLException{
	
		Connection con = null;
		Statement stmt = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		try{
			StringBuffer findQuery = new StringBuffer();
			findQuery.append("SELECT DISTINCT recipient ");
			findQuery.append("FROM consult ");
			findQuery.append("WHERE recipient like 'P%' ");
			findQuery.append("AND accept = 'Y' ");
			findQuery.append("AND CONS_DATE < sysdate ");
			findQuery.append("order by recipient");
			
			con = ds.getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(findQuery.toString());
			
			//교수님 담을 리스트
			List<String> profList = new ArrayList<String>();
			while(rs.next()){
				profList.add(rs.getString("recipient"));
			}
			System.out.println("교수님리스트 : " + profList);
			
			//hashMap안에 교수님 번호와 각 교수별로 상담 카테고리 횟수 담기! 
			Map<String, HashMap<String,Integer>> profConsultCnt = new HashMap<String, HashMap<String,Integer>>();
			//DB연결한 김에 각 교수별로 상담 카테고리 완료 횟수 가져오기! 

			for(int i=0; i<profList.size(); i++){
				StringBuffer findQuery2 = new StringBuffer();
				findQuery2.append("SELECT category, count(*) ");
				findQuery2.append("FROM consult ");
				findQuery2.append("WHERE accept = 'Y' ");
				findQuery2.append("AND CONS_DATE < sysdate ");
				findQuery2.append("AND recipient = ? ");
				findQuery2.append("GROUP BY category");
				
				pstmt = con.prepareStatement(findQuery2.toString());
				pstmt.setString(1, profList.get(i));
				rs = pstmt.executeQuery();
				
				HashMap<String, Integer> hashMap = new HashMap<String, Integer>();
				
				while(rs.next()){
					hashMap.put(rs.getString("category"), rs.getInt("count(*)"));
				}
				profConsultCnt.put(profList.get(i), hashMap);
			}
			System.out.println(profConsultCnt);
			return profConsultCnt;
			
		}finally {
			if(pstmt != null){
				pstmt.close();
			}			
			if ( con != null ){
				con.close();
			}
		}	
	}
	//최종적으로 교수님 이름으로 바꿔서 담기
	public List<String> profChangeNameList(List<String> list) throws SQLException{
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		try{
			List<String> profNameRecommandList = new ArrayList<String>();
			
			for(int i=0; i<list.size(); i++){
				
				StringBuffer findQuery3 = new StringBuffer();
				findQuery3.append("SELECT name ");
				findQuery3.append("FROM professor ");
				findQuery3.append("WHERE prof_id = ?");
			
				con = ds.getConnection();
				pstmt = con.prepareStatement(findQuery3.toString());
				pstmt.setString(1, list.get(i));
				
				rs = pstmt.executeQuery();
				
				if(rs.next()){
					profNameRecommandList.add(rs.getString("name"));
				}
			}
			System.out.println(profNameRecommandList);
			return profNameRecommandList;

		}finally {
			if(pstmt != null){
				pstmt.close();
			}			
			if ( con != null ){
				con.close();
			}
		}	
	}
	
	
	
	
}
