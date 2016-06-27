/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uta.mav.appoint.db.command;

import java.sql.PreparedStatement;
import uta.mav.appoint.beans.AppointmentType;

/**
 *
 * @author oguni
 */
public class GetAppointmentType extends SQLCmd{
	String name;
	
	public GetAppointmentType(String name){
		this.name = name;
	}
	
	public void queryDB(){
		try{
		String command = "SELECT * FROM appointment_types WHERE TYPE =?";
		PreparedStatement statement = conn.prepareStatement(command);
		statement.setString(1,name);
		res = statement.executeQuery();
		}
		catch(Exception e){
			System.out.println(e);
		}
	}
	
	
	public void processResult(){
		AppointmentType app = new AppointmentType();
		try{
			while (res.next()){
				app.setDuration(res.getInt("duration"));
                                app.setType(res.getString("type"));
				result.add(app);
			}
		}
		catch(Exception e){
			System.out.println(e);
		}
	}
}
