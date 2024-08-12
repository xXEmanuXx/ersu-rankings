<?php
    function buildData($record) {
        $string = "";

        $string .= $record->id . ",";
        if (strlen($record->isee) > 0) {
            $string .= $record->isee . ",";
        }
        else {
            $string .= "\N,";
        }
        if (strlen($record->ispe) > 0) {
            $string .= $record->ispe . ",";
        }
        else {
            $string .= "\N,";
        }
        $string .= $record->score . ",";
        if (strlen($record->notes) > 0) {
            $string .= $record->notes;
        }
        else {
            $string .= "\N";
        }
        $string .= "\n";

        return $string;
    }
?>

<?php
    require "record.php";

    $file = fopen("/data/first_years.txt", "r");
    //$file = fopen("../../data/first_years.txt", "r");
    if ($file == false) {
        echo "Error on file opening\n";
        exit();
    }

    /* $bs = fopen("../../data/participant_fy_bs.csv", "w");
    $pl = fopen("../../data/participant_fy_pl.csv", "w"); */
    $bs = fopen("/data/participant_fy_bs.csv", "w");
    $pl = fopen("/data/participant_fy_pl.csv", "w");

    while ($string = fgets($file)) {
        $record = new Record($string);
        $record->calculateScore();

        $data = buildData($record);
        if (strlen($record->outcome_bs) > 0 && $record->outcome_bs[0] != 'n') {
            fwrite($bs, $data);
        }
        if (strlen($record->outcome_pl) > 0 && $record->outcome_pl[0] != 'n') {
            fwrite($pl, $data);
        }
    }

    fclose($file);
    fclose($bs);
    fclose($pl);

    require "../configdb.php";

    $conn->select_db("ranking_first_years_scholarship");
    $query = "LOAD DATA LOCAL INFILE '/data/participant_fy_bs.csv' INTO TABLE participant FIELDS TERMINATED BY ',' LINES TERMINATED BY '\n' (request_number, isee, ispe, score, notes)";
    //$query = "LOAD DATA INFILE 'D:/progetti/ersu-rankings/data/participant_fy_bs.csv' INTO TABLE participant FIELDS TERMINATED BY ',' LINES TERMINATED BY '\n' (request_number, isee, ispe, score, notes)";
    $conn->query($query);

    $conn->select_db("ranking_first_years_housing");
    $query = "LOAD DATA LOCAL INFILE '/data/participant_fy_pl.csv' INTO TABLE participant FIELDS TERMINATED BY ',' LINES TERMINATED BY '\n' (request_number, isee, ispe, score, notes)";
    //$query = "LOAD DATA INFILE 'D:/progetti/ersu-rankings/data/participant_fy_pl.csv' INTO TABLE participant FIELDS TERMINATED BY ',' LINES TERMINATED BY '\n' (request_number, isee, ispe, score, notes)";
    $conn->query($query);

    $conn->close();
    echo "Correct termination\n";
?>