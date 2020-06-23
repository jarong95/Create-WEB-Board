package Create.Board.Review.VO;

public class ReplyVO {

	private String replynum;
	private String bnum;
	private String id;
	private String content;
	private String inputdate;
	public ReplyVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ReplyVO(String replynum, String bnum, String id, String content, String inputdate) {
		super();
		this.replynum = replynum;
		this.bnum = bnum;
		this.id = id;
		this.content = content;
		this.inputdate = inputdate;
	}
	@Override
	public String toString() {
		return "ReplyVO [replynum=" + replynum + ", bnum=" + bnum + ", id=" + id + ", content=" + content
				+ ", inputdate=" + inputdate + "]";
	}
	public String getReplynum() {
		return replynum;
	}
	public void setReplynum(String replynum) {
		this.replynum = replynum;
	}
	public String getBnum() {
		return bnum;
	}
	public void setBnum(String bnum) {
		this.bnum = bnum;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	
}
