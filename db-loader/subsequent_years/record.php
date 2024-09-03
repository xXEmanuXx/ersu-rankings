<?php
    $DELIM = "\t";
    $CFU_PER_YEAR = 60;
    $CFU_WEIGHT = 70;
    $AVERAGE_WEIGHT = 28;
    $HONORS_WEIGHT = 0.5;
    $MAX_AVERAGE = 30;

    class Record {
        public $id;
        public $cdl;
        public $type;
        public $year;
        public $cfu;
        public $average;
        public $honors;
        public $bonus;
        public $score;
        public $notes;
        public $isee;
        public $outcome_bs;
        public $outcome_pl;

        function __construct($string) {
            $string = substr($string, 0, strlen($string) - 1); // newline character "\n" is removed
            $tokens = explode($GLOBALS["DELIM"], $string);

            /*  $tokens contents
                tokens[0] = id, tokens[1] = type, tokens[2] = institute, tokens[3] = cdl, tokens[4] = year, tokens[5] = place, tokens[6] = cfu, tokens[7] = average, 
                tokens[8] = honors, tokens[9] = bonus, tokens[10] = isee, tokens[11] = ispe, tokens[12] = outcome_bs, tokens[13] = outcome_pl, tokens[14] = notes
            */
            if ($tokens[1] == "laurea triennale") {
                $tokens[1] = "triennale";
            }
            else if ($tokens[1] == "laurea magistrale") {
                $tokens[1] = "magistrale";
            }
            else if ($tokens[1] == "laurea magistrale a ciclo unico") {
                $tokens[1] = "ciclo unico";
            }
            else if ($tokens[1] == "corso di specializzazione") {
                $tokens[1] = "specializzazione";
            }

            if ($tokens[4][0] != 'F') {
                $tokens[4] = substr($tokens[4], 0, strlen($tokens[4]) - 1);
            }
            
            $this->id = $tokens[0];
            $this->cdl = $tokens[3];
            $this->type = $tokens[1];
            $this->year = $tokens[4];
            if (isset($tokens[6])) {
                $this->cfu = $tokens[6];
            }
            if (isset($tokens[7])) {
                $this->average = $tokens[7];
            }
            if (isset($tokens[8])) {
                $this->honors = $tokens[8];
            }
            $this->bonus = $tokens[9];
            if (isset($tokens[14])) {
                $this->notes = $tokens[14];
            }
            if (isset($tokens[10])) {
                $this->isee = $tokens[10];
            }
            if (isset($tokens[12])) {
                $this->outcome_bs = $tokens[12];
            }
            if (isset($tokens[13])) {
                $this->outcome_pl = $tokens[13];
            }
        }

        function calculateScore() {
            $expected_cfu;
            $total_years;
            $honors;

            if (is_numeric($this->year)) {
                $expected_cfu = ($this->year - 1) * $GLOBALS["CFU_PER_YEAR"];
            }
            else {
                switch ($this->type) {
                    case "triennale":
                        $total_years = 3;
                    case "magistrale":
                        $total_years = 2;
                    default:
                        if ($this->cdl == "medicina e chirurgia" || $this->cdl == "odontoiatria e protesi dentaria") {
                            $total_years = 6;
                        }
                        else {
                            $total_years = 5;
                        }
                }
                $expected_cfu = $total_years * $GLOBALS["CFU_PER_YEAR"]; 
            }

            if (strlen($this->honors) > 0) {
                if ($this->honors > 4) {
                    $honors = 4;
                }
                else {
                    $honors = $this->honors;
                }
            }
            else {
                $honors = 0;
            }

            if (strlen($this->cfu) > 0 && strlen($this->average) > 0) {
                $this->score = $GLOBALS["CFU_WEIGHT"] * ($this->cfu / $expected_cfu) + $GLOBALS["AVERAGE_WEIGHT"] * ($this->average / $GLOBALS["MAX_AVERAGE"]) + $GLOBALS["HONORS_WEIGHT"] * $honors;
            }
            else {
                $this->score = 0;
            }

        }
    }
?>