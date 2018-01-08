/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.supersightings.dao;

import com.sg.supersightings.model.Location;
import com.sg.supersightings.model.Sighting;
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
public class LocationDaoTest {

    LocationDao dao;
    SightingDao sightingDao;

    public LocationDaoTest() {
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

        dao = ctx.getBean("LocationDao", LocationDao.class);
        sightingDao = ctx.getBean("SightingDao", SightingDao.class);

//        List<Super> supers = dao.getAllSupers();
//        for (Super currentSuper : supers) {
//            dao.deleteSuper(currentSuper.getSuperId());
//        }
//        
        List<Sighting> sightings = sightingDao.getAllSightings();
        for (Sighting currentSighting : sightings) {
            sightingDao.deleteSighting(currentSighting.getSightingId());
        }
//        
//        List<Power> powers = dao.getAllPowers();
//        for (Power currentPower : powers) {
//            dao.deletePower(currentPower.getPowerId());
//        }
//        
//        List<Organization> organizations = dao.getAllOrganizations();
//        for (Organization currentOrganization : organizations) {
//            dao.deleteOrganization(currentOrganization.getOrganizationId());
//        }
//        
        List<Location> locations = dao.getAllLocations();
        for (Location currentLocation : locations) {
            dao.deleteLocation(currentLocation.getLocationId());
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

        dao.addLocation(location);
        Location fromDao = dao.getLocationById(location.getLocationId());
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

        dao.addLocation(location);
        location.setCity("Minneapolis");
        dao.updateLocation(location);
        Location fromDao = dao.getLocationById(location.getLocationId());
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

        dao.addLocation(location);
        Location fromDao = dao.getLocationById(location.getLocationId());
        assertEquals(fromDao, location);

        dao.deleteLocation(location.getLocationId());
        assertNull(dao.getLocationById(location.getLocationId()));
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

        dao.addLocation(location);

        Location location2 = new Location();
        location2.setName("Theater");
        location2.setDescription("movie theater");
        location2.setAddress("2102 Hemlock Lane");
        location2.setCity("Louisville");
        location2.setState("KY");
        location2.setZipcode("44222");
        location2.setLatitude(new BigDecimal("45.172500"));
        location2.setLongitude(new BigDecimal("93.455800"));

        dao.addLocation(location2);

        assertEquals(2, dao.getAllLocations().size());
    }

}
