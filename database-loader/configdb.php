<?php
    $host = "host.docker.internal";
    $user = "emanu";
    $password = "balls123.";
    $db = "";

    $conn = new mysqli($host, $user, $password, $db);
    if ($conn->connect_error) {
        echo "Error connecting to database\n";
        exit();
    }
    $conn->options(MYSQLI_OPT_LOCAL_INFILE, true);
?>