/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uta.mav.appoint;

import java.text.SimpleDateFormat;
import java.util.TimerTask;
import uta.mav.appoint.db.DatabaseManager;
import java.util.ArrayList;
import java.util.Calendar;
import uta.mav.appoint.beans.Appointment;
import uta.mav.appoint.beans.AppointmentType;
import uta.mav.appoint.beans.WaitList;
import uta.mav.appoint.email.Email;

/**
 *
 * @author oguni
 */
public class CheckWaitListTask extends TimerTask{

    @Override
    public void run() {
        try{
            DatabaseManager dbm = new DatabaseManager();
            String[] array = dbm.getMailIds();
            ArrayList<Object> waitlist = dbm.getWaitList();
            if (!waitlist.isEmpty()){
                ArrayList<TimeSlotComponent> slots = dbm.getAdvisorSchedule("all");
                if (!slots.isEmpty()){
                    while(slots.size() > 0 && waitlist.size() >0){
                        TimeSlotComponent slot = slots.get(0);
                        WaitList listItem = (WaitList)waitlist.get(0);
                        Appointment app = new Appointment();
                        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-ddHH:mm:ss");
                        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                        SimpleDateFormat tf = new SimpleDateFormat("HH:mm:ss");
                        
                        AppointmentType type = dbm.getAppointmentType(listItem.getAppointmentType());
                        int duration = type.getDuration();
                        Calendar time = Calendar.getInstance();
                        time.setTime(format.parse(slot.getDate()+slot.getStartTime()));
                        time.add(Calendar.MINUTE, duration);
                        
                        app.setAdvisingDate(slot.getDate());
                        app.setAdvisingStartTime(slot.getStartTime());
                        app.setAdvisingEndTime(tf.format(time.getTime()));
                        app.setStudentid(listItem.getStudentid());
                        app.setDescription(listItem.getDescription());
                        app.setPname(slot.getName());
                        app.setAppointmentType(listItem.getAppointmentType());
                    
                        app.setAppointmentId(slot.getUniqueId());
                        dbm.createAppointment(app, listItem.getStudentEmail());
                        dbm.removeFromWaitList(listItem.getStudentEmail());
                        slots.remove(0);
                        waitlist.remove(0);
                        
                        sendEmail(app, listItem.getStudentEmail());
                    }
                }
            }
        } catch (Exception e){
            System.out.println("Error");
        }
    }
    
    private void sendEmail(Appointment app, String email){
        
        Email newEmail = new Email("Your Appointment","", email);
        try{
                        String date = app.getAdvisingDate();
                        String startTime = app.getAdvisingStartTime();
                        String endTime = app.getAdvisingEndTime();
                        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-ddHH:mm:ss");
                        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                        SimpleDateFormat tf = new SimpleDateFormat("HH:mm:ss");
                        Calendar start = Calendar.getInstance();
                        start.setTime(format.parse(date+startTime));
                        start.add(Calendar.HOUR_OF_DAY, 5);
                        
                        Calendar end = Calendar.getInstance();
                        end.setTime(format.parse(date+endTime));
                        end.add(Calendar.HOUR_OF_DAY, 5);
                        newEmail.sendEventSync("You have an appointment", "You have an advising appointment with " + app.getPname() + ".", start, end);
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        
    }
    
}
