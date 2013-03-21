package my.shop.db;

import java.sql.*;
import java.util.*;

import javax.naming.*;
import javax.sql.*;


public class ShopDBBean {
	private static ShopDBBean instance = new ShopDBBean();
	public static ShopDBBean getInstance() {
		return instance;
	}
	public ShopDBBean() {
	}
	
	private Connection getConnection() throws Exception {
		Context initCtx = new InitialContext();
		Context envCtx  = (Context)initCtx.lookup("java:comp/env");
		DataSource ds = (DataSource)envCtx.lookup("jdbc/oracle");
		return ds.getConnection();
	}
	
	public List<ShopDataBean> getCouponList(int start, int end, String id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<ShopDataBean> couponList = null;
		String sql = "";
		
		try {
			conn = getConnection();
			sql = "select a.* from (select ROWNUM as RNUM, b.* from (select * from coupon where id=?" +
			" order by cou_num desc) b) a where a.RNUM >=? and a.RNUM <=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				couponList = new ArrayList<ShopDataBean>();
				do {
					ShopDataBean coupon = new ShopDataBean();
					coupon.setCou_name(rs.getString("cou_name"));
					coupon.setCou_num(rs.getInt("cou_num"));
					coupon.setCou_usage(rs.getInt("cou_usage"));
					coupon.setCou_date(rs.getDate("cou_date"));
					
					couponList.add(coupon);
				} while (rs.next());
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			if(rs != null){
				try{rs.close();}
				catch(SQLException e){e.printStackTrace();}
			}
			if(pstmt != null){
				try{pstmt.close();}
				catch(SQLException e){e.printStackTrace();}
			}
			if(conn != null){
				try{conn.close();}
				catch(SQLException e){e.printStackTrace();}
			}
		}
		return couponList;
	}
	
	public int getCouponCount(String id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int cnt = 0;
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("select count(*) from coupon where id=?");
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				cnt = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			if(rs != null){
				try{rs.close();}
				catch(SQLException e){e.printStackTrace();}
			}
			if(pstmt != null){
				try{pstmt.close();}
				catch(SQLException e){e.printStackTrace();}
			}
			if(conn != null){
				try{conn.close();}
				catch(SQLException e){e.printStackTrace();}
			}
		}
		
		return cnt;
	}
	
	public void createCoupon(String cou_name,String cou_file, int quantity){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		try{
			conn = getConnection();
			sql = "insert into coupon(cou_num,cou_name,cou_usage,cou_date.cou_file) values" +
					"(cou_num_seq.nextval,?,0,sysdate+180,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, cou_name);
			pstmt.setString(2, cou_file);
			for(int i=0;i<quantity;i++){
				rs =pstmt.executeQuery(); 
			}			
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			if(rs!=null)try{rs.close();}catch(SQLException ex){}
			if(pstmt!=null)try{pstmt.close();}catch(SQLException ex){}
			if(conn!=null)try{conn.close();}catch(SQLException ex){}
		}
	}
	public int allQuantity(String cou_name){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		int result = 0;
		try{
			conn = getConnection();
			sql = "select cou_name,count(*) as quantity from coupon where cou_name=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, cou_name);
			rs =pstmt.executeQuery();
			if(rs.next()){
				result = rs.getInt("quantity");
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			if(rs!=null)try{rs.close();}catch(SQLException ex){}
			if(pstmt!=null)try{pstmt.close();}catch(SQLException ex){}
			if(conn!=null)try{conn.close();}catch(SQLException ex){}
		}
		return result;
	}
	public int ramainQuantity(String cou_name){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		int result = 0;
		try{
			conn = getConnection();
			sql = "select cou_name,count(*) as quantity from coupon where cou_name=? ans cou_usage=1";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, cou_name);
			rs =pstmt.executeQuery();
			if(rs.next()){
				result = rs.getInt("quantity");
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			if(rs!=null)try{rs.close();}catch(SQLException ex){}
			if(pstmt!=null)try{pstmt.close();}catch(SQLException ex){}
			if(conn!=null)try{conn.close();}catch(SQLException ex){}
		}
		return result;
	}
	public void sellCoupon(String id,String cou_name){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		int count = 0;
		try{
			conn = getConnection();
			sql = "select max(cou_num) from coupon where cou_name=? and cou_usage=0";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, cou_name);
			rs =pstmt.executeQuery();
			if(rs.next()){
				count = rs.getInt(1);
			}
			sql = "update coupon set cou_usage=1, id=? where cou_num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, cou_name);
			pstmt.executeUpdate();
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			if(rs!=null)try{rs.close();}catch(SQLException ex){}
			if(pstmt!=null)try{pstmt.close();}catch(SQLException ex){}
			if(conn!=null)try{conn.close();}catch(SQLException ex){}
		}
	}
	public List<ShopDataBean> getarticleList() throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		List<ShopDataBean> articleList = null;
		ShopDataBean shopBean = null;
		try{
			conn = getConnection();
			sql = "select cou_name,count(*) from coupon";
			pstmt = conn.prepareStatement(sql);
			rs =pstmt.executeQuery();
			if(rs.next()){
				articleList = new ArrayList<ShopDataBean>();
				do{
					shopBean = new ShopDataBean();
					shopBean.setCou_name(rs.getString("cou_name"));
					shopBean.setCou_quantity(rs.getInt(2));
					articleList.add(shopBean);
				}
				while(rs.next());
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			if(rs!=null)try{rs.close();}catch(SQLException ex){}
			if(pstmt!=null)try{pstmt.close();}catch(SQLException ex){}
			if(conn!=null)try{conn.close();}catch(SQLException ex){}
		}
		return articleList;
	}
}
