package Create.Board.Review.DAO;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.session.RowBounds;

import Create.Board.Review.VO.ReplyVO;

import Create.Board.Review.VO.BoardVO;



public interface BoardMapper {

	int writeBoard(BoardVO bv);

	//ArrayList<BoardVO> getBoard(RowBounds rb);

	int deleteBoard(int bnum);

	void likeInsert(HashMap<String, Object> map);

	void likeUp(int bnum);

	void likeDown(int bnum);

	void likeDelete(HashMap<String, Object> map);

	int likeCheck(HashMap<String, Object> map);

	BoardVO getRead(int bnum);

	void setReply(ReplyVO result);

	//ArrayList<ReplyVO> getReply(int bnum);

	void updateReply(ReplyVO result);

	void deleteReply(ReplyVO result);

	//int getTotal();

	void getFileDelete(HashMap<String, Integer> map);

	int updateBoard(BoardVO bv);

	int likeall(int bnum);

	ArrayList<BoardVO> getBoard();

	ArrayList<ReplyVO> getReply();

}
