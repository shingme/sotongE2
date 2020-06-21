package controller.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.*;
import model.*;

public class ProfSearchAction implements Action {
	/**
	 * UserManager�� list�޽�带 ȣ���Ͽ� 
	 * List�� response�� �����ϴ� �ҽ��ڵ尡 ����. 
	 * list.jsp���� response�� ����� List��ü�� �̿��Ѵ�.
	 */
	
	public ActionForward execute(
			HttpServletRequest request,	HttpServletResponse response)
			throws Exception {
		
			if(request.getMethod().equals("GET")){
				ActionForward forward = new ActionForward();
				forward.setPath("consult_search.jsp");
				forward.setRedirect(true);
				return forward;		
			}	
		
			HttpSession session = request.getSession();
			String user_id = (String)session.getAttribute("user_id");
			String search_value = request.getParameter("search_value");
			String search = request.getParameter("search");
			String recipient = request.getParameter("recipient");
			session.setAttribute("recipient", recipient);
			
			ActionForward forward = new ActionForward();
			
			ConsultManager manager = ConsultManager.getInstance();
			List<ProfDTO> prof = null;
			List<StudentDTO> stu = null;
			request.setAttribute("forward", "n");
			
			if(user_id.startsWith("s")){
				if(search_value.equals("prof_name")){
					prof = manager.findProfessor(search);			
				}else if(search_value.equals("dept_name")){
					prof = manager.findProfDept(search);				
				}else if(search_value.equals("my")){
					prof = manager.findProfTime(user_id);
				}else{
					request.setAttribute("forward", "pr");
				}
				
				request.setAttribute("prof", prof);
			}else{
				if(search_value.equals("stu_name")){
					stu = manager.findStudentName(search);			
				}else if(search_value.equals("dept_name")){
					stu = manager.findStudentDept(search);		
				}else if(search_value.equals("my")){
					stu = manager.findStudentTime(user_id);
				}else{
					request.setAttribute("forward", "st");
				}
				
				request.setAttribute("stu", stu);
			}
			
			forward.setPath("consult_search.jsp");
					
			return forward;
		}
}
