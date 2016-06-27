package uta.mav.appoint.db;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.AbstractMap.SimpleEntry;

import uta.mav.appoint.TimeSlotComponent;
import uta.mav.appoint.beans.AllocateTime;
import uta.mav.appoint.beans.Appointment;
import uta.mav.appoint.beans.AppointmentType;
import uta.mav.appoint.beans.CreateAdvisorBean;
import uta.mav.appoint.beans.GetSet;
import uta.mav.appoint.beans.PollServletB;
import uta.mav.appoint.beans.WaitList;
import uta.mav.appoint.login.AdminUser;
import uta.mav.appoint.login.AdvisorUser;
import uta.mav.appoint.login.LoginUser;
import uta.mav.appoint.login.StudentUser;
import uta.mav.appoint.login.UpdatePasswordUser;

public interface DBImplInterface {
	public Boolean cancelAppointment(int id) throws SQLException;
	public ArrayList<Object> getAppointments(AdvisorUser user) throws SQLException;
	public ArrayList<Object> getAppointments(StudentUser user) throws SQLException;
	public ArrayList<Object> getAppointments(AdminUser user) throws SQLException;
	public Boolean createAppointment(Appointment a, String email) throws SQLException;
	public ArrayList<TimeSlotComponent> getAdvisorSchedule(String name) throws SQLException;
        public String[] getMailIds() ;
	public int addUser(GetSet set) throws SQLException;
	public ArrayList<String> getAdvisors() throws SQLException;
	public LoginUser checkUser(GetSet set) throws SQLException;
	public String addTimeSlot(AllocateTime at) throws SQLException;
	public Connection connectDB();
	public ArrayList<AppointmentType> getAppointmentTypes(String pname) throws SQLException;
	public Boolean updateAppointment(Appointment a);
	public Boolean deleteTimeSlot(AllocateTime at) throws SQLException;
	public Appointment getAppointment(String d, String e) throws SQLException;
	public Boolean createAdvisor(CreateAdvisorBean ca) throws SQLException;	
	public String addAppointmentType(AdvisorUser user, AppointmentType at) throws SQLException;
	public Boolean createStudent(StudentUser studentUser)throws SQLException;
        public Boolean updateStudent(StudentUser studentUser) throws SQLException;
	public UpdatePasswordUser checkUserPassword(GetSet set)throws SQLException;
        public ArrayList<Object> getUpcomingAppointments(int startIn, int timelimit);
        public StudentUser getStudent(String id);
        public boolean addDefaulter(String email);
        public boolean removeFromWaitList(String email);
        public AppointmentType getAppointmentType(String name);
        public boolean lockAccount(String email);
        public boolean unlockAccount(String email);
        public void resetLoginAttempts(String email);
        public int incrementLoginAttempts(String email);
        public boolean insertSecQuestions(String email, int sq1, int sq2, int sq3, String sq1a, String sq2a, String sq3a);
        public LoginUser updateStudentProfile(String email, String carrier, String phone, String course, String oldEmail);
        public ArrayList<SimpleEntry<Integer, String>> getSecurityQuestions(String email);
        public void toggleUrgency(int id);
        public ArrayList<Object> getFutureStudents(int id);
        public ArrayList<Object> getTodaysStudents(int id);
        public LoginUser getUser(String email);

    public ArrayList<Object> getAppointments(String email);

    public String waitUser(GetSet set)throws SQLException;

    public String updateNotify(GetSet set) throws SQLException;

    public ArrayList<Object> getWaitList();

    public String onlinePortal(GetSet set) throws SQLException;

    public ArrayList<Object> getPortal() throws SQLException;

    public String pollServ(PollServletB set) throws SQLException;

    public void updateProfilePicture(String email, InputStream image);
}
