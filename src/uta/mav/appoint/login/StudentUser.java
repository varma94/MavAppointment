package uta.mav.appoint.login;

import java.io.InputStream;
import java.sql.Blob;
import java.util.ArrayList;

import uta.mav.appoint.visitor.Visitor;

public class StudentUser extends LoginUser {

	private String studentId;
        private Blob picture;

	public StudentUser(String em) {
		super(em);
	}

	public StudentUser() {
		super();
	}

	@Override
	public String getHeader() {
		return "student_header";
	}

	@Override
	public void accept(Visitor v) {
		v.check(this);
	}

	@Override
	public ArrayList<Object> accept(Visitor v, Object o) {
		// allow javabean to be passed in
		return v.check(this, o);
	}

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}
        
        public void setPicture(Blob picture){
            this.picture = picture;
        }
        public Blob getPicture(){
            return picture;
        }
        
}
