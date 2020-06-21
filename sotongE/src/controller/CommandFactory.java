package controller;


import controller.action.*;

public class CommandFactory {
	private CommandFactory() {}

	public static CommandFactory getInstance() {
		return new CommandFactory();
	}

	public Action getAction(String command)  throws IllegalCommandException {
		Action action = null;
		
		if(command.equals("insert")){
			action = new ConsultInsertAction();
		}
		else if(command.equals("update")){
			action = new ConsultUpdateAction();
		}
		else if(command.equals("delete")){
			action = new ConsultDeleteAction();
		}
		else if(command.equals("login")){
			action = new LoginAction();
		}
		else if(command.equals("list")){
			action = new ListAction();
		}
		else if(command.equals("search")){
			action = new ProfSearchAction();
		} 
		else if(command.equals("content")){
			action = new ConsultContentAction();
		}
		else if(command.equals("result")){
			action = new ResultInsertAction();
		}
		else if(command.equals("resultDelete")){
			action = new ResultDeleteAction();
		}
		else if(command.equals("timetable")){
			action = new TimetableAction();
		}
		else if(command.equals("profSearch")){
			action = new ProfSchSearchAction();
		}
		else if(command.equals("insert_sch")){
			action = new SchInsertAction();
		}
		else if(command.equals("weight")){
			action = new WeightAction();
		}
		else if(command.equals("accept")){
			action = new ConsultAcceptAciotn();
		}
		else if (command.equals("logout")) {
			action = new LogoutAction();
		}
		else{
			throw new IllegalCommandException("오류발생");
		}


		return action;
	}
	

}
