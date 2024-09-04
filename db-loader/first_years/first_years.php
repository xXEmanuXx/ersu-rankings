<?php
    function buildData($record) {
        $string = "";
        
        $string .= $record->id . ",";
        $string .= strlen($record->isee) > 0 ? $record->isee . "," : "\N,";
        $string .= strlen($record->ispe) > 0 ? $record->ispe . "," : "\N,";
        $string .= $record->score . ",";
        $string .= strlen($record->notes) > 0 ? $record->notes . "," : "\N,";
        $string .= strlen($record->outcome_bs) > 0 && ($record->outcome_bs[0] == 'i' || str_contains($record->outcome_bs, "omissioni")) ? "1," : "0,";
        $string .= strlen($record->outcome_pl) > 0 && ($record->outcome_pl[0] == 'i' || str_contains($record->outcome_pl, "omissioni")) ? "1" : "0";

        /* if (strlen($record->isee) > 0) {
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
            $string .= $record->notes . ",";
        }
        else {
            $string .= "\N,";
        }

        if (strlen($record->outcome_bs) > 0 && ($record->outcome_bs[0] == 'i' || str_contains($record->outcome_bs, "omissioni"))) {
            $string .= "1,";
        }
        else {
            $string .= "0,";
        }

        if (strlen($record->outcome_pl) > 0 && ($record->outcome_pl[0] == 'i' || str_contains($record->outcome_pl, "omissioni"))) {
            $string .= "1";
        }
        else {
            $string .= "0";
        } */

        $string .= "\n";
        return $string;
    }
?>

<?php
    require "record.php";

    $file = fopen("/data/first_years.txt", "r");
    if ($file == false) {
        echo "Error on file opening\n";
        exit();
    }

    $participant = fopen("/data/participant_fy.csv", "w");

    while ($string = fgets($file)) {
        $record = new Record($string);
        $record->calculateScore();

        if ((strlen($record->outcome_bs) > 0 && ($record->outcome_bs[0] == 'i' || str_contains($record->outcome_bs, "omissioni"))) || 
            (strlen($record->outcome_pl) > 0 && ($record->outcome_pl[0] == 'i' || str_contains($record->outcome_pl, "omissioni")))) 
        {
            $data = buildData($record);
            fwrite($participant, $data);
        }
    }

    fclose($file);
    fclose($participant);

    require "../configdb.php";

    $query = "LOAD DATA INFILE '/data/participant_fy.csv' INTO TABLE participant_fy FIELDS TERMINATED BY ',' LINES TERMINATED BY '\n' (request_number, isee, ispe, score, notes, scholarship, accommodation)";
    $conn->query($query);

    $conn->close();
    echo "Correct termination\n";
?>