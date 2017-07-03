package com.verteil.airlineprofile.common.client;

import java.util.List;

import com.verteil.airlineprofile.common.dto.AirlineCodeRequestDto;
import com.verteil.airlineprofile.common.dto.AirlineProfilesDto;
import com.verteil.commons.support.BaseService;

public interface AirlineProfileSectorServiceClient extends BaseService {

	String AIRLINEPROFILE_GET_SECTOR_URL = "airlineprofile.sector.airlineId.url";
	String AIRLINES_GET_URL = "airlineprofile.airlines.url";
	
	public List<AirlineProfilesDto> fetchAirlineSectors(String airlineCode);
	
	public List<String> fetchAirlineCodes(AirlineCodeRequestDto airlineCodeRequestDto);
}
