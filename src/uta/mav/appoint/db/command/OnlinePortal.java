package uta.mav.appoint.db.command;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import uta.mav.appoint.login.AdminUser;
import uta.mav.appoint.login.AdvisorUser;
import uta.mav.appoint.login.FacultyUser;
import uta.mav.appoint.login.LoginUser;
import uta.mav.appoint.login.StudentUser;

public class OnlinePortal extends SQLCmd{
	String email;
	//String aName;
	String desc;
        String sId;
        String sName;
        
	
	public OnlinePortal(String t, String n, String d, String nam){
		email = n;
                desc = d;
                sId = t;
                sName = nam;
	}
	
	@Override
	public void queryDB(){
		try{
			String command = "Insert into onlineportal(sid,email,description,name) values(?,?,?,?)";
			PreparedStatement statement = conn.prepareStatement(command); 
			statement.setInt(1,Integer.parseInt(sId));
			statement.setString(2,email);
			statement.setString(3,desc);
                        statement.setString(4,sName);
                     //   statement.setString(5,sName);
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
