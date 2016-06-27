package uta.mav.appoint.beans;

import java.io.Serializable;

public class OnlinePortalView implements Serializable{

	/**
	 * JavaBean for Appointments db table
	 */
	private static final long serialVersionUID = -3734663824525723817L;
	String sID;
	String email;
	String question;
	String name;
	
	/**
	 * @return the pname
	 */
	public String getSid() {
		return sID;
	}
	/**
	 * @param pname the pname to set
	 */
	public void setSid(String sid) {
		this.sID = sid;
	}
	/**
	 * @return the advisingDate
	 */
	
	
	/**
	 * @param appointmentType the appointmentType to set
	 */
	
	/**
	 * @return the advisorEmail
	 */
	
	/**
	 * @return the appointmentId
	 */
	
	
	@Override
	public String toString(){
		return	String.format("%10s %10s ",this.getQuestion(),this.getStudentid());
	}
	/**
	 * @return the description
	 */
	public String getQuestion() {
		return question;
	}
	/**
	 * @param description the description to set
	 */
	public void setQuestion(String description) {
		this.question = description;
	}
	/**
	 * @return the studentid
	 */
	public String getStudentid() {
		return sID;
	}
	/**
	 * @param studentid the studentid to set
	 */
	public void setStudentid(String studentid) {
		this.sID = studentid;
	}
	/**
	 * @return the studentEmail
	 */
	public String getStudentEmail() {
		return email;
	}
	/**
	 * @param studentEmail the studentEmail to set
	 */
	public void setStudentEmail(String studentEmail) {
		this.email = studentEmail;
	}
}