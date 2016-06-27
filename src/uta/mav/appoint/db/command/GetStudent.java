/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uta.mav.appoint.db.command;

import java.sql.Blob;
import java.sql.PreparedStatement;
import uta.mav.appoint.login.StudentUser;

/**
 *
 * @author oguni
 */
public class GetStudent extends SQLCmd{
    String email;
    
    public GetStudent(String email){
        this.email= email;
    }
    
    @Override
    public void queryDB(){
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
    public void processResult(){
		StudentUser student = new StudentUser();
		try{
			while (res.next()){
                            student.setCarrier(res.getString("carrier"));
                            student.setPhone(res.getString("phone"));
                            student.setStudentId(res.getString("userid"));
                            student.setEmail(res.getString("email"));
                            student.setPicture(res.getBlob("profile_pic"));
                            student.setCourse(res.getString("course"));
				result.add(student);
			}
		}
		catch(Exception e){
			System.out.println(e);
		}
	}
    
}
