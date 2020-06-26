package Create.Board.Review.Controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import Create.Board.Review.DAO.MemberDAO;
import Create.Board.Review.VO.MemberVO;



@Controller
public class LogController {

	
	@Autowired
	MemberDAO dao;
	
	//로그인
	@RequestMapping(value="login", method=RequestMethod.POST)
	public String loginId(MemberVO member
			, HttpSession sess){
		//아이디를 체크
		MemberVO mv = dao.checkID(member);
		if(mv == null){
			return "redirect:/";
		}
		//아이디가 없으면 첫화면으로 이동
		//있으면 로그인 정보를 세션에 저장 
		sess.setAttribute("log_id", member.getId());
		sess.setAttribute("log_pw", mv.getPassword());
		
		//아이디가 삭제 중인지 확인하고 세션에 저장
		if(mv.getDeletedate() != null){
			sess.setAttribute("delete_id", true);
		}
		return "redirect:board";
	}
	
	//로그아웃
	@RequestMapping(value="logout", method=RequestMethod.GET)
	public String logout(HttpSession sess){
		
		//세션을 비워낸다.
		sess.invalidate();
		return "redirect:/";
	}
	
	//회원가입
	@RequestMapping(value="signup", method=RequestMethod.GET)
	public String joinId(HttpSession sess){
		//회원가입 페이지로 이동
		return "join";
	}
	
	//회원가입 정보 DB에 저장
	@RequestMapping(value="signup", method=RequestMethod.POST)
	public String joinId(MemberVO member
			, HttpSession sess){
		int result = dao.joinID(member);
		//회원정보를 확인하고 저장한다.
		if(result == 0){
			//저장이 안된경우 다시 회원가입 페이지
			return "redirect:/signup";
		}
		return "redirect:/";
	}
	
	//회원정보 수정
	@RequestMapping(value="update", method=RequestMethod.GET)
	public String updateID(HttpSession sess, Model model){
		String id = (String)sess.getAttribute("log_id");
		//세션에 있는 아이디를 가져와서 해당 아이디의 개인정보를 가져온다.
		MemberVO result = dao.getInfo(id);
		//가져온 정보를  model에 저장하여 보여준다.
		model.addAttribute("user", result);
		return "update";
	}
	
	//회원정보 수정 정보 DB에 저장
	@RequestMapping(value="update", method=RequestMethod.POST)
	public String updateID(HttpSession sess, MemberVO member){
		String id = (String)sess.getAttribute("log_id");
		member.setId(id);
		//수정한 정보를 DB에 저장
		dao.setInfo(member);
		//redirect를 하더라도 인터셉터에 의해 로그인이 되어있으면 board페이지로 이동한다.
		return "redirect:/";
	}
	
	//회원 삭제
	@RequestMapping(value="delete", method=RequestMethod.GET)
	public String deleteID(){
		//삭제 페이지로 이동
		return "delete";
	}
	
	//회원 정보 삭제를 DB에 저장
	@RequestMapping(value="delete", method=RequestMethod.POST)
	public String deleteID(MemberVO member, HttpSession sess){
		String id = (String)sess.getAttribute("log_id");
		//회원정보를 확인하고 삭체 리스트에 추가
		dao.deleteID(id);
		//세션에 탈퇴할 아이디라는 것을 인지
		sess.setAttribute("delete_id", true);
		return "redirect:/board";
	}
	
	//회원탈퇴 취소
	@ResponseBody
	@RequestMapping(value="recovery", method=RequestMethod.POST, produces="application/json; charset=UTF-8")
	public void recoveryID(HttpSession sess){
		String id = (String)sess.getAttribute("log_id");
		//회원정보 탈퇴를 취소화.
		dao.recoveryID(id);
		//취소했다는 것을 세션에 저장하여 페이지 변환
		sess.setAttribute("delete_id",null);
	}
	
	//회원 아이디 중복 체크
	@ResponseBody
	@RequestMapping(value="idcheck", method=RequestMethod.GET,
			produces="application/json; charset=UTF-8")
	public String checkID(String id){
		//회원 아이디가 이미 중복된 것인지 확인하고 가입이 안되도록 함
		int result = dao.cehckID(id);
		return ""+result;
	}
}
