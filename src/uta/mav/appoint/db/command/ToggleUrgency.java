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
public class ToggleUrgency extends SQLCmd {
    private int id;
    
    public ToggleUrgency(int id){
        this.id = id;
    }

    @Override
    public void queryDB() {
        try{
            String command = "SELECT * FROM appointments where ID=?";
            PreparedStatement statement = conn.prepareStatement(command);
            statement.setInt(1, id);
            res = statement.executeQuery();
            res.next();
            int current = res.getInt("urgency");
            if (current == 0){
                command = "UPDATE APPOINTMENTS set urgency=1 where ID=?";
            }else {
                command = "UPDATE APPOINTMENTS set urgency=0 where ID=?";
            }
            statement = conn.prepareStatement(command);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void processResult() {
        
    }
}
