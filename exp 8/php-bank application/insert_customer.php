<?php
include 'db_connect.php';

if ($_SERVER["REQUEST_METHOD"] == "POST") {
    $cid = $_POST['cid'];
    $cname = $_POST['cname'];

    // Check if the CID already exists
    $checkQuery = "SELECT * FROM CUSTOMER WHERE CID='$cid'";
    $result = $conn->query($checkQuery);

    if ($result->num_rows > 0) {
        // CID already exists
        echo "
        <script>
            alert('Customer ID $cid already exists! Please enter a different Customer ID.');
            window.location.href='add_customer.php'; // Redirect to the add customer page
        </script>
        ";
    } else {
        // Insert customer into the database
        $sql = "INSERT INTO CUSTOMER (CID, CNAME) VALUES ('$cid', '$cname')";
        
        if ($conn->query($sql) === TRUE) {
            echo "
            <!DOCTYPE html>
            <html lang='en'>
            <head>
                <meta charset='UTF-8'>
                <meta name='viewport' content='width=device-width, initial-scale=1.0'>
                <title>Customer Added</title>
                <style>
                    body {
                        font-family: Arial, sans-serif;
                        background-color: #f4f4f4;
                        display: flex;
                        justify-content: center;
                        align-items: center;
                        height: 100vh;
                        margin: 0;
                    }
                    .message-container {
                        background: white;
                        padding: 20px;
                        border-radius: 8px;
                        box-shadow: 0 0 10px rgba(0,0,0,0.1);
                        text-align: center;
                    }
                    h1 {
                        color: #4CAF50;
                    }
                    a {
                        text-decoration: none;
                        color: #4CAF50;
                        margin: 10px;
                        display: inline-block;
                        font-weight: bold;
                    }
                    a:hover {
                        color: #45a049;
                    }
                </style>
            </head>
            <body>
                <div class='message-container'>
                    <h1>Customer added successfully!</h1>
                    <a href='add_customer.php'>Add Another Customer</a>
                    <a href='view_customer.php'>View Customers</a>
                    <a href='add_account.php'>Add Account</a>
                </div>
            </body>
            </html>
            ";
        } else {
            echo "Error: " . $sql . "<br>" . $conn->error;
        }
    }
}

$conn->close();
?>
