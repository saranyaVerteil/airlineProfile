package com.verteil.airlineprofile.core.biz.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.verteil.airlineprofile.common.biz.AbstractAirlineProfileService;
import com.verteil.airlineprofile.common.dto.AirlineCodeRequestDto;
import com.verteil.airlineprofile.common.dto.AirlineProfilesDto;
import com.verteil.airlineprofile.common.exception.AirlineProfileException;
import com.verteil.commons.support.dto.RequestWrapperDto;
import com.verteil.opendata.common.dto.CitiesDto;
import com.verteil.opendata.common.dto.OpendataServiceDto;

public class AirlineProfileServiceImpl extends AbstractAirlineProfileService{
	
	@Override
	public List<AirlineProfilesDto> loadSectorsForAirline(String airlineCode) {
		return airlineProfilePsqlDao.readAirlineSectors(airlineCode);
	}

	@Override
	public Object process(RequestWrapperDto arg0) {
		return null;
	}

	@Override
	public List<String> getAirlineCodes(AirlineCodeRequestDto airlineCodeRequestDto) {
		String validator = airlineRequestParamValidator(airlineCodeRequestDto);
		if (validator != null)
			throw new AirlineProfileException(0, validator);
		String deptCountryCode = getCountryCodeFromAirportCode(airlineCodeRequestDto.getDeptAirportCode());
		airlineCodeRequestDto.setDeptCountryCode(deptCountryCode);
		String arrvCountryCode = getCountryCodeFromAirportCode(airlineCodeRequestDto.getArrvAirportCode());
		airlineCodeRequestDto.setArrvCountryCode(arrvCountryCode);
		return airlineProfilePsqlDao.readAirlineCodes(airlineCodeRequestDto);
	}
	
	private String getCountryCodeFromAirportCode(String airportCode) {
		List<String> fields = new ArrayList<>();
		fields.add(ODS_COLUMN_NAME_COUNTRY_CODE);
		Map<String, List<String>> criteria = new HashMap<String, List<String>>();
		List<String> isoCodes = new ArrayList<String>();
		isoCodes.add(airportCode);
		criteria.put(ODS_COLUMN_NAME_ISO_CODE, isoCodes );
		RequestWrapperDto requestWrapper = new RequestWrapperDto();
		OpendataServiceDto opendataServiceDto = new OpendataServiceDto();
		opendataServiceDto.setEntitiesKeyName(ODS_TABLE_NAME_CITIES);
		opendataServiceDto.setCriteria(criteria);
		opendataServiceDto.setFields(fields);
		requestWrapper.setRequest(opendataServiceDto);
		requestWrapper.setContentType(ODS_CITIES_REQUEST_TYPE);
		Object response  = opendataServiceClientImpl.doRemoteCall(requestWrapper);
		if(response.equals(null)) {
			throw new AirlineProfileException(0, "Unable to find country code!");
		}		
		List<CitiesDto> citiesDtos = (List<CitiesDto>) response;
		String countryCode = citiesDtos.get(0).getCountry_code();
		return countryCode;
	}
	
	private String airlineRequestParamValidator(AirlineCodeRequestDto airlineCodeRequestDto){
		String errorMessage = null;
		if (airlineCodeRequestDto.getDeptAirportCode() == null || airlineCodeRequestDto.getDeptAirportCode().isEmpty())
			errorMessage = "Departure airport code is empty.";
		else if (airlineCodeRequestDto.getDeptCountryCode() == null || airlineCodeRequestDto.getDeptCountryCode().isEmpty())
			errorMessage = "Departure country code is empty.";
		else if (airlineCodeRequestDto.getArrvAirportCode() == null || airlineCodeRequestDto.getArrvAirportCode().isEmpty())
			errorMessage = "Arrival airport code is empty.";
		else if (airlineCodeRequestDto.getArrvCountryCode() == null || airlineCodeRequestDto.getArrvCountryCode().isEmpty())
			errorMessage = "Arrival country code is empty.";
		return errorMessage;
	}
}
