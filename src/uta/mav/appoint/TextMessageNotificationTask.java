/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uta.mav.appoint;

import java.util.TimerTask;
import uta.mav.appoint.db.DatabaseManager;
import java.util.ArrayList;
import uta.mav.appoint.beans.Appointment;
import uta.mav.appoint.login.StudentUser;
import uta.mav.appoint.email.Email;

/**
 *
 * @author oguni
 */
public class TextMessageNotificationTask extends TimerTask{
    
    @Override
    public void run() {
        try{
            DatabaseManager db = new DatabaseManager();
            ArrayList<Object> appts = db.getUpcomingAppointments(60, 5);
            for (Object obj: appts){
                Appointment appt = (Appointment)obj;
                StudentUser student = db.getStudent(appt.getStudentEmail());
                if (student.getPhone()!=null && student.getCarrier()!=null){
                    Email newEmail = new Email("Appointment Reminder",
                        "You have an advising appointment at the advising office in one hour ", 
                            student.getPhone() + "@" + student.getCarrier());
                    newEmail.sendMail();
                }
            }
        } catch (Exception e){
            String message = e.getMessage();
            System.out.println(e.getMessage());
        }
    }
}
