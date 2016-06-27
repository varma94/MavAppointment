package uta.mav.appoint.beans;

import java.io.Serializable;

public class WaitList implements Serializable{

	/**
	 * JavaBean for Appointments db table
	 */
	private static final long serialVersionUID = -3734663824525723817L;
	String pname;
	String appointmentType;
	String description;
	String studentid;
	int appointmentId;
	String studentEmail;
	/**
	 * @return the pname
	 */
	public String getPname() {
		return pname;
	}
	/**
	 * @param pname the pname to set
	 */
	public void setPname(String pname) {
		this.pname = pname;
	}
	/**
	 * @return the advisingDate
	 */
	
	public String getAppointmentType() {
		return appointmentType;
	}
	/**
	 * @param appointmentType the appointmentType to set
	 */
	public void setAppointmentType(String appointmentType) {
		this.appointmentType = appointmentType;
	}
	/**
	 * @return the advisorEmail
	 */
	
	/**
	 * @return the appointmentId
	 */
	
	
	@Override
	public String toString(){
		return	String.format("%10s %10s ",this.getPname(),this.getAppointmentType());
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @return the studentid
	 */
	public String getStudentid() {
		return studentid;
	}
	/**
	 * @param studentid the studentid to set
	 */
	public void setStudentid(String studentid) {
		this.studentid = studentid;
	}
	/**
	 * @return the studentEmail
	 */
	public String getStudentEmail() {
		return studentEmail;
	}
	/**
	 * @param studentEmail the studentEmail to set
	 */
	public void setStudentEmail(String studentEmail) {
		this.studentEmail = studentEmail;
	}
}