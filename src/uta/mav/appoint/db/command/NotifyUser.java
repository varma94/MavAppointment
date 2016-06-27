package uta.mav.appoint.db.command;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import uta.mav.appoint.login.AdminUser;
import uta.mav.appoint.login.AdvisorUser;
import uta.mav.appoint.login.FacultyUser;
import uta.mav.appoint.login.LoginUser;
import uta.mav.appoint.login.StudentUser;

public class NotifyUser extends SQLCmd{
	String email;
	String notify;
	
	public NotifyUser(String e, String p){
		email = e;
		notify = p;
	}
	
	@Override
	public void queryDB(){
		try{
			String command = "Update notifyusers set notify=? where email = ?";
			PreparedStatement statement = conn.prepareStatement(command); 
			statement.setString(2,notify);
			statement.setString(1,email);
			statement.executeUpdate();
			
			}
		catch (Exception e){
			System.out.println(e);	
		}
		
	}
	
	@Override
	public void processResult(){
		
		
	}	
}
