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
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author DavidKing
 */
public class LocationServiceTest {

    LocationServiceLayer service;
    SightingServiceLayer sightingService;

    public LocationServiceTest() {
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

        service = ctx.getBean("LocationServiceLayer", LocationServiceLayer.class);
        sightingService = ctx.getBean("SightingServiceLayer", SightingServiceLayer.class);

        List<Sighting> sightings = sightingService.getAllSightings();
        for (Sighting currentSighting : sightings) {
            sightingService.deleteSighting(currentSighting.getSightingId());
        }
        
        List<Location> locations = service.getAllLocations();
        for (Location currentLocation : locations) {
            service.deleteLocation(currentLocation.getLocationId());
        }
    }

    @After
    public void tearDown() {
    }

       @Test
    public void addGetLocation() {
        Location location = new Location();
        location.setName("AMC Theater");
        location.setDescription("movie theater");
        location.setAddress("2102 Hemlock Lane");
        location.setCity("Maple Grove");
        location.setState("MN");
        location.setZipcode("55369");
        location.setLatitude(new BigDecimal("45.172500"));
        location.setLongitude(new BigDecimal("93.455800"));

        service.addLocation(location);
        Location fromDao = service.getLocationById(location.getLocationId());
        assertEquals(fromDao, location);
    }

    @Test
    public void updateLocation() {
        Location location = new Location();
        location.setName("AMC Theater");
        location.setDescription("movie theater");
        location.setAddress("2102 Hemlock Lane");
        location.setCity("Maple Grove");
        location.setState("MN");
        location.setZipcode("55369");
        location.setLatitude(new BigDecimal("45.172500"));
        location.setLongitude(new BigDecimal("93.455800"));

        service.addLocation(location);
        location.setCity("Minneapolis");
        service.updateLocation(location);
        Location fromDao = service.getLocationById(location.getLocationId());
        assertEquals(fromDao, location);
    }

    @Test
    public void deleteLocation() {
        Location location = new Location();
        location.setName("AMC Theater");
        location.setDescription("movie theater");
        location.setAddress("2102 Hemlock Lane");
        location.setCity("Maple Grove");
        location.setState("MN");
        location.setZipcode("55369");
        location.setLatitude(new BigDecimal("45.172500"));
        location.setLongitude(new BigDecimal("93.455800"));

        service.addLocation(location);
        Location fromDao = service.getLocationById(location.getLocationId());
        assertEquals(fromDao, location);

        service.deleteLocation(location.getLocationId());
        assertNull(service.getLocationById(location.getLocationId()));
    }

    @Test
    public void getAllLocations() {
        Location location = new Location();
        location.setName("AMC Theater");
        location.setDescription("movie theater");
        location.setAddress("2102 Hemlock Lane");
        location.setCity("Maple Grove");
        location.setState("MN");
        location.setZipcode("55369");
        location.setLatitude(new BigDecimal("45.172500"));
        location.setLongitude(new BigDecimal("93.455800"));

        service.addLocation(location);

        Location location2 = new Location();
        location2.setName("Theater");
        location2.setDescription("movie theater");
        location2.setAddress("2102 Hemlock Lane");
        location2.setCity("Louisville");
        location2.setState("KY");
        location2.setZipcode("44222");
        location2.setLatitude(new BigDecimal("45.172500"));
        location2.setLongitude(new BigDecimal("93.455800"));

        service.addLocation(location2);

        assertEquals(2, service.getAllLocations().size());
    }
}
