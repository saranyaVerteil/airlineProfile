/**
 * Copyright © 2016 Verteil Technologies Pvt. Ltd.
 *
 * @author  Franklin Joshua
 * @version 1.0
 * @since   2016-01-15 
 */
package com.verteil.airlineprofile.client;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

import com.verteil.airlineprofile.common.client.AbstractAirlineProfileServiceClient;
import com.verteil.airlineprofile.common.dto.AirlineProfileCriteriaDto;
import com.verteil.airlineprofile.common.exception.AirlineProfileException;
import com.verteil.commons.support.dto.KeyValueDto;
import com.verteil.commons.support.dto.RequestWrapperDto;
import com.verteil.schemas.ndc16_1.AirlineProfileRQ;
import com.verteil.schemas.ndc16_1.AirlineProfileRS;

public class AirlineProfileServiceClientImpl extends AbstractAirlineProfileServiceClient {

	@Override
	public AirlineProfileRS putAirlineProfileRQ(RequestWrapperDto requestWrapper) {
		Builder builder = createBuilderClient(AIRLINEPROFILE_URL);
		AirlineProfileRS airlineProfileRs = builder.put(Entity.json(requestWrapper), AirlineProfileRS.class);
		return airlineProfileRs;
	}

	@Override
	public List<AirlineProfileRS> fetchAirlineProfile(RequestWrapperDto requestWrapper) {
		Builder request = createBuilderClient(AIRLINEID_POST_URL);
		List<AirlineProfileRS> airlineProfiles = request.post(Entity.entity(requestWrapper, MediaType.APPLICATION_JSON),
				new GenericType<List<AirlineProfileRS>>() {});
		return airlineProfiles;
	}

	@Override
	public List<String> fetchProfileOwners(AirlineProfileCriteriaDto airProfileCriteria, List<String> activeThirdPartyIds) {
		Builder request = createBuilderClient(AIRLINEPROFILE_THIRDPARTYIDS_URL);
		List<String> serviceProvidingThirdPartyIds = request.post(Entity.entity(airProfileCriteria, MediaType.APPLICATION_JSON),
				new GenericType<List<String>>() {});
		if (serviceProvidingThirdPartyIds == null || serviceProvidingThirdPartyIds.isEmpty()) {
			return null;
		}
		Set<String> activeThirdPartyIdsSet = new HashSet<>(activeThirdPartyIds);
		List<String> serviceProvidingAndActiveThirdPartyIds = new ArrayList<>(serviceProvidingThirdPartyIds.size());
		for (String serviceProvidingThirdPartyId : serviceProvidingThirdPartyIds) {
			if (activeThirdPartyIdsSet.contains(serviceProvidingThirdPartyId)) {
				serviceProvidingAndActiveThirdPartyIds.add(serviceProvidingThirdPartyId);
			}
		}
		return serviceProvidingAndActiveThirdPartyIds;
	}

	@Override
	public List<String> fetchBlockedAgents(String airlineId) {
		WebTarget target = createWebTarget(AIRLINEPROFILE_ALL_BLOCKED_AGENTS_URL, false);
		target = target.queryParam("airlineId", airlineId);
		Builder request = target.request();
		List<String> blockedAgents = request.get(new GenericType<List<String>>() {});
		return blockedAgents;
	}

	@Override
	public List<String> fetchBlockedAirlines(String iataCode) {
		WebTarget target = createWebTarget(AIRLINEPROFILE_ALL_BLOCKED_AIRLINES_URL, false);
		target = target.queryParam("iataCode", iataCode);
		Builder request = target.request();
		List<String> blockedAgents = request.get(new GenericType<List<String>>() {});
		return blockedAgents;
	}

	@Override
	public boolean isAgentBlockedByAirline(String airlineId, String iataCode) {
		WebTarget target = createWebTarget(AIRLINEPROFILE_IS_AGENT_BLOCKED_BY_AIRLINE_URL, false);
		target = target.queryParam("airlineId", airlineId).queryParam("iataCode", iataCode);
		Builder request = target.request();
		Boolean isBlocked = request.get(new GenericType<Boolean>() {});
		return isBlocked;
	}

	@Override
	public Object process(RequestWrapperDto requestWrapper) {
		Object request = requestWrapper.getRequest();
		if (request == null) {
			throw new AirlineProfileException(0, "Invalid params! Nothing to process.");
		} else if (request instanceof AirlineProfileCriteriaDto) {
			return fetchProfileOwners((AirlineProfileCriteriaDto) request, null);
		} else if (request instanceof AirlineProfileRQ) {
			return putAirlineProfileRQ(requestWrapper);
		} else if (request instanceof KeyValueDto) {
			return fetchAirlineProfile(requestWrapper);
		} else {
			throw new AirlineProfileException(0, "Invalid params!");
		}
	}

	@Override
	public Object doRemoteCall(RequestWrapperDto requestWrapper) {
		return null;
	}

}
