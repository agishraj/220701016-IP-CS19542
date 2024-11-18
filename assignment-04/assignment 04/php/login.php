<?php



include 'db.php';


header("Access-Control-Allow-Origin: *"); // Allow all origins or specify your domain
header("Access-Control-Allow-Methods: POST, GET, OPTIONS"); // Allow specific methods
header("Access-Control-Allow-Headers: Content-Type"); // Allow specific headers



if ($_SERVER["REQUEST_METHOD"] == "POST") {
    // Retrieve username and password
    $username = $_POST['username'];
    $password = $_POST['password'];

    // Prepare SQL statement
    $stmt = $pdo->prepare("SELECT * FROM players WHERE username = ? AND password = ?");
    $stmt->execute([$username, $password]);
    $user = $stmt->fetch();

    if ($user) {
        session_start();
        $_SESSION['user_id'] = $user['player_id']; // Use 'player_id' instead of 'id'
        $_SESSION['username'] = $user['username'];
        echo json_encode(['status' => 'success', 'redirect' => 'game.html']);
        exit();
    } else {
        echo json_encode(['status' => 'error', 'message' => 'Invalid username or password.']);
    }
} else {
    http_response_code(405); // Method Not Allowed
    echo json_encode(['status' => 'error', 'message' => 'Method Not Allowed']);
}
?>
