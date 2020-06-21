package model;

public class StudentDTO {
	private String stu_id = null;	//�й�
	private String name = null;	//�л� �̸�
	private String stu_pwd = null;	//��й�ȣ
	private String dept_name = null;
	
	public String getDept_name() {
		return dept_name;
	}
	public void setDept_name(String dept_name) {
		this.dept_name = dept_name;
	}
	public String getStu_id() {
		return stu_id;
	}
	public void setStu_id(String stu_id) {
		this.stu_id = stu_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStu_pwd() {
		return stu_pwd;
	}
	public void setStu_pwd(String stu_pwd) {
		this.stu_pwd = stu_pwd;
	}
	/**
	 * ��й�ȣ�� ��ġ�ϴ��� ���θ� �����ϴ� �޽��.
	 */
	public boolean isMatchPassword(String inputPassword){
		if ( getStu_pwd().equals(inputPassword)){
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public String toString() {
		return "StudentDTO [stu_id=" + stu_id + ", name=" + name + ", stu_pwd=" + stu_pwd + "]";
	}
	
	
	
	
}
