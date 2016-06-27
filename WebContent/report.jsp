<form action="reportservlet.java" method="SET">
  <input type="submit" value="approvAll"/>
  <p>Comments on any bugs faced</p>    
  <c:forEach items="${accountIdList}" var="val">
    <input type="text" value="report" name="id"> 
    <select name="role">
        <option value="admin">Admin</option>
        <option value="Advisor">user</option>
        <option value="Student">user</option>
    </select>                              
  </c:forEach>        
</form>