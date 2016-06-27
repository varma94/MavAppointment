/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uta.mav.appoint.db.command;

import java.sql.PreparedStatement;

/**
 *
 * @author oguni
 */
public class RemoveFromWaitList extends SQLCmd{
    String email;
    boolean success;
	
	public RemoveFromWaitList(String email){
		this.email = email;
	}
	
	@Override
	public void queryDB(){
		try{
                    String command = "DELETE FROM waitlist WHERE student_email=?";
			PreparedStatement statement = conn.prepareStatement(command);
			statement.setString(1,email);
			statement.executeUpdate();
                        success = true;
		}
		catch (Exception e){
			System.out.println(e);	
                        success = false;
		}
	}
	
	@Override
	public void processResult(){
		result.add(success);
	}
}
