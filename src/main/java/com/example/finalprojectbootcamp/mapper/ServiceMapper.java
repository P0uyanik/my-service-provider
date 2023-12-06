package com.example.finalprojectbootcamp.mapper;
import com.example.finalprojectbootcamp.core.entities.Service;
import com.example.finalprojectbootcamp.dto.ServiceDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


import java.util.List;

@Mapper
public interface ServiceMapper {
    ServiceMapper INSTANCE = Mappers.getMapper(ServiceMapper.class) ;
    ServiceDto toServiceDto (Service service) ;
    Service toService (ServiceDto serviceDto) ;
    List<ServiceDto> toServiceDtoAsList (List<Service> serviceList) ;
    List<Service> toServiceAsList (List<ServiceDto> serviceDtoS) ;
}
