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
public class ResetLoginAttempts extends SQLCmd{
    String email;
	
	public ResetLoginAttempts(String email){
		this.email = email;
	}
	
	@Override
	public void queryDB(){
		try{
                    String command = "UPDATE USER set false_tries =0 WHERE email=?";
			PreparedStatement statement = conn.prepareStatement(command);
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