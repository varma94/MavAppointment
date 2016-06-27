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
						<strong> WaitList Form</strong>
					</div>
					<div class="panel-body">
						<form role="form" action="WaitListServlet" method="POST">
							<fieldset>
								<div class="row">
									<div class="center-block">
										<img class="profile-img"
											src="img/mavblue.jpg" alt="">
									</div>
								</div>
								<div class="row">
									<div class="col-sm-12 col-md-10  col-md-offset-1 ">
										<div class="form-group">
											<div class="dropdown">
												<span class="input-group-addon">
													<i class="glyphicon glyphicon-user"></i>
												</span> 
												<select name=advisorName class="form-control">
                                                                                                         <option value="Test Adv1">Test Adv1</option>
                                                                                                             <option value="Test Adv2">Test Adv2</option>
                                                                                                              <option value="Test Adv3">Test Adv3</option>
   
                                                                                                    </select>
											</div>
										</div>
                                                                            
                                                                            <div class="form-group">
											<div class="input-group">
												<span class="input-group-addon">
													<i class="glyphicon glyphicon-user"></i>
												</span> 
											<!--<input type="text" class="form-control" name=advisingType placeholder="Advising Type">-->
                                                                                                <select name=advisingType class="form-control">
                                                                                                          <option value="Add Class">Add Class</option>
                                                                                                           <option value="Drop Class">Drop Class</option>
                                                                                                             <option value="Transfer Student">Transfer Student</option>
   
                                                                                                        </select>
											</div>
										</div>
                                                                           
                                                                            <div class="form-group">
											<div class="input-group">
												<span class="input-group-addon">
													<i class="glyphicon glyphicon-user"></i>
												</span> 
												<input type="text" class="form-control" name=description placeholder="Description">
											</div>
										</div>
										<div class="form-group">
											<div class="input-group">
												<span class="input-group-addon">
													<i class="glyphicon glyphicon-lock"></i>
												</span>
												<input type="text" class="form-control" name=studentID placeholder="Student ID" >
											</div>
										</div>
                                                                            <div class="form-group">
											<div class="input-group">
												<span class="input-group-addon">
													<i class="glyphicon glyphicon-lock"></i>
												</span>
												<input type="text" class="form-control" name=studentEmail placeholder="Student Email" >
											</div>
										</div>
										<div class="form-group">
											<input type="submit" class="btn btn-lg btn-primary btn-block" value="Submit">
										</div>
                                                                            <div class="form-group">
                                                                           
                                               
                                                
                                                                              </div>
									</div>
								</div>
							</fieldset>
						</form>

					</div>
					<div class="panel-footer ">
					</div>
                </div>
			</div>
		</div>
	</div>


<%@ include file="templates/footer.jsp" %>