<?php
    $DELIM = "\t";
    $YEAR_ONLY = 0;
    $CDL_ONLY = 1;
    $CDL_AND_YEAR = 2;

    class cdl_year {
        public $name;
        public $type;
        public $year;
        public $outcome_bs;
        public $outcome_pl;

        function __construct($string) {
            $string = substr($string, 0, strlen($string) - 2);
            $tokens = explode($GLOBALS["DELIM"], $string);

            /*  $tokens contents
                tokens[0] = id, tokens[1] = type, tokens[2] = institute, tokens[3] = cdl, tokens[4] = anno, tokens[5] = place, tokens[6] = cfu, tokens[7] = average, 
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

            $this->name = $tokens[3];
            $this->type = $tokens[1];
            $this->year = $tokens[4];
            $this->outcome_bs = $tokens[12];
            $this->outcome_pl = $tokens[13];
        }
    };

    function find($array, $c, $mode) {
        switch ($mode) {
            case $GLOBALS["YEAR_ONLY"]:
                foreach ($array as $elem) {
                    if ($elem->year == $c->year) {
                        return true;
                    }
                }
                break;
            case $GLOBALS["CDL_ONLY"];
                foreach ($array as $elem) {
                    if ($elem->name == $c->name && $elem->type == $c->type) {
                        return true;
                    }
                }
                break;
            case $GLOBALS["CDL_AND_YEAR"];
                foreach ($array as $elem) {
                    if ($elem->name == $c->name && $elem->type == $c->type && $elem->year == $c->year) {
                        return true;
                    }
                }
            default:
                break;
        }
        return false;
    }
?>