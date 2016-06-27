package uta.mav.appoint.db.command;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import uta.mav.appoint.login.StudentUser;

public class CreateStudent extends SQLCmd {

	private Integer userid;
	private String student_Id;
	private Boolean b;

	public CreateStudent(StudentUser studentUser) {
		userid = studentUser.getUserId();
		student_Id = studentUser.getStudentId();
		b = false;
	}

	@Override
	public void queryDB() {
		try {
			String command = "INSERT INTO User_Student (userid,studentId) "
					+ "values(?,?)";
			PreparedStatement statement = conn.prepareStatement(command);

			statement.setInt(1, userid);
			statement.setString(2, student_Id);

			statement.executeUpdate();
			b = true;
		} catch (SQLException sqe) {
			System.out.println(sqe.toString() + "RegisterInitialStudent");
		}
	}

	@Override
	public void processResult() {
		System.out.println("Created " + result);
		result.add(b);
	}

}