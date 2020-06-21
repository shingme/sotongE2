package model;

public class ResultDTO {
	private String consult_id = null;
	private String user_id = null;
	private String result_cons = null;
	private String cons_date = null;

	public String getConsult_id() {
		return consult_id;
	}
	public void setConsult_id(String consult_id) {
		this.consult_id = consult_id;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getResult_cons() {
		return result_cons;
	}
	public void setResult_cons(String result_cons) {
		this.result_cons = result_cons;
	}
	public String getCons_date() {
		return cons_date;
	}
	public void setCons_date(String cons_date) {
		this.cons_date = cons_date;
	}
}

