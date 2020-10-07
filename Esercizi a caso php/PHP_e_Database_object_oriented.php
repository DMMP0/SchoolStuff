 <?php
$servername = "localhost";
$username = "root";
$password = "";
$dbname="cinema";
// Create connection
$conn = new mysqli($servername, $username, $password,$dbname);

// Check connection
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
}
echo "Connected successfully";

$sql = "INSERT INTO attori (CodAttore,nome,annoNascita,nazionalita)
VALUES (NULL, 'Doe','1888','ObjectOriented')";

if ($conn->query($sql) === TRUE) {
    $last_id = $conn->insert_id;
    echo "New record created successfully. Last inserted ID is: " . $last_id;
} else {
    echo "Error: " . $sql . "<br>" . $conn->error;
}

$conn->close();
?> 