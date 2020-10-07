<form method="post" action="hiddenF-final.php">

	 
	<input type="hidden" name="name_on_card"
           value="<?php echo $_POST['name_on_card']; ?>">
	 
	<input type="hidden" name="credit_card_number"
           value="<?php echo $_POST['credit_card_number']; ?>">
	 
	<input type="hidden" name="credit_card_expiration_date"
           value="<?php echo $_POST['credit_card_expiration_date']; ?>">
	 
	<input type="hidden" name="name"
	 value="<?php echo $_POST['name']; ?>">
	  
	<input type="hidden" name="email"
	 value="<?php echo $_POST['email'] ?>";
	  
	<input type="hidden" name="membership_type"
	 value="<?php echo $_POST['membership_type']; ?>">
	  
	<input type="hidden" name="terms_and_conditions"
	  value="<?php echo $_POST['terms_and_conditions']; ?>">

	<input type="submit" value="Finish">
	</form>

<?php
// define variables and set to empty values
$name = $email = $membert = $tec = $namecard = $credcard=$excard="";

if ($_SERVER["REQUEST_METHOD"] == "POST") {
  if (empty($_POST["name"])) {
    $nameErr = "Name is required";
  } else {
    $name = test_input($_POST["name"]);
    // check if name only contains letters and whitespace
    if (!preg_match("/^[a-zA-Z ]*$/",$name)) {
      $nameErr = "Only letters and white space allowed";
    }
  }
  
  if (empty($_POST["email"])) {
    $emailErr = "Email is required";
  } else {
    $email = test_input($_POST["email"]);
    // check if e-mail address is well-formed
    if (!filter_var($email, FILTER_VALIDATE_EMAIL)) {
      $emailErr = "Invalid email format";
    }
  }

  if (empty($_POST["membership_type"])) {
    $membert = "";
  } else {
    $membert = test_input($_POST["membership_type"]);
  }
//FINIRE DA QUì IN POI
  if (empty($_POST["terms_and_conditions"])) {
    $tec = "";
  } else {
    $tec = test_input($_POST["terms_and_conditions"]);
  }
    if (empty($_POST["name_on_card"])) {
    $namecard = "";
  } else {
    $namecard = test_input($_POST["name_on_card"]);
  }
    if (empty($_POST["credit_card_number"])) {
    $credcard = "";
  } else {
    $credcard = test_input($_POST["credit_card_number"]);
  }
    if (empty($_POST["credit_card_expiration_date"])) {
    $excard = "";
  } else {
    $excard = test_input($_POST["credit_card_expiration_date"]);
  }
}

function test_input($data) {
  $data = trim($data);
  $data = stripslashes($data);
  $data = htmlspecialchars($data);
  return $data;
}
?>

<?php
echo "<h2>Your Input:</h2>";
echo $name;
echo "<br>";
echo $email;
echo "<br>";
echo $membert;
echo "<br>";
echo $tec;
echo "<br>";
echo $namecard;
echo "<br>";
echo $credcard;
echo "<br>";
echo $excard;
echo "<br>";

?>