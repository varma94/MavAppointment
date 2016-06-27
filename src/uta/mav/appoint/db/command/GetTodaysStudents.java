/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uta.mav.appoint.db.command;

import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 *
 * @author oguni
 */
public class GetTodaysStudents extends SQLCmd{
    int id;
    
    public GetTodaysStudents(int id){
        this.id = id;
    }

    @Override
    public void queryDB() {
        Calendar currentTime = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat tf = new SimpleDateFormat("HH:mm:ss");
        try{
            String command = "SELECT DISTINCT student_email FROM appointments "
                    + "WHERE advisor_userid =? "
                    + "and advising_starttime >=? "
                    + "and advising_date =?";
            PreparedStatement statement = conn.prepareStatement(command);
            statement.setInt(1,id);
            statement.setString(2, tf.format(currentTime.getTime()));
            statement.setString(3, df.format(currentTime.getTime()));
            res = statement.executeQuery();
        }
        catch(Exception e){
            System.out.println(e);
	}
    }

    @Override
    public void processResult() {
        String email;
        try{
            while (res.next()){
                email = res.getString("student_email");
                result.add(email);
            }
        }
        catch(Exception e){
                System.out.println(e);
        }
    }
}
