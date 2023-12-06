package com.example.finalprojectbootcamp.mapper;

import com.example.finalprojectbootcamp.core.entities.Credit;
import com.example.finalprojectbootcamp.dto.CreditDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CreditMapper {
    CreditMapper INSTANCE = Mappers.getMapper(CreditMapper.class) ;
    CreditDto toCreditDto (Credit credit) ;
    Credit toCredit (CreditDto creditDto) ;
    List<CreditDto> toCreditDtoAsList (List<Credit> creditList) ;
    List<Credit> toCreditAsList (List<CreditDto> creditList) ;
}
