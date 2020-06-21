package controller.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.ActionForward;
import model.*;

public class TimetableAction implements Action {
	
	public ActionForward execute(
			HttpServletRequest request,	HttpServletResponse response)
			throws Exception {

			ActionForward forward = new ActionForward();
			
			HttpSession session = request.getSession();
			String user_id = (String)session.getAttribute("user_id");
			
			String recipient_id = request.getParameter("recipient_id");
			String recipient_name = request.getParameter("recipient_name");
			
			ConsultManager manager = ConsultManager.getInstance();
			List<WeekDTO> timetable = manager.findTimetable(user_id,recipient_id);
			
			request.setAttribute("recipient_id", recipient_id); 
			request.setAttribute("recipient_name", recipient_name); 
		
			if(timetable == null){
				request.setAttribute("forward", "y");
			}
			session.setAttribute("timetable", timetable);		
			
			if(recipient_id != null){
				forward.setPath("include_time.jsp");
			}
			else{
				if((user_id.substring(0, 1)).equals("s")){
					forward.setPath("student_timetable.jsp");
				}
			}
			
			
					
			return forward;
		}
}
