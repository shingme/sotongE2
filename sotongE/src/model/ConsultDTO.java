package model;

public class ConsultDTO {
	private String consult_id = null;
	private String category = null; 
	private String recipient = null;
	private String recipient_id = null;
	private String send = null;
	private String send_id = null;
	private String cons_date = null; 
	private String time = null; 
	private String content = null; 
	private String accept = null; 
	private String read = null;                       
	private String title = null; 
	private String result=null;
	private String current_date = null;

	public String getCurrent_date() {
		return current_date;
	}
	public void setCurrent_date(String current_date) {
		this.current_date = current_date;
	}
	public String getConsult_id() {
		return consult_id;
	}
	public void setConsult_id(String consult_id) {
		this.consult_id = consult_id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getRecipient() {
		return recipient;
	}
	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}
	
	public String getRecipient_id() {
		return recipient_id;
	}
	public void setRecipient_id(String recipient_id) {
		this.recipient_id = recipient_id;
	}
	public String getSend() {
		return send;
	}
	public void setSend(String send) {
		this.send = send;
	}
	public String getSend_id() {
		return send_id;
	}
	public void setSend_id(String send_id) {
		this.send_id = send_id;
	}
	public String getCons_date() {
		return cons_date;
	}
	public void setCons_date(String cons_date) {
		this.cons_date = cons_date;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getAccept() {
		return accept;
	}
	public void setAccept(String accept) {
		this.accept = accept;
	}
	public String getRead() {
		return read;
	}
	public void setRead(String read) {
		this.read = read;
	}
	public String getResult() {
		if(accept==null){
			accept="";
		}
		result = resultCheck(accept,read);
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	
	public String resultCheck(String accept, String read){
		 if(read.equals("N")){
	         result = "미확인";
	      }
	      else{
	         if(accept.equals("Y"))
	            result = "수락";
	         else if(accept.equals("N"))
	            result = "거절";
	         else
	            result = "확인";
	      }
	      return result;
	}
}

