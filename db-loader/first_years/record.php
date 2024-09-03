<?php
    $DELIM = "\t";
    $LIMIT_ISEE = 24335.11; // might change every year
    
    class Record {
        public $id;
        public $isee;
        public $ispe;
        public $score;
        public $notes;
        public $outcome_bs;
        public $outcome_pl;

        function __construct($string) {
            $string = substr($string, 0, strlen($string) - 2); // carriage-return and newline characters "\r\n" are removed 
            $tokens = explode($GLOBALS["DELIM"], $string);

            /*  $tokens contents
            tokens[0] = id, tokens[1] = type, tokens[2] = institute, tokens[3] = place, tokens[4] = isee, 
            tokens[5] = ispe, tokens[6] = outcome_bs, tokens[7] = outcome_pl, tokens[8] = notes
            */ 

            $this->id = $tokens[0];
            if (isset($tokens[4])) {
                $this->isee = $tokens[4];
            }
            if (isset($tokens[5])) {
                $this->ispe = $tokens[5];
            }
            if (isset($tokens[8])) {
                $this->notes = $tokens[8];
            }
            if (isset($tokens[6])) {
                $this->outcome_bs = $tokens[6];
            }
            if (isset($tokens[7])) {
                $this->outcome_pl = $tokens[7];
            }
        }

        function calculateScore() {
            if (strlen($this->isee) > 0) {
                $this->score = 100 * (1 - $this->isee / $GLOBALS["LIMIT_ISEE"]);
            }
            else {
                $this->score = 0;
            }
        }
    };
?>