<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Add Account</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
        }
        .container {
            width: 80%;
            margin: 20px auto;
            background: white;
            padding: 20px;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
            border-radius: 10px;
        }
        h2 {
            text-align: center;
            color: #333;
        }
        form {
            width: 100%;
            max-width: 600px;
            margin: 0 auto;
            padding: 20px;
            background: #f9f9f9;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
        }
        form label {
            display: block;
            margin: 10px 0 5px;
            font-weight: bold;
            color: #333;
        }
        form input[type="number"],
        form input[type="text"],
        form input[type="submit"] {
            width: 100%;
            padding: 10px;
            margin: 5px 0 20px;
            border: 1px solid #ccc;
            border-radius: 5px;
        }
        form input[type="submit"] {
            background-color: #4CAF50;
            color: white;
            font-weight: bold;
            cursor: pointer;
        }
        form input[type="submit"]:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>Add New Account</h2>
        <form action="insert_account.php" method="POST">
            <label for="cid">Customer ID:</label>
            <input type="number" id="cid" name="cid" required>

            <label for="atype">Account Type (S for Savings, C for Current):</label>
            <input type="text" id="atype" name="atype" maxlength="1" required>

            <label for="balance">Initial Balance:</label>
            <input type="number" id="balance" name="balance" required>

            <input type="submit" value="Add Account">
        </form>
    </div>
</body>
</html>
