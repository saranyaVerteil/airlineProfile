package com.verteil.airlineprofile.common.client;

import com.verteil.airlineprofile.common.biz.AirlineProfileNoSqlService;
import com.verteil.commons.support.web.client.AbstractRestClient;

public abstract class AbstractAirlineProfileSectorServiceClient extends AbstractRestClient implements AirlineProfileSectorServiceClient{
	private AirlineProfileNoSqlService airlineProfileService;

	public AirlineProfileNoSqlService getAirlineProfileService() {
		return airlineProfileService;
	}

	public void setAirlineProfileService(
			AirlineProfileNoSqlService airlineProfileService) {
		this.airlineProfileService = airlineProfileService;
	}
}
