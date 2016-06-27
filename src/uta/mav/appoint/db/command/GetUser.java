/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uta.mav.appoint.db.command;

import java.sql.PreparedStatement;
import uta.mav.appoint.login.LoginUser;

/**
 *
 * @author oguni
 */
public class GetUser extends SQLCmd{
    String email;
    
    public GetUser(String email){
        this.email = email;
    }

    @Override
    public void queryDB() {
        try{
		String command = "SELECT * FROM user WHERE email =?";
		PreparedStatement statement = conn.prepareStatement(command);
		statement.setString(1,email);
		res = statement.executeQuery();
		}
		catch(Exception e){
			System.out.println(e);
		}
    }

    @Override
    public void processResult() {
        LoginUser user = new LoginUser();
        try{
            while (res.next()){
                user.setUserId(res.getInt("userid"));
                user.setEmail(res.getString("email"));
                result.add(user);
            }
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
}
