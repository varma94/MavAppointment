package uta.mav.appoint.db.command;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import uta.mav.appoint.login.AdminUser;
import uta.mav.appoint.login.AdvisorUser;
import uta.mav.appoint.login.FacultyUser;
import uta.mav.appoint.login.LoginUser;
import uta.mav.appoint.login.StudentUser;

public class WaitUser extends SQLCmd{
	String aType;
	String aName;
	String desc;
        String sId;
        String sName;
        
	
	public WaitUser(String t, String n, String d, String s, String nam){
		aType = t;
		aName = n;
                desc = d;
                sId = s;
                sName = nam;
	}
	
	@Override
	public void queryDB(){
		try{
			String command = "Insert into Waitlist(advisor_name,advising_type,description,student_id,student_email) values(?,?,?,?,?)";
			PreparedStatement statement = conn.prepareStatement(command); 
			statement.setString(1,aName);
			statement.setString(2,aType);
			statement.setString(3,desc);
                        statement.setString(4,sId);
                        statement.setString(5,sName);
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
