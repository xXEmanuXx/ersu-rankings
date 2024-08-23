package org.ersugraduatorie.rankings.controllers;

import java.util.List;

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
                participants = participantSyRepository.findAllWithScholarship(participantId);
            }
            else {
                participants = participantSyRepository.findAllWithAccommodation(participantId);
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
}
