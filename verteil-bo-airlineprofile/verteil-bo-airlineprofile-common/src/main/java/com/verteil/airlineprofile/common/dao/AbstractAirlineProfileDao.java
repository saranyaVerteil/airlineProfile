package com.verteil.airlineprofile.common.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.verteil.airlineprofile.common.dto.AirlineProfilesDto;
import com.verteil.airlineprofile.common.exception.AirlineProfileException;
import com.verteil.commons.genericdao.dao.AbstractDaoImpl;

public abstract class AbstractAirlineProfileDao extends AbstractDaoImpl implements AirlineProfileDao{

	private static final Logger LOGGER = LoggerFactory.getLogger(AbstractAirlineProfileDao.class);
	
	public ResultSetExtractor<List<AirlineProfilesDto>> createResultSetExtractor() {
		return new ResultSetExtractor<List<AirlineProfilesDto>>() {
			@Override
			public List<AirlineProfilesDto> extractData(ResultSet rs) {
				List<AirlineProfilesDto> sectors = null;
				try {
					 while(rs.next()) {
						if (sectors == null) {
							sectors = new ArrayList<AirlineProfilesDto>();
						}
						AirlineProfilesDto airlineProfilesDto = createAirlineProfileDto(rs);
						sectors.add(airlineProfilesDto);
					}
				} catch (SQLException e) {
					throw new AirlineProfileException(e);
				}
				return sectors;
			}
		};
	}
	
	public ResultSetExtractor<List<String>> createAirlineCodeResultExtractor() {
		return new ResultSetExtractor<List<String>>() {
			@Override
			public List<String> extractData(ResultSet rs) {
				List<String> airlines = null;
				try {
					 while(rs.next()) {
						if (airlines == null) {
							airlines = new ArrayList<String>();
						}
						airlines.add(rs.getString(COLUMN_NAME_AIRLINE_CODE));
					}
				} catch (SQLException e) {
					throw new AirlineProfileException(e);
				}
				return airlines;
			}
		};
	}
	
	private AirlineProfilesDto createAirlineProfileDto(ResultSet rs){
		AirlineProfilesDto airlineProfileDto = new AirlineProfilesDto();
		try{
			airlineProfileDto.setDeptAirportCode(rs.getString(COLUMN_NAME_DEPT_AIRPORT_CODE));
			airlineProfileDto.setDeptCountry(rs.getString(COLUMN_NAME_DEPT_COUNTRY_CODE));
			airlineProfileDto.setArrvAirportCode(rs.getString(COLUMN_NAME_ARRV_AIRPORT_CODE));
			airlineProfileDto.setArrvCountry(rs.getString(COLUMN_NAME_ARRV_COUNTRY_CODE));
		}catch(SQLException e){
			throw new AirlineProfileException(e);
		}
		LOGGER.info("dto values..");
		LOGGER.info(airlineProfileDto.toString());
		return airlineProfileDto;
	}


}
