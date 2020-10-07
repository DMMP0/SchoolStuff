 <?php
$servername = "localhost";
$username = "root";
$password = "";
$dbname = "cinema";

// Create connection
$conn = mysqli_connect($servername, $username, $password,$dbname);

// Check connection
if (!$conn) {
    die("Connection failed: " . mysqli_connect_error());
}
echo "Connected successfully";

$use = "use cinema";
$sql = "INSERT INTO attori (CodAttore,nome,annoNascita,nazionalita)
VALUES (NULL, 'Doe','1888','Procedural')";

if (mysqli_query($conn, $sql)) {
    $last_id = mysqli_insert_id($conn);
    echo "New record created successfully. Last inserted ID is: " . $last_id;
} else {
    echo "Error: " . $sql . "<br>" . mysqli_error($conn);
}


mysqli_close($conn); 
?>