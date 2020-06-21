
package model;

public class WeightDTO {
	
	private String category="";
	private String stu_id="";
	private int weight=0;
	
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getStu_id() {
		return stu_id;
	}
	public void setStu_id(String stu_id) {
		this.stu_id = stu_id;
	}
	public int getWeight() {
		return weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
	@Override
	public String toString() {
		return "WeightDTO [category=" + category + ", stu_id=" + stu_id + ", weight=" + weight + "]";
	}

	
	
}
