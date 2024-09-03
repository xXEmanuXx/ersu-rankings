<?php
    function buildData($record, $cdl_id, $year_id) {
        $string = "";

        $string .= $record->id . ",";
        $string .= strlen($record->cfu) > 0 ? $record->cfu . "," : "\N,";
        $string .= strlen($record->average) > 0 ? $record->average . "," : "\N,";
        $string .= strlen($record->honors) > 0 ? $record->honors . "," : "\N,";
        $string .= $record->bonus . ",";
        $string .= $record->score . ",";
        $string .= strlen($record->notes) > 0 ? $record->notes . "," : "\N,";
        $string .= strlen($record->isee) > 0 ? $record->isee . "," : "\N,";
        $string .= strlen($record->outcome_bs) > 0 && ($record->outcome_bs[0] == 'i' || str_contains($record->outcome_bs, "omissioni")) ? "1," : "0,";
        $string .= strlen($record->outcome_pl) > 0 && ($record->outcome_pl[0] == 'i' || str_contains($record->outcome_pl, "omissioni")) ? "1," : "0,";
        

        /* if (strlen($record->cfu) > 0) {
            $string .= $record->cfu . ",";
        }
        else {
            $string .= "\N,";
        }

        if (strlen($record->average) > 0) {
            $string .= $record->average . ",";
        }
        else {
            $string .= "\N,";
        }

        if (strlen($record->honors) > 0) {
            $string .= $record->honors . ",";
        }
        else {
            $string .= "\N,";
        }

        $string .= $record->bonus . ",";
        $string .= $record->score . ",";
        
        if (strlen($record->notes) > 0) {
            $string .= $record->notes . ",";
        }
        else {
            $string .= "\N,";
        }

        if (strlen($record->isee) > 0) {
            $string .= $record->isee . ",";
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
            $string .= "1,";
        }
        else {
            $string .= "0,";
        } */

        $string .= $cdl_id . ",";
        $string .= $year_id . "\n";

        return $string;
    }
?>

<?php
    require "cdl_year.php";
    require "record.php";

    $file = fopen("/data/subsequent_years.txt", "r");
    if ($file == false) {
        echo "Error on file opening\n";
        exit();
    }

    $list = array();

    $year = fopen("/data/year.csv", "w");
    $cdl = fopen("/data/cdl.csv", "w");

    while ($string = fgets($file)) {
        $cdl_year = new cdl_year($string);
        
        // if cdl and year are encountered for the first time they are put in a list so there won't be redudant data

        if ((strlen($cdl_year->outcome_bs) > 0 && ($cdl_year->outcome_bs[0] == 'i' || str_contains($cdl_year->outcome_bs, "omissioni"))) ||
            (strlen($cdl_year->outcome_pl) > 0 && ($cdl_year->outcome_pl[0] == 'i' || str_contains($cdl_year->outcome_pl, "omissioni")))) 
        {
            if (!find($list, $cdl_year, $YEAR_ONLY)) {
                fwrite($year, "{$cdl_year->year}\n"); 
            }
            if (!find($list, $cdl_year, $CDL_ONLY)) {
                fwrite($cdl, "{$cdl_year->name},{$cdl_year->type}\n"); 
            }
            if (!find($list, $cdl_year, $CDL_AND_YEAR)) {
                array_push($list, $cdl_year); 
            } else {
                $index = search($list, $cdl_year);

                if (!(strlen($list[$index]->outcome_bs) > 0 && ($list[$index]->outcome_bs[0] == 'i' || str_contains($list[$index]->outcome_bs, "omissioni")))) {
                    if (strlen($cdl_year->outcome_bs) > 0 && ($cdl_year->outcome_bs[0] == 'i' || str_contains($cdl_year->outcome_bs, "omissioni"))) {
                        $list[$index]->outcome_bs = $cdl_year->outcome_bs;
                    }
                }
                if (!(strlen($list[$index]->outcome_pl) > 0 && ($list[$index]->outcome_pl[0] == 'i' || str_contains($list[$index]->outcome_pl, "omissioni")))) {
                    if (strlen($cdl_year->outcome_pl) > 0 && ($cdl_year->outcome_pl[0] == 'i' || str_contains($cdl_year->outcome_pl, "omissioni"))) {
                        $list[$index]->outcome_pl = $cdl_year->outcome_pl;
                    }
                }

            }
        }
    }

    fclose($year);
    fclose($cdl);

    require "../configdb.php";
    
    $query = "LOAD DATA INFILE '/data/year.csv' INTO TABLE year FIELDS TERMINATED BY ',' LINES TERMINATED BY '\n' (year)";
    $conn->query($query);
    
    $query = "LOAD DATA INFILE '/data/cdl.csv' INTO TABLE cdl FIELDS TERMINATED BY ',' LINES TERMINATED BY '\n' (name, type)";
    $conn->query($query);

    $cdl_year_file = fopen("/data/cdl_year.csv", "w");
    foreach($list as $cdl_year) {
        $query = "SELECT cdl.id AS cdl_id, year.id AS year_id FROM cdl, year WHERE name = ? AND type = ? AND year = ?";
        $stat = $conn->prepare($query);
        $stat->bind_param("sss", $cdl_year->name, $cdl_year->type, $cdl_year->year);
        $stat->execute();
        $result = $stat->get_result();
        $row = $result->fetch_assoc();
        
        $cdl_id = $row["cdl_id"];
        $year_id = $row["year_id"];

        $string = "$cdl_id,$year_id,";
        $string .= strlen($cdl_year->outcome_bs) > 0 && ($cdl_year->outcome_bs[0] == 'i' || str_contains($cdl_year->outcome_bs, "omissioni")) ? "1," : "0,";
        $string .= strlen($cdl_year->outcome_pl) > 0 && ($cdl_year->outcome_pl[0] == 'i' || str_contains($cdl_year->outcome_pl, "omissioni")) ? "1\n" : "0\n";
        /* if (strlen($cdl_year->outcome_bs) > 0 && ($cdl_year->outcome_bs[0] == 'i' || str_contains($cdl_year->outcome_bs, "omissioni"))) {
            $string .= "1,";
        }
        else {
            $string .= "0,";
        }
        if (strlen($cdl_year->outcome_pl) > 0 && ($cdl_year->outcome_pl[0] == 'i' || str_contains($cdl_year->outcome_pl, "omissioni"))) {
            $string .= "1\n";
        }
        else {
            $string .= "0\n";
        } */

        fwrite($cdl_year_file, $string);
    }

    fclose($cdl_year_file);

    $query = "LOAD DATA INFILE '/data/cdl_year.csv' INTO TABLE cdl_year FIELDS TERMINATED BY ',' LINES TERMINATED BY '\n'";
    $conn->query($query);
    
    rewind($file);

    $participant = fopen("/data/participant_sy.csv", "w");
    while ($string = fgets($file)) {
        $record = new Record($string);
        $record->calculateScore();

        if ((strlen($record->outcome_bs) > 0 && ($record->outcome_bs[0] == 'i' || str_contains($record->outcome_bs, "omissioni"))) ||
            (strlen($record->outcome_pl) > 0 && ($record->outcome_pl[0] == 'i' || str_contains($record->outcome_pl, "omissioni")))) 
        {
            $query = "SELECT cdl.id AS cdl_id, year.id AS year_id FROM cdl, year WHERE name = ? AND type = ? AND year = ?";
            $stat = $conn->prepare($query);
            $stat->bind_param("sss", $record->cdl, $record->type, $record->year);
            $stat->execute();
            $result = $stat->get_result();
            $row = $result->fetch_assoc();
            $cdl_id = $row["cdl_id"];
            $year_id = $row["year_id"];

            $data = buildData($record, $cdl_id, $year_id);
            fwrite($participant, $data);
        }
    }
    
    fclose($participant);
    fclose($file);
 
    $query = "LOAD DATA INFILE '/data/participant_sy.csv' INTO TABLE participant_sy FIELDS TERMINATED BY ',' LINES TERMINATED BY '\n' (request_number, cfu, average, honors, bonus, score, notes, isee, scholarship, accommodation, cdl, year)";
    $conn->query($query);
    $conn->close();

    echo "Correct termination\n";
?>