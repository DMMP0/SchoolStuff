 <?php
$cookie_name = "info";
$cookie_value = " ";
setcookie($cookie_name, $cookie_value, time() + (86400 * 30), "/"); // 86400 = 1 day
?>
<html>
<body>
    <form method="post" action="Cookie1.php">
        <br>Name</br>
	    <input type="text" name="name">
        <br>Mail</br>
	    <input type="text" name="email">
        <br />
        <input type="radio" group="membership_type" value="Free" name="free" > free <br />
	 
	    <input type="radio" group="membership_type" value="Normal" name="normal"> normal <br />
	 
     	<input type="radio" group="membership_type" value="Deluxe" name="deluxe"> deluxe <br />
	 
	    <input type="checkbox" name="terms_and_conditions">term and condition <br />
        <input type="text" name="name_on_card">Name on card <br />
           
	 
     	<input type="text" name="credit_card_number">credit card number <br />
           
	 
    	<input type="text" name="credit_card_expiration_date">credit card expiration day<br />
    
	    <input type="submit" value="Reload">
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

$_COOKIE[$cookie_value] = $_COOKIE[$cookie_value].$name."| ";
$_COOKIE[$cookie_value] = $_COOKIE[$cookie_value].$email."| ";
$_COOKIE[$cookie_value] = $_COOKIE[$cookie_value].$membert."| ";
$_COOKIE[$cookie_value] = $_COOKIE[$cookie_value].$tec."| ";
$_COOKIE[$cookie_value] = $_COOKIE[$cookie_value].$namecard."| ";
$_COOKIE[$cookie_value] = $_COOKIE[$cookie_value].$credcard."| ";
$_COOKIE[$cookie_value] = $_COOKIE[$cookie_value].$excard;

 

/**echo "<h2>Your Input:</h2>";
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
echo "<br>";*/

?>

<?php

   if(!isset($_COOKIE[$cookie_name])) {
    echo "Cookie named '" . $cookie_name . "' is not set!";
   } else {
    echo "Cookie '" . $cookie_name . " is set!<br>";
    echo "Value is: " . $_COOKIE[$cookie_value]. "<br>";
   }

    $i = 0; $j = 0;
    $prname =""; $premail =""; $prmembert =""; $prtec = ""; $prnamecard = ""; $prcredcard =""; $prexcard = "" ;
    
    while(i<6) do
    {
        witch ($i) 
        {
        case 0:
          echo "i equals 0";
          break;
        case 1:
          echo "i equals 1";
          break;
        case 2:
          echo "i equals 2";
          break;
        case 3:
          echo "i equals 0";
          break;
        case 4:
          echo "i equals 1";
          break;
        case 5:
          echo "i equals 2";
          break;
       }
            
    }

?>

</body>
</html> 