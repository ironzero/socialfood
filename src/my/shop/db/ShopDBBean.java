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
	
	public List<CouponBean> getCouponList(int start, int end, String id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<CouponBean> couponList = null;
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
				couponList = new ArrayList<CouponBean>();
				do {
					CouponBean coupon = new CouponBean();
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
}
