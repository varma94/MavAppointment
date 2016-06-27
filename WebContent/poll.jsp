<%@include file="templates/header.jsp"%>
<div class="container">
	<form action="PollServlet" method="post">
            <div class="row">
                <div class="col-md-4 col-lg-4">
                    <div class="form-group">
                        <label for="userid">User ID</label> 
                        <input type="text"
                                class="form-control" id=userid name=userid
                                placeholder="1000xxxxxx or 6000xxxxxx"> 
                        
                        <label for="advisor">Do you need more Advisors ?</label>
                        <input type="text" class="form-control" id=advisor name = advisor> 
                        
                        <label for="teaching">How is the teaching ?</label>
                        <input type="text" class="form-control" id=teaching name = teaching>  
                        
                        <label for="subject">Which subject do you like the most ?</label>
                        <input type="text" class="form-control" id=subject name = subject> 
                        
                        <strong>
                            <br> 
                            <label for="q1">1. Did you like the online portal</label>
                            <br>
                            <input type="radio" name="q1" value = "5">Excellent
                            <br>
                            <input type="radio" name="q1" value = "4">Very good
                            <br>
                            <input type="radio" name="q1" value = "3">Average
                            <br>
                            <input type="radio" name="q1" value = "2">Below Average
                            <br>
                            <input type="radio" name="q1" value = "1">Poor
                        </strong>
                    </div>
                </div>
            </div>
            <button type="submit" class="btn btn-primary">Submit</button>
        </form>
		<style>
.form-group {
	color: #FFFFFF;
}
</style>
		<%@include file="templates/footer.jsp"%>