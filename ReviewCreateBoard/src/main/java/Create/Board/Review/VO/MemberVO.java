package Create.Board.Review.VO;

public class MemberVO {

	private String id;
	private String password;
	private String email;
	private String name;
	private String deletedate;
	
	public MemberVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public MemberVO(String id, String password, String email, String name) {
		super();
		this.id = id;
		this.password = password;
		this.email = email;
		this.name = name;
	}
	
	public String getDeletedate() {
		return deletedate;
	}
	public void setDeletedate(String deletedate) {
		this.deletedate = deletedate;
	}
	@Override
	public String toString() {
		return "MemberVO [id=" + id + ", password=" + password + ", email=" + email + ", name=" + name + "]";
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String emai) {
		this.email = emai;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}
