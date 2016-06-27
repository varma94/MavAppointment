package uta.mav.appoint.beans;

import java.io.Serializable;

public class PollServletB implements Serializable{

	/**
	 * JavaBean for Appointments db table
	 */
	private static final long serialVersionUID = -3734663824525723817L;
	String sID;
	String teaching;
	String subject;
	String online;
        String advising;
	
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
	
	
//	@Override
//	public String toString(){
//		return	String.format("%10s %10s ",this.getQuestion(),this.getStudentid());
//	}
	/**
	 * @return the description
	 */
	public String getAdvising() {
		return advising;
	}
	/**
	 * @param description the description to set
	 */
	public void setAdvising(String advising) {
		this.advising = advising;
	}
	/**
	 * @return the studentid
	 */
	
	/**
	 * @return the studentEmail
	 */
	public String getTeaching() {
		return teaching;
	}
	/**
	 * @param teaching the value to set
	 */
	public void setTeaching(String teaching) {
		this.teaching = teaching;
	}
        
        public String getSubject() {
		return subject;
	}
	/**
	 * @param studentEmail the studentEmail to set
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}
        
        public String getOnline() {
		return online;
	}
	/**
	 * @param studentEmail the studentEmail to set
	 */
	public void setOnline(String online) {
		this.online = online;
	}
}