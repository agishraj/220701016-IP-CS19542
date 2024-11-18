<?php
include 'db.php';

// Get Employee ID from the URL
$empid = $_GET['id'];

// Retrieve the employee data based on EMPID
$sql = "SELECT * FROM EMPDETAILS WHERE EMPID = $empid";
$result = $conn->query($sql);
$row = $result->fetch_assoc();

// Handle form submission to update the data
if ($_SERVER["REQUEST_METHOD"] == "POST") {
    $ename = $_POST["ename"];
    $desig = $_POST["desig"];
    $dept = $_POST["dept"];
    $doj = $_POST["doj"];
    $salary = $_POST["salary"];

    // Update query
    $update_sql = "UPDATE EMPDETAILS SET ENAME='$ename', DESIG='$desig', DEPT='$dept', DOJ='$doj', SALARY='$salary' WHERE EMPID='$empid'";
    if ($conn->query($update_sql) === TRUE) {
        echo "Record updated successfully. <a href='employee.php'>Go Back</a>";
    } else {
        echo "Error updating record: " . $conn->error;
    }
}
?>

<!-- HTML Form for Editing -->
<h2>Edit Employee Details</h2>
<form method="post">
    Name: <input type="text" name="ename" value="<?php echo $row['ENAME']; ?>"><br><br>
    Designation: <input type="text" name="desig" value="<?php echo $row['DESIG']; ?>"><br><br>
    Department: <input type="text" name="dept" value="<?php echo $row['DEPT']; ?>"><br><br>
    Date of Joining: <input type="date" name="doj" value="<?php echo $row['DOJ']; ?>"><br><br>
    Salary: <input type="text" name="salary" value="<?php echo $row['SALARY']; ?>"><br><br>
    <input type="submit" value="Update">
</form>
