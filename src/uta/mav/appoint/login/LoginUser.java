package uta.mav.appoint.login;

import java.util.ArrayList;

import uta.mav.appoint.visitor.Visitor;

public class LoginUser {
	String email;
	String role;
	String msg;
	String password;
	Integer userId;
        String que;
        String phone;
        String course;
        String carrier;

	public LoginUser() {
		email = "";
		msg = "";
	}

	public void accept(Visitor v) {
		v.check(this);
	}

	public String getPname() {
		return "";
	}

	public ArrayList<Object> accept(Visitor v, Object o) {// allow javabean to
															// be passed in
		return v.check(this, o);
	}

	public LoginUser(String em) {
		email = em;
	}

	/*
	 * @return the header - override in extended classes for proper header
	 * display
	 */
	public String getHeader() {
		return "header";
	}

	
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
	/**
	 * @return the email
	 */
	
	public String getEmail() {
		return email;
	}

	public String getMsg() {
		return msg;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	public void setMsg(String s) {
		this.msg = s;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
         
          public void setPhone(String phone) {
		this.phone = phone;
	}
        
         public String getPhone() {
		return phone;
	}
         
          public void setCourse(String course) {
		this.course = course;
	}
        
         public String getCourse() {
		return course;
	}
         
         public void setCarrier(String carrier){
             this.carrier = carrier;
         }
         
         public String getCarrier(){
             return this.carrier;
         }
}
