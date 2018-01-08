/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.supersightings.dao;

import com.sg.supersightings.model.Location;
import com.sg.supersightings.model.Sighting;
import com.sg.supersightings.model.Super;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author DavidKing
 */
public class SightingDaoJdbcTempImpl implements SightingDao {

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static final String SQL_INSERT_SIGHTING
            = "insert into Sighting (Date, LocationId) values (?, ?)";

    private static final String SQL_DELETE_SIGHTING
            = "delete from Sighting where SightingId = ?";
    
    private static final String SQL_DELETE_SUPERSIGHTING
            = "delete from SuperSighting where SightingId = ?";

    private static final String SQL_UPDATE_SIGHTING
            = "update sighting set date = ?, LocationId=? where SightingId= ?";

    private static final String SQL_SELECT_SIGHTING
            = "select * from Sighting where SightingId = ?";

    private static final String SQL_SELECT_SIGHTING_BY_LOCATIONID
            = "select * from Sighting where LocationId = ?";

    private static final String SQL_SELECT_SIGHTING_DATES
            = "Select * from Sighting ORDER BY Date DESC Limit 10";

    private static final String SQL_SELECT_SUPERSIGHTING_SIGHTINGID_BY_SUPERID
            = "select SightingId from SuperSighting where SuperId = ?";

    private static final String SQL_SELECT_SUPERSIGHTING_SUPERID_BY_SIGHTINGID
            = "select SuperId from SuperSighting where SightingId = ?";

    //Find all the Sightings for a given superhero
    private static final String SQL_SELECT_SIGHTING_BY_SUPERID
            = "select s.SightingId, s.LocationId, s.Date from "
            + "Sighting s join SuperSighting ss on SuperId "
            + "where s.SightingId = ss.SightingId "
            + "and ss.SuperId = ?";

    private static final String SQL_SELECT_SUPER_BY_SIGHTING_ID
            = "select s.SuperId, s.Name, s.Description from Super s join SuperSighting ss "
            + "on SightingId where s.SuperId = ss.SuperId "
            + "and ss.SightingId = ?";

    private static final String SQL_SELECT_LOCATION_BY_SIGHTINGID
            = "select l.LocationId, l.Name, l.Description, l.Address, l.City, l.State, "
            + "l.Zipcode, l.Longitude, l.Latitude from Location l join "
            + "Sighting s on l.LocationId = s.LocationId where s.SightingId= ?";

    private static final String SQL_SELECT_ALL_SIGHTING
            = "select * from Sighting ORDER BY Date DESC";

    private static final String SQL_INSERT_SUPERSIGHTING
            = "insert into SuperSighting (SuperId, SightingId) values (?, ?)";

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void addSighting(Sighting sighting) {
        jdbcTemplate.update(SQL_INSERT_SIGHTING,
                sighting.getDate().toString(),
                sighting.getLocation().getLocationId());

        int sightingId = jdbcTemplate.queryForObject("select LAST_INSERT_ID()",
                Integer.class);
        sighting.setSightingId(sightingId);
    }

    @Override
    public void deleteSighting(int sightingId) {
        jdbcTemplate.update(SQL_DELETE_SUPERSIGHTING, sightingId);
        jdbcTemplate.update(SQL_DELETE_SIGHTING, sightingId);
    }

    @Override
    public void updateSighting(Sighting sighting) {
        jdbcTemplate.update(SQL_UPDATE_SIGHTING,
                sighting.getDate().toString(),
                sighting.getLocation().getLocationId(),
                sighting.getSightingId());
    }

    @Override
    public Sighting getSightingById(int id) {
        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_SIGHTING,
                    new SightingMapper(), id);

        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Sighting> getSightingsDescDate() {
        return jdbcTemplate.query(SQL_SELECT_SIGHTING_DATES,
                new SightingMapper());
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void insertSuperSighting(Super superperson, Sighting sighting) {
        jdbcTemplate.update(SQL_INSERT_SUPERSIGHTING,
                superperson.getSuperId(),
                sighting.getSightingId());

    }

    @Override
    public List<Sighting> getSightingByLocationId(int locationId) {
        List<Sighting> sightingList = jdbcTemplate.query(SQL_SELECT_SIGHTING_BY_LOCATIONID,
                new SightingMapper(),
                locationId);
        return associateLocationWithSighting(sightingList);
    }

    @Override
    public List<Sighting> getAllSightings() {

        return jdbcTemplate.query(SQL_SELECT_ALL_SIGHTING,
                new SightingMapper());

    }

    private List<Sighting> associateLocationWithSighting(List<Sighting> sightingList) {
        for (Sighting currentSighting : sightingList) {
            currentSighting.setLocation(findLocationForSighting(currentSighting));
        }
        return sightingList;
    }

    private Location findLocationForSighting(Sighting sighting) {
        return jdbcTemplate.queryForObject(SQL_SELECT_LOCATION_BY_SIGHTINGID,
                new LocationMapper(),
                sighting.getSightingId());
    }
    
    @Override
    public List<Sighting> findSightingForSuper(Super superperson){
        return jdbcTemplate.query(SQL_SELECT_SIGHTING_BY_SUPERID,
                new SightingMapper(),
                superperson.getSuperId());
    }


    private static final class SightingMapper implements RowMapper<Sighting> {

        @Override
        public Sighting mapRow(ResultSet rs, int i) throws SQLException {
            Sighting s = new Sighting();
            s.setSightingId(rs.getInt("sightingId"));
            s.setDate(rs.getTimestamp("date"));
            return s;
        }
    }

    private static final class LocationMapper implements RowMapper<Location> {

        @Override
        public Location mapRow(ResultSet rs, int i) throws SQLException {
            Location l = new Location();
            l.setLocationId(rs.getInt("locationId"));
            l.setName(rs.getString("name"));
            l.setDescription(rs.getString("description"));
            l.setAddress(rs.getString("address"));
            l.setCity(rs.getString("city"));
            l.setState(rs.getString("state"));
            l.setZipcode(rs.getString("zipcode"));
            l.setLatitude(rs.getBigDecimal("latitude"));
            l.setLongitude(rs.getBigDecimal("longitude"));

            return l;
        }
    }

}
