package com.superherosightingsspringmvc.dao;

import com.superherosightingsspringmvc.dto.Location;
import com.superherosightingsspringmvc.dto.Org;
import com.superherosightingsspringmvc.dto.SuperPower;
import com.superherosightingsspringmvc.dto.SuperSighting;
import com.superherosightingsspringmvc.dto.SuperHeroSuperVill;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author brian russick
 */
public class SuperHeroSightingsDaoImpl implements SuperHeroSightingsDao {    
    // sql statements
    private static final String SQL_INSERT_SUPERHV
            = "insert into superhvs (Name, Description,Image)"
            + " values (?, ?, ?)";
    
    private static final String SQL_SELECT_SUPERHV
            = "select * from superhvs where SuperHVId = ?";
    
    private static final String SQL_SELECT_ALL_SUPERHVS
            = "select * from superhvs";
    
    private static final String SQL_UPDATE_SUPERHV
            = "update superhvs"
            + " set Name = ?, Description = ?"
            + " where SuperHVId = ?";
    
    private static final String SQL_DELETE_SUPERHV
            = "delete from superhvs where SuperHVId = ?";
    
    private static final String SQL_DELETE_ALL_SUPERHVS
            = "delete from superhvs";
    
    private static final String SQL_INSERT_POWER
            = "insert into powers (Name, Description)"
            + " values (?, ?)";
    
    private static final String SQL_SELECT_POWER
            = "select * from powers where SuperPowerId = ?";
    
    private static final String SQL_SELECT_ALL_POWERS
            = "select * from powers";
    
    private static final String SQL_UPDATE_POWER
            = "update powers"
            + " set Name = ?, Description = ?"
            + " where SuperPowerId = ?";
    
    private static final String SQL_DELETE_POWER
            = "delete from powers where SuperPowerId = ?";
    
    private static final String SQL_DELETE_ALL_POWERS
            = "delete from powers";
    
    private static final String SQL_INSERT_SIGHTING
            = "insert into sightings (Date, SuperHVId, LocaId)"
            + " values (?, ?, ?)";
    
    private static final String SQL_SELECT_SIGHTING
            = "select * from sightings where SightingId = ?";
    
    private static final String SQL_SELECT_ALL_SIGHTINGS
            = "select * from sightings";
    
    private static final String SQL_SELECT_SIGHTINGS_BY_LOCAID
            = "select * from sightings where LocaId = ?";
    
    private static final String SQL_SELECT_SIGHTINGS_BY_DATE
            = "select * from sightings where Date = ?";
    
    private static final String SQL_UPDATE_SIGHTING
            = "update sightings"
            + " set Date = ?, SuperHVId = ?, LocaId = ?"
            + " where SightingId = ?";
    
    private static final String SQL_DELETE_SIGHTING
            = "delete from sightings where SightingId = ?";
    
    private static final String SQL_DELETE_ALL_SIGHTINGS
            = "delete from sightings";
    
    private static final String SQL_DELETE_SIGHTINGS_BY_SUPERHVID
            = "delete from sightings where SuperHVId = ?";
    
    private static final String SQL_DELETE_SIGHTINGS_BY_LOCAID
            = "delete from sightings where LocaId = ?";
    
    private static final String SQL_INSERT_ORG
            = "insert into orgs (Name, Description, Address, PhoneNumber)"
            + " values (?, ?, ?, ?)";
    
    private static final String SQL_SELECT_ORG
            = "select * from orgs where OrgId = ?";
    
    private static final String SQL_SELECT_ALL_ORGS
            = "select * from orgs";
    
    private static final String SQL_UPDATE_ORG
            = "update orgs"
            + " set Name = ?, Description = ?, Address = ?, PhoneNumber = ?"
            + " where OrgId = ?";
    
    private static final String SQL_DELETE_ORG
            = "delete from orgs where OrgId = ?";
    
    private static final String SQL_DELETE_ALL_ORGS
            = "delete from orgs";
    
    private static final String SQL_INSERT_LOCATION
            = "insert into locations (Name, Description, Address, Latitude, Longitude)"
            + " values (?, ?, ?, ?, ?)";
    
    private static final String SQL_SELECT_LOCATION
            = "select * from locations where LocaId = ?";
    
    private static final String SQL_SELECT_ALL_LOCATIONS
            = "select * from locations";
    
    private static final String SQL_SELECT_LOCATIONS_BY_SUPERHVID
            = "select locations.LocaId, locations.Name, locations.Description, "
            + "locations.Address, locations.Latitude, locations.Longitude "
            + "from sightings "
            + "inner join locations on sightings.LocaId = locations.LocaId "
            + "where sightings.SuperHVId = ?";
    
    private static final String SQL_UPDATE_LOCATION
            = "update locations"
            + " set Name = ?, Description = ?, Address = ?, Latitude = ?, Longitude = ?"
            + " where LocaId = ?";
    
    private static final String SQL_DELETE_LOCATION
            = "delete from locations where LocaId = ?";
    
    private static final String SQL_DELETE_ALL_LOCATIONS
            = "delete from locations";
    
    private static final String SQL_DELETE_SUPERHVPOWERS_BY_SUPERHVID
            = "delete from superpowers where SuperHVId = ?";
    
    private static final String SQL_DELETE_SUPERHVORGS_BY_SUPERHVID
            = "delete from superorgs where SuperHVId = ?";
    
    private static final String SQL_DELETE_SUPERHVPOWERS_BY_POWERID
            = "delete from superpowers where SuperPowerId = ?";
    
    private static final String SQL_DELETE_SUPERHVORGS_BY_ORGID
            = "delete from superorgs where OrgId = ?";
       
    private static final String SQL_DELETE_ALL_SUPERPOWERS
            = "delete from superpowers";
    
    private static final String SQL_DELETE_ALL_SUPERORGS
            = "delete from superorgs";
    
    private static final String SQL_INSERT_SUPERHVORGS
            = "insert into superorgs (SuperHVId, OrgId)"
            + " values (?, ?)";
    
    private static final String SQL_INSERT_SUPERHVPOWERS
            = "insert into superpowers (SuperHVId, SuperPowerId)"
            + " values (?, ?)";
    
    private static final String SQL_SELECT_SUPERHVS_BY_ORGID
            = "select * from superorgs where OrgId = ?";
    
    private static final String SQL_SELECT_POWERS_BY_SUPERHVID
            = "select * from superpowers where SuperHVId = ?";
    
    private static final String SQL_SELECT_ORGS_BY_SUPERHVID
            = "select * from superorgs where SuperHVId = ?";
    
    private static final String SQL_SELECT_SUPERHVS_BY_SUPERPOWERID
            = "select * from superpowers where SuperPowerId = ?";
       

    // get the top 10 most recent super h/v sightings
    private static final String SQL_SELECT_TOP10_SIGHTINGS
            = " SELECT sightings.SightingId,sightings.Date,sightings.LocaId,"
            + " superhvs.SuperHVId,superhvs.Name,superhvs.Image from sightings"
            + " inner join superhvs on sightings.SuperHVId=superhvs.SuperHVId order by SightingId desc Limit 10";

    private static final String SQL_SELECT_TOP10_HV
            ="select * from superhvs order by SuperHVId DESC Limit 10";
    
    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    
    // add methods
    @Override
    public void addSuperSighting(SuperSighting sighting) {
        jdbcTemplate.update(
                SQL_INSERT_SIGHTING, 
                java.sql.Date.valueOf(sighting.getDate()), 
                sighting.getSuperHVId(), 
                sighting.getLocaId());
        
        int sightingId = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class);      
        sighting.setId(sightingId);
    }

    @Override
    public void addSuper(SuperHeroSuperVill superHV) {
        List<Integer> superPList = superHV.getSuperPowerIdList();
        List<Integer> orgList = superHV.getOrgIdList();

        jdbcTemplate.update(
                SQL_INSERT_SUPERHV, 
                superHV.getName(), 
                superHV.getDescription(),
                superHV.getImage());

        int superHVId = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class);
        superHV.setId(superHVId);

        if (superPList == null) {
        } else {
            superPList.forEach((superPowerId) -> {
                jdbcTemplate.update(SQL_INSERT_SUPERHVPOWERS,
                        superHV.getId(), 
                        superPowerId);
            });
        }
        if (orgList == null) {
        } else {
            superHV.getOrgIdList().forEach((orgId) -> {
                jdbcTemplate.update(SQL_INSERT_SUPERHVORGS,
                        superHV.getId(), 
                        orgId);
            });
        }
    }

    @Override
    public void addSuperPower(SuperPower superP) {
        List<Integer> superList = superP.getSuperHVIdList();

        jdbcTemplate.update(SQL_INSERT_POWER, 
                superP.getName(), 
                superP.getDescription());

        int superPowerId = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class);
        superP.setId(superPowerId);

        if (superList == null) {
        } else {
            superP.getSuperHVIdList().forEach((superHVId) -> {
                jdbcTemplate.update(SQL_INSERT_SUPERHVPOWERS,
                        superHVId, 
                        superP.getId());
            });
        }
    }

    @Override
    public void addLocation(Location loca) {
        jdbcTemplate.update(SQL_INSERT_LOCATION, 
                loca.getName(), 
                loca.getDescription(), 
                loca.getAddress(), 
                loca.getLatitude(), 
                loca.getLongitude());

        int locaId = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class);
        loca.setId(locaId);
    }

    @Override
    public void addOrg(Org org) {
        List<Integer> superList = org.getSuperHVIdList();

        jdbcTemplate.update(SQL_INSERT_ORG, 
                org.getName(), 
                org.getDescription(), 
                org.getAddress(), 
                org.getPhoneNumber());

        int orgId = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class);
        org.setId(orgId);

        if (superList == null) {
        } else {
            org.getSuperHVIdList().forEach((superHVId) -> {
                jdbcTemplate.update(SQL_INSERT_SUPERHVORGS,
                        superHVId, 
                        org.getId());
            });
        }
    }

    
    // get methods
    @Override
    public SuperSighting getSuperSighting(int superSightingId) {
        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_SIGHTING, new SuperSightingMapper(), superSightingId);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<SuperSighting> getSuperSightingsAll() {
        return jdbcTemplate.query(SQL_SELECT_ALL_SIGHTINGS, new SuperSightingMapper());
    }
   
    @Override
    public List<SuperSighting> getTopSuperSightings(){
         return jdbcTemplate.query(SQL_SELECT_TOP10_SIGHTINGS, new IndexSightingMapper());
    }

    @Override
    public List<SuperSighting> getSuperSightingsByLocation(int locaId) {
        return jdbcTemplate.query(SQL_SELECT_SIGHTINGS_BY_LOCAID, new SuperSightingMapper(), locaId);
    }

    @Override
    public List<SuperSighting> getSuperSightingsByDate(LocalDate date) {
        return jdbcTemplate.query(SQL_SELECT_SIGHTINGS_BY_DATE, new SuperSightingMapper(), java.sql.Date.valueOf(date));
    }

    @Override
    public List<SuperHeroSuperVill> getTopSuperHeros(){
        return jdbcTemplate.query(SQL_SELECT_TOP10_HV , new SuperHVMapper());
    }

    @Override
    public SuperHeroSuperVill getSuperHeroSuperVill(int superHVId) {
        try {
            SuperHeroSuperVill superHV = jdbcTemplate.queryForObject(SQL_SELECT_SUPERHV, new SuperHVMapper(), superHVId);
            superHV.setSuperPowerIdList(jdbcTemplate.query(SQL_SELECT_POWERS_BY_SUPERHVID, new SuperPowerIdMapper(), superHV.getId()));
            superHV.setOrgIdList(jdbcTemplate.query(SQL_SELECT_ORGS_BY_SUPERHVID, new OrgIdMapper(), superHV.getId()));
            return superHV;
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<SuperHeroSuperVill> getSuperHeroSuperVillAll() {
        List<SuperHeroSuperVill> superHVList = jdbcTemplate.query(SQL_SELECT_ALL_SUPERHVS, new SuperHVMapper());

        superHVList.stream().map((superHV) -> {
            superHV.setSuperPowerIdList(jdbcTemplate.query(SQL_SELECT_POWERS_BY_SUPERHVID, new SuperPowerIdMapper(), superHV.getId()));
            return superHV;
        }).forEachOrdered((superHV) -> {
            superHV.setOrgIdList(jdbcTemplate.query(SQL_SELECT_ORGS_BY_SUPERHVID, new OrgIdMapper(), superHV.getId()));
        });
        return superHVList;
    }

    @Override
    public SuperPower getSuperPower(int superPowerId) {
        try {
            SuperPower superP = jdbcTemplate.queryForObject(SQL_SELECT_POWER, new SuperPowerMapper(), superPowerId);
            superP.setSuperHVIdList(jdbcTemplate.query(SQL_SELECT_SUPERHVS_BY_SUPERPOWERID, new SuperHVIdMapper(), superP.getId()));
            return superP;
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<SuperPower> getSuperPowerAll() {
        List<SuperPower> superPList = jdbcTemplate.query(SQL_SELECT_ALL_POWERS, new SuperPowerMapper());

        superPList.forEach((superP) -> {
            superP.setSuperHVIdList(jdbcTemplate.query(SQL_SELECT_SUPERHVS_BY_SUPERPOWERID, new SuperHVIdMapper(), superP.getId()));
        });
        return superPList;
    }

    @Override
    public List<SuperHeroSuperVill> getSupersHVInOrg(Org org) {
        List<Integer> superHVIdList = org.getSuperHVIdList();
        List<SuperHeroSuperVill> superList = new ArrayList<>();

        superHVIdList.forEach((superHVId) -> {
            superList.add(getSuperHeroSuperVill(superHVId));
        });
        return superList;
    }

    @Override
    public Location getLocation(int locaId) {
        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_LOCATION, new LocationMapper(), locaId);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Location> getLocationAll() {
        return jdbcTemplate.query(SQL_SELECT_ALL_LOCATIONS, new LocationMapper());
    }

    @Override
    public List<Location> getSuperHVLocations(int superHVId) {
        return jdbcTemplate.query(SQL_SELECT_LOCATIONS_BY_SUPERHVID, new LocationMapper(), superHVId);
    }

    @Override
    public Org getOrg(int orgId) {
        try {
            Org org = jdbcTemplate.queryForObject(SQL_SELECT_ORG, new OrgMapper(), orgId);
            org.setSuperHVIdList(jdbcTemplate.query(SQL_SELECT_SUPERHVS_BY_ORGID, new SuperHVIdMapper(), org.getId()));
            return org;
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Org> getOrgAll() {
        List<Org> orgList = jdbcTemplate.query(SQL_SELECT_ALL_ORGS, new OrgMapper());

        orgList.forEach((org) -> {
            org.setSuperHVIdList(jdbcTemplate.query(SQL_SELECT_SUPERHVS_BY_ORGID, new SuperHVIdMapper(), org.getId()));
        });
        return orgList;
    }

    @Override
    public List<Org> getOrgsBySuperHV(SuperHeroSuperVill superP) {
        List<Integer> orgIdList = superP.getOrgIdList();
        List<Org> orgList = new ArrayList<>();
        orgIdList.forEach((orgId) -> {
            orgList.add(getOrg(orgId));
        });
        return orgList;
    }

    @Override
    public List<SuperSighting> getNewSuperSighting() {
        return new ArrayList<>();
    }

    
    // update methods
    @Override
    public void updateSuperSighting(SuperSighting sighting) {
        jdbcTemplate.update(SQL_UPDATE_SIGHTING, 
                java.sql.Date.valueOf(sighting.getDate()),
                sighting.getSuperHVId(),
                sighting.getLocaId(),
                sighting.getId());
    }

    @Override
    public void updateSuperHeroSuperVill(SuperHeroSuperVill superHV) {
        List<Integer> superPowerIdList = superHV.getSuperPowerIdList();
        List<Integer> orgIdList = superHV.getOrgIdList();

        jdbcTemplate.update(SQL_UPDATE_SUPERHV, 
                superHV.getName(), 
                superHV.getDescription(), 
                superHV.getId());
        jdbcTemplate.update(SQL_DELETE_SUPERHVPOWERS_BY_SUPERHVID, superHV.getId());
        jdbcTemplate.update(SQL_DELETE_SUPERHVORGS_BY_SUPERHVID, superHV.getId());

        if (superPowerIdList == null) {
        } else {
            superPowerIdList.forEach((superPowerId) -> {
                jdbcTemplate.update(SQL_INSERT_SUPERHVPOWERS, superHV.getId(), superPowerId);
            });
        }
        if (orgIdList == null) {
        } else {
            orgIdList.forEach((orgId) -> {
                jdbcTemplate.update(SQL_INSERT_SUPERHVORGS, superHV.getId(), orgId);
            });
        }
    }

    @Override
    public void updateSuperPower(SuperPower superP) {
        List<Integer> superHVIdList = superP.getSuperHVIdList();

        jdbcTemplate.update(SQL_UPDATE_POWER, superP.getName(), superP.getDescription(), superP.getId());
        jdbcTemplate.update(SQL_DELETE_SUPERHVPOWERS_BY_POWERID, superP.getId());

        if (superHVIdList == null) {
        } else {
            superHVIdList.forEach((superHVId) -> {
                jdbcTemplate.update(SQL_INSERT_SUPERHVPOWERS, superHVId, superP.getId());
            });
        }
    }

    @Override
    public void updateLocation(Location loca) {
        jdbcTemplate.update(SQL_UPDATE_LOCATION,
                loca.getName(),
                loca.getDescription(),
                loca.getAddress(),
                loca.getLatitude(),
                loca.getLongitude(),
                loca.getId());
    }

    @Override
    public void updateOrg(Org org) {
        List<Integer> superHVIdList = org.getSuperHVIdList();

        jdbcTemplate.update(SQL_UPDATE_ORG,
                org.getName(),
                org.getDescription(),
                org.getAddress(),
                org.getPhoneNumber(),
                org.getId());
        jdbcTemplate.update(SQL_DELETE_SUPERHVORGS_BY_ORGID, org.getId());

        if (superHVIdList == null) {
        } else {
            superHVIdList.forEach((superHVId) -> {
                jdbcTemplate.update(SQL_INSERT_SUPERHVORGS, superHVId, org.getId());
            });
        }
    }

    
    // remove methods
    @Override
    public void removeSuperSighting(int superSightingId) {
        jdbcTemplate.update(SQL_DELETE_SIGHTING, superSightingId);
    }

    @Override
    public void removeSuperHeroSuperVill(int superHVId) {
        jdbcTemplate.update(SQL_DELETE_SUPERHVPOWERS_BY_SUPERHVID, superHVId);
        jdbcTemplate.update(SQL_DELETE_SUPERHVORGS_BY_SUPERHVID, superHVId);
        jdbcTemplate.update(SQL_DELETE_SIGHTINGS_BY_SUPERHVID, superHVId);
        jdbcTemplate.update(SQL_DELETE_SUPERHV, superHVId);
    }


    @Override
    public void removeSuperPower(int superPowerId) {
        jdbcTemplate.update(SQL_DELETE_SUPERHVPOWERS_BY_POWERID, superPowerId);
        jdbcTemplate.update(SQL_DELETE_POWER, superPowerId);
    }

    @Override
    public void removeLocation(int locaId) {
        jdbcTemplate.update(SQL_DELETE_SIGHTINGS_BY_LOCAID, locaId);
        jdbcTemplate.update(SQL_DELETE_LOCATION, locaId);
    }

    @Override
    public void removeOrg(int orgId) {
        jdbcTemplate.update(SQL_DELETE_SUPERHVORGS_BY_ORGID, orgId);
        jdbcTemplate.update(SQL_DELETE_ORG, orgId);
    }

    @Override
    public void resetDatabase() {
        jdbcTemplate.update(SQL_DELETE_ALL_SIGHTINGS);
        jdbcTemplate.update(SQL_DELETE_ALL_SUPERPOWERS);
        jdbcTemplate.update(SQL_DELETE_ALL_SUPERORGS);
        jdbcTemplate.update(SQL_DELETE_ALL_ORGS);
        jdbcTemplate.update(SQL_DELETE_ALL_LOCATIONS);
        jdbcTemplate.update(SQL_DELETE_ALL_POWERS);
        jdbcTemplate.update(SQL_DELETE_ALL_SUPERHVS); 
    }


    // mapper classes    
    private static final class SuperSightingMapper implements RowMapper<SuperSighting> {      
        @Override
        public SuperSighting mapRow(ResultSet rs, int i) throws SQLException {
            SuperSighting sighting = new SuperSighting();
            sighting.setId(rs.getInt("SightingId"));
            sighting.setDate(rs.getTimestamp("Date").toLocalDateTime().toLocalDate());
            sighting.setSuperHVId(rs.getInt("superHVId"));
            sighting.setLocaId(rs.getInt("locaId"));
            return sighting;
        }
    }

    private static final class IndexSightingMapper implements RowMapper<SuperSighting> {
        @Override
        public SuperSighting mapRow(ResultSet rs, int i) throws SQLException {
            SuperSighting sighting = new SuperSighting();
            sighting.setId(rs.getInt("SightingId"));
            sighting.setDate(rs.getTimestamp("Date").toLocalDateTime().toLocalDate());
            sighting.setSuperHVId(rs.getInt("superHVId"));
            sighting.setHeroName(rs.getString("Name"));
            sighting.setHeroImage(rs.getString("Image"));
            sighting.setLocaId(rs.getInt("locaId"));
            return sighting;
        }
    }
    
    private static final class SuperHVMapper implements RowMapper<SuperHeroSuperVill> {      
        @Override
        public SuperHeroSuperVill mapRow(ResultSet rs, int i) throws SQLException {
            SuperHeroSuperVill superP = new SuperHeroSuperVill();
            superP.setId(rs.getInt("SuperHVId"));
            superP.setName(rs.getString("Name"));
            superP.setImage(rs.getString("Image"));
            superP.setDescription(rs.getString("Description"));
            return superP;
        }
    }
    
    private static final class SuperPowerMapper implements RowMapper<SuperPower> {        
        @Override
        public SuperPower mapRow(ResultSet rs, int i) throws SQLException {
            SuperPower superP = new SuperPower();
            superP.setId(rs.getInt("SuperPowerId"));
            superP.setName(rs.getString("Name"));
            superP.setDescription(rs.getString("Description"));
            return superP;
        }
    }
    
    private static final class LocationMapper implements RowMapper<Location> {        
        @Override
        public Location mapRow(ResultSet rs, int i) throws SQLException {
            Location loca = new Location();
            loca.setId(rs.getInt("LocaId"));
            loca.setName(rs.getString("Name"));
            loca.setDescription(rs.getString("Description"));
            loca.setAddress(rs.getString("Address"));
            loca.setLatitude(rs.getDouble("Latitude"));
            loca.setLongitude(rs.getDouble("Longitude"));
            return loca;
        }
    }
    
    private static final class OrgMapper implements RowMapper<Org> {       
        @Override
        public Org mapRow(ResultSet rs, int i) throws SQLException {
            Org org = new Org();
            org.setId(rs.getInt("OrgId"));
            org.setName(rs.getString("Name"));
            org.setDescription(rs.getString("Description"));
            org.setAddress(rs.getString("Address"));
            org.setPhoneNumber(rs.getString("PhoneNumber"));
            return org;
        } 
    }
    
    private static final class SuperPowerIdMapper implements RowMapper<Integer> {       
        @Override
        public Integer mapRow(ResultSet rs, int i) throws SQLException {
            return rs.getInt("SuperPowerId");
        }
    }
    
    private static final class OrgIdMapper implements RowMapper<Integer> {       
        @Override
        public Integer mapRow(ResultSet rs, int i) throws SQLException {
            return rs.getInt("OrgId");
        }
    }
    
    private static final class SuperHVIdMapper implements RowMapper<Integer> {     
        @Override
        public Integer mapRow(ResultSet rs, int i) throws SQLException {
            return rs.getInt("SuperHVId");
        }
    }
}