<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Life fitness</title>
</head>
<body>
       <h1>JOIN TODAY!</h1>
      <form action = "register">
         First name<br>
         <input type = "text" name = "fname" maxlength="30" placeholder="First Name" required />
        Last name  
         <input type = "text" name = "lname" maxlength="30" required /><br>
             Enter Phone Number<br>
         <input type = "text" name = "phoneNo"  required /><br>
         Enter Email id<br>
         <input type = "email" name = "email"  required /><br>
         Enter the password<br>
         
		<input type="password" name = "password" pattern="^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?!.*\s).*$" title="Please include at least 1 uppercase character, 1 lowercase character, and 1 number." required><br>
          Enter Age<br>
         <input type="text" name = "age"/> <br>
          Gender Type <br> 
         <input type="radio" name="gender" value="male"> Male
 		 <input type="radio" name="gender" value="female"> Female
 		 <input type="radio" name="gender" value="other"> Other <br>
         <legend>Employment status</legend>
 		 <select class="form-control dropdown" id="empstatus" name="empstatus">
   		 <option value="" selected="selected" disabled="disabled">-- select one --</option>
   		 <option value="Employed">Employed</option>
   		 <option value="Self-employed">Self-employed</option>
   		 <option value="Doctor">Doctor</option>
         <option value="Engineer">Engineer</option>
   		 <option value="Homemaker">Homemaker</option>
   		 <option value="Student">Student</option>
   		 <option value="Retired">Retired</option>
   		 <option value="Politician">Politician</option>
  	     <option value="Other">Other</option>
  		 </select><br>
         Enter the Address 1<br> 
         <input type = "text" name = "address1"/><br>
         Enter the Address 2<br>
         <input type = "text" name = "address2"/><br>
         Enter the Address 3<br>
         <input type = "text" name = "address3"/><br>
         Enter the City/Town <br>
         <input type = "text" name = "city"/><br>
         Enter District<br>
         <input type = "text" name = "district"/><br>
         Enter Pincode<br>
         <input type = "text" name = "pincode"/> <br>
         Enter State<br>
         <input type = "text" name = "state"/> <br><br>
         <input type = "submit" value = "Register"/><br>
      </form>
   </body>
 
</html>