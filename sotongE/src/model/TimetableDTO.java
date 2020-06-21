package model;

public class TimetableDTO {
	private String user_id = null; //로그인하고 있는 교수 또는 학생 번호
	private String subject = null; //과목명 
	private String name = null; //해당 과목 강의하는 교수님 이름
	private int day = 0; // 요일 
	private int time = 0; //교시
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getDay() {
		return day;
	}
	public void setDay(int day) {
		this.day = day;
	}
	public int getTime() {
		return time;
	}
	public void setTime(int time) {
		this.time = time;
	}

}
