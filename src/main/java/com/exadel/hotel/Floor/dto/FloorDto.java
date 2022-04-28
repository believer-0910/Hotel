package com.exadel.hotel.Floor.dto;

import com.exadel.hotel.hotel.dto.HotelDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class FloorDto {
    private int number;

    private HotelDto hotelDto;
}
