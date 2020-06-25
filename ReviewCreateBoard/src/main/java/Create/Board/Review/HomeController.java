package Create.Board.Review;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
/*
 * 컨트롤러 어노테이션_ 컨트롤러 인터페이스를 구현하지 않고도 컨트롤러를 사용
       클레스타입에 적용되며, 해당 클레스를 웹 요청을 처리하는 컨트롤러로 사용한다.
 */
@Controller
public class HomeController {
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	/*
	 * 요청 URL 매핑을 @RequestMapping을 이용하여 설정
	 * 클래스나 메서드에 사용되며, 메서드에 이용할 경우 각각의 메서드가 처리할 요청 URL을 명시한다.
	 * value에 있는 값이 URL값 / method = RequestMethod GET / POST에 따라 받는 방식이 달라진다. 
	 * return 값에 redirect를 이용하여 URL값을 적으면 해당 URL경로로 이동
	 * return 값에 string값으로 jsp값을 적으면 해당 URL은 String.jsp페이지가 되어 화면에 출력한다.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home() {
		return "home";
	}
	
}
