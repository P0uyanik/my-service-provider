package com.example.finalprojectbootcamp.mapper;

import com.example.finalprojectbootcamp.core.entities.Customer;
import com.example.finalprojectbootcamp.core.entities.Expert;
import com.example.finalprojectbootcamp.dto.CustomerDto;
import com.example.finalprojectbootcamp.dto.ExpertDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ExpertMapper {
    ExpertMapper INSTANCE = Mappers.getMapper(ExpertMapper.class) ;
    ExpertDto toExpertDto (Expert expert) ;
    Expert toExpert (ExpertDto expertDto) ;
    List<ExpertDto> toExpertDtoAsList (List<Expert> expertList) ;
    List<Expert> toExpertAsList (List<ExpertDto> expertDtoS) ;

}
