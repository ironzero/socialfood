package my.board.db;

import java.sql.*;
import java.util.*;

import javax.naming.*;
import javax.sql.*;


public class BoardDBBean {
	private static BoardDBBean instance = new BoardDBBean();
	public static BoardDBBean getInstance(){
		return instance;
	}
	private BoardDBBean(){
		
	}
	private Connection getConnection() throws Exception{
		Context initCtx = new InitialContext();
		Context envCtx  = (Context)initCtx.lookup("java:comp/env");
		DataSource ds = (DataSource)envCtx.lookup("jdbc/oracle");
		return ds.getConnection();
	}
	public int insertArticle(BoardDataBean article) {
		int pk = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int idx = article.getIdx();
		int ref = article.getRef();
		int step = article.getStep();
		int depth = article.getDepth();
		int area = article.getArea();
		String area_seq = "board_area_"+ area +"_idx_seq.nextval";
		String sql_PK_currval = "select board_idx_seq.currval from dual";
		StringBuilder sql_sb = new StringBuilder("select max(idx) from board ");
		String sql = "";
		try{
			conn = getConnection();
			pstmt = conn.prepareStatement(sql_sb.toString());// max(area_idx)
			rs = pstmt.executeQuery();
			if(rs.next()){
				pk = rs.getInt(1)+1;
			}else
				pk = 1;
			if(idx!=0){//답글일 때 기존 답글을 아래로 민다.
				sql_sb.replace(0, sql_sb.length()," update board set step=step+1 where ref=? and step>? and area=?");
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, ref);
				pstmt.setInt(2, step);
				pstmt.setInt(3, area);
				int howMany = pstmt.executeUpdate();
				step = step+1;
				depth = depth+1;
				
			}else{// idx == 0 새 글이라면.
				ref = pk;
				step = 0;
				depth= 0;
			}
			sql_sb.replace(0, sql_sb.length(), " insert into board (idx, " );
			sql_sb.append( "title, wdate, content, category, area, area_idx, ");
			sql_sb.append( "ref, depth, step, id, nickname)");
			// 열의 값들
			sql_sb.append( " values( board_idx_seq.nextval");	//idx
			sql_sb.append( " ,?,?,?,?,?, " + area_seq + " ,?,?,?,?,?)");
			pstmt = conn.prepareStatement(sql_sb.toString());
			pstmt.setString(1, article.getTitle());
			pstmt.setTimestamp(2, article.getWdate());
			pstmt.setString(3, article.getContent());
			pstmt.setString(4, article.getCategory());
			pstmt.setInt(5, area);
			pstmt.setInt(6, ref);
			pstmt.setInt(7, depth);
			pstmt.setInt(8, step);
			pstmt.setString(9, article.getId());
			pstmt.setString(10, article.getNickname());

			int r = pstmt.executeUpdate();
			//System.out.println("인서트 성공여부 0 or 1 : "+r);
			pstmt = conn.prepareStatement(sql_PK_currval );
			rs = pstmt.executeQuery();
			rs.next();
			pk = rs.getInt(1);
		
			if( r != 1 )
				pk = 0;
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			if(rs!=null)try{rs.close();}catch(SQLException ex){}
			if(pstmt!=null)try{pstmt.close();}catch(SQLException ex){}
			if(conn!=null)try{conn.close();}catch(SQLException ex){}
		}
		return pk;
	}
	
	public int getArticleCount(int area) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs =null;
		int result = 0;
		try{
			conn = getConnection();
			pstmt = conn.prepareStatement("select count(*) from board where area=?");
			pstmt.setInt(1, area);
			rs = pstmt.executeQuery();
			if(rs.next()){
				result = rs.getInt(1);
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
		}
		return result;
	}
	
	/*리스트 불러오기*/
	public List getarticleList(int start, int end, int area) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List articleList = null;
		String sql  = "";
		try{
			conn = getConnection();
			/*추천지수 불러오는sql*/
			sql = "update board set recommand_count = recommand - non_recommand ";
			pstmt = conn.prepareStatement(sql);
			pstmt.executeUpdate();
		
			sql = "select a.* from (select ROWNUM as RNUM, b.* from (select * from board" +
					" order by idx desc) b order by ref desc, step asc) a where a.RNUM >=? and a.RNUM <=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			rs = pstmt.executeQuery();
			if(rs.next()){
				articleList = new ArrayList(end);
				do{
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
					
				}
				while(rs.next());
			}
		}catch(Exception ex){
			System.out.println("boardDBBean getarticleList : " + ex);
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
		}
		return articleList;
		
	}
	
	/*게시글 내용받아오기*/
	public BoardDataBean getArticle(int idx) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BoardDataBean article = null;
		String sql="";
		try{
			conn = getConnection();
			sql = "update board set read_count = read_count + 1 where idx=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, idx);
			pstmt.executeUpdate();
			sql = "select * from board where idx=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, idx);
			rs = pstmt.executeQuery();
			if(rs.next()){
				article = new BoardDataBean();
				article.setArea_idx(rs.getInt("area_idx"));
				article.setIdx(rs.getInt("idx"));
				article.setNickname(rs.getString("nickname"));
				article.setId(rs.getString("id"));
				article.setTitle(rs.getString("title"));
				article.setWdate(rs.getTimestamp("wdate"));
				article.setRead_count(rs.getInt("read_count"));
				article.setRef(rs.getInt("ref"));
				article.setStep(rs.getInt("step"));
				article.setDepth(rs.getInt("depth"));
				article.setContent(rs.getString("content"));
				article.setRecommand_count(rs.getInt("recommand_count"));
				article.setRecommand(rs.getInt("recommand"));
				article.setNon_recommand(rs.getInt("non_recommand"));
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
		}
		return article;
	}
	
	/*추천수 올리기*/
	public void CommandPlus(int idx) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BoardDataBean article = null;
		String sql="";
		try{
			conn = getConnection();
			sql = "update board set recommand = recommand + 1 where idx=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, idx);
			pstmt.executeUpdate();
		}catch(Exception ex){
			System.out.println("boardDBBean CommandPlus : " + ex);
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
		}
	}
	
	/*비추천 올리기*/
	public void Non_CommandPlus(int idx) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BoardDataBean article = null;
		String sql="";
		try{
			conn = getConnection();
			sql = "update board set non_recommand = non_recommand + 1 where idx=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, idx);
			pstmt.executeUpdate();
		}catch(Exception ex){
			System.out.println("boardDBBean CommandPlus : " + ex);
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
		}
	}
	/*게시글 수정*/
	public void updateArticle(int idx, String title, String content) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BoardDataBean article = null;
		String sql="";
		try{
			conn = getConnection();
			sql = "update board set title=?, content=? where idx=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, title);
			pstmt.setString(2, content);
			pstmt.setInt(3, idx);
			pstmt.executeUpdate();
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
		}
	}
	/*게시글 삭제*/
	public void deleteArticle(int idx) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BoardDataBean article = null;
		String sql="";
		try{
			conn = getConnection();
			sql = "delete from boardcommentary where idx=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, idx);
			pstmt.executeUpdate();
			sql = "delete from boardfile where idx=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, idx);
			pstmt.executeUpdate();
			sql = "delete from board where idx=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, idx);
			pstmt.executeUpdate();
			
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
		}
	}
	
	public int getSearchArticleCount(String search,String val, int area) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		Statement stmt = null;
		ResultSet rs = null;
		int x = 0;
		try{
			conn = getConnection();
			String sql = "select count(*) from board where "+search+" like '%"+val+"%' and area="+area;
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			if(rs.next()){
				x = rs.getInt(1);
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
		}
		return x;
	}
	public List getSearchArticles(int start, int end,String search, String val, int area) throws Exception{
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		List<BoardDataBean> articleList = null;
		try{
			conn = getConnection();
//			val = new String(val.getBytes("iso_8859-1"),"utf-8");
			String sql = "select * from (select ROWNUM as RNUM, b.* from (select * from board" +
					" where area="+area+" and "+search+" like '%"+val+"%'"+" order by ref desc, step asc) b) a " +
					"where RNUM >="+start+" and RNUM <="+end;
			System.out.println(sql);
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			if(rs.next()){
				articleList = new ArrayList(end);
				do{
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
					articleList.add(article);
				}while(rs.next());
			}
		}catch(Exception ex){
			System.out.println("boardDBBean getSearcharticles : " + ex);
		}finally{
			if(rs != null){
				try{rs.close();}
				catch(SQLException e){e.printStackTrace();}
			}
			if(stmt != null){
				try{stmt.close();}
				catch(SQLException e){e.printStackTrace();}
			}
			if(conn != null){
				try{conn.close();}
				catch(SQLException e){e.printStackTrace();}
			}
		}
		return articleList;
	}
	public void saveFile(BoardFileBean file)  throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "insert into boardfile values(boardfile_fileid_seq.nextval,?,?,?)";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, file.getFilename());
			pstmt.setLong(2, file.getFilesize());
			pstmt.setInt(3, file.getIdx());
			pstmt.executeUpdate();
				
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(rs!=null)try{rs.close();}catch(SQLException ex){}
			if(pstmt!=null)try{pstmt.close();}catch(SQLException ex){}
			if(conn!=null)try{conn.close();}catch(SQLException ex){}
		}
	}
	public int deleteFile(int fileid) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		int r = 0;
		try{
			conn = getConnection();
			sql = "delete from boardfile where fileid=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, fileid);
			r = pstmt.executeUpdate();
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			if(rs!=null)try{rs.close();}catch(SQLException ex){}
			if(pstmt!=null)try{pstmt.close();}catch(SQLException ex){}
			if(conn!=null)try{conn.close();}catch(SQLException ex){}
		}
		return r;
	}
	public BoardFileBean getFileName(int idx) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BoardFileBean fileBean = null;
		String sql = "";
		String filename = "";
		String fileTmp = "";
		try{
			conn = getConnection();
			sql = "select * from boardfile where idx=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, idx);
			rs = pstmt.executeQuery();
			fileBean = new BoardFileBean();
			if(rs.next()){
				fileBean.setFileid(rs.getInt("fileid"));
				fileTmp = rs.getString("filename");
				StringTokenizer st = new StringTokenizer(fileTmp,"\\");
				while(st.hasMoreElements()){
					filename=st.nextToken();
				}
				fileBean.setFilename(filename);
				fileBean.setRealpath("upload/"+filename);
			}else{
				fileBean.setFilename("파일없음");
				fileBean.setRealpath("파일없음");
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			if(rs!=null)try{rs.close();}catch(SQLException ex){}
			if(pstmt!=null)try{pstmt.close();}catch(SQLException ex){}
			if(conn!=null)try{conn.close();}catch(SQLException ex){}
		}
		return fileBean;
	}
	public List getFileList(int idx) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BoardFileBean fileBean = null;
		String sql = "";
		List fileList = null;
		String filename = "";
		String fileTmp = "";
		try{
			conn = getConnection();
			sql = "select * from boardfile where idx=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, idx);
			rs = pstmt.executeQuery();
			fileList = new ArrayList(5);
			if(rs.next()){
				do{
					fileBean = new BoardFileBean();
					fileTmp = rs.getString("filename");
					StringTokenizer st = new StringTokenizer(fileTmp,"\\");
					while(st.hasMoreElements()){
						filename=st.nextToken();
					}
					fileBean.setFilename(filename);
					fileBean.setRealpath("../upload/"+filename);
					fileBean.setFilesize(rs.getLong("filesize"));
					fileList.add(fileBean);
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
		return fileList;
	}
	/*
	public int getIdxNum() throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int x = 0;
		try{
			conn=getConnection();
			String sql = "select max(idx) from board";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()){
				x = rs.getInt(1) +1;
			}
			
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			if(rs!=null)try{rs.close();}catch(SQLException ex){}
			if(pstmt!=null)try{pstmt.close();}catch(SQLException ex){}
			if(conn!=null)try{conn.close();}catch(SQLException ex){}
		}
		return x;
	}
	*/
	public int getFileCount(int idx) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs =null;
		int result = 0;
		try{
			conn = getConnection();
			pstmt = conn.prepareStatement("select count(*) from board where idx=?");
			pstmt.setInt(1, idx);
			rs = pstmt.executeQuery();
			if(rs.next()){
				result = rs.getInt(1);
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
		}
		return result;
	}
	public int getBoardCommentaryCount(int idx) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs =null;
		int result = 0;
		String sql = "";
		try{
			conn = getConnection();
			sql = "select count(*) from boardcommentary where idx=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, idx);
			rs = pstmt.executeQuery();
			if(rs.next()){
				result = rs.getInt(1);
			}
		}catch(Exception ex ){
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
		}
		return result;
	}
	public List getBoardCommentaryList(int idx) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List commList = null;
		String sql = "";
		BoardDataBean comm = null;
		try{
			conn = getConnection();
			sql  = "select * from boardcommentary where idx=? order by num desc";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, idx);
			rs = pstmt.executeQuery();
			if(rs.next()){
				commList = new ArrayList();
				do{
					comm = new BoardDataBean();
					comm.setComm_content(rs.getString("content"));
					comm.setComm_nickname(rs.getString("nickname"));
					comm.setComm_wdate(rs.getTimestamp("wdate"));
					commList.add(comm);
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
	return commList;
	}
	
	public void insertCommentary(BoardDataBean article) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
			try{
				conn = getConnection();
				
				sql = "insert into boardcommentary(num,nickname,content,wdate,idx) values" +
						"(boardcommentary_num_seq.nextval,?,?,?,?)";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, article.getComm_nickname());
				pstmt.setString(2, article.getComm_content());
				pstmt.setTimestamp(3, article.getComm_wdate());
				pstmt.setInt(4, article.getComm_idx());
				int result =pstmt.executeUpdate();
			}catch(Exception ex){
				ex.printStackTrace();
			}finally{
				if(rs!=null)try{rs.close();}catch(SQLException ex){}
				if(pstmt!=null)try{pstmt.close();}catch(SQLException ex){}
				if(conn!=null)try{conn.close();}catch(SQLException ex){}
			}
		}	
}
