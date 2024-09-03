package org.ersugraduatorie.rankings.services;

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
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public class RankingsService {

    private final CdlRepository cdlRepository;
    private final YearRepository yearRepository;
    private final ParticipantFyRepository participantFyRepository;
    private final ParticipantSyRepository participantSyRepository;

    private List<ParticipantFy> getFirstYearsParticipants(String requestType) {
        return "bs".equals(requestType) ? participantFyRepository.findAllWithScholarship() : participantFyRepository.findAllWithAccommodation();
    }

    private List<ParticipantFy> getFirstYearsParticipants(String requestType, String requestNumber) {
        return "bs".equals(requestType) ? participantFyRepository.findAllWithScholarshipUsingRequestNumber(requestNumber) : participantFyRepository.findAllWithAccommodationUsingRequestNumber(requestNumber); 
    }

    private List<ParticipantSy> getSubsequentYearsParticipants(String requestType, String requestNumber) {
        return "bs".equals(requestType) ? participantSyRepository.findAllWithScholarshipUsingRequestNumber(requestNumber) : participantSyRepository.findAllWithAccommodationUsingRequestNumber(requestNumber);
    }

    private List<ParticipantSy> getSubsequentYearsParticipants(String requestType, String cdlName, String cdlType, String year) {
        return "bs".equals(requestType) ? participantSyRepository.findAllWithScholarshipUsingDegree(cdlName, cdlType, year) : participantSyRepository.findAllWithAccommodationUsingDegree(cdlName, cdlType, year);
    }

    private List<ParticipantSy> getSubsequentYearsParticipants(String requestType, Long cdlId, Long yearId) {
        return "bs".equals(requestType) ? participantSyRepository.findAllWithScholarshipUsingManual(cdlId, yearId) : participantSyRepository.findAllWithAccommodationUsingManual(cdlId, yearId);
    }

    private Optional<Cdl> getCdl(Long cdlId) {
        return cdlRepository.findById(cdlId);
    }

    private Optional<Cdl> getCdlOfParticipant(String requestNumber) {
        return cdlRepository.findCdlOfParticipant(requestNumber);
    }

    private List<Cdl> getCdlList(String requestType) {
        return "bs".equals(requestType) ? cdlRepository.findAllWithScholarship() : cdlRepository.findAllWithAccommodation();
    }
    
    private Optional<Year> getYear(Long yearId) {
        return yearRepository.findById(yearId);
    }

    private Optional<Year> getYearOfParticipant(String requestNumber) {
        return yearRepository.findYearOfParticipant(requestNumber);
    }

    private List<Year> getYearList(String requestType, Long cdlId) {
        return "bs".equals(requestType) ? yearRepository.findAllWithScholarship(cdlId) : yearRepository.findAllWithAccommodation(cdlId);
    }

    public RankingsService(CdlRepository cdlRepository, YearRepository yearRepository, ParticipantFyRepository participantFyRepository, ParticipantSyRepository participantSyRepository) {
        this.cdlRepository = cdlRepository;
        this.yearRepository = yearRepository;
        this.participantFyRepository = participantFyRepository;
        this.participantSyRepository = participantSyRepository;
    }

    public boolean populateFirstYearsParticipants(String requestType, String requestNumber, Model model) {
        List<ParticipantFy> participants = getFirstYearsParticipants(requestType, requestNumber);
        if (!participants.isEmpty()) {
            model.addAttribute("participants", participants);
            return true;
        }

        return false;
    }

    public void populateFirstYearsParticipants(String requestType, Model model) {
        List<ParticipantFy> participants = getFirstYearsParticipants(requestType);
        model.addAttribute("participants", participants);
    }

    public boolean populateSubsequentYearsParticipants(String requestType, String requestNumber, Model model) {
        List<ParticipantSy> participants = getSubsequentYearsParticipants(requestType, requestNumber);
        if (!participants.isEmpty()) {

            Optional<Cdl> cdl = getCdlOfParticipant(requestNumber);
            Optional<Year> year = getYearOfParticipant(requestNumber);

            if (cdl.isEmpty() || year.isEmpty()) {
                return false;
            }

            model.addAttribute("participants", participants);
            model.addAttribute("cdl", cdl.get());
            model.addAttribute("year", year.get());

            return true;
        }

        return false;
    }

    public boolean populateSubsequentYearsParticipants(String requestType, String cdlName, String cdlType, String year, Model model) {
        if (cdlName != null && cdlType != null && year != null) {
            List<ParticipantSy> participants = getSubsequentYearsParticipants(requestType, cdlName, cdlType, year);
            if (!participants.isEmpty()) {
                model.addAttribute("participants", participants);
                model.addAttribute("cdl", new Cdl(cdlName, cdlType));
                model.addAttribute("year", new Year(year));
                return true;
            }
        }
        
        return false;
    }

    public boolean populateSubsequentYearsParticipants(String requestType, Long cdlId, Long yearId, Model model) {
        if (cdlId != null && yearId != null) {
            List<ParticipantSy> participants = getSubsequentYearsParticipants(requestType, cdlId, yearId);
            if (!participants.isEmpty()) {
                
                Optional<Cdl> cdl = getCdl(cdlId);
                Optional<Year> year = getYear(yearId);
    
                if (cdl.isEmpty() || year.isEmpty()) {
                    return false;
                }
                
                model.addAttribute("participants", participants);
                model.addAttribute("cdl", cdl.get());
                model.addAttribute("year", year.get());
    
                return true;
            }
        }

        return false;
    }

    public boolean populateCdls(String requestType, Long cdlId, Long yearId, Model model) {
        if (cdlId == null && yearId == null) {
            List<Cdl> cdls = getCdlList(requestType);
            if (!cdls.isEmpty()) {
                model.addAttribute("cdls", cdls);
                
                return true;
            }
        }

        return false;
    }

    public boolean populateYears(String requestType, Long cdlId, Long yearId, Model model) {
        if (cdlId != null && yearId == null) {
            List<Year> years = getYearList(requestType, cdlId);
            if (!years.isEmpty()) {
                model.addAttribute("years", years);
                model.addAttribute("cdlId", cdlId);
                
                return true;
            }
        }

        return false;
    }
}