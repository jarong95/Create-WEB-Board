package Create.Board.Review.VO;

public class LikeVO {

	private String id;
	private String like;
	public LikeVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public LikeVO(String id, String like) {
		super();
		this.id = id;
		this.like = like;
	}
	@Override
	public String toString() {
		return "LikeVO [id=" + id + ", like=" + like + "]";
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getLike() {
		return like;
	}
	public void setLike(String like) {
		this.like = like;
	}
	
}
