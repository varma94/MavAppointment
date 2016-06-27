package uta.mav.appoint.db.command;

import java.sql.PreparedStatement;

import uta.mav.appoint.login.LoginUser;

public class CreateUser extends SQLCmd {

	private LoginUser loginUser;
	private String email;
	private String password;
	private String role;
	private Boolean b;
        private String course;
        private String phone;
        private String carrier;

	public CreateUser(LoginUser loginUser) {
		this.email = loginUser.getEmail();
		this.password = loginUser.getPassword();
		this.role = loginUser.getRole();
		this.loginUser = loginUser;
                this.course = loginUser.getCourse();
                this.phone = loginUser.getPhone();
                this.carrier = loginUser.getCarrier();
                
		b = false;
	}

	@Override
	public void queryDB() {
		try {
			String command = "INSERT INTO user (email,password,role,phone,course,carrier)"
					+ " values(?,?,?,?,?,?)";
			PreparedStatement statement = conn.prepareStatement(command);
			statement.setString(1, email);
			statement.setString(2, password);
			statement.setString(3, role);
                        statement.setString(4, phone);
                        statement.setString(5, course);
                        statement.setString(6, carrier);

			statement.executeUpdate();

			b = true;
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	@Override
	public void processResult() {
		try {
			SQLCmd cmd = new GetUserIDByEmail(loginUser.getEmail());
			cmd.execute();
			loginUser.setUserId((int) cmd.getResult().get(0));
			System.out.println("Finished getting ID");
		} catch (Exception e) {
			System.out.println(e + " In " + this.getClass().getSimpleName());
		}

		result.add(b);
	}

}
