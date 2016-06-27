package uta.mav.appoint.db.command;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import uta.mav.appoint.login.AdminUser;
import uta.mav.appoint.login.AdvisorUser;
import uta.mav.appoint.login.FacultyUser;
import uta.mav.appoint.login.LoginUser;
import uta.mav.appoint.login.StudentUser;

public class CheckUserPassword extends SQLCmd{
	String email;
	String password;
	String pname;
	
	public CheckUserPassword(String e, String p){
		email = e;
		password = p;
	}
	
	@Override
	public void queryDB(){
		try{
			String command = "Update user set password = ? where email = ?";
			PreparedStatement statement = conn.prepareStatement(command); 
			statement.setString(2,email);
			statement.setString(1,password);
		   statement.executeUpdate();
			processResult();}
		catch (Exception e){
			System.out.println(e);	
		}
		
	}
	
	@Override
	public void processResult(){
		LoginUser user = null;
		try{
			while(res.next()){
				if (!(res.getInt(1) == 0)){
					if (res.getString(2).toLowerCase().equals("advisor")){
						user = new AdvisorUser(email,pname);
					}
					else if (res.getString(2).toLowerCase().equals("student")){
						user = new StudentUser(email);
					}
					else if (res.getString(2).toLowerCase().equals("admin")){
						user = new AdminUser(email);
					} 
					else {
						user = new FacultyUser(email);
					}
				}	
			}
			result.add(user);
		}
		catch(Exception e){
			System.out.println(e);
		}
	}	
}
