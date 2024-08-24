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

    private boolean isValidRequestType(String requestType) {
        return "bs".equals(requestType) || "pl".equals(requestType);
    }

    private List<String> getCdlNames(String requestType) {
        return "bs".equals(requestType) ? cdlRepository.findCdlNamesWithScholarship() : cdlRepository.findCdlNamesWithAccommodation();
    }

    private List<String> getCdlTypes(String requestType, String cdlName) {
        return "bs".equals(requestType) ? cdlRepository.findCdlTypesWithScholarship(cdlName) : cdlRepository.findCdlTypesWithAccommodation(cdlName);
    }

    private List<String> getYears(String requestType, String cdlName, String cdlTypes) {
        return "bs".equals(requestType) ? yearRepository.findYearsWithScholarship(cdlName, cdlTypes) : yearRepository.findYearsWithAccommodation(cdlName, cdlTypes);
    }

    public ApiController(CdlRepository cdlRepository, YearRepository yearRepository) {
        this.cdlRepository = cdlRepository;
        this.yearRepository = yearRepository;
    }

    @PostMapping("/getCdlName")
    public List<String> getCdlName(@RequestBody CdlNameRequestDTO data) {
        List<String> options = null;
        
        String requestType = data.getRequestType();

        if (isValidRequestType(requestType)) {
            options = getCdlNames(requestType);
        }

        return options;
    }

    @PostMapping("/getCdlType")
    public List<String> getCdlType(@RequestBody CdlTypeRequestDTO data) {
        List<String> options = null;

        String requestType = data.getRequestType();
        String cdlName = data.getCdlName();

        if (isValidRequestType(requestType)) {
            if (cdlName != null) {
                options = getCdlTypes(requestType, cdlName);
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

        if (isValidRequestType(requestType)) {
            if (cdlName != null && cdlType != null) {
                options = getYears(requestType, cdlName, cdlType);
            }
        }

        return options;
    }
}
