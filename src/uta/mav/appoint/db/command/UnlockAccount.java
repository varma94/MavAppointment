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
public class UnlockAccount extends SQLCmd{
    String email;
    boolean success;
	
	public UnlockAccount(String email){
		this.email = email;
	}
	
	@Override
	public void queryDB(){
		try{
                    String command = "UPDATE USER set locked =0 WHERE email=?";
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