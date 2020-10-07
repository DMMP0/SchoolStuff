<form method="post" action="hiddenF-3.php">

	<input type="radio" group="membership_type" value="Free" name="free" > free <br />
	 
	<input type="radio" group="membership_type" value="Normal" name="normal"> normal <br />
	 
	<input type="radio" group="membership_type" value="Deluxe" name="deluxe"> deluxe <br />
	 
	<input type="checkbox" name="terms_and_conditions">term and condition
	 
	<input type="hidden" name="name"
	value="<?php echo $_POST['name']; ?>">
	 
	<input type="hidden" name="email"
	 value="<?php echo $_POST['email']; ?>">
	  
	<input type="submit" value="Go To Step 3">
	</form>