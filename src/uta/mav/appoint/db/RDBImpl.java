package uta.mav.appoint.db;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.AbstractMap.SimpleEntry;

import uta.mav.appoint.PrimitiveTimeSlot;
import uta.mav.appoint.TimeSlotComponent;
import uta.mav.appoint.beans.AllocateTime;
import uta.mav.appoint.beans.Appointment;
import uta.mav.appoint.beans.AppointmentType;
import uta.mav.appoint.beans.CreateAdvisorBean;
import uta.mav.appoint.beans.GetSet;
import uta.mav.appoint.beans.OnlinePortalView;
import uta.mav.appoint.beans.PollServletB;
import uta.mav.appoint.beans.WaitList;
import uta.mav.appoint.db.command.*;
import uta.mav.appoint.email.Email;
import uta.mav.appoint.flyweight.TimeSlotFlyweightFactory;
import uta.mav.appoint.helpers.TimeSlotHelpers;
import uta.mav.appoint.login.AdminUser;
import uta.mav.appoint.login.AdvisorUser;
import uta.mav.appoint.login.LoginUser;
import uta.mav.appoint.login.StudentUser;
import uta.mav.appoint.login.UpdatePasswordUser;

public class RDBImpl implements DBImplInterface {

        @Override
	public Connection connectDB() {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			String jdbcUrl = "jdbc:mysql://localhost:3306/mav_appointment";
			String userid = "root";
			String password = "root";
			Connection conn = DriverManager.getConnection(jdbcUrl, userid,
					password);
			return conn;
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return null;
	}

	// user login checking, check username and password against database
	// then return role if a match is found
	// using command pattern
        @Override
	public LoginUser checkUser(GetSet set) throws SQLException {
		LoginUser user = null;
		try {
			SQLCmd cmd = new CheckUser(set.getEmailAddress(), set.getPassword());
			cmd.execute();
			user = (LoginUser) (cmd.getResult()).get(0);

		} catch (Exception e) {
			System.out.println(e);
		}
		return user;
	}
        
        @Override
        public String updateNotify(GetSet set) throws SQLException {
		
		try {
			SQLCmd cmd = new NotifyUser(set.getEmailAddress(), set.getNotify());
			cmd.execute();
                        return "1";
			

		} catch (Exception e) {
			System.out.println(e);
                        return "null";
		}
		
	}
        
        @Override
        public String waitUser(GetSet set) throws SQLException {
		
		try {
			SQLCmd cmd = new WaitUser(set.getAdvisingType(), set.getAdvisorName(),set.getDescription(),set.getStudentId(),set.getEmailAddress());
			cmd.execute();
			return "1";

		} catch (Exception e) {
                        
			System.out.println(e);
                        return "null";
		}
		
	}
        
        @Override
          public String onlinePortal(GetSet set) throws SQLException {
		
		try {
			SQLCmd cmd = new OnlinePortal(set.getStudentId(), set.getEmailAddress(),set.getDescription(),set.getName());
			cmd.execute();
			return "1";

		} catch (Exception e) {
                        
			System.out.println(e);
                        return "null";
		}
		
	}
          
          @Override
           public String pollServ(PollServletB set) throws SQLException {
		
		try {
			SQLCmd cmd = new PollRegister(set.getSid(), set.getAdvising(),set.getTeaching(),set.getSubject(),set.getOnline());
			cmd.execute();
			return "1";

		} catch (Exception e) {
                        
			System.out.println(e);
                        return "null";
		}
		
	}
	
           @Override
	public UpdatePasswordUser checkUserPassword(GetSet set) throws SQLException{
		UpdatePasswordUser user = null;
		try{
			SQLCmd cmd = new CheckUserPassword(set.getEmailAddress(), set.getPassword());
			cmd.execute();
			user = (UpdatePasswordUser)(cmd.getResult()).get(0);
			
		}
		catch(Exception e){
			System.out.println(e);
		}
		return user;
	}
	
        @Override
	public Boolean updateAppointment(Appointment a) {
		Boolean result = false;
		try {
			SQLCmd cmd = new UpdateAppointment(a);
			cmd.execute();
			result = (Boolean) (cmd.getResult()).get(0);
		} catch (Exception e) {

		}
		return result;
	}

        @Override
	public Boolean createStudent(StudentUser studentUser){
		try{
			SQLCmd cmd = new CreateUser(studentUser);
			cmd.execute();
			System.out.println("Created User"+cmd.getResult());
			
			SQLCmd cmd1 = new CreateStudent(studentUser);
			cmd1.execute();
			System.out.println(cmd1.getResult());
			return (Boolean)cmd1.getResult().get(0);
		}
		catch(Exception e){
			System.out.println(e+" -- Found In -- "+this.getClass().getName());
			return false;
		}
	}
		
	
        
        @Override
        public Boolean updateStudent(StudentUser studentUser){
		try{
			SQLCmd cmd = new UpdateUser(studentUser);
			cmd.execute();
			System.out.println("Updated User"+cmd.getResult());
			
			
			return (Boolean)cmd.getResult().get(0);
		}
		catch(Exception e){
			System.out.println(e+" -- Found In -- "+this.getClass().getName());
			return false;
		}
	}
	
        @Override
	public int addUser(GetSet set) {
		/*
		 * int check = 0; Connection conn = DatabaseManager.ConnectDB(); String
		 * command =
		 * "INSERT INTO USER (email,password,role) VALUES(email=?,password=?,role=?)"
		 * ; return check;
		 */
		return 0;
	}

	// using command pattern
        @Override
	public ArrayList<String> getAdvisors() throws SQLException {
		ArrayList<String> arraylist = new ArrayList<>();
		try {
			SQLCmd cmd = new GetAdvisors();
			cmd.execute();
			ArrayList<Object> tmp = cmd.getResult();
			for (int i = 0; i < tmp.size(); i++) {
				arraylist.add(((String) tmp.get(i)));
			}
		} catch (Exception sq) {
			System.out.printf(sq.toString());
		}
		return arraylist;
	}

        @Override
	public ArrayList<TimeSlotComponent> getAdvisorSchedule(String name) {
		ArrayList<TimeSlotComponent> array = new ArrayList<>();
		try {
			Connection conn = this.connectDB();
			PreparedStatement statement;
			if (name.equals("all")) {
				String command = "SELECT pname,advising_date,advising_starttime,advising_endtime,id FROM user,advising_schedule,advisor_settings "
						+ "WHERE user.userid=advisor_settings.userid AND user.userid=advising_schedule.userid AND studentid is null";
				statement = conn.prepareStatement(command);
			} else {
				String command = "SELECT pname,advising_date,advising_starttime,advising_endtime,id FROM USER,ADVISING_SCHEDULE,ADVISOR_SETTINGS "
						+ "WHERE USER.userid=ADVISOR_SETTINGS.userid AND USER.userid=ADVISING_SCHEDULE.userid AND USER.userid=ADVISING_SCHEDULE.userid AND ADVISOR_SETTINGS.pname=? AND studentid is null";
				statement = conn.prepareStatement(command);
				statement.setString(1, name);
			}
			ResultSet res = statement.executeQuery();
			while (res.next()) {
				// Use flyweight factory to avoid build cost if possible
				PrimitiveTimeSlot set = (PrimitiveTimeSlot) TimeSlotFlyweightFactory
						.getInstance().getFlyweight(
								res.getString(1) + "-" + res.getString(2),
								res.getString(3));
				set.setName(res.getString(1));
				set.setDate(res.getString(2));
				set.setStartTime(res.getString(3));
				set.setEndTime(res.getString(4));
				set.setUniqueId(res.getInt(5));
				array.add(set);
			}
			array = TimeSlotHelpers.createCompositeTimeSlot(array);
			conn.close();
		} catch (Exception e) {
			System.out.printf(e.toString());
		}
		return array;
	}

        @Override
	public Boolean createAppointment(Appointment a, String email) {
		Boolean result = false;
		int student_id = 0;
		int advisor_id = 0;
		try {
			Connection conn = this.connectDB();
			PreparedStatement statement;
			String command = "SELECT userid from user where email=?";
			statement = conn.prepareStatement(command);
			statement.setString(1, email);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				student_id = rs.getInt(1);
			}
			command = "SELECT userid FROM advisor_settings WHERE advisor_settings.pname=?";
			statement = conn.prepareStatement(command);
			statement.setString(1, a.getPname());
			rs = statement.executeQuery();
			while (rs.next()) {
				advisor_id = rs.getInt(1);
			}
			// check for slots already taken
			command = "SELECT COUNT(*) FROM advising_schedule WHERE userid=? AND advising_date=? AND advising_starttime=? AND advising_endtime=? AND studentid is not null";
			statement = conn.prepareStatement(command);
			statement.setInt(1, advisor_id);
			statement.setString(2, a.getAdvisingDate());
			statement.setString(3, a.getAdvisingStartTime());
			statement.setString(4, a.getAdvisingEndTime());
			rs = statement.executeQuery();
			while (rs.next()) {
				if (rs.getInt(1) < 1) {
					command = "INSERT INTO appointments (advisor_userid,student_userid,advising_date,advising_starttime,advising_endtime,appointment_type,studentid,description,student_email)"
							+ "VALUES(?,?,?,?,?,?,?,?,?)";
					statement = conn.prepareStatement(command);
					statement.setInt(1, advisor_id);
					statement.setInt(2, student_id);
					statement.setString(3, a.getAdvisingDate());
					statement.setString(4, a.getAdvisingStartTime());
					statement.setString(5, a.getAdvisingEndTime());
					statement.setString(6, a.getAppointmentType());
					statement.setInt(7, Integer.parseInt(a.getStudentid()));
					statement.setString(8, a.getDescription());
					statement.setString(9, email);
					statement.executeUpdate();
					command = "UPDATE advising_schedule SET studentid=? where userid=? AND advising_date=? and advising_starttime >= ? and advising_endtime <= ?";
					statement = conn.prepareStatement(command);
					statement.setInt(1, Integer.parseInt(a.getStudentid()));
					statement.setInt(2, advisor_id);
					statement.setString(3, a.getAdvisingDate());
					statement.setString(4, a.getAdvisingStartTime());
					statement.setString(5, a.getAdvisingEndTime());
					statement.executeUpdate();
					result = true;
				}
			}
			conn.close();
		} catch (Exception e) {
			System.out.printf(e.toString());
		}
		return result;
	}

        @Override
	public ArrayList<Object> getAppointments(AdvisorUser user) {
		ArrayList<Object> appointments = new ArrayList<>();
		try {
			Connection conn = this.connectDB();
			PreparedStatement statement;
			String command = "SELECT advisor_settings.pname,advisor_settings.email,"
                                + "advising_date,advising_starttime,advising_endtime,"
                                + "appointment_type,id,appointments.description,"
                                + "studentid,appointments.student_email, urgency "
                                + "FROM USER,APPOINTMENTS,ADVISOR_SETTINGS "
					+ "WHERE USER.email=? AND "
                                + "user.userid=appointments.advisor_userid AND "
                                + "advisor_settings.userid=appointments.advisor_userid";
			statement = conn.prepareStatement(command);
			statement.setString(1, user.getEmail());
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				Appointment set = new Appointment();
				set.setPname(rs.getString(1));
				set.setAdvisorEmail(rs.getString(2));
				set.setAdvisingDate(rs.getString(3));
				set.setAdvisingStartTime(rs.getString(4));
				set.setAdvisingEndTime(rs.getString(5));
				set.setAppointmentType(rs.getString(6));
				set.setAppointmentId(rs.getInt(7));
				set.setDescription(rs.getString(8));
				set.setStudentid(rs.getString(9));
				set.setStudentEmail(rs.getString(10));
                                set.setUrgency(rs.getInt(11));
				appointments.add(set);
			}
			conn.close();
		} catch (Exception e) {
			System.out.printf(e.toString());
		}

		return appointments;
	}

        @Override
	public ArrayList<Object> getAppointments(StudentUser user) {
		ArrayList<Object> appointments = new ArrayList<>();
		try {
			Connection conn = this.connectDB();
			PreparedStatement statement;
			String command = "SELECT advisor_settings.pname,advisor_settings.email,advising_date,advising_starttime,advising_endtime,appointment_type,id,description,student_email,urgency FROM USER,APPOINTMENTS,ADVISOR_SETTINGS "
					+ "WHERE USER.email=? AND user.userid=appointments.student_userid AND advisor_settings.userid=appointments.advisor_userid";
			statement = conn.prepareStatement(command);
			statement.setString(1, user.getEmail());
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				Appointment set = new Appointment();
				set.setPname(rs.getString(1));
				set.setAdvisorEmail(rs.getString(2));
				set.setAdvisingDate(rs.getString(3));
				set.setAdvisingStartTime(rs.getString(4));
				set.setAdvisingEndTime(rs.getString(5));
				set.setAppointmentType(rs.getString(6));
				set.setAppointmentId(rs.getInt(7));
				set.setDescription(rs.getString(8));
				set.setStudentid("Advisor only");
				set.setStudentEmail(rs.getString(9));
                                set.setUrgency(rs.getInt(10));
				appointments.add(set);
			}
			conn.close();
		} catch (Exception e) {
			System.out.printf(e.toString());
		}

		return appointments;
	}

        @Override
        public ArrayList<Object> getAppointments(String user) {
		ArrayList<Object> appointments = new ArrayList<>();
		try {
			Connection conn = this.connectDB();
			PreparedStatement statement;
			String command = "SELECT advisor_settings.pname,advisor_settings.email,advising_date,advising_starttime,advising_endtime,appointment_type,id,description,student_email, urgency FROM USER,APPOINTMENTS,ADVISOR_SETTINGS "
					+ "WHERE USER.email=? AND user.userid=appointments.student_userid AND advisor_settings.userid=appointments.advisor_userid AND advising_starttime=?";
			statement = conn.prepareStatement(command);
			statement.setString(1, user);
                        statement.setString(2, (new SimpleDateFormat("HHmmss").format(Calendar.getInstance().getTime())));
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				Appointment set = new Appointment();
				set.setPname(rs.getString(1));
				set.setAdvisorEmail(rs.getString(2));
				set.setAdvisingDate(rs.getString(3));
				set.setAdvisingStartTime(rs.getString(4));
				set.setAdvisingEndTime(rs.getString(5));
				set.setAppointmentType(rs.getString(6));
				set.setAppointmentId(rs.getInt(7));
				set.setDescription(rs.getString(8));
				set.setStudentid("Advisor only");
				set.setStudentEmail(rs.getString(9));
                                set.setUrgency(rs.getInt(10));
				appointments.add(set);
			}
			conn.close();
		} catch (Exception e) {
			System.out.printf(e.toString());
		}

		return appointments;
	}
        
        @Override
        public String[] getMailIds() 
        {
            
                int i=0;
                String arr1[] = new String[1];
                try
                {
                    Connection conn = this.connectDB();
                    PreparedStatement statement;
                    String command = "Select email from user where role='student'";
                    statement = conn.prepareStatement(command);
                    ResultSet rs = statement.executeQuery();
                    String[] arr = new String[40];
                    while(rs.next())
                    {
                        arr[i] = rs.getString(1);
                        i++;
                    }
                    return arr;
                }
                catch(Exception e)
                {
                    System.out.printf(e.toString());
                 }
              return arr1;      
         }
        
        @Override
	public ArrayList<Object> getAppointments(AdminUser user) {
		ArrayList<Object> appointments = new ArrayList<>();
		try {
			Connection conn = this.connectDB();
			PreparedStatement statement;
			String command = "SELECT advisor_settings.pname,advisor_settings.email,advising_date,advising_starttime,advising_endtime,appointment_type,id, urgency FROM appointments INNER JOIN advisor_settings "
					+ "WHERE advisor_settings.userid = appointments.advisor_userid";
			statement = conn.prepareStatement(command);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				Appointment set = new Appointment();
				set.setPname(rs.getString(1));
				set.setAdvisorEmail(rs.getString(2));
				set.setAdvisingDate(rs.getString(3));
				set.setAdvisingStartTime(rs.getString(4));
				set.setAdvisingEndTime(rs.getString(5));
				set.setAppointmentType(rs.getString(6));
				set.setAppointmentId(rs.getInt(7));
                                set.setUrgency(rs.getInt(8));
				appointments.add(set);
			}
			conn.close();
		} catch (Exception e) {
			System.out.printf(e.toString());
		}

		return appointments;
	}
        
        @Override
        public ArrayList<Object> getWaitList() {
		ArrayList<Object> waitlist = new ArrayList<>();
		try {
			Connection conn = this.connectDB();
			PreparedStatement statement;
			String command = "SELECT * from waitlist";
			statement = conn.prepareStatement(command);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				WaitList set = new WaitList();
				set.setPname(rs.getString(1));
				
				set.setAppointmentType(rs.getString(2));
				set.setDescription(rs.getString(3));
                                set.setStudentid(rs.getString(4));
                                set.setStudentEmail(rs.getString(5));
				waitlist.add(set);
			}
			conn.close();
		} catch (Exception e) {
			System.out.printf(e.toString());
		}

		return waitlist;
	}
        
        @Override
         public ArrayList<Object> getPortal() {
		ArrayList<Object> waitlist = new ArrayList<>();
		try {
			Connection conn = this.connectDB();
			PreparedStatement statement;
			String command = "SELECT * from onlineportal";
			statement = conn.prepareStatement(command);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				OnlinePortalView set = new OnlinePortalView();
				set.setStudentid(rs.getString(1));
				
				set.setStudentEmail(rs.getString(2));
				set.setQuestion(rs.getString(3));
                              
				waitlist.add(set);
			}
			conn.close();
		} catch (Exception e) {
			System.out.printf(e.toString());
		}

		return waitlist;
	}
         
         @Override
	public Boolean cancelAppointment(int id) {
		Boolean result = false;
		try {
                        DatabaseManager dbm = new DatabaseManager();
                        ArrayList<Object> waitlist = dbm.getWaitList();
                        
                        Runnable r1 = new Runnable() {
                            @Override
                            public void run() {
                                for (Object obj : waitlist){
                                    WaitList student = (WaitList)obj;
                                    Email e = new Email("Your waiting list has decreased by one","",student.getStudentEmail());
                                    e.sendMail();
                                }
                            }
                        };
                        r1.run();
			Connection conn = this.connectDB();
			PreparedStatement statement;
			String command = "SELECT count(*),advising_date,advising_starttime, advising_endtime from appointments where id=?";
			statement = conn.prepareStatement(command);
			statement.setInt(1, id);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				if (rs.getInt(1) == 1) {
					command = "DELETE FROM appointments where id=?";
					statement = conn.prepareStatement(command);
					statement.setInt(1, id);
					statement.executeUpdate();
					command = "UPDATE advising_schedule SET studentid=null where advising_date=? AND advising_starttime >=? AND advising_endtime <=?";
					statement = conn.prepareStatement(command);
					statement.setString(1, rs.getString(2));
					statement.setString(2, rs.getString(3));
					statement.setString(3, rs.getString(4));
					statement.executeUpdate();
					result = true;
				}
			}
			conn.close();
		} catch (SQLException e) {
			System.out.printf("Error in Database: " + e.toString());
			return false;
		}
		return result;
	}
@Override
	public String addTimeSlot(AllocateTime at) {
		SQLCmd cmd = new GetUserID(at.getEmail());
		cmd.execute();
		int id = (int) cmd.getResult().get(0);
		cmd = new CheckTimeSlot(at, id);
		cmd.execute();
		if ((Boolean) cmd.getResult().get(0) == true) {
			cmd = new AddTimeSlot(at, id);
			cmd.execute();
			return (String) cmd.getResult().get(0);
		} else {
			return "Unable to add time slot.";
		}
	}
@Override
	public ArrayList<AppointmentType> getAppointmentTypes(String pname) {
		ArrayList<AppointmentType> ats = new ArrayList<>();
		try {
			Connection conn = this.connectDB();
			PreparedStatement statement;
			String command = "SELECT type,duration,user.email FROM  appointment_types,advisor_settings,user WHERE appointment_types.userid=advisor_settings.userid AND advisor_settings.userid=user.userid AND advisor_settings.pname=?";
			statement = conn.prepareStatement(command);
			statement.setString(1, pname);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				AppointmentType at = new AppointmentType();
				at.setType(rs.getString(1));
				at.setDuration(rs.getInt(2));
				at.setEmail(rs.getString(3));
				ats.add(at);
			}
			conn.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		return ats;

	}
@Override
	public Boolean deleteTimeSlot(AllocateTime at) {
		Boolean b;
		SQLCmd cmd = new DeleteTimeSlot(at);
		cmd.execute();
		b = (Boolean) (cmd.getResult()).get(0);
		return b;
	}
@Override
	public Appointment getAppointment(String d, String e) {
		Appointment app = null;
		try {
			SQLCmd cmd = new GetAppointment(d, e);
			cmd.execute();
			if (cmd.getResult().size() > 0) {
				app = (Appointment) (cmd.getResult()).get(0);
			}
		} catch (Exception ex) {
			System.out.println(ex);
		}
		return app;
	}
@Override
	public Boolean createAdvisor(CreateAdvisorBean ca) {
		try {
			SQLCmd cmd = new CreateAdvisor(ca);
			cmd.execute();
			if ((Boolean) cmd.getResult().get(0)) {
				cmd = new GetUserID(ca.getEmail());
				cmd.execute();
				cmd = new CreateInitialAdvisorSettings((int) cmd.getResult()
						.get(0), ca);
				cmd.execute();
				return (Boolean) cmd.getResult().get(0);
			} else {
				return false;
			}

		} catch (Exception e) {
			return false;
		}
	}
@Override
	public String addAppointmentType(AdvisorUser user, AppointmentType at) {
		String msg = null;
		SQLCmd cmd = new GetUserID(user.getEmail());
		cmd.execute();
		cmd = new AddAppointmentType(at, (int) cmd.getResult().get(0));
		cmd.execute();
		return (String) cmd.getResult().get(0);
	}

    @Override
    public ArrayList<Object> getUpcomingAppointments(int startIn, int timelimit) {
        ArrayList<Object> result = null;
	try {
            SQLCmd cmd = new GetUpcomingAppointments(startIn, timelimit);
            cmd.execute();
            result = cmd.getResult();
	} catch (Exception ex) {
            System.out.println(ex);
	}
	return result;
    }

    @Override
    public StudentUser getStudent(String id){
        StudentUser student = null;
		try {
			SQLCmd cmd = new GetStudent(id);
			cmd.execute();
			if (cmd.getResult().size() > 0) {
				student = (StudentUser) (cmd.getResult()).get(0);
			}
		} catch (Exception ex) {
			System.out.println(ex);
		}
		return student;
    }
    
    @Override
    public boolean addDefaulter(String email){
        boolean result = false;
        try{
            SQLCmd cmd = new AddDefaulter(email);
                cmd.execute();
                if (cmd.getResult().size() > 0){
                    result = (boolean)(cmd.getResult().get(0));
                }
            }
        catch(Exception e) {
            return false;
        }
        return result;
    }
    
    @Override
    public boolean removeFromWaitList(String email){
        boolean result = false;
        try{
            SQLCmd cmd = new RemoveFromWaitList(email);
                cmd.execute();
                if (cmd.getResult().size() > 0){
                    result = (boolean)(cmd.getResult().get(0));
                }
            }
        catch(Exception e) {
            return result;
        }
        return result;
    }
    
    @Override
    public boolean lockAccount(String email){
        boolean result = false;
        try{
            SQLCmd cmd = new LockAccount(email);
            cmd.execute();
            if(cmd.getResult().size() > 0){
                result = (boolean)(cmd.getResult().get(0));
            }
        } catch(Exception e){
            return result;
        }
        return result;
    }
    
    @Override
    public boolean unlockAccount(String email){
        boolean result = false;
        try{
            SQLCmd cmd = new UnlockAccount(email);
            cmd.execute();
            if(cmd.getResult().size() > 0){
                result = (boolean)(cmd.getResult().get(0));
            }
        } catch(Exception e){
            return result;
        }
        return result;
    }
    
    @Override
    public AppointmentType getAppointmentType(String name){
        AppointmentType app = null;
		try {
			SQLCmd cmd = new GetAppointmentType(name);
			cmd.execute();
			if (cmd.getResult().size() > 0) {
				app = (AppointmentType) (cmd.getResult()).get(0);
			}
		} catch (Exception ex) {
			System.out.println(ex);
		}
		return app;
    }

    @Override
    public void resetLoginAttempts(String email) {
        try{
            SQLCmd cmd = new ResetLoginAttempts(email);
            cmd.execute();
        } catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public int incrementLoginAttempts(String email) {
        int result = 0;
        try{
            SQLCmd cmd = new IncrementLoginAttempts(email);
            cmd.execute();
            if(cmd.getResult().size() > 0){
                result = (int)(cmd.getResult().get(0));
            }
        } catch(Exception e){
            return result;
        }
        return result;
    }
    
    @Override
    public boolean insertSecQuestions(String email, int sq1, int sq2, int sq3, String sqa1, String sqa2, String sqa3){
        boolean result = false;
        try{
            SQLCmd cmd = new InsertSecQuestions(email, sq1, sq2, sq3, sqa1, sqa2, sqa3);
            cmd.execute();
            if(cmd.getResult().size() > 0){
                result = (boolean)(cmd.getResult().get(0));
            }
        } catch(Exception e){
            return result;
        }
        return result;
    }

    @Override
    public void updateProfilePicture(String email, InputStream image) {
        try{
            SQLCmd cmd = new UpdateProfilePicture(email, image);
            cmd.execute();
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    @Override
    public LoginUser updateStudentProfile(String email, String carrier, String phone, String course, String oldEmail){
        LoginUser result = null;
        try{
            SQLCmd cmd = new UpdateStudentProfile(email, carrier, phone, course, oldEmail);
            cmd.execute();
            cmd = new GetStudent(email);
            cmd.execute();
            if(cmd.getResult().size() > 0){
                result = (LoginUser)(cmd.getResult().get(0));
            }
        } catch (Exception e){
            System.out.println(e.getMessage());
            return result;
        }
        return result;
    }
    
    @Override
    public ArrayList<SimpleEntry<Integer, String>> getSecurityQuestions(String email){
        ArrayList<SimpleEntry<Integer, String>> questions = new ArrayList<>();
        try{
            SQLCmd cmd = new GetSecurityQuestions(email);
            cmd.execute();
            for (int x=0; x< cmd.getResult().size(); x++){
                questions.add((SimpleEntry<Integer, String>)cmd.getResult().get(x));
            }

        } catch (Exception e){
            return questions;
        }
        return questions;
    }
    
    @Override
    public void toggleUrgency(int id){
        try{
            SQLCmd cmd = new ToggleUrgency(id);
            cmd.execute();
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public ArrayList<Object> getFutureStudents(int id) {
        ArrayList<Object> result = null;
        try{
            SQLCmd cmd = new GetFutureStudents(id);
            cmd.execute();
            if(cmd.getResult().size() > 0){
                result = cmd.getResult();
            }
        } catch (Exception e){
            return result;
        }
        return result;
    }

    @Override
    public ArrayList<Object> getTodaysStudents(int id) {
        ArrayList<Object> result = null;
        try{
            SQLCmd cmd = new GetTodaysStudents(id);
            cmd.execute();
            if(cmd.getResult().size() > 0){
                result = cmd.getResult();
            }
        } catch (Exception e){
            return result;
        }
        return result;
    }
    
    @Override
    public LoginUser getUser(String email){
        LoginUser user = null;
        try{
            SQLCmd cmd = new GetUser(email);
            cmd.execute();
            if(cmd.getResult().size() > 0){
                user = (LoginUser)cmd.getResult().get(0);
            }
        } catch (Exception e){
            return user;
        }
        return user;
    }
}
