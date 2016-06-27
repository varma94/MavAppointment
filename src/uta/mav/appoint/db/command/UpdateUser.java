package uta.mav.appoint.db.command;

import java.sql.PreparedStatement;

import uta.mav.appoint.login.LoginUser;

public class UpdateUser extends SQLCmd {

	private LoginUser loginUser;
	private String email;
	private String password;
	
	private Boolean b;

	public UpdateUser(LoginUser loginUser) {
		this.email = loginUser.getEmail();
		this.password = loginUser.getPassword();
		b = false;
	}

	@Override
	public void queryDB() {
		try {
                        String command = "Update user set password = ?, locked=0 where email = ?";
			PreparedStatement statement = conn.prepareStatement(command);
			statement.setString(2, email);
			statement.setString(1, password);
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
