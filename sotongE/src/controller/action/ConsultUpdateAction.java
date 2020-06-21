package controller.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.ActionForward;
import model.*;


public class ConsultUpdateAction implements Action {
	public ActionForward execute(
			HttpServletRequest request,	HttpServletResponse response)
			throws Exception {

			String consult_id = request.getParameter("consult_id");

			HttpSession session = request.getSession();
			String user_id = ((String)session.getAttribute("user_id"));
			
			ActionForward forward = new ActionForward();
			ConsultManager manager = ConsultManager.getInstance();
			
			if(request.getParameter("update") == null){
				ConsultDTO consultContent = manager.findContent(consult_id, user_id);
				request.setAttribute("content", consultContent);
				
				forward.setPath("consult_update.jsp");
			}
			else if(request.getParameter("update").equals("y")){	
				ConsultDTO cons = new ConsultDTO();
				
				cons.setTitle(request.getParameter("title"));
				cons.setContent(request.getParameter("content"));
				cons.setConsult_id(consult_id);
				
				manager.updateContent(cons);
				forward.setPath("consult_content.m2?command=content&consult_id="+consult_id);
			}
					
			return forward;
		}

}
