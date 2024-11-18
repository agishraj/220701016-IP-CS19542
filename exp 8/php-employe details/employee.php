<?php
include 'db.php';

// Retrieve Employee Details
$sql = "SELECT EMPID, ENAME, DESIG, DEPT, DOJ, SALARY FROM EMPDETAILS";
$result = $conn->query($sql);

if ($result->num_rows > 0) {
    echo "<table border='1'>
            <tr>
                <th>EMPID</th>
                <th>Name</th>
                <th>Designation</th>
                <th>Department</th>
                <th>Date of Joining</th>
                <th>Salary</th>
                <th>Edit</th>
            </tr>";
    while($row = $result->fetch_assoc()) {
        echo "<tr>
                <td>".$row["EMPID"]."</td>
                <td>".$row["ENAME"]."</td>
                <td>".$row["DESIG"]."</td>
                <td>".$row["DEPT"]."</td>
                <td>".$row["DOJ"]."</td>
                <td>".$row["SALARY"]."</td>
                <td><a href='edit.php?id=".$row["EMPID"]."'>Edit</a></td>
              </tr>";
    }
    echo "</table>";
} else {
    echo "0 results";
}

$conn->close();
?>
