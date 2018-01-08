/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.supersightings.controller;

import com.sg.supersightings.model.sightingLocJsonResponse;
import com.sg.supersightings.model.Location;
import com.sg.supersightings.model.Sighting;
import com.sg.supersightings.model.SightingLoc;
import com.sg.supersightings.model.SightingLoc2;
import com.sg.supersightings.model.Super;
import com.sg.supersightings.servicelayer.LocationServiceLayer;
import com.sg.supersightings.servicelayer.SightingServiceLayer;
import com.sg.supersightings.servicelayer.SuperServiceLayer;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
public class SightingsController {

    SuperServiceLayer superService;
    SightingServiceLayer sightingService;
    LocationServiceLayer locationService;

    @Inject
    public SightingsController(SuperServiceLayer superService,
            SightingServiceLayer sightingService,
            LocationServiceLayer locationService) {
        this.superService = superService;
        this.locationService = locationService;

        this.sightingService = sightingService;
    }

    @RequestMapping(value = "/displaySightingsPage", method = RequestMethod.GET)
    public String dipslaySightingsPage(Model model) {
        List<Sighting> sightingList = sightingService.getAllSightings();

        for (Sighting sighting : sightingList) {
            List<Super> sL = new ArrayList();
            List<Super> supers = superService.findSuperForSighting(sighting);
            for (Super s : supers) {
                sL.add(s);
            }
            sighting.setSupers(sL);

        }

        model.addAttribute("sightingList", sightingList);

        return "sightings";
    }

    @PostMapping(value = "/sightings/createSighting", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public sightingLocJsonResponse createSighting(@RequestBody @Valid @ModelAttribute("sighting") SightingLoc sl, BindingResult result, Model model) {

        sightingLocJsonResponse response = new sightingLocJsonResponse();

        if (result.hasErrors()) {

            Map<String, String> errors = result.getFieldErrors().stream()
                    .collect(
                            Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage)
                    );

            response.setValidated(false);
            response.setErrorMessages(errors);
        } else {

            response.setValidated(true);
            response.setSl(sl);

            String d = sl.getDate().toString();
            String dt = d + "" + sl.getTime();
            String n = dt.replaceAll("..........", "$0 ");

            DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime dateTime = LocalDateTime.parse(n, format);

            Location loc = new Location();
            loc.setName(sl.getName());
            loc.setDescription(sl.getDescription());
            loc.setAddress(sl.getAddress());
            loc.setCity(sl.getCity());
            loc.setState(sl.getState());
            loc.setZipcode(sl.getZipcode());
            loc.setLatitude(sl.getLatitude());
            loc.setLongitude(sl.getLongitude());

            locationService.addLocation(loc);

            Sighting sighting = new Sighting();

            Timestamp timestamp = Timestamp.valueOf(dateTime);

            sighting.setDate(timestamp);

            sighting.setLocation(loc);
            sightingService.addSighting(sighting);

            sl.setLocationId(loc.getLocationId());
            sl.setSightingId(sighting.getSightingId());

        }

        return response;
    }

    @RequestMapping(value = "/addSuperToSighting", method = RequestMethod.GET)
    public String addSuperToSighting(@RequestParam("superId") String sId, @RequestParam("sightingId") String sight, Model model) {
        int superId = Integer.parseInt(sId);

        int sightingId = Integer.parseInt(sight);

        Super s = superService.getSuperById(superId);
        Sighting sighting = sightingService.getSightingById(sightingId);

        sightingService.insertSuperSighting(s, sighting);

        return "redirect:displaySightingDetails?sightingId=" + sighting.getSightingId();
    }

    @RequestMapping(value = "/displaySightingDetails", method = RequestMethod.GET)
    public String displaySightingDetails(@RequestParam("sightingId") String sight, Model model) {
        int sightingId = Integer.parseInt(sight);

        Sighting sighting = sightingService.getSightingById(sightingId);

        Location location = locationService.getLocationIdBySighting(sighting);
        model.addAttribute("location", location);
        model.addAttribute("sighting", sighting);

        List<Super> supersSighted = superService.findSuperForSighting(sighting);
        model.addAttribute("supers", supersSighted);

        List<Super> allSupers = superService.getAllSupers();
        List<Super> allSupersOne = superService.getAllSupers();

        for (Super sightedSuper : supersSighted) {
            for (Super everySuper : allSupersOne) {
                if (everySuper.getSuperId() == sightedSuper.getSuperId()) {
                    allSupers.remove(superService.getSuperById(everySuper.getSuperId()));
                }
            }
        }

        model.addAttribute("superList", allSupers);

        return "sightingDetails";
    }

    @RequestMapping(value = "/deleteSighting", method = RequestMethod.GET)
    public String deleteSighting(@RequestParam("sightingId") String sight) {
        int sightingId = Integer.parseInt(sight);
        sightingService.deleteSighting(sightingId);

        return "redirect:displaySightingsPage";
    }

    @RequestMapping(value = "/displayEditSightingForm", method = RequestMethod.GET)
    public String displayEditSightingForm(@RequestParam("sightingId") String sight, Model model) {
        int sightingId = Integer.parseInt(sight);

        Sighting sighting = sightingService.getSightingById(sightingId);
        Location location = locationService.getLocationIdBySighting(sighting);

        SightingLoc2 sc = new SightingLoc2();
        sc.setLocationId(location.getLocationId());

        sc.setName(location.getName());
        sc.setDescription(location.getDescription());
        sc.setAddress(location.getAddress());
        sc.setState(location.getState());
        sc.setCity(location.getCity());
        sc.setZipcode(location.getZipcode());
        sc.setLatitude(location.getLatitude());
        sc.setLongitude(location.getLongitude());

        sc.setSightingId(sighting.getSightingId());
        sc.setDate(sighting.getDate().toString());

        model.addAttribute("SightingLoc2", sc);
        return "editSightingForm";
    }

    @RequestMapping(value = "/editSighting", method = RequestMethod.POST)
    public String editSighting(@Valid @ModelAttribute("SightingLoc2") SightingLoc2 sl, BindingResult result) {
        if (result.hasErrors()) {
            return "editSightingForm";
        }

        Location location = locationService.getLocationById(sl.getLocationId());
        location.setName(sl.getName());
        location.setDescription(sl.getDescription());
        location.setAddress(sl.getAddress());
        location.setState(sl.getState());
        location.setCity(sl.getCity());
        location.setZipcode(sl.getZipcode());
        location.setLatitude(sl.getLatitude());
        location.setLongitude(sl.getLongitude());
        location.setLocationId(sl.getLocationId());
        locationService.updateLocation(location);

        Sighting sighting = sightingService.getSightingById(sl.getSightingId());
        String slDate = sl.getDate();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
        LocalDateTime dateTime = LocalDateTime.parse(slDate, format);
        Timestamp timestamp = Timestamp.valueOf(dateTime);
        sighting.setDate(timestamp);

        // sighting.setDate(sl.getDate());
        sighting.setLocation(location);
        sightingService.updateSighting(sighting);

        return "redirect:displaySightingsPage";
    }

}
