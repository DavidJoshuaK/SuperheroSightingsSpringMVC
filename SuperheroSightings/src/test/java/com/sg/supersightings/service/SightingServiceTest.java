/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.supersightings.service;

import com.sg.supersightings.model.Location;
import com.sg.supersightings.model.Sighting;
import com.sg.supersightings.servicelayer.LocationServiceLayer;
import com.sg.supersightings.servicelayer.SightingServiceLayer;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author DavidKing
 */
public class SightingServiceTest {
    
    SightingServiceLayer sightingService;
    LocationServiceLayer locationService;
    
    
    public SightingServiceTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
     @Before
    public void setUp() {
        ApplicationContext ctx
                = new ClassPathXmlApplicationContext("test-applicationContext.xml");

        locationService = ctx.getBean("LocationServiceLayer", LocationServiceLayer.class);
        sightingService = ctx.getBean("SightingServiceLayer", SightingServiceLayer.class);

        List<Sighting> sightings = sightingService.getAllSightings();
        for (Sighting currentSighting : sightings) {
            sightingService.deleteSighting(currentSighting.getSightingId());
        }
        
        List<Location> locations = locationService.getAllLocations();
        for (Location currentLocation : locations) {
            locationService.deleteLocation(currentLocation.getLocationId());
        }
    }
    
    @After
    public void tearDown() {
    }

     @Test
    public void addGetSighting() {
        Location location = new Location();
        location.setName("AMC Theater");
        location.setDescription("movie theater");
        location.setAddress("2102 Hemlock Lane");
        location.setCity("Maple Grove");
        location.setState("MN");
        location.setZipcode("55369");
        location.setLatitude(new BigDecimal("45.172500"));
        location.setLongitude(new BigDecimal("93.455800"));

        locationService.addLocation(location);

        Sighting sighting = new Sighting();
        String n = "2010-12-03 05:55:00";
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dateTime = LocalDateTime.parse(n, format);
        Timestamp timestamp = Timestamp.valueOf(dateTime);
        sighting.setDate(timestamp);

        sighting.setLocation(location);
        sightingService.addSighting(sighting);

        Sighting fromDao = sightingService.getSightingById(sighting.getSightingId());
        fromDao.setLocation(location);
        assertEquals(fromDao, sighting);

    }

    @Test
    public void deleteSighting() {
        Location location = new Location();
        location.setName("AMC Theater");
        location.setDescription("movie theater");
        location.setAddress("2102 Hemlock Lane");
        location.setCity("Maple Grove");
        location.setState("MN");
        location.setZipcode("55369");
        location.setLatitude(new BigDecimal("45.172500"));
        location.setLongitude(new BigDecimal("93.455800"));

        locationService.addLocation(location);

        Sighting sighting = new Sighting();
        String n = "2010-12-03 05:55:00";
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dateTime = LocalDateTime.parse(n, format);
        Timestamp timestamp = Timestamp.valueOf(dateTime);
        sighting.setDate(timestamp);

        sighting.setLocation(location);
        sightingService.addSighting(sighting);
        Sighting fromDao = sightingService.getSightingById(sighting.getSightingId());
        fromDao.setLocation(location);
        assertEquals(fromDao, sighting);

        sightingService.deleteSighting(sighting.getSightingId());
        assertNull(sightingService.getSightingById(sighting.getSightingId()));
    }

    @Test
    public void updateSighting() {
        Location location = new Location();
        location.setName("AMC Theater");
        location.setDescription("movie theater");
        location.setAddress("2102 Hemlock Lane");
        location.setCity("Maple Grove");
        location.setState("MN");
        location.setZipcode("55369");
        location.setLatitude(new BigDecimal("45.172500"));
        location.setLongitude(new BigDecimal("93.455800"));

        locationService.addLocation(location);

        Sighting sighting = new Sighting();
        String n = "2010-12-03 05:55:00";
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dateTime = LocalDateTime.parse(n, format);
        Timestamp timestamp = Timestamp.valueOf(dateTime);
        sighting.setDate(timestamp);

        sighting.setLocation(location);
        sightingService.addSighting(sighting);
        location.setCity("Minneapolis");
        locationService.updateLocation(location);
        Sighting fromDao = sightingService.getSightingById(sighting.getSightingId());
        fromDao.setLocation(location);
        assertEquals(fromDao, sighting);

    }
    
    @Test
    public void getAllSightings(){
        Location location = new Location();
        location.setName("AMC Theater");
        location.setDescription("movie theater");
        location.setAddress("2102 Hemlock Lane");
        location.setCity("Maple Grove");
        location.setState("MN");
        location.setZipcode("55369");
        location.setLatitude(new BigDecimal("45.172500"));
        location.setLongitude(new BigDecimal("93.455800"));
        
        locationService.addLocation(location);
        
        Sighting sighting = new Sighting();
        String n = "2010-12-03 05:55:00";
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dateTime = LocalDateTime.parse(n, format);
        Timestamp timestamp = Timestamp.valueOf(dateTime);
        sighting.setDate(timestamp);
        
        sighting.setLocation(location);
        sightingService.addSighting(sighting);
        
         Location location2 = new Location();
        location2.setName("Theater");
        location2.setDescription("movie theater");
        location2.setAddress("2102 Hemlock Lane");
        location2.setCity("Louisville");
        location2.setState("KY");
        location2.setZipcode("44222");
        location2.setLatitude(new BigDecimal("45.172500"));
        location2.setLongitude(new BigDecimal("93.455800"));
        
        locationService.addLocation(location2);
        
        Sighting sighting2 = new Sighting();
        String l = "2017-10-03 06:55:00";
        DateTimeFormatter format2 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dateTime2 = LocalDateTime.parse(l, format2);
        Timestamp timestamp2 = Timestamp.valueOf(dateTime2);
        sighting2.setDate(timestamp2);
        
        sighting2.setLocation(location2);
        sightingService.addSighting(sighting2);
        
        assertEquals(2, sightingService.getAllSightings().size());
    }
}
