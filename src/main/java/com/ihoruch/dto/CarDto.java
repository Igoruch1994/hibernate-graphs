package com.ihoruch.dto;

import com.ihoruch.enums.Colour;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CarDto {

    private String model;
    private Colour colour;

}
