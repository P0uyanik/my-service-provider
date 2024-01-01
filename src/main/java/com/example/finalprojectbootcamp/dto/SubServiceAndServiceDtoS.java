package com.example.finalprojectbootcamp.dto;

import jakarta.validation.Valid;
import lombok.*;

import java.io.Serializable;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SubServiceAndServiceDtoS implements Serializable {
  @Valid private SubServiceDto subServiceDto;
  @Valid private ServiceDto serviceDto;

}


















