package com.verteil.airlineprofile.web;

import static com.verteil.commons.support.exception.CommonsSupportErrorCodes.COMMON_ERR_INVALID_REQUEST_TYPE;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.springframework.web.bind.annotation.RestController;

import com.verteil.airlineprofile.common.biz.AirlineProfileService;
import com.verteil.airlineprofile.common.dto.AirlineCodeRequestDto;
import com.verteil.airlineprofile.common.dto.AirlineProfilesDto;
import com.verteil.airlineprofile.common.exception.AirlineProfileException;
import com.verteil.commons.support.dto.RequestWrapperDto;
import com.verteil.commons.support.web.rest.AbstractRestService;


@RestController
@Path(value = "/")
public class AirlineProfileRestService extends AbstractRestService{
	
	private AirlineProfileService airlineProfileService;
	
	public AirlineProfileService getAirlineProfileService() {
		return airlineProfileService;
	}

	public void setAirlineProfileService(AirlineProfileService airlineProfileService) {
		this.airlineProfileService = airlineProfileService;
	}
	
	@Path(value = "airlineSectors")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<AirlineProfilesDto> getSectorsForAirline(@QueryParam("airlineCode") String airlineCode){
		List<AirlineProfilesDto> airlineSectors = airlineProfileService.loadSectorsForAirline(airlineCode);
		return airlineSectors;
	}
	
	@Path(value = "airlines")
	@POST
	@Consumes (MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public List<String> getAirlineCodes(AirlineCodeRequestDto airlineCodeRequestDto) {
		if(airlineCodeRequestDto == null) {
			throw new AirlineProfileException(COMMON_ERR_INVALID_REQUEST_TYPE, "Invalid request type! Received null.");
		}
		List<String> airlines = airlineProfileService.getAirlineCodes(airlineCodeRequestDto);
		return airlines;
	}
		
	@Override
	public Object process(RequestWrapperDto arg0) {
		// TODO Auto-generated method stub
		return null;
	}

}
