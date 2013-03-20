package my.shop.db;

public class CouponBean {
	private int cou_num, cou_usage;
	private String cou_name;
	private java.sql.Date cou_date;
	
	public int getCou_num() {
		return cou_num;
	}
	public void setCou_num(int cou_num) {
		this.cou_num = cou_num;
	}
	public int getCou_usage() {
		return cou_usage;
	}
	public void setCou_usage(int cou_usage) {
		this.cou_usage = cou_usage;
	}
	public String getCou_name() {
		return cou_name;
	}
	public void setCou_name(String cou_name) {
		this.cou_name = cou_name;
	}
	public java.sql.Date getCou_date() {
		return cou_date;
	}
	public void setCou_date(java.sql.Date cou_date) {
		this.cou_date = cou_date;
	}
	
}
