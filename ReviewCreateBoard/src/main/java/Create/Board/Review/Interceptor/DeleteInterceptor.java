package Create.Board.Review.Interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import Create.Board.Review.DAO.MemberDAO;



public class DeleteInterceptor extends HandlerInterceptorAdapter {
	
	@Autowired
	MemberDAO dao;
	
	//콘트롤러의 메서드 실행 전에 처리
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		dao.deleteCheck();
		return super.preHandle(request, response, handler);
	}

}
