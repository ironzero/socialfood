package my.shop.db;


public class ShopDataBean {
	
	private int cou_num, cou_usage ,cou_quantity;
	private String cou_name, cou_owner;
	private java.sql.Date cou_date;
	private int shop_id, shop_priceoriginal, shop_pricesale;
	private String shop_name,shop_imagefilename,shop_detail ;
	private long shop_imagefilesize;
	
	public int getCou_quantity() {
		return cou_quantity;
	}
	public void setCou_quantity(int cou_quantity) {
		this.cou_quantity = cou_quantity;
	}
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
	public String getCou_owner() {
		return cou_owner;
	}
	public void setCou_owner(String cou_owner) {
		this.cou_owner = cou_owner;
	}
	public int getShop_id() {
		return shop_id;
	}
	public void setShop_id(int shop_id) {
		this.shop_id = shop_id;
	}
	public int getShop_priceoriginal() {
		return shop_priceoriginal;
	}
	public void setShop_priceoriginal(int shop_priceoriginal) {
		this.shop_priceoriginal = shop_priceoriginal;
	}
	public int getShop_pricesale() {
		return shop_pricesale;
	}
	public void setShop_pricesale(int shop_pricesale) {
		this.shop_pricesale = shop_pricesale;
	}
	public String getShop_name() {
		return shop_name;
	}
	public void setShop_name(String shop_name) {
		this.shop_name = shop_name;
	}
	public String getShop_imagefilename() {
		return shop_imagefilename;
	}
	public void setShop_imagefilename(String shop_imagefilename) {
		this.shop_imagefilename = shop_imagefilename;
	}
	public String getShop_detail() {
		return shop_detail;
	}
	public void setShop_detail(String shop_detail) {
		this.shop_detail = shop_detail;
	}
	public long getShop_imagefilesize() {
		return shop_imagefilesize;
	}
	public void setShop_imagefilesize(long shop_imagefilesize) {
		this.shop_imagefilesize = shop_imagefilesize;
	}
	public java.sql.Date getCou_date() {
		return cou_date;
	}
	public void setCou_date(java.sql.Date cou_date) {
		this.cou_date = cou_date;
	}
	
}
