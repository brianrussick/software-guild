package com.superherosightingsspringmvc.dao;

import com.superherosightingsspringmvc.dto.Location;
import com.superherosightingsspringmvc.dto.Org;
import com.superherosightingsspringmvc.dto.SuperPower;
import com.superherosightingsspringmvc.dto.SuperSighting;
import com.superherosightingsspringmvc.dto.SuperHeroSuperVill;
import java.time.LocalDate;
import java.util.ArrayList;
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
 * @author brian russick
 */
public class SuperHeroSightingsDaoTest {   
    SuperHeroSightingsDao dao;
    int orgId;
    int locaId;
    int sightingId;
    int superPowerId;
    int superHVId;
      
    public SuperHeroSightingsDaoTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("test-applicationContext.xml");       
        dao = ctx.getBean("SuperHeroSightingsDao", SuperHeroSightingsDao.class);        
        dao.resetDatabase();
               
        SuperPower superP = new SuperPower();
        superP.setName("Super Speed");
        superP.setDescription("Fast");
        List<Integer> superHVIdList = new ArrayList<>();
        SuperHeroSuperVill superHVTest = new SuperHeroSuperVill();
        superHVTest.setName("Hulk");
        superHVTest.setDescription("Green");
        dao.addSuper(superHVTest);
        superHVId = superHVTest.getId();
        superHVIdList.add(superHVId);
        superP.setSuperHVIdList(superHVIdList);
        dao.addSuperPower(superP);
        superPowerId = superP.getId();
        dao.removeSuperHeroSuperVill(superHVId);
              
        Org org = new Org();
        org.setName("Marvel");
        org.setDescription("Marvel Inc");
        org.setAddress("111 Marvel Dr.");
        org.setPhoneNumber("111-1111"); 
        dao.addOrg(org);
        orgId = org.getId();
               
        SuperHeroSuperVill superHV = new SuperHeroSuperVill();
        superHV.setName("Hulk");
        superHV.setDescription("Green");
        List<Integer> orgIdList = new ArrayList<>();
        List<Integer> superPowerIdList = new ArrayList<>();
        orgIdList.add(orgId);
        superPowerIdList.add(superPowerId);
        superHV.setOrgIdList(orgIdList);
        superHV.setSuperPowerIdList(superPowerIdList);
        dao.addSuper(superHV);
        superHVId = superHV.getId();
    
        Location loca = new Location();
        loca.setName("Ocean");
        loca.setDescription("Water");
        loca.setAddress("111 Ocean Blvd");
        loca.setLatitude(14.5);
        loca.setLongitude(28.6);
        dao.addLocation(loca);
        locaId = loca.getId();
        
        SuperSighting sighting = new SuperSighting();
        sighting.setDate(LocalDate.of(2018, 11, 01));
        sighting.setLocaId(locaId);
        sighting.setSuperHVId(superHVId);
        dao.addSuperSighting(sighting);
        sightingId = sighting.getId();       
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of addOrg method, of class SuperHeroSightingsDao.
     */
    @Test
    public void testAddGetOrg() {
        Org org = dao.getOrg(orgId);
        
        assertEquals("Marvel", org.getName());
        assertEquals("Marvel Inc", org.getDescription());
        assertEquals("111 Marvel Dr.", org.getAddress());
        assertEquals("111-1111", org.getPhoneNumber());
        assertEquals(1, org.getSuperHVIdList().size());
        
        superHVId = org.getSuperHVIdList().get(0);
        SuperHeroSuperVill superHV = dao.getSuperHeroSuperVill(superHVId);
        assertEquals("Hulk", superHV.getName());
        assertEquals("Green", superHV.getDescription());
    }

    /**
     * Test of getOrgAll method, of class SuperHeroSightingsDao.
     */
    @Test
    public void testGetOrgAll() {
        List<Org> orgList = dao.getOrgAll();       
        assertEquals(1, orgList.size());       
        Org org = orgList.get(0);
        
        assertEquals("Marvel", org.getName());
        assertEquals("Marvel Inc", org.getDescription());
        assertEquals("111 Marvel Dr.", org.getAddress());
        assertEquals("111-1111", org.getPhoneNumber());
        assertEquals(1, org.getSuperHVIdList().size());
        
        superHVId = org.getSuperHVIdList().get(0);
        SuperHeroSuperVill superHV = dao.getSuperHeroSuperVill(superHVId);
        assertEquals("Hulk", superHV.getName());
        assertEquals("Green", superHV.getDescription());
    }

    /**
     * Test of updateOrg method, of class SuperHeroSightingsDao.
     */
    @Test
    public void testUpdateOrg() {
        Org org = dao.getOrg(orgId);
        
        List<Integer> superHVIdList = new ArrayList<>();
        SuperHeroSuperVill superHV = new SuperHeroSuperVill();
        superHV.setName("Gambit");
        superHV.setDescription("Slick");
        dao.addSuper(superHV);
        superHVId = superHV.getId();
        superHVIdList.add(superHVId);
        
        org.setName("Test ORG");
        org.setDescription("Test");
        org.setAddress("111 Test Ave");
        org.setPhoneNumber("111-9999");
        org.setSuperHVIdList(superHVIdList);
        
        dao.updateOrg(org);
        
        org = dao.getOrg(orgId);
        
        assertEquals("Test ORG", org.getName());
        assertEquals("Test", org.getDescription());
        assertEquals("111 Test Ave", org.getAddress());
        assertEquals("111-9999", org.getPhoneNumber());
        assertEquals(1, org.getSuperHVIdList().size());
    }

    /**
     * Test of removeOrg method, of class SuperHeroSightingsDao.
     */
    @Test
    public void testRemoveOrg() {
        dao.removeOrg(orgId);       
        Org org = dao.getOrg(orgId);       
        assertNull(org);
    } 
    
    /**
     * Test of addLocation method, of class SuperHeroSightingsDao.
     */
    @Test
    public void testAddGetLocation() {
        Location loca = dao.getLocation(locaId);
        
        assertEquals("Ocean", loca.getName());
        assertEquals("Water", loca.getDescription());
        assertEquals("111 Ocean Blvd", loca.getAddress());  
    }

    /**
     * Test of getLocationAll method, of class SuperHeroSightingsDao.
     */
    @Test
    public void testGetLocationAll() {     
        List<Location> locaList = dao.getLocationAll();     
        assertEquals(1, locaList.size());      
        Location loca = locaList.get(0);
        
        assertEquals("Ocean", loca.getName());
        assertEquals("Water", loca.getDescription());
        assertEquals("111 Ocean Blvd", loca.getAddress());  
    }

    /**
     * Test of updateLocation method, of class SuperHeroSightingsDao.
     */
    @Test
    public void testUpdateLocation() {
        Location loca = dao.getLocation(locaId);
   
        loca.setName("Test Loca");
        loca.setDescription("Test");
        loca.setAddress("1111111 Test Lane");
        loca.setLatitude(3);
        loca.setLongitude(4.3);
        
        dao.updateLocation(loca);
        
        loca = dao.getLocation(locaId);
        
        assertEquals("Test Loca", loca.getName());
        assertEquals("Test", loca.getDescription());
        assertEquals("1111111 Test Lane", loca.getAddress());         
    }

    /**
     * Test of removeLocation method, of class SuperHeroSightingsDao.
     */
    @Test
    public void testRemoveLocation() {        
        dao.removeLocation(locaId);       
        Location loca = dao.getLocation(locaId);       
        assertNull(loca);
    }
    
    /**
     * Test of addSuperPower method, of class SuperHeroSightingsDao.
     */
    @Test
    public void testAddGetSuperPower() {
        SuperPower superP = dao.getSuperPower(superPowerId);
        
        assertEquals("Super Speed", superP.getName());
        assertEquals("Fast", superP.getDescription());
        assertEquals(1, superP.getSuperHVIdList().size());
        
        superHVId = superP.getSuperHVIdList().get(0);
        SuperHeroSuperVill superHV = dao.getSuperHeroSuperVill(superHVId);
        assertEquals("Hulk", superHV.getName());
        assertEquals("Green", superHV.getDescription());
    }

    /**
     * Test of getSuperPowerAll method, of class SuperHeroSightingsDao.
     */
    @Test
    public void testGetSuperPowerAll() {
        List<SuperPower> superPList = dao.getSuperPowerAll();        
        assertEquals(1, superPList.size());        
        SuperPower superP = superPList.get(0);
        
        assertEquals("Super Speed", superP.getName());
        assertEquals("Fast", superP.getDescription());
        assertEquals(1, superP.getSuperHVIdList().size());
        
        superHVId = superP.getSuperHVIdList().get(0);
        SuperHeroSuperVill superHV = dao.getSuperHeroSuperVill(superHVId);
        assertEquals("Hulk", superHV.getName());
        assertEquals("Green", superHV.getDescription());
    }

    /**
     * Test of updatePower method, of class SuperHeroSightingsDao.
     */
    @Test
    public void testUpdateSuperPower() {
        SuperPower superP = dao.getSuperPower(superPowerId);        
        List<Integer> superHVIdList = new ArrayList<>();
        SuperHeroSuperVill superHV = new SuperHeroSuperVill();
        superHV.setName("Gambit");
        superHV.setDescription("Slick");
        dao.addSuper(superHV);
        superHVId = superHV.getId();
        superHVIdList.add(superHVId);
        
        superP.setName("Super charge");
        superP.setDescription("Charge Power");
        superP.setSuperHVIdList(superHVIdList);
        
        dao.updateSuperPower(superP);       
        superP = dao.getSuperPower(superPowerId);
        
        assertEquals("Super charge", superP.getName());
        assertEquals("Charge Power", superP.getDescription());
        assertEquals(1, superP.getSuperHVIdList().size());       
    }

    /**
     * Test of removeSuperPower method, of class SuperHeroSightingsDao.
     */
    @Test
    public void testRemoveSuperPower() {
        dao.removeSuperPower(superPowerId);        
        SuperPower superP = dao.getSuperPower(superPowerId);       
        assertNull(superP);
    }
    
    /**
     * Test of getSuperSighting method, of class SuperHeroSightingsDao.
     */
    @Test
    public void testGetSuperSighting() {               
        SuperSighting sighting = dao.getSuperSighting(sightingId);
        
        assertEquals(java.time.LocalDate.parse("2018-11-01"), sighting.getDate());
        assertEquals(superHVId, sighting.getSuperHVId());
        assertEquals(locaId, sighting.getLocaId());  
    }

    /**
     * Test of getSuperSightingsAll method, of class SuperHeroSightingsDao.
     */
    @Test
    public void testGetSuperSightingsAll() {       
        List<SuperSighting> sightingList = dao.getSuperSightingsAll();        
        assertEquals(1, sightingList.size());       
        SuperSighting sighting = sightingList.get(0);
        
        assertEquals(java.time.LocalDate.parse("2018-11-01"), sighting.getDate());
        assertEquals(superHVId, sighting.getSuperHVId());
        assertEquals(locaId, sighting.getLocaId());
    }

    /**
     * Test of updateSuperSighting method, of class SuperHeroSightingsDao.
     */
    @Test
    public void testUpdateSuperSighting() {       
        SuperSighting sighting = dao.getSuperSighting(sightingId);      
        sighting.setDate(LocalDate.of(2015, 01, 05));       
        dao.updateSuperSighting(sighting);     
        sighting = dao.getSuperSighting(sightingId);        
        LocalDate sightingDate = LocalDate.of(2015, 01, 05);
        
        assertEquals(sightingDate, sighting.getDate()); 
        assertEquals(superHVId, sighting.getSuperHVId());
        assertEquals(locaId, sighting.getLocaId());
    }

    /**
     * Test of removeSuperSighting method, of class SuperHeroSightingsDao.
     */
    @Test
    public void testRemoveSuperSighting() {       
        dao.removeSuperSighting(sightingId);       
        SuperSighting sighting = dao.getSuperSighting(sightingId);       
        assertNull(sighting);
    }


    /**
     * Test of addSuperHeroSuperVill method, of class SuperHeroSightingsDao.
     */
    @Test
    public void testAddGetSuperHeroSuperVill() {
        SuperHeroSuperVill superHV = dao.getSuperHeroSuperVill(superHVId);
        
        assertEquals("Hulk", superHV.getName());
        assertEquals("Green", superHV.getDescription());
        assertEquals(1, superHV.getOrgIdList().size());
        assertEquals(1, superHV.getSuperPowerIdList().size());
        
        superPowerId = superHV.getSuperPowerIdList().get(0);
        SuperPower superP = dao.getSuperPower(superPowerId);
        assertEquals("Super Speed", superP.getName());
        assertEquals("Fast", superP.getDescription());
        
        orgId = superHV.getOrgIdList().get(0);
        Org org = dao.getOrg(orgId);
        assertEquals("Marvel", org.getName());
        assertEquals("Marvel Inc", org.getDescription());
        assertEquals("111 Marvel Dr.", org.getAddress());
        assertEquals("111-1111", org.getPhoneNumber());
    }

    /**
     * Test of getSuperHeroSuperVillAll method, of class SuperHeroSightingsDao.
     */
    @Test
    public void testGetSuperHeroSuperVillAll() {
        List<SuperHeroSuperVill> superHVList = dao.getSuperHeroSuperVillAll();
        
        assertEquals(1, superHVList.size());        
        SuperHeroSuperVill superHV = superHVList.get(0);
        
        assertEquals("Hulk", superHV.getName());
        assertEquals("Green", superHV.getDescription());
        assertEquals(1, superHV.getOrgIdList().size());
        assertEquals(1, superHV.getSuperPowerIdList().size()); 
        
        superPowerId = superHV.getSuperPowerIdList().get(0);
        SuperPower superP = dao.getSuperPower(superPowerId);
        assertEquals("Super Speed", superP.getName());
        assertEquals("Fast", superP.getDescription());
        
        orgId = superHV.getOrgIdList().get(0);
        Org org = dao.getOrg(orgId);
        assertEquals("Marvel", org.getName());
        assertEquals("Marvel Inc", org.getDescription());
        assertEquals("111 Marvel Dr.", org.getAddress());
        assertEquals("111-1111", org.getPhoneNumber());      
    }

    /**
     * Test of updateSuperHeroSuperVill method, of class SuperHeroSightingsDao.
     */
    @Test
    public void testUpdateSuperHeroSuperVill() {
        SuperHeroSuperVill superHV = dao.getSuperHeroSuperVill(superHVId);
        List<Integer> orgIdList = superHV.getOrgIdList();
        List<Integer> superPowerIdList = superHV.getSuperPowerIdList();
        
        SuperPower superP = new SuperPower();
        superP.setName("Super Mind");
        superP.setDescription("Mental Powers");
        dao.addSuperPower(superP);
        superPowerId = superP.getId();
        superPowerIdList.add(superPowerId);
        
        Org org = new Org();
        org.setName("Test ORG");
        org.setDescription("Test");
        org.setAddress("111 Test Ave");
        org.setPhoneNumber("111-9999");
        dao.addOrg(org);
        orgId = org.getId();
        orgIdList.add(orgId);
        
        superHV.setName("Gambit");
        superHV.setDescription("Slick");
        superHV.setOrgIdList(orgIdList);
        superHV.setSuperPowerIdList(superPowerIdList);
        
        dao.updateSuperHeroSuperVill(superHV);      
        superHV = dao.getSuperHeroSuperVill(superHVId);
        
        assertEquals("Gambit", superHV.getName());
        assertEquals("Slick", superHV.getDescription());
        assertEquals(2, superHV.getOrgIdList().size());
        assertEquals(2, superHV.getSuperPowerIdList().size());
    }

    /**
     * Test of removeSuperHeroSuperVill method, of class SuperHeroSightingsDao.
     */
    @Test
    public void testRemoveSuperHeroSuperVill() {
        dao.removeSuperHeroSuperVill(superHVId);       
        SuperHeroSuperVill superHV = dao.getSuperHeroSuperVill(superHVId);      
        assertNull(superHV);
    }     
}