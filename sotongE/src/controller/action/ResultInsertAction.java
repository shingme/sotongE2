package controller.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.ActionForward;
import model.*;


public class ResultInsertAction implements Action {
	public ActionForward execute(
			HttpServletRequest request,	HttpServletResponse response)
			throws Exception {

			ResultDTO cons = new ResultDTO();
			
			//���ǿ��� ����� ���̵� ��������
			HttpSession session = request.getSession();
			String consult_id = request.getParameter("consult_id");
			
			cons.setConsult_id(consult_id);
			cons.setUser_id((String)session.getAttribute("user_id"));
			cons.setResult_cons(request.getParameter("result_cons"));

			ConsultManager manager = ConsultManager.getInstance();
			manager.createResult(cons);
			
			ActionForward forward = new ActionForward();
			
			//�̵��� ������ ����
			forward.setPath("consult_content.m2?command=content&consult_id="+consult_id);
			
			return forward;
		}

}
