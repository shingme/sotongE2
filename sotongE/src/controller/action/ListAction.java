package controller.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.*;
import model.*;

public class ListAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
	
		
		int currentPageStr = Integer.parseInt(request.getParameter("currentPage")); //1 
		
		String type = request.getParameter("type");
		System.out.println(type);
		//���ǿ��� ����� ���̵� ��������(send�� �����ϱ� ����)
		HttpSession session = request.getSession();
		String send = ((String)session.getAttribute("user_id"));//����ȯ
		
		System.out.println("send" + send);
		System.out.println("currentPage : "+ currentPageStr);
		
		ConsultManager manager = ConsultManager.getInstance();
		
		request.setAttribute("consultPager", manager.makePager(currentPageStr, send, type));
		
		List<ConsultDTO> consultList = manager.findConsultList(currentPageStr, send, type);
		
		request.setAttribute("consultList", consultList);
		
		
		ActionForward forward = new ActionForward();
		
		forward.setPath("consult_list.jsp");
		return forward;
	}

}
