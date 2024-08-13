<?php
    $host = getenv('DB_HOST');
    $user = trim(file_get_contents(getenv('DB_USER_FILE')));
    $password = trim(file_get_contents(getenv('DB_PASSWORD_FILE')));
    $db = "rankings";

    $conn = new mysqli($host, $user, $password, $db);
    if ($conn->connect_error) {
        echo "Error connecting to database\n";
        exit();
    }
?>