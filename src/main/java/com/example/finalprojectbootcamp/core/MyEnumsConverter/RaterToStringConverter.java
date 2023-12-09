package com.example.finalprojectbootcamp.core.MyEnumsConverter;

import com.example.finalprojectbootcamp.core.enums.Rater;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class RaterToStringConverter implements AttributeConverter <Rater , String> {
    @Override
    public String convertToDatabaseColumn(Rater attribute) {
        return  attribute.toString() ;
    }

    @Override
    public Rater convertToEntityAttribute(String dbData) {
        return  Rater.valueOf(dbData)  ;
    }
}
