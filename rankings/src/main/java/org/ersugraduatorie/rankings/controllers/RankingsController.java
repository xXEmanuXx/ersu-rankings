package org.ersugraduatorie.rankings.controllers;

import org.ersugraduatorie.rankings.services.RankingsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/rankings")
public class RankingsController {

    private final RankingsService rankingsService;

    private boolean isValidLang(String lang) {
        return "it".equals(lang) || "en".equals(lang);
    }

    private boolean isValidRequestType(String requestType) {
        return "bs".equals(requestType) || "pl".equals(requestType);
    }

    private boolean isValidYearType(String yearType) {
        return "fy".equals(yearType) || "sy".equals(yearType);
    }

    private String resolveViewName(String baseViewName, String lang) {
        return "it".equals(lang) ? baseViewName : baseViewName + "-en";
    }

    public RankingsController(RankingsService rankingsService) {
        this.rankingsService = rankingsService;
    }

    @PostMapping("/request_number")
    public String rankingsRequestNumber(@RequestParam(name = "lang") String lang, 
                                        @RequestParam(name = "request-type") String requestType,
                                        @RequestParam(name = "request-number") String requestNumber,
                                        Model model) {

        if (!isValidLang(lang) || !isValidRequestType(requestType)) {
            return "redirect:/error";
        }

        model.addAttribute("requestNumber", requestNumber);

        if (rankingsService.populateFirstYearsParticipants(requestType, requestNumber, model)) {
            return resolveViewName("rankings_fy", lang);
        }

        if (rankingsService.populateSubsequentYearsParticipants(requestType, requestNumber, model)) {
            return resolveViewName("rankings_sy", lang);
        }

        return "redirect:/error";
    }

    @PostMapping("/degree")
    public String rankingsDegree(@RequestParam(name = "lang") String lang,
                                 @RequestParam(name = "request-type") String requestType,
                                 @RequestParam(name = "year-type") String yearType,
                                 @RequestParam(name = "cdl-name", required = false) String cdlName,
                                 @RequestParam(name = "cdl-type", required = false) String cdlType,
                                 @RequestParam(name = "year", required = false) String year,
                                 Model model) {

        if (!isValidLang(lang) || !isValidRequestType(requestType) || !isValidYearType(yearType)) {
            return "redirect:/error";
        }

        // ricerca primi anni
        if ("fy".equals(yearType)) {
            rankingsService.populateFirstYearsParticipants(requestType, model);
            return resolveViewName("rankings_fy", lang);
        }

        // ricerca anni successivi
        if (rankingsService.populateSubsequentYearsParticipants(requestType, cdlName, cdlType, year, model)) {
            return resolveViewName("rankings_sy", lang);
        }

        return "redirect:/error";
    }

    @PostMapping("/manual")
    public String rankingsManual(@RequestParam(name = "lang") String lang,
                                 @RequestParam(name = "request-type") String requestType,
                                 @RequestParam(name = "year-type") String yearType,
                                 @RequestParam(name = "cdl-id", required = false) Long cdlId,
                                 @RequestParam(name = "year-id", required = false) Long yearId,
                                 Model model) {

        if (!isValidLang(lang) || !isValidRequestType(requestType) || !isValidYearType(yearType)) {
            return "redirect:/error";
        }

        model.addAttribute("lang", lang);
        model.addAttribute("requestType", requestType);
        model.addAttribute("yearType", yearType);

        if ("fy".equals(yearType)) {
            rankingsService.populateFirstYearsParticipants(requestType, model);
            return resolveViewName("rankings_fy", lang);
        }

        if (rankingsService.populateCdls(requestType, cdlId, yearId, model)) {
            return resolveViewName("rankings_manual_cdl", lang);
        }

        if (rankingsService.populateYears(requestType, cdlId, yearId, model)) {
            return resolveViewName("rankings_manual_year", lang);
        }

        if (rankingsService.populateSubsequentYearsParticipants(requestType, cdlId, yearId, model)) {
            return resolveViewName("rankings_sy", lang);
        }

        return "redirect:/error";
    }
}
