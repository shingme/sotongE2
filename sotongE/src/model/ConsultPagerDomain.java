package model;

public class ConsultPagerDomain {
	private int tempStart;
	private int tempEnd;
	private int start;
	private int end;
	private boolean prev, next;
	
	public int getTempEnd() {
		return tempEnd;
	}
	public void setTempEnd(int tempEnd) {
		this.tempEnd = tempEnd;
	}
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public int getEnd() {
		return end;
	}
	public void setEnd(int end) {
		this.end = end;
	}
	public boolean isPrev() {
		return prev;
	}
	public void setPrev(boolean prev) {
		this.prev = prev;
	}
	public boolean isNext() {
		return next;
	}
	public void setNext(boolean next) {
		this.next = next;
	}
	public int getTempStart() {
		return tempStart;
	}
	public void setTempStart(int tempStart) {
		this.tempStart = tempStart;
	}
	@Override
	public String toString() {
		return "ConsultPagerDomain [tempStart=" + tempStart + ", tempEnd=" + tempEnd + ", start=" + start + ", end="
				+ end + ", prev=" + prev + ", next=" + next + "]";
	}
	
	
	
}
