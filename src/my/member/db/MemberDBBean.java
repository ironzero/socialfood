package my.member.db;

import java.sql.*;
import java.util.*;

import javax.naming.*;
import javax.sql.*;

import my.board.db.*;

public class MemberDBBean {
	private static MemberDBBean instance = new MemberDBBean();

	public static MemberDBBean getInstance() {
		return instance;
	}

	public MemberDBBean() {
		// TODO Auto-generated constructor stub
	}

	private Connection getConnection() throws Exception {
		Context initCtx = new InitialContext();
		Context envCtx = (Context) initCtx.lookup("java:comp/env");
		DataSource ds = (DataSource) envCtx.lookup("jdbc/oracle");
		return ds.getConnection();
	}

	public void joinMember(MemberBean member) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = getConnection();
			pstmt = conn
					.prepareStatement("insert into member(id, nickname, password, name, email, reg_date, birth_date, phone, locate) values(?, ?, ?, ?, ?, ?, ?, ?, ?)");
			pstmt.setString(1, member.getId());
			pstmt.setString(2, member.getNickname());
			pstmt.setString(3, member.getPasswd());
			pstmt.setString(4, member.getName());
			pstmt.setString(5, member.getEmail());
			pstmt.setTimestamp(6, member.getReg_date());
			pstmt.setDate(7, member.getBirth_date());
			pstmt.setString(8, member.getPhone());
			pstmt.setString(9, member.getLocate());

			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public boolean checkId(String id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("select id from member where id=?");
			pstmt.setString(1, id);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return false;
	}

	public boolean checkNick(String nick) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = getConnection();
			pstmt = conn
					.prepareStatement("select nickname from member where nickname=?");
			pstmt.setString(1, nick);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return false;
	}

	public boolean checkPasswd(String id, String passwd) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = getConnection();
			pstmt = conn
					.prepareStatement("select password from member where id=?");
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				if (passwd.equals(rs.getString(1))) {
					return true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return false;
	}

	public List<MemberBean> getMemberList(int start, int end) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<MemberBean> memberList = null;
		String sql = "";

		try {
			conn = getConnection();
			sql = "select a.* from (select ROWNUM as RNUM, b.* from (select * from member"
					+ " order by reg_date desc) b) a where a.RNUM >=? and a.RNUM <=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				memberList = new ArrayList<MemberBean>();
				do {
					MemberBean member = new MemberBean();
					member.setId(rs.getString("id"));
					member.setName(rs.getString("name"));
					member.setNickname(rs.getString("nickname"));
					member.setEmail(rs.getString("email"));
					member.setPhone(rs.getString("phone"));
					member.setLocate(rs.getString("locate"));
					member.setPoint(rs.getInt("point"));
					member.setBirth_date(rs.getDate("birth_date"));
					member.setReg_date(rs.getTimestamp("reg_date"));
					member.setLast_login_ip(rs.getString("last_login_ip"));
					member.setLast_login_time(rs
							.getTimestamp("last_login_time"));
					member.setLv(rs.getInt("lv"));

					memberList.add(member);
				} while (rs.next());
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return memberList;
	}

	public int getMemberCount() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int cnt = 0;

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("select count(*) from member");
			rs = pstmt.executeQuery();

			if (rs.next()) {
				cnt = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		return cnt;
	}

	public List<MemberBean> getSearchMemberList(int start, int end,
			String colName, String search) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<MemberBean> memberList = null;
		String sql = null;

		try {
			conn = getConnection();
			sql = "select a.* from (select ROWNUM as RNUM, b.* from (select * from member where "
					+ colName
					+ " like '%"
					+ search
					+ "%' order by reg_date desc) b) a where a.RNUM >=? and a.RNUM <=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				memberList = new ArrayList<MemberBean>();
				do {
					MemberBean member = new MemberBean();
					member.setId(rs.getString("id"));
					member.setName(rs.getString("name"));
					member.setNickname(rs.getString("nickname"));
					member.setEmail(rs.getString("email"));
					member.setPhone(rs.getString("phone"));
					member.setLocate(rs.getString("locate"));
					member.setPoint(rs.getInt("point"));
					member.setBirth_date(rs.getDate("birth_date"));
					member.setReg_date(rs.getTimestamp("reg_date"));
					member.setLast_login_ip(rs.getString("last_login_ip"));
					member.setLast_login_time(rs
							.getTimestamp("last_login_time"));
					member.setLv(rs.getInt("lv"));

					memberList.add(member);
				} while (rs.next());
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return memberList;
	}

	public int getSearchCount(String colName, String search) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuilder sb = new StringBuilder();
		sb.append("select count(*) from member where ");
		sb.append(colName);
		sb.append(" like '%");
		sb.append(search);
		sb.append("%'");
		int cnt = 0;

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sb.toString());
			rs = pstmt.executeQuery();

			if (rs.next()) {
				cnt = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		return cnt;
	}

	public void deleteMember(String id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("delete from member where id=?");
			pstmt.setString(1, id);
			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public MemberBean getMemberInfo(String id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MemberBean member = null;

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("select * from member where id=?");
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				member = new MemberBean();
				member.setId(id);
				member.setPasswd(rs.getString("password"));
				member.setName(rs.getString("name"));
				member.setBirth_date(rs.getDate("birth_date"));
				member.setEmail(rs.getString("email"));
				member.setLast_login_time(rs.getTimestamp("last_login_time"));
				member.setPhone(rs.getString("phone"));
				member.setLocate(rs.getString("locate"));
				member.setNickname(rs.getString("nickname"));
				member.setReg_date(rs.getTimestamp("reg_date"));
				member.setLast_login_ip(rs.getString("last_login_ip"));
				member.setPoint(rs.getInt("point"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		return member;
	}

	public boolean updateMember(MemberBean member) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean result = false;

		try {
			conn = getConnection();

			pstmt = conn
					.prepareStatement("update member set password=?, email=?, phone=?, birth_date=?, locate=? where id=?");
			pstmt.setString(1, member.getPasswd());
			pstmt.setString(2, member.getEmail());
			pstmt.setString(3, member.getPhone());
			pstmt.setDate(4, member.getBirth_date());
			pstmt.setString(5, member.getLocate());
			pstmt.setString(6, member.getId());
			pstmt.executeUpdate();

			result = true;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		return result;
	}

	public List<BoardDataBean> getArticleList(int start, int end, String id)
			throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<BoardDataBean> articleList = null;
		String sql = "";
		try {
			conn = getConnection();
			/*추천지수 불러오는sql*/
			sql = "update board set recommand_count = recommand - non_recommand ";
			pstmt = conn.prepareStatement(sql);
			pstmt.executeUpdate();

			sql = "select a.* from (select ROWNUM as RNUM, b.* from (select * from board union all select * from food union all select * from travel) b where id=?) a where a.RNUM >=? and a.RNUM <=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				articleList = new ArrayList<BoardDataBean>(end);
				do {
					BoardDataBean article = new BoardDataBean();
					article.setIdx(rs.getInt("idx"));
					article.setArea_idx(rs.getInt("area_idx"));
					article.setTitle(rs.getString("title"));
					article.setContent(rs.getString("content"));
					article.setNickname(rs.getString("nickname"));
					article.setId(rs.getString("id"));
					article.setWdate(rs.getTimestamp("wdate"));
					article.setRead_count(rs.getInt("read_count"));
					article.setRecommand_count(rs.getInt("recommand_count"));
					article.setRecommand(rs.getInt("recommand"));
					article.setNon_recommand(rs.getInt("non_recommand"));
					article.setCategory(rs.getString("category"));
					article.setDepth(rs.getInt("depth"));
					articleList.add(article);

				} while (rs.next());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return articleList;
	}
	
	public int getArticleCount(String id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		int cnt = 0;
		try {
			conn = getConnection();

			sql = "select count(*) from (select ROWNUM as RNUM, b.* from (select * from board union all select * from food union all select * from travel) b where id=?) a";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			rs.next();
			cnt = rs.getInt(1);
		
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return cnt;
	}
	
	
	public int loginCheck(String id, String password) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		int isLogin = 0;
		try{
			conn = getConnection();
			sql = "select count(*) from member where id=? and password=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, password);
			rs = pstmt.executeQuery();
			rs.next();
			isLogin += rs.getInt(1);
			if(rs.getInt(1)>0 && id.equals("admin") ||rs.getInt(1)>0 && id.equals("pbreaker"))
				isLogin += 1;
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
		return isLogin;
	}
	public String getNickname(String id) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		String nickname = "";
		try{
			conn = getConnection();
			sql = "select nickname from member where id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()){
			nickname = rs.getString(1);
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
		return nickname;
	}
	
}
