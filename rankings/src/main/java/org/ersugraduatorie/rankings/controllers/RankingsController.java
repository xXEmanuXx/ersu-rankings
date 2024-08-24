package org.ersugraduatorie.rankings.controllers;

import java.util.List;
import java.util.Optional;

import org.ersugraduatorie.rankings.models.Cdl;
import org.ersugraduatorie.rankings.models.ParticipantFy;
import org.ersugraduatorie.rankings.models.ParticipantSy;
import org.ersugraduatorie.rankings.models.Year;
import org.ersugraduatorie.rankings.repositories.CdlRepository;
import org.ersugraduatorie.rankings.repositories.ParticipantFyRepository;
import org.ersugraduatorie.rankings.repositories.ParticipantSyRepository;
import org.ersugraduatorie.rankings.repositories.YearRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/rankings")
public class RankingsController {

    private final ParticipantFyRepository participantFyRepository;
    private final ParticipantSyRepository participantSyRepository;
    private final CdlRepository cdlRepository;
    private final YearRepository yearRepository;

    public RankingsController(ParticipantFyRepository participantFyRepository, ParticipantSyRepository participantSyRepository, CdlRepository cdlRepository, YearRepository yearRepository) {
        this.participantFyRepository = participantFyRepository;
        this.participantSyRepository = participantSyRepository;
        this.cdlRepository = cdlRepository;
        this.yearRepository = yearRepository;
    }
    
    @PostMapping("/request_number")
    public String rankingsRequestNumber(@RequestParam(name = "lang") String lang, 
                                        @RequestParam(name = "request-type") String requestType,
                                        @RequestParam(name = "request-number") String requestNumber,
                                        Model model) {

        if (!(lang.equals("it") || lang.equals("en")) || !(requestType.equals("bs") || requestType.equals("pl"))) {
            return "redirect:/error";
        }

        Long participantId;

        model.addAttribute("requestNumber", requestNumber);

        // fy
        participantId = participantFyRepository.findParticipantId(requestNumber);
        if (participantId != null) {
            List<ParticipantFy> participants;
            if (requestType.equals("bs")) {
                participants = participantFyRepository.findAllWithScholarship();
            }
            else {
                participants = participantFyRepository.findAllWithAccommodation();
            }
            
            model.addAttribute("participants", participants);

            if (lang.equals("it")) {
                return "rankings_fy";
            }
            else {
                return "rankings_fy-en";
            }
        }

        // sy
        participantId = participantSyRepository.findParticipantId(requestNumber);
        if (participantId != null) {
            List<ParticipantSy> participants;
            if (requestType.equals("bs")) {
                participants = participantSyRepository.findAllWithScholarshipUsingParticipantId(participantId);
            }
            else {
                participants = participantSyRepository.findAllWithAccommodationUsingParticipantId(participantId);
            }

            Cdl cdl = cdlRepository.findCdlOfParticipant(participantId);
            Year year = yearRepository.findYearOfParticipant(participantId);
            
            model.addAttribute("participants", participants);
            model.addAttribute("cdl", cdl);
            model.addAttribute("year", year);

            if (lang.equals("it")) {
                return "rankings_sy";
            }
            else {
                return "rankings_sy-en";
            }
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

        if (!(lang.equals("it") || lang.equals("en")) || !(requestType.equals("bs") || requestType.equals("pl")) || !(yearType.equals("fy") || yearType.equals("sy"))) {
            return "redirect:/error";
        }

        // ricerca primi anni
        if (yearType.equals("fy")) {
            List<ParticipantFy> participants;
            if (requestType.equals("bs")) {
                participants = participantFyRepository.findAllWithScholarship();
            }
            else {
                participants = participantFyRepository.findAllWithAccommodation();
            }

            model.addAttribute("participants", participants);

            if (lang.equals("it")) {
                return "rankings_fy";
            }
            else {
                return "rankings_fy-en";
            }
        }

        // ricerca anni successivi
        if (cdlName != null && cdlType != null && year != null) {
            List<ParticipantSy> participants;
            if (requestType.equals("bs")) {
                participants = participantSyRepository.findAllWithScholarshipUsingDegree(cdlName, cdlType, year);
            }
            else {
                participants = participantSyRepository.findAllWithAccommodationUsingDegree(cdlName, cdlType, year);
            }
    
            if (participants.isEmpty()) {
                return "redirect:/error";
            }
    
            model.addAttribute("participants", participants);
    
            Cdl cdl = new Cdl();
            cdl.setName(cdlName);
            cdl.setType(cdlType);
            model.addAttribute("cdl", cdl);
    
            Year yearModel = new Year();
            yearModel.setYear(year);
            model.addAttribute("year", yearModel);
    
            if (lang.equals("it")) {
                return "rankings_sy";
            }
            else {
                return "rankings_sy-en";
            }
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

        if (!(lang.equals("it") || lang.equals("en")) || !(requestType.equals("bs") || requestType.equals("pl")) || !(yearType.equals("fy") || yearType.equals("sy"))) {
            return "redirect:/error";
        }

        if (yearType.equals("fy")) {
            List<ParticipantFy> participants;
            if (requestType.equals("bs")) {
                participants = participantFyRepository.findAllWithScholarship();
            }
            else {
                participants = participantFyRepository.findAllWithAccommodation();
            }

            model.addAttribute("participants", participants);

            if (lang.equals("it")) {
                return "rankings_fy";
            }
            else {
                return "rankings_fy-en";
            }
        }

        if (cdlId == null && yearId == null) {
            List<Cdl> cdls;
            if (requestType.equals("bs")) {
                cdls = cdlRepository.findAllWithScholarship();
            }
            else {
                cdls = cdlRepository.findAllWithAccommodation();
            }

            model.addAttribute("cdls", cdls);
            model.addAttribute("lang", lang);
            model.addAttribute("requestType", requestType);
            model.addAttribute("yearType", yearType);

            if (lang.equals("it")) {
                return "rankings_manual_cdl";
            }
            else {
                return "rankings_manual_cdl-en";
            }
        }

        if (cdlId != null && yearId == null) {
            List<Year> years;
            if (requestType.equals("bs")) {
                years = yearRepository.findAllWithScholarship(cdlId);
            }
            else {
                years = yearRepository.findAllWithAccommodation(cdlId);
            }

            model.addAttribute("years", years);
            model.addAttribute("lang", lang);
            model.addAttribute("requestType", requestType);
            model.addAttribute("yearType", yearType);
            model.addAttribute("cdlId", cdlId);

            if (lang.equals("it")) {
                return "rankings_manual_year";
            }
            else {
                return "rankings_manual_year-en";
            }
        }

        if (cdlId != null && yearId != null) {
            List<ParticipantSy> participants;
            if (requestType.equals("bs")) {
                participants = participantSyRepository.findAllWithScholarshipUsingIds(cdlId, yearId);
            }
            else {
                participants = participantSyRepository.findAllWithAccommodationUsingIds(cdlId, yearId);
            }
            
            model.addAttribute("participants", participants);
           
            Optional<Cdl> cdl;
            cdl = cdlRepository.findById(cdlId);
            if (cdl.isPresent()) {
                model.addAttribute("cdl", cdl.get());
            }

            Optional<Year> year;
            year = yearRepository.findById(yearId);
            if (year.isPresent()) {
                model.addAttribute("year", year.get());
            }

            if (lang.equals("it")) {
                return "rankings_sy";
            }
            else {
                return "rankings_sy-en";
            }
        }

        return "redirect:/error";
    }
}
