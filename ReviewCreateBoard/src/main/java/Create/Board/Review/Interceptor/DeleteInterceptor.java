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
	// SQL을 좀 더 공부해서 매번  삭제가 돌아가도록 sql문 작성 할 것!!
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		//홈페이지에 접속하면 탈퇴 후 7일이 지난 사람을 삭제한다.
		dao.deleteCheck();
		return super.preHandle(request, response, handler);
	}

}
