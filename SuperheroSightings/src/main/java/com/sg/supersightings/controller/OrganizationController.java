/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.supersightings.controller;

import com.sg.supersightings.model.OrgJsonResponse;
import com.sg.supersightings.model.Organization;
import com.sg.supersightings.model.Super;
import com.sg.supersightings.servicelayer.OrganizationServiceLayer;
import com.sg.supersightings.servicelayer.SuperServiceLayer;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author DavidKing
 */
@Controller
public class OrganizationController {

    SuperServiceLayer superService;
    OrganizationServiceLayer orgService;
    
    @Inject
    public OrganizationController(SuperServiceLayer superService,
    OrganizationServiceLayer orgService) {
        this.superService = superService;
        this.orgService = orgService;
    }

    @RequestMapping(value = "/displayOrganizationPage", method = RequestMethod.GET)
    public String dipslayOrganizationPage(Model model) {

        List<Organization> orgList = orgService.getAllOrganizations();

        model.addAttribute("orgList", orgList);

        return "organizations";
    }

    @PostMapping(value = "/organizations/createOrganization", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public OrgJsonResponse createOrganization(@RequestBody @Valid @ModelAttribute("organization") Organization org, BindingResult result, Model model) {
        OrgJsonResponse response = new OrgJsonResponse();

        if (result.hasErrors()) {
            Map<String, String> errors = result.getFieldErrors().stream()
                    .collect(
                            Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage)
                    );

            response.setValidated(false);
            response.setErrorMessages(errors);
            model.addAttribute("organization", response);
        } else {

            orgService.addOrganization(org);
            response.setValidated(true);
            response.setOrg(org);
            model.addAttribute("organization", org);
        }
        return response;

    }

    @RequestMapping(value = "/displayOrganizationDetails", method = RequestMethod.GET)
    public String displayOrganizationDetails(@RequestParam("organizationId") String oId, Model model) {

        int orgId = Integer.parseInt(oId);

        Organization orgs = orgService.getOrganizationById(orgId);

        model.addAttribute("organizations", orgs);

        List<Super> supers = superService.findSuperForOrganization(orgService.getOrganizationById(orgId));
        model.addAttribute("supers", supers);

        return "organizationDetails";
    }

    @RequestMapping(value = "/deleteOrganization", method = RequestMethod.GET)
    public String deleteOrganization(@RequestParam("organizationId") String orgId) {
        int organizationId = Integer.parseInt(orgId);
        orgService.deleteOrganization(organizationId);

        return "redirect:displayOrganizationPage";
    }

    @RequestMapping(value = "/displayEditOrganizationForm", method = RequestMethod.GET)
    public String displayEditSuperForm(@RequestParam("organizationId") String orgId, Model model) {
        int organizationId = Integer.parseInt(orgId);
        Organization organization = orgService.getOrganizationById(organizationId);
        model.addAttribute("organization", organization);
        return "editOrganizationForm";
    }

    @RequestMapping(value = "/editOrganization", method = RequestMethod.POST)
    public String editOrganization(@Valid @ModelAttribute("organization") Organization organization, BindingResult result) {
        if (result.hasErrors()) {
            return "editOrganizationForm";
        }
        orgService.updateOrganization(organization);
        return "redirect:displayOrganizationPage";
    }

}
