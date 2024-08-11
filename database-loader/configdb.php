<?php
    $host = "localhost";
    $user = "emanu";
    $password = "balls123.";
    $db = "";

    $conn = new mysqli($host, $user, $password, $db);
    if ($conn->connect_error) {
        echo "Error connecting to database\n";
        exit();
    }
?>