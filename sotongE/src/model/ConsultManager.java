package model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConsultManager {
	private static ConsultManager consMan = new ConsultManager();
	private ConsultDAO consDAO;
	private StudentDAO studentDAO;
	private ProfDAO profDAO;
	private TimetableDAO timeDAO;
	private FindProfSubjDAO subjDAO;
	private ProfSchDAO profSchDAO;
	private WeightDAO weightDAO;
	private ResultDAO resultDAO;
	private WeightCaculate weighCal;
	private ConsultPagerDomain consultPager;
	
	
	private ConsultManager(){
		try {
			consDAO = new ConsultDAO();
			studentDAO = new StudentDAO();
			profDAO = new ProfDAO();
			timeDAO = new TimetableDAO();
			subjDAO = new FindProfSubjDAO();	
			profSchDAO = new ProfSchDAO();
			weightDAO = new WeightDAO();
			resultDAO = new ResultDAO();
			weighCal = new WeightCaculate();
			consultPager = new ConsultPagerDomain();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}			
	}
	public static ConsultManager getInstance() {
		return consMan;
	}
	
	public int create(ConsultDTO cons) throws SQLException{
		
		return consDAO.createConsult(cons);

	}
	
	public int delete(String consult_id) throws SQLException {
		return consDAO.deleteConsult(consult_id);
	}

	public int updateContent(ConsultDTO cons) throws SQLException {
		return consDAO.updateConsult(cons);		
	}
	
	public boolean login(String user_val, String user_id, String user_pwd)
			throws SQLException, UserNotFoundException, PasswordMismatchException{
		
		StudentDTO stu = null;
		ProfDTO prof = null;
		
		System.out.println(user_id);
		
		if(user_val.equals("student")){
			stu = studentDAO.findStudent(user_id);

			if(stu == null){ // 사용자 아이디가 존재하지 않을 때 
				throw new UserNotFoundException("아이디가 틀립니다. 다시 입력해주세요. ");
			}
			
			else if (!stu.isMatchPassword(user_pwd)) { //비번이 틀렸을 때
				throw new PasswordMismatchException("비밀번호가 틀립니다. ");
			}
		
		}
		else{
			prof = profDAO.findProf(user_id);
			
			if(prof == null){
				throw new UserNotFoundException("아이디가 틀립니다. 다시 입력해주세요. ");
			}
			
			if (!prof.isMatchPassword(user_pwd)) {
				throw new PasswordMismatchException("비밀번호가 틀립니다. ");
			}
		
		}
		return true;
		
		
	}
	
	public List<ProfDTO> findProfessor(String name)
		throws SQLException {
		
		if(name == null){
			return profDAO.findProfessor("占쏙옙창占쏙옙");
		}else{
			return profDAO.findProfessor(name);
		}
	}

	public ConsultPagerDomain makePager(int currentPage, String send, String type) throws SQLException{
		int tempEnd = (int)(Math.ceil(currentPage/5.0)*5); //5, 10, 15 ...
		int tempStart = tempEnd - 5; // 0, 5, 10, ... 
	
		int totalPageCnt = (int)Math.ceil(consDAO.totalConsultCnt(send, type)/5.0);

		if(tempStart != 0)
			consultPager.setPrev(true);
		else
			consultPager.setPrev(false);
		
		if(tempEnd < totalPageCnt)
			consultPager.setNext(true);
		else
			consultPager.setNext(false);
		
		if(tempEnd > totalPageCnt)
			consultPager.setTempEnd(tempStart + totalPageCnt%5); 
		else
			consultPager.setTempEnd(tempEnd);
		
		consultPager.setTempStart(tempStart+1);
		consultPager.setStart((currentPage-1)*5); //0,5,10, ...
		consultPager.setEnd(currentPage*5); //5, 10, 15, ... 
		
		System.out.println(consultPager.toString());
		
		return consultPager;
	}

	public List<ConsultDTO> findConsultList(int currentPage, String send ,String type) throws SQLException{
		System.out.println("zz");
		return consDAO.findConsultList(consultPager, send, type);
	}

	public List<ProfDTO> findProfDept(String dept_name) throws SQLException {
		return profDAO.findProfDept(dept_name);
	}
	
	
	public List<ProfDTO> findProfTime(String user_id) throws SQLException {
		return profDAO.findProfTime(user_id);
	}
	
	
	public ConsultDTO findContent(String consult_id, String user_id) throws SQLException{
		return consDAO.findContent(consult_id, user_id);
	}
	
	public String findRecName(String prof_id) throws SQLException{
		return consDAO.findRecName(prof_id);
	}
	
	public ResultDTO findResult(String consult_id, String user_id) throws SQLException{
		return resultDAO.findResult(consult_id, user_id);
	}
	
	public int createResult(ResultDTO cons) throws SQLException{
		return resultDAO.createResult(cons);
	}
	
	public int createProfSch(ProfSchDTO sch) throws Exception{
		return profSchDAO.createSch(sch);
	}
	
	public int deleteResult(String consult_id) throws SQLException {
		return resultDAO.deleteResult(consult_id);
	}
	
	public int consultAccept(String consult_id, String accept) throws SQLException {
		return consDAO.consultAccept(consult_id, accept);
	}
	
	public List<WeekDTO> findTimetable(String user_id, String recipient_id) throws SQLException {
		// TODO Auto-generated method stub
		return findWeek(timeDAO.findTimetable(user_id, recipient_id));
	}
	
	public List<WeekDTO> findWeek(List<TimetableDTO> timtalble) throws SQLException{
		return timeDAO.findWeekTable(timtalble);
	}
	
	public List<StudentDTO> findStudentDept(String dept_name) throws SQLException {
		return studentDAO.findStudentDept(dept_name);
	}
	
	public List<StudentDTO> findStudentTime(String user_id) throws SQLException {
		return studentDAO.findStudentTime(user_id);
	}
	
	public List<StudentDTO> findStudentName(String stu_name) throws SQLException {
		return studentDAO.findStudentName(stu_name);
	}
	
	public String[] findProfSubject(String subject) throws SQLException{
		ProfDTO prof = subjDAO.findProfSub(subject);
		String[] recipient = new String[3];
		recipient[0] = prof.getProf_id();
		recipient[1] = prof.getName();
		return recipient;
	}
	
	public List<ProfSchDTO> findProfSch(String recipient_id, String user_id) throws SQLException{
		return profSchDAO.findProfSch(recipient_id, user_id);
	}
	
	public int giveWeight(List<WeightDTO> weight) throws SQLException{
		return weightDAO.giveWeight(weight);
	}
	
	public List<String> profConsultCompleteList(List<WeightDTO> weightList) throws SQLException{
		
		Map<String, HashMap<String,Integer>> profConsultCnt = new HashMap<String, HashMap<String,Integer>>();
		List<String> list = new ArrayList<String>();
		int totalConsultCount = weightDAO.totalConsultCount();
		
		profConsultCnt = weighCal.profAndStuOfWeightSum(weightDAO.profConsultCompleteList(), weightList, totalConsultCount);
		list = weighCal.profEachCategoryCntSum(profConsultCnt);
		
		return weightDAO.profChangeNameList(list);		
	}
	

}
