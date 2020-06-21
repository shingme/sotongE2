package controller.action;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.*;
import model.*;

public class ConsultContentAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
	
		String consult_id = request.getParameter("consult_id");

		HttpSession session = request.getSession();
		String user_id = ((String)session.getAttribute("user_id"));
		
		ConsultManager manager = ConsultManager.getInstance();
		ConsultDTO consultContent = manager.findContent(consult_id, user_id);
		
		ResultDTO resultContent = manager.findResult(consult_id, user_id);
		request.setAttribute("content", consultContent);
		
		//상담신청 날짜가 지나면 after <- y
		Date today = new Date();
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date cons_date = dateFormat.parse(consultContent.getCons_date());
		
		if(today.after(cons_date)){
			request.setAttribute("after", "y");
		}else{
			request.setAttribute("after", "n");
		}
		//상담결과가 작성되지 않았으면 reg <- yet
		if(resultContent == null){
			request.setAttribute("reg", "yet");
		}else{
			request.setAttribute("reg", "");
			request.setAttribute("result", resultContent);
		}
		
		ActionForward forward = new ActionForward();
		
		forward.setPath("consult_content.jsp");
		return forward;
	}

}
