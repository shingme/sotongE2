package model;

public class TimetableDTO {
	private String user_id = null; //�α����ϰ� �ִ� ���� �Ǵ� �л� ��ȣ
	private String subject = null; //����� 
	private String name = null; //�ش� ���� �����ϴ� ������ �̸�
	private int day = 0; // ���� 
	private int time = 0; //����
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
