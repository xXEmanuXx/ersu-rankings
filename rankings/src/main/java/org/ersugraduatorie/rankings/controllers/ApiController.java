package org.ersugraduatorie.rankings.controllers;

import java.util.List;

import org.ersugraduatorie.rankings.models.CdlNameRequestDTO;
import org.ersugraduatorie.rankings.models.CdlTypeRequestDTO;
import org.ersugraduatorie.rankings.models.YearRequestDTO;
import org.ersugraduatorie.rankings.repositories.CdlRepository;
import org.ersugraduatorie.rankings.repositories.YearRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ApiController {
    
    private final CdlRepository cdlRepository;
    private final YearRepository yearRepository;

    public ApiController(CdlRepository cdlRepository, YearRepository yearRepository) {
        this.cdlRepository = cdlRepository;
        this.yearRepository = yearRepository;
    }

    @PostMapping("/getCdlName")
    public List<String> getCdlName(@RequestBody CdlNameRequestDTO data) {
        List<String> options = null;
        
        String requestType = data.getRequestType();
        if (requestType != null) {
            if (requestType.equals("bs")) {
                options = cdlRepository.findCdlNamesWithScholarship();
            }
            else if (requestType.equals("pl")) {
                options = cdlRepository.findCdlNamesWithAccommodation();
            }
        }

        return options;
    }

    @PostMapping("/getCdlType")
    public List<String> getCdlType(@RequestBody CdlTypeRequestDTO data) {
        List<String> options = null;

        String requestType = data.getRequestType();
        String cdlName = data.getCdlName();
        if (requestType != null && cdlName != null) {
            if (requestType.equals("bs")) {
                options = cdlRepository.findCdlTypesWithScholarship(cdlName);
            }
            else if (requestType.equals("pl")) {
                options = cdlRepository.findCdlTypesWithAccommodation(cdlName);
            }
        }

        return options;
    }

    @PostMapping("/getYear")
    public List<String> getYear(@RequestBody YearRequestDTO data) {
        List<String> options = null;

        String requestType = data.getRequestType();
        String cdlName = data.getCdlName();
        String cdlType = data.getCdlType();

        if (requestType != null && cdlName != null && cdlType != null) {
            Long cdlId = cdlRepository.cdlId(cdlName, cdlType);
            if (cdlId != null) {
                if (requestType.equals("bs")) {
                    options = yearRepository.findYearsWithScholarship(cdlId);
                }
                else if (requestType.equals("pl")) {
                    options = yearRepository.findYearsWithAccommodation(cdlId);
                }
            }
        }

        return options;
    }
}
