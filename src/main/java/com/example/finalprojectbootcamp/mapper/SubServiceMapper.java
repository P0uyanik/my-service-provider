package com.example.finalprojectbootcamp.mapper;

import com.example.finalprojectbootcamp.core.entities.Expert;
import com.example.finalprojectbootcamp.core.entities.SubService;
import com.example.finalprojectbootcamp.dto.ExpertDto;
import com.example.finalprojectbootcamp.dto.SubServiceDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface SubServiceMapper {
    SubServiceMapper INSTANCE = Mappers.getMapper(SubServiceMapper.class) ;
    SubServiceDto toSubServiceDto (SubService subService) ;
    SubService toSubService (SubServiceDto serviceDto) ;
    List<SubServiceDto> toSubServiceDtoAsList (List<SubService> serviceList) ;
    List<SubService> toSubServiceAsList (List<SubServiceDto> subServiceDtoS) ;
}
