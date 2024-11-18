<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>View Customers</title>
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
        <h2>Customer Information</h2>
        <table>
            <thead>
                <tr>
                    <th>Customer ID</th>
                    <th>Customer Name</th>
                </tr>
            </thead>
            <tbody>
                <?php
                include 'db_connect.php';

                $sql = "SELECT * FROM CUSTOMER";
                $result = $conn->query($sql);

                if ($result->num_rows > 0) {
                    while($row = $result->fetch_assoc()) {
                        echo "<tr>
                                <td>{$row['CID']}</td>
                                <td>{$row['CNAME']}</td>
                            </tr>";
                    }
                } else {
                    echo "<tr><td colspan='2'>No customers found</td></tr>";
                }

                $conn->close();
                ?>
            </tbody>
        </table>
    </div>
</body>
</html>
