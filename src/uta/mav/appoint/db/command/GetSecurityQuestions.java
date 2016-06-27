/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uta.mav.appoint.db.command;

import java.sql.PreparedStatement;
import java.util.AbstractMap.SimpleEntry;

/**
 *
 * @author oguni
 */
public class GetSecurityQuestions extends SQLCmd{
    String email;
    
    public GetSecurityQuestions(String email){
        this.email = email;
    }

    @Override
    public void queryDB() {
        try{
            String command = "SELECT * FROM security_questions WHERE user_email=?";
            PreparedStatement statement = conn.prepareStatement(command);
            statement.setString(1, email);
            res = statement.executeQuery();
        } catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void processResult() {
        try{
            while (res.next()){
                int question = res.getInt("question_id");
                String answer = res.getString("answer");
                SimpleEntry<Integer,String> entry = new SimpleEntry(question, answer);
                result.add(entry);
            }
        }catch (Exception e){
            
        }
    }
}
