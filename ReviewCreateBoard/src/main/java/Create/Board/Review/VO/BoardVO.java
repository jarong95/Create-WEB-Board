package Create.Board.Review.VO;

public class BoardVO {

	private String boardnum;
	private String title;
	private String content;
	private String inputdate;
	
	private String filename;
	private String filesavename;
	private String id;
	private String likenum;
	@Override
	public String toString() {
		return "BoardVO [boardnum=" + boardnum + ", title=" + title + ", content=" + content + ", inputdate="
				+ inputdate + ", filename=" + filename + ", filesavename=" + filesavename + ", id=" + id + ", likenum="
				+ likenum + "]";
	}
	public BoardVO(String boardnum, String title, String content, String inputdate, String filename,
			String filesavename, String id, String likenum) {
		super();
		this.boardnum = boardnum;
		this.title = title;
		this.content = content;
		this.inputdate = inputdate;
		this.filename = filename;
		this.filesavename = filesavename;
		this.id = id;
		this.likenum = likenum;
	}
	public BoardVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getBoardnum() {
		return boardnum;
	}
	public void setBoardnum(String boardnum) {
		this.boardnum = boardnum;
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
	public String getInputdate() {
		return inputdate;
	}
	public void setInputdate(String inputdate) {
		this.inputdate = inputdate;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public String getFilesavename() {
		return filesavename;
	}
	public void setFilesavename(String filesavename) {
		this.filesavename = filesavename;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getLikenum() {
		return likenum;
	}
	public void setLikenum(String likenum) {
		this.likenum = likenum;
	}
	
	
}