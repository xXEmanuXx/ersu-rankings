<?php
    $string = "2023000003	laurea triennale	universita degli studi di catania	catania	4424.89	0.00	idoneo		";
    $delim = "\t";

    $tokens = explode($delim, $string);
    var_dump($tokens);
?>