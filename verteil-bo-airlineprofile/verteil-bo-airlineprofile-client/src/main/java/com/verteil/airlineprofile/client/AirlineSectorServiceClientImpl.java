package com.verteil.airlineprofile.client;

import java.util.List;

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

import com.verteil.airlineprofile.common.client.AbstractAirlineProfileSectorServiceClient;
import com.verteil.airlineprofile.common.dto.AirlineCodeRequestDto;
import com.verteil.airlineprofile.common.dto.AirlineProfilesDto;
import com.verteil.commons.support.dto.RequestWrapperDto;

public class AirlineSectorServiceClientImpl extends AbstractAirlineProfileSectorServiceClient {

	@Override
	public Object process(RequestWrapperDto arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object doRemoteCall(RequestWrapperDto arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AirlineProfilesDto> fetchAirlineSectors(String airlineCode) {
		WebTarget target = createWebTarget(AIRLINEPROFILE_GET_SECTOR_URL, false);
		target = target.queryParam("airlineCode", airlineCode);
		Builder request = target.request();
		List<AirlineProfilesDto> airlineSectors = request.get(new GenericType<List<AirlineProfilesDto>>() {});
		return airlineSectors;
	}

	@Override
	public List<String> fetchAirlineCodes(AirlineCodeRequestDto airlineCodeRequestDto) {
		WebTarget target = createWebTarget(AIRLINES_GET_URL, false);
		Builder request = target.request();
		List<String> airlines = request.post(Entity.entity(airlineCodeRequestDto, MediaType.APPLICATION_JSON), new GenericType<List<String>>() {}); 
		return airlines;
	}


}
