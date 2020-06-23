package Create.Board.Review.DAO;

import Create.Board.Review.VO.MemberVO;

public interface MemberMapper {

	MemberVO checkID(MemberVO member);

	int joinID(MemberVO member);

	MemberVO getInfo(String id);

	int setInfo(MemberVO member);

	int deleteID(String id);

	void deleteCheck();

	int checkID2(String id);

	void recoveryID(String id);

}
