package model;

public class ProfSchDTO {

	private String prof_id; //������ȣ
	private String day;	//����
	private String time; //����
	private String title;	//������
	private String cons_date; //��¥
	
	public String getProf_id() {
		return prof_id;
	}
	public void setProf_id(String prof_id) {
		this.prof_id = prof_id;
	}
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getCons_date() {
		return cons_date;
	}
	public void setCons_date(String cons_date) {
		this.cons_date = cons_date;
	}
	
}
