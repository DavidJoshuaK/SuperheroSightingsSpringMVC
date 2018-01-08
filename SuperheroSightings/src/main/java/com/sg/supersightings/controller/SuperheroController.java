/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.supersightings.controller;

import com.sg.supersightings.model.Location;
import com.sg.supersightings.model.Organization;
import com.sg.supersightings.model.Power;
import com.sg.supersightings.model.Sighting;
import com.sg.supersightings.model.Super;
import com.sg.supersightings.model.SuperJsonResponse;
import com.sg.supersightings.servicelayer.LocationServiceLayer;
import com.sg.supersightings.servicelayer.OrganizationServiceLayer;
import com.sg.supersightings.servicelayer.PowerServiceLayer;
import com.sg.supersightings.servicelayer.SightingServiceLayer;
import com.sg.supersightings.servicelayer.SuperServiceLayer;
import java.util.ArrayList;
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
public class SuperheroController {

    SuperServiceLayer superService;
    OrganizationServiceLayer orgService;
    PowerServiceLayer powerService;
    SightingServiceLayer sightingService;
    LocationServiceLayer locationService;

    @Inject
    public SuperheroController(SuperServiceLayer superService,
            OrganizationServiceLayer orgService,
            PowerServiceLayer powerService,
            SightingServiceLayer sightingService,
            LocationServiceLayer locationService) {
        this.superService = superService;
        this.locationService = locationService;
        this.orgService = orgService;
        this.powerService = powerService;
        this.sightingService = sightingService;
    }

    @RequestMapping(value = "/displaySuperheroPage", method = RequestMethod.GET)
    public String dipslaySuperheroPage(Model model) {
        List<Super> superList = superService.getAllSupers();

        model.addAttribute("superList", superList);

        return "supers";
    }

    @PostMapping(value = "/supers/createSuper", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public SuperJsonResponse createSuper(@RequestBody @Valid @ModelAttribute("supers") Super supers, BindingResult result, Model model) {

        SuperJsonResponse response = new SuperJsonResponse();

        if (result.hasErrors()) {

            Map<String, String> errors = result.getFieldErrors().stream()
                    .collect(
                            Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage)
                    );

            response.setValidated(false);
            response.setErrorMessages(errors);

            model.addAttribute("supers", response);
        } else {

            superService.addSuper(supers);
            response.setValidated(true);
            response.setSupers(supers);
            model.addAttribute("supers", response);
        }

        return response;
    }

    @RequestMapping(value = "/createPower", method = RequestMethod.POST)
    public String createPower(@RequestParam("description") String description, Model model, Super s) {
        s.getSuperId();

        Super supers = superService.getSuperById(s.getSuperId());
        model.addAttribute("supers", supers);

        Power power = new Power();
        power.setDescription(description);
        powerService.addPower(power);

        List<Power> powerList = powerService.getAllPowers();
        model.addAttribute("powerList", powerList);
        
        List<Organization> orgList = orgService.getAllOrganizations();
        model.addAttribute("orgList", orgList);
        return "supersDetails";
    }

    @RequestMapping(value = "/addPowerToSuper", method = RequestMethod.GET)
    public String addPowerToSuper(@RequestParam("superId") String sId, @RequestParam("powerId") String pId, Model model) {
        int superId = Integer.parseInt(sId);
        int powerId = Integer.parseInt(pId);

        Super s = superService.getSuperById(superId);
        Power p = powerService.getPowerById(powerId);
        superService.insertSuperPower(s, p);

        return "redirect:displaySupersDetails?superId=" + s.getSuperId();
    }

    @RequestMapping(value = "/addOrganizationToSuper", method = RequestMethod.GET)
    public String addOrganizationToSuper(@RequestParam("superId") String sId, @RequestParam("organizationId") String oId, Model model) {
        int superId = Integer.parseInt(sId);
        int organizationId = Integer.parseInt(oId);

        Super s = superService.getSuperById(superId);
        Organization o = orgService.getOrganizationById(organizationId);
        superService.insertSuperOrganization(s, o);

        return "redirect:displaySupersDetails?superId=" + s.getSuperId();
    }

    @RequestMapping(value = "/displaySupersDetails", method = RequestMethod.GET)
    public String displaySupersDetails(@RequestParam(value = "superId") String sId,
            Model model) {

        // String superIdParameter = request.getParameter("superId");
        int superId = Integer.parseInt(sId);

        Super supers = superService.getSuperById(superId);
        List<Power> powers = powerService.findPowerForSuper(supers);

        model.addAttribute("powers", powers);
        model.addAttribute("supers", supers);

        List<Power> powerListOne = powerService.getAllPowers();
        List<Power> powerList = powerService.getAllPowers();
        for (Power superPowers : powers) {
            for (Power currentPower : powerListOne) {
                if (currentPower.getPowerId() == superPowers.getPowerId()) {
                    powerList.remove(powerService.getPowerById(currentPower.getPowerId()));
                }
            }
        }
        model.addAttribute("powerList", powerList);

        List<Organization> organizations = orgService.findOrganizationsForSuper(supers);
        model.addAttribute("organizations", organizations);

        List<Organization> orgListOne = orgService.getAllOrganizations();
        List<Organization> orgList = orgService.getAllOrganizations();
        for (Organization superOrgs : organizations) {
            for (Organization currentOrg : orgListOne) {
                if (currentOrg.getOrganizationId() == superOrgs.getOrganizationId()) {
                    orgList.remove(orgService.getOrganizationById(currentOrg.getOrganizationId()));
                }
            }
        }

        model.addAttribute("orgList", orgList);

        List<Location> location = new ArrayList();
        List<Sighting> getSightingsForSuper = sightingService.findSightingForSuper(supers);
        for (Sighting superSighted : getSightingsForSuper) {
            location.add(locationService.getLocationIdBySighting(superSighted));
        }

        model.addAttribute("sighting", location);

        return "supersDetails";
    }

    @RequestMapping(value = "/deleteSuper", method = RequestMethod.GET)
    public String deleteSuper(@RequestParam("superId") String sId) {
        int superId = Integer.parseInt(sId);
        superService.deleteSuper(superId);
        return "redirect:displaySuperheroPage";
    }

    @RequestMapping(value = "/displayEditSuperForm", method = RequestMethod.GET)
    public String displayEditSuperForm(@RequestParam("superId") String sId, Model model) {
        int superId = Integer.parseInt(sId);
        Super supers = superService.getSuperById(superId);
        model.addAttribute("supers", supers);
        return "editSuperForm";
    }

    @RequestMapping(value = "/editSuper", method = RequestMethod.POST)
    public String editSuper(@Valid @ModelAttribute("supers") Super supers, BindingResult result) {
        if (result.hasErrors()) {
            return "editSuperForm";
        }
        superService.updateSuper(supers);
        return "redirect:displaySuperheroPage";
    }

    @RequestMapping(value = {"/", "index"}, method = RequestMethod.GET)
    public String displayTenSightings(Model model) {

        List<Sighting> sightingsByDate = sightingService.getRecentSightings(10);

        for (Sighting sight : sightingsByDate) {
            List<Super> sL = new ArrayList();
            List<Super> supers = superService.findSuperForSighting(sight);
            Location loc = locationService.getLocationIdBySighting(sight);
            for (Super s : supers) {
                sL.add(s);
            }
            sight.setSupers(sL);
            sight.setLocation(loc);
        }
        model.addAttribute("sightingsByDate", sightingsByDate);

        return "index";
    }

}
