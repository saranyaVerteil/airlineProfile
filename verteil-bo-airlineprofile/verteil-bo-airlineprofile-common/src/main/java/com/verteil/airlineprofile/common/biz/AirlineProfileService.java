package com.verteil.airlineprofile.common.biz;

import java.util.List;

import com.verteil.airlineprofile.common.dto.AirlineCodeRequestDto;
import com.verteil.airlineprofile.common.dto.AirlineProfilesDto;

public interface AirlineProfileService {
	
	String ODS_TABLE_NAME_CITIES = "cities";
	String ODS_COLUMN_NAME_ISO_CODE = "code";
	String ODS_COLUMN_NAME_COUNTRY_CODE = "country_code";
	String ODS_CITIES_REQUEST_TYPE = "application/json";
	
	public List<AirlineProfilesDto> loadSectorsForAirline(String airlineCode);
	
	public List<String> getAirlineCodes(AirlineCodeRequestDto airlineCodeRequestDto);

}
