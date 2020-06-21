package controller.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.ActionForward;
import model.*;


public class SchInsertAction implements Action {
   public ActionForward execute(
         HttpServletRequest request,   HttpServletResponse response)
         throws Exception {
      
         //세션에서 사용자 아이디 꺼내오기
         HttpSession session = request.getSession();
      
         ProfSchDTO sch = new ProfSchDTO();
         String prof_id = (String)session.getAttribute("user_id");
         String recipient_id = null;
         
         System.out.println(request.getParameter("title"));

         sch.setProf_id(prof_id);
         sch.setTime(request.getParameter("time"));
         sch.setTitle(request.getParameter("title"));
         sch.setCons_date(request.getParameter("cons_date"));

         ConsultManager manager = ConsultManager.getInstance();

         ActionForward forward = new ActionForward(); 
         int action = manager.createProfSch(sch);
         
         List<ProfSchDTO> profSch = manager.findProfSch(recipient_id, prof_id);
         request.setAttribute("profSchList", profSch);
         
         if(request.getMethod().equals("GET")){
             request.setAttribute("overlap", "y");
             forward.setPath("prof_schedule.jsp");
             forward.setRedirect(false);
          }
         
         
         if(action == 0){
             request.setAttribute("overlap", "y");
             forward.setPath("prof_schedule.jsp");
             forward.setRedirect(false);
          }else{
             //이동할 페이지 결정
             forward.setPath("prof_schedule.jsp");
          }
       
         return forward;
      }

}