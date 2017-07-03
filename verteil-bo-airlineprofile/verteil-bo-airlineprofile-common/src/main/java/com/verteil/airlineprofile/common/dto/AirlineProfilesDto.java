package com.verteil.airlineprofile.common.dto;

public class AirlineProfilesDto {
	private String deptAirportCode;
	private String deptCountry;
	private String arrvAirportCode;
	private String arrvCountry;
	
	public String getDeptAirportCode() {
		return deptAirportCode;
	}
	public void setDeptAirportCode(String deptAirportCode) {
		this.deptAirportCode = deptAirportCode;
	}
	public String getDeptCountry() {
		return deptCountry;
	}
	public void setDeptCountry(String deptCountry) {
		this.deptCountry = deptCountry;
	}
	public String getArrvAirportCode() {
		return arrvAirportCode;
	}
	public void setArrvAirportCode(String arrvAirportCode) {
		this.arrvAirportCode = arrvAirportCode;
	}
	public String getArrvCountry() {
		return arrvCountry;
	}
	public void setArrvCountry(String arrvCountry) {
		this.arrvCountry = arrvCountry;
	}
}
