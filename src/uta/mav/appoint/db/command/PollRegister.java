package uta.mav.appoint.db.command;

import java.sql.PreparedStatement;

public class PollRegister extends SQLCmd{
	String sid;
	//String aName;
	String advising;
        String teaching;
        String subject,online;
        
	
	public PollRegister(String t, String n, String d, String nam, String l){
		sid = t;
                advising = n;
                teaching = d;
                subject = nam;
                online = l;
               
	}
	
	@Override
	public void queryDB(){
		try{
			String command = "Insert into poll(sid,advising,teaching,subject,online) values(?,?,?,?,?)";
			PreparedStatement statement = conn.prepareStatement(command); 
			statement.setInt(1,Integer.parseInt(sid));
			statement.setString(2,advising);
			statement.setString(3,teaching);
                        statement.setString(4,subject);
                        statement.setString(5,online);
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
