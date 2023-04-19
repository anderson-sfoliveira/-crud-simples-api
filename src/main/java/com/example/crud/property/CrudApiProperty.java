package com.example.crud.property;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("crud-api")
public class CrudApiProperty {

	private String[] originPermitida;

	public String[] getOriginPermitida() {
		return originPermitida;
	}

	public void setOriginPermitida(String[] originPermitida) {
		this.originPermitida = originPermitida;
	}
}
