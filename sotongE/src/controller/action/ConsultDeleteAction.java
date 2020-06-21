package controller.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.ActionForward;
import model.*;


public class ConsultDeleteAction implements Action {
	public ActionForward execute(
			HttpServletRequest request,	HttpServletResponse response)
			throws Exception {
			
			String consult_id = request.getParameter("consult_id");
		
			ConsultManager manager = ConsultManager.getInstance();
			manager.delete(consult_id);
			
			ActionForward forward = new ActionForward();
			
			//이동할 페이지 결정
			forward.setPath("consult_list.jsp");
			forward.setRedirect(true);
					
			return forward;
		}

}
