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
public class InsertSecQuestions extends SQLCmd{
    String email;
    int[] questions; 
    String[] answers; 
    boolean success;
	
	public InsertSecQuestions(String email, int sq1, int sq2, int sq3, String sqa1, String sqa2, String sqa3){
		this.email = email;
                questions = new int[]{sq1, sq2, sq3};
                answers = new String[]{sqa1, sqa2, sqa3};
	}
	
	@Override
	public void queryDB(){
            PreparedStatement statement = null;
		try{
                    String command = "INSERT INTO security_questions(user_email, question_id, answer) values(?, ?, ?) ";
                    for (int x=0; x<questions.length; x++){
                        statement = conn.prepareStatement(command);
			statement.setString(1,email);
                        statement.setInt(2, questions[x]);
                        statement.setString(3, answers[x]);
			statement.executeUpdate();
                        statement.close();
                        success = true;
                    }
			
		}
		catch (Exception e){
                    System.out.println(e);
                    success = false;
                    try{
                        if (statement != null){
                            statement.close();
                        }
                    } catch (Exception ex){
                        
                    }
		}
	}
	
	@Override
	public void processResult(){
		result.add(success);
	}
}