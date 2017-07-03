package com.verteil.airlineprofile.common.biz;

import com.verteil.airlineprofile.common.dao.AirlineProfileDao;
import com.verteil.commons.support.AbstractBaseService;
import com.verteil.opendata.client.impl.OpendataServiceClientImpl;

public abstract class AbstractAirlineProfileService extends AbstractBaseService implements AirlineProfileService{

	protected AirlineProfileDao airlineProfilePsqlDao;
	protected OpendataServiceClientImpl opendataServiceClientImpl;
	

	public OpendataServiceClientImpl getOpendataServiceClientImpl() {
		return opendataServiceClientImpl;
	}

	public void setOpendataServiceClientImpl(
			OpendataServiceClientImpl opendataServiceClientImpl) {
		this.opendataServiceClientImpl = opendataServiceClientImpl;
	}

	public AirlineProfileDao getAirlineProfilePsqlDao() {
		return airlineProfilePsqlDao;
	}

	public void setAirlineProfilePsqlDao(AirlineProfileDao airlineProfilePsqlDao) {
		this.airlineProfilePsqlDao = airlineProfilePsqlDao;
	}
}
