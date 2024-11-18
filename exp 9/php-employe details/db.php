<?php
$servername = "localhost";  // MySQL server
$username = "root";         // MySQL username
$password = "admin";             // MySQL password (leave blank if no password is set)
$dbname = "EmployeeDB";     // Name of the database

// Create connection
$conn = new mysqli($servername, $username, $password, $dbname);

// Check connection
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
}
?>
