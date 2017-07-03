package com.verteil.airlineprofile.core.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.verteil.airlineprofile.common.dao.AbstractAirlineProfileDao;
import com.verteil.airlineprofile.common.dto.AirlineCodeRequestDto;
import com.verteil.airlineprofile.common.dto.AirlineProfilesDto;


public class AirlineProfileDaoImpl extends AbstractAirlineProfileDao{

	@Override
	public List<AirlineProfilesDto> readAirlineSectors(String airlineCode) {
		StringBuilder whereClause = buildWhereClause(null, COLUMN_NAME_AIRLINE_CODE, airlineCode, null, true);
		String sql = new StringBuilder(SQL_SELECT_SECTORS).append(TABLE_NAME_SECTORS).append(whereClause).toString();
		List<AirlineProfilesDto> sectors = executeQuery(sql, createResultSetExtractor()); 
		return sectors;
	}

	@Override
	public List<String> readAirlineCodes(AirlineCodeRequestDto airlineCodeRequestDto) {
		List<String> values = new ArrayList<String>();
		values.add(ASTRIK_VALUE);
		values.add(airlineCodeRequestDto.getDeptAirportCode());
		String deptCodeInClause = buildInClause(values);
		values.remove(airlineCodeRequestDto.getDeptAirportCode());
		values.add(airlineCodeRequestDto.getArrvAirportCode());
		String arrvCodeInClause = buildInClause(values);
		StringBuilder whereClause = buildWhereClause(null, COLUMN_NAME_ARRV_AIRPORT_CODE, airlineCodeRequestDto.getArrvAirportCode(), AND_OR.AND, true);
		whereClause = buildWhereClause(whereClause, COLUMN_NAME_DEPT_COUNTRY_CODE, airlineCodeRequestDto.getDeptCountryCode(), AND_OR.AND, true);		
		String sql = new StringBuilder(SQL_SELECT_AIRLINE_CODES).append(TABLE_NAME_SECTORS)
                .append(" where ").append(COLUMN_NAME_DEPT_AIRPORT_CODE).append(deptCodeInClause)
                .append(" and ").append(COLUMN_NAME_DEPT_COUNTRY_CODE).append(" = '").append(airlineCodeRequestDto.getDeptCountryCode()).append("'")
                .append(" and ").append(COLUMN_NAME_ARRV_AIRPORT_CODE).append(arrvCodeInClause)
                .append(" and ").append(COLUMN_NAME_ARRV_COUNTRY_CODE).append(" = '").append(airlineCodeRequestDto.getArrvCountryCode()).append("'").toString();
		List<String> airlineCodes = executeQuery(sql, createAirlineCodeResultExtractor());
		return airlineCodes;
	}
	
}

