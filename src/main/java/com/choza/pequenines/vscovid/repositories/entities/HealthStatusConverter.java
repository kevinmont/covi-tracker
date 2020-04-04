package com.choza.pequenines.vscovid.repositories.entities;

import java.util.stream.Stream;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class HealthStatusConverter implements AttributeConverter<HealthStatusEnum, String>  {

	@Override
	public String convertToDatabaseColumn(HealthStatusEnum attribute) {
		if (attribute == null) {
			return null;
		}
		return attribute.getStatus();
	}

	@Override
	public HealthStatusEnum convertToEntityAttribute(String dbData) {
		if (dbData == null) {
			return null;
		}

		return Stream.of(HealthStatusEnum.values()).filter(s -> s.getStatus().equals(dbData)).findFirst()
				.orElseThrow(IllegalArgumentException::new);
	}
	
}
