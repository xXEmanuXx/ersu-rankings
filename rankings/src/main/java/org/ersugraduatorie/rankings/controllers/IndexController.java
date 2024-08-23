package org.ersugraduatorie.rankings.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/en")
    public String index_en() {
        return "index-en";
    }

    // these are all tests

    // @PostMapping("/")
    // public String win() {
    //     return "index";
    // }

    // @GetMapping("/rankings_fy")
    // public String rankings_fy() {
    //     return "rankings_fy";
    // }

    // @GetMapping("/rankings_fy-en")
    // public String rankings_fy_en() {
    //     return "rankings_fy-en";
    // }

    // @GetMapping("/rankings_sy")
    // public String rankings_sy() {
    //     return "rankings_sy";
    // }

    // @GetMapping("/rankings_sy-en")
    // public String rankings_sy_en() {
    //     return "rankings_sy-en";
    // }

    // @GetMapping("/rankings_manual")
    // public String rankings_manual() {
    //     return "rankings_manual";
    // }

    // @GetMapping("/rankings_manual-en")
    // public String rankings_manual_en() {
    //     return "rankings_manual-en";
    // }
}
