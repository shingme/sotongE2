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
		
		String user_val = request.getParameter("user_val"); //����ڰ� �������� �л����� ����
		
		String user_id = request.getParameter("user_id") + " "; //DB�� char(10)���� �����س��� ������ �ڿ� ���� ��ĭ ����!
		String user_pwd = request.getParameter("user_pwd");
		System.out.println(user_id);
		ActionForward forward = new ActionForward();

		try {
			//�𵨿� �α��� ó���� ����.
			ConsultManager manager = ConsultManager.getInstance();
			manager.login(user_val, user_id, user_pwd);
			//���ǿ� ����� ���̵� ����. �л��̸� �л���, �������̸� �����Բ�
			
			HttpSession session = request.getSession();
			session.setAttribute("user_id", user_id);
			
			
			//�̵��� �������� ����.
			forward.setPath("base.jsp");
			forward.setRedirect(true);
			
		} catch (Exception e) {
			/* ExistedUserException�̳� PasswordMismatchException�� �߻� ��
			 * �ٽ� login form (login.jsp)�� ����ڿ��� �����ϰ� ���� �޼����� ���
			 */
			request.setAttribute("exception", e);
			forward.setPath("login.jsp");
			forward.setRedirect(false);					
		}		
		
		return forward;
	}
}
