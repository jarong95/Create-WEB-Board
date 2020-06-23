package Create.Board.Review.DAO;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import Create.Board.Review.Util.SecurityUtil;
import Create.Board.Review.VO.MemberVO;



@Repository
public class MemberDAO {
	
	@Autowired
	SqlSession sqlsession;
	
	public MemberVO checkID(MemberVO member) {
		MemberVO result = null;
		MemberMapper mapper = sqlsession.getMapper(MemberMapper.class);
		member = SecurityUtil.encryptSHA256(member);
		result = mapper.checkID(member);
		return result;
	}

	public int joinID(MemberVO member) {
		int result = 0;
		MemberMapper mapper = sqlsession.getMapper(MemberMapper.class);
		member = SecurityUtil.encryptSHA256(member);
		result = mapper.joinID(member);
		return result;
	}

	public MemberVO getInfo(String id) {
		MemberVO result = null;
		MemberMapper mapper = sqlsession.getMapper(MemberMapper.class);
		result = mapper.getInfo(id);
		return result;
	}

	public int setInfo(MemberVO member) {
		int result = 0;
		MemberMapper mapper = sqlsession.getMapper(MemberMapper.class);
		member = SecurityUtil.encryptSHA256(member);
		result = mapper.setInfo(member);
		return result;
	}

	public int deleteID(String id) {
		int result = 0;
		MemberMapper mapper = sqlsession.getMapper(MemberMapper.class);
		result = mapper.deleteID(id);
		return result;
	}

	public void deleteCheck() {
		MemberMapper mapper = sqlsession.getMapper(MemberMapper.class);
		mapper.deleteCheck();
	}

	public int cehckID(String id) {
		MemberMapper mapper = sqlsession.getMapper(MemberMapper.class);
		return mapper.checkID2(id);
	}

	public void recoveryID(String id) {
		MemberMapper mapper = sqlsession.getMapper(MemberMapper.class);
		mapper.recoveryID(id);
	}

}
