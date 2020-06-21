package controller.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ActionForward;
import model.ConsultManager;

public class ConsultAcceptAciotn implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String consult_id = request.getParameter("consult_id");
		String accept = request.getParameter("accept");

		ConsultManager manager = ConsultManager.getInstance();
		manager.consultAccept(consult_id, accept);

		ActionForward forward = new ActionForward();
		
		forward.setPath("consult_content.m2?command=content&consult_id="+consult_id);
		forward.setRedirect(true);
		return forward;
	}

}
