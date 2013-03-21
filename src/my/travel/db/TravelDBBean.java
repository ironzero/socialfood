package my.travel.db;

import java.sql.*;
import java.util.*;

import javax.naming.*;
import javax.sql.*;


public class TravelDBBean {
	private static TravelDBBean instance = new TravelDBBean();
	public static TravelDBBean getInstance(){
		return instance;
	}
	private TravelDBBean(){
		
	}
	private Connection getConnection() throws Exception{
		Context initCtx = new InitialContext();
		Context envCtx  = (Context)initCtx.lookup("java:comp/env");
		DataSource ds = (DataSource)envCtx.lookup("jdbc/oracle");
		return ds.getConnection();
	}
	public int insertArticle(TravelDataBean article) {
		int pk = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int idx = article.getIdx();
		int ref = article.getRef();
		int step = article.getStep();
		int depth = article.getDepth();
		int area = article.getArea();
		String sql = "";
		try{
			conn = getConnection();
			pstmt = conn.prepareStatement("select max(idx) from travel where area=1");// max(area_idx)
			rs = pstmt.executeQuery();
			if(rs.next()){
				System.out.println("max(idx) = "+rs.getInt(1));
				pk = rs.getInt(1)+1;
			}else
				pk = 1;
			
			if(idx!=0){//답글일 때 기존 답글을 아래로 민다.
				sql = "update travel set step=step+1 where ref=? and step>? and area=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, ref);
				pstmt.setInt(2, step);
				pstmt.setInt(3, area);
				int howMany = pstmt.executeUpdate();
				System.out.println("뒤로 밀리는  기존 답글은 몇 개? "+howMany);
				step = step+1;
				depth = depth+1;
				
			}else{// idx == 0 새 글이라면.
				ref = pk;
				step = 0;
				depth= 0;
			}
			sql = "insert into travel (idx, title, wdate, content, category, area," +
					" area_idx,  ref, depth, step, nickname, id) values( board_idx_seq.nextval, " +
					"?,?,?,?,?,board_area"+area+"idx_seq.nextval,?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, article.getTitle());
			pstmt.setTimestamp(2, article.getWdate());
			pstmt.setString(3, article.getContent());
			pstmt.setString(4, article.getCategory());
			pstmt.setInt(5, article.getArea());
			pstmt.setInt(6, ref);
			pstmt.setInt(7, depth);
			pstmt.setInt(8, step);
			pstmt.setString(9, article.getNickname());
			pstmt.setString(10, article.getId());

			if( pstmt.executeUpdate() != 1 )
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
			pstmt = conn.prepareStatement("select count(*) from travel where area=?");
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
	public List<TravelDataBean> getarticleList(int start, int end, int area) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<TravelDataBean> articleList = null;
		String sql  = "";
		try{
			conn = getConnection();
			/*추천지수 불러오는sql*/
			sql = "update travel set recommand_count = recommand - non_recommand ";
			pstmt = conn.prepareStatement(sql);
			pstmt.executeUpdate();
		
			sql = "select a.* from (select ROWNUM as RNUM, b.* from (select * from travel" +
			" where area=? order by idx desc) b order by ref desc, step asc) a where a.RNUM >=? and a.RNUM <=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, area);
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
			rs = pstmt.executeQuery();
			if(rs.next()){
				articleList = new ArrayList<TravelDataBean>(end);
				do{
					TravelDataBean article = new TravelDataBean();
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
		return articleList;
		
	}
	
	/*게시글 내용받아오기*/
	public TravelDataBean getArticle(int idx) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		TravelDataBean article = null;
		String sql="";
		try{
			conn = getConnection();
			sql = "update travel set read_count = read_count + 1 where idx=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, idx);
			pstmt.executeUpdate();
			sql = "select * from travel where idx=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, idx);
			rs = pstmt.executeQuery();
			if(rs.next()){
				article = new TravelDataBean();
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
		String sql="";
		try{
			conn = getConnection();
			sql = "update travel set recommand = recommand + 1 where idx=?";
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
	
	/*비추천 올리기*/
	public void Non_CommandPlus(int idx) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql="";
		try{
			conn = getConnection();
			sql = "update travel set non_recommand = non_recommand + 1 where idx=?";
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
	/*게시글 수정*/
	public void updateArticle(int idx, String title, String content) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql="";
		try{
			conn = getConnection();
			sql = "update travel set title=?, content=? where idx=?";
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
		String sql="";
		try{
			conn = getConnection();
			sql = "delete from travelcommentary where idx=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, idx);
			pstmt.executeUpdate();
			sql = "delete from travelfile where idx=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, idx);
			pstmt.executeUpdate();
			sql = "delete from travel where idx=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, idx);
			pstmt.executeUpdate();
			
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
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
			val = new String(val.getBytes("iso_8859-1"),"utf-8");
			String sql = "select count(*) from travel where "+search+" like '%"+val+"%' and area="+area;
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
	public List<TravelDataBean> getSearchArticles(int start, int end,String search, String val, int area) throws Exception{
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		List<TravelDataBean> articleList = null;
		try{
			conn = getConnection();
			val = new String(val.getBytes("iso_8859-1"),"utf-8");
			String sql = "select * from (select ROWNUM as RNUM, b.* from (select * from travel" +
					" where area="+area+" and "+search+" like '%"+val+"%'"+" order by ref desc, step asc) b) a " +
					"where RNUM >="+start+" and RNUM <="+end;
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			if(rs.next()){
				articleList = new ArrayList<TravelDataBean>(end);
				do{
					TravelDataBean article = new TravelDataBean();
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
			ex.printStackTrace();
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
	public void saveFile(TravelFileBean file)  throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "insert into travelfile values(travelfile_fileid_seq.nextval,?,?,?,?)";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, file.getFilename());
			pstmt.setLong(2, file.getFilesize());
			pstmt.setInt(3, file.getFilestep());
			pstmt.setInt(4, file.getIdx());
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
			sql = "delete from travelfile where fileid=?";
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
	public TravelFileBean getFileName(int idx) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		TravelFileBean fileBean = null;
		String sql = "";
		String filename = "";
		String fileTmp = "";
		try{
			conn = getConnection();
			sql = "select * from travelfile where idx=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, idx);
			rs = pstmt.executeQuery();
			fileBean = new TravelFileBean();
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
	public List<TravelFileBean> getFileList(int idx) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		TravelFileBean fileBean = null;
		String sql = "";
		List<TravelFileBean> fileList = null;
		String filename = "";
		String fileTmp = "";
		try{
			conn = getConnection();
			sql = "select * from travelfile where idx=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, idx);
			rs = pstmt.executeQuery();
			fileList = new ArrayList<TravelFileBean>(5);
			if(rs.next()){
				do{
					fileBean = new TravelFileBean();
					fileTmp = rs.getString("filename");
					StringTokenizer st = new StringTokenizer(fileTmp,"\\");
					while(st.hasMoreElements()){
						filename=st.nextToken();
					}
					fileBean.setFilename(filename);
					fileBean.setRealpath("upload/"+filename);
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
	public int getIdxNum() throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int x = 0;
		try{
			conn=getConnection();
			String sql = "select max(idx) from travel";
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
	
	public int getFileCount(int idx) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs =null;
		int result = 0;
		try{
			conn = getConnection();
			pstmt = conn.prepareStatement("select count(*) from travel where idx=?");
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
			sql = "select count(*) from travelcommentary where idx=?";
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
	public List<TravelDataBean> getBoardCommentaryList(int idx) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<TravelDataBean> commList = null;
		String sql = "";
		TravelDataBean comm = null;
		try{
			conn = getConnection();
			sql  = "select * from travelcommentary where idx=? order by num desc";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, idx);
			rs = pstmt.executeQuery();
			if(rs.next()){
				commList = new ArrayList<TravelDataBean>();
				do{
					comm = new TravelDataBean();
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
	public void insertCommentary(TravelDataBean article) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
			try{
				conn = getConnection();
				
				sql = "insert into travelcommentary(num,nickname,content,wdate,idx) values" +
						"(travelcommentary_num_seq.nextval,?,?,?,?)";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, article.getComm_nickname());
				pstmt.setString(2, article.getComm_content());
				pstmt.setTimestamp(3, article.getComm_wdate());
				pstmt.setInt(4, article.getComm_idx());
				pstmt.executeUpdate();
			}catch(Exception ex){
				ex.printStackTrace();
			}finally{
				if(rs!=null)try{rs.close();}catch(SQLException ex){}
				if(pstmt!=null)try{pstmt.close();}catch(SQLException ex){}
				if(conn!=null)try{conn.close();}catch(SQLException ex){}
			}
		}	
}
