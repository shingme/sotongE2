package controller.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.ActionForward;
import model.*;


public class ResultDeleteAction implements Action {
	public ActionForward execute(
			HttpServletRequest request,	HttpServletResponse response)
			throws Exception {

			ResultDTO cons = new ResultDTO();
			
			//���ǿ��� ����� ���̵� ��������
			String consult_id = request.getParameter("consult_id");

			ConsultManager manager = ConsultManager.getInstance();
			manager.deleteResult(consult_id);
			
			ActionForward forward = new ActionForward();
			
			//�̵��� ������ ����
			forward.setPath("consult_content.m2?command=content&consult_id="+consult_id);
			
			return forward;
		}

}
