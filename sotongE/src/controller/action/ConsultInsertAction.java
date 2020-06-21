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
				//student_timetable.jsp���� ������ �����۸�ũ�� Ŭ���ؼ� ��������
				if(request.getParameter("subject") != null){
					System.out.println("����" + request.getParameter("subject"));

					ConsultManager manager = ConsultManager.getInstance();
					String[] recipient = manager.findProfSubject(request.getParameter("subject"));
					
					request.setAttribute("recipient_id", recipient[0]); //�������̵� ����
					request.setAttribute("recipient_name", recipient[1]); //�����̸� ����
				}
				//consult_search.jsp���� �����̸� Ŭ���ؼ� ��������
				else{
				
					String recipient_id = request.getParameter("recipient_id");
					String recipient_name = request.getParameter("recipient_name");
				
					request.setAttribute("recipient_id", recipient_id); //�������̵� ���� 
					request.setAttribute("recipient_name", recipient_name); //�����̸� ����
				}
				ActionForward forward = new ActionForward();
				forward.setPath("consult.jsp");
				return forward;		
			}
		
			//���ǿ��� ����� ���̵� ��������
			HttpSession session = request.getSession();
			String send = ((String)session.getAttribute("user_id"));//����ȯ
		
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
			
			//�̵��� ������ ����
			forward.setPath("consult_list.jsp");
			forward.setRedirect(true);
					
			return forward;
		}

}
