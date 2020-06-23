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
	
	@RequestMapping(value="login", method=RequestMethod.POST)
	public String loginId(MemberVO member
			, HttpSession sess){
		MemberVO mv = dao.checkID(member);
		if(mv == null){
			return "redirect:/";
		}
		sess.setAttribute("log_id", member.getId());
		sess.setAttribute("log_pw", mv.getPassword());
		if(mv.getDeletedate() != null){
			sess.setAttribute("delete_id", true);
		}
		return "redirect:board";
	}
	
	@RequestMapping(value="logout", method=RequestMethod.GET)
	public String logout(HttpSession sess){
		sess.invalidate();
		return "redirect:/";
	}
	
	@RequestMapping(value="signup", method=RequestMethod.GET)
	public String joinId(HttpSession sess){
		return "join";
	}
	@RequestMapping(value="signup", method=RequestMethod.POST)
	public String joinId(MemberVO member
			, HttpSession sess){
		int result = dao.joinID(member);
		if(result == 0){
			return "redirect:/signup";
		}
		return "redirect:/";
	}
	
	@RequestMapping(value="update", method=RequestMethod.GET)
	public String updateID(HttpSession sess, Model model){
		String id = (String)sess.getAttribute("log_id");
		MemberVO result = dao.getInfo(id);
		model.addAttribute("user", result);
		return "update";
	}
	@RequestMapping(value="update", method=RequestMethod.POST)
	public String updateID(HttpSession sess, MemberVO member){
		String id = (String)sess.getAttribute("log_id");
		member.setId(id);
		int result = dao.setInfo(member);
		return "redirect:/";
	}
	
	@RequestMapping(value="delete", method=RequestMethod.GET)
	public String deleteID(){
		return "delete";
	}
	
	@RequestMapping(value="delete", method=RequestMethod.POST)
	public String deleteID(MemberVO member, HttpSession sess){
		String id = (String)sess.getAttribute("log_id");
		int result = dao.deleteID(id);
		sess.setAttribute("delete_id", true);
		return "redirect:/board";
	}
	
	@ResponseBody
	@RequestMapping(value="recovery", method=RequestMethod.POST, produces="application/json; charset=UTF-8")
	public void recoveryID(HttpSession sess){
		String id = (String)sess.getAttribute("log_id");
		dao.recoveryID(id);
		sess.setAttribute("delete_id",null);
	}
	
	@ResponseBody
	@RequestMapping(value="idcheck", method=RequestMethod.GET,
			produces="application/json; charset=UTF-8")
	public String checkID(String id){
		int result = dao.cehckID(id);
		return ""+result;
	}
}
