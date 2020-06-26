package Create.Board.Review.DAO;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import Create.Board.Review.VO.BoardVO;
import Create.Board.Review.VO.ReplyVO;



//DAO임을 알리기 위해, Controller는 아니지만 autoWired를 사용하기 위해 
@Repository
public class BoardDAO {

	@Autowired
	SqlSession sqlSession;
	//DB연결
	
	//글작성
	public int writeBoard(BoardVO bv) {
		int result = 0;
		//Mapper의 정보를 가져와 DB에 작성할 문장을 가져와서 저장 
		BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);
		//MApper의 글자 읽기 위해
		result = mapper.writeBoard(bv);
		//결과값은 0(실패) or 1(작성) 
		return result;
	}

	//게시글 읽어오기
	public BoardVO getRead(int bnum) {
		BoardVO result = null;
		BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);
		result = mapper.getRead(bnum);
		
		//해당 Boardnum에 해당하는 글을 BoardVO 객체로 보냄
		return result;
	}

	//게시글 삭제
	public int deleteBoard(int bnum) {
		int result = 0;
		BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);
		result = mapper.deleteBoard(bnum);
		return result;
	}

	//좋아요 아이디 추가
	public void likeInsert(int bnum, String id) {
		BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);
		HashMap<String, Object> map = new HashMap<>();
		map.put("id", id);
		map.put("bnum", bnum);
		//해쉬맵에 저장하여 해당 아이디와 게시글을 저장
		mapper.likeInsert(map);
	}

	//+1
	public void likeUp(int bnum) {
		BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);
		mapper.likeUp(bnum);
	}

	//-1
	public void likeDown(int bnum) {
		BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);
		mapper.likeDown(bnum);
	}

	//좋아요 아이디 삭제
	public void likeDelete(int bnum, String id) {
		BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);
		HashMap<String, Object> map = new HashMap<>();
		map.put("id", id);
		map.put("bnum", bnum);
		mapper.likeDelete(map);
	}

	//좋아요를 이미 했는지 확인
	public int likeCheck(String id, int bnum) {
		BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);
		HashMap<String, Object> map = new HashMap<>();
		map.put("id", id);
		map.put("bnum", bnum);
		int result = mapper.likeCheck(map);
		//있으면 1, 없으면 0
		return result;
	}

	//댓글 달기
	public void setReply(ReplyVO result) {
		BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);
		mapper.setReply(result);
	}

	//댓글 삭제
	public void deleteReply(ReplyVO result) {
		BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);
		mapper.deleteReply(result);
	}

	//파일 삭제
	public void getFileDelete(int boardnum, int i) {
		BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);
		HashMap<String, Integer> map = new HashMap<>();
		map.put("bnum", boardnum);
		map.put("filenum", i);
		mapper.getFileDelete(map);
	}

	//게시글 수정
	public int updateBoard(BoardVO bv) {
		int result = 0;
		BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);
		result = mapper.updateBoard(bv);
		return result;
	}

	//해당 게시글에 대한 좋아요 수를 가져옴
	public int likeall(int bnum) {
		int result = 0;
		BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);
		result = mapper.likeall(bnum);
		return result;
	}

	//게시글 전체를 가져옴
	public ArrayList<BoardVO> getBoard() {
		ArrayList<BoardVO> result = null;
		BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);
		
		result = mapper.getBoard();
		//가져온 전체 글을 Arraylist에 저장
		return result;
	}

	//댓글 전체 리스트를 가져온다.
	public ArrayList<ReplyVO> getReply() {
		ArrayList<ReplyVO> result = null;
		BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);
		result = mapper.getReply();
		return result;
	}
}
