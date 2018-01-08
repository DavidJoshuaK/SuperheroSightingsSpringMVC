/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.supersightings.dao;

import com.sg.supersightings.model.Location;
import com.sg.supersightings.model.Sighting;
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
public class LocationDaoJdbcTempImpl implements LocationDao {

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static final String SQL_INSERT_LOCATION
            = "insert into Location (Name, Description, Address, City, State, "
            + "Zipcode, Latitude, Longitude) values (?, ?, ?, ?, ?, ?, ?, ?)";

    private static final String SQL_DELETE_LOCATION
            = "delete from Location where LocationId = ?";

    private static final String SQL_UPDATE_LOCATION
            = "update Location set Name = ?, Description = ?, Address = ?, "
            + "City = ?, State = ?, "
            + "Zipcode = ?, Longitude = ?, Latitude = ? where LocationId = ?";

    private static final String SQL_SELECT_LOCATION
            = "select * from Location where LocationId = ?";

    private static final String SQL_SELECT_LOCATION_BY_SIGHTINGID
            = "select l.LocationId, l.Name, l.Description, l.Address, l.City, l.State, "
            + "l.Zipcode, l.Longitude, l.Latitude from Location l join "
            + "Sighting s on l.LocationId = s.LocationId where s.SightingId= ?";

    private static final String SQL_SELECT_ALL_LOCATION
            = "select * from Location";

    //****************
//LOCATION METHODS
//****************    
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void addLocation(Location location) {
        jdbcTemplate.update(SQL_INSERT_LOCATION,
                location.getName(),
                location.getDescription(),
                location.getAddress(),
                location.getCity(),
                location.getState(),
                location.getZipcode(),
                location.getLatitude(),
                location.getLongitude());

        int locationId = jdbcTemplate.queryForObject("select LAST_INSERT_ID()",
                Integer.class);
        location.setLocationId(locationId);
    }

    @Override
    public void deleteLocation(int locationId) {
        jdbcTemplate.update(SQL_DELETE_LOCATION, locationId);
    }

    @Override
    public void updateLocation(Location location) {
        jdbcTemplate.update(SQL_UPDATE_LOCATION,
                location.getName(),
                location.getDescription(),
                location.getAddress(),
                location.getCity(),
                location.getState(),
                location.getZipcode(),
                location.getLongitude(),
                location.getLatitude(),
                location.getLocationId());
    }

    @Override
    public Location getLocationById(int id) {
        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_LOCATION,
                    new LocationMapper(),
                    id);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Location> getAllLocations() {
        return jdbcTemplate.query(SQL_SELECT_ALL_LOCATION, new LocationMapper());
    }

    @Override
    public Location getLocationIdBySighting(Sighting sighting) {
        return jdbcTemplate.queryForObject(SQL_SELECT_LOCATION_BY_SIGHTINGID,
                new LocationMapper(),
                sighting.getSightingId());
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
