<%@ include file="templates/header.jsp" %>
<style>
.panel-heading {
    padding: 5px 15px;
}

.panel-footer {
	padding: 1px 15px;
	color: #A0A0A0;
}

.profile-img {
	width: 96px;
	height: 96px;
	margin: 0 auto 10px;
	display: block;
	-moz-border-radius: 50%;
	-webkit-border-radius: 50%;
	border-radius: 50%;
}
</style>

 <div class="container" style="margin-top:40px">
		<div class="row">
			<div class="col-sm-6 col-md-4 col-md-offset-4">
				<div class="panel panel-default">
					<div class="panel-heading">
						<strong> Your Account has been locked after three failed login attempts</strong>
                                                <strong>Click the forgot password link to reset your password</strong>
					</div>
					<div class="panel-body">
						
                            <a href = "ForgotPassword.jsp"><button class="btn btn-link" role="link" type="submit"> Forgot Password</button></a>

					</div>
					<div class="panel-footer ">
					</div>
                </div>
			</div>
		</div>
	</div>


<%@ include file="templates/footer.jsp" %>