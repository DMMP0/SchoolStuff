<form method="post" action="hiddenF-final.php">

	 
	<input type="text" name="name_on_card">Name on card <br />
           
	 
	<input type="text" name="credit_card_number">credit card number <br />
           
	 
	<input type="text" name="credit_card_expiration_date">credit card expiration day<br />
           
	 
	<input type="hidden" name="name"
	 value="<?php echo $_POST['name']; ?>">
	  
	<input type="hidden" name="email"
	 value="<?php echo $_POST['email']; ?>" >
	  
	<input type="hidden" name="membership_type"
	 value="<?php echo $_POST['membership_type']; ?>" >
	  
	<input type="hidden" name="terms_and_conditions"
	  value="<?php echo $_POST['terms_and_conditions']; ?>">

	<input type="submit" value="Finish">
	</form>