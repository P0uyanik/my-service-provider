package com.example.finalprojectbootcamp.core.MyEnumsConverter;
import com.example.finalprojectbootcamp.core.enums.ExpertStatus;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class ExpertStatusToStringConverter implements AttributeConverter<ExpertStatus, String> {
    @Override
    public String convertToDatabaseColumn(ExpertStatus attribute) {
        return attribute.name();
    }

    @Override
    public ExpertStatus convertToEntityAttribute(String dbData) {
        return Enum.valueOf(ExpertStatus.class , dbData) ;
    }
}
