/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uta.mav.appoint.db.command;
import java.sql.PreparedStatement;
import java.util.Calendar;
import java.util.Date;
import java.text.SimpleDateFormat;

import uta.mav.appoint.beans.Appointment;
/**
 *
 * @author oguni
 */
public class GetUpcomingAppointments extends SQLCmd {
    int timelimit;
    int startIn;
    
    public GetUpcomingAppointments(int startIn, int timelimit){
        this.timelimit = timelimit;
        this.startIn = startIn;
    }
    
    @Override
    public void queryDB(){
        Calendar currentTime = Calendar.getInstance();
        currentTime.add(Calendar.MINUTE, startIn);
        Calendar end =Calendar.getInstance();
        end.add(Calendar.MINUTE, timelimit + startIn);
        Date date = currentTime.getTime();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat tf = new SimpleDateFormat("HH:mm:ss");
        String startDay = df.format(currentTime.getTime());
        String endDay = df.format(end.getTime());
        String startTime = tf.format(currentTime.getTime());
        String endTime = tf.format(end.getTime());
        
		try{
		String command = "SELECT * FROM appointments "
                        + "WHERE advising_starttime <? "
                        + "AND advising_starttime >=?"
                        + "AND (advising_date =? OR advising_date =?)";
		PreparedStatement statement = conn.prepareStatement(command);
		statement.setString(1,endTime);
		statement.setString(2,startTime);
                statement.setString(3,startDay);
                statement.setString(4,endDay);
		res = statement.executeQuery();
		}
		catch(Exception e){
			System.out.println(e);
		}
	}
    
    public void processResult(){
		try{
			while (res.next()){
                            Appointment app = new Appointment();
				app.setAdvisingDate(res.getString("advising_date"));
				app.setAdvisingStartTime(res.getString("advising_starttime"));
				app.setAdvisingEndTime(res.getString("advising_endtime"));
				app.setAppointmentType(res.getString("appointment_type"));
                                app.setStudentEmail(res.getString("student_email"));
                                app.setStudentid(res.getString("studentid"));
				result.add(app);
			}
		}
		catch(Exception e){
			System.out.println(e);
		}
	}
}
