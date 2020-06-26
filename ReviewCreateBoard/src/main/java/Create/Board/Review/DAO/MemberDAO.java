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
	
	//아이디 로그인
	public MemberVO checkID(MemberVO member) {
		MemberVO result = null;
		MemberMapper mapper = sqlsession.getMapper(MemberMapper.class);
		//비밀번호 암호화
		//복호화를 불가능하게 해야함
		member = SecurityUtil.encryptSHA256(member);
		//로그인 아이디 정보 가져오기
		result = mapper.checkID(member);
		return result;
	}

	//회원가입
	public int joinID(MemberVO member) {
		int result = 0;
		MemberMapper mapper = sqlsession.getMapper(MemberMapper.class);
		//비밀번호 암호화
		member = SecurityUtil.encryptSHA256(member);
		result = mapper.joinID(member);
		return result;
	}

	//회원 정보 가져오기
	public MemberVO getInfo(String id) {
		MemberVO result = null;
		MemberMapper mapper = sqlsession.getMapper(MemberMapper.class);
		result = mapper.getInfo(id);
		return result;
	}

	//회원정보 수정
	public int setInfo(MemberVO member) {
		int result = 0;
		MemberMapper mapper = sqlsession.getMapper(MemberMapper.class);
		//비밀번호 암호화
		member = SecurityUtil.encryptSHA256(member);
		result = mapper.setInfo(member);
		return result;
	}

	//회원정보 삭제
	public int deleteID(String id) {
		int result = 0;
		MemberMapper mapper = sqlsession.getMapper(MemberMapper.class);
		result = mapper.deleteID(id);
		return result;
	}

	//회원이 탈퇴 상태인지 확인
	public void deleteCheck() {
		MemberMapper mapper = sqlsession.getMapper(MemberMapper.class);
		mapper.deleteCheck();
	}

	//중복 확인
	public int cehckID(String id) {
		MemberMapper mapper = sqlsession.getMapper(MemberMapper.class);
		return mapper.checkID2(id);
	}

	//아이디 복구
	public void recoveryID(String id) {
		MemberMapper mapper = sqlsession.getMapper(MemberMapper.class);
		mapper.recoveryID(id);
	}

}
