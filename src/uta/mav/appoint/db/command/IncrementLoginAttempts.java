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
public class IncrementLoginAttempts extends SQLCmd{
    String email;
    int falseTries;
	
	public IncrementLoginAttempts(String email){
		this.email = email;
	}
	
	@Override
	public void queryDB(){
            falseTries =0;
		try{
                    String command = "UPDATE USER set false_tries = false_tries+1 WHERE email=?";
                    PreparedStatement statement = conn.prepareStatement(command);
                    statement.setString(1,email);
                    statement.executeUpdate();
                        
                    command = "SELECT false_tries from USER where email=?";
                    statement = conn.prepareStatement(command);
                    statement.setString(1,email);
                    res = statement.executeQuery();
                    
		}
		catch (Exception e){
                    System.out.println(e);	
		}
	}
	
	@Override
	public void processResult(){
            try{
		while (res.next()){
                    result.add(res.getInt("false_tries"));
		}
            } catch(Exception e){
		System.out.println(e);
            }
            result.add(falseTries);
	}
}