package com.verteil.airlineprofile.common.dto;

public class AirlineCodeRequestDto {
	
	private String deptAirportCode;
	private String deptCountryCode;
	private String arrvAirportCode;
	private String arrvCountryCode;
	
	public String getDeptAirportCode() {
		return deptAirportCode;
	}
	public void setDeptAirportCode(String deptAirportCode) {
		this.deptAirportCode = deptAirportCode;
	}
	public String getDeptCountryCode() {
		return deptCountryCode;
	}
	public void setDeptCountryCode(String deptCountry) {
		this.deptCountryCode = deptCountry;
	}
	public String getArrvAirportCode() {
		return arrvAirportCode;
	}
	public void setArrvAirportCode(String arrvAirportCode) {
		this.arrvAirportCode = arrvAirportCode;
	}
	public String getArrvCountryCode() {
		return arrvCountryCode;
	}
	public void setArrvCountryCode(String arrvCountry) {
		this.arrvCountryCode = arrvCountry;
	}
}
