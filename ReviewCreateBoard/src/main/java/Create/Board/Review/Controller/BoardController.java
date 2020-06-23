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

	@Autowired
	BoardDAO dao;
	
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	/*
	 * 
	//페이지 당 글 수 
	private static final int countPerPage = 10;
		
	//페이지 이동 링크 그룹당 페이지 수
	private static final int pagePerGroup = 10;
	*/
	private final String uploadPath = "/boardfile";
	
/*	@RequestMapping(value="write", method=RequestMethod.GET)
	public String write(){
		return "write";
	}*/
	
	@RequestMapping(value="write", method=RequestMethod.POST)
	public String wrtie(BoardVO bv
			, MultipartFile upload1){
		
		if (upload1 != null && !upload1.isEmpty()) {
			String savedfile = FileService.saveFile(upload1, uploadPath);
			bv.setFilename(upload1.getOriginalFilename());
			bv.setFilesavename(savedfile);
		}
		
		dao.writeBoard(bv);
		return "redirect:board";
	}
	
	/*
	@RequestMapping(value="board", method=RequestMethod.GET)
	public String board(Model model
			, @RequestParam(value="page", defaultValue="1") int page){
		
		int total = dao.getTotal();
		PageNavigator pageNavigator 
		= new PageNavigator(countPerPage, pagePerGroup, page, total);
		ArrayList<BoardVO> list = dao.getBoard(pageNavigator.getStartRecord(), pageNavigator.getCountPerPage());
	
		model.addAttribute("list_board", list);
		return "first";
	}
	*/

	/*
	@RequestMapping(value="read", method=RequestMethod.GET)
	public String read(int bnum, Model model){
		
		BoardVO result = dao.getRead(bnum);
		ArrayList<ReplyVO> resultList = dao.getReply(bnum);
		model.addAttribute("board", result);
		model.addAttribute("reply", resultList);
		return "read";
	}
	 */
	@RequestMapping(value="board", method=RequestMethod.GET)
	public String board(Model model){
		
		ArrayList<BoardVO> list = dao.getBoard();
		ArrayList<ReplyVO> resultList = dao.getReply();
		logger.debug("{}", resultList);
		model.addAttribute("list_board", list);
		model.addAttribute("reply", resultList);
		return "groupBoard";
	}
	
/*	@RequestMapping(value="updateBoard", method=RequestMethod.GET)
	public String board(int bnum, Model model){
		model.addAttribute("board_update", dao.getRead(bnum));
		return "updateboard";
	}*/
	@ResponseBody
	@RequestMapping(value="updateBoard", method=RequestMethod.GET)
	public BoardVO board(int bnum, Model model){
		return dao.getRead(bnum);	
	}
/*	
	@ResponseBody
	@RequestMapping(value="updateBoard", method=RequestMethod.POST, produces="application/json; charset=UTF-8")
	public String board(BoardVO bv
			, MultipartFile upload1
			, String fileDel1
			, String deleteFile){
		int boardnum = Integer.parseInt(bv.getBoardnum());
		String savedfile1 = dao.getRead(boardnum).getFilesavename();
		if(deleteFile != null && !deleteFile.equals("continue")){
			if(deleteFile.equals("delete")){
				FileService.deleteFile(uploadPath + "/" + savedfile1);
				dao.getFileDelete(boardnum, 1);
			}
			
			if (deleteFile.equals("update") && upload1 != null && !upload1.isEmpty()) {
				FileService.deleteFile(uploadPath + "/" + savedfile1);
				String savedfile = FileService.saveFile(upload1, uploadPath);
				bv.setFilename(upload1.getOriginalFilename());
				bv.setFilesavename(savedfile);
			}
		}
		if(deleteFile == null && upload1 != null && !upload1.isEmpty()){
			String savedfile = FileService.saveFile(upload1, uploadPath);
			bv.setFilename(upload1.getOriginalFilename());
			bv.setFilesavename(savedfile);
		}
		int result = dao.updateBoard(bv);
		return "redirect:board";
	}
	*/
	@RequestMapping(value="updateBoard", method=RequestMethod.POST, produces="application/json; charset=UTF-8")
	public String board(BoardVO bv
			, MultipartFile upload1
			, String fileDel1
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
	
	@RequestMapping(value="deleteBoard", method=RequestMethod.GET)
	public String delete(int bnum, Model model){
		model.addAttribute("bnum", bnum);
		return "deleteBoard";
	}

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
	
	@ResponseBody
	@RequestMapping(value="like", method=RequestMethod.GET, produces="application/json; charset=UTF-8")
	public String like(int bnum, HttpSession sess){
		String id = (String)sess.getAttribute("log_id");
		int result = dao.likeCheck(id,bnum);
		if(result != 0){
			dao.likeDelete(bnum,id);
			dao.likeDown(bnum);
		}
		else{
			dao.likeInsert(bnum, id);
			dao.likeUp(bnum);
		}
		return dao.likeall(bnum)+"";
	}
	
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

		//return "redirect:read?bnum="+boardnum;
		return "redirect:board";
	}
	
	
	@RequestMapping(value="reply",method=RequestMethod.POST)
	public String reply(ReplyVO result, HttpSession sess){
		result.setId((String)sess.getAttribute("log_id"));
		logger.debug("{}", result);
		dao.setReply(result);
		//return "redirect:read?bnum="+result.getBnum();
		return "redirect:board";
	}
	
/*	@RequestMapping(value="updateRE", method=RequestMethod.POST)
	public String update(ReplyVO result){
		logger.debug("{}",result);
		dao.updateReply(result);
		//return "redirect:read?bnum="+result.getBnum();
		return "redirect:board";
	}*/
	
	@RequestMapping(value="deleteRE", method=RequestMethod.GET)
	public String delete(ReplyVO result){
		logger.debug("{}",result);
		dao.deleteReply(result);
		//return "redirect:read?bnum="+result.getBnum();
		return "redirect:board";
	}
	
	
}
