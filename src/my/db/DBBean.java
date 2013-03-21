package my.db;

import java.sql.*;
import java.util.*;

import javax.naming.*;
import javax.sql.*;

import com.sun.org.apache.bcel.internal.generic.ARRAYLENGTH;

public class DBBean {
	private static DBBean instance = new DBBean();

	public static DBBean getInstance() {
		return instance;
	}

	private DBBean() {

	}

	private Connection getConnection() throws Exception {
		Context initCtx = new InitialContext();
		Context envCtx = (Context) initCtx.lookup("java:comp/env");
		DataSource ds = (DataSource) envCtx.lookup("jdbc/oracle");
		return ds.getConnection();
	}

	public List getWhatsNew() throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		List newList =null;
		DataBean bean = null;
		int k=0;
		try{
			conn = getConnection();
			sql="select ROWNUM, b.* from (select * from (select * from board union all select * from food union all select * from travel)" +
					" order by wdate desc) b where ROWNUM<=3";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()){
				newList = new ArrayList(3);
				do{
					bean = new DataBean();
					bean.setTitle(rs.getString("title"));
					bean.setNickname(rs.getString("nickname"));
					newList.add(bean);
				}while(rs.next());
				System.out.println(newList.size());
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
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
		}return newList;
	}
	
	public List getRecommandHot() throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		List recommandList = null;
		DataBean bean = null;
		int k=0;
		try{
			sql="select ROWNUM, b.* from (select * from (select * from board union all select * from food union all select * from travel)" +
					" order by recommand_count desc) b where ROWNUM<=3";
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()){
				recommandList = new ArrayList(3);
					do{
						bean = new DataBean();
						bean.setTitle(rs.getString("title"));
						bean.setNickname(rs.getString("nickname"));
						recommandList.add(bean);
					}while(rs.next());
				
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
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
		}return recommandList;
	}
	
	public List getHotIssu() throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		List issueList = null;
		DataBean bean = null;
		try{
			sql="select ROWNUM, b.* from (select * from (select * from board union all select * from food union all select * from travel)" +
					" order by read_count desc) b where ROWNUM<=3";
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()){
				issueList = new ArrayList(3);
				do{
					bean = new DataBean();
					bean.setTitle(rs.getString("title"));
					bean.setNickname(rs.getString("nickname"));
					issueList.add(bean);
				}while(rs.next());
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
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
		}return issueList;
	}
}
