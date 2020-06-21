package controller.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.ActionForward;
import model.*;


public class ConsultInsertAction implements Action {
	public ActionForward execute(
			HttpServletRequest request,	HttpServletResponse response)
			throws Exception {
		
			if(request.getMethod().equals("GET")){
				//student_timetable.jsp에서 교과목 하이퍼링크로 클릭해서 들어왔을시
				if(request.getParameter("subject") != null){
					System.out.println("과목" + request.getParameter("subject"));

					ConsultManager manager = ConsultManager.getInstance();
					String[] recipient = manager.findProfSubject(request.getParameter("subject"));
					
					request.setAttribute("recipient_id", recipient[0]); //교수아이디 저장
					request.setAttribute("recipient_name", recipient[1]); //교수이름 저장
				}
				//consult_search.jsp에서 교수이름 클릭해서 들어왔을시
				else{
				
					String recipient_id = request.getParameter("recipient_id");
					String recipient_name = request.getParameter("recipient_name");
				
					request.setAttribute("recipient_id", recipient_id); //교수아이디 저장 
					request.setAttribute("recipient_name", recipient_name); //교수이름 저장
				}
				ActionForward forward = new ActionForward();
				forward.setPath("consult.jsp");
				return forward;		
			}
		
			//세션에서 사용자 아이디 꺼내오기
			HttpSession session = request.getSession();
			String send = ((String)session.getAttribute("user_id"));//형변환
		
			ConsultDTO cons = new ConsultDTO();
			cons.setCategory(request.getParameter("category"));
			cons.setRecipient(request.getParameter("recipient"));
			cons.setCons_date(request.getParameter("cons_date"));
			cons.setTime(request.getParameter("time"));	
			cons.setContent(request.getParameter("content"));
			cons.setSend(send);
			cons.setTitle(request.getParameter("title"));

			ConsultManager manager = ConsultManager.getInstance();
			manager.create(cons);
			
			ActionForward forward = new ActionForward();
			
			//이동할 페이지 결정
			forward.setPath("consult_list.jsp");
			forward.setRedirect(true);
					
			return forward;
		}

}
