/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.supersightings.dao;


import com.sg.supersightings.model.Location;
import com.sg.supersightings.model.Organization;
import com.sg.supersightings.model.Power;
import com.sg.supersightings.model.Sighting;
import com.sg.supersightings.model.Super;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 *
 * @author DavidKing
 */
public class SuperDaoTest {

    SuperDao dao;

    public SuperDaoTest() {
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

        dao = ctx.getBean("SuperDao", SuperDao.class);

        List<Super> supers = dao.getAllSupers();
        for (Super currentSuper : supers) {
            dao.deleteSuper(currentSuper.getSuperId());
        }
//        
//        List<Sighting> sightings = dao.getAllSightings();
//        for (Sighting currentSighting : sightings) {
//            dao.deleteSighting(currentSighting.getSightingId());
//        }
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
//        List<Location> locations = dao.getAllLocations();
//        for (Location currentLocation : locations) {
//            dao.deleteLocation(currentLocation.getLocationId());
//        }
    }

    @After
    public void tearDown() {
    }

//    @Test
//    public void addGetPower() {
//        Power power = new Power();
//        power.setDescription("Very Strong");
//        
//        dao.addPower(power);
//        
//        Power fromDao = dao.getPowerById(power.getPowerId());
//        assertEquals(fromDao, power);
//    }
//    
//    @Test
//    public void updatePower() {
//        Power power = new Power();
//        power.setDescription("Very Strong");
//        
//        dao.addPower(power);
//        power.setDescription("Fast");
//        dao.updatePower(power);
//        Power fromDao = dao.getPowerById(power.getPowerId());
//        assertEquals(fromDao, power);
//    }
//    
//    @Test
//    public void deletePower() {
//        Power power = new Power();
//        power.setDescription("Very Strong");
//        
//        dao.addPower(power);
//        Power fromDao = dao.getPowerById(power.getPowerId());
//        assertEquals(fromDao, power);
//        dao.deletePower(power.getPowerId());
//        assertNull(dao.getPowerById(power.getPowerId()));
//        
//    }
//    @Test
//    public void addGetLocation() {
//        Location location = new Location();
//        location.setName("AMC Theater");
//        location.setDescription("movie theater");
//        location.setAddress("2102 Hemlock Lane");
//        location.setCity("Maple Grove");
//        location.setState("MN");
//        location.setZipcode("55369");
//        location.setLatitude(new BigDecimal("45.172500"));
//        location.setLongitude(new BigDecimal("93.455800"));
//        
//        dao.addLocation(location);
//        Location fromDao = dao.getLocationById(location.getLocationId());
//        assertEquals(fromDao, location);
//    }
//    
//    @Test
//    public void updateLocation() {
//        Location location = new Location();
//        location.setName("AMC Theater");
//        location.setDescription("movie theater");
//        location.setAddress("2102 Hemlock Lane");
//        location.setCity("Maple Grove");
//        location.setState("MN");
//        location.setZipcode("55369");
//        location.setLatitude(new BigDecimal("45.172500"));
//        location.setLongitude(new BigDecimal("93.455800"));
//        
//        dao.addLocation(location);
//        location.setCity("Minneapolis");
//        dao.updateLocation(location);
//        Location fromDao = dao.getLocationById(location.getLocationId());
//        assertEquals(fromDao, location);
//    }
//    
//    @Test
//    public void deleteLocation() {
//        Location location = new Location();
//        location.setName("AMC Theater");
//        location.setDescription("movie theater");
//        location.setAddress("2102 Hemlock Lane");
//        location.setCity("Maple Grove");
//        location.setState("MN");
//        location.setZipcode("55369");
//        location.setLatitude(new BigDecimal("45.172500"));
//        location.setLongitude(new BigDecimal("93.455800"));
//        
//        dao.addLocation(location);
//        Location fromDao = dao.getLocationById(location.getLocationId());
//        assertEquals(fromDao, location);
//        
//        dao.deleteLocation(location.getLocationId());
//        assertNull(dao.getLocationById(location.getLocationId()));
//    }
//    
//    @Test
//    public void addGetSighting() {
//        Location location = new Location();
//        location.setName("AMC Theater");
//        location.setDescription("movie theater");
//        location.setAddress("2102 Hemlock Lane");
//        location.setCity("Maple Grove");
//        location.setState("MN");
//        location.setZipcode("55369");
//        location.setLatitude(new BigDecimal("45.172500"));
//        location.setLongitude(new BigDecimal("93.455800"));
//        
//        dao.addLocation(location);
//        
//        Sighting sighting = new Sighting();
//        String n = "2010-12-03 05:55:00";
//        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//        LocalDateTime dateTime = LocalDateTime.parse(n, format);
//        Timestamp timestamp = Timestamp.valueOf(dateTime);
//        sighting.setDate(timestamp);
//        
//        sighting.setLocation(location);
//        dao.addSighting(sighting);
//        Sighting fromDao = dao.getSightingById(sighting.getSightingId());
//        fromDao.setLocation(location);
//        assertEquals(fromDao, sighting);
//        
//    }
//    
//    @Test
//    public void deleteSighting() {
//        Location location = new Location();
//        location.setName("AMC Theater");
//        location.setDescription("movie theater");
//        location.setAddress("2102 Hemlock Lane");
//        location.setCity("Maple Grove");
//        location.setState("MN");
//        location.setZipcode("55369");
//        location.setLatitude(new BigDecimal("45.172500"));
//        location.setLongitude(new BigDecimal("93.455800"));
//        
//        dao.addLocation(location);
//        
//        Sighting sighting = new Sighting();
//        String n = "2010-12-03 05:55:00";
//        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//        LocalDateTime dateTime = LocalDateTime.parse(n, format);
//        Timestamp timestamp = Timestamp.valueOf(dateTime);
//        sighting.setDate(timestamp);
//        
//        sighting.setLocation(location);
//        dao.addSighting(sighting);
//        Sighting fromDao = dao.getSightingById(sighting.getSightingId());
//        fromDao.setLocation(location);
//        assertEquals(fromDao, sighting);
//        
//        dao.deleteSighting(sighting.getSightingId());
//        assertNull(dao.getSightingById(sighting.getSightingId()));
//    }
//    
//    @Test
//    public void updateSighting() {
//        Location location = new Location();
//        location.setName("AMC Theater");
//        location.setDescription("movie theater");
//        location.setAddress("2102 Hemlock Lane");
//        location.setCity("Maple Grove");
//        location.setState("MN");
//        location.setZipcode("55369");
//        location.setLatitude(new BigDecimal("45.172500"));
//        location.setLongitude(new BigDecimal("93.455800"));
//        
//        dao.addLocation(location);
//        
//        Sighting sighting = new Sighting();
//        String n = "2010-12-03 05:55:00";
//        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//        LocalDateTime dateTime = LocalDateTime.parse(n, format);
//        Timestamp timestamp = Timestamp.valueOf(dateTime);
//        sighting.setDate(timestamp);
//        
//        sighting.setLocation(location);
//        dao.addSighting(sighting);
//        location.setCity("Minneapolis");
//        dao.updateLocation(location);
//        Sighting fromDao = dao.getSightingById(sighting.getSightingId());
//        fromDao.setLocation(location);
//        assertEquals(fromDao, sighting);
//        
//    }
//    
//    @Test
//    public void addGetOrganization() {
//        Organization o = new Organization();
//        o.setName("Avengers");
//        o.setDescription("Large");
//        o.setType("Good");
//        o.setAddress("201 Stree");
//        o.setCity("New York");
//        o.setState("NY");
//        o.setZipcode("12000");
//        o.setPhone("555-3030");
//        dao.addOrganization(o);
//        
//        Organization fromDao = dao.getOrganizationById(o.getOrganizationId());
//        assertEquals(fromDao, o);
//    }
//    
//    @Test
//    public void updateOrganization() {
//        Organization o = new Organization();
//        o.setName("Avengers");
//        o.setDescription("Large");
//        o.setType("Good");
//        o.setAddress("201 Stree");
//        o.setCity("New York");
//        o.setState("NY");
//        o.setZipcode("12000");
//        o.setPhone("555-3030");
//        dao.addOrganization(o);
//        
//        o.setState("KY");
//        dao.updateOrganization(o);
//        Organization fromDao = dao.getOrganizationById(o.getOrganizationId());
//        assertEquals(fromDao, o);
//    }
//    
//    @Test
//    public void deleteOrganization() {
//        Organization o = new Organization();
//        o.setName("Avengers");
//        o.setDescription("Large");
//        o.setType("Good");
//        o.setAddress("201 Stree");
//        o.setCity("New York");
//        o.setState("NY");
//        o.setZipcode("12000");
//        o.setPhone("555-3030");
//        dao.addOrganization(o);
//        
//        Organization fromDao = dao.getOrganizationById(o.getOrganizationId());
//        assertEquals(fromDao, o);
//        
//        dao.deleteOrganization(o.getOrganizationId());
//        assertNull(dao.getOrganizationById(o.getOrganizationId()));
//    }
//    
    @Test
    public void addGetSuper() {

        Super superperson = new Super();
        superperson.setName("Hulk");
        superperson.setDescription("Green");

        dao.addSuper(superperson);

        Super fromDao = dao.getSuperById(superperson.getSuperId());

        assertEquals(fromDao, superperson);
    }

    @Test
    public void deleteSuper() {
        Super superperson = new Super();
        superperson.setName("Hulk");
        superperson.setDescription("Green");

        dao.addSuper(superperson);

        Super fromDao = dao.getSuperById(superperson.getSuperId());
        assertEquals(fromDao, superperson);

        dao.deleteSuper(superperson.getSuperId());
        assertNull(dao.getSuperById(superperson.getSuperId()));
    }

    @Test
    public void updateSuper() {
        Super superperson = new Super();
        superperson.setName("Hulk");
        superperson.setDescription("Green");

        dao.addSuper(superperson);

        superperson.setName("The Incredible Hulk");
        dao.updateSuper(superperson);

        Super fromDao = dao.getSuperById(superperson.getSuperId());
        assertEquals(fromDao, superperson);

    }

    @Test
    public void getAllSupers() {
        Super superperson = new Super();
        superperson.setName("Hulk");
        superperson.setDescription("Green");

        dao.addSuper(superperson);

        Super superperson2 = new Super();
        superperson2.setName("Wolverine");
        superperson2.setDescription("claws");

        dao.addSuper(superperson2);

        assertEquals(2, dao.getAllSupers().size());
    }
}
