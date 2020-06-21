package controller.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.ActionForward;
import model.ConsultManager;

public class LoginAction implements Action {
	public ActionForward execute(
		HttpServletRequest request,	HttpServletResponse response)
		throws Exception {
		
		String user_val = request.getParameter("user_val"); //사용자가 교수인지 학생인지 구별
		
		String user_id = request.getParameter("user_id") + " "; //DB에 char(10)으로 설정해놨기 때문에 뒤에 공백 한칸 삽입!
		String user_pwd = request.getParameter("user_pwd");
		System.out.println(user_id);
		ActionForward forward = new ActionForward();

		try {
			//모델에 로그인 처리를 위임.
			ConsultManager manager = ConsultManager.getInstance();
			manager.login(user_val, user_id, user_pwd);
			//세션에 사용자 이이디 저장. 학생이면 학생꺼, 교수님이면 교수님꺼
			
			HttpSession session = request.getSession();
			session.setAttribute("user_id", user_id);
			
			
			//이동할 페이지를 결정.
			forward.setPath("base.jsp");
			forward.setRedirect(true);
			
		} catch (Exception e) {
			/* ExistedUserException이나 PasswordMismatchException이 발생 시
			 * 다시 login form (login.jsp)을 사용자에게 전송하고 오류 메세지도 출력
			 */
			request.setAttribute("exception", e);
			forward.setPath("login.jsp");
			forward.setRedirect(false);					
		}		
		
		return forward;
	}
}
