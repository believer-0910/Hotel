package com.exadel.hotel.room.dto;

import com.exadel.hotel.floor.dto.FloorDto;
import com.exadel.hotel.type.dto.TypeDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RoomDto {
    private int number;
    private TypeDto type;
    private FloorDto floor;
}
