package com.example.finalprojectbootcamp.mapper;

import com.example.finalprojectbootcamp.core.entities.Expert;
import com.example.finalprojectbootcamp.core.entities.Offer;
import com.example.finalprojectbootcamp.dto.ExpertDto;
import com.example.finalprojectbootcamp.dto.OfferDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface OfferMapper {
    OfferMapper INSTANCE = Mappers.getMapper(OfferMapper.class) ;
    OfferDto toOfferDto (Offer offer) ;
    Offer toOffer (OfferDto offerDto) ;
    List<OfferDto> toOfferDtoAsList (List<Offer> offerList) ;
    List<Offer> toOfferAsList (List<OfferDto> offerDtoS) ;
}
