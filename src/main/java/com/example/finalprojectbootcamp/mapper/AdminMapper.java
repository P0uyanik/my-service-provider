package com.example.finalprojectbootcamp.mapper;
import com.example.finalprojectbootcamp.core.entities.Admin;
import com.example.finalprojectbootcamp.dto.AdminDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface AdminMapper {
    AdminMapper INSTANCE = Mappers.getMapper(AdminMapper.class) ;
    AdminDto toAdminDto (Admin admin) ;
    Admin toAdmin (AdminDto adminDto) ;
    List<AdminDto> toAdminDtoAsList (List<Admin> adminList) ;
    List<Admin> toAdminAsList (List<AdminDto> adminDtoS) ;
}
