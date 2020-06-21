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
			String category[] = {"�������","�б���Ȱ","�а�","����","���(����)","����"};
			String select[] = {"sel1","sel2","sel3","sel4","sel5","sel6"};
			HttpSession session = request.getSession();
			String send = ((String)session.getAttribute("user_id"));
			System.out.println(send);
			List<WeightDTO> weightList = new ArrayList<WeightDTO>(); 
			
			//���� �ο��� ����ġ ���� DTO�� weigh�ο��� List�� �ֱ�
			for(int i=0;i<category.length;i++){
				WeightDTO weight = new WeightDTO();
				weight.setCategory(category[i]);
				weight.setStu_id(send);
				weight.setWeight(Integer.parseInt(request.getParameter(select[i])));
				weightList.add(weight);	
			}
			
			ConsultManager manager = ConsultManager.getInstance();
			manager.giveWeight(weightList);//����ġ ��� �ֱ�
			profRecommandList = manager.profConsultCompleteList(weightList); // ���� ��õ ����
			
			request.setAttribute("profList", profRecommandList);
			
			ActionForward forward = new ActionForward();
			//�̵��� ������ ����
			forward.setPath("weight.jsp");
			
			return forward;
			
		}
}

