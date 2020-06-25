package Create.Board.Review.Controller;


import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import Create.Board.Review.Util.FileService;
import Create.Board.Review.Util.PageNavigator;
import Create.Board.Review.Util.SecurityUtil;
import Create.Board.Review.DAO.BoardDAO;
import Create.Board.Review.VO.BoardVO;
import Create.Board.Review.VO.MemberVO;
import Create.Board.Review.VO.ReplyVO;

@Controller
public class BoardController {

	/*
	 * 각 상황의 타입에 맞는 IoC컨테이너 안에 존재하는 Bean을 자동으로 주입해준다. 
	 * @Repository Dao라는 것을 알리기 위해 사용하하는데 이를 사용하기 위해 Autowitred를 사용한다. 
	 */
	@Autowired
	BoardDAO dao; //BoardDAO를 사용하기 위해 
	
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	//로거를 사용하기 위해 현재 상황을 파악하기 위한 목적으로 사용한다. 
	//System.out.println();을 사용이 불가하기 때문
	
	
	private final String uploadPath = "/boardfile";
	//파일을저장하기위한 경로 설정
	
	
	//글쓰기 post로 받으음
	@RequestMapping(value="write", method=RequestMethod.POST)
	public String wrtie(BoardVO bv
			, MultipartFile upload1){
		//BoardVO 객체로 받고 파일은 multipartfile로 받음
		
		if (upload1 != null && !upload1.isEmpty()) {
			//파일이 있는 경우 fileservice를 이용하여 저장
			String savedfile = FileService.saveFile(upload1, uploadPath);
			//저장한 파일이름과 파일 원본 이름을 저장
			bv.setFilename(upload1.getOriginalFilename());
			bv.setFilesavename(savedfile);
		}
		
		//dao로 이동시켜서 DB에 저장
		dao.writeBoard(bv);
		
		//board 주소로 redirect
		return "redirect:board";
	}
	
	@RequestMapping(value="board", method=RequestMethod.GET)
	public String board(Model model){
		
		//게시글과 답글을 모두 가져온다.
		ArrayList<BoardVO> list = dao.getBoard();
		ArrayList<ReplyVO> resultList = dao.getReply();
		logger.debug("{}", resultList);
		
		//model객체에 저장하여 화면에 출력한다.
		model.addAttribute("list_board", list);
		model.addAttribute("reply", resultList);
		
		//groupBoard.jsp페이지를 board주소에 출력한다.
		return "groupBoard";
	}
	
	//ajax를 활용하기 위한 어노테이션
	@ResponseBody
	@RequestMapping(value="updateBoard", method=RequestMethod.GET)
	public BoardVO board(int bnum, Model model){
		return dao.getRead(bnum);	
	}
	
	//게시글을 수정
	@RequestMapping(value="updateBoard", method=RequestMethod.POST, produces="application/json; charset=UTF-8")
	public String board(BoardVO bv
			, MultipartFile upload1
			, String fileContinue){
		logger.debug(fileContinue +"bv: {}, mul:{}", bv, upload1);
		int boardnum = Integer.parseInt(bv.getBoardnum());
		String savedfile1 = dao.getRead(boardnum).getFilesavename();
		if(!fileContinue.equals("yes")){
			FileService.deleteFile(uploadPath + "/" + savedfile1);
			dao.getFileDelete(boardnum, 1);
			if (upload1 != null && !upload1.isEmpty()) {
				String savedfile = FileService.saveFile(upload1, uploadPath);
				bv.setFilename(upload1.getOriginalFilename());
				bv.setFilesavename(savedfile);
			}
		}
		dao.updateBoard(bv);
		return "redirect:board";
	}
	
	//게시글 삭제
	@RequestMapping(value="deleteBoard", method=RequestMethod.GET)
	public String delete(int bnum, Model model){
		model.addAttribute("bnum", bnum);
		return "deleteBoard";
	}
	
	//게시글 삭제, 파일도 삭제
	@RequestMapping(value="deleteBoard", method=RequestMethod.POST)
	public String delete(String password, int bnum, HttpSession sess){
		MemberVO delete = new MemberVO();
		BoardVO check = dao.getRead(bnum);
		int result = 0;
		delete.setPassword(password);
		delete = SecurityUtil.encryptSHA256(delete);
		if(sess.getAttribute("log_pw").equals(delete.getPassword())){
			result = dao.deleteBoard(bnum);
		}
		
		if(result != 0){
			if (check.getFilesavename() != null) {
				FileService.deleteFile(uploadPath + "/" + check.getFilesavename());
			}
		}
		
		return "redirect:board";
	}
	
	//좋아요 설정
	@ResponseBody
	@RequestMapping(value="like", method=RequestMethod.GET, produces="application/json; charset=UTF-8")
	public String like(int bnum, HttpSession sess){
		String id = (String)sess.getAttribute("log_id");
		int result = dao.likeCheck(id,bnum);
		
		//아이디를 검색해서 해당 아이디가 해당 글을 이미 좋아요를 눌렀던 것이 있으면 좋아요를 삭제하고 -1을 그런 적이 없었으면 추가하고 +1을한다음
		if(result != 0){
			dao.likeDelete(bnum,id);
			dao.likeDown(bnum);
		}
		else{
			dao.likeInsert(bnum, id);
			dao.likeUp(bnum);
		}
		
		//like 값을 가져온다음 String으로 보냄
		return dao.likeall(bnum)+"";
	}
	
	//파일을 다운로드
	@RequestMapping(value = "download", method = RequestMethod.GET)
	public String fileDownload(int boardnum, Model model, HttpServletResponse response, int number) {
		BoardVO board = dao.getRead(boardnum);		//글 1개에 대한 정보
		String originalfile = "";
		originalfile = board.getFilename();
		
		try {
			response.setHeader("Content-Disposition", " attachment;filename="+ URLEncoder.encode(originalfile, "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		String fullPath = "";
		
		fullPath = uploadPath + "/" + board.getFilesavename();
		//저장된 파일 경로
		
		//서버의 파일을 읽을 입력 스트림과 클라이언트에게 전달할 출력스트림
		FileInputStream filein = null;
		ServletOutputStream fileout = null;
		
		try {
			filein = new FileInputStream(fullPath);
			fileout = response.getOutputStream();
			
			//Spring의 파일 관련 유틸
			FileCopyUtils.copy(filein, fileout);
			
			filein.close();
			fileout.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return "redirect:board";
	}
	
	
	//댓글 달기
	@RequestMapping(value="reply",method=RequestMethod.POST)
	public String reply(ReplyVO result, HttpSession sess){
		result.setId((String)sess.getAttribute("log_id"));
		logger.debug("{}", result);
		dao.setReply(result);
		return "redirect:board";
	}
	
	//댓글 삭제
	@RequestMapping(value="deleteRE", method=RequestMethod.GET)
	public String delete(ReplyVO result){
		logger.debug("{}",result);
		dao.deleteReply(result);
		return "redirect:board";
	}
	
	
}
