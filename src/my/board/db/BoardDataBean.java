package my.board.db;

import java.sql.*;

public class BoardDataBean {
	private int idx, read_count, recommand, non_recommand, recommand_count, area, area_idx,ref,depth;
	private int step;
	private String title, content, category, nickname,id;
	private Timestamp wdate;
	//댓글
	private int comm_num, comm_depth, comm_step, comm_idx,comm_ref;
	private Timestamp comm_wdate;
	private String comm_content,comm_nickname;
	//자료
	private int fileid, filestep;
	private String filename;
	private long filesize;
	
	public long getFilesize() {
		return filesize;
	}
	public void setFilesize(long filesize) {
		this.filesize = filesize;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public int getFileid() {
		return fileid;
	}
	public void setFileid(int fileid) {
		this.fileid = fileid;
	}
	public int getFilestep() {
		return filestep;
	}
	public void setFilestep(int filestep) {
		this.filestep = filestep;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
	}
	public int getRead_count() {
		return read_count;
	}
	public void setRead_count(int read_count) {
		this.read_count = read_count;
	}
	public int getRecommand() {
		return recommand;
	}
	public void setRecommand(int recommand) {
		this.recommand = recommand;
	}
	public int getNon_recommand() {
		return non_recommand;
	}
	public void setNon_recommand(int non_recommand) {
		this.non_recommand = non_recommand;
	}
	public int getRecommand_count() {
		return recommand_count;
	}
	public void setRecommand_count(int recommand_count) {
		this.recommand_count = recommand_count;
	}
	public int getArea() {
		return area;
	}
	public void setArea(int area) {
		this.area = area;
	}
	public int getArea_idx() {
		return area_idx;
	}
	public void setArea_idx(int area_idx) {
		this.area_idx = area_idx;
	}
	public int getRef() {
		return ref;
	}
	public void setRef(int ref) {
		this.ref = ref;
	}
	public int getDepth() {
		return depth;
	}
	public void setDepth(int depth) {
		this.depth = depth;
	}
	public int getStep() {
		return step;
	}
	public void setStep(int step) {
		this.step = step;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public Timestamp getWdate() {
		return wdate;
	}
	public void setWdate(Timestamp wdate) {
		this.wdate = wdate;
	}
	public int getComm_num() {
		return comm_num;
	}
	public void setComm_num(int comm_num) {
		this.comm_num = comm_num;
	}
	public int getComm_depth() {
		return comm_depth;
	}
	public void setComm_depth(int comm_depth) {
		this.comm_depth = comm_depth;
	}
	public int getComm_step() {
		return comm_step;
	}
	public void setComm_step(int comm_step) {
		this.comm_step = comm_step;
	}
	public Timestamp getComm_wdate() {
		return comm_wdate;
	}
	public void setComm_wdate(Timestamp comm_wdate) {
		this.comm_wdate = comm_wdate;
	}
	public String getComm_content() {
		return comm_content;
	}
	public void setComm_content(String comm_content) {
		this.comm_content = comm_content;
	}
	public String getComm_nickname() {
		return comm_nickname;
	}
	public void setComm_nickname(String comm_nickname) {
		this.comm_nickname = comm_nickname;
	}
	public int getComm_idx() {
		return comm_idx;
	}
	public void setComm_idx(int comm_idx) {
		this.comm_idx = comm_idx;
	}
	public int getComm_ref() {
		return comm_ref;
	}
	public void setComm_ref(int comm_ref) {
		this.comm_ref = comm_ref;
	}
	
}
