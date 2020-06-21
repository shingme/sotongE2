package model;

public class ProfDTO {

	private String prof_id; //교수번호
	private String name;	//교수명
	private String prof_pwd; //비밀번호
	private String office;	//연구실
	private String dept_name;
	
	public String getDept_name() {
		return dept_name;
	}
	public void setDept_name(String dept_name) {
		this.dept_name = dept_name;
	}
	public String getProf_id() {
		return prof_id;
	}
	public void setProf_id(String prof_id) {
		this.prof_id = prof_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getProf_pwd() {
		return prof_pwd;
	}
	public void setProf_pwd(String prof_pwd) {
		this.prof_pwd = prof_pwd;
	}
	public String getOffice() {
		return office;
	}
	public void setOffice(String office) {
		this.office = office;
	}
	public boolean isMatchPassword(String inputPassword){
		if ( getProf_pwd().equals(inputPassword)){
			return true;
		} else {
			return false;
		}
	}

	
}
