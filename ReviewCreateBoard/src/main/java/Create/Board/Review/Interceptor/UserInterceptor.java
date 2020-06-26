package Create.Board.Review.Interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * 사용자 로그인 확인 인터셉터. HandlerInterceptorAdapter를 상속받아서 정의.
 */
public class UserInterceptor extends HandlerInterceptorAdapter {
	private static final Logger logger = LoggerFactory.getLogger(UserInterceptor.class);

	//콘트롤러의 메서드 실행 전에 처리
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		//세션의 로그인 정보 읽기
		HttpSession session = request.getSession();
		String loginId = (String) session.getAttribute("log_id");
		
		//로그인 되어 있는 경우 기본 페이지로 이동하면 자동적으로 /board페이지로 이동한다. 
		if (loginId != null) {
			//request.getContextPath()로 루트 경로를 구하여 절대 경로로 처리
			response.sendRedirect(request.getContextPath() + "/board");
			return false;
		}
		//로그인 되지 않은 경우 요청한 경로로 진행
		return super.preHandle(request, response, handler);
	}

}
