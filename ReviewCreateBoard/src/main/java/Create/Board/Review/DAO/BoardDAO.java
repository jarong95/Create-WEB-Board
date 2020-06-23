package Create.Board.Review.DAO;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import Create.Board.Review.VO.BoardVO;
import Create.Board.Review.VO.ReplyVO;



@Repository
public class BoardDAO {

	@Autowired
	SqlSession sqlSession;

	public int writeBoard(BoardVO bv) {
		int result = 0;
		BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);
		result = mapper.writeBoard(bv);
		return result;
	}

/*	public ArrayList<BoardVO> getBoard(int i, int j) {
		ArrayList<BoardVO> result = null;
		BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);
		
		RowBounds rb = new RowBounds(i, j);
		
		result = mapper.getBoard(rb);
		return result;
	}*/

	public BoardVO getRead(int bnum) {
		BoardVO result = null;
		BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);
		result = mapper.getRead(bnum);
		return result;
	}

/*	public ArrayList<ReplyVO> getReply(int bnum) {
		ArrayList<ReplyVO> result = null;
		BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);
		result = mapper.getReply(bnum);
		return result;
	}*/

	public int deleteBoard(int bnum) {
		int result = 0;
		BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);
		result = mapper.deleteBoard(bnum);
		return result;
	}

	public void likeInsert(int bnum, String id) {
		BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);
		HashMap<String, Object> map = new HashMap<>();
		map.put("id", id);
		map.put("bnum", bnum);
		mapper.likeInsert(map);
	}

	public void likeUp(int bnum) {
		BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);
		mapper.likeUp(bnum);
	}

	public void likeDown(int bnum) {
		BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);
		mapper.likeDown(bnum);
	}

	public void likeDelete(int bnum, String id) {
		BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);
		HashMap<String, Object> map = new HashMap<>();
		map.put("id", id);
		map.put("bnum", bnum);
		mapper.likeDelete(map);
	}

	public int likeCheck(String id, int bnum) {
		BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);
		HashMap<String, Object> map = new HashMap<>();
		map.put("id", id);
		map.put("bnum", bnum);
		int result = mapper.likeCheck(map);
		return result;
	}

	public void setReply(ReplyVO result) {
		BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);
		mapper.setReply(result);
	}

/*	public void updateReply(ReplyVO result) {
		BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);
		mapper.updateReply(result);
	}*/

	public void deleteReply(ReplyVO result) {
		BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);
		mapper.deleteReply(result);
	}

	/*public int getTotal() {
		int result = 0;
		BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);
		result = mapper.getTotal();
		return result;
	}*/

	public void getFileDelete(int boardnum, int i) {
		BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);
		HashMap<String, Integer> map = new HashMap<>();
		map.put("bnum", boardnum);
		map.put("filenum", i);
		mapper.getFileDelete(map);
	}

	public int updateBoard(BoardVO bv) {
		int result = 0;
		BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);
		result = mapper.updateBoard(bv);
		return result;
	}

	public int likeall(int bnum) {
		int result = 0;
		BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);
		result = mapper.likeall(bnum);
		return result;
	}

	public ArrayList<BoardVO> getBoard() {
		ArrayList<BoardVO> result = null;
		BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);
		
		result = mapper.getBoard();
		return result;
	}

	public ArrayList<ReplyVO> getReply() {
		ArrayList<ReplyVO> result = null;
		BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);
		result = mapper.getReply();
		return result;
	}
}
