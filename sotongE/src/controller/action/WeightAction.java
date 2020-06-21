package controller.action;

import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.ActionForward;
import model.WeightDTO;
import model.ConsultManager;

public class WeightAction implements Action{
	
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
			List<String> profRecommandList = null;
			String category[] = {"교우관계","학교생활","학과","수업","취업(진로)","과제"};
			String select[] = {"sel1","sel2","sel3","sel4","sel5","sel6"};
			HttpSession session = request.getSession();
			String send = ((String)session.getAttribute("user_id"));
			System.out.println(send);
			List<WeightDTO> weightList = new ArrayList<WeightDTO>(); 
			
			//내가 부여한 가중치 별로 DTO에 weigh부여한 List에 넣기
			for(int i=0;i<category.length;i++){
				WeightDTO weight = new WeightDTO();
				weight.setCategory(category[i]);
				weight.setStu_id(send);
				weight.setWeight(Integer.parseInt(request.getParameter(select[i])));
				weightList.add(weight);	
			}
			
			ConsultManager manager = ConsultManager.getInstance();
			manager.giveWeight(weightList);//가중치 디비에 넣기
			profRecommandList = manager.profConsultCompleteList(weightList); // 교수 추천 로직
			
			request.setAttribute("profList", profRecommandList);
			
			ActionForward forward = new ActionForward();
			//이동할 페이지 결정
			forward.setPath("weight.jsp");
			
			return forward;
			
		}
}

