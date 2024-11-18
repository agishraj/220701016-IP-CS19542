<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>View Accounts</title>
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
        table {
            width: 100%;
            border-collapse: collapse;
            margin: 20px 0;
        }
        table, th, td {
            border: 1px solid #ddd;
        }
        table th, table td {
            padding: 10px;
            text-align: left;
        }
        table th {
            background-color: #4CAF50;
            color: white;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>Account Information</h2>
        <table>
            <thead>
                <tr>
                    <th>Customer ID</th>
                    <th>Customer Name</th>
                    <th>Account Number</th>
                    <th>Account Type</th>
                    <th>Balance</th>
                </tr>
            </thead>
            <tbody>
                <?php
                include 'db_connect.php';

                $sql = "SELECT A.ANO, A.ATYPE, A.BALANCE, C.CID, C.CNAME FROM ACCOUNT A INNER JOIN CUSTOMER C ON A.CID = C.CID";
                $result = $conn->query($sql);

                if ($result->num_rows > 0) {
                    while($row = $result->fetch_assoc()) {
                        echo "<tr>
                                <td>{$row['CID']}</td>
                                <td>{$row['CNAME']}</td>
                                <td>{$row['ANO']}</td>
                                <td>{$row['ATYPE']}</td>
                                <td>{$row['BALANCE']}</td>
                            </tr>";
                    }
                } else {
                    echo "<tr><td colspan='5'>No accounts found</td></tr>";
                }

                $conn->close();
                ?>
            </tbody>
        </table>
    </div>
</body>
</html>
