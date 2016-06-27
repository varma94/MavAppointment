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
public class UpdateStudentProfile extends SQLCmd {
    String email;
    String carrier;
    String phone;
    String oldEmail;
    String course;
    
    public UpdateStudentProfile(String email, String carrier, String phone, String course, String oldEmail){
        this.email = email;
        this.carrier = carrier;
        this.phone = phone;
        this.oldEmail = oldEmail;
        this.course = course;
    }

    @Override
    public void queryDB() {
        try{
            String command = "UPDATE user " +
                    "SET email=?, carrier=?, phone=?, course=? " +
                    "WHERE email=?;";
            PreparedStatement statement = conn.prepareStatement(command);
			statement.setString(1, email);
			statement.setString(2, carrier);
                        statement.setString(3, phone);
                        statement.setString(4, course);
                        statement.setString(5, oldEmail);
			statement.executeUpdate();
        } catch (Exception e){
            
        }
    }

    @Override
    public void processResult() {
        
    }
}
