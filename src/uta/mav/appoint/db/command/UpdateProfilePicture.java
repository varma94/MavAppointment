/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uta.mav.appoint.db.command;

import java.sql.PreparedStatement;
import java.io.InputStream;

/**
 *
 * @author oguni
 */
public class UpdateProfilePicture extends SQLCmd{
    String email;
    InputStream image;
    
    public UpdateProfilePicture(String email, InputStream image) {
		this.email = email;
		this.image = image;
	}

    @Override
    public void queryDB() {
        try {
                        String command = "Update user set profile_pic = ? where email = ?";
			PreparedStatement statement = conn.prepareStatement(command);
			statement.setString(2, email);
			statement.setBinaryStream(1, image);
			statement.executeUpdate();
		} catch (Exception e) {
			System.out.println(e);
		}
    }

    @Override
    public void processResult() {
        
    }
    
}
