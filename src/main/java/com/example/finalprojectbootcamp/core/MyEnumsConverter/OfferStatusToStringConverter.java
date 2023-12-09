package com.example.finalprojectbootcamp.core.MyEnumsConverter;
import com.example.finalprojectbootcamp.core.enums.OfferStatus;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class OfferStatusToStringConverter implements AttributeConverter<OfferStatus, String> {
    @Override
    public String convertToDatabaseColumn(OfferStatus attribute) {
        return attribute.name();
    }

    @Override
    public OfferStatus convertToEntityAttribute(String dbData) {
        return   Enum.valueOf(OfferStatus.class , dbData  );
    }
}
