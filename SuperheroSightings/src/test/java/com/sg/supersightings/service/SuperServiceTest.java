/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.supersightings.service;

import com.sg.supersightings.dao.SuperDao;
import com.sg.supersightings.model.Super;
import com.sg.supersightings.servicelayer.SuperServiceLayer;
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
public class SuperServiceTest {
    
    SuperServiceLayer service;
    
    public SuperServiceTest() {
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
        
        service = ctx.getBean("SuperServiceLayer", SuperServiceLayer.class);
        
        List<Super> supers = service.getAllSupers();
        for (Super currentSuper : supers) {
            service.deleteSuper(currentSuper.getSuperId());
        }
    }
    
    @After
    public void tearDown() {
    }

 
    
     @Test
    public void addGetSuper() {
        
        Super superperson = new Super();
        superperson.setName("Hulk");
        superperson.setDescription("Green");
        
        service.addSuper(superperson);
        
        Super fromDao = service.getSuperById(superperson.getSuperId());
        
        assertEquals(fromDao, superperson);
    }
    
    @Test
    public void deleteSuper() {
        Super superperson = new Super();
        superperson.setName("Hulk");
        superperson.setDescription("Green");
        
        service.addSuper(superperson);
        
        Super fromDao = service.getSuperById(superperson.getSuperId());
        assertEquals(fromDao, superperson);
        
        service.deleteSuper(superperson.getSuperId());
        assertNull(service.getSuperById(superperson.getSuperId()));
    }
    
    @Test
    public void updateSuper() {
        Super superperson = new Super();
        superperson.setName("Hulk");
        superperson.setDescription("Green");
        
        service.addSuper(superperson);
        
        superperson.setName("The Incredible Hulk");
        service.updateSuper(superperson);
        
        Super fromDao = service.getSuperById(superperson.getSuperId());
        assertEquals(fromDao, superperson);
    }
    
    @Test
    public void getAllSupers(){
        Super superperson = new Super();
        superperson.setName("Hulk");
        superperson.setDescription("Green");
        
        service.addSuper(superperson);
        
        Super superperson2 = new Super();
        superperson2.setName("Wolverine");
        superperson2.setDescription("claws");
        
        service.addSuper(superperson2);
        
        assertEquals(2, service.getAllSupers().size());
    }
}
