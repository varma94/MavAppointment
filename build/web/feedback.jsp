<!DOCTYPE html>
<html>
<head><title>Help</title>
</head>
<body>
	<div class="container">
	<form action="FeedBack" method="post">
	<div class="row">
	<div class="col-md-4 col-lg-4">
		<div class="form-group">
			<label for="userid">User ID</label>
			 <input type="text" class="form-control" id=userid name = userid
			 placeholder="1000xxxxxx or 6000xxxxxx">
			 	
			 <label for="emailAddress">Email Address</label>
			 <input type="text" class="form-control" id=emailAddress name = emailAddress
			 placeholder="firstname.lastname@mavs.uta.edu">
			 
			 <label for="firstName">First Name</label>
			 <input type="text" class="form-control" id=fname name = fname>
			 
			 <label for="lastName">Last Name</label>
			 <input type="text" class="form-control" id=lname name = lname>
			 <strong>
			 <br>
			 <label for="q1">1. Did the quality of the services on the Mav Appoint site meet your expectations?</label>
			 <input type="radio" name="q1" value = "5">Excellent
			 <br>
			 <input type="radio" name="q1" value = "4">Verry good
			 <br>
			 <input type="radio" name="q1" value = "3">Average
			 <br>
			 <input type="radio" name="q1" value = "2">Below Average
			 <br>
			 <input type="radio" name="q1" value = "1">Poor
			 <br>
			 <br>
			 <label for="q2">2. How would you rate the design of MavAppoint Web site?</label>
			 <input type="radio" name="q2" value = "5">Excellent
			 <br>
			 <input type="radio" name="q2" value = "4">Verry good
			 <br>
			 <input type="radio" name="q2" value = "3">Average
			 <br>
			 <input type="radio" name="q2" value = "2">Below Average
			 <br>
			 <input type="radio" name="q2" value = "1">Poor
			 <br>
			 <br>
			 <label for="q3">3. Were you able to easily find the information you were seeking?</label>
			 <input type="radio" name="q3" value = "Yes">Yes
			 <br>
			 <input type="radio" name="q3" value = "No">No
			 <br>
			 </strong>
		</div>
	</div>
	</div>
	<button type="submit" class="btn btn-primary">Submit</button></p>	
	<style>
	.form-group{
	color:#FFFFFF;
	}
	</style>
</form>
</div>
</body>
</html>