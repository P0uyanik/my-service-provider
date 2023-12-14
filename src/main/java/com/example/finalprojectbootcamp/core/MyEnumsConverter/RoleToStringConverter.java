package com.example.finalprojectbootcamp.core.MyEnumsConverter;
import com.example.finalprojectbootcamp.core.enums.Role;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class RoleToStringConverter implements AttributeConverter<Role, String> {
    @Override
    public String convertToDatabaseColumn(Role attribute) {
        return  attribute.name();
    }

    @Override
    public Role convertToEntityAttribute(String dbData) {
        return Enum.valueOf(Role.class , dbData);
    }
}
