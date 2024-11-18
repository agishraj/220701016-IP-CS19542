<?php
include 'db_connect.php';

if ($_SERVER["REQUEST_METHOD"] == "POST") {
    $cid = $_POST['cid'];
    $atype = $_POST['atype'];
    $balance = $_POST['balance'];

    // Validate account type
    if ($atype !== 'S' && $atype !== 'C') {
        die("Invalid account type. Use 'S' for Savings or 'C' for Current.");
    }

    // Generate a random account number
    $ano = rand(1000, 9999); // Change the range as per your requirement

    // Insert account into the database
    $sql = "INSERT INTO ACCOUNT (ANO, ATYPE, BALANCE, CID) VALUES ('$ano', '$atype', '$balance', '$cid')";

    if ($conn->query($sql) === TRUE) {
        echo "New account added successfully!<br>";
        echo "Account Number: " . $ano;
    } else {
        echo "Error: " . $sql . "<br>" . $conn->error;
    }
}

$conn->close();
?>
<a href="add_account.php">Add Another Account</a>
<a href="view_accounts.php">View Accounts</a>
