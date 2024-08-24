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
}
